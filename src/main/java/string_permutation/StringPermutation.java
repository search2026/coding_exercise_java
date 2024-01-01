package string_permutation;

import java.util.*;

import org.apache.commons.lang3.StringUtils;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class StringPermutation {
    /*
        Output Permutation of each character inside a String
        For example, permutate "abc" to "abc", "acb", "bac", "bca", "cab", "cba"
     */
    public class Solution {
        private void doPermutate(List<String> res, StringBuilder sb, String str, boolean[] visited) {
            if (sb.length() == str.length()) {
                res.add(new String(sb));
                return;
            }

            for (int i = 0; i < str.length(); i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    sb.append(str.charAt(i));
                    doPermutate(res, sb, str, visited);
                    sb.setLength(sb.length() - 1);
                    visited[i] = false;
                }
            }
        }

        public List<String> permutateString(String str) {
            if (StringUtils.isBlank(str)) {
                throw new IllegalArgumentException();
            }

            int n = str.length();
            List<String> res = new ArrayList<>();
            if (n == 1) {
                res.add(str);
                return res;
            }
            boolean visited[] = new boolean[n];
            doPermutate(res, new StringBuilder(), str, visited);
            return res;
        }
    }

    /*
        Output Permutation of each character inside a String. String maybe contains duplicates
        For example, permutate "abc" to "abc", "acb", "bac", "bca", "cab", "cba"
     */
    public class Solution_2 {
        private void doPermutate(List<String> res, StringBuilder sb, String str, boolean[] visited) {
            if (sb.length() == str.length()) {
                res.add(new String(sb));
                return;
            }

            for (int i = 0; i < str.length(); i++) {
                if ((i > 0) && visited[i-1] && str.charAt(i) == str.charAt(i-1) )
                    continue;
                if (!visited[i]) {
                    visited[i] = true;
                    sb.append(str.charAt(i));
                    doPermutate(res, sb, str, visited);
                    sb.setLength(sb.length() - 1);
                    visited[i] = false;
                }
            }
        }

        public List<String> permutateString(String str) {
            if (StringUtils.isBlank(str)) {
                throw new IllegalArgumentException();
            }

            int n = str.length();
            List<String> res = new ArrayList<>();
            if (n == 1) {
                res.add(str);
                return res;
            }
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            System.out.println(sorted);
            boolean[] visited = new boolean[n];
            doPermutate(res, new StringBuilder(), sorted, visited);
            return res;
        }
    }

    @Nested
    class UnitTest {
        @Test
        public void test1() {
            Solution sol = new StringPermutation().new Solution();
            List<String> res = sol.permutateString("abc");
            assertEquals(6, res.size());
            assertEquals("abc", res.get(0));
            assertEquals("acb", res.get(1));
            assertEquals("bac", res.get(2));
            assertEquals("bca", res.get(3));
            assertEquals("cab", res.get(4));
            assertEquals("cba", res.get(5));
        }

        @Test
        public void test2() {
            Solution_2 sol = new StringPermutation().new Solution_2();
            List<String> res = sol.permutateString("aca");
            System.out.println(res);
            assertEquals(3, res.size());
            assertTrue(res.contains("aac"));
            assertTrue(res.contains("caa"));
            assertTrue(res.contains("aca"));
        }
    }
}

