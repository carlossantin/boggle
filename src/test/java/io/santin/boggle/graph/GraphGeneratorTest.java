package io.santin.boggle.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class GraphGeneratorTest {

    @Test
    public void testCreateGraphFromStrings() {
        final List<String> dictionary = List.of("cat", "car", "cart", "mouse", "mother", "dog", "doodle");

        final List<Node> graph = GraphGenerator.createGraphFromStrings(dictionary);

        dictionary.forEach(word -> {

            for (int i = 0; i < word.length(); i++) {
                final Character c = word.charAt(i);

                final List<Node> currentLevel = GraphProcessor.getNodesAtCharacterLevel(graph, i);

                // Check if the character is in the correct level
                Assertions.assertTrue(currentLevel.contains(new Node(false, c, Collections.emptyList())));

                // If the character is the last one in the word, check if there is a leaf node after it
                if (i == word.length() - 1) {
                    Assertions.assertTrue(GraphProcessor.isNextLeafNode(c, currentLevel));
                }
            }
        });
    }
}
