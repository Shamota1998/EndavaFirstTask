package com.endava.internship.s2020.hashmaptask;

import java.util.*;

public class StudentMap<K, V> implements Map<K, V> {
    private int size;
    private static final int DEFAULT_CAPACITY = 5;
    private Node<K, V>[] arr;

    public StudentMap(int capacity) {
        arr = new Node[capacity];
    }

    public StudentMap() {
        arr = new Node[DEFAULT_CAPACITY];
    }


    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;
    }

    @Override
    public V put(K key, V value) {
        Node<K, V> node = new Node<>();
        node.key = key;
        node.value = value;
        int index = bucketIndex(key);

        if (arr[index] == null) {
            arr[index] = node;
            this.size++;
        }
        else{
        Node<K, V> tempNode = arr[index];

        while (tempNode != null) {
            if (arr[index].key.equals(node.key)) {
                V prevValue = arr[index].value;
                arr[index].value = node.value;
                return prevValue;
            }

            if (tempNode.next == null) {
                tempNode.next = node;
                this.size++;
                break;
            }
            tempNode = tempNode.next;
        }
        }

        return null;
    }


    public int bucketIndex(Object key) {
        return Math.abs(key.hashCode()) % DEFAULT_CAPACITY;
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
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] != null) && (arr[i].next == null)) {
                if (arr[i].value.equals(o)) {
                    return true;
                }
            } else if (arr[i] != null && arr[i].next != null) {
                    Node <K,V> tempNode = arr[i];
                    while (tempNode != null) {

                        if (tempNode.value.equals(o)) {
                            return true;
                        } else {
                            tempNode = tempNode.next;
                        }
                    }
            }
        }
        return false;
    }

    @Override
    public V get(Object o) {
        if (!(o instanceof Student)) {
            return null;
        }
        int index = bucketIndex(o.hashCode());
        Node<K, V> nodeTmp = arr[index];
        if (nodeTmp.next == null) {
            if (nodeTmp.key.equals(o)) {
                return nodeTmp.value;
            } else return null;
        } else {
            while (nodeTmp.next != null) {
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
        Student obj = (Student) o;

        int removedIndex = bucketIndex(o.hashCode());

        if (arr[removedIndex] != null) {
            if (arr[removedIndex].next == null) {
                if (arr[removedIndex].key.equals(obj)) {
                    arr[removedIndex].key = null;
                    arr[removedIndex].value = null;
                    arr[removedIndex] = null;
                }
            } else {
                while (arr[removedIndex] != null) {
                    if (arr[removedIndex].key.equals(obj)) {

                        arr[removedIndex].next = arr[removedIndex].next.next;

                    }
                    arr[removedIndex] = arr[removedIndex].next;
                }
            }
        }
        return null;
    }


    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        Set<K> keySet = (Set<K>) map.keySet();
        for (K obj : keySet
        ) {
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
        Node node1;

        if (size == 0) {
            return null;
        } else {

            for (int i = 0; i < arr.length; i++) {
                node1 = arr[i];
                while (node1 != null) {
                    keySet.add((K) node1.key);
                    node1 = node1.next;
                }
            }
            return keySet;
        }
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> arrayList = new ArrayList<>();
        if (size == 0) {
            return arrayList;
        } else {
            for (Node<K, V> studentIntegerNode : arr) {
                while (studentIntegerNode != null) {
                    arrayList.add((V) studentIntegerNode.value);
                    studentIntegerNode = studentIntegerNode.next;
                }
            }
        }
        return arrayList;
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
        for (Student student : arrList) {
            System.out.println("\nValue : " + this.get(student) + "\n" + student.toString());
        }
        return "";
    }

}

