package io.github.cwdesautels.easy;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.
 * <p>
 * Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
 * <p>
 * Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
 * Return k.
 * <p>
 * Custom Judge:
 * <p>
 * The judge will test your solution with the following code:
 * <p>
 * int[] nums = [...]; // Input array
 * int[] expectedNums = [...]; // The expected answer with correct length
 * <p>
 * int k = removeDuplicates(nums); // Calls your implementation
 * <p>
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 * assert nums[i] == expectedNums[i];
 * }
 * <p>
 * If all assertions pass, then your solution will be accepted.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,2]
 * Output: 2, nums = [1,2,_]
 * Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * nums is sorted in non-decreasing order.
 */
public class RemoveDuplicatesFromSortedArray26Test {
    public static int removeDuplicates(int[] nums) {
        int w = 1;

        for (int r = 1; r < nums.length; r++) {
            if (nums[r] != nums[r - 1]) {
                nums[w++] = nums[r];
            }
        }

        return w;
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new int[]{1},
                        new int[]{1}),
                Arguments.of(
                        new int[]{1, 1},
                        new int[]{1}),
                Arguments.of(
                        new int[]{1, 1, 1},
                        new int[]{1}),
//                Arguments.of(
//                        new int[]{},
//                        new int[]{}),
                Arguments.of(
                        new int[]{1, 1, 2},
                        new int[]{1, 2}),
                Arguments.of(
                        new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4},
                        new int[]{0, 1, 2, 3, 4}),
                Arguments.of(
                        new int[]{1, 2, 2, 2, 3},
                        new int[]{1, 2, 3}),
                Arguments.of(
                        new int[]{1, 1, 2, 3},
                        new int[]{1, 2, 3}),
                Arguments.of(
                        new int[]{1, 2, 3},
                        new int[]{1, 2, 3}),
                Arguments.of(
                        new int[]{1, 2, 3, 4},
                        new int[]{1, 2, 3, 4}),
                Arguments.of(
                        new int[]{1, 2, 3, 4, 4, 4},
                        new int[]{1, 2, 3, 4})
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(int[] input, int[] expected) {
        final var size = removeDuplicates(input);
        final var actual = Arrays.copyOfRange(input, 0, size);

        assertThat(actual).containsExactly(expected);
    }
}