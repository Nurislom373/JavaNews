package org.khasanof;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Observable<String> observable = Observable.create(
                emitter -> {
                    emitter.onNext("Hello RxJava 1!");
                    emitter.onError(new RuntimeException("RxJava Error!"));
                    emitter.onComplete();
                }
        );
        Disposable subscribe = observable.subscribe(
                System.out::println,
                System.err::println,
                () -> System.out.println("Done!")
        );
        subscribe.dispose();
    }



    private Maybe<Integer> simpleGenerateMaybe() {
        return Observable.fromIterable(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
                .map(m -> m * 2).doOnError(System.err::println)
                .doOnNext(System.out::println)
                .filter(f -> f % 2 == 0)
                .reduce(Integer::sum);
    }

}