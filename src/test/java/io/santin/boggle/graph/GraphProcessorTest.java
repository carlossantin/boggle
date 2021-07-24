package io.santin.boggle.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GraphProcessorTest {

    @Test
    public void testContains() {
        final List<String> dictionary = List.of("cat", "car", "cart", "mouse", "mother", "dog", "doodle");
        final List<Node> graph = GraphGenerator.createGraphFromStrings(dictionary);

        dictionary.forEach(word -> {
            for (int i = word.length(); i > 0; i--) {
                Assertions.assertTrue(GraphProcessor.contains(graph, word.substring(0, i)));
            }
        });

        Assertions.assertFalse(GraphProcessor.contains(graph, "camel"));
        Assertions.assertFalse(GraphProcessor.contains(graph, "cars"));
    }

    @Test
    public void testIsACompleteWord() {
        final List<String> dictionary = List.of("cat", "car", "cart", "mouse", "mother", "dog", "doodle");
        final List<Node> graph = GraphGenerator.createGraphFromStrings(dictionary);

        dictionary.forEach(word -> Assertions.assertTrue(GraphProcessor.isACompleteWord(graph, word)));

        Assertions.assertFalse(GraphProcessor.isACompleteWord(graph, "camel"));
        Assertions.assertFalse(GraphProcessor.isACompleteWord(graph, "cars"));
    }
}
