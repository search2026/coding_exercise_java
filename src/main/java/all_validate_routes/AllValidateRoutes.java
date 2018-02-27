package all_validate_routes;

import java.util.*;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AllValidateRoutes {
    /*
        list all validate routes.
        One router may contents multiple pickup and delivery pair event. The delivery event can not happened
        before the same pair pickup event. Given a validate route, write a function that output all validate
        route variation.
     */
    public class Solution {
        public void doPermutate(List<List<String>> res, List<String> curr, boolean[] visited,
                                String[] routes, int steps) {
            if (steps == routes.length) {
                res.add(new ArrayList<String>(curr));
                return;
            }

            for (int i=0; i<routes.length; i++) {
                if (!visited[i]) {
                    if (routes[i].startsWith("d")) {
                        String delivery = routes[i];
                        String pickup = delivery.replace("d", "p");
                        if (!curr.contains(pickup))  continue;
                    }
                    visited[i] = true;
                    curr.add(routes[i]);
                    doPermutate(res, curr, visited, routes, steps+1);
                    visited[i] = false;
                    curr.remove(curr.size()-1);
                }
            }
        }

        public List<List<String>> permuateRoute(String[] routes) {
            List<List<String>> res = new ArrayList<>();
            if (routes == null || routes.length == 0) return res;

            boolean[] visited = new boolean[routes.length];
            doPermutate(res, new ArrayList<>(), visited, routes, 0);
            return res;
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new AllValidateRoutes().new Solution();
            List<List<String>> res = sol.permuateRoute(new String[]{"p1", "d1"});
            assertEquals(1, res.size());
            assertEquals(2, res.get(0).size());
            res = sol.permuateRoute(new String[]{"p1", "d1", "p2", "d2"});
            assertEquals(6, res.size());
            for (List<String> route : res)
                assertEquals(4, route.size());
            res = sol.permuateRoute(new String[]{"p1", "d1", "p2", "d2", "p3", "d3"});
            assertEquals(90, res.size());
            for (List<String> route : res)
                assertEquals(6, route.size());
            res = sol.permuateRoute(new String[]{"p1", "d1", "p2", "d2", "p3", "d3", "p4", "d4"});
            assertEquals(2520, res.size());
            for (List<String> route : res)
                assertEquals(8, route.size());
        }
    }
}

