package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KadanesAlgorithmTest {
    @Test
    void testNormalCase() {
        KadanesAlgorithm kadane = new KadanesAlgorithm();
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] result = kadane.findMaxSubarray(arr);
        assertArrayEquals(new int[]{6, 3, 6}, result);
    }

    @Test
    void testAllNegative() {
        KadanesAlgorithm kadane = new KadanesAlgorithm();
        int[] arr = {-3, -1, -2, -4};
        int[] result = kadane.findMaxSubarray(arr);
        assertArrayEquals(new int[]{-1, 1, 1}, result);
    }

    @Test
    void testSingleElement() {
        KadanesAlgorithm kadane = new KadanesAlgorithm();
        int[] arr = {5};
        int[] result = kadane.findMaxSubarray(arr);
        assertArrayEquals(new int[]{5, 0, 0}, result);
    }

    @Test
    void testEmptyArray() {
        KadanesAlgorithm kadane = new KadanesAlgorithm();
        assertThrows(IllegalArgumentException.class, () -> kadane.findMaxSubarray(new int[]{}));
    }

    @Test
    void testNullArray() {
        KadanesAlgorithm kadane = new KadanesAlgorithm();
        assertThrows(IllegalArgumentException.class, () -> kadane.findMaxSubarray(null));
    }
}