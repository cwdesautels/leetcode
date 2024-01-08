package io.github.cwdesautels.easy;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class RemoveDuplicatesTest {
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