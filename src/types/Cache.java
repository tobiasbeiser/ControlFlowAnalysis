package types;

public class Cache implements Node {
    private int label;

    public Cache(int label) {
        this.label = label;
    }
    public int getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "C(" + label + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cache) {
            Cache other = (Cache) obj;
            return label == other.label;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return label;
    }
}
