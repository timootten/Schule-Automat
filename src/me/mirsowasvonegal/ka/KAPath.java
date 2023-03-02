package me.mirsowasvonegal.ka;

public class KAPath {

    private int fromState;
    private int toState;
    private String terminal;
    private String top;
    private Action action;

    public enum Action {
        ADD,
        DELETE
    }

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

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public KAPath(int fromState, int toState, String terminal, String top, Action action) {
        this.fromState = fromState;
        this.toState = toState;
        this.terminal = terminal;
        this.top = top;
        this.action = action;
    }
}
