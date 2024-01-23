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
}
