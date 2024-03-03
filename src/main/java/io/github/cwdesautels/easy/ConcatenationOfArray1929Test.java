package io.github.cwdesautels.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums of length n, you want to create an array ans of length 2n where ans[i] == nums[i] and ans[i + n] == nums[i] for 0 <= i < n (0-indexed).
 * <p>
 * Specifically, ans is the concatenation of two nums arrays.
 * <p>
 * Return the array ans.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,1]
 * Output: [1,2,1,1,2,1]
 * Explanation: The array ans is formed as follows:
 * - ans = [nums[0],nums[1],nums[2],nums[0],nums[1],nums[2]]
 * - ans = [1,2,1,1,2,1]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,3,2,1]
 * Output: [1,3,2,1,1,3,2,1]
 * Explanation: The array ans is formed as follows:
 * - ans = [nums[0],nums[1],nums[2],nums[3],nums[0],nums[1],nums[2],nums[3]]
 * - ans = [1,3,2,1,1,3,2,1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 1000
 * 1 <= nums[i] <= 1000
 */
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