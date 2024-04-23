package bandwith;

//Bandwidth Distribution
//Description
//Total bandwidth is total_bandwidth units and there are n API endpoints. The ith endpoint needs a bandwidth of bandwidth[i] to be functional, and the expected number of requests for the endpoint is request[i]. Given total_bandwidth, bandwidth, and request, find the maximum total number of requests that can be served by optimally allocating bandwidth to different endpoints.
//
//
//
//Example
//
//        Suppose n = 5, total_bandwidth = 500, bandwidth = [200, 100, 350, 50, 100], and request = [270, 142, 450, 124, 189].
//
//
//
//It is optimal to allocate bandwidth to the last three APIs which sum to 350 + 50 + 100 = 500 and will serve a total of 450 + 124 + 189 = 763 requests.
//
//
//
//        Function Description
//
//Complete the function getMaxRequests in the editor below.
//
//
//
//getMaxRequests has the following parameters:
//
//int bandwidth[n]:  the bandwidth by the endpoints
//
//int request[n]:  the requests that the APIs is asked to serve
//
//int total_bandwidth: the total bandwidth available
//
//
//
//Returns
//
//long int: the maximum number of requests that can be served
//
//
//
//        Constraints
//
//1 ≤ n ≤ 102
//        1 ≤ total_bandwidth ≤ 105
//        1 ≤ bandwidth[i] ≤ 105
//        1 ≤ request[i] ≤ 109
//
//
//Input Format For Custom Testing
//Sample Case 0
//Sample Input For Custom Testing
//
//STDIN       FUNCTION
//-----       --------
//        5       →   bandwidth[] size n = 5
//        100     →   bandwidth = [100, 500, 80, 25, 400]
//        500
//        80
//        25
//        400
//        5       →   request[] size n = 5
//        100     →   request = [100, 1000, 120, 110, 100]
//        1000
//        120
//        110
//        100
//        200     →   total_bandwidth = 200
//Sample Output
//
//230
//Explanation
//
//It is optimal to allocate bandwidth to the third and fourth endpoints that will consume a total of 80 + 25 = 105 units and serve 120 + 110 = 230 requests. No other endpoints can be active now.
//
//Sample Case 1


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

//Total bandwidth is total_bandwidth units and there are n API endpoints. The ith endpoint needs a bandwidth of bandwidth[i] to be functional, and the expected number of requests for the endpoint is request[i]. Given total_bandwidth, bandwidth, and request, find the maximum total number of requests that can be served by optimally allocating bandwidth to different endpoints.
//
//
//
//Example
//
//        Suppose n = 5, total_bandwidth = 500, bandwidth = [200, 100, 350, 50, 100], and request = [270, 142, 450, 124, 189].
//
//
//
//It is optimal to allocate bandwidth to the last three APIs which sum to 350 + 50 + 100 = 500 and will serve a total of 450 + 124 + 189 = 763 requests.
//
//
//
//        Function Description
//
//Complete the function getMaxRequests in the editor below.
//
//
//
//getMaxRequests has the following parameters:
//
//int bandwidth[n]:  the bandwidth by the endpoints
//
//int request[n]:  the requests that the APIs is asked to serve
//
//int total_bandwidth: the total bandwidth available
//
//
//
//Returns
//
//long int: the maximum number of requests that can be served
//
//
//
//        Constraints
//
//1 ≤ n ≤ 102
//        1 ≤ total_bandwidth ≤ 105
//        1 ≤ bandwidth[i] ≤ 105
//        1 ≤ request[i] ≤ 109


public class Bandwidth {

    public class Solution {
        // Write your code here
        //     * Complete the 'getMaxRequests' function below.     *
        // * The function is expected to return a LONG_INTEGER.
        // * The function accepts following parameters:
        // *  1. INTEGER_ARRAY bandwidth
        // *  2. INTEGER_ARRAY requestimport java.io.*;class Result {
        // *  3. INTEGER total_bandwidth

        public long getMaxRequests(List<Integer> bandwidth, List<Integer> request, int total_bandwidth) {
            // Write your code here
            Long[] res = new Long[]{0L};
            dfs(bandwidth, request, total_bandwidth, 0, 0L, 0L, res);
            return res[0];
        }

        private void dfs(List<Integer> bandwidth, List<Integer> request, int total_bandwidth, Integer start,
                         Long curRequests, Long currBand, Long[] maxRequest) {
            for (int i = start; i < bandwidth.size(); i++) {
                currBand += bandwidth.get(i);
                System.out.println("level: " + start + " currBand: " + currBand);
                if (currBand > total_bandwidth) {
                    currBand -= bandwidth.get(i);
                } else {
                    curRequests += request.get(i);
                    maxRequest[0] = Math.max(maxRequest[0], curRequests);
                    System.out.println("curRequests: " + curRequests + " maxRequest: " + maxRequest[0]);
                }

                dfs(bandwidth, request, total_bandwidth, i + 1, curRequests, currBand, maxRequest);

                if (currBand <= total_bandwidth) {
                    curRequests -= request.get(i);
                }
                currBand -= bandwidth.get(i);
            }
        }

    }

    public class Solution2 {
        public long getMaxRequests(List<Integer> bandwidth, List<Integer> request, int total_bandwidth) {
            int n = bandwidth.size();
            long[] dp = new long[total_bandwidth + 1];

            for (int i = 0; i < n; i++) {
                for (int j = total_bandwidth; j >= bandwidth.get(i); j--) {
                    dp[j] = Math.max(dp[j], dp[j - bandwidth.get(i)] + request.get(i));
                }
            }

            return dp[total_bandwidth];
        }
    }

    @Nested
    class UnitTest {
        @Test
        public void test1() {
            Bandwidth.Solution sol = new Bandwidth().new Solution();

            List<Integer> bandwidth = new ArrayList<>(Arrays.asList(200, 100, 350, 50, 100));
            List<Integer> request = new ArrayList<>(Arrays.asList(270, 142, 450, 124, 189));
            int totalBandwidth = 500;

            assertEquals(763, sol.getMaxRequests(bandwidth, request, totalBandwidth));

            bandwidth = new ArrayList<>(Arrays.asList(100, 500, 80, 25, 400));
            request = new ArrayList<>(Arrays.asList(100, 1000, 120, 110, 100));
            totalBandwidth = 200;

            assertEquals(230, sol.getMaxRequests(bandwidth, request, totalBandwidth));

            bandwidth = new ArrayList<>(Arrays.asList(100, 200, 500));
            request = new ArrayList<>(Arrays.asList(5, 5, 5));
            totalBandwidth = 1000;

            assertEquals(15, sol.getMaxRequests(bandwidth, request, totalBandwidth));
        }

        @Test
        public void test2() {
            Bandwidth.Solution2 sol = new Bandwidth().new Solution2();

            List<Integer> bandwidth = new ArrayList<>(Arrays.asList(200, 100, 350, 50, 100));
            List<Integer> request = new ArrayList<>(Arrays.asList(270, 142, 450, 124, 189));
            int totalBandwidth = 500;

            assertEquals(763, sol.getMaxRequests(bandwidth, request, totalBandwidth));

            bandwidth = new ArrayList<>(Arrays.asList(100, 500, 80, 25, 400));
            request = new ArrayList<>(Arrays.asList(100, 1000, 120, 110, 100));
            totalBandwidth = 200;

            assertEquals(230, sol.getMaxRequests(bandwidth, request, totalBandwidth));

            bandwidth = new ArrayList<>(Arrays.asList(100, 200, 500));
            request = new ArrayList<>(Arrays.asList(5, 5, 5));
            totalBandwidth = 1000;

            assertEquals(15, sol.getMaxRequests(bandwidth, request, totalBandwidth));
        }
    }
}
