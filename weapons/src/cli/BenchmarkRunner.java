package cli;

import algorithms.KadanesAlgorithm;
import java.io.FileWriter;
import java.io.IOException;

public class BenchmarkRunner {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java BenchmarkRunner <input_size>");
            return;
        }

        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input size: " + args[0]);
            return;
        }

        KadanesAlgorithm kadane = new KadanesAlgorithm();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * 2000 - 1000);
        }

        kadane.resetMetrics();
        long startTime = System.nanoTime();
        int[] result = kadane.findMaxSubarray(arr);
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1_000_000.0;

        System.out.printf("n=%d, MaxSum=%d, Start=%d, End=%d, Time=%.2f ms, Comparisons=%d, ArrayAccesses=%d\n",
                n, result[0], result[1], result[2], timeMs, kadane.getComparisons(), kadane.getArrayAccesses());

        try (FileWriter writer = new FileWriter("kadane_metrics.csv", true)) {
            writer.write(String.format("%d,%.2f,%d,%d,%d\n",
                    n, timeMs, kadane.getComparisons(), kadane.getArrayAccesses(), kadane.getAllocations()));
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }
}