package io.github.cwdesautels.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Design an algorithm to encode a list of strings to a single string. The encoded string is then decoded back to the original list of strings.
 * <p>
 * Please implement encode and decode
 * <p>
 * Example 1:
 * <p>
 * Input: ["neet","code","love","you"]
 * <p>
 * Output:["neet","code","love","you"]
 * <p>
 * Example 2:
 * <p>
 * Input: ["we","say",":","yes"]
 * <p>
 * Output: ["we","say",":","yes"]
 * <p>
 * Constraints:
 * <p>
 * 0 <= strs.length < 100
 * 0 <= strs[i].length < 200
 * strs[i] contains only UTF-8 characters.
 */
public class StringEncodeDecode271Test {
    private final char DELIMITER = ':';
    private final int DELIMITER_LENGTH = 1;

    public String encode(List<String> strs) {
        final var buffer = new StringBuilder(256);

        for (String value : strs) {
            buffer.append(value.length())
                    .append(DELIMITER)
                    .append(value);
        }

        return buffer.toString();
    }

    public List<String> decode(String str) {
        final var list = new LinkedList<String>();
        var i = 0;

        while (i < str.length()) {
            final var separator = str.indexOf(DELIMITER, i);

            if (separator < 0) {
                return list;
            } else {
                final var size = Integer.parseInt(str.substring(i, separator));

                i = separator + DELIMITER_LENGTH + size;

                final var ss = str.substring(separator + DELIMITER_LENGTH, i);

                list.add(ss);
            }
        }

        return list;
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(List.of("neet", "code", "love", "you")),
                Arguments.of(List.of("we", "say", ":", "yes")),
                Arguments.of(List.of("")),
                Arguments.of(List.of()),
                Arguments.of(List.of("we", "say", ":", "yes", "!@#$%^&*()")),
                Arguments.of(List.of("#", "##")),
                Arguments.of(List.of("1,23", "45,6", "7,8,9")),
                Arguments.of(List.of("hello", "world", "hello")),
                Arguments.of(List.of("a", "b", "c", "d")),
                Arguments.of(List.of("@#$", "%^&*", "!@#$%^&*")),
                Arguments.of(List.of("apple", "orange", "banana", "grape", "kiwi", "melon")),
                Arguments.of(List.of("The quick brown fox", "jumps over the", "lazy dog", "1234567890", "abcdefghijklmnopqrstuvwxyz")),
                Arguments.of(List.of("", "   ", "!@#$%^&*()_+", "LongStringWithNoSpaces", "Another, String With, Commas")),
                Arguments.of(List.of("String with new\nline", "Another\nLine", "And\nOne\nMore")),
                Arguments.of(List.of("123", "456", "789", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20")),
                Arguments.of(List.of("Repeated", "Repeated", "Repeated", "Repeated", "Repeated", "Repeated")),
                Arguments.of(List.of("SingleCharacter", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J")),
                Arguments.of(List.of("EmojiTest 😊", "🌟✨🌟✨🌟", "🤖👽🤖👽")),
                Arguments.of(List.of("Strings with spaces are tricky", "Another one with spaces", "This also has spaces")),
                Arguments.of(List.of("VeryLongStringWithoutAnySpacesOrSpecialCharactersRepeatedManyTimesVeryLongStringWithoutAnySpacesOrSpecialCharactersRepeatedManyTimes")));
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(List<String> expected) {
        final var encoded = encode(expected);
        final var decoded = decode(encoded);

        assertThat(decoded).isEqualTo(expected);
    }
}