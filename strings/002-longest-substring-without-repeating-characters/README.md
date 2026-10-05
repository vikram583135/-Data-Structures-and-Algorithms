# Day 2: Longest Substring Without Repeating Characters

**Topic:** Strings · **Difficulty:** Medium · **Date:** 2026-10-05

**Similar to:** LeetCode 3 - Longest Substring Without Repeating Characters

## Problem

Given a string `s`, determine the length of the longest contiguous substring in which no character appears more than once.

**Example 1**
- Input: `s = "abcabcbb"`
- Output: `3`
- Explanation: `"abc"` has 3 distinct characters. Any longer window would contain a repeated letter.

**Example 2**
- Input: `s = "pwwkew"`
- Output: `3`
- Explanation: `"wke"` is the longest valid substring. `"pwke"` does not count because it is a subsequence, not a contiguous substring.

**Constraints**
- `0 <= s.length <= 5 * 10^4`
- `s` may contain English letters, digits, symbols and spaces (ASCII).

## Approach

**Brute force:** Check every substring and test whether it has all-unique characters. This takes O(n^2) to O(n^3) time, which is too slow for n = 5*10^4.

**Intuition (sliding window):** Keep a window `[left, right]` that never contains a duplicate. As `right` advances, a new character can only clash with its own previous occurrence. If that occurrence lies inside the window, move `left` to just past it.

**Algorithm**
1. Create an array `last[128]`, initialised to -1. It stores the most recent index of each ASCII character.
2. Set `left = 0` and `best = 0`.
3. For each index `right`, with `c = s[right]`:
   - If `last[c] >= left`, set `left = last[c] + 1`.
   - Set `last[c] = right`.
   - Set `best = max(best, right - left + 1)`.
4. Return `best`.

**Why it is correct**
- **Invariant:** after each step the window `[left, right]` has no duplicates. A duplicate could only involve `c`, and we jump `left` past the earlier copy of `c`.
- **Optimality:** `left` only moves to the smallest position that keeps the window valid. So for every `right`, we measure the longest valid substring ending at `right`. The maximum over all `right` is therefore the answer.

Each index is processed once and `left` never moves backward, giving linear time.

## Complexity

- **Time:** O(n): each character is visited once by the right pointer, and the left pointer only moves forward.
- **Space:** O(1): a fixed 128-entry table regardless of input size (O(k) for an alphabet of size k).

## Solution

See [`Solution.java`](Solution.java). Run the tests with:

```bash
javac Solution.java && java Solution
```
