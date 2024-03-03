package io.github.cwdesautels.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 * <p>
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 * <p>
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
 * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
 * <p>
 * Example 2:
 * <p>
 * Input: nums1 = [1], m = 1, nums2 = [], n = 0
 * Output: [1]
 * Explanation: The arrays we are merging are [1] and [].
 * The result of the merge is [1].
 * <p>
 * Example 3:
 * <p>
 * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
 * Output: [1]
 * Explanation: The arrays we are merging are [] and [1].
 * The result of the merge is [1].
 * Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[j] <= 109
 * <p>
 * <p>
 * <p>
 * Follow up: Can you come up with an algorithm that runs in O(m + n) time?
 */
public class MergeSortedArray88Test {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;

        // Fill in the destination array from the end, taking the largest of either inputs
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // Drain the rest of nums2 into nums1 if needed (nums1 is already both sorted and the destination)
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 0},
                        1,
                        new int[]{1},
                        1,
                        new int[]{1, 1}),
                Arguments.of(
                        new int[]{},
                        0,
                        new int[]{},
                        0,
                        new int[]{}),
                Arguments.of(
                        new int[]{1, 1, 2, 0, 0},
                        3,
                        new int[]{1, 2},
                        2,
                        new int[]{1, 1, 1, 2, 2}),
                Arguments.of(
                        new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 0, 0, 0, 0, 0},
                        10,
                        new int[]{0, 1, 2, 3, 4},
                        5,
                        new int[]{0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4}),
                Arguments.of(
                        new int[]{1, 2, 2, 2, 3, 0, 0, 0},
                        5,
                        new int[]{1, 2, 3},
                        3,
                        new int[]{1, 1, 2, 2, 2, 2, 3, 3}),
                Arguments.of(
                        new int[]{1, 1, 2, 3, 0, 0, 0},
                        4,
                        new int[]{1, 2, 3},
                        3,
                        new int[]{1, 1, 1, 2, 2, 3, 3})
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(int[] input1, int input1len, int[] input2, int input2len, int[] expected) {
        merge(input1, input1len, input2, input2len);

        assertThat(input1).containsExactly(expected);
    }
}