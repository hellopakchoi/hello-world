package com.test.hash;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 1; i < 13; i++) {
            map.put(i + "abc", null);
        }
        for (int i = 0; i < 10; i++) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.print(entry.getKey() + "-");
            }
            System.out.println();
        }
        map.put("xzy", null);
        for (int i = 0; i < 10; i++) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.print(entry.getKey() + "-");
            }
            System.out.println();
        }
        map.put("opq", null);
        for (int i = 0; i < 10; i++) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.print(entry.getKey() + "-");
            }
            System.out.println();
        }
    }
}
