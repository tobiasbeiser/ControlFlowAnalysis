/* This file was generated by SableCC (http://www.sablecc.org/). */

package fun.node;

import fun.analysis.*;

@SuppressWarnings("nls")
public final class TPlus extends Token
{
    public TPlus()
    {
        super.setText("+");
    }

    public TPlus(int line, int pos)
    {
        super.setText("+");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TPlus(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTPlus(this);
    }

	@SuppressWarnings("unchecked")
	
    public <T,S> T apply(Switch<T,S> sw, S helper)
    {
        return ((GAnalysis<T,S>) sw).caseTPlus(this,helper);
    }
    
    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TPlus text.");
    }
}
