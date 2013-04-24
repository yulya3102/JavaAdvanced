package ru.ifmo.ctddev.pyankova.task3;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: yulya3102
 * Date: 21.03.13
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        Bag bag = new Bag();
        bag.addAll(Arrays.asList(1, 2, 3, 2, 3, 5, 1));
        System.out.println(Arrays.toString(bag.toArray()));
        bag.remove(3);
        System.out.println(Arrays.toString(bag.toArray()));

        LinkedBag linkedBag = new LinkedBag();
        linkedBag.addAll(Arrays.asList(1, 2, 3, 2, 3, 5, 1));
        System.out.println(Arrays.toString(linkedBag.toArray()));
        linkedBag.remove(3);
        System.out.println(Arrays.toString(linkedBag.toArray()));
    }
}
