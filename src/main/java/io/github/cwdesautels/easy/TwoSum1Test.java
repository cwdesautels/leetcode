package io.github.cwdesautels.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class TwoSum1Test {
    public static int[] twoSum(int[] nums, int target) {
        final Map<Integer, Integer> pairs = new HashMap<>(nums.length);

        for (int i = nums.length - 1; i > -1; i--) {
            final int left = nums[i];
            final int right = target - left;

            if (pairs.containsKey(right)) {
                return new int[]{
                        i,
                        pairs.get(right)
                };
            } else {
                pairs.put(left, i);
            }
        }

        return new int[0];
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new int[]{2, 7, 11, 15},
                        9,
                        new int[]{0, 1}),
                Arguments.of(
                        new int[]{3, 2, 4},
                        6,
                        new int[]{1, 2}),
                Arguments.of(
                        new int[]{3, 3},
                        6,
                        new int[]{0, 1})
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(int[] input, int target, int[] expected) {
        final var actual = twoSum(input, target);

        assertThat(actual).containsExactly(expected);
    }
}