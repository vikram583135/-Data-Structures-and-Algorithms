# Day 4: Valid Palindrome

**Topic:** Two Pointers · **Difficulty:** Easy · **Date:** 2026-10-06

**Similar to:** LeetCode 125 - Valid Palindrome

## Problem

Given a string `s`, decide whether it reads the same forwards and backwards. Only letters and digits count. Letter case does not matter, and every other character is ignored.

Return `true` if the cleaned-up string is a palindrome, otherwise `false`. An empty cleaned string counts as a palindrome.

**Example 1**
- Input: `s = "Was it a car or a cat I saw?"`
- Output: `true`
- Explanation: Keeping only alphanumerics and lowercasing gives `"wasitacaroracatisaw"`, which reads the same both ways.

**Example 2**
- Input: `s = "race a car"`
- Output: `false`
- Explanation: The cleaned string is `"raceacar"`. Reversed, it is `"racaecar"`, which is different.

**Constraints**
- `1 <= s.length <= 2 * 10^5`
- `s` consists of printable ASCII characters.

## Approach

**Brute force:** Build a new string with only the lowercased alphanumeric characters. Then compare it with its reverse. This is O(n) time but needs O(n) extra space.

**Two pointers (optimal):** Compare characters from both ends in place, so no extra string is needed.

1. Set `left = 0` and `right = n - 1`.
2. While `left < right`:
   - Move `left` forward past any non-alphanumeric characters.
   - Move `right` backward past any non-alphanumeric characters.
   - If the lowercase forms of `s[left]` and `s[right]` differ, return `false`.
   - Otherwise move both pointers inward.
3. If the loop finishes, return `true`.

**Why it is correct:** A string is a palindrome exactly when its i-th character from the start equals its i-th character from the end, for every i.
- Skipping ignored characters makes the two pointers walk through the cleaned string from both ends at once.
- So each comparison checks one symmetric pair of the cleaned string.
- Every pair is checked once, and we stop early on the first mismatch.

This matches the brute force in time but uses O(1) extra space.

## Complexity

- **Time:** O(n) — each pointer moves across the string at most once.
- **Space:** O(1) — only two index variables are used; no cleaned copy is built.

## Solution

See [`Solution.java`](Solution.java). Run the tests with:

```bash
javac Solution.java && java Solution
```
