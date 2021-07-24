package io.santin.boggle.graph;

import java.util.List;
import java.util.Objects;

public class Node {

    private boolean isLeaf;
    private Character value;
    private List<Node> nextNodes;

    public Node(boolean isLeaf, Character value, List<Node> nextNodes) {
        this.isLeaf = isLeaf;
        this.value = value;
        this.nextNodes = nextNodes;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public Character getValue() {
        return value;
    }

    public void setValue(Character value) {
        this.value = value;
    }

    public List<Node> getNextNodes() {
        return nextNodes;
    }

    public void setNextNodes(List<Node> nextNodes) {
        this.nextNodes = nextNodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Node{" +
                "isLeaf=" + isLeaf +
                ", value=" + value +
                ", nextNodes=" + nextNodes +
                '}';
    }
}
