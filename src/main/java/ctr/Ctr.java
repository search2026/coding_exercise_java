package ctr;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ctr {

    public class ClickData {
        public String docId;
        public int clicks;

        ClickData(String docId, int clicks) {
            this.docId = docId;
            this.clicks = clicks;
        }
    }

    public class ExperimentData {
        public String id;
        public List<ClickData> clickData;

        ExperimentData(String id, List<ClickData> clickData) {
            this.id = id;
            this.clickData = new ArrayList<>(clickData);
        }

        public List<Double> getCtr() {
            return new ArrayList<>();
        }
    }

    @Nested
    class UnitTest {
        @Test
        public void test1() {

            //LongestConsecutiveCharacter.Solution sol = new LongestConsecutiveCharacter().new Solution();

            assertEquals(1, 1);
        }
    }
}
