package visitors;

import fun.node.AAndOp;
import fun.node.AAppTerm;
import fun.node.ABinopTerm;
import fun.node.ACfalseConst;
import fun.node.ACnumConst;
import fun.node.AConstTerm;
import fun.node.ACtrueConst;
import fun.node.AFnTerm;
import fun.node.AFunTerm;
import fun.node.AGtOp;
import fun.node.AIdentTerm;
import fun.node.AIfTerm;
import fun.node.ALetTerm;
import fun.node.ALsOp;
import fun.node.AMinusOp;
import fun.node.AOrOp;
import fun.node.APlusOp;
import fun.node.ATimesOp;
import fun.node.EOF;
import fun.node.Node;
import fun.node.Start;


public class PrettyVisitor extends fun.analysis.GAnalysisAdapter<String,Integer> {


	public String getString(fun.node.Start ast) {
		return ast.apply(this,0);
	}
	
	private String getIndent (int i) {
		StringBuilder s = new StringBuilder("  ");
		while (i>0) {
			s.append("  ");
			--i;
		}
		return s.toString();
	}
	

	@Override
	public String caseAAndOp(AAndOp node, Integer helper) {
		return "&&";
	}

	@Override
	public String caseAAppTerm(AAppTerm node, Integer helper) {
		return "(" + node.getFun().apply(this,helper) + "  " 
		
			+ node.getArg().apply(this,helper) + ")";
	}

	@Override
	public String caseABinopTerm(ABinopTerm node, Integer helper) {
		return  "(" + node.getLeft().apply(this,helper) + ")" +
			node.getOp().apply(this,helper) + "(" + node.getRight().apply(this,helper) + ")";
	}

	@Override
	public String caseACfalseConst(ACfalseConst node, Integer helper) {
		return "false";
	}

	@Override
	public String caseACnumConst(ACnumConst node, Integer helper) {
		return node.getInteger().getText();
	}

	@Override
	public String caseAConstTerm(AConstTerm node, Integer helper) {
		return node.getConst().apply(this,helper);
	}

	@Override
	public String caseACtrueConst(ACtrueConst node, Integer helper) {
		return "true";
	}

	@Override
	public String caseAFnTerm(AFnTerm node, Integer helper) {
		return "fn " + node.getId().getText() + " => " + node.getTerm().apply(this,helper);
	} 

	@Override
	public String caseAFunTerm(AFunTerm node, Integer helper) {
		return "fun " + node.getName().getText() + " " + node.getParam().getText() + " => " + node.getTerm().apply(this,helper);
	}

	@Override
	public String caseAGtOp(AGtOp node, Integer helper) {
		return ">";
	}

	@Override
	public String caseAIdentTerm(AIdentTerm node, Integer helper) {
		return node.getId().getText();
	}

	@Override
	public String caseAIfTerm(AIfTerm node, Integer helper) {
		return "if " + node.getTest().apply(this,helper) 
			+"\n"+ getIndent(helper) + "then " + node.getTruebranch().apply(this, helper+1)
			+"\n"+ getIndent(helper) + "else " + node.getFalsebranch().apply(this,helper+1);

	}

	@Override
	public String caseALetTerm(ALetTerm node, Integer helper) {
		return "let " + node.getId().getText() + " = "
		 	+ node.getVal().apply(this, helper) + " in\n" + getIndent(helper) + node.getBody().apply(this,helper+1);
	}

	@Override
	public String caseALsOp(ALsOp node, Integer helper) {
		return "<";
	}

	@Override
	public String caseAMinusOp(AMinusOp node, Integer helper) {
		return "-";
	}

	@Override
	public String caseAOrOp(AOrOp node, Integer helper) {
		return "||";
	}

	@Override
	public String caseAPlusOp(APlusOp node, Integer helper) {
		return "+";
	}

	@Override
	public String caseATimesOp(ATimesOp node, Integer helper) {
		return "*";
	}

	@Override
	public String caseEOF(EOF node, Integer helper) {
		return "";
	}

	@Override
	public String caseStart(Start node, Integer helper) {
		return node.getPTerm().apply(this,helper);
	}

	@Override
	public String defaultCase(Node node, Integer helper) {
		throw new RuntimeException("not implemented yet");
	}
	
}
