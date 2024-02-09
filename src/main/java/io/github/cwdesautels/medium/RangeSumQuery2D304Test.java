package io.github.cwdesautels.medium;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RangeSumQuery2D304Test {
    private static class NumMatrix {
        private final int[][] prefixes;

        public NumMatrix(int[][] matrix) {
            prefixes = new int[matrix.length][];

            for (int row = 0; row < matrix.length; row++) {
                prefixes[row] = new int[matrix[row].length];

                for (int col = 0; col < matrix[row].length; col++) {
                    if (row < 1) {
                        if (col < 1) {
                            prefixes[0][0] = matrix[0][0];
                        } else {
                            prefixes[0][col] = matrix[0][col]
                                    // add column prefix
                                    + prefixes[0][col - 1];
                        }
                    } else {
                        if (col < 1) {
                            prefixes[row][0] = matrix[row][0]
                                    // add row prefix
                                    + prefixes[row - 1][0];
                        } else {
                            prefixes[row][col] = matrix[row][col]
                                    // add column prefix
                                    + prefixes[row][col - 1]
                                    // add row prefix
                                    + prefixes[row - 1][col]
                                    // remove double counted section
                                    - prefixes[row - 1][col - 1];
                        }
                    }
                }
            }
        }

        public int sumRegion(int tlr, int tlc, int brr, int brc) {
            if (tlr < 1) {
                if (tlc < 1) {
                    return prefixes[brr][brc];
                } else {
                    return prefixes[brr][brc]
                            // remove col prefix
                            - prefixes[brr][tlc - 1];
                }
            } else {
                if (tlc < 1) {
                    return prefixes[brr][brc]
                            // remove row prefix
                            - prefixes[tlr - 1][brc];
                } else {
                    return prefixes[brr][brc]
                            // remove row prefix
                            - prefixes[tlr - 1][brc]
                            // remove col prefix
                            - prefixes[brr][tlc - 1]
                            // add double counted section
                            + prefixes[tlr - 1][tlc - 1];
                }
            }
        }
    }

    @Test
    void test1() {
        final var numMatrix = new NumMatrix(new int[][]{
                new int[]{3, 0, 1, 4, 2},
                new int[]{5, 6, 3, 2, 1},
                new int[]{1, 2, 0, 1, 5},
                new int[]{4, 1, 0, 1, 7},
                new int[]{1, 0, 3, 0, 5}});

        assertThat(numMatrix.sumRegion(2, 1, 4, 3)).isEqualTo(8); // return 8 (i.e sum of the red rectangle)
        assertThat(numMatrix.sumRegion(1, 1, 2, 2)).isEqualTo(11); // return 11 (i.e sum of the green rectangle)
        assertThat(numMatrix.sumRegion(1, 2, 2, 4)).isEqualTo(12); // return 12 (i.e sum of the blue rectangle)
    }

    @Test
    void test2() {
        final var numMatrix = new NumMatrix(new int[][]{
                new int[]{-4, -5}});

        assertThat(numMatrix.sumRegion(0, 0, 0, 0)).isEqualTo(-4);
        assertThat(numMatrix.sumRegion(0, 0, 0, 1)).isEqualTo(-9);
        assertThat(numMatrix.sumRegion(0, 1, 0, 1)).isEqualTo(-5);
    }

    @Test
    void test3() {
        final var numMatrix = new NumMatrix(new int[][]{
                new int[]{7, 7, 0},
                new int[]{-4, -7, 7},
                new int[]{-4, 0, -2},
                new int[]{-8, -5, 6}});

        assertThat(numMatrix.sumRegion(1, 0, 2, 2)).isEqualTo(-10);
        assertThat(numMatrix.sumRegion(3, 2, 3, 2)).isEqualTo(6);
        assertThat(numMatrix.sumRegion(2, 1, 2, 2)).isEqualTo(-2);
        assertThat(numMatrix.sumRegion(0, 2, 2, 2)).isEqualTo(5);
        assertThat(numMatrix.sumRegion(3, 2, 3, 2)).isEqualTo(6);
    }
}