package io.github.cwdesautels.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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