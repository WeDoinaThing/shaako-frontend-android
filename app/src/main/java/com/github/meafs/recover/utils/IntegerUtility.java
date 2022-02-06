package com.github.meafs.recover.utils;

import java.util.Arrays;

public class IntegerUtility {
    public static int[] getBestKIndices(int[] array, int num) {
        IndexValuePair[] pairs = new IndexValuePair[array.length];
        for (int i = 0; i < array.length; i++) {
            pairs[i] = new IndexValuePair(i, array[i]);
        }
        Arrays.sort(pairs, (o1, o2) -> Integer.compare(o2.value, o1.value));
        int[] result = new int[num];
        for (int i = 0; i < num; i++) {
            result[i] = pairs[i].index;
        }
        return result;
    }

    private static class IndexValuePair {
        private final int index;
        private final int value;

        public IndexValuePair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}
