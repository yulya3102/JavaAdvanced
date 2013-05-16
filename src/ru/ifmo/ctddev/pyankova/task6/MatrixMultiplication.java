package ru.ifmo.ctddev.pyankova.task6;

/**
 * Multiplies two random matrices (n * n) in <code>m</code> threads
 */
public class MatrixMultiplication {
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
            return;
        }
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        MatrixMultiplicationCalculator calculator = new MatrixMultiplicationCalculator(n);
        long startTime = System.currentTimeMillis();
        int[][] result = calculator.calculate(m);
        long endTime = System.currentTimeMillis();
        System.out.println("Wall-clock time: " + (endTime - startTime) + " milliseconds");
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += result[i][j];
            }
        }
        System.out.println("Sum of result matrix elements: " + sum);
    }
}
