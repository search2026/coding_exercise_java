package cosine_similarity;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CosineSimilarity {

    // Function to calculate the dot product of two vectors
    private static double dotProduct(double[] vector1, double[] vector2) {
        return IntStream.range(0, vector1.length)
                        .mapToDouble(i -> vector1[i] * vector2[i])
                        .sum();
    }

    //     Function to calculate the magnitude of a vector
    private static double magnitude(double[] vector) {
        return Math.sqrt(Arrays.stream(vector)
                               .map(v -> v * v)
                               .sum());
    }

    // Function to calculate the cosine similarity between two vectors
    public static double cosineSimilarity(double[] vector1, double[] vector2) {
        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException("Vectors must have the same length");
        }

        double dotProduct = dotProduct(vector1, vector2);
        double magnitude1 = magnitude(vector1);
        double magnitude2 = magnitude(vector2);

        if (magnitude1 == 0 || magnitude2 == 0) {
            return 0.0; // Prevent division by zero
        }

        return dotProduct / (magnitude1 * magnitude2);
    }

    public static void main(String[] args) {
        // Example usage
        double[] vector1 = {1, 2, 3};
        double[] vector2 = {4, 5, 6};

        double similarity = cosineSimilarity(vector1, vector2);
        System.out.println("Cosine similarity: " + similarity);
    }
}
