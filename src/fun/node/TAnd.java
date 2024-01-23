/* This file was generated by SableCC (http://www.sablecc.org/). */

package fun.node;

import fun.analysis.*;

@SuppressWarnings("nls")
public final class TAnd extends Token
{
    public TAnd()
    {
        super.setText("&&");
    }

    public TAnd(int line, int pos)
    {
        super.setText("&&");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TAnd(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTAnd(this);
    }

	@SuppressWarnings("unchecked")
	
    public <T,S> T apply(Switch<T,S> sw, S helper)
    {
        return ((GAnalysis<T,S>) sw).caseTAnd(this,helper);
    }
    
    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TAnd text.");
    }
}
