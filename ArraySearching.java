import java.util.Random;

public class ArraySearching {
    public static void main(String[] args) {
        benchmarkWarmUp();

        System.out.println("Warmup done" + "\n");

        benchmark();
    }

    public static boolean unsorted_search(int[] array, int key) {
        for (int index = 0; index < array.length ; index++) {
            if (array[index] == key) {
                return true;
            }
        }
        return false;
    }

    public static int[] createUnsortedArray(int size, int intRange) {
        Random random = new Random();

        int[] array = new int[size];
        for (int index = 0; index < size; index++) {
            array[index] = random.nextInt(intRange*2);
        }
        return array;
    }

    public static boolean sorted_search(int[] array, int key) {
        for (int index = 0; index < array.length ; index++) {
            if (array[index] > key) {
                return false;
            }
            if (array[index] == key) {
                return true;
            }
        }
        return false;
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

    public static void benchmark(){
        int[] sizes = {100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600}; //array som ska accessas, 12800, 25600, 51200
        int k = 100000; // så många gånger benchmark ska köras

        for(int n : sizes) {
            int[]keys = createUnsortedArray(k,n);
            int[]unSo = createUnsortedArray(n,n);
            int[]so = createSortedArray(n);
            
            long n0uS = System.nanoTime();
            for (int i = 0; i < k; i++) {
                unsorted_search(unSo, keys[i]);
            }
            long n1uS = System.nanoTime();
            long avgUS = (n1uS-n0uS)/k;

            long n0S = System.nanoTime();
            for (int i = 0; i < k; i++) {
                sorted_search(so, keys[i]);
            }
            long n1S = System.nanoTime();
            long avgS = (n1S-n0S)/k;

            System.out.println("n: " + n + " unsorted avg: " + avgUS + " ns" + " sorted avg: " + avgS + " ns");
        }
    }

    public static void benchmarkWarmUp(){
        int[] sizes = {6400, 12800}; //array som ska accessas, 12800, 25600, 51200
        int k = 100000; // så många gånger benchmark ska köras

        for(int n : sizes) {
            int[]keys = createUnsortedArray(k,n);
            int[]unSo = createUnsortedArray(n,n);
            int[]so = createSortedArray(n);
            
            for (int i = 0; i < k; i++) {
                unsorted_search(unSo, keys[i]);
            }

            for (int i = 0; i < k; i++) {
                sorted_search(so, keys[i]);
            }
        }
    }
}