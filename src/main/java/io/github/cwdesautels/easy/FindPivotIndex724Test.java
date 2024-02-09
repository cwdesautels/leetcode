package io.github.cwdesautels.easy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FindPivotIndex724Test {
    public int pivotIndex(int[] nums) {
        final int[] prefixes = new int[nums.length];
        final int[] suffixes = new int[nums.length];

        for (int i = suffixes.length - 1; i > -1; i--) {
            suffixes[i] = i == suffixes.length - 1
                    ? nums[i]
                    : suffixes[i + 1] + nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            prefixes[i] = i == 0
                    ? nums[i]
                    : prefixes[i - 1] + nums[i];

            if (suffixes[i] == prefixes[i]) {
                return i;
            }
        }

        return -1;
    }

    @Test
    void test1() {
        final var array = new int[]{1, 7, 3, 6, 5, 6};

        assertThat(pivotIndex(array)).isEqualTo(3);
    }

    @Test
    void test2() {
        final var array = new int[]{1, 2, 3};

        assertThat(pivotIndex(array)).isEqualTo(-1);
    }

    @Test
    void test3() {
        final var array = new int[]{2, 1, -1};

        assertThat(pivotIndex(array)).isEqualTo(0);
    }

    @Test
    void test4() {
        final var array = new int[]{-1, -1, 0, 0, -1, -1};

        assertThat(pivotIndex(array)).isEqualTo(2);
    }
}