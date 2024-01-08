package io.github.cwdesautels.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MergeSortedArray {
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