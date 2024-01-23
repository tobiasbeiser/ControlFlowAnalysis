/* This file was generated by SableCC (http://www.sablecc.org/). */

package fun.analysis;

import java.util.*;
import fun.node.*;

public class AnalysisAdapter implements Analysis
{
    private Hashtable<Node,Object> in;
    private Hashtable<Node,Object> out;

    public Object getIn(Node node)
    {
        if(this.in == null)
        {
            return null;
        }

        return this.in.get(node);
    }

    public void setIn(Node node, Object o)
    {
        if(this.in == null)
        {
            this.in = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.in.put(node, o);
        }
        else
        {
            this.in.remove(node);
        }
    }

    public Object getOut(Node node)
    {
        if(this.out == null)
        {
            return null;
        }

        return this.out.get(node);
    }

    public void setOut(Node node, Object o)
    {
        if(this.out == null)
        {
            this.out = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.out.put(node, o);
        }
        else
        {
            this.out.remove(node);
        }
    }

    public void caseStart(Start node)
    {
        defaultCase(node);
    }

    public void caseAConstTerm(AConstTerm node)
    {
        defaultCase(node);
    }

    public void caseAIdentTerm(AIdentTerm node)
    {
        defaultCase(node);
    }

    public void caseAFnTerm(AFnTerm node)
    {
        defaultCase(node);
    }

    public void caseAFunTerm(AFunTerm node)
    {
        defaultCase(node);
    }

    public void caseAAppTerm(AAppTerm node)
    {
        defaultCase(node);
    }

    public void caseAIfTerm(AIfTerm node)
    {
        defaultCase(node);
    }

    public void caseALetTerm(ALetTerm node)
    {
        defaultCase(node);
    }

    public void caseABinopTerm(ABinopTerm node)
    {
        defaultCase(node);
    }

    public void caseACnumConst(ACnumConst node)
    {
        defaultCase(node);
    }

    public void caseACtrueConst(ACtrueConst node)
    {
        defaultCase(node);
    }

    public void caseACfalseConst(ACfalseConst node)
    {
        defaultCase(node);
    }

    public void caseAPlusOp(APlusOp node)
    {
        defaultCase(node);
    }

    public void caseATimesOp(ATimesOp node)
    {
        defaultCase(node);
    }

    public void caseAMinusOp(AMinusOp node)
    {
        defaultCase(node);
    }

    public void caseAAndOp(AAndOp node)
    {
        defaultCase(node);
    }

    public void caseAOrOp(AOrOp node)
    {
        defaultCase(node);
    }

    public void caseALsOp(ALsOp node)
    {
        defaultCase(node);
    }

    public void caseAGtOp(AGtOp node)
    {
        defaultCase(node);
    }

    public void caseTWhiteSpace(TWhiteSpace node)
    {
        defaultCase(node);
    }

    public void caseTLparen(TLparen node)
    {
        defaultCase(node);
    }

    public void caseTRparen(TRparen node)
    {
        defaultCase(node);
    }

    public void caseTCtrue(TCtrue node)
    {
        defaultCase(node);
    }

    public void caseTCfalse(TCfalse node)
    {
        defaultCase(node);
    }

    public void caseTFn(TFn node)
    {
        defaultCase(node);
    }

    public void caseTFun(TFun node)
    {
        defaultCase(node);
    }

    public void caseTIf(TIf node)
    {
        defaultCase(node);
    }

    public void caseTThen(TThen node)
    {
        defaultCase(node);
    }

    public void caseTElse(TElse node)
    {
        defaultCase(node);
    }

    public void caseTLet(TLet node)
    {
        defaultCase(node);
    }

    public void caseTIn(TIn node)
    {
        defaultCase(node);
    }

    public void caseTImplies(TImplies node)
    {
        defaultCase(node);
    }

    public void caseTIs(TIs node)
    {
        defaultCase(node);
    }

    public void caseTPlus(TPlus node)
    {
        defaultCase(node);
    }

    public void caseTMinus(TMinus node)
    {
        defaultCase(node);
    }

    public void caseTTimes(TTimes node)
    {
        defaultCase(node);
    }

    public void caseTAnd(TAnd node)
    {
        defaultCase(node);
    }

    public void caseTOr(TOr node)
    {
        defaultCase(node);
    }

    public void caseTLs(TLs node)
    {
        defaultCase(node);
    }

    public void caseTGt(TGt node)
    {
        defaultCase(node);
    }

    public void caseTInteger(TInteger node)
    {
        defaultCase(node);
    }

    public void caseTId(TId node)
    {
        defaultCase(node);
    }

    public void caseEOF(EOF node)
    {
        defaultCase(node);
    }

    public void defaultCase(@SuppressWarnings("unused") Node node)
    {
        // do nothing
    }
}
