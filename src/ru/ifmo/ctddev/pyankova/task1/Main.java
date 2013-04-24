package ru.ifmo.ctddev.pyankova.task1;

/**
 * Created with IntelliJ IDEA.
 * User: yulya3102
 * Date: 20.02.13
 * Time: 17:26
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        int[][] a = {
                {1, 2},
                {3, 4}
        };
        int[][] b = {
                {5, 6},
                {7, 8}
        };
        //Matrix result = (new Matrix(a)).multiply(new Matrix(b));
        Matrix result = new Matrix(a).multiply((new Matrix(a)).transpose()).add(new Matrix(b));
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.columns; j++)
                System.out.print((j > 0 ? " " : "") + result.get(i, j));
            System.out.println();
        }
    }
}
