package org.khasanof;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class MyBenchmark {

    @Benchmark
    public void testIntern(Blackhole bh) {
        for (int i = 0; i < 10_000; i++) {
            String s = "String to intern " + i;
            String t = s.intern();

            bh.consume(t);
        }
    }
}