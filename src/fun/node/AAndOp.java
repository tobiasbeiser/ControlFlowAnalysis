/* This file was generated by SableCC (http://www.sablecc.org/). */

package fun.node;

import fun.analysis.*;

@SuppressWarnings("nls")
public final class AAndOp extends POp
{

    public AAndOp()
    {
        // Constructor
    }

    @Override
    public Object clone()
    {
        return new AAndOp();
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAndOp(this);
    }
    @SuppressWarnings("unchecked")

    public <T,S> T apply(Switch<T,S> sw, S helper)
    {
        return ((GAnalysis<T,S>) sw).caseAAndOp(this,helper);
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
