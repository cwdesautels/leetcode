package io.github.cwdesautels.easy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums, handle multiple queries of the following type:
 * <p>
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * <p>
 * Implement the NumArray class:
 * <p>
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * Output
 * [null, 1, -1, -3]
 * <p>
 * Explanation
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
 * numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
 * numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 * 0 <= left <= right < nums.length
 * At most 104 calls will be made to sumRange.
 */
public class RangeSumQuery303Test {
    private static class NumArray {

        private final int[] prefixes;

        public NumArray(int[] nums) {
            this.prefixes = new int[nums.length];

            for (int i = 0, len = nums.length; i < len; i++) {
                final var j = i - 1;
                final var left = j < 0 ? 0 : this.prefixes[j];
                final var right = nums[i];

                this.prefixes[i] = left + right;
            }
        }

        public int sumRange(int left, int right) {
            if (left > right) {
                return 0;
            } else {
                final var excess = left < 1
                        ? 0
                        : prefixes[left - 1];
                final var sum = right < prefixes.length
                        ? prefixes[right]
                        : prefixes[prefixes.length - 1];

                return sum - excess;
            }
        }
    }

    @Test
    void test1() {
        // Given
        final var array = new NumArray(new int[]{
                -2, 0, 3, -5, 2, -1
        });

        // Then
        assertThat(array.sumRange(0, 2)).isEqualTo(1);
        assertThat(array.sumRange(2, 5)).isEqualTo(-1);
        assertThat(array.sumRange(0, 5)).isEqualTo(-3);
        assertThat(array.sumRange(0, 6)).isEqualTo(-3);
        assertThat(array.sumRange(-1, 2)).isEqualTo(1);
        assertThat(array.sumRange(2, 2)).isEqualTo(3);
    }

    @Test
    void test2() {
        // Given
        final var array = new NumArray(new int[]{
                -4, -5
        });

        // Then
        assertThat(array.sumRange(0, 0)).isEqualTo(-4);
        assertThat(array.sumRange(1, 1)).isEqualTo(-5);
        assertThat(array.sumRange(0, 1)).isEqualTo(-9);
        assertThat(array.sumRange(1, 1)).isEqualTo(-5);
        assertThat(array.sumRange(0, 0)).isEqualTo(-4);
    }
}