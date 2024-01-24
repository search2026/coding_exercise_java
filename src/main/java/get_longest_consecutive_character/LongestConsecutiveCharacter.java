package get_longest_consecutive_character;

/*
    implement a method to find longest consecutive characters

    some examples:

    'this is a test sentence' => [t,h,i,s,i,s,a,t,e,s,t,s,e,n,t,e,n,c,e]
    (Longest: 1, return every character since they all occur exactly once consecutively)

    'thiis iss a teest seentennce' => [i,s,e,e,n]
    (Longest: 2, 'th[ii]s i[ss] a t[ee]st s[ee]nte[nn]ce')

    'thiiis iss a teeest seentennncce' => [i,e,n]
    (Longest: 3, 'th[iii]s iss a t[eee]st seente[nnn]cce')

    'thiiiis iss a teeest seeentennncccce' => [i, c]
    (Longest: 4, 'th[iiii]s iss a teeest seeentennn[cccc]e')

    'aaabbaaa' => [a, a]
     */

import all_validate_routes.AllValidateRoutes;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestConsecutiveCharacter {

    public class Solution {
        public List<Character> getLongestConsecutiveCharacter(String s) {
            List<Character> res = new ArrayList<>();
            if (null == s || s.isEmpty()) {
                return res;
            }

            char prev = s.charAt(0);
            res.add(s.charAt(0));
            int freqSoFar = 1; // remember the highest freq
            int currFreq = 1; // current freq

            for (int i = 1; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == ' ') {
                    continue;
                }
                if (c == prev) {
                    currFreq++;
                    if (currFreq >= freqSoFar) {
                        if (currFreq > freqSoFar) {
                            res.clear();
                            freqSoFar = currFreq;
                        }
                        res.add(c);
                    }
                } else {
                    if (freqSoFar == 1) {
                        res.add(c);
                    }
                    currFreq = 1;
                    prev = c;
                }
            }

            return res;
        }
    }

    @Nested
    class UnitTest {
        @Test
        public void test1() {
            LongestConsecutiveCharacter.Solution sol = new LongestConsecutiveCharacter().new Solution();

            assertArrayEquals(new Character[]{'t', 'h', 'i', 's', 'i', 's', 'a', 't', 'e', 's', 't', 's', 'e', 'n', 't', 'e', 'n', 'c', 'e'},
                    sol.getLongestConsecutiveCharacter("this is a test sentence").toArray(Character[]::new));

            assertArrayEquals(new Character[]{'i', 's', 'e', 'e', 'n'},
                    sol.getLongestConsecutiveCharacter("thiis iss a teest seentennce").toArray(Character[]::new));

            assertArrayEquals(new Character[]{'i', 'e', 'n'},
                    sol.getLongestConsecutiveCharacter("thiiis iss a teeest seentennncce").toArray(Character[]::new));

            assertArrayEquals(new Character[]{'i', 'c'},
                    sol.getLongestConsecutiveCharacter("thiiiis iss a teeest seeentennncccce").toArray(Character[]::new));

            assertArrayEquals(new Character[]{'a', 'a'},
                    sol.getLongestConsecutiveCharacter("aaabbaaa").toArray(Character[]::new));
        }
    }

}
