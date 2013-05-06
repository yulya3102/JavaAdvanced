package ru.ifmo.ctddev.pyankova.task5;

/**
 * Created with IntelliJ IDEA.
 * User: yulya3102
 * Date: 03.05.13
 * Time: 23:21
 */
public class Invoker {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Invoker <full-class-name> <method-name> <method-arg...>");
        }
        String fullClassName = args[0];
        String methodName = args[1];

    }
}
