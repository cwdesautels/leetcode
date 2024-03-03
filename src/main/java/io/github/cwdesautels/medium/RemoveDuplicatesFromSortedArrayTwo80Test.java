package io.github.cwdesautels.medium;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.
 * <p>
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 * <p>
 * Return k after placing the final result in the first k slots of nums.
 * <p>
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
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
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,0,1,1,1,1,2,3,3]
 * Output: 7, nums = [0,0,1,1,2,3,3,_,_]
 * Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums is sorted in non-decreasing order.
 */
public class RemoveDuplicatesFromSortedArrayTwo80Test {
    public static int removeDuplicates(int[] nums) {
        int w = 1;

        for (int r = 1, n = 0; r < nums.length; r++) {
            if (nums[r] != nums[r - 1]) {
                nums[w++] = nums[r];
                n = 0;
            } else {
                n++;

                if (n == 1) {
                    nums[w++] = nums[r];
                }
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
                        new int[]{1, 1}),
                Arguments.of(
                        new int[]{1, 1, 1},
                        new int[]{1, 1}),
//                Arguments.of(
//                        new int[]{},
//                        new int[]{}),
                Arguments.of(
                        new int[]{1, 1, 2},
                        new int[]{1, 1, 2}),
                Arguments.of(
                        new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4},
                        new int[]{0, 0, 1, 1, 2, 2, 3, 3, 4}),
                Arguments.of(
                        new int[]{1, 2, 2, 2, 3},
                        new int[]{1, 2, 2, 3}),
                Arguments.of(
                        new int[]{1, 1, 2, 3},
                        new int[]{1, 1, 2, 3}),
                Arguments.of(
                        new int[]{1, 2, 3},
                        new int[]{1, 2, 3}),
                Arguments.of(
                        new int[]{1, 2, 3, 4},
                        new int[]{1, 2, 3, 4}),
                Arguments.of(
                        new int[]{1, 2, 3, 4, 4, 4},
                        new int[]{1, 2, 3, 4, 4})
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