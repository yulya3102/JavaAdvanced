package ru.ifmo.ctddev.pyankova.task3;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: yulya3102
 * Date: 20.03.13
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */
public class LinkedBag extends AbstractSet {
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
    public Iterator iterator() {
        return new LinkedBagIterator();
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
        Node node = new Node(--index, o);
        set.remove(node);
        size--;
        if (index == 0) {
            map.remove(o);
        } else {
            map.put(o, index);
        }
        return true;
    }

    private class LinkedBagIterator implements Iterator {
        private Iterator<Node> nodeIterator = null;
        private boolean canRemove = false;
        private Node currentNode = null;

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
                canRemove = true;
                currentNode = nodeIterator.next();
                return currentNode.value;
            }
            throw new NoSuchElementException("Iteration has no more elements");
        }

        @Override
        public void remove() {
            if (canRemove) {
                canRemove = false;
                size--;
                nodeIterator.remove();
                // FIXME: map.get(currentNode.value) doesn't contain amount of objects equal to currentNode.value anymore
            } else {
                throw new IllegalStateException("next method has not yet been called, or the remove method has already been called after the last call to the next method");
            }
        }
    }
}
