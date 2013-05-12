package ru.ifmo.ctddev.pyankova.task6;

/**
 * Created with IntelliJ IDEA.
 * User: yulya3102
 * Date: 12.05.13
 * Time: 23:47
 */
public class MatrixMultiplication {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java MatrixMultiplication <n> <m>, where n — matrix size, m — thread count");
        } else {
            int n = Integer.parseInt(args[0]);
            int m = Integer.parseInt(args[1]);
            // TODO: generate two random matrix
            // TODO: create m threads
            // TODO: join m threads
            // TODO: print wall-clock time
            // TODO: sum result matrix elements and print
            // TODO: Javadoc
        }
    }
}
