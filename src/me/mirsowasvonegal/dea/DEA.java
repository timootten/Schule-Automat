package me.mirsowasvonegal.dea;

import java.util.*;

public class DEA {

    private ArrayList<Integer> states = new ArrayList<>(); // Zustandsliste von 1-X
    private int startState; // Ein bestimmter Zustand
    private Set<Integer> endStates = new TreeSet<>(); // Teilmenge der Zustände
    private Set<String> terminals = new HashSet<>();  // Kleinbuchstaben von a-z
    private ArrayList<DEAPath> paths = new ArrayList<>();

    public DEA(int stateCount, int startState, Set<Integer> endStates, Set<String> terminals, ArrayList<DEAPath> paths) {
        for (int i = 0; i < stateCount; i++) {
            states.add(i);
        }
        this.startState = startState;
        this.endStates = endStates;
        this.terminals = terminals;
        this.paths = paths;
    }

    public boolean checkWord(String word) throws IllegalArgumentException {
        int state = startState;
        String[] characters = word.split("");
        for (int i = 0; i < characters.length; i++) {
            if(!terminals.contains(characters[i])) {
                throw new IllegalArgumentException();
            }
            DEAPath path = getPathByStateAndTerminal(state, characters[i]);
            if(path == null) return false;
            state = path.getToState();
            if((characters.length-1) == i) {
                return endStates.contains(state);
            }
        }
        return endStates.contains(state);
    }

    public DEAPath getPathByStateAndTerminal(int state, String terminal) {
        for (DEAPath path : paths) {
            if(path.getFromState() == state && path.getTerminal().equals(terminal)) {
                return path;
            }
        }
        return null;
    }

}
