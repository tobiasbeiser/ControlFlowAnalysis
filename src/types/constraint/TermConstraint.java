package types.constraint;

import types.Node;
import types.Terms;

import java.util.Set;

public record TermConstraint(Terms terms, Node p) implements Constraint {

    @Override
    public Set<Node> getNodes() {
        return Set.of(p);
    }

    @Override
    public String toString() {
        return terms.toString() + " ⊆ " + p.toString();
    }
}
