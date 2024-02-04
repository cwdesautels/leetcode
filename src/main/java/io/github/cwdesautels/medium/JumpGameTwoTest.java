package io.github.cwdesautels.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JumpGameTwoTest {
    public int jump(int[] nums) {
        for (int i = 0, len = nums.length; i < len; i++) {
            for (int j = i + 1; j < len; j++) {

                return 0;
            }
        }
        return 0;
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new int[]{2, 3, 1, 1, 4},
                        2),
                Arguments.of(
                        new int[]{2, 3, 0, 1, 4},
                        2));
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(int[] input, int expected) {
        final var actual = jump(input);

        assertThat(actual).isEqualTo(expected);
    }
}