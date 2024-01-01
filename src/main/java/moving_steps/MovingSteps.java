package moving_steps;

import java.util.*;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MovingSteps {
    /*
        Find how many steps can a King (in Chess game) moves (8 directions, vertically, horizontally, or diagonally)
        Input: dims, a tuple (width, height) of the dimensions of the board
               start, a tuple (x, y) of the king's starting coordinate
               target, a tuple (x, y) of the king's destination
               moves, how many moves that a king can move
        Depth First Search
     */
    public class Solution {
        private int[][] dirs = new int[][]{
                {0, -1},
                {0, 1},
                {1, 0},
                {-1, 0},
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1}
        };

        private int search(int width, int depth, int cX, int cY, int tX, int tY, int k, int cSteps) {
            if (cX < 0 || cX >= width || cY <0 || cY >=depth)
                return 0;
            if (cSteps >= k) {
                if (cX == tX && cY == tY)
                    return 1;
                return 0;
            }

            int steps = 0;
            for (int[] dir : dirs) {
                int x = cX+dir[0];
                int y = cY+dir[1];
                steps += search(width, depth, x, y, tX, tY, k, cSteps + 1);
            }
            return steps;
        }

        public int movingSteps(int width, int depth, int sX, int sY, int tX, int tY, int k) {
            return search(width, depth, sX, sY, tX, tY, k, 0);
        }

    }

    /*
        Find how many steps can a King (in Chess game) moves (8 directions, vertically, horizontally, or diagonally)
        Input: dims, a tuple (width, height) of the dimensions of the board
               start, a tuple (x, y) of the king's starting coordinate
               target, a tuple (x, y) of the king's destination
               moves, how many moves that a king can move
        Depth First Search with Memory
     */
    public class Solution_2 {
        private int[][] dirs = new int[][]{
                {0, -1},
                {0, 1},
                {1, 0},
                {-1, 0},
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1}
        };

        private int search(int width, int depth, int cX, int cY, int tX, int tY, int k, int cSteps,
                           Map<String, Integer> map) {
            if (cX < 0 || cX >= width || cY <0 || cY >=depth)
                return 0;
            if (cSteps >= k) {
                if (cX == tX && cY == tY)
                    return 1;
                return 0;
            }

            int steps = 0;
            for (int[] dir : dirs) {
                int x = cX+dir[0];
                int y = cY+dir[1];
                if (x >= 0 && y < width || x >=0 || y < depth) {
                    String key = x + " " + y + " " + cSteps;
                    if (map.containsKey(key)) {
                        steps += map.get(key);
                    } else {
                        int step = search(width, depth, x, y, tX, tY, k, cSteps + 1, map);
                        map.put(key, step);
                        steps += step;
                    }
                }
            }
            return steps;
        }

        public int movingSteps(int width, int depth, int sX, int sY, int tX, int tY, int k) {
            return search(width, depth, sX, sY, tX, tY, k, 0, new HashMap<String, Integer>());
        }

    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new MovingSteps().new Solution();
            assertEquals(1, sol.movingSteps(3, 3, 0, 0, 2, 2, 2));
            assertEquals(6, sol.movingSteps(3, 3, 0, 0, 2, 2, 3));
            assertEquals(12, sol.movingSteps(4, 4, 3, 2, 3, 2, 3));
            assertEquals(84, sol.movingSteps(4, 4, 3, 2, 1, 1, 4));
            assertEquals(122529792, sol.movingSteps(4, 6, 0, 2, 3, 4, 12));
        }

        @Test
        public void test2() {
            Solution_2 sol = new MovingSteps().new Solution_2();
            assertEquals(1, sol.movingSteps(3, 3, 0, 0, 2, 2, 2));
            assertEquals(6, sol.movingSteps(3, 3, 0, 0, 2, 2, 3));
            assertEquals(12, sol.movingSteps(4, 4, 3, 2, 3, 2, 3));
            assertEquals(84, sol.movingSteps(4, 4, 3, 2, 1, 1, 4));
            assertEquals(122529792, sol.movingSteps(4, 6, 0, 2, 3, 4, 12));
        }
    }
}

