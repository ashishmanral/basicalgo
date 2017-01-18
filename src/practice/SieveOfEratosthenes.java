package practice;

/**
 * Fancy name - this is used to print prime numbers upto a given max number
 *
 * Created by ashis on 1/9/2017.
 */
public class SieveOfEratosthenes {

    public static void main(String[] args) {
        printAllPrimeNumbers(200);
    }

    private static void printAllPrimeNumbers(int max) {
        int prime = 2;
        int sqrt = (int)Math.sqrt(max);

        boolean[] nonPrime = new boolean[max];

        // 1 is a non-prime number
        nonPrime[0] = true;

        while(prime <= sqrt) {
            //System.out.println(prime + " at start " + nonPrime[prime]);
            if(nonPrime[prime - 1]) {
                ++prime;
                continue;
            }

            for(int i = prime*prime; i <= max; ++i) {
                if(i % prime == 0) nonPrime[i - 1] = true;
            }

            ++prime;
        }

        // Print all prime numbers
        System.out.println("Prime numbers till " + max + " are : ");
        for(int i = 0; i < max; ++i) {
            if(!nonPrime[i]) {
                System.out.print((i+1) + " ");
            }
        }
    }
}
