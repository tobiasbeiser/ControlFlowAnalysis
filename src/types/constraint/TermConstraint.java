package types.constraint;

import types.Node;
import types.Terms;

import java.util.Set;

public class TermConstraint implements Constraint {
    private final Terms terms;
    private final Node p;

    public TermConstraint(Terms terms, Node p) {
        this.terms = terms;
        this.p = p;
    }

    public Terms getTerms() {
        return terms;
    }

    public Node getP() {
        return p;
    }

    @Override
    public Set<Node> getNodes() {
        return Set.of(p);
    }

    @Override
    public String toString() {
        return terms.toString() + " ⊆ " + p.toString();
    }
}
