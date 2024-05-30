package org.khasanof.threadPools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @author Nurislom
 * @see org.khasanof.threadPools
 * @since 08.07.2023 21:06
 */
public class CompletionStageTest {

    @Test
    void completionStageTest() {
        CompletionStage<String> stage = new CompletableFuture<>();
    }

}
