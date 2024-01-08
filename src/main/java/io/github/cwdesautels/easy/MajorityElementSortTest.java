package io.github.cwdesautels.easy;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MajorityElementSortTest {
    public static int majorityElement(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    final var t = nums[j];

                    nums[j] = nums[j - 1];
                    nums[j - 1] = t;
                }
            }
        }

        // The majority element is the element that appears more than ⌊n / 2⌋ times
        return nums[nums.length / 2];
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new int[]{1},
                        1),
                Arguments.of(
                        new int[]{1, 1},
                        1),
                Arguments.of(
                        new int[]{1, 1, 1, 0},
                        1),
                Arguments.of(
                        new int[]{1, 1, 2, 0},
                        1),
//                Arguments.of(
//                        new int[]{4, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4},
//                        1),
                Arguments.of(
                        new int[]{1, 2, 2, 2, 3},
                        2),
//                Arguments.of(
//                        new int[]{1, 1, 2, 3},
//                        1),
                Arguments.of(
                        new int[]{4, 1, 2, 3, 4, 4, 4},
                        4)
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(int[] input, int expected) {
        final var actual = majorityElement(input);

        assertThat(actual).isEqualTo(expected);
    }
}