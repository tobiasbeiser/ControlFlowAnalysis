package types;

public class Term {
    private String value;
    private int label;

    public Term(String value, int label) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Term) {
            Term other = (Term) obj;
            return value.equals(other.value) && label== other.label;
        }
        return false;
    }
}
