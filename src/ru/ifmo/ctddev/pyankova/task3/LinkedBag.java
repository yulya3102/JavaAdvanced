package ru.ifmo.ctddev.pyankova.task3;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: yulya3102
 * Date: 20.03.13
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */
public class LinkedBag implements Collection {
    private class Node {
        public int index;
        public Object value;

        public Node(int index, Object value) {
            this.index = index;
            this.value = value;
        }

        public int hashCode() {
            return value.hashCode() + index;
        }

        public boolean equals(Object o) {
            Node node = (Node) o;
            return index == node.index && value == node.value;
        }
    }

    private Set<Node> set = null;
    private Map<Object, Integer> map = null;
    private int size = 0;

    public LinkedBag() {
        set = new LinkedHashSet<>();
        map = new HashMap<>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public Iterator<Object> iterator() {
        return new LinkedBagIterator();
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
    public boolean remove(Object o) {
        Integer index = map.get(o);
        if (index == null) {
            return false;
        }
        Object e = o;
        Node node = new Node(--index, e);
        set.remove(node);
        size--;
        if (index == 0) {
            map.remove(e);
        } else {
            map.put(e, index);
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection objects) {
        return map.keySet().containsAll(objects);
    }

    @Override
    public boolean addAll(Collection es) {
        boolean result = false;
        for (Object e : es) {
            if (add(e)) {
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
        boolean result = false;
        for (Map.Entry<Object, Integer> entry : map.entrySet()) {
            Object e = entry.getKey();
            if (!objects.contains(e)) {
                Integer index = entry.getValue();
                while (index > 0) {
                    remove(e);
                    index--;
                }
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

    private class LinkedBagIterator implements Iterator<Object> {
        private Iterator<Node> nodeIterator;

        public LinkedBagIterator() {
            nodeIterator = set.iterator();
        }

        @Override
        public boolean hasNext() {
            return nodeIterator.hasNext();
        }

        @Override
        public Object next() {
            if (nodeIterator.hasNext()) {
                return nodeIterator.next().value;
            }
            throw new NoSuchElementException("Iteration has no more elements");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported by this Iterator");
        }
    }
}
