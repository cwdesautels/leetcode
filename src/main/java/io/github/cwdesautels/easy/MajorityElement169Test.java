package io.github.cwdesautels.easy;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array nums of size n, return the majority element.
 * <p>
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,3]
 * Output: 3
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 * <p>
 * <p>
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
public class MajorityElement169Test {
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