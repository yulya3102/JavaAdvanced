package ru.ifmo.ctddev.pyankova.task3;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: yulya3102
 * Date: 19.03.13
 * Time: 22:40
 * To change this template use File | Settings | File Templates.
 */

public class Bag<E> implements Collection<E> {
    private Map<E, List<E>> map;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        return map.keySet().contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new BagIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int size = 0;
        for (E e : this) {
            array[size++] = e;
        }
        return array;
    }

    @Override
    public boolean add(E e) {
        List<E> list = map.get(e);
        if (list == null) {
            list = new LinkedList<>();
            map.put(e, list);
        }
        list.add(e);
        size++;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        return map.keySet().containsAll(objects);
    }

    @Override
    public boolean addAll(Collection<? extends E> es) {
        for (E e : es) {
            add(e);
        }
        return true;
    }

    @Override
    public void clear() {
        map.clear();
        size = 0;
    }
}
