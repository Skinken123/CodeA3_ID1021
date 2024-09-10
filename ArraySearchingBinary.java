import java.util.Random;

public class ArraySearchingBinary {
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

    public static boolean binary_search(int[] array, int key) {
        int first = 0;
        int last = array.length-1;
        while (first <= last) {
            // jump to the middle
            int index = (first + last) / 2;
            if (array[index] == key) {
                return true;
            }
            if (array[index] < key) { // && index < last
                // what is the first possible page?
                first = index + 1;
                continue;
            }
            if (array[index] > key) { //&& index > first
                // what is the last possible page?
                last = index - 1;
                continue;
            }
        }
        return false;
    }

    public static void benchmark(){
        int[] sizes = {100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600}; //array som ska accessas, 12800, 25600, 51200
        int k = 100000; // så många gånger benchmark ska köras

        for(int n : sizes) {
            int[]keys = createUnsortedArray(k,n);
            int[]so = createSortedArray(n);
            
            long n0SB = System.nanoTime();
            for (int i = 0; i < k; i++) {
                binary_search(so, keys[i]);
            }
            long n1SB = System.nanoTime();
            long avgSB = (n1SB-n0SB)/k;

            System.out.println("n: " + n + " binary avg: " + avgSB + " ns");
        }
    }

    public static void benchmarkWarmUp(){
        int[] sizes = {10000000}; //array som ska accessas, 12800, 25600, 51200
        int k = 100000; // så många gånger benchmark ska köras

        for(int n : sizes) {
            int[]keys = createUnsortedArray(k,n);
            int[]so = createSortedArray(n);
            
            binary_search(sizes, k);
        }
    }
}
