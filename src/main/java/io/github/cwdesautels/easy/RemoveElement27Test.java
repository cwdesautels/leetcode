package io.github.cwdesautels.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
 * <p>
 * Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:
 * <p>
 * Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
 * Return k.
 * <p>
 * Custom Judge:
 * <p>
 * The judge will test your solution with the following code:
 * <p>
 * int[] nums = [...]; // Input array
 * int val = ...; // Value to remove
 * int[] expectedNums = [...]; // The expected answer with correct length.
 * // It is sorted with no values equaling val.
 * <p>
 * int k = removeElement(nums, val); // Calls your implementation
 * <p>
 * assert k == expectedNums.length;
 * sort(nums, 0, k); // Sort the first k elements of nums
 * for (int i = 0; i < actualLength; i++) {
 * assert nums[i] == expectedNums[i];
 * }
 * <p>
 * If all assertions pass, then your solution will be accepted.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,2,3], val = 3
 * Output: 2, nums = [2,2,_,_]
 * Explanation: Your function should return k = 2, with the first two elements of nums being 2.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,1,2,2,3,0,4,2], val = 2
 * Output: 5, nums = [0,1,4,0,3,_,_,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
 * Note that the five elements can be returned in any order.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 50
 * 0 <= val <= 100
 */
public class RemoveElement27Test {
    public int removeElement(int[] nums, int val) {
        int k = 0;

        // Two pointer: from start and end
        for (int i = 0, j = nums.length; i < j; i++) {
            if (nums[i] == val) {
                // If matching find the next non-matching value and swap
                while (j > i) {
                    if (nums[--j] != val) {
                        nums[i] = nums[j];
                        k++;
                        break;
                    }
                }
            } else {
                k++;
            }
        }

        return k;
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new int[]{1},
                        0,
                        new int[]{1}),
                Arguments.of(
                        new int[]{},
                        2,
                        new int[]{}),
                Arguments.of(
                        new int[]{1, 1, 2},
                        2,
                        new int[]{1, 1}),
                Arguments.of(
                        new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4},
                        3,
                        new int[]{0, 0, 1, 1, 1, 2, 2, 4}),
                Arguments.of(
                        new int[]{1, 2, 2, 2, 3},
                        3,
                        new int[]{1, 2, 2, 2}),
                Arguments.of(
                        new int[]{1, 1, 2, 3},
                        1,
                        new int[]{2, 3})
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(int[] input, int remove, int[] expected) {
        final var size = removeElement(input, remove);
        final var actual = Arrays.copyOfRange(input, 0, size);

        assertThat(actual).containsExactlyInAnyOrder(expected);
    }
}