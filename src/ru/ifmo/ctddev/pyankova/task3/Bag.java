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
    private Map<E, LinkedList<E>> map;
    private int size = 0;

    public Bag() {
        map = new HashMap<>();
    }

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
    public <T> T[] toArray(T[] ts) {
        if (ts.length < size) {
            return (T[]) toArray();
        }
        int size = 0;
        for (E e : this) {
            ts[size++] = (T) e;
        }
        for (; size < ts.length; size++) {
            ts[size] = null;
        }
        return ts;
    }

    @Override
    public boolean add(E e) {
        LinkedList<E> list = map.get(e);
        if (list == null) {
            list = new LinkedList<>();
            map.put(e, list);
        }
        list.add(e);
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        LinkedList<E> list = map.get(o);
        if (list == null) {
            return false;
        }
        list.remove();
        size--;
        if (list.isEmpty()) {
            map.remove(o);
        }
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
    public boolean removeAll(Collection<?> objects) {
        boolean result = false;
        for (Object o : objects) {
            if (remove(o)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> objects) {
        Map<E, LinkedList<E>> newMap = new HashMap<>();
        int newSize = 0;
        for (Object o : objects) {
            LinkedList<E> list = map.get(o);
            if (list != null) {
                newMap.put(list.getFirst(), list);
                newSize += list.size();
            }
        }
        boolean result = newSize == size;
        map = newMap;
        size = newSize;
        return result;
    }

    @Override
    public void clear() {
        map.clear();
        size = 0;
    }

    private class BagIterator implements Iterator<E> {
        private Iterator<LinkedList<E>> mapIterator = null;
        private Iterator<E> listIterator = null;

        public BagIterator() {
            mapIterator = map.values().iterator();
            if (mapIterator.hasNext()) {
                listIterator = mapIterator.next().iterator();
            } else {
                // empty list iterator
                listIterator = new LinkedList<E>().iterator();
            }
        }

        @Override
        public boolean hasNext() {
            return listIterator.hasNext() || mapIterator.hasNext();
        }

        @Override
        public E next() {
            if (listIterator.hasNext()) {
                return listIterator.next();
            }
            if (mapIterator.hasNext()) {
                listIterator = mapIterator.next().iterator();
                return listIterator.next();
            }
            throw new NoSuchElementException("Iteration has no more elements");
        }

        @Override
        public void remove() {
            // throws necessary exceptions
            listIterator.remove();
            size--;
        }
    }
}
