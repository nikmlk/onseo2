package com.onseo.demo;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import static com.onseo.demo.ListUtil.sum;

public class Main {
    public static void main(String[] args) {

        List<Integer> humanWeight = getFromArgsOrDefault(args);

        List<Integer> rightList = new ForkJoinPool().invoke(new CustomRecursiveTask(new LinkedList<>(), humanWeight, -1, sum(humanWeight) / 2));

        List<Integer> leftList = humanWeight.stream()
                .filter(weight -> !rightList.contains(weight))
                .collect(Collectors.toList());

        System.out.println("Total sum of rightList: " + sum(rightList));
        System.out.println("RightList: " + rightList);
        System.out.println();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println();
        System.out.println("Total sum of leftList: " + sum(leftList));
        System.out.println("LeftList: " + leftList);


    }

    private static List<Integer> getFromArgsOrDefault(String[] args) {
        List<Integer> humanWeight = new ArrayList<>();
        if (args.length > 0) {
            humanWeight = Arrays.stream(args)
                    .map(str -> {
                        try {
                            Integer weight = Integer.valueOf(str);
                            return weight;
                        } catch (NumberFormatException e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        if (humanWeight.size() == 0) {
            humanWeight.add(98);
            humanWeight.add(83);
            humanWeight.add(75);
            humanWeight.add(68);
            humanWeight.add(65);
            humanWeight.add(74);
            humanWeight.add(68);
            humanWeight.add(58);
            humanWeight.add(54);
            humanWeight.add(69);
        }

        return humanWeight;
    }
}
