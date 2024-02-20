package io.github.cwdesautels.medium;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductOfArrayExceptSelf238Test {
    public int[] productExceptSelf(int[] nums) {
        final int[] products = new int[nums.length];

        for (int i = 0, swp = 1; i < nums.length; i++) {
            // As the result array is 0 we need to not clobber values
            products[i] = swp * (products[i] == 0 // Deal with zero initialized array
                    ? 1
                    : products[i]);
            // Progress prefix product after assignment above to produce a single element offset
            swp = swp * nums[i];
        }

        for (int i = nums.length - 1, swp = 1; i > -1; i--) {
            // The swap contains the suffix product when combined with the offset prefix product creates the resulting element
            products[i] = swp * products[i];
            // Progress the suffix product
            swp = swp * nums[i];
        }

        return products;
    }

    @Test
    void test1() {
        final var input = new int[]{1, 2, 3, 4};
        final var expected = new int[]{24, 12, 8, 6};
        final var actual = productExceptSelf(input);

        assertThat(actual).containsExactly(expected);
    }

    @Test
    void test2() {
        final var input = new int[]{-1, 1, 0, -3, 3};
        final var expected = new int[]{0, 0, 9, 0, 0};
        final var actual = productExceptSelf(input);

        assertThat(actual).containsExactly(expected);
    }
}