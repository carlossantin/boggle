package io.santin.boggle.graph;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GraphProcessor {

    /**
     * Check if a word exists in graph
     *
     * @param graph Graph where each node is a word character
     * @param value String to check presence in graph
     * @return True if the word exists in graph, false otherwise
     */
    public static boolean contains(final List<Node> graph,
                                   final String value) {

        boolean contains = true;

        for (int i = 0; i < value.length(); i++) {

            final Character c = value.charAt(i);
            final List<Node> currentLevel = getNodesAtCharacterLevel(graph, i);
            contains = contains && currentLevel.contains(new Node(false, c, Collections.emptyList()));
        }

        return contains;
    }

    /**
     * Get all nodes (characters) in a specific graph level
     *
     * @param graph Graph to check
     * @param level Level from graph to get all nodes
     * @return all nodes (characters) in a specific graph level
     */
    static List<Node> getNodesAtCharacterLevel(final List<Node> graph,
                                               final int level) {

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

    /**
     * Checks if the next level for a node (Character) is a leaf
     *
     * @param c             Character representing the node
     * @param currentLevel  Graph current level
     * @return True if the next level has a leaf node, false otherwise
     */
    public static boolean isNextLeafNode(final Character c,
                                         final List<Node> currentLevel) {

        return currentLevel.stream()
                .filter(Objects::nonNull)
                .filter(it -> it.getValue() != null)
                .filter(it -> it.getValue().equals(c))
                .map(Node::getNextNodes)
                .flatMap(List::stream)
                .anyMatch(Node::isLeaf);
    }

}
