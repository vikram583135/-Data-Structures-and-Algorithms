/*
 * Day 3 (2026-10-05) - Subarray Sum Equals K
 * Topic: Hashing | Difficulty: Medium
 * Time: O(n) — a single pass, with expected O(1) hash map operations per element.
 * Space: O(n) — the hash map may store up to n+1 distinct prefix sums.
 */

import java.util.*;

public class Solution {

    /**
     * Counts contiguous subarrays whose sum equals k using prefix sums + hashing.
     */
    public static int subarraySum(int[] nums, int k) {
        // prefix sum value -> number of times it has occurred so far
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1); // empty prefix, allows subarrays starting at index 0
        int sum = 0;
        int result = 0;
        for (int x : nums) {
            sum += x;
            // number of earlier prefixes P[j] with P[i] - P[j] == k
            result += count.getOrDefault(sum - k, 0);
            count.merge(sum, 1, Integer::sum);
        }
        return result;
    }

    // Brute force reference for verification.
    private static int brute(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int s = 0;
            for (int j = i; j < nums.length; j++) {
                s += nums[j];
                if (s == k) res++;
            }
        }
        return res;
    }

    private static void check(boolean ok, String name) {
        if (!ok) throw new RuntimeException("FAILED: " + name);
    }

    public static void main(String[] args) {
        check(subarraySum(new int[]{1, 1, 1}, 2) == 2, "example 1");
        check(subarraySum(new int[]{1, 2, 3}, 3) == 2, "example 2");
        check(subarraySum(new int[]{5}, 5) == 1 && subarraySum(new int[]{5}, 3) == 0, "single element");
        check(subarraySum(new int[]{0, 0, 0}, 0) == 6, "all zeros k=0");
        check(subarraySum(new int[]{1, -1, 1, -1}, 0) == 4, "negatives");
        // randomized cross-check with brute force
        Random rnd = new Random(42);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(30);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(11) - 5;
            int k = rnd.nextInt(11) - 5;
            check(subarraySum(a, k) == brute(a, k), "random " + t);
        }
        System.out.println("All tests passed!");
    }
}
