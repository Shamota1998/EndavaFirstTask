package com.endava.internship.s2020.hashmaptask;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String breakLine = "\n------------------------------------------\n";

        Student st1 = new Student("Vasilii", LocalDate.ofYearDay(1998, 6), "student");
        Student st2 = new Student("Alexei", LocalDate.ofYearDay(1998, 7), "QA");
        Student st3 = new Student("Alex", LocalDate.ofYearDay(1998, 7), "QA");
        Student st7 = new Student("Alx", LocalDate.ofYearDay(1998, 7), "QA");
        Student st4 = new Student("Andrei", LocalDate.ofYearDay(1982, 6), "student");
        Student st5 = new Student("Kate", LocalDate.ofYearDay(1993, 6), "Developer");
        Student st6 = new Student("Cris", LocalDate.ofYearDay(1994, 7), "Intern");

        Map<Student, Integer> map2 = new StudentMap<>();
        StudentMap<Student, Integer> map = new StudentMap<>();


        map.put(st1, 10);
        map.put(st1, 11);
        map.put(st7, 4);
        map.put(st3, 33);

        map2.put(st4, 1);
        map2.put(st5, 2);
        map2.put(st6, 6);

        map.putAll(map2);
        map.put(st2, 44);

        System.out.println("|----------------------------------------------|\n"
                + "           StudentMap implementation : \n" +
                "|----------------------------------------------|");
        System.out.println("isEmpty method(after put method) = " + map.isEmpty());
        System.out.println(breakLine);
        System.out.println("Contains Key: " + map.containsKey(st5));
        System.out.println(breakLine);
        System.out.println("Contains value: " + map.containsValue(4));
        System.out.println(breakLine);

        System.out.println("Map Size Method = " + map.size());
        System.out.println(breakLine);

        ArrayList<Integer> arr = (ArrayList) map.values();
        ListIterator<Integer> iter = arr.listIterator();
        System.out.println("Values(map.values()) :");
        while (iter.hasNext()) {
            System.out.println("Value : " + iter.next());
        }
        System.out.println("end values");
        System.out.println(breakLine);
        System.out.println("Method get for st7: " + map.get(st7));
        System.out.println(breakLine);
        map.remove(st1);
        ArrayList<Integer> arr2 = (ArrayList) map.values();

        for (Integer integer : arr2) {
            System.out.println("After removing method :" + integer);
        }
        System.out.println(breakLine);

        Set<Student> keySet = map.keySet();
        System.out.println("KeySet Method : \n");
        System.out.println(keySet);

        map.clear();
        System.out.println("isEmpty method (after clear() method) = " + map.isEmpty());

    }
}
