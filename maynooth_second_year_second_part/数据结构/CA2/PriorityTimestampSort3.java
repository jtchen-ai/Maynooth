import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Class to represent an item with priority and timestamp
class Item {
    int priority;
    double timestamp;

    // Constructor
    Item(int priority, double timestamp) {
        this.priority = priority;
        this.timestamp = timestamp;
    }

    // For printing the item
    @Override
    public String toString() {
        return priority + "," + timestamp;
    }
}

public class PriorityTimestampSort3 {
    // Compare function adapted to return -1, 0, or 1 for sorting
    private static int compare(Item a, Item b) {
        if (a.priority > b.priority) return -1; // Higher priority comes first
        else if (a.priority < b.priority) return 1;
        else {
            // If priority is equal, compare timestamp ascending
            return Double.compare(a.timestamp, b.timestamp);
        }
    }

    // Merge function to combine two sorted subarrays
    private static void merge(Item[] arr, int left, int mid, int right) {
        // Calculate sizes of two subarrays to merge
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays for left and right subarrays
        Item[] leftArr = new Item[n1];
        Item[] rightArr = new Item[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        // Merge the temporary arrays back into arr[left..right]
        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (compare(leftArr[i], rightArr[j]) <= 0) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArr, if any
        while (i < n1) {
            arr[k++] = leftArr[i++];
        }

        // Copy remaining elements of rightArr, if any
        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }

    // MergeSort function to sort the array
    private static void mergeSort(Item[] arr, int left, int right) {
        if (left < right) {
            // Find the middle point
            int mid = left + (right - left) / 2;

            // Recursively sort the left and right halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    // Method to read items from numbers.csv and sort them
    public static List<Item> readAndSortCSV(String fileName) {
        List<Item> items = new ArrayList<>();

        // Read the file
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split each line into priority and timestamp
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int priority = Integer.parseInt(parts[0].trim());
                    double timestamp = Double.parseDouble(parts[1].trim());
                    items.add(new Item(priority, timestamp));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in CSV: " + e.getMessage());
        }

        // Convert List to array for mergeSort
        Item[] arr = items.toArray(new Item[0]);

        // Sort the array using mergeSort
        mergeSort(arr, 0, arr.length - 1);

        // Convert sorted array back to List
        List<Item> sortedItems = new ArrayList<>();
        for (Item item : arr) {
            sortedItems.add(item);
        }

        return sortedItems;
    }

    // Main method to run the program
    public static void main(String[] args) {
        String fileName = "D:\\学习编程\\javaWeb学习\\coding\\lab2\\src\\numbers.csv";

        // Read and sort items
        List<Item> sortedItems = readAndSortCSV(fileName);

        // Print results
        if (sortedItems.isEmpty()) {
            System.out.println("No items to sort.");
            return;
        }

        System.out.println("Sorted items (first 10):");
        for (int i = 0; i < Math.min(10, sortedItems.size()); i++) {
            System.out.println(sortedItems.get(i));
        }

        System.out.println("\nTotal items sorted: " + sortedItems.size());
    }
}