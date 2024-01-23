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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Environment) {
            Environment other = (Environment) obj;
            return variable.equals(other.variable);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return variable.hashCode();
    }
}
