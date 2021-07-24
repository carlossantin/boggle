package io.santin.boggle.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GraphGenerator {

    /**
     * Creates a graph where each level is a character from a string
     *
     * @param value String to transform in a graph
     * @return A graph where each level is a character from a string
     */
    private Node createGraphFromString(final String value) {

        if (value.isBlank()) {
            return new Node(true, null, null);
        }

        return new Node(false, value.charAt(0), new ArrayList<>(List.of(this.createGraphFromString(value.substring(1)))));
    }

    /**
     * Creates only one list based on two.
     * If the root nodes are the same, they are merged in the same node, for example:
     * <pre>
     *     [
     *      Node(c -> [Node(a -> [Node (t)])])
     *     ]
     * </pre>
     *
     * Merge with:
     * <pre>
     *     [
     *      Node(c -> [Node(a -> [Node (n)])])
     *     ]
     * </pre>
     *
     * Will result in:
     * <pre>
     *     [
     *      Node(c -> [Node(a -> [Node (t), Node(n)])])
     *     ]
     * </pre>
     *
     * If we merge this result with:
     * <pre>
     *     [
     *      Node(d -> [Node(o -> [Node (g)])])
     *     ]
     * </pre>
     *
     * We will have:
     * <pre>
     *     [
     *      Node(c -> [Node(a -> [Node (t), Node(n)])]),
     *      Node(d -> [Node(o -> [Node (g)])])
     *     ]
     * </pre>
     *
     * @param nodes1    First list of nodes to merge
     * @param nodes2    Second list of nodes to merge
     * @return A merged list of the first and second list
     */
    private List<Node> mergeNodes(final List<Node> nodes1, final List<Node> nodes2) {

        if (nodes1.isEmpty()) return nodes2;
        if (nodes2.isEmpty()) return nodes1;

        List<Node> nodes2Clone = new ArrayList<>(nodes2);

        for (Node n1: nodes1) {
            for (Node n2: nodes2) {
                if (n1.equals(n2)) {
                    nodes2Clone.remove(n2);
                    n1.setNextNodes(mergeNodes(n1.getNextNodes(), n2.getNextNodes()));
                }
            }
        }

        return Stream.concat(nodes1.stream(), nodes2Clone.stream())
                .collect(Collectors.toList());
    }

    /**
     * Creates a graph where each node represents a character from string
     *
     * @param strings A list of strings to create the graph
     * @return A graph where each node represents a character from string
     */
    public List<Node> createGraphFromStrings(final List<String> strings) {
        final List<Node> allNodes = strings.stream().map(this::createGraphFromString)
                .collect(Collectors.toList());

        int i = 0;
        List<Node> mergedNodes = new ArrayList<>();
        while (i < allNodes.size()) {
            mergedNodes = this.mergeNodes(mergedNodes, List.of(allNodes.get(i)));
            i++;
        }

        return mergedNodes;
    }
}
