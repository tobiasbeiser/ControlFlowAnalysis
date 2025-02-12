import types.Cache;
import types.Environment;
import types.constraint.ConditionalConstraint;
import types.Node;
import types.Terms;
import types.constraint.Constraint;
import types.constraint.NodeConstraint;
import types.constraint.TermConstraint;

import java.util.*;

public class ZeroCFA {

    private final List<Node> worklist = new LinkedList<>();
    private final Map<Node, Terms> data = new HashMap<>();
    private final Map<Node, List<Constraint>> edges = new HashMap<>();

    public ZeroCFA() {
    }

    private Set<Node> extractNodes(List<Constraint> constraints) {
        Set<Node> nodes = new HashSet<>();
        for (Constraint constraint : constraints) {
            nodes.addAll(constraint.getNodes());
        }
        return nodes;
    }

    private void add(Node node, Terms terms) {
        if (!this.data.get(node).isSubset(terms)
        ) {
            this.data.get(node).union(terms);
            this.worklist.add(0, node);
        }
    }

    private String getData(Node node) {
        String result = this.data.get(node).toString();
        if (result.equals("{}")) {
            result = "∅";
        }
        return result;
    }

    public void worklist(List<Constraint> constraints) {
        Set<Node> nodes = extractNodes(constraints);


        // STEP 1 Initialization
        for (Node node : nodes) {
            this.data.put(node, new Terms());
            this.edges.put(node, new LinkedList<>());
        }

        // STEP 2 Building the graph
        for (Constraint constraint : constraints) {
            if (constraint instanceof ConditionalConstraint) {
                Node p = (Node) ((ConditionalConstraint) constraint).p();
                Node p1 = (Node) ((ConditionalConstraint) constraint).p1();
                this.edges.get(p1).add(0, constraint);
                this.edges.get(p).add(0, constraint);
            } else if (constraint instanceof NodeConstraint) {
                Node p1 = ((NodeConstraint) constraint).p1();
                this.edges.get(p1).add(constraint);
            } else if (constraint instanceof TermConstraint) {
                Node p = ((TermConstraint) constraint).p();
                Terms terms = ((TermConstraint) constraint).terms();
                add(p, terms);
            }
        }

        //STEP 3 Iteration
        while (worklist.size() > 0) {
            Node q = worklist.remove(0);
            for (Constraint constraint : this.edges.get(q)) {
                if (constraint instanceof NodeConstraint) {
                    Node p1 = ((NodeConstraint) constraint).p1();
                    Node p2 = ((NodeConstraint) constraint).p2();
                    add(p2, this.data.get(p1));
                }
                if (constraint instanceof ConditionalConstraint) {
                    Node p = ((ConditionalConstraint) constraint).p();
                    Node p1 = ((ConditionalConstraint) constraint).p1();
                    Node p2 = ((ConditionalConstraint) constraint).p2();
                    Terms terms = ((ConditionalConstraint) constraint).terms();
                    if (this.data.get(p).equals(terms)) {
                        add(p2, this.data.get(p1));
                    }
                }
            }
        }

        //STEP 4 Output
        for (Cache node : nodes.stream().filter(n -> n instanceof Cache).map(n -> (Cache) n).sorted((Comparator.comparingInt(Cache::label))).toList()) {
            System.out.println("C(" + node.label() + ") = " + getData(node));
        }
        for (Environment node : nodes.stream().filter(n -> n instanceof Environment).map(n -> (Environment) n).sorted((Comparator.comparing(Environment::variable))).toList()) {
            System.out.println("r(" + node.variable() + ") = " + getData(node));
        }
    }

}
