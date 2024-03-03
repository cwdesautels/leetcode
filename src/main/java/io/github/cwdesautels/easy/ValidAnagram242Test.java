package io.github.cwdesautels.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: s = "rat", t = "car"
 * Output: false
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length, t.length <= 5 * 104
 * s and t consist of lowercase English letters.
 * <p>
 * <p>
 * <p>
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */
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