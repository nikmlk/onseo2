package com.onseo.demo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import static com.onseo.demo.ListUtil.sum;

public class CustomRecursiveTask extends RecursiveTask<List<Integer>> {
    private List<Integer> newList;
    private List<Integer> oldList;
    private int weight;
    private int maxWeight;

    public CustomRecursiveTask(List<Integer> newList, List<Integer> oldList, int weight, int maxWeight) {
        this.newList = newList;
        this.oldList = oldList;
        this.weight = weight;
        this.maxWeight = maxWeight;
    }

    @Override
    protected List<Integer> compute() {

        if ((sum(newList) + weight) > maxWeight){
            return newList;
        }
        if (weight > 0 ){
            newList.add(weight);
        }

        List<CustomRecursiveTask> subTasks = new ArrayList<>();

        for (int i = 0; i < oldList.size(); i++) {
            if (!newList.contains(oldList.get(i))){
                CustomRecursiveTask task = new CustomRecursiveTask(new ArrayList<>(newList), oldList, oldList.get(i), maxWeight);
                task.fork();
                subTasks.add(task);
            }
        }

        List<Integer> max = new ArrayList<>();
        for (int i = 0; i < subTasks.size(); i++) {
            List<Integer> array = subTasks.get(i).join();
            if (sum(max) < sum(array)){
                max = array;
            }
        }

        return max;
    }

}
