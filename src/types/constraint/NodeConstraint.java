package types.constraint;

import types.Node;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public record NodeConstraint(Node p1, Node p2) implements Constraint {

    @Override
    public Set<Node> getNodes() {
        return new HashSet<>(Arrays.asList(p1, p2));
    }

    @Override
    public String toString() {
        return p1.toString() + " ⊆ " + p2.toString();
    }
}
