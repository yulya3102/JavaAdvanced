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
        Thread[] threads = new Thread[threadsCount];
        int workForThread = size * size / threadsCount;
        for (int i = 0; i < threadsCount - 1; i++) {
            threads[i] = new Thread(new MultiplicationRunnable(i * workForThread, i * workForThread + workForThread));
            threads[i].start();
        }
        threads[threadsCount - 1] = new Thread(new MultiplicationRunnable((threadsCount - 1) * workForThread, size * size));
        threads[threadsCount - 1].start();

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Calculates part of matrices product
     */
    private class MultiplicationRunnable implements Runnable {
        /**
         * start index
         */
        private int start;

        /**
         * finish index
         */
        private int finish;

        /**
         * Constructs object that will calculate elements of result matrix from <code>start</code> to <code>finish</code>
         *
         * @param start start index
         * @param finish finish index
         */
        public MultiplicationRunnable(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }

        /**
         * Calculates requested elements of result matrix
         */
        @Override
        public void run() {
            for (int index = start; index < finish; index++) {
                int i = index / size;
                int j = index - size * i;
                int matrixElement = 0;
                for (int k = 0; k < size; k++) {
                    matrixElement += first[i][k] * second[k][j];
                }
                result[i][j] = matrixElement;
            }
        }
    }
}
