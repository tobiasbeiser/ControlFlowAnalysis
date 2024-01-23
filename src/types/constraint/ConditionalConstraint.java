package types.constraint;


import types.Terms;
import types.Node;

import java.util.Set;

public class ConditionalConstraint implements Constraint {
    private Terms terms;
    private Node p;
    private Node p1;
    private Node p2;

    public ConditionalConstraint(Terms terms, Node p, Node p1, Node p2) {
        this.terms = terms;
        this.p = p;
        this.p1 = p1;
        this.p2 = p2;
    }

    public Terms getTerms() {
        return terms;
    }

    public Node getP() {
        return p;
    }

    public Node getP1() {
        return p1;
    }

    public Node getP2() {
        return p2;
    }

    @Override
    public Set<Node> getNodes() {
        return Set.of(p, p1, p2);
    }

    @Override
    public String toString() {
        return terms.toString() + " ⊆ " + p.toString() + " => " + p1.toString() + " ⊆ " + p2.toString();
    }
}
