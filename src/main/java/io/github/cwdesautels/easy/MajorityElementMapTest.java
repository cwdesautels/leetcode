package io.github.cwdesautels.easy;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MajorityElementMapTest {
    public static int majorityElement(int[] nums) {
        final Map<Integer, Integer> counts = new HashMap<>();
        int max = 0;
        int value = -1;
        int limit = nums.length / 2;

        for (final int key : nums) {
            final var count = counts.getOrDefault(key, 0) + 1;

            if (count > max) {
                max = count;
                value = key;
            }

            if (count > limit) {
                // short circuit
                return value;
            }

            counts.put(key, count);
        }

        return value;
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new int[]{1},
                        1),
                Arguments.of(
                        new int[]{1, 1},
                        1),
                Arguments.of(
                        new int[]{1, 1, 1, 0},
                        1),
                Arguments.of(
                        new int[]{1, 1, 2, 0},
                        1),
                Arguments.of(
                        new int[]{4, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4},
                        1),
                Arguments.of(
                        new int[]{1, 2, 2, 2, 3},
                        2),
                Arguments.of(
                        new int[]{1, 1, 2, 3},
                        1),
                Arguments.of(
                        new int[]{4, 1, 2, 3, 4, 4, 4},
                        4)
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(int[] input, int expected) {
        final var actual = majorityElement(input);

        assertThat(actual).isEqualTo(expected);
    }
}