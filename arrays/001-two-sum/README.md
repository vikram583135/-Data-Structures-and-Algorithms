# Day 1: Two Sum

**Topic:** Arrays · **Difficulty:** Easy · **Date:** 2026-10-05

**Similar to:** LeetCode 1 - Two Sum

## Problem

Given an array of integers `nums` and an integer `target`, find the indices of the **two distinct elements** whose values add up to `target`.

You may assume that exactly one valid pair exists, and you may not use the same element twice. Return the two indices in any order (the reference solution returns them in increasing order).

**Example 1**
- Input: `nums = [2, 7, 11, 15]`, `target = 9`
- Output: `[0, 1]`
- Explanation: `nums[0] + nums[1] = 2 + 7 = 9`.

**Example 2**
- Input: `nums = [3, 2, 4]`, `target = 6`
- Output: `[1, 2]`
- Explanation: `2 + 4 = 6`. Note that index 0 cannot be paired with itself (`3 + 3`), because the same element may not be used twice.

**Constraints**
- `2 <= nums.length <= 10^4`
- `-10^9 <= nums[i] <= 10^9`
- `-10^9 <= target <= 10^9`
- Exactly one valid answer exists.

## Approach

**Brute force:** Check every pair `(i, j)` with `i < j`. This takes O(n^2) time, which is too slow when n is large.

**Intuition:** For each element `x`, the only partner that works is `target - x` (its *complement*). If we remember every value seen so far together with its index, we can check in O(1) whether that complement has already appeared.

**Algorithm:**
1. Create a hash map from value to index.
2. Scan the array from left to right. For each index `i`:
   - Compute `need = target - nums[i]`.
   - If `need` is in the map, return `[map.get(need), i]`.
   - Otherwise, store `nums[i] -> i` in the map.
3. If the loop finishes without a match, no pair exists (this cannot happen under the constraints).

**Why it is correct:**
- Let the answer pair be `(i, j)` with `i < j`. When the scan reaches `j`, index `i` is already in the map, so the pair is found at that step.
- We look up the complement *before* inserting the current element. This prevents an element from being paired with itself.
- Duplicate values are handled correctly. For example, with `[3, 3]` and target 6, the first 3 is stored, and the second 3 then finds it.
- Subtraction is done in `long` to avoid any risk of integer overflow at the edges of the value range.

This needs a single pass with O(1) average work per element, so it is O(n) overall, compared with O(n^2) for brute force.

## Complexity

- **Time:** O(n) — a single pass with O(1) average-time hash map lookups and inserts.
- **Space:** O(n) — the hash map can hold up to n entries.

## Solution

See [`Solution.java`](Solution.java). Run the tests with:

```bash
javac Solution.java && java Solution
```
