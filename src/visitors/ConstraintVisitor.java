package visitors;

import fun.analysis.GAnalysisAdapter;
import fun.node.*;
import types.Cache;
import types.Environment;
import types.Term;
import types.Terms;
import types.constraint.ConditionalConstraint;
import types.constraint.Constraint;
import types.constraint.NodeConstraint;
import types.constraint.TermConstraint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstraintVisitor extends GAnalysisAdapter<List<Constraint>, List<Constraint>> {
    private final Map<Node, Integer> terms = new HashMap<>();

    public List<Constraint> getConstraints(fun.node.Start ast, Map<Node, Integer> terms) {
        this.terms.putAll(terms);
        return ast.apply(this, new ArrayList<>());
    }

    @Override //[con]
    public List<Constraint> caseAConstTerm(AConstTerm node, List<Constraint> helper) {
        return new ArrayList<>();
    }

    @Override //[var]
    public List<Constraint> caseAIdentTerm(AIdentTerm node, List<Constraint> helper) {
        Environment var = new Environment(node.getId().getText().trim());
        Constraint constraint = new NodeConstraint(var, new Cache(terms.get(node)));
        ArrayList<Constraint> constraints = new ArrayList<>();
        constraints.add(constraint);
        return constraints;
    }

    @Override //[fn]
    public List<Constraint> caseAFnTerm(AFnTerm node, List<Constraint> helper) {
        PrettyVisitor prettyVisitor = new PrettyVisitor();
        List<Constraint> constraints = new ArrayList<>();
        List<Constraint> c2 = node.getTerm().apply(this, helper);
        Term term = new Term(prettyVisitor.caseAFnTerm(node, 0));
        Constraint constraint = new TermConstraint(new Terms(term), new Cache(terms.get(node)));
        constraints.add(constraint);
        constraints.addAll(c2);
        return constraints;
    }

    @Override //[fun]
    public List<Constraint> caseAFunTerm(AFunTerm node, List<Constraint> helper) {
        PrettyVisitor prettyVisitor = new PrettyVisitor();
        List<Constraint> constraints = new ArrayList<>();
        List<Constraint> c2 = node.getTerm().apply(this, helper);
        Term term = new Term(prettyVisitor.caseAFunTerm(node, 0));
        Constraint c1 = new TermConstraint(new Terms(term), new Cache(terms.get(node)));
        Constraint c3 = new TermConstraint(new Terms(term), new Environment(node.getName().getText().trim()));
        constraints.add(c1);
        constraints.addAll(c2);
        constraints.add(c3);
        return constraints;
    }

    @Override //[app]
    public List<Constraint> caseAAppTerm(AAppTerm node, List<Constraint> helper) {
        List<Constraint> constraints = new ArrayList<>(node.getFun().apply(this, helper));
        int term1Label = terms.get(node.getFun());
        constraints.addAll(node.getArg().apply(this, helper));
        int term2Label = terms.get(node.getArg());
        int appLabel = terms.get(node);

        PrettyVisitor prettyVisitor = new PrettyVisitor();

        for (var fnTerm : terms.keySet().stream().filter(n -> n instanceof AFnTerm).map(n -> (AFnTerm) n).toList()) {
            String term = fnTerm.apply(prettyVisitor, 0);
            ConditionalConstraint cc_fn_rx = new ConditionalConstraint(
                    new Terms(new Term(term)),
                    new Cache(term1Label),
                    new Cache(term2Label),
                    new Environment(fnTerm.getId().getText().trim()));
            constraints.add(cc_fn_rx);

            ConditionalConstraint cc_fn_c = new ConditionalConstraint(
                    new Terms(new Term(term)),
                    new Cache(term1Label),
                    new Cache(terms.get(fnTerm.getTerm())),
                    new Cache(appLabel));
            constraints.add(cc_fn_c);
        }

        for (var funTerm : terms.keySet().stream().filter(n -> n instanceof AFunTerm).map(n -> (AFunTerm) n).toList()) {
            String term = funTerm.apply(prettyVisitor, 0);
            ConditionalConstraint cc_fn_rx = new ConditionalConstraint(
                    new Terms(new Term(term)),
                    new Cache(term1Label),
                    new Cache(term2Label),
                    new Environment(funTerm.getParam().getText().trim()));
            constraints.add(cc_fn_rx);

            ConditionalConstraint cc_fn_c = new ConditionalConstraint(
                    new Terms(new Term(term)),
                    new Cache(term1Label),
                    new Cache(terms.get(funTerm.getTerm())),
                    new Cache(appLabel));
            constraints.add(cc_fn_c);
        }
        return constraints;
    }

    @Override //[if]
    public List<Constraint> caseAIfTerm(AIfTerm node, List<Constraint> helper) {
        List<Constraint> constraints = node.getTest().apply(this, helper);
        constraints.addAll(node.getTruebranch().apply(this, helper));
        int trueLabel = terms.get(node.getTruebranch());
        constraints.addAll(node.getFalsebranch().apply(this, helper));
        int falseLabel = terms.get(node.getFalsebranch());
        int testLabel = terms.get(node.getTest());

        Constraint c1 = new NodeConstraint(new Cache(trueLabel), new Cache(testLabel));
        Constraint c2 = new NodeConstraint(new Cache(falseLabel), new Cache(testLabel));
        constraints.add(c1);
        constraints.add(c2);

        return constraints;
    }

    @Override //[let]
    public List<Constraint> caseALetTerm(ALetTerm node, List<Constraint> helper) {
        List<Constraint> constraints = node.getVal().apply(this, helper);
        int valLabel = terms.get(node.getVal());
        constraints.addAll(node.getBody().apply(this, helper));
        int bodyLabel = terms.get(node.getBody());
        int letLabel = terms.get(node);
        Constraint c1 = new NodeConstraint(new Cache(valLabel), new Environment(node.getId().getText().trim()));
        Constraint c2 = new NodeConstraint(new Cache(bodyLabel), new Cache(letLabel));
        constraints.add(c1);
        constraints.add(c2);
        return constraints;
    }

    @Override //[op]
    public List<Constraint> caseABinopTerm(ABinopTerm node, List<Constraint> helper) {
        List<Constraint> constraints = new ArrayList<>();
        constraints.addAll(node.getLeft().apply(this, helper));
        constraints.addAll(node.getRight().apply(this, helper));
        return constraints;
    }

    @Override
    public List<Constraint> caseEOF(EOF node, List<Constraint> helper) {
        return new ArrayList<>();
    }

    @Override
    public List<Constraint> caseStart(Start node, List<Constraint> helper) {
        return node.getPTerm().apply(this, helper);
    }

}
