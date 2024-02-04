package io.github.cwdesautels.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JumpGameTest {
    public boolean canJump(int[] nums) {
        final int len = nums.length;

        if (len == 1) {
            return true;
        } else if (nums[0] == 0) {
            return false;
        }

        int reachable = 0;

        for (int i = 0; i < len; i++) {
            final int jump = nums[i];

            if (i > reachable) {
                return false;
            } else if (i + jump > reachable) {
                reachable = i + jump;
            }
        }

        return reachable >= len - 1;
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new int[]{2, 3, 1, 1, 4},
                        true),
                Arguments.of(
                        new int[]{3, 2, 1, 0, 4},
                        false),
                Arguments.of(
                        new int[]{0},
                        true),
                Arguments.of(
                        new int[]{1},
                        true),
                Arguments.of(
                        new int[]{2, 5, 0, 0},
                        true));
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(int[] input, boolean expected) {
        final var actual = canJump(input);

        assertThat(actual).isEqualTo(expected);
    }
}