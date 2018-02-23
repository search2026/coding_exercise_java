package longest_increasing_sequence;

import java.util.*;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LongestIncreasingSequence {
    /*
        Longest Increasing Sequence. The increasing is exactly one larger than the one that came exactly before it.
        For example, [1, 2, 5, 6], the longest increasing sequence is [1, 2] or [5, 6]
        [3, 5, 6, 7, 12], the longest increasing sequence is [5, 6, 7]
     */
    public class Solution {
        int LongestIncreasingSequence(int[] nums) {
            if (nums == null || nums.length == 0)
                throw new IllegalArgumentException();

            int n = nums.length;
            if (n == 1) return 1;

            int prev = nums[0];
            int globalMax = 1, localMax = 1;
            for (int i = 1; i < n; i++) {
                if (nums[i] == prev + 1) {
                    localMax++;
                } else {
                    globalMax = Math.max(localMax, globalMax);
                    localMax = 1;
                }
                prev = nums[i];
            }
            return globalMax;
        }
    }

    /*
        Longest Increasing Sequence. The increasing is exactly one larger than the one that came before it.
        For example, [1, 5, 2, 6], the longest increasing sequence is [1, 2] or [5, 6]
        [5, 3, 6, 12, 7], the longest increasing sequence is [5, 6, 7]
     */
    public class Solution_2 {
        int LongestIncreasingSequence(int[] nums) {
            if (nums == null || nums.length == 0)
                throw new IllegalArgumentException();

            int n = nums.length;
            if (n == 1) return 1;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(nums[0], 1);
            for (int i = 1; i < n; i++) {
                if (map.containsKey(nums[i] - 1)) {
                    int seq = map.get(nums[i] - 1);
                    map.remove(nums[i] - 1);
                    map.put(nums[i], seq + 1);
                } else {
                    map.put(nums[i], 1);
                }
            }
            int globalMax = 1;
            for (int key : map.keySet()) {
                int seq = map.get(key);
                globalMax = Math.max(seq, globalMax);
            }

            return globalMax;
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new LongestIncreasingSequence().new Solution();
            assertEquals(2, sol.LongestIncreasingSequence(new int[]{1, 2, 5, 6}));
            assertEquals(1, sol.LongestIncreasingSequence(new int[]{1, 5, 2, 6}));
            assertEquals(3, sol.LongestIncreasingSequence(new int[]{3, 5, 6, 7, 12}));
            assertEquals(3, sol.LongestIncreasingSequence(new int[]{1, 4, 5, 6, 8, 10, 11}));
        }

        @Test
        public void test2() {
            Solution_2 sol = new LongestIncreasingSequence().new Solution_2();
            assertEquals(2, sol.LongestIncreasingSequence(new int[]{1, 2, 5, 6}));
            assertEquals(2, sol.LongestIncreasingSequence(new int[]{1, 5, 2, 6}));
            assertEquals(3, sol.LongestIncreasingSequence(new int[]{3, 5, 6, 7, 12}));
            assertEquals(3, sol.LongestIncreasingSequence(new int[]{5, 3, 6, 12, 7}));
            assertEquals(3, sol.LongestIncreasingSequence(new int[]{1, 4, 5, 6, 8, 10, 11}));
            assertEquals(3, sol.LongestIncreasingSequence(new int[]{1, 4, 2, 5, 3, 9, 10}));
        }
    }
}

