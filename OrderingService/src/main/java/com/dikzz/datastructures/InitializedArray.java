package com.dikzz.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by dikzz on 8/19/16.
 */
public class InitializedArray extends ArrayList<Integer> {

    public InitializedArray(int initialCapacity) {
        super(initialCapacity);
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        for (int i = 0; i < initialCapacity; i++) {
            this.add(threadLocalRandom.nextInt(initialCapacity));
        }
    }

    public void shuffle() {
        Collections.shuffle(this);
    }

    public void sortBubble() {
        for (int i = 1; i < this.size(); i++) {
            for (int j = 0; j < this.size() - i; j++) {
                if (this.get(j) > this.get(j + 1)) {
                    Collections.swap(this, j, j + 1);
                }
            }
        }
    }

    public void insertSort() {
        for (int i = 1; i < this.size(); i++) {
            swapBack(i);
        }
    }

    public void mergeSort() {
        List<Integer> merge = merge(this);
        for (int i = 0; i < merge.size(); i++) {
            this.set(i, merge.get(i));
        }
    }

    private List<Integer> merge(List<Integer> array) {
        if (array.size() == 1) {
            return array;
        }

        int middle = array.size() / 2;
        List<Integer> first = array.subList(0, middle);
        List<Integer> last = array.subList(middle, array.size());
        List<Integer> firstResult = merge(first);
        List<Integer> lastResult = merge(last);
        return mergeResult(firstResult, lastResult);
    }

    private List<Integer> mergeResult(List<Integer> firstResult, List<Integer> lastResult) {
        int total = firstResult.size() + lastResult.size();
        List<Integer> result = new ArrayList<>(total);
        int firstIndex = 0;
        int lastIndex = 0;
        for (int i = 0; i < total; i++) {
            if (firstIndex >= firstResult.size() || (lastIndex < lastResult.size() && firstResult.get(firstIndex) >= lastResult.get(lastIndex))) {
                result.add(lastResult.get(lastIndex));
                lastIndex++;
            } else if (lastIndex >= lastResult.size() || (firstIndex < firstResult.size() && firstResult.get(firstIndex) < lastResult.get(lastIndex))) {
                result.add(firstResult.get(firstIndex));
                firstIndex++;
            } else {
                throw new IllegalStateException();
            }
        }
        return result;
    }

    private void swapBack(int j) {
        while (j != 0 && this.get(j) < this.get(j - 1)) {
            Collections.swap(this, j, --j);
        }
    }

    public static void main(String[] args) {
        InitializedArray integers = new InitializedArray(21);
        System.out.println(integers);
        integers.shuffle();
        System.out.println(integers);
        integers.mergeSort();
        System.out.println(integers);


    }
}
