package io.github.cwdesautels.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BestTimeToBuySellStockTwoTest {
    public int maxProfit(int[] prices) {
        int proft = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                proft += prices[i] - prices[i - 1];
            }
        }

        return proft;
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new int[]{7, 1, 5, 3, 6, 4},
                        7),
                Arguments.of(
                        new int[]{1, 2, 3, 4, 5},
                        4),
                Arguments.of(
                        new int[]{7, 6, 4, 3, 1},
                        0));
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(int[] input, int expected) {
        final var actual = maxProfit(input);

        assertThat(actual).isEqualTo(expected);
    }
}