package dsa.arrays;

import java.util.Arrays;

public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 455, 455, 66, 778, 876, 765, 32, 876, 765, 32};
        Arrays.sort(array); // Ensure array is sorted
        System.out.println("sorted array - " + Arrays.toString(array));
        int length = removeDuplicatesInPlace(array);
        System.out.println(Arrays.toString(Arrays.copyOf(array, length)));
    }

    private static int removeDuplicatesInPlace(int[] array) {
        if (array.length == 0) return 0;
        int uniqueIndex = 0; // Pointer for unique elements

        for (int i = 1; i < array.length; i++) {
            if (array[i] != array[uniqueIndex]) {
                uniqueIndex++;
                array[uniqueIndex] = array[i]; // Overwrite duplicates
            }
        }
        return uniqueIndex + 1; // New length of unique elements
    }
}
