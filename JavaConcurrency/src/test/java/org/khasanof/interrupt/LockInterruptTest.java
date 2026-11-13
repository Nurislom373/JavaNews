package org.khasanof.interrupt;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LockInterruptTest {

    private final LockInterruptExamples examples = new LockInterruptExamples();

    // ---------- synchronized ----------

    @Test
    void threadBlockedOnSynchronizedIgnoresInterrupt() throws Exception {
        var outcome = examples.synchronizedBlockedIsNotInterruptible();

        assertThat(outcome.interruptedExceptionThrown()).isFalse();
        assertThat(outcome.acquiredLock()).isTrue();        // когда монитор освободился, поток всё равно зашёл внутрь
        assertThat(outcome.flagAfterAcquire()).isTrue();    // флаг сохранился
    }

    @Test
    void waitInsideSynchronizedIsInterruptible() throws Exception {
        var outcome = examples.synchronizedWaitIsInterruptible();

        assertThat(outcome.interruptedExceptionThrown()).isTrue();
        assertThat(outcome.acquiredLock()).isTrue();        // монитор снова взят перед исключением
        assertThat(outcome.flagAfterAcquire()).isFalse();
    }

    // ---------- ReentrantLock ----------

    @Test
    void lockIgnoresInterruptWhileWaiting() throws Exception {
        var outcome = examples.lockIsNotInterruptible();

        assertThat(outcome.interruptedExceptionThrown()).isFalse();
        assertThat(outcome.acquiredLock()).isTrue();
        assertThat(outcome.flagAfterAcquire()).isTrue();
    }

    @Test
    void lockInterruptiblyThrowsAndDoesNotAcquire() throws Exception {
        var outcome = examples.lockInterruptiblyIsInterruptible();

        assertThat(outcome.interruptedExceptionThrown()).isTrue();
        assertThat(outcome.acquiredLock()).isFalse();
        assertThat(outcome.flagAfterAcquire()).isFalse();
    }

    @Test
    void tryLockWithTimeoutRespondsToInterrupt() throws Exception {
        var outcome = examples.tryLockWithTimeoutIsInterruptible();

        assertThat(outcome.interruptedExceptionThrown()).isTrue();
        assertThat(outcome.acquiredLock()).isFalse();
    }

    @Test
    void conditionAwaitThrowsButReacquiresLockFirst() throws Exception {
        var outcome = examples.conditionAwaitIsInterruptible();

        assertThat(outcome.interruptedExceptionThrown()).isTrue();
        assertThat(outcome.acquiredLock()).isTrue();
    }

    @Test
    void lockIsReleasedInFinallyEvenAfterInterrupt() throws Exception {
        assertThat(examples.lockReleasedAfterInterrupt()).isTrue();
    }
}
