package types;

public class Term {
    private final String value;
    public Term(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Term other) {
            return value.equals(other.value);
        }
        return false;
    }
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
