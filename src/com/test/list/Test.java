package com.test.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Object[] arr = new Object[2];
        arr[2] = null;
        List<Object> list = new ArrayList<>(Integer.MAX_VALUE - 9);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            list.add(null);
        }
    }

    @org.junit.Test
    public void linkedList() {
        LinkedList<Object> list = new LinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        System.out.println(list);
        list.pop();
        System.out.println(list);

    }
}
