package types.constraint;

import types.Node;

import java.util.Set;

public class NodeConstraint implements Constraint{
    private final Node p1;
    private final Node p2;

    public NodeConstraint(Node p1, Node p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Node getP1() {
        return p1;
    }

    public Node getP2() {
        return p2;
    }

    @Override
    public Set<Node> getNodes() {
        return Set.of(p1, p2);
    }

    @Override
    public String toString() {
        return p1.toString() + " ⊆ " + p2.toString();
    }
}
