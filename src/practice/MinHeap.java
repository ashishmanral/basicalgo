package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ashis on 1/14/2017.
 */
public class MinHeap {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();
        input.add(30);
        input.add(40);
        input.add(50);
        input.add(100);
        input.add(10);
        input.add(70);
        input.add(60);
        input.add(50);

        createMinHeap(input);
        printHeap(input);
        System.out.println("\nAdding " + 5 + " to min heap...");
        add(input, 5);
        printHeap(input);
        System.out.println("\nExtracting min : " + extractMin(input));
        printHeap(input);
        System.out.println("\nExtracting min : " + extractMin(input));
        printHeap(input);
    }

    private static void printHeap(List<Integer> arr) {
        System.out.println("Here is the min heap : ");
        for(int i : arr) {
            System.out.print(i + " ");
        }
    }
    private static void createMinHeap(List<Integer> arr) {
         for(int i = 1; i< arr.size(); ++i) {
             bubbleUp(arr, i);
         }
    }

    private static void bubbleUp(List<Integer> arr, int index) {
        int parentIndex = (index + 1) / 2 - 1;

        while(parentIndex >= 0 && (arr.get(index) < arr.get(parentIndex))) {
            arr.set(parentIndex, arr.get(parentIndex) + arr.get(index));
            arr.set(index, arr.get(parentIndex) - arr.get(index));
            arr.set(parentIndex, arr.get(parentIndex) - arr.get(index));
            index = parentIndex;
            parentIndex = (index + 1) / 2 - 1;
        }
    }

    private static void add(List<Integer> arr, int data) {
        arr.add(data);
        bubbleUp(arr, arr.size() - 1);
    }

    private static int extractMin(List<Integer> arr) {
        arr.set(0, arr.get(0) + arr.get(arr.size() - 1));
        arr.set(arr.size() - 1, arr.get(0) - arr.get(arr.size() - 1));
        arr.set(0, arr.get(0) - arr.get(arr.size() - 1));

        int min = arr.remove(arr.size() - 1);

        bubbleDown(arr);

        return min;
    }

    private static void bubbleDown(List<Integer> arr) {
        int currIndex = 0;
        int child1Index = 1;
        int child2Index = 2;

        int minChildIndex = arr.get(child1Index) < arr.get(child2Index) ? child1Index : child2Index;
        int minIndex = arr.get(currIndex) <= arr.get(minChildIndex) ? currIndex : minChildIndex;

        while(minIndex != currIndex && currIndex < arr.size()){
            arr.set(minIndex, arr.get(minIndex) + arr.get(currIndex));
            arr.set(currIndex, arr.get(minIndex) - arr.get(currIndex));
            arr.set(minIndex, arr.get(minIndex) - arr.get(currIndex));

            currIndex = minIndex;
            child1Index = (currIndex + 1) * 2 - 1;
            child2Index = (currIndex + 1) * 2;

            if(child1Index < arr.size() && child2Index < arr.size()) {
                minChildIndex = arr.get(child1Index) < arr.get(child2Index) ? child1Index : child2Index;
            } else if(child1Index < arr.size()) {
                minChildIndex = child1Index;
            } else if(child2Index < arr.size()) {
                minChildIndex = child2Index;
            } else {
                minChildIndex = currIndex;
            }

            minIndex = arr.get(currIndex) <= arr.get(minChildIndex) ? currIndex : minChildIndex;
        }
    }
}
