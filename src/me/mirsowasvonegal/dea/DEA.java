package me.mirsowasvonegal.dea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DEA {

    private ArrayList<Integer> states = new ArrayList<>(); // Zustandsliste von 1-X
    private int startState; // Ein bestimmter Zustand
    private ArrayList<Integer> endStates = new ArrayList<>(); // Teilmenge der Zustände
    private Set<String> terminals = new HashSet<>();  // Kleinbuchstaben von a-z
    private ArrayList<Path> paths = new ArrayList<>();

    public DEA(int stateCount, int startState, ArrayList<Integer> endStates, Set<String> terminals, ArrayList<Path> paths) {
        for (int i = 0; i < stateCount; i++) {
            states.add(i);
        }
        this.startState = startState;
        this.endStates = endStates;
        this.terminals = terminals;
        this.paths = paths;
    }

    public boolean checkWord(String word) {
        int state = startState;
        String[] characters = word.split("");
        for (int i = 0; i < characters.length; i++) {
            Path path = getPathByStateAndTerminal(state, characters[i]);
            if(path == null) return false;
            state = path.getToState();
            if((characters.length-1) == i) {
                return endStates.contains(state);
            }
        }
        return endStates.contains(state);
    }

    public Path getPathByStateAndTerminal(int state, String terminal) {
        for (Path path : paths) {
            if(path.getFromState() == state && path.getTerminal().equals(terminal)) {
                return path;
            }
        }
        return null;
    }

}
