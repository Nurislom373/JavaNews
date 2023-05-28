package org.khasanof.tempraturerxjava;

import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import rx.Observable;
import rx.Subscriber;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class TempratureRxJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TempratureRxJavaApplication.class, args);
    }

}

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Temperature {
    private double value;
}

@Component
class TemperatureSensor {

    private final Random random = new Random();

    private final Observable<Temperature> temperatureObservable = Observable
            .range(0, Integer.MAX_VALUE)
            .concatMap(tick -> Observable
                    .just(tick)
                    .delay(random.nextInt(5000), TimeUnit.MILLISECONDS)
                    .map(tickVal -> this.probe()))
            .publish()
            .refCount();

    private Temperature probe() {
        return new Temperature(16 + random.nextGaussian() * 10); // (11)
    }

    public Observable<Temperature> temperatureStream() { // (12)
        return temperatureObservable;
    }

}

@RestController
class TemperatureController {

    private static final Logger log = LoggerFactory.getLogger(TemperatureController.class);

    private final TemperatureSensor temperatureSensor;

    public TemperatureController(TemperatureSensor temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }

    @RequestMapping(value = "/temperature-stream", method = RequestMethod.GET)
    public SseEmitter events(HttpServletRequest request) {
        RxSeeEmitter emitter = new RxSeeEmitter();
        log.info("[{}] Rx SSE stream opened for client: {}",
                emitter.getSessionId(), request.getRemoteAddr());

        temperatureSensor.temperatureStream()
                .subscribe(temperature -> emitter.getSubscriber());

        return emitter;
    }

    static class RxSeeEmitter extends SseEmitter {
        static final long SSE_SESSION_TIMEOUT = 30 * 60 * 1000L;
        private final static AtomicInteger sessionIdSequence = new AtomicInteger(0);

        private final int sessionId = sessionIdSequence.incrementAndGet();
        private final Subscriber<Temperature> subscriber;

        RxSeeEmitter() {
            super(SSE_SESSION_TIMEOUT);

            this.subscriber = new Subscriber<>() {

                @Override
                public void onNext(Temperature temperature) {
                    try {
                        RxSeeEmitter.this.send(temperature);
                        log.info("[{}] << {} ", sessionId, temperature.getValue());
                    } catch (IOException e) {
                        log.warn("[{}] Can not send event to SSE, closing subscription, message: {}",
                                sessionId, e.getMessage());
                        subscriber.onCompleted();
                    }
                }

                @Override
                public void onCompleted() {
                    log.warn("[{}] Stream completed", sessionId);
                }

                @Override
                public void onError(Throwable e) {
                    log.warn("[{}] Received sensor error: {}", sessionId, e.getMessage());
                }
            };

            onCompletion(() -> {
                log.info("[{}] SSE completed", sessionId);
                subscriber.onCompleted();
            });
            onTimeout(() -> {
                log.info("[{}] SSE timeout", sessionId);
                subscriber.onCompleted();
            });
        }

        Subscriber<Temperature> getSubscriber() {
            return subscriber;
        }

        int getSessionId() {
            return sessionId;
        }
    }

}