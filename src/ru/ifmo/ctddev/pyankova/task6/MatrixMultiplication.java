package ru.ifmo.ctddev.pyankova.task6;

import java.util.Random;

/**
 * Multiplies two random matrices (n * n) in <code>m</code> threads
 */
public class MatrixMultiplication {
    /**
     * Generates random matrix with size n * n
     * @param n matrix size
     * @param random {@link java.util.Random} object used to generate matrix
     * @return random matrix with size n * n
     */
    private static int[][] matrix(int n, Random random) {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = random.nextInt();
            }
        }
        return result;
    }

    /**
     * Generates two random matrices with size n * n,
     * multiplies them in <code>m</code> threads
     * and prints sum of elements of result matrix
     *
     * @param args array containing two strings: <code>n</code> and <code>m</code> parameters
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java MatrixMultiplication <n> <m>, where n — matrix size, m — thread count");
        } else {
            int n = Integer.parseInt(args[0]);
            int m = Integer.parseInt(args[1]);
            Random random = new Random();
            int[][] first = matrix(n, random);
            int[][] second = matrix(n, random);
            int[][] result = new int[n][n];
            // TODO: create m threads
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int value = 0;
                    for (int k = 0; k < n; k++) {
                        value += first[i][k] * second[k][j];
                    }
                    // TODO: join m threads
                    result[i][j] = value;
                }
            }
            // TODO: print wall-clock time
            int sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sum += result[i][j];
                }
            }
            System.out.println("Sum of result matrix elements: " + sum);
        }
    }
}
