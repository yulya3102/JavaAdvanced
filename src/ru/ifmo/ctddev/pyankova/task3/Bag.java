package ru.ifmo.ctddev.pyankova.task3;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: yulya3102
 * Date: 19.03.13
 * Time: 22:40
 * To change this template use File | Settings | File Templates.
 */

public class Bag extends AbstractSet {
    private Map<Object, List> map;
    private int size = 0;

    public Bag() {
        map = new HashMap<>();
    }

    @Override
    public Iterator iterator() {
        return new BagIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(Object e) {
        List list = map.get(e);
        if (list == null) {
            list = new LinkedList();
            map.put(e, list);
        }
        list.add(e);
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        List list = map.get(o);
        if (list == null) {
            return false;
        }
        list.remove(o);
        size--;
        if (list.isEmpty()) {
            map.remove(o);
        }
        return true;
    }

    private class BagIterator implements Iterator {
        private Iterator<List> mapIterator = null;
        private List currentList = null;
        private Iterator listIterator = null;
        private boolean canRemove = false;

        public BagIterator() {
            mapIterator = map.values().iterator();
            if (mapIterator.hasNext()) {
                currentList = mapIterator.next();
            } else {
                // empty list
                currentList = new LinkedList();
            }
            listIterator = currentList.iterator();
        }

        @Override
        public boolean hasNext() {
            return listIterator.hasNext() || mapIterator.hasNext();
        }

        @Override
        public Object next() {
            if (listIterator.hasNext()) {
                canRemove = true;
                return listIterator.next();
            }
            if (mapIterator.hasNext()) {
                canRemove = true;
                currentList = mapIterator.next();
                listIterator = currentList.iterator();
                return listIterator.next();
            }
            throw new NoSuchElementException("Iteration has no more elements");
        }

        @Override
        public void remove() {
            if (canRemove) {
                canRemove = false;
                listIterator.remove();
                size--;
                if (currentList.isEmpty()) {
                    mapIterator.remove();
                }
            } else {
                throw new IllegalStateException("next method has not yet been called, or the remove method has already been called after the last call to the next method");
            }
        }
    }
}
