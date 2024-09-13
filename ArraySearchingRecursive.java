import java.util.Random;

public class ArraySearchingRecursive {
    //create main
    public static void main(String[] args) {
        benchmarkWarmUp();

        System.out.println("Warmup done" + "\n");

        benchmark();
    }

    private static int[] createSortedArray(int size) {
        Random rnd = new Random();

        int[] array = new int[size];
        int nxt = 0;
        for (int i = 0; i < size ; i++) {
            nxt += rnd.nextInt(3) + 1;
            array[i] = nxt;
        }
        return array;
    }

    public static int[] createUnsortedArray(int size, int intRange) {
        Random random = new Random();

        int[] array = new int[size];
        for (int index = 0; index < size; index++) {
            array[index] = random.nextInt(intRange*2);
        }
        return array;
    }

    private static boolean recursive(int[] arr, int key, int min, int max) {
        // Base case: calculate the middle index
        int mid = min + ((max - min) / 2);
    
        // If the key is found at the mid index
        if (arr[mid] == key) {
            return true;
        }
    
        // If the key is smaller, search the left half (narrow the search range)
        if ((arr[mid] > key) && (min < mid)) {
            return recursive(arr, key, min, mid - 1);
        }
    
        // If the key is larger, search the right half (narrow the search range)
        if ((arr[mid] < key) && (mid < max)) {
            return recursive(arr, key, mid + 1, max);
        }
    
        // If we reach this point, the key was not found
        return false;
    }
    

    public static void benchmark(){
        int[] sizes = {100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600}; //array som ska accessas, 12800, 25600, 51200
        int k = 100000; // så många gånger benchmark ska köras

        for(int n : sizes) {
            int[]keys = createUnsortedArray(k,n);
            int[]so = createSortedArray(n);
            
            long n0SBR = System.nanoTime();
            for (int i = 0; i < k; i++) {
                recursive(so, keys[i], 0, (so.length-1));
            }
            long n1SBR = System.nanoTime();
            long avgSBR = (n1SBR-n0SBR)/k;

            System.out.println("n: " + n + " binary avg: " + avgSBR + " ns");
        }
    }

    public static void benchmarkWarmUp(){
        int[] sizes = {10000000}; //array som ska accessas, 12800, 25600, 51200
        int k = 100000; // så många gånger benchmark ska köras

        for(int n : sizes) {
            int[]keys = createUnsortedArray(k,n);
            int[]so = createSortedArray(n);
            
            for (int i = 0; i < k; i++) {
                recursive(so, keys[i], 0, (so.length-1));
            }
        }
    }
}
