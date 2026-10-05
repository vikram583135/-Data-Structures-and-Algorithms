/*
 * Day 1 (2026-10-05) - Two Sum
 * Topic: Arrays | Difficulty: Easy
 * Time: O(n) — a single pass with O(1) average-time hash map lookups and inserts.
 * Space: O(n) — the hash map can hold up to n entries.
 */

import java.util.*;

public class Solution {

    /**
     * Returns indices of the two numbers that add up to target.
     * Uses a one-pass hash map of value -> index.
     * Returns an empty array if no pair exists.
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Long, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // Complement needed to reach target (long avoids overflow)
            long need = (long) target - nums[i];
            Integer j = seen.get(need);
            if (j != null) {
                return new int[]{j, i}; // j < i always
            }
            // Insert after the lookup so an element never pairs with itself
            seen.put((long) nums[i], i);
        }
        return new int[0];
    }

    private static void check(boolean ok, String name) {
        if (!ok) throw new RuntimeException("FAILED: " + name);
    }

    public static void main(String[] args) {
        check(Arrays.equals(twoSum(new int[]{2, 7, 11, 15}, 9), new int[]{0, 1}), "basic");
        check(Arrays.equals(twoSum(new int[]{3, 2, 4}, 6), new int[]{1, 2}), "no self-pair");
        check(Arrays.equals(twoSum(new int[]{3, 3}, 6), new int[]{0, 1}), "duplicates");
        check(Arrays.equals(twoSum(new int[]{-1, -2, -3, -4, -5}, -8), new int[]{2, 4}), "negatives");
        check(Arrays.equals(twoSum(new int[]{1000000000, -1000000000, 5}, 0), new int[]{0, 1}), "extreme values");
        check(twoSum(new int[]{1, 2}, 10).length == 0, "no solution");
        System.out.println("All tests passed!");
    }
}
