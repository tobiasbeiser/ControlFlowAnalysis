package types.constraint;

import types.Node;
import types.Terms;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public record TermConstraint(Terms terms, Node p) implements Constraint {

    @Override
    public Set<Node> getNodes() {
        return new HashSet<>(Collections.singletonList(p));
    }

    @Override
    public String toString() {
        return terms.toString() + " ⊆ " + p.toString();
    }
}
