package ru.ifmo.ctddev.pyankova.task5;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

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
        System.out.println("Class name: " + fullClassName);
        String methodName = args[1];
        System.out.println("Method name: " + methodName);
        String[] methodArgs = Arrays.copyOfRange(args, 2, args.length);
        System.out.println("Method args: " + Arrays.toString(methodArgs));
        System.out.flush();
        try {
            Class<?> loadedClass = Class.forName(fullClassName);
            try {
                Constructor constructor = loadedClass.getConstructor();
                Object object = constructor.newInstance();
                try {
                    Class[] argsTypes = new Class[methodArgs.length];
                    Arrays.fill(argsTypes, Object.class);
                    // TODO: invoke every method that can get these arguments
                    Method loadedMethod = loadedClass.getMethod(methodName, argsTypes);
                    loadedMethod.invoke(object, methodArgs);
                    System.out.println(object);
                } catch (NoSuchMethodException e) {
                    System.out.println(fullClassName + " doesn't contain method " + methodName);
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                System.out.println(fullClassName + " doesn't contain default constructor");
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                System.out.println("Constructor threw an exception");
                e.printStackTrace();
            } catch (InstantiationException e) {
                System.out.println(fullClassName + " is an abstract class");
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                System.out.println("Constructor is inaccessible");
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class " + fullClassName + " not found");
            e.printStackTrace();
        }
    }
}
