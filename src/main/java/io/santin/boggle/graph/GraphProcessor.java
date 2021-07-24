package io.santin.boggle.graph;

import java.util.List;
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

        if (value.length() == 1) return true;

        final List<Node> nextNodes = getNextNodes(nodesWithValue);

        return contains(nextNodes, value.substring(1));
    }

    /**
     * Check if a word exists in graph
     *
     * @param graph Graph where each node is a word character
     * @param value String to check presence in graph
     * @return True if the word exists in graph, false otherwise
     */
    public static boolean isACompleteWord(final List<Node> graph,
                                          final String value) {

        final List<Node> nodesWithValue = getNodesWithValue(graph, value.charAt(0));

        if (nodesWithValue.isEmpty()) return false;

        if (value.length() == 1) return isThereALeafInNextNodes(nodesWithValue);

        final List<Node> nextNodes = getNextNodes(nodesWithValue);

        return isACompleteWord(nextNodes, value.substring(1));
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
