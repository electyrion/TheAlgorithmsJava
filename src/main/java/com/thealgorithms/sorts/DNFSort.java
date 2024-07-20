package com.thealgorithms.sorts;

public final class DNFSort {
    private DNFSort() {
    }

    // Sort the input array, the array is assumed to
    // have values in {0, 1, 2}
    static void sort012(int[] a, int arrSize) {
        int low = 0;
        int high = arrSize - 1;
        int mid = 0;
        int temp;
        while (mid <= high) {
            switch (a[mid]) {
            case 0 -> {
                temp = a[low];
                a[low] = a[mid];
                a[mid] = temp;
                low++;
                mid++;
                }

            case 1 -> mid++;
            case 2 -> {
                temp = a[mid];
                a[mid] = a[high];
                a[high] = temp;
                high--;
                }

            default -> throw new IllegalArgumentException("Unexpected value: " + a[mid]);
            }
        }
    }

    /* Utility function to print array arr[] */
    static void printArray(int[] arr, int arrSize) {
        for (int i = 0; i < arrSize; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /*Driver function to check for above functions*/
    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        int arrSize = arr.length;
        sort012(arr, arrSize);
        System.out.println("Array after segregation ");
        printArray(arr, arrSize);
    }
}
