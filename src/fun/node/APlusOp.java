/* This file was generated by SableCC (http://www.sablecc.org/). */

package fun.node;

import fun.analysis.*;

@SuppressWarnings("nls")
public final class APlusOp extends POp
{

    public APlusOp()
    {
        // Constructor
    }

    @Override
    public Object clone()
    {
        return new APlusOp();
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAPlusOp(this);
    }
    @SuppressWarnings("unchecked")

    public <T,S> T apply(Switch<T,S> sw, S helper)
    {
        return ((GAnalysis<T,S>) sw).caseAPlusOp(this,helper);
    }


    @Override
    public String toString()
    {
        return "";
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        throw new RuntimeException("Not a child.");
    }
}
