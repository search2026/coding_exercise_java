package sort_objects;
import java.util.*;

import all_validate_routes.AllValidateRoutes;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class SortObjects {

    /*
    A list of products, 包含name, popularity, price，要求按照popularity + price排序。
     */
    public class Product {
        public String name;
        public Double popularity;
        public Integer price;

        Product(String name, Double popularity, Integer price) {
            this.name = name;
            this.popularity = popularity;
            this.price = price;
        }

    }

    class SortbyPopularityPrice implements Comparator<Product> {

        // Method
        // Sorting in ascending order of name
        public int compare(Product a, Product b)
        {
            int popularityCompare = a.popularity.compareTo(b.popularity);
            if (popularityCompare != 0) {
                return popularityCompare;
            }
            return a.price.compareTo(b.price);
        }
    }

    @Nested
    class UnitTest {
        @Test
        public void test1() {
            List<SortObjects.Product> prodList = new ArrayList<>();
            prodList.add(new SortObjects().new Product("tv1", 0.9, 100));
            prodList.add(new SortObjects().new Product("tv2", 0.2, 200));
            prodList.add(new SortObjects().new Product("tv3", 1.0, 300));
            prodList.add(new SortObjects().new Product("tv4", 0.5, 400));
            prodList.add(new SortObjects().new Product("tv5", 1.0, 500));
            prodList.add(new SortObjects().new Product("tv6", 0.3, 600));
            prodList.add(new SortObjects().new Product("tv7", 0.4, 700));
            prodList.add(new SortObjects().new Product("tv8", 0.5, 800));
            prodList.add(new SortObjects().new Product("tv9", 0.4, 900));
            prodList.add(new SortObjects().new Product("tv10", 0.5, 1000));
            prodList.sort(new SortbyPopularityPrice());
            assertEquals("tv2", prodList.get(0).name);
            assertEquals("tv5", prodList.get(9).name);
        }
    }
}
