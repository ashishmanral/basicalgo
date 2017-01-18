package practice;

/**
 * Created by ashis on 12/26/2016.
 */
public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for(int i=0;i<(2*arr.length);++i) {
            System.out.println("Element " + (i+1) + " is at " + binarySearch(arr, i+1) + " position." );
        }
    }

    private static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int middle = (low + high) / 2;

        while(low <= high) {
            if(arr[middle] == target) {
                return middle;
            } else if (arr[middle] < target) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
            middle = (low + high) / 2;
        }

        return -1;
    }

}