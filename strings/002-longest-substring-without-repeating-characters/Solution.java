/*
 * Day 2 (2026-10-05) - Longest Substring Without Repeating Characters
 * Topic: Strings | Difficulty: Medium
 * Time: O(n): each character is visited once by the right pointer, and the left pointer only moves forward.
 * Space: O(1): a fixed 128-entry table regardless of input size (O(k) for an alphabet of size k).
 */

import java.util.*;

public class Solution {

    /**
     * Returns the length of the longest substring of s with all distinct characters.
     * Uses a sliding window with a last-seen-index table.
     */
    public static int lengthOfLongestSubstring(String s) {
        int[] last = new int[128];      // last index where each ASCII char was seen
        Arrays.fill(last, -1);
        int left = 0, best = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            // If c already occurs inside the current window, shrink the window past it
            if (last[c] >= left) {
                left = last[c] + 1;
            }
            last[c] = right;
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    private static void check(boolean ok, String name) {
        if (!ok) throw new RuntimeException("FAILED: " + name);
    }

    public static void main(String[] args) {
        check(lengthOfLongestSubstring("abcabcbb") == 3, "example 1");
        check(lengthOfLongestSubstring("pwwkew") == 3, "example 2");
        check(lengthOfLongestSubstring("") == 0, "empty string");
        check(lengthOfLongestSubstring("bbbbb") == 1, "all same");
        check(lengthOfLongestSubstring("abba") == 2, "left must not move backward");
        check(lengthOfLongestSubstring("a b!c") == 5, "spaces and symbols");
        System.out.println("All tests passed!");
    }
}
