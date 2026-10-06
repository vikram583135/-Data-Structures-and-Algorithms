# Day 3: Subarray Sum Equals K

**Topic:** Hashing · **Difficulty:** Medium · **Date:** 2026-10-05

**Similar to:** LeetCode 560 - Subarray Sum Equals K

## Problem

Given an integer array `nums` and an integer `k`, count the **contiguous, non-empty** subarrays whose elements add up to exactly `k`.

**Example 1**
- Input: `nums = [1, 1, 1]`, `k = 2`
- Output: `2`
- Explanation: The subarrays `[1,1]` at indices 0..1 and `[1,1]` at indices 1..2 both sum to 2.

**Example 2**
- Input: `nums = [1, 2, 3]`, `k = 3`
- Output: `2`
- Explanation: `[1,2]` and `[3]` both sum to 3.

**Constraints**
- `1 <= nums.length <= 2 * 10^4`
- `-1000 <= nums[i] <= 1000`
- `-10^7 <= k <= 10^7`

## Approach

**Brute force.** Try every start index and extend the end index while keeping a running sum. This takes O(n^2) time. A sliding window does not help, because negative numbers mean the sum is not monotonic as the window grows.

**Intuition.** Let `P[i]` be the sum of the first `i` elements. The subarray `(j, i]` has sum `P[i] - P[j]`. That sum equals `k` exactly when `P[j] = P[i] - k`.

So, at each position `i`, the number of valid subarrays ending there equals the number of earlier prefix sums whose value is `P[i] - k`.

**Algorithm.**
1. Create a hash map `count` from prefix-sum value to how many times it has occurred. Seed it with `count[0] = 1`; this is the empty prefix, which lets subarrays starting at index 0 be counted.
2. Scan the array, keeping a running `sum`.
3. For each element:
   - add it to `sum`;
   - add `count[sum - k]` to the answer;
   - increment `count[sum]`.
4. Return the answer.

**Correctness.** Every subarray ending at index `i` corresponds to exactly one earlier prefix `j` (where `j < i`).
- Its sum is `k` if and only if `P[j] = sum - k`.
- The map holds exactly the multiset of prefixes `P[0..i-1]` at the moment we query it, because we query before inserting the current `sum`.
- Therefore each valid subarray is counted once, and no invalid one is counted.

This improves the running time from O(n^2) to O(n).

## Complexity

- **Time:** O(n) — a single pass, with expected O(1) hash map operations per element.
- **Space:** O(n) — the hash map may store up to n+1 distinct prefix sums.

## Solution

See [`Solution.java`](Solution.java). Run the tests with:

```bash
javac Solution.java && java Solution
```
