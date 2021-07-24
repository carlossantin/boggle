package io.santin.boggle.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GraphProcessorTest {

    @Test
    public void testContains() {
        final List<String> dictionary = List.of("cat", "car", "cart", "mouse", "mother", "dog", "doodle");
        final List<Node> graph = GraphGenerator.createGraphFromStrings(dictionary);

        dictionary.forEach(word -> Assertions.assertTrue(GraphProcessor.contains(graph, word)));

        Assertions.assertFalse(GraphProcessor.contains(graph, "camel"));
        Assertions.assertFalse(GraphProcessor.contains(graph, "cars"));
    }

    @Test
    public void testIsNextLeafNode() {
        final List<String> dictionary = List.of("cat", "car", "cart", "mouse", "mother", "dog", "doodle");
        final List<Node> graph = GraphGenerator.createGraphFromStrings(dictionary);

        dictionary.forEach(word -> {
            int lastIndex = word.length() - 1;
            Assertions.assertTrue(
                    GraphProcessor.isNextLeafNode(
                            word.charAt(lastIndex),
                            GraphProcessor.getNodesAtCharacterLevel(graph, lastIndex)
                    ));
        });

        Assertions.assertFalse(
                GraphProcessor.isNextLeafNode(
                        'a',
                        GraphProcessor.getNodesAtCharacterLevel(graph, 1)
                ));

    }
}
