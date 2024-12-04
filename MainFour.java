import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MainFour {
    public static void main(String[] args) {
        Counter xmasCount = new Counter(); // Use a mutable object

        try {
            File file = new File("input4.txt");
            Scanner sc = new Scanner(file);

            // Use an ArrayList to dynamically store the rows
            ArrayList<char[]> lines = new ArrayList<>();

            // Read each line and convert it to a char array
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                lines.add(line.toCharArray());
            }
            sc.close();

            // Convert the ArrayList to a 2D array
            char[][] grid = lines.toArray(new char[0][0]);

            // Print the 2D array to verify
            for (char[] row : grid) {
                for (char c : row) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }

            // Part one: Find "XMAS" in all directions
            String target = "XMAS";
            int rows = grid.length;
            int cols = grid[0].length;

            // Directions to check
            int[][] directions = {
                {0, 1},  // Right
                {0, -1}, // Left
                {1, 0},  // Down
                {-1, 0}, // Up
                {1, 1},  // Bottom-right diagonal
                {-1, -1}, // Top-left diagonal
                {1, -1},  // Bottom-left diagonal
                {-1, 1}  // Top-right diagonal
            };

            traverseGrid(grid, rows, cols, directions, target, xmasCount);
            System.out.println("Total instances of XMAS found was: " + xmasCount.count);

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            System.exit(0);
        }
    }

    private static void traverseGrid(char[][] grid, int rows, int cols, int[][] directions, String target, Counter xmasCount) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int[] dir : directions) {
                    int rowDir = dir[0];
                    int colDir = dir[1];
                    if (checkDirection(grid, row, col, rowDir, colDir, target)) {
                        xmasCount.increment(); // Increment count using the mutable object
                        System.out.printf("Found XMAS starting at (%d, %d) in direction (%d, %d)%n",
                                row, col, rowDir, colDir);
                    }
                }
            }
        }
    }

    private static boolean checkDirection(char[][] grid, int startRow, int startCol, int rowDir, int colDir, String target) {
        int rows = grid.length;
        int cols = grid[0].length;
        int len = target.length();

        for (int i = 0; i < len; i++) {
            int newRow = startRow + i * rowDir;
            int newCol = startCol + i * colDir;

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                return false;
            }

            // Check character at i'th index
            if (grid[newRow][newCol] != target.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}


class Counter {
    public int count = 0; // Mutable field

    public void increment() {
        count++;
    }
}

