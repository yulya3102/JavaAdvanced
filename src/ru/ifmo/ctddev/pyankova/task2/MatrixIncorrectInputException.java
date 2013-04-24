package ru.ifmo.ctddev.pyankova.task2;

/**
 * Created with IntelliJ IDEA.
 * User: yulya3102
 * Date: 28.02.13
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
public class MatrixIncorrectInputException extends RuntimeException {
    public MatrixIncorrectInputException(String message) {
        super(message);
    }
    public MatrixIncorrectInputException(Exception cause) {
        super(cause);
    }
}
