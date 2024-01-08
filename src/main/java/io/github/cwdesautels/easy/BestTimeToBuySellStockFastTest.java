package io.github.cwdesautels.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BestTimeToBuySellStockFastTest {
    public int maxProfit(int[] prices) {
        int low = Integer.MAX_VALUE;
        int profit = 0;

        for (final int price : prices) {
            if (price < low) {
                low = price;
            }

            final int potential = price - low;

            if (potential > profit) {
                profit = potential;
            }
        }

        return profit;
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