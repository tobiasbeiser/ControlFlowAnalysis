package types;

import java.util.HashSet;
import java.util.Set;

public class Terms {
    private final Set<Term> terms;

    public Terms(Term term) {
        this.terms = new HashSet<>();
        this.terms.add(term);
    }

    public Terms() {
        this.terms = new HashSet<>();
    }

    public Set<Term> getTerms() {
        return new HashSet<>(terms);
    }

    public boolean isSubset(Terms terms) {
        return this.terms.containsAll(terms.getTerms());
    }

    public void union(Terms terms) {
        this.terms.addAll(terms.getTerms());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Term term : this.terms) {
            builder.append(term.toString());
            builder.append(", ");
        }
        if (builder.length() > 2) {
            builder.delete(builder.length() - 2, builder.length());
        }
        return "{" + builder + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Terms terms) {
            return this.terms.equals(terms.getTerms());
        }
        return false;
    }
}
