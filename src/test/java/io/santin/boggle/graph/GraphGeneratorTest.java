package io.santin.boggle.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GraphGeneratorTest {

    @Test
    public void testCreateGraphFromStrings() {
        final GraphGenerator graphGenerator = new GraphGenerator();

        final List<String> dictionary = List.of("cat", "car", "cart", "mouse", "mother", "dog", "doodle");

        final List<Node> graph = graphGenerator.createGraphFromStrings(dictionary);

        dictionary.forEach(word -> {

            for (int i = 0; i < word.length(); i++) {
                final Character c = word.charAt(i);

                final List<Node> currentLevel = this.getNodesAtCharacterLevel(graph, i);

                // Check if the character is in the correct level
                Assertions.assertTrue(currentLevel.contains(new Node(false, c, Collections.emptyList())));

                // If the character is the last one in the word, check if there is a leaf node after it
                if (i == word.length() - 1) {
                    Assertions.assertTrue(isNextLeafNode(c, currentLevel));
                }
            }
        });

    }

    private boolean isNextLeafNode(final Character c,
                                   final List<Node> currentLevel) {

        return currentLevel.stream()
                .filter(Objects::nonNull)
                .filter(it -> it.getValue() != null)
                .filter(it -> it.getValue().equals(c))
                .map(Node::getNextNodes)
                .flatMap(List::stream)
                .anyMatch(Node::isLeaf);
    }

    private List<Node> getNodesAtCharacterLevel(final List<Node> graph, int level) {

        List<Node> currentLevel = graph;

        for (int j = 0; j < level; j++) {
            currentLevel = currentLevel.stream()
                    .map(Node::getNextNodes)
                    .filter(Objects::nonNull)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
        }

        return currentLevel;
    }
}
