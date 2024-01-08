package io.github.cwdesautels.medium;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RotateArraySlowTest {

    public static void rotate(int[] nums, int k) {
        k = k % nums.length;

        // (O)n ^ 2
        while (k-- > 0) {
            for (int i = 1, t = nums[0]; i <= nums.length; i++) {
                if (i == nums.length) {
                    // wrap
                    nums[0] = t;
                } else {
                    // rotate forward
                    final int v = nums[i];

                    nums[i] = t;
                    t = v;
                }
            }
        }
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new int[]{1},
                        1,
                        new int[]{1}),
                Arguments.of(
                        new int[]{1, 2},
                        2,
                        new int[]{1, 2}),
                Arguments.of(
                        new int[]{1, 2},
                        3,
                        new int[]{2, 1}),
                Arguments.of(
                        new int[]{1, 2},
                        4,
                        new int[]{1, 2}),
                Arguments.of(
                        new int[]{1, 1, 1, 0},
                        1,
                        new int[]{0, 1, 1, 1}),
                Arguments.of(
                        new int[]{1, 1, 2, 0},
                        1,
                        new int[]{0, 1, 1, 2}),
                Arguments.of(
                        new int[]{4, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4},
                        1,
                        new int[]{4, 4, 0, 0, 1, 1, 1, 2, 2, 3, 3,}),
                Arguments.of(
                        new int[]{1, 2, 2, 2, 3},
                        2,
                        new int[]{2, 3, 1, 2, 2}),
                Arguments.of(
                        new int[]{1, 1, 2, 3},
                        4,
                        new int[]{1, 1, 2, 3}),
                Arguments.of(
                        new int[]{4, 1, 2, 3, 4, 4, 4},
                        4,
                        new int[]{3, 4, 4, 4, 4, 1, 2}),
                Arguments.of(
                        new int[]{-1, -100, 3, 99},
                        2,
                        new int[]{3, 99, -1, -100}),
                Arguments.of(
                        new int[]{-1, -100, 3, 99},
                        3,
                        new int[]{-100, 3, 99, -1}),
                Arguments.of(
                        new int[]{-1, -100, 3, 3, 99},
                        2,
                        new int[]{3, 99, -1, -100, 3})
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(int[] input, int rotations, int[] expected) {
        rotate(input, rotations);

        final var actual = Arrays.copyOfRange(input, 0, input.length);

        assertThat(actual).isEqualTo(expected);
    }
}