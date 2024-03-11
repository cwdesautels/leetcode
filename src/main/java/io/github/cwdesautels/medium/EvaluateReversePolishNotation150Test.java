package io.github.cwdesautels.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Stack;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * <p>
 * Evaluate the expression. Return an integer that represents the value of the expression.
 * <p>
 * Note that:
 * <p>
 * The valid operators are '+', '-', '*', and '/'.
 * Each operand may be an integer or another expression.
 * The division between two integers always truncates toward zero.
 * There will not be any division by zero.
 * The input represents a valid arithmetic expression in a reverse polish notation.
 * The answer and all the intermediate calculations can be represented in a 32-bit integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * <p>
 * Example 2:
 * <p>
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * <p>
 * Example 3:
 * <p>
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= tokens.length <= 104
 * tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 */
public class EvaluateReversePolishNotation150Test {
    public int evalRPN(String[] tokens) {
        final Stack<Integer> stack = new Stack<>();

        for (final String token : tokens) {
            switch (token) {
                case "+" -> {
                    final var right = stack.pop();
                    final var left = stack.pop();

                    stack.push(left + right);
                }
                case "-" -> {
                    final var right = stack.pop();
                    final var left = stack.pop();

                    stack.push(left - right);
                }
                case "*" -> {
                    final var right = stack.pop();
                    final var left = stack.pop();

                    stack.push(left * right);
                }
                case "/" -> {
                    final var right = stack.pop();
                    final var left = stack.pop();

                    stack.push(left / right);
                }
                default -> {
                    stack.push(Integer.parseInt(token));
                }
            }
        }

        return stack.pop();
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"},
                        22),
                Arguments.of(
                        new String[]{"4", "13", "5", "/", "+"},
                        6),
                Arguments.of(
                        new String[]{"2", "1", "+", "3", "*"},
                        9));
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(String[] input, int expected) {
        final var actual = evalRPN(input);

        assertThat(actual).isEqualTo(expected);
    }
}