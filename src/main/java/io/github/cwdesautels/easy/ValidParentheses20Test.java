package io.github.cwdesautels.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Stack;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 20. Valid Parentheses
 * Easy
 * Topics
 * Companies
 * Hint
 * <p>
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "()"
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: s = "()[]{}"
 * Output: true
 * <p>
 * Example 3:
 * <p>
 * Input: s = "(]"
 * Output: false
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 */
public class ValidParentheses20Test {
    public boolean isValid(String s) {
        final Stack<Character> stack = new Stack<>();

        for (char character : s.toCharArray()) {
            switch (character) {
                case '(', '{', '[' -> {
                    stack.push(character);
                }
                case ')' -> {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                }
                case '}' -> {
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                }
                case ']' -> {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                }
            }
        }

        return stack.isEmpty();
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        "()",
                        true),
                Arguments.of(
                        "()[]{}",
                        true),
                Arguments.of(
                        "(]",
                        false),
                Arguments.of(
                        "((]",
                        false),
                Arguments.of(
                        "((",
                        false));
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(String s, boolean expected) {
        final var actual = isValid(s);

        assertThat(actual).isEqualTo(expected);
    }
}