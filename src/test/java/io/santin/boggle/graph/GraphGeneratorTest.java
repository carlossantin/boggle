package io.santin.boggle.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GraphGeneratorTest {

    @Test
    public void testCreateGraphFromStrings() {
        final List<String> dictionary = List.of("cat", "car", "cart", "mouse", "mother", "dog", "doodle");

        final List<Node> graph = GraphGenerator.createGraphFromStrings(dictionary);

        dictionary.forEach(word -> {

            for (int i = word.length(); i > 0; i--) {
                Assertions.assertTrue(GraphProcessor.contains(graph, word.substring(0, i)));
            }

            Assertions.assertTrue(GraphProcessor.isACompleteWord(graph, word));
        });
    }
}
