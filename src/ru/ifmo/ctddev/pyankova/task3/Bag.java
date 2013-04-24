package ru.ifmo.ctddev.pyankova.task3;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: yulya3102
 * Date: 19.03.13
 * Time: 22:40
 * To change this template use File | Settings | File Templates.
 */

public class Bag implements Collection {
    private Map<Object, LinkedList<Object>> map;
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
    public Iterator<Object> iterator() {
        return new BagIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int size = 0;
        for (Object e : this) {
            array[size++] = e;
        }
        return array;
    }

    @Override
    public Object[] toArray(Object[] objects) {
        if (objects.length < size) {
            return toArray();
        }
        int size = 0;
        for (Object o : this) {
            objects[size++] = o;
        }
        for (; size < objects.length; size++) {
            objects[size] = null;
        }
        return objects;
    }

    @Override
    public boolean add(Object e) {
        LinkedList<Object> list = map.get(e);
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
        LinkedList<Object> list = map.get(o);
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
    public boolean containsAll(Collection objects) {
        return map.keySet().containsAll(objects);
    }

    @Override
    public boolean addAll(Collection collection) {
        boolean result = false;
        for (Object o : collection) {
            if (add(o)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection objects) {
        boolean result = false;
        for (Object o : objects) {
            if (remove(o)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection objects) {
        Map<Object, LinkedList<Object>> newMap = new HashMap<>();
        int newSize = 0;
        for (Object o : objects) {
            LinkedList<Object> list = map.get(o);
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

    private class BagIterator implements Iterator<Object> {
        private Iterator<LinkedList<Object>> mapIterator = null;
        private Iterator<Object> listIterator = null;

        public BagIterator() {
            mapIterator = map.values().iterator();
            if (mapIterator.hasNext()) {
                listIterator = mapIterator.next().iterator();
            } else {
                // empty list iterator
                listIterator = new LinkedList().iterator();
            }
        }

        @Override
        public boolean hasNext() {
            return listIterator.hasNext() || mapIterator.hasNext();
        }

        @Override
        public Object next() {
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
