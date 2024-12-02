import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainTwo {
    public static void main(String[] args) {
        int safeReportsCount = 0;
        Scanner sc = new Scanner(System.in);

        // Input reading
        while (sc.hasNextLine()) {
            String in = sc.nextLine().trim();
            if (in.isEmpty()) {
                break; // Stop reading on empty input
            }

            String[] levels = in.split("\\s+");
            int[] report = new int[levels.length];
            try {
                for (int i = 0; i < levels.length; i++) {
                    report[i] = Integer.parseInt(levels[i]);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
                continue;
            }

            // Check if the report is safe without removing a level
            if (isSafe(report)) {
                safeReportsCount++;
                continue;
            }

            // Try removing each level and check if the report becomes safe
            boolean foundSafe = false;
            for (int i = 0; i < report.length; i++) {
                int[] modifiedReport = removeLevel(report, i);
                if (isSafe(modifiedReport)) {
                    foundSafe = true;
                    break;
                }
            }

            if (foundSafe) {
                safeReportsCount++;
            }
        }

        sc.close();

        // Output the count of safe reports
        System.out.println("safe reports: " + safeReportsCount);
    }

    /**
     * Checks if a report is safe according to the rules.
     */
    private static boolean isSafe(int[] report) {
        if (report.length < 2) {
            return true; // Always safe
        }

        boolean isIncreasing = report[1] > report[0];
        for (int i = 1; i < report.length; i++) {
            int diff = report[i] - report[i - 1];

            // Check if adjacent levels differ by at least 1 and at most 3
            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return false;
            }

            // Check if levels are consistently increasing or decreasing
            if ((isIncreasing && diff < 0) || (!isIncreasing && diff > 0)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Removes the level at the specified index and returns a new array.
     */
    private static int[] removeLevel(int[] report, int indexToRemove) {
        int[] modifiedReport = new int[report.length - 1];
        for (int i = 0, j = 0; i < report.length; i++) {
            if (i != indexToRemove) {
                modifiedReport[j++] = report[i];
            }
        }
        return modifiedReport;
    }
}