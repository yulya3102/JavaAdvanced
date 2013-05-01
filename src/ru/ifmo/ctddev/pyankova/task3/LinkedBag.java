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
        public Node prev;
        public Node next;
        public Object value;

        public Node(Object value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        public int hashCode() {
            return value.hashCode() + (prev == null ? 0 : prev.value.hashCode()) + (next == null ? 0 : next.value.hashCode());
        }

        public boolean equals(Object o) {
            Node node = (Node) o;
            return value == node.value && prev == node.prev && next == node.next;
        }
    }

    private Set<Node> set = null;
    private Map<Object, Node> map = null;
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
        Node lastNode = map.get(e);
        Node newNode = new Node(e, lastNode, null);
        set.add(newNode);
        map.put(e, newNode);
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node node = map.get(o);
        if (node == null) {
            return false;
        }
        // remove node from set
        set.remove(node);
        size--;
        // remove node from map
        map.put(o, node.prev);
        if (node.prev != null) {
            node.prev.next = null;
        }
        return true;
    }

    private class LinkedBagIterator implements Iterator {
        private Iterator<Node> setIterator = null;
        private boolean canRemove = false;
        private Node currentNode = null;

        public LinkedBagIterator() {
            setIterator = set.iterator();
        }

        @Override
        public boolean hasNext() {
            return setIterator.hasNext();
        }

        @Override
        public Object next() {
            if (setIterator.hasNext()) {
                canRemove = true;
                currentNode = setIterator.next();
                return currentNode.value;
            }
            throw new NoSuchElementException("Iteration has no more elements");
        }

        @Override
        public void remove() {
            if (canRemove) {
                canRemove = false;
                size--;
                // remove from map
                if (currentNode.prev != null) {
                    currentNode.prev.next = currentNode.next;
                }
                if (currentNode.next != null) {
                    currentNode.next.prev = currentNode.prev;
                } else {
                    // this is last element in map
                    map.put(currentNode.value, currentNode.prev);
                }
                // remove from set
                setIterator.remove();
            } else {
                throw new IllegalStateException("next method has not yet been called, or the remove method has already been called after the last call to the next method");
            }
        }
    }
}
