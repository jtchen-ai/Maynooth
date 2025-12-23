package lab1;

import java.util.Random;

public class MergeSort {
    static int comparisons = 0;

    public static void main(String[] args) {
        System.out.println("Using Merge Sort:");
        int[] sizes = {10, 100, 1000};
        for (int size : sizes) {
            int[] array = generateRandomArray(size);
            int[] originalArray = array.clone();
            comparisons = 0;
            mergeSort(array, 0, array.length - 1);

            if (size == 10) {
                printResult(size, originalArray, array);
            } else {
                System.out.print("\nn=" + size + "\n算法比较次数：" + comparisons);
            }
        }
    }

    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(1000);
        }
        return array;
    }

    private static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = array[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = array[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            comparisons++;
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    private static void printResult(int size, int[] originalArray, int[] sortedArray) {
        System.out.print("n=" + size);
        System.out.print("\n排序前的数组：");
        for (int num : originalArray) {
            System.out.print(num + " ");
        }
        System.out.print("\n排序后的数组：");
        for (int num : sortedArray) {
            System.out.print(num + " ");
        }
        System.out.print("\n算法比较次数：" + comparisons);
    }
}