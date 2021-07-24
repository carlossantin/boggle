package io.santin.boggle.graph;

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

        final List<Node> nodesWithValue = getNodesWithValue(graph, value.charAt(0));

        if (nodesWithValue.isEmpty()) return false;

        if (value.length() == 1) return isThereALeafInNextNodes(nodesWithValue);

        final List<Node> nextNodes = getNextNodes(nodesWithValue);

        return contains(nextNodes, value.substring(1));
    }

    /**
     * Gets the next nodes from current ones
     *
     * @param currentNodes Current nodes
     * @return the next nodes from current ones
     */
    private static List<Node> getNextNodes(List<Node> currentNodes) {
        return currentNodes.stream()
                .map(Node::getNextNodes)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    /**
     * Checks if in the next nodes of each current node there is a leaf node
     *
     * @param currentNodes  Current nodes
     * @return True if there is a leaf node in the next nodes, false otherwise
     */
    private static boolean isThereALeafInNextNodes(List<Node> currentNodes) {
        return currentNodes.stream()
                .map(Node::getNextNodes)
                .flatMap(List::stream)
                .anyMatch(Node::isLeaf);
    }

    /**
     * Get all nodes (characters) in a specific graph level
     *
     * @param graph Graph to check
     * @param level Level from graph to get all nodes
     * @return all nodes (characters) in a specific graph level
     */
    public static List<Node> getNodesAtCharacterLevel(final List<Node> graph,
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

    /**
     * Gets a list of nodes where the value is equal to a character
     *
     * @param graph Nodes to check
     * @param value Character to compare with node values
     * @return All nodes where the value is equal to a character
     */
    private static List<Node> getNodesWithValue(final List<Node> graph, final Character value) {
        return graph.stream()
                .filter(it -> it.getValue() != null)
                .filter(it -> it.getValue().equals(value))
                .collect(Collectors.toList());
    }

}
