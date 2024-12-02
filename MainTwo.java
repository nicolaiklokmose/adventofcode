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
            boolean isSafe = true; // Flag to check if the report is safe

            try {
                int prevLevel = Integer.parseInt(levels[0]);
                Boolean isIncreasing = null;

                for (int i = 1; i < levels.length; i++) {
                    int currentLevel = Integer.parseInt(levels[i]);
                    int diff = Math.abs(currentLevel - prevLevel);

                    // Check if the difference is between 1 and 3
                    if (diff < 1 || diff > 3) {
                        isSafe = false;
                        break;
                    }

                    // Check if the sequence is increasing or decreasing
                    if (isIncreasing == null) {
                        if (currentLevel > prevLevel) {
                            isIncreasing = true;
                        } else if (currentLevel < prevLevel) {
                            isIncreasing = false;
                        }
                    } else if (isIncreasing && currentLevel < prevLevel) {
                        isSafe = false;
                        break;
                    } else if (!isIncreasing && currentLevel > prevLevel) {
                        isSafe = false;
                        break;
                    }

                    prevLevel = currentLevel;
                }
                
                // If no issues were found, the report is safe
                if (isSafe) {
                    safeReportsCount++;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }

        sc.close();

        // Output the count of safe reports
        System.out.println("safe reports: " + safeReportsCount);
    }
}