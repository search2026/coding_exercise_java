package validate_route;

import java.util.*;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ValidateRoute {
    /*
        validate if a route is good.
        One router may contents multiple pickup and delivery pair event. The delivery event can not happened
        before the same pair pickup event. Given a route, write a function to check if a router is validate.
        For examples:
        {p1,d1} true
        {p1,d1,p2,d2} true
        {p1,p2,d1, d2} true
        {p1, d2} false // no p2 and d1
        {p1, d2, d1} false // no p
        {p1, d1, d1} false // duplicate d1
     */
    public class Solution {
        public boolean isvalidRoute(List<String> routeList) {
            if (routeList == null || routeList.size() == 0)
                return false;

            Set<String> setP = new HashSet<>();
            Set<String> setD = new HashSet<>();
            for (String r : routeList) {
                if (r.startsWith("p")) {
                    if (setP.contains(r))
                        return false;
                    setP.add(r);
                } else if (r.startsWith("d")) {
                    if (setD.contains(r))
                        return false;
                    String pikcup = r.replace("d", "p");
                    if (!setP.contains(pikcup))
                        return false;
                    setD.add(r);
                } else {
                    return false;
                }
            }

            return setP.size() == setD.size();
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new ValidateRoute().new Solution();
            List<String> test = new ArrayList<>();
            test.add("p1");
            test.add("d1");
            assertTrue(sol.isvalidRoute(test));
            test.clear();
            test.add("p1");
            test.add("p2");
            test.add("d1");
            test.add("d2");
            assertTrue(sol.isvalidRoute(test));
            test.clear();
            test.add("p1");
            test.add("d1");
            test.add("p2");
            test.add("d2");
            assertTrue(sol.isvalidRoute(test));
            test.clear();
            test.add("p1");
            test.add("d2");
            test.add("p2");
            test.add("d1");
            assertFalse(sol.isvalidRoute(test));
            test.clear();
            test.add("p1");
            test.add("p2");
            test.add("d1");
            assertFalse(sol.isvalidRoute(test));
            test.clear();
            test.add("p1");
            test.add("d1");
            test.add("d1");
            assertFalse(sol.isvalidRoute(test));
        }
    }
}

