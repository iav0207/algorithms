package ru.iav.std.algorithms.p1.w1;

import java.util.Arrays;

/**
 * Created by takoe on 12.02.17.
 */
public class TwoSumFast implements TwoSum {

    public int countTwoSums(int[] array) {
        Arrays.sort(array);
        if (containsDuplicates(array)) {
            throw new IllegalArgumentException("Input array must contain no duplicates.");
        }
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            int j = Arrays.binarySearch(array, -array[i]);
            if (j > i) {
                count++;
            }
        }
        System.out.println("The two-sums count is " + count);
        return count;
    }

    private boolean containsDuplicates(int[] sortedArray) {
        for (int i = 1; i < sortedArray.length; i++) {
            if (sortedArray[i] == sortedArray[i-1]) {
                return true;
            }
        }
        return false;
    }

}
