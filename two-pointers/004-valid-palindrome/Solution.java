/*
 * Day 4 (2026-10-06) - Valid Palindrome
 * Topic: Two Pointers | Difficulty: Easy
 * Time: O(n) — each pointer moves across the string at most once.
 * Space: O(1) — only two index variables are used; no cleaned copy is built.
 */

import java.util.*;

public class Solution {

    /**
     * Returns true if s is a palindrome when considering only
     * alphanumeric characters and ignoring case.
     */
    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            // Skip non-alphanumeric characters from the left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // Skip non-alphanumeric characters from the right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            // Compare the symmetric pair case-insensitively
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static void check(boolean ok, String name) {
        if (!ok) throw new RuntimeException("FAILED: " + name);
    }

    public static void main(String[] args) {
        check(isPalindrome("Was it a car or a cat I saw?"), "example 1");
        check(!isPalindrome("race a car"), "example 2");
        check(isPalindrome(" "), "only non-alphanumeric -> empty -> true");
        check(isPalindrome("a"), "single char");
        check(!isPalindrome("0P"), "digit vs letter mismatch");
        check(isPalindrome("A man, a plan, a canal: Panama"), "classic panama");
        System.out.println("All tests passed!");
    }
}
