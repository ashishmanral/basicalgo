package practice;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * Created by ashis on 12/27/2016.
 */
public class Sorting {

    public static void main(String[] args) {
        int iterations = 100;
        int[] arr = new int[iterations];

        System.out.println("Unsorted Array : ");

        for(int i = 0; i < iterations ; ++i) {
            arr[i] = (int)(Math.random() * iterations + 1);
            System.out.print(arr[i] + " ");
        }

        bucketSort(copyArr(arr));
        bubbleSort(copyArr(arr));
        selectionSort(copyArr(arr));
        insertionSort(copyArr(arr));
        quickSort(copyArr(arr));
        mergeSort(copyArr(arr));
        correctAnswer(copyArr(arr));
    }

    /**
     * This is bucket sort. Couple of disadvantages :
     * 1. Memory size
     * 2. Cannot sort negative numbers
     * 3. Need an O(n) lookup for highest element.
     * @param arr
     */
    private static void bucketSort(int[] arr) {
        int max = -1;
        for(int i : arr) {
            if(i>max) max = i;
        }
        System.out.println("\nSorted Array Through Bucket Sort : ");

        int[] lookupArr = new int[max];
        for(int i : arr) {
            ++lookupArr[i - 1];
        }

        for(int i = 0; i < max; ++i) {
            if(lookupArr[i] != 0) {
                int number = i + 1;
                for(int j = 1; j <= lookupArr[i]; ++j) {
                    System.out.print(number + " ");
                }
            }
        }
    }

    private static void selectionSort(int[] arr) {
        System.out.println("\nSorted Array Through Selection Sort : ");

        for(int i = 0; i < arr.length; ++i) {
            int minIndex = i;
            int min = arr[i];
            for(int j = i + 1; j < arr.length; ++j) {
                if(arr[j] < min) {
                    minIndex = j;
                    min = arr[j];
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }

        printArr(arr);
    }

    private static void bubbleSort(int[] arr) {
        System.out.println("\nSorted Array Through Bubble Sort : ");

        for(int i = 0; i < arr.length; ++i) {
            for(int j = 0; j < arr.length - i - 1; ++j) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        printArr(arr);
    }

    private static void insertionSort(int[] arr) {
        System.out.println("\nSorted Array Through Insertion Sort : ");

        for(int i = 0; i < arr.length; ++i) {
            int backComparator = i - 1;
            int curr = arr[i];
            while(backComparator >= 0 && arr[backComparator] > curr) {
                arr[backComparator + 1] = arr[backComparator];
                --backComparator;
            }
            arr[backComparator + 1] = curr;
        }

        printArr(arr);
    }

    private static void quickSort(int[] arr) {
        System.out.println("\nSorted Array Through Quick Sort : ");

        quickSortHelper(0, arr.length - 1, arr);

        printArr(arr);
    }

    private static void mergeSort(int[] arr) {
        System.out.println("\nSorted Array Through Merge Sort : ");

        int[] sorted = mergeSortHelper(0, arr.length - 1, arr);

        printArr(sorted);
    }

    private static int[] mergeSortHelper(int low, int high, int[] arr) {
        if(low == high) {
            return new int[]{arr[low]};
        }

        int middle = (low + high) / 2;
        return merge(mergeSortHelper(low, middle, arr), mergeSortHelper(middle + 1, high, arr));
    }

    private static int[] merge(int[] arr1, int[] arr2) {
        int li = 0;
        int ri = 0;
        int mi = 0;

        int[] merged = new int[arr1.length + arr2.length];

        while(mi != merged.length) {
            merged[mi++] = (li == arr1.length ? arr2[ri++] : (
                    ri == arr2.length ? arr1[li++] : (
                            arr1[li] < arr2[ri] ? arr1[li++] : arr2[ri++])));
        }

        return merged;
    }

    private static void quickSortHelper(int low, int high, int[] arr) {
        int range = high - low;

        // base case
        if(range <= 0) {
            return;
        }

        int randomIndex = (int)(Math.random() * (range + 1));
        swapIndex(high, low + randomIndex, arr);

        int wall = low - 1;
        for(int i = low; i<high;  ++i) {
            if(arr[i] < arr[high]) {
                swapIndex(wall + 1, i, arr);
                ++wall;
            }
        }

        swapIndex(high, wall + 1, arr);

        quickSortHelper(low, wall, arr); // Sort left of the new pivot position
        quickSortHelper(wall + 2, high, arr); // Sort right of the new pivot position
    }

    private static void swapIndex(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void correctAnswer(int[] arr) {
        Arrays.sort(arr);
        System.out.println("\nCorrect Sorted Array : ");

        for(int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static int[] copyArr(int[] arr) {
        int[] copy = new int[arr.length];

        for(int i = 0; i < arr.length; ++i) {
            copy[i] = arr[i];
        }

        return copy;
    }

    private static void printArr(int[] arr) {
        for(int i : arr) {
            System.out.print(i + " ");
        }
    }
}
