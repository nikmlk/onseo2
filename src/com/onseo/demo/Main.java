package com.onseo.demo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        List<Integer> things = new ArrayList<>();
        things.add(98);
        things.add(83);
        things.add(75);
        things.add(68);
        things.add(65);
        things.add(74);
        things.add(68);
        things.add(58);
        things.add(54);
        things.add(69);

        List<Integer> result = new ForkJoinPool().invoke(new CustomRecursiveTask(new LinkedList<>(), things, -1, 580));

        System.out.println(result.stream().mapToInt(val -> val).sum());
        System.out.println();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println();
        System.out.println(result);

    }
}
