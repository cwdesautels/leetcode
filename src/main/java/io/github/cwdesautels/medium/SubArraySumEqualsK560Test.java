package io.github.cwdesautels.medium;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class SubArraySumEqualsK560Test {
    public int subarraySum(int[] nums, int k) {
        final var prefixes = new HashMap<Integer, Integer>(nums.length);
        var count = 0;

        prefixes.put(0, 1);

        for (int i = 0, previous = 0; i < nums.length; i++) {
            final var prefix = nums[i] + previous;

            count += prefixes.getOrDefault(prefix - k, 0);
            previous = prefix;
            
            prefixes.merge(prefix, 1, Integer::sum);
        }

        return count;
    }

    @Test
    void test1() {
        final var input = new int[]{1, 1, 1};
        final var sum = 2;
        final var expected = 2;
        final var actual = subarraySum(input, sum);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test2() {
        final var input = new int[]{1, 2, 3};
        final var sum = 3;
        final var expected = 2;
        final var actual = subarraySum(input, sum);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test3() {
        final var input = new int[]{1, 2, 1, 2, 1};
        final var sum = 3;
        final var expected = 4;
        final var actual = subarraySum(input, sum);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test4() {
        final var input = new int[]{1};
        final var sum = 1;
        final var expected = 1;
        final var actual = subarraySum(input, sum);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test5() {
        final var input = new int[]{-1, -1, 1};
        final var sum = 2;
        final var expected = 0;
        final var actual = subarraySum(input, sum);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test6() {
        final var input = new int[]{3, 3, 3};
        final var sum = 3;
        final var expected = 3;
        final var actual = subarraySum(input, sum);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test7() {
        final var input = new int[]{3, 3, 3};
        final var sum = 6;
        final var expected = 2;
        final var actual = subarraySum(input, sum);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test8() {
        final var input = new int[]{1};
        final var sum = 0;
        final var expected = 0;
        final var actual = subarraySum(input, sum);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test9() {
        final var input = new int[]{1, 1};
        final var sum = 0;
        final var expected = 0;
        final var actual = subarraySum(input, sum);

        assertThat(actual).isEqualTo(expected);
    }
}