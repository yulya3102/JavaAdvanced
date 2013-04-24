package ru.ifmo.ctddev.pyankova.task1;

/**
 * Created with IntelliJ IDEA.
 * User: yulya3102
 * Date: 20.02.13
 * Time: 22:06
 * To change this template use File | Settings | File Templates.
 */
public class InvalidMatrixSizeException extends RuntimeException {
    public InvalidMatrixSizeException(int rows) {
        super("Rows: " + rows);
    }
    public InvalidMatrixSizeException(int rows, int columns) {
        super("Rows: " + rows + ", columns: " + columns);
    }
}
