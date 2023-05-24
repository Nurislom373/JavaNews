package org.khasanof;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        Observable<String> observable = Observable.create(
//                emitter -> {
//                    emitter.onNext("Hello RxJava 1!");
//                    emitter.onError(new RuntimeException("RxJava Error!"));
//                    emitter.onComplete();
//                }
//        );
//        Disposable subscribe = observable.subscribe(
//                System.out::println,
//                System.err::println,
//                () -> System.out.println("Done!")
//        );
//        subscribe.dispose();
//        intervalMethod();
        intervalTest();
    }

    private static void someExampleCallableAndFuture() {
        Observable<String> observable = Observable.fromCallable(() -> "Hello");
        observable.subscribe(System.out::println).dispose();

        Future<String> future = Executors.newCachedThreadPool().submit(() -> "World");
        Observable<String> frommed = Observable.fromFuture(future);
        frommed.subscribe(System.out::println).dispose();
    }

    private static void intervalMethod() throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(e -> System.out.println("Received: " + e));
        Thread.sleep(500);
    }


    private Maybe<Integer> simpleGenerateMaybe() {
        return Observable.fromIterable(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
                .map(m -> m * 2).doOnError(System.err::println)
                .doOnNext(System.out::println)
                .filter(f -> f % 2 == 0)
                .reduce(Integer::sum);
    }

    private static void intervalTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Disposable subscribe = Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(300)
                .subscribe(System.out::println);

        countDownLatch.await();
        subscribe.dispose();
    }
}