package io.github.cwdesautels.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 * <p>
 * <p>
 * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 */
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