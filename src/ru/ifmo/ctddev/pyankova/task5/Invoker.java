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
    private static void tryInvoke(Method method, Object object, String[] args) {
        Class<?>[] parameters = method.getParameterTypes();
        if (parameters.length != args.length) {
            return;
        }
        for (Class<?> parameterClass : parameters) {
            if (!parameterClass.isAssignableFrom(String.class)) {
                return;
            }
        }
        try {
            method.invoke(object, args);
            System.out.println(object);
        } catch (IllegalAccessException e) {
            System.out.println("Method " + method.getName() + " is inaccessible");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println("Method " + method.getName() + " threw an exception");
            e.printStackTrace();
        }
    }

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
                Method[] classMethods = loadedClass.getMethods();
                for (Method method : classMethods) {
                    if (methodName.equals(method.getName())) {
                        tryInvoke(method, object, methodArgs);
                    }
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
