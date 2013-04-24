package ru.ifmo.ctddev.pyankova.task2;

/**
 * Created with IntelliJ IDEA.
 * User: yulya3102
 * Date: 20.02.13
 * Time: 23:05
 * To change this template use File | Settings | File Templates.
 */
public class MismatchingMatrixSizesException extends RuntimeException {
    public MismatchingMatrixSizesException(Matrix first, Matrix second) {
        super("First matrix: " + first.rows + "*" + first.columns + ", second matrix: " + second.rows + "*" + second.columns);
    }
}
