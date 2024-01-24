package types;

public record Cache(int label) implements Node {

    @Override
    public String toString() {
        return "C(" + label + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cache other) {
            return label == other.label;
        }
        return false;
    }

}
