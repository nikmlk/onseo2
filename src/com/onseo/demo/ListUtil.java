package com.onseo.demo;

import java.util.List;

public class ListUtil {
    public static int sum(List<Integer> list) {
        return list.stream().mapToInt(value -> value).sum();
    }
}
