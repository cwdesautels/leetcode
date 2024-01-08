package io.github.cwdesautels.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BestTimeToBuySellStockSlowTest {
    public int maxProfit(int[] prices) {
        int global = 0;

        for (int i = 0; i < prices.length; i++) {
            int local = 0;

            for (int j = i + 1; j < prices.length; j++) {
                final var current = prices[j] - prices[i];

                if (current > local) {
                    local = current;
                }
            }

            if (local > global) {
                global = local;
            }
        }

        return global;
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new int[]{7, 1, 5, 3, 6, 4},
                        5),
                Arguments.of(
                        new int[]{7, 6, 4, 3, 1},
                        0),
                Arguments.of(
                        new int[]{1, 2},
                        1));
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(int[] input, int expected) {
        final var actual = maxProfit(input);

        assertThat(actual).isEqualTo(expected);
    }
}