package com.endava.internship.s2020.hashmaptask;

import java.util.*;

public class StudentMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_CAPACITY = 5;
    private int size;
    private Node<K, V>[] arr;

    public StudentMap(int newCapacity) {
        if (newCapacity <= 0) {
            throw new IllegalArgumentException("Argument must be non-negative and greater than 0!");
        }
        arr = new Node[newCapacity];
    }

    public StudentMap() {
        arr = new Node[DEFAULT_CAPACITY];
    }

    @Override
    public V put(K key, V value) {
        Node<K, V> node = new Node<>();
        node.key = key;
        node.value = value;
        int index = bucketIndex(key);

        if (arr[index] == null) {
            arr[index] = node;
        } else {
            Node<K, V> tempNode = arr[index];
            while (tempNode != null) {
                if (Objects.equals(arr[index].key, node.key)) {
                    V prevValue = arr[index].value;
                    arr[index].value = node.value;
                    return prevValue;
                }
                if (tempNode.next == null) {
                    tempNode.next = node;
                    break;
                }
                tempNode = tempNode.next;
            }
        }
        this.size++;
        return null;
    }

    public int bucketIndex(Object key) {
        if (Objects.equals(key, null)) {
            return 0;
        }
        return Math.abs(key.hashCode()) % arr.length;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object o) {
        int index;
        if (Objects.equals(o, null)) {
            index = bucketIndex(o);
        } else index = bucketIndex(o.hashCode());
        Node<K, V> tmpNode = arr[index];
        while (tmpNode != null) {
            if (Objects.equals(tmpNode.key, o)) {
                return true;
            }
            tmpNode = tmpNode.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        for (Node<K, V> kvNode : arr) {
            Node<K, V> tempNode = kvNode;
            while (tempNode != null) {
                if (Objects.equals(tempNode.value, o)) {
                    return true;
                }
                tempNode = tempNode.next;
            }
        }
        return false;
    }

    @Override
    public V get(Object o) {
        int index;
        if (Objects.equals(o, null)) {
            index = bucketIndex(o);
        } else index = bucketIndex(o.hashCode());
        Node<K, V> nodeTmp = arr[index];
        while (nodeTmp != null) {
            if (Objects.equals(nodeTmp.key, o)) {
                return nodeTmp.value;
            }
            nodeTmp = nodeTmp.next;
        }
        return null;
    }

    @Override
    public V remove(Object o) {
        int removedIndex;
        V removedValue;
        if (Objects.equals(null, o)) {
            removedIndex = bucketIndex(o);
        } else {
            removedIndex = bucketIndex(o.hashCode());
        }
        if (arr[removedIndex] != null) {
            if (Objects.equals(arr[removedIndex].key, o)) {
                if (arr[removedIndex].next == null) {
                    removedValue = arr[removedIndex].value;
                    arr[removedIndex] = null;
                } else {
                    removedValue = arr[removedIndex].value;
                    arr[removedIndex] = arr[removedIndex].next;
                }
                this.size--;
                return removedValue;
            }

            if (arr[removedIndex].next != null) {
                Node<K, V> nodeTmp = arr[removedIndex];
                while (nodeTmp.next != null) {
                    if (Objects.equals(nodeTmp.next.key, o)) {
                        removedValue = nodeTmp.next.value;
                        nodeTmp.next = nodeTmp.next.next;
                        this.size--;
                        return removedValue;
                    }
                    nodeTmp = nodeTmp.next;
                }
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        Set<K> keySet = (Set<K>) map.keySet();
        for (K obj : keySet) {
            this.put(obj, map.get(obj));
        }
    }

    @Override
    public void clear() {
        this.size = 0;
        Arrays.fill(arr, null);
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        Node<K, V> tempNode;
        for (Node<K, V> kvNode : arr) {
            tempNode = kvNode;
            while (tempNode != null) {
                keySet.add(tempNode.key);
                tempNode = tempNode.next;
            }
        }
        return keySet;
    }

    @Override
    public Collection<V> values() {
        List<V> listOfValues = new ArrayList<>();
        for (Node<K, V> kvNode : arr) {
            while (kvNode != null) {
                listOfValues.add(kvNode.value);
                kvNode = kvNode.next;
            }
        }
        return listOfValues;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }
    //
    @Override
    public String toString() {
        K[] arrList = (K[]) new Object[keySet().size()];
        StringBuilder stringBuilder = new StringBuilder("{");
        this.keySet().toArray(arrList);
        for (K key : arrList) {
            if (!Objects.equals(key, null)) {
                stringBuilder.append(key.toString() + " = " + this.get(key) + ",\n");
            } else {
                stringBuilder.append(null + " = " + this.get(key) + ",\n");
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;
    }
}

