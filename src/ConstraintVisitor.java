import fun.analysis.GAnalysis;
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
import java.util.List;

public class ConstraintVisitor extends GAnalysisAdapter<List<Constraint>, List<Constraint>> {
    private int label = 0;

    public List<Constraint> getConstraints(fun.node.Start ast) {
        return ast.apply(this, new ArrayList<>());
    }

    @Override //[con]
    public List<Constraint> caseACnumConst(ACnumConst node, List<Constraint> helper) {
        return new ArrayList<>();
    }

    @Override //[var]
    public List<Constraint> caseAIdentTerm(AIdentTerm node, List<Constraint> helper) {
        Environment var = new Environment(node.getId().getText().trim());
        Constraint constraint = new NodeConstraint(var, new Cache(++this.label));
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
        Constraint constraint = new TermConstraint(new Terms(term), new Cache(++this.label));
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
        Constraint c1 = new TermConstraint(new Terms(term), new Cache(++this.label));
        Constraint c3 = new TermConstraint(new Terms(term), new Environment(node.getParam().getText().trim()));
        constraints.add(c1);
        constraints.addAll(c2);
        constraints.add(c3);
        return constraints;
    }


    @Override //[app]
    public List<Constraint> caseAAppTerm(AAppTerm node, List<Constraint> helper) {
        List<Constraint> constraints = new ArrayList<>(node.getFun().apply(this, helper));
        int term1Label = this.label;
        constraints.addAll(node.getArg().apply(this, helper));
        int term2Label = this.label;
        int appLabel = ++this.label;

        PrettyVisitor prettyVisitor = new PrettyVisitor();
        String term1 = node.getFun().apply(prettyVisitor, 0);
        String term2 = node.getArg().apply(prettyVisitor, 0);

        if (node.getFun() instanceof AFnTerm || node.getFun() instanceof AFunTerm) {
            PTerm fun = node.getFun();
            String var = "";
            if (fun instanceof AFnTerm) {
                var = ((AFnTerm) fun).getId().getText().trim();
            } else {
                var = ((AFunTerm) fun).getParam().getText().trim();
            }

            ConditionalConstraint cc_fn_rx = new ConditionalConstraint(
                    new Terms(new Term(term1)),
                    new Cache(term1Label),
                    new Cache(term2Label),
                    new Environment(var));
            constraints.add(cc_fn_rx);

            ConditionalConstraint cc_fn_c = new ConditionalConstraint(
                    new Terms(new Term(term1)),
                    new Cache(term1Label),
                    new Cache(term1Label - 1),
                    new Cache(appLabel));
            constraints.add(cc_fn_c);
        }

        if (node.getArg() instanceof AFnTerm || node.getArg() instanceof AFunTerm) {
            PTerm fun = node.getArg();
            String var = "";
            if (fun instanceof AFnTerm) {
                var = ((AFnTerm) fun).getId().getText().trim();
            } else {
                var = ((AFunTerm) fun).getParam().getText().trim();
            }
            ConditionalConstraint cc_fn_rx = new ConditionalConstraint(
                    new Terms(new Term(term2)),
                    new Cache(term1Label),
                    new Cache(term2Label),
                    new Environment(var));
            constraints.add(cc_fn_rx);

            ConditionalConstraint cc_fn_c = new ConditionalConstraint(
                    new Terms(new Term(term2)),
                    new Cache(term1Label),
                    new Cache(term2Label - 1),
                    new Cache(appLabel));
            constraints.add(cc_fn_c);
        }


        return constraints;
    }

    @Override //[if]
    public List<Constraint> caseAIfTerm(AIfTerm node, List<Constraint> helper) {
        List<Constraint> constraints = new ArrayList<>();
        constraints.addAll(node.getTest().apply(this, helper));
        constraints.addAll(node.getTruebranch().apply(this, helper));
        int trueLabel = this.label;
        constraints.addAll(node.getFalsebranch().apply(this, helper));
        int falseLabel = this.label;
        int testLabel = ++this.label;

        Constraint c1 = new NodeConstraint(new Cache(trueLabel), new Cache(testLabel));
        Constraint c2 = new NodeConstraint(new Cache(falseLabel), new Cache(testLabel));
        constraints.add(c1);
        constraints.add(c2);

        return constraints;
    }

    @Override //[let]
    public List<Constraint> caseALetTerm(ALetTerm node, List<Constraint> helper) {
        List<Constraint> constraints = new ArrayList<>();
        constraints.addAll(node.getVal().apply(this, helper));
        int valLabel = this.label;
        constraints.addAll(node.getBody().apply(this, helper));
        int bodyLabel = this.label;
        int letLabel = ++this.label;
        Constraint c1 = new NodeConstraint(new Cache(valLabel), new Environment(node.getId().getText().trim()));
        Constraint c2 = new NodeConstraint(new Cache(bodyLabel), new Cache(letLabel));
        constraints.add(c1);
        constraints.add(c2);
        return constraints;
    }

    @Override //[op]
    public List<Constraint> caseABinopTerm (ABinopTerm node, List<Constraint> helper) {
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
