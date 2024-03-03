package io.github.cwdesautels.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 * <p>
 * <p>
 * <p>
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKElements347Test {
    public int[] topKFrequent(int[] nums, int k) {
        final var counts = new HashMap<Integer, Integer>(nums.length);

        Arrays.stream(nums)
                .forEach(num -> counts.merge(num, 1, Integer::sum));

        return counts.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .map(Map.Entry::getKey)
                .flatMapToInt(IntStream::of)
                .limit(k)
                .toArray();
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 1, 1, 2, 2, 3},
                        2,
                        new int[]{1, 2}),
                Arguments.of(
                        new int[]{1},
                        1,
                        new int[]{1})
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(int[] input, int k, int[] expected) {
        final var actual = topKFrequent(input, k);

        assertThat(actual).containsExactly(expected);
    }
}