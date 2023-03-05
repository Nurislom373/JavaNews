package org.khasanof.customTakeWhile;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/5/2023
 * <br/>
 * Time: 5:36 PM
 * <br/>
 * Package: org.khasanof.customTakeWhile
 */
public class CustomSpliterator<T> extends Spliterators.AbstractSpliterator<T> {

    private Spliterator<T> spliterator;
    private Predicate<T> predicate;
    private boolean isMatched = true;

    /**
     * Creates a spliterator reporting the given estimated size and
     * additionalCharacteristics.
     */
    protected CustomSpliterator(Spliterator<T> spliterator, Predicate<T> predicate) {
        super(spliterator.estimateSize(), 0);
        this.spliterator = spliterator;
        this.predicate = predicate;
    }

    /**
     * If a remaining element exists, performs the given action on it,
     * returning {@code true}; else returns {@code false}.  If this
     * Spliterator is {@link #ORDERED} the action is performed on the
     * next element in encounter order.  Exceptions thrown by the
     * action are relayed to the caller.
     * <p>
     * Subsequent behavior of a spliterator is unspecified if the action throws
     * an exception.
     *
     * @param action The action
     * @return {@code false} if no remaining elements existed
     * upon entry to this method, else {@code true}.
     * @throws NullPointerException if the specified action is null
     */
    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        boolean hadNext = spliterator.tryAdvance(elem -> {
            if (predicate.test(elem) && isMatched) {
                action.accept(elem);
            } else {
                isMatched = false;
            }
        });
        return hadNext && isMatched;
    }
}
