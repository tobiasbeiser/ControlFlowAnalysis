/* This file was generated by SableCC (http://www.sablecc.org/). */

package fun.node;

import fun.analysis.*;

@SuppressWarnings("nls")
public final class AFunTerm extends PTerm
{
    private TId _name_;
    private TId _param_;
    private PTerm _term_;

    public AFunTerm()
    {
        // Constructor
    }

    public AFunTerm(
        @SuppressWarnings("hiding") TId _name_,
        @SuppressWarnings("hiding") TId _param_,
        @SuppressWarnings("hiding") PTerm _term_)
    {
        // Constructor
        setName(_name_);

        setParam(_param_);

        setTerm(_term_);

    }

    @Override
    public Object clone()
    {
        return new AFunTerm(
            cloneNode(this._name_),
            cloneNode(this._param_),
            cloneNode(this._term_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFunTerm(this);
    }
    @SuppressWarnings("unchecked")

    public <T,S> T apply(Switch<T,S> sw, S helper)
    {
        return ((GAnalysis<T,S>) sw).caseAFunTerm(this,helper);
    }


    public TId getName()
    {
        return this._name_;
    }

    public void setName(TId node)
    {
        if(this._name_ != null)
        {
            this._name_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._name_ = node;
    }

    public TId getParam()
    {
        return this._param_;
    }

    public void setParam(TId node)
    {
        if(this._param_ != null)
        {
            this._param_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._param_ = node;
    }

    public PTerm getTerm()
    {
        return this._term_;
    }

    public void setTerm(PTerm node)
    {
        if(this._term_ != null)
        {
            this._term_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._term_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._name_)
            + toString(this._param_)
            + toString(this._term_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._name_ == child)
        {
            this._name_ = null;
            return;
        }

        if(this._param_ == child)
        {
            this._param_ = null;
            return;
        }

        if(this._term_ == child)
        {
            this._term_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._name_ == oldChild)
        {
            setName((TId) newChild);
            return;
        }

        if(this._param_ == oldChild)
        {
            setParam((TId) newChild);
            return;
        }

        if(this._term_ == oldChild)
        {
            setTerm((PTerm) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
