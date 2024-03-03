package io.github.cwdesautels.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: false
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class ContainsDuplicate217Test {
    public static boolean containsDuplicate(int[] nums) {
        final var uniques = new HashSet<Integer>(nums.length * 2);

        for (int i = nums.length - 1; i >= 0; i--) {
            if (!uniques.add(nums[i])) {
                return true;
            }
        }

        return false;
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 2, 3, 1},
                        true),
                Arguments.of(
                        new int[]{1, 2, 3, 4},
                        false),
                Arguments.of(
                        new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2},
                        true)
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(int[] input, boolean expected) {
        final var actual = containsDuplicate(input);

        assertThat(actual).isEqualTo(expected);
    }
}