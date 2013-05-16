package ru.ifmo.ctddev.pyankova.task6;

import java.util.Random;

/**
 * Generates two random matrices with size n * n and
 * multiplies them in <code>m</code> threads
 */
public class MatrixMultiplicationCalculator {
    /**
     * First random matrix
     */
    private int[][] first;

    /**
     * Second random matrix
     */
    private int[][] second;

    /**
     * Result matrix
     */
    private int[][] result;

    /**
     * Matrices size
     */
    private final int size;

    /**
     * Generates random matrix with size n * n
     *
     * @param n matrix size
     * @param random {@link java.util.Random} object used to generate matrix
     * @return random matrix with size n * n
     */
    private int[][] matrix(int n, Random random) {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = random.nextInt();
            }
        }
        return result;
    }

    /**
     * Constructs new <code>MatrixMultiplicationCalculator</code> that will calculate product of two random matrices
     *
     * @param size random matrices size
     */
    public MatrixMultiplicationCalculator(int size) {
        Random random = new Random();
        first = matrix(size, random);
        second = matrix(size, random);
        result = new int[size][size];
        this.size = size;
    }

    /**
     * Calculates product of two random matrices
     *
     * @param threadsCount number of threads that will be used to calculate product
     * @return product of two random matrices
     */
    public int[][] calculate(int threadsCount) {
        // TODO: create m threads
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = 0;
                for (int k = 0; k < size; k++) {
                    value += first[i][k] * second[k][j];
                }
                // TODO: join m threads
                result[i][j] = value;
            }
        }
        return result;
    }
}
