import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int res = 0;
        Scanner sc = new Scanner(System.in);
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        // Input reading
        while (sc.hasNextLine()) {
            String in = sc.nextLine().trim();
            if (in.isEmpty()) {
                break; // Stop reading on empty input
            }

            String[] parts = in.split("\\s+");
            if (parts.length == 2) {
                try {
                    listA.add(Integer.parseInt(parts[0]));
                    listB.add(Integer.parseInt(parts[1]));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input");
                }
            } else {
                System.out.println("Invalid input format");
            }
        }

        sc.close();

        // Sort both lists
        iterativeQuickSort(listA);
        iterativeQuickSort(listB);

        // Compute total distance between list parallels
        for (int i = 0; i < listA.size(); i++) {
            res += Math.abs(listA.get(i) - listB.get(i));
        }

        // Output result of total distance
        System.out.println(res);
    }

    /**
     * Sorts a list using iterative quicksort
     *
     * @param list the list to sort
     */
    private static void iterativeQuickSort(List<Integer> list) {
        int low = 0;
        int high = list.size() - 1;

        if (high <= low) {
            return; // Already sorted or empty
        }

        int[] stack = new int[high - low + 1];
        int top = -1;

        stack[++top] = low;
        stack[++top] = high;

        while (top >= 0) {
            high = stack[top--];
            low = stack[top--];

            int pivot = list.get(high);
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (list.get(j) <= pivot) {
                    i++;
                    swap(list, i, j);
                }
            }

            swap(list, i + 1, high);

            int partitionIndex = i + 1;

            if (partitionIndex - 1 > low) {
                stack[++top] = low;
                stack[++top] = partitionIndex - 1;
            }

            if (partitionIndex + 1 < high) {
                stack[++top] = partitionIndex + 1;
                stack[++top] = high;
            }
        }
    }

    /**
     * Swaps two elements in a list
     *
     * @param list the list
     * @param i    index of the first element
     * @param j    index of the second element
     */
    private static void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
