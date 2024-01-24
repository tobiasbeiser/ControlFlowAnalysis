package types.constraint;


import types.Terms;
import types.Node;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public record ConditionalConstraint(Terms terms, Node p, Node p1, Node p2) implements Constraint {

    @Override
    public Set<Node> getNodes() {
        return new HashSet<>(Arrays.asList(p, p1, p2));
    }

    @Override
    public String toString() {
        return terms.toString() + " ⊆ " + p.toString() + " => " + p1.toString() + " ⊆ " + p2.toString();
    }
}
