package org.khasanof.interrupt;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ThreadInterruptTest {

    private final ThreadInterruptExamples examples = new ThreadInterruptExamples();

    @Test
    void sleepingThreadThrowsInterruptedExceptionAndFlagIsCleared() throws Exception {
        var result = examples.interruptSleepingThread();

        assertThat(result.interruptedExceptionThrown()).isTrue();
        assertThat(result.flagAfterCatch()).isFalse();
    }

    @Test
    void interruptBeforeSleepMakesSleepFailImmediately() {
        assertThat(examples.interruptBeforeSleep()).isTrue();
    }

    @Test
    void cpuBoundLoopStopsOnlyBecauseItChecksTheFlag() throws Exception {
        // цикл завершился, а флаг всё ещё true (его никто не сбрасывал)
        assertThat(examples.cooperativeCancellation()).isTrue();
    }

    @Test
    void interruptIsIgnoredIfThreadNeverChecksFlag() throws Exception {
        assertThat(examples.ignoredInterruptKeepsRunning()).isTrue();
    }

    @Test
    void isInterruptedKeepsFlagButStaticInterruptedClearsIt() {
        assertThat(examples.isInterruptedVsInterrupted())
                .containsExactly(true, true, true, false);
    }

    @Test
    void swallowingInterruptedExceptionLosesTheInterrupt() throws Exception {
        assertThat(examples.swallowedInterruptIsLost()).isFalse();
    }

    @Test
    void restoringInterruptFlagPreservesTheInterrupt() throws Exception {
        assertThat(examples.restoredInterruptIsPreserved()).isTrue();
    }

    @Test
    void joinIsInterruptible() throws Exception {
        assertThat(examples.interruptWhileJoining()).isTrue();
    }

    @Test
    void objectWaitIsInterruptible() throws Exception {
        assertThat(examples.interruptWaitingThread()).isTrue();
    }
}
