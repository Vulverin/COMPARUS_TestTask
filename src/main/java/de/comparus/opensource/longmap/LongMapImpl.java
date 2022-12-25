package de.comparus.opensource.longmap;

import java.util.Objects;

public class LongMapImpl<V> implements LongMap<V> {

    private int size;
    private Node<V> firstNode;
    private Node<V> lastNode;

    public LongMapImpl(){
        size=0;
    }

    public V put(long key, V value) {
        if(containsKey(key)){
            Node<V> currentNode = firstNode;
            while (currentNode.next != null){
                if(currentNode.key == key){
                    currentNode.value = value;
                    return value;
                }
                currentNode = currentNode.next;
            }
        }
        if (firstNode == null){
            firstNode = new Node<>(value, key, null);
            lastNode = firstNode;
            size++;
            return value;
        }
            lastNode.next = new Node<>(value, key, null);
            lastNode = lastNode.next;
            size++;
            return value;
    }

    public V get(long key) {
        Node<V> currentNode = firstNode;
        while (currentNode != null){
            if (key == currentNode.key){
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    public V remove(long key) {
        if(isEmpty()){
            return null;
        }
        Node<V> currentNode = firstNode;
        if(currentNode.key == key){
            V value = currentNode.value;
            firstNode = currentNode.next;
            size--;
            return value;
        }
        while (currentNode != null){
            if(currentNode.next == null && currentNode.next.key == key){
                V value = currentNode.next.value;
                currentNode.next = null;
                lastNode = currentNode;
                size--;
                return value;
            }
            if(currentNode.key == key){
                V value = currentNode.value;
                currentNode = currentNode.next;
                size--;
                return value;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(long key) {
        Node<V> currentNode = firstNode;
        while (currentNode != null){
            if(currentNode.key == key){
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public boolean containsValue(V value) {
        Node<V> currentNode = firstNode;
            while (currentNode != null){
                if(Objects.equals(value, currentNode.value)){
                    return true;
                }
                currentNode = currentNode.next;
            }
        return false;
    }

    public long[] keys() {
        Node<V> currentNode = firstNode;
        long[] keys = new long[size];
        int index = 0;
        while (currentNode != null){
            keys[index++] = currentNode.key;
            currentNode = currentNode.next;
        }
        return keys;
    }

    public V[] values() {
        Node<V> currentNode = firstNode;
        V[] values = (V[]) new Object[size];
        int index = 0;
        while (currentNode != null){
                values[index++] = currentNode.value;
                currentNode = currentNode.next;
        }
        return values;
    }

    public long size() {
        return size;
    }

    public void clear() {
        Node<V> currentNode = firstNode;
        while (currentNode != null){
            Node<V> tempCurrentNode = currentNode;
            currentNode = currentNode.next;
            tempCurrentNode = null;
        }
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    private static class Node<V> {
        private V value;
        private final long key;
        private Node<V> next;

        public Node(V value, long key, Node<V> next) {
            this.value = value;
            this.key = key;
            this.next = next;
        }
    }

}
