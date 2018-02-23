package intersect_intervals;

import java.util.*;

import common.Interval;

import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class IntersectIntervals {
    /*
        Given two list of intervals, find the list of intersected/common intervals
        For example, with these two list of intervals
        interval_list1 = [(9, 12), (14, 17), (21, 23)]
        interval_list2 = [(8, 10), (11, 22)]
        The common/intersected list is:
        [(9, 10), (11, 12), (14, 17), (21, 22)]
        https://www.alexandruene.com/intersecting-lists-intervals/
     */
    public class Solution {
        public List<Interval> intersectInterval(List<Interval> interval1, List<Interval> interval2) {
            List<Interval> res = new ArrayList<>();
            if (interval1 == null || interval1.size() == 0 ||
                    interval2 == null || interval2.size() == 0)
                return res;

            int p1 = 0, p2 = 0;
            while (p1 < interval1.size() && p2 < interval2.size()) {
                int start1 = interval1.get(p1).start;
                int end1 = interval1.get(p1).end;
                int start2 = interval2.get(p2).start;
                int end2 = interval2.get(p2).end;
                if (start2 < end1  && start1 < end2 ) {
                    res.add(new Interval(Math.max(start1, start2), Math.min(end1, end2)));
                }
                if (end1 < end2)
                    p1++;
                else if (end1 > end2)
                    p2++;
                else {
                    p1++;
                    p2++;
                }
            }

            return res;
        }

    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new IntersectIntervals().new Solution();
            List<Interval> interval1 = new ArrayList<>();
            interval1.add(new Interval(9, 12));
            interval1.add(new Interval(14, 17));
            interval1.add(new Interval(21, 23));
            List<Interval> interval2 = new ArrayList<>();
            interval2.add(new Interval(1, 2));
            interval2.add(new Interval(8, 10));
            interval2.add(new Interval(11, 22));
            List<Interval> res = sol.intersectInterval(interval1, interval2);
            assertEquals(4, res.size());
        }
    }
}

