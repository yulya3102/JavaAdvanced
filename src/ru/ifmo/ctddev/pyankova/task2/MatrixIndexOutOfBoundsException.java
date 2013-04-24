package ru.ifmo.ctddev.pyankova.task2;

/**
 * Created with IntelliJ IDEA.
 * User: yulya3102
 * Date: 21.02.13
 * Time: 1:20
 * To change this template use File | Settings | File Templates.
 */
public class MatrixIndexOutOfBoundsException extends RuntimeException {
    public MatrixIndexOutOfBoundsException(Matrix matrix, int i, int j) {
        super("Matrix has size " + matrix.rows + "*" + matrix.columns + ", but requested element is in row " + i + ", column " + j);
    }
}
