package io.github.cwdesautels.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidAnagram242Test {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        final var counts = new HashMap<Integer, Integer>(t.length());
        final var tItr = t.chars().iterator();

        while (tItr.hasNext()) {
            counts.merge(tItr.next(), 1, Integer::sum);
        }

        final var sItr = s.chars().iterator();

        while (sItr.hasNext()) {
            if (counts.merge(sItr.next(), -1, Integer::sum) < 0) {
                return false;
            }
        }

        return true;
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        "racecar",
                        "racecar",
                        true),
                Arguments.of(
                        "abc",
                        "xyz",
                        false),
                Arguments.of(
                        "k",
                        "k",
                        true),
                Arguments.of(
                        "ab",
                        "a",
                        false));
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(String s, String t, boolean expected) {
        final var actual = isAnagram(s, t);

        assertThat(actual).isEqualTo(expected);
    }
}