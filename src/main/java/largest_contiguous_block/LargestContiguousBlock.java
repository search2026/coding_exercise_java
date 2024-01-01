package largest_contiguous_block;

import java.util.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LargestContiguousBlock {
    /*
        Largest Contiguous Block
        given a 2D matrix, find the size of the largest contiguous block (
        horizontally/vertically connected) of numbers with the same value)
        For example,
        {{1,2,3},
        {4,1,6},
        {4,5,1}}
        largest block is 2
        {
        {0,0,0,0,0},
        {0,0,0,0,0},
        {0,0,1,0,0},
        {0,0,0,0,0},
        {0,0,0,0,0}}
        largest block is 24
     */
    public class Solution {
        int[][] dirs = {
                {0, 1},
                {0, -1},
                {1, 0},
                {-1, 0}
        };

        private int doFloodFill(int[][] matrix, int i, int j, int n) {
            if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] != n)
                return 0;
            // assume all value are 0 or positive values
            matrix[i][j] = -1;
            int count = 1;
            for (int[] dir : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                count += doFloodFill(matrix, x, y, n);
            }
            return count;
        }

        public int findLargestBlock(int[][] matrix) {
            if (matrix == null || matrix.length == 0 ||
                    matrix[0] == null || matrix[0].length == 0)
                throw new IllegalArgumentException();

            int res = 1;
            for (int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] >= 0) {
                        int local = doFloodFill(matrix, i, j, matrix[i][j]);
                        res = Math.max(local, res);
                    }
                }

            return res;
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new LargestContiguousBlock().new Solution();
            int[][] matrix1 = new int[][]{
                    {1, 2, 3},
                    {4, 1, 6},
                    {4, 5, 1}};
            assertEquals(2, sol.findLargestBlock(matrix1));
            int[][] matrix2 = new int[][]{
                    {1, 1, 1, 2, 4},
                    {5, 1, 5, 3, 1},
                    {3, 4, 2, 1, 1}};
            assertEquals(4, sol.findLargestBlock(matrix2));
            int[][] matrix3 = new int[][]{
                    {3, 3, 3, 3, 3, 1},
                    {3, 4, 4, 4, 3, 4},
                    {2, 4, 3, 3, 3, 4},
                    {2, 4, 4, 4, 4, 4}};
            assertEquals(11, sol.findLargestBlock(matrix3));
            int[][] matrix4 = new int[][]{
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0}};
            assertEquals(24, sol.findLargestBlock(matrix4));
        }
    }

}

