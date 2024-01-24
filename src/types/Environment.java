package types;

public record Environment(String variable) implements Node {

    @Override
    public String toString() {
        return "r(" + variable + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Environment other) {
            return variable.equals(other.variable);
        }
        return false;
    }

}
