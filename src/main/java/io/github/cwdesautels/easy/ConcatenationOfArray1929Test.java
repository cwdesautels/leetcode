package io.github.cwdesautels.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ConcatenationOfArray1929Test {
    public static int[] getConcatenation(int[] nums) {
        final int[] dst = new int[nums.length * 2];

        for (int len = dst.length, i = 0, j = nums.length; j < len; i++, j++) {
            dst[i] = nums[i];
            dst[j] = nums[i];
        }

        return dst;
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 2, 1},
                        new int[]{1, 2, 1, 1, 2, 1}),
                Arguments.of(
                        new int[]{1, 3, 2, 1},
                        new int[]{1, 3, 2, 1, 1, 3, 2, 1})
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(int[] input, int[] expected) {
        final var actual = getConcatenation(input);

        assertThat(actual).containsExactly(expected);
    }
}