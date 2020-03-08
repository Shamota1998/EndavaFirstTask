package com.endava.internship.s2020.hashmaptask;

import java.util.*;

public class StudentMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_CAPACITY = 5;
    private int size;
    private Node<K, V>[] arr;

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;
    }
    public StudentMap(int capacity) {
        arr = new Node[capacity];
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
                if (arr[index].key.equals(node.key)) {
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
        return 1;//Math.abs(key.hashCode()) % DEFAULT_CAPACITY;
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

        int index = bucketIndex(o.hashCode());
        Node<K, V> tmpNode = arr[index];
        while (tmpNode != null) {
            if (tmpNode.key.equals(o)) {
                return true;
            } else {
                tmpNode = tmpNode.next;
            }
        }
        return false;
    }


    @Override
    public boolean containsValue(Object o) {
        for (Node<K, V> kvNode : arr) {
            Node<K, V> tempNode = kvNode;
            while (tempNode != null) {
                if (tempNode.value.equals(o)) {
                    return true;
                } else {
                    tempNode = tempNode.next;
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object o) {
        int index = bucketIndex(o.hashCode());
        Node<K, V> nodeTmp = arr[index];
        if (nodeTmp.next == null) {
            if (nodeTmp.key.equals(o)) {
                return nodeTmp.value;
            } else return null;
        } else {
            while (nodeTmp != null) {
                if (nodeTmp.key.equals(o)) {
                    return nodeTmp.value;
                }
                nodeTmp = nodeTmp.next;
            }
        }
        return null;
    }

    @Override
    public V remove(Object o) {
        int removedIndex = bucketIndex(o.hashCode());
        V removedValue;
        if (arr[removedIndex] != null) {
            if (arr[removedIndex].key.equals(o)) {
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
        }
        if (arr[removedIndex].next != null) {
            Node<K, V> nodeTmp = arr[removedIndex];
            while (nodeTmp.next != null) {
                if (nodeTmp.next.key.equals(o)) {
                    removedValue = nodeTmp.next.value;
                    nodeTmp.next = nodeTmp.next.next;
                    this.size--;
                    return removedValue;
                }
                nodeTmp = nodeTmp.next;
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
        Node<K, V> node1;
        if (size != 0) {
            for (Node<K, V> kvNode : arr) {
                node1 = kvNode;
                while (node1 != null) {
                    keySet.add(node1.key);
                    node1 = node1.next;
                }
            }
        }
        return keySet;
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> listOfValues = new ArrayList<>();
        if (size == 0) {
            return listOfValues;
        } else {
            for (Node<K, V> studentNode : arr) {
                while (studentNode != null) {
                    listOfValues.add(studentNode.value);
                    studentNode = studentNode.next;
                }
            }
        }
        return listOfValues;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        Student[] arrList = new Student[keySet().size()];
        this.keySet().toArray(arrList);
        for (Student key : arrList) {
            System.out.println("\nValue : " + this.get(key) + "\n" + key.toString());
        }
        return "";
    }
}

