package visitors;

import fun.node.*;

import java.util.HashMap;
import java.util.Map;

public class PrettyVisitorWithLabels extends PrettyVisitor {
    private int label = 0;
    private final Map<Node, Integer> terms = new HashMap<>();

    private String getLabel(Node node) {
        String exponent = Integer.toString(++this.label);
        terms.put(node, this.label);
        String[] digits = {"⁰", "¹", "²", "³", "⁴", "⁵", "⁶", "⁷", "⁸", "⁹"};

        // Convert the integer to a superscript string
        StringBuilder result = new StringBuilder();
        for (char digit : exponent.toCharArray()) {
            result.append(digits[Character.getNumericValue(digit)]);
        }
        return result.toString();
    }

    public Map<Node, Integer> getTerms() {
        return this.terms;
    }

    @Override
    public String caseAAppTerm(AAppTerm node, Integer helper) {
        return super.caseAAppTerm(node, helper) + getLabel(node);
    }

    @Override
    public String caseAFnTerm(AFnTerm node, Integer helper) {
        return "(" + super.caseAFnTerm(node, helper) + ")" + getLabel(node);
    }

    @Override
    public String caseAFunTerm(AFunTerm node, Integer helper) {
        return "(" + super.caseAFunTerm(node, helper) + ")" + getLabel(node);
    }

    @Override
    public String caseAIdentTerm(AIdentTerm node, Integer helper) {
        return super.caseAIdentTerm(node, helper) + getLabel(node);
    }

    @Override
    public String caseALetTerm(ALetTerm node, Integer helper) {
        return "(" + super.caseALetTerm(node, helper) + ")" + getLabel(node);
    }

    @Override
    public String caseAConstTerm(AConstTerm node, Integer helper) {
        return super.caseAConstTerm(node, helper) + getLabel(node);
    }

    @Override
    public String caseABinopTerm(ABinopTerm node, Integer helper) {
        return super.caseABinopTerm(node, helper) + getLabel(node);
    }
}
