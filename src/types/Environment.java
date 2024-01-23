package types;

public class Environment implements Node{
    private String variable;
    public Environment(String variable) {
        this.variable = variable;
    }

    public String getVariable() {
        return variable;
    }

    @Override
    public String toString() {
        return "r(" + variable + ")";
    }
}
