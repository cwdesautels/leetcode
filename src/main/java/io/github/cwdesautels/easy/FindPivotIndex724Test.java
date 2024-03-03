package io.github.cwdesautels.easy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of integers nums, calculate the pivot index of this array.
 * <p>
 * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.
 * <p>
 * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.
 * <p>
 * Return the leftmost pivot index. If no such index exists, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,7,3,6,5,6]
 * Output: 3
 * Explanation:
 * The pivot index is 3.
 * Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
 * Right sum = nums[4] + nums[5] = 5 + 6 = 11
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,2,3]
 * Output: -1
 * Explanation:
 * There is no index that satisfies the conditions in the problem statement.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [2,1,-1]
 * Output: 0
 * Explanation:
 * The pivot index is 0.
 * Left sum = 0 (no elements to the left of index 0)
 * Right sum = nums[1] + nums[2] = 1 + -1 = 0
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * -1000 <= nums[i] <= 1000
 */
public class FindPivotIndex724Test {
    public int pivotIndex(int[] nums) {
        final int[] prefixes = new int[nums.length];
        final int[] suffixes = new int[nums.length];

        for (int i = suffixes.length - 1; i > -1; i--) {
            suffixes[i] = i == suffixes.length - 1
                    ? nums[i]
                    : suffixes[i + 1] + nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            prefixes[i] = i == 0
                    ? nums[i]
                    : prefixes[i - 1] + nums[i];

            if (suffixes[i] == prefixes[i]) {
                return i;
            }
        }

        return -1;
    }

    @Test
    void test1() {
        final var array = new int[]{1, 7, 3, 6, 5, 6};

        assertThat(pivotIndex(array)).isEqualTo(3);
    }

    @Test
    void test2() {
        final var array = new int[]{1, 2, 3};

        assertThat(pivotIndex(array)).isEqualTo(-1);
    }

    @Test
    void test3() {
        final var array = new int[]{2, 1, -1};

        assertThat(pivotIndex(array)).isEqualTo(0);
    }

    @Test
    void test4() {
        final var array = new int[]{-1, -1, 0, 0, -1, -1};

        assertThat(pivotIndex(array)).isEqualTo(2);
    }
}