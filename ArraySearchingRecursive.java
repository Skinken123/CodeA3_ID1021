public class ArraySearchingRecursive {
    //create main
    public static void main(String[] args) {
        
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
    
}
