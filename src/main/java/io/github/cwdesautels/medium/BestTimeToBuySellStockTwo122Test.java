package io.github.cwdesautels.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.
 * <p>
 * Find and return the maximum profit you can achieve.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Total profit is 4 + 3 = 7.
 * <p>
 * Example 2:
 * <p>
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Total profit is 4.
 * <p>
 * Example 3:
 * <p>
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 3 * 104
 * 0 <= prices[i] <= 104
 */
public class BestTimeToBuySellStockTwo122Test {
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