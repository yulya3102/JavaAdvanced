package ru.ifmo.ctddev.pyankova.task3;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: yulya3102
 * Date: 20.03.13
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */
public class LinkedBag<E> implements Collection<E> {
    private class Node {
        public int index;
        public E value;
        public Node(int index, E value) {
            this.index = index;
            this.value = value;
        }
    }

    private Set<Node> set = null;
    private Map<E, Integer> map = null;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedBagIterator();
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
        Integer index = map.get(e);
        if (index == null) {
            index = 0;
        }
        set.add(new Node(index++, e));
        map.put(e, index);
        size++;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        return map.keySet().containsAll(objects);
    }

    @Override
    public boolean addAll(Collection<? extends E> es) {
        boolean result = false;
        for (E e : es) {
            if (add(e)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public void clear() {
        set.clear();
        map.clear();
        size = 0;
    }
}
