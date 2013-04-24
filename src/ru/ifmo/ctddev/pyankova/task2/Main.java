package ru.ifmo.ctddev.pyankova.task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: yulya3102
 * Date: 20.02.13
 * Time: 17:26
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Main <input file> <output file>");
        } else {
            String input = args[0];
            String output = args[1];
            try (Scanner scanner = new Scanner(new File(input))) {
                Matrix a = new Matrix(scanner);
                Matrix b = new Matrix(scanner);
                Matrix c = new Matrix(scanner);
                Matrix result = a.multiply(a).add(b.multiply(c));
                try {
                    result.write(new File(output));
                } catch (IOException exception) {
                    System.out.println("Error while writing matrix");
                }
            } catch (FileNotFoundException exception) {
                System.out.println("File not found: " + input);
            }
        }
    }
}
