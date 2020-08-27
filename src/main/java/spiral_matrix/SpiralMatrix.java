package spiral_matrix;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpiralMatrix {
    /*
        Spiral Matrix
        print integers inside a matrix anti-clockwise. For example, given the following matrix:
        [
         [ 1, 2, 3 ],
         [ 4, 5, 6 ],
         [ 7, 8, 9 ]
        ]
        output [ 1, 4, 7, 8, 9, 6, 3, 2, 5]
     */
    public class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            if (matrix == null || matrix.length == 0 ||
                    matrix[0] == null || matrix[0].length == 0) {
                throw new IllegalArgumentException();
            }

            List<Integer> res = new ArrayList<>();
            int m = matrix.length, n = matrix[0].length;
            int rowStart=0, rowEnd=m-1;
            int colStart=0, colEnd=n-1;
            while (rowStart <= rowEnd && colStart<=colEnd) {
                for (int i = rowStart; i <= rowEnd; i++) {
                    res.add(matrix[i][colStart]);
                }
                 if (++colStart > colEnd) break;
                for(int j=colStart; j<=colEnd; j++) {
                    res.add(matrix[rowEnd][j]);
                }
                if (--rowEnd < rowStart) break;
                for (int i= rowEnd; i>=rowStart; i--) {
                    res.add(matrix[i][colEnd]);
                }
                if (--colEnd < colStart) break;
                for (int j=colEnd; j>=colStart; j--) {
                    res.add(matrix[rowStart][j]);
                }
                if (++rowStart > rowEnd) break;
            }

            return res;

        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new SpiralMatrix().new Solution();
            int[][] matrix = new int[][] {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9}
            };
            List<Integer> res = sol.spiralOrder(matrix);
            assertEquals(9, res.size());
            assertEquals(1, (int)res.get(0));
            assertEquals(4, (int)res.get(1));
            assertEquals(7, (int)res.get(2));
            assertEquals(8, (int)res.get(3));
            assertEquals(9, (int)res.get(4));
            assertEquals(6, (int)res.get(5));
            assertEquals(3, (int)res.get(6));
            assertEquals(2, (int)res.get(7));
            assertEquals(5, (int)res.get(8));
        }
    }
}

