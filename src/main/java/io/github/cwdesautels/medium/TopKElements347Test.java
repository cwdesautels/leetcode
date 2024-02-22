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