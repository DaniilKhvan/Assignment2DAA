package algorithms;

import java.util.Arrays;

public class KadanesAlgorithm {
    private long comparisons = 0;
    private long arrayAccesses = 0;
    private int allocations = 1;

    public int[] findMaxSubarray(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int maxSum = arr[0];
        int currentSum = arr[0];
        int start = 0, end = 0, tempStart = 0;
        arrayAccesses += 2;

        for (int i = 1; i < arr.length; i++) {
            arrayAccesses++;
            comparisons++;
            if (arr[i] > currentSum + arr[i]) {
                currentSum = arr[i];
                tempStart = i;
            } else {
                currentSum += arr[i];
            }

            comparisons++;
            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }

        return new int[]{maxSum, start, end};
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getArrayAccesses() {
        return arrayAccesses;
    }

    public int getAllocations() {
        return allocations;
    }

    public void resetMetrics() {
        comparisons = 0;
        arrayAccesses = 0;
        allocations = 1;
    }

    public static void main(String[] args) {
        KadanesAlgorithm kadane = new KadanesAlgorithm();
        int[] sizes = {100, 1000, 10000, 100000};

        try (java.io.FileWriter writer = new java.io.FileWriter("kadane_metrics.csv")) {
            writer.write("n,time_ms,comparisons,array_accesses,allocations\n");
            for (int n : sizes) {
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
                writer.write(String.format("%d,%.2f,%d,%d,%d\n",
                        n, timeMs, kadane.getComparisons(), kadane.getArrayAccesses(), kadane.getAllocations()));
            }
        } catch (java.io.IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }
}