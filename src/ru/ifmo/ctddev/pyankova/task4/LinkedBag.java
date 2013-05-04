package ru.ifmo.ctddev.pyankova.task4;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: yulya3102
 * Date: 20.03.13
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */
public class LinkedBag<E> extends AbstractSet<E> {
    private class Node {
        public Node prev;
        public Node next;
        public E value;

        public Node(E value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private Set<Node> set;
    private Map<E, Node> map;
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
    public Iterator<E> iterator() {
        return new LinkedBagIterator();
    }

    @Override
    public boolean add(E e) {
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
        map.put(node.value, node.prev);
        if (node.prev != null) {
            node.prev.next = null;
        }
        return true;
    }

    private class LinkedBagIterator implements Iterator<E> {
        private Iterator<Node> setIterator;
        private boolean canRemove = false;
        private Node currentNode;

        public LinkedBagIterator() {
            setIterator = set.iterator();
        }

        @Override
        public boolean hasNext() {
            return setIterator.hasNext();
        }

        @Override
        public E next() {
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
