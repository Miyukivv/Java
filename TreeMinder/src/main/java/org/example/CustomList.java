package org.example;

import java.util.AbstractList;
import java.util.Iterator;

public class CustomList<T> extends AbstractList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public void addLast(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public T getLast() {
        if (tail == null) {
            return null;
        }
        return tail.value;
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public T getFirst() {
        if (head == null) {
            return null;
        }
        return head.value;
    }

    public T removeLast() {
        if (head == null) {
            return null;
        }
        if (head == tail) {
            T retVal = head.value;
            head = null;
            tail = null;
            size = 0;
            return retVal;
        }

        Node<T> tmp = head;
        while (tmp.next != tail) {
            tmp = tmp.next;
        }

        T retVal = tail.value;
        tail = tmp;
        tail.next = null;
        size--;
        return retVal;
    }

    public T removeFirst() {
        if (head == null) {
            return null;
        }
        T retVal = head.value;
        if (head == tail) {
            head = null;
            tail = null;
            size = 0;
            return retVal;
        }
        head = head.next;
        size--;
        return retVal;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T retVal = current.value;
                current = current.next;
                return retVal;
            }
        };
    }

    public static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }
}
