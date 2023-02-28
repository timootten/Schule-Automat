package me.mirsowasvonegal.dea;

public class Path {

    private int fromState;
    private int toState;
    private String terminal;

    public int getFromState() {
        return fromState;
    }

    public void setFromState(int fromState) {
        this.fromState = fromState;
    }

    public int getToState() {
        return toState;
    }

    public void setToState(int toState) {
        this.toState = toState;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public Path(int fromState, int toState, String terminal) {
        this.fromState = fromState;
        this.toState = toState;
        this.terminal = terminal;
    }
}
