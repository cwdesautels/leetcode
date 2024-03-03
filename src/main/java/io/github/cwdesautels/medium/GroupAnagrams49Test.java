package io.github.cwdesautels.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * Example 2:
 * <p>
 * Input: strs = [""]
 * Output: [[""]]
 * <p>
 * Example 3:
 * <p>
 * Input: strs = ["a"]
 * Output: [["a"]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */
public class GroupAnagrams49Test {
    public List<List<String>> groupAnagrams(String[] strs) {
        final var anagrams = new HashMap<String, List<String>>(strs.length);

        for (String str : strs) {
            final var key = str.toCharArray();

            Arrays.sort(key);

            anagrams.computeIfAbsent(String.valueOf(key), (__) -> new LinkedList<>())
                    .add(str);
        }

        return anagrams.values().stream().toList();
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},
                        of(of("bat"), of("nat", "tan"), of("ate", "eat", "tea"))),
                Arguments.of(
                        new String[]{""},
                        of(of(""))),
                Arguments.of(
                        new String[]{"a"},
                        of(of("a")))
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(String[] input, List<List<String>> expected) {
        final var actual = groupAnagrams(input);

        assertThat(actual).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expected);
    }
}