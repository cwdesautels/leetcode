package io.github.cwdesautels.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * <p>
 * You must write an algorithm that runs in O(n) time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class LongestConsecutiveSequence128Test {
    public int longestConsecutive(int[] nums) {
        final var sequences = HashSet.newHashSet(nums.length);
        var max = 0;

        for (final int num : nums) {
            sequences.add(num);
        }

        for (final int num : nums) {
            if (!sequences.contains(num - 1)) {
                var ceiling = num;

                while (sequences.contains(ceiling)) {
                    sequences.remove(ceiling++);
                }

                sequences.remove(num);

                max = Math.max(ceiling - num, max);
            }
        }

        return max;
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new int[]{100, 4, 200, 1, 3, 2},
                        4
                ),
                Arguments.of(
                        new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1},
                        9
                ));
    }


    @ParameterizedTest
    @MethodSource("cases")
    void test(int[] input, int expected) {
        final var actual = longestConsecutive(input);

        assertThat(actual).isEqualTo(expected);
    }
}