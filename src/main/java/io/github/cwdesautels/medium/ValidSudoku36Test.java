package io.github.cwdesautels.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ValidSudoku36Test {
    public boolean isValidSudoku(char[][] board) {
        final var rowValidation = HashMap.<Integer, Set<Character>>newHashMap(9);
        final var colValidation = HashMap.<Integer, Set<Character>>newHashMap(9);
        final var sectionValidation = HashMap.<Integer, Set<Character>>newHashMap(9);

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                final var value = board[row][col];

                if (value != '.') {
                    // Validate row
                    if (!rowValidation.computeIfAbsent(row, __ -> HashSet.newHashSet(9)).add(value)) {
                        return false;
                    }

                    // Validate col
                    if (!colValidation.computeIfAbsent(col, __ -> HashSet.newHashSet(9)).add(value)) {
                        return false;
                    }

                    // Validate section
                    if (!sectionValidation.computeIfAbsent(computeSection(row, col), __ -> HashSet.newHashSet(9)).add(value)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static int computeSection(int row, int col) {
        if (row < 3) {
            if (col < 3) {
                return 0;
            } else if (col < 6) {
                return 1;
            } else {
                return 2;
            }
        } else if (row < 6) {
            if (col < 3) {
                return 3;
            } else if (col < 6) {
                return 4;
            } else {
                return 5;
            }
        } else {
            if (col < 3) {
                return 6;
            } else if (col < 6) {
                return 7;
            } else {
                return 8;
            }
        }
    }
}