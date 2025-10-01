package algorithms;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

public class KadanesAlgorithmBenchmark {
    @Param({"100", "1000", "10000", "100000"})
    public int size;
    public int[] arr;

    @Setup(Level.Trial)
    public void setup() {
        arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 2000 - 1000);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public int[] benchmarkKadanesAlgorithm() {
        return new KadanesAlgorithm().findMaxSubarray(arr);
    }
}