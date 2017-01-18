package practice;

/**
 * Created by ashis on 12/24/2016.
 */
public class LinearSearch {

    public static void main(String[] args) {
        int[] arr = new int[10];
        for(int i = 0; i < 10 ; ++i) {
            arr[i] = i + 1;
        }

        int random = ((int)Math.random() * arr.length) + 1;

        System.out.println("Element " + random + " is at " + linearSearch(arr, random) + " position.");
    }

    public static int linearSearch(int[] arr, int target) {
        for(int i = 0; i < arr.length; ++i) {
            if(target == arr[i]) {
                return i;
            }
        }
        return -1;
    }
}
