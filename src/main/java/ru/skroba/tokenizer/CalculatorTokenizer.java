package ru.skroba.tokenizer;

import ru.skroba.exception.TokenizerException;
import ru.skroba.state.EndState;
import ru.skroba.state.StartState;
import ru.skroba.state.State;
import ru.skroba.token.Token;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public final class CalculatorTokenizer implements Tokenizer {
    private final String text;
    private int index;
    
    private State state;
    
    public CalculatorTokenizer(final String text) {
        this.text = removeAllSpaces(text);
        this.index = 0;
        this.state = new StartState();
    }
    
    private String removeAllSpaces(final String text) {
        final StringBuilder result = new StringBuilder();
        
        for (char c : text.toCharArray()) {
            result.append(c);
        }
        
        return result.toString();
    }
    
    @Override
    public List<Token> getTokens() throws TokenizerException {
        List<Token> tokens = new LinkedList<>();
        
        while (!(state instanceof EndState)) {
            tokens.add(state.createToken(this));
            state.setNextState(this);
        }
        
        return tokens;
    }
    
    @Override
    public State getState() {
        return state;
    }
    
    @Override
    public void setState(final State state) {
        this.state = state;
    }
    
    @Override
    public boolean hasNext() {
        return index < text.length() - 1;
    }
    
    @Override
    public char nextChar() {
        return text.charAt(++index);
    }
    
    @Override
    public boolean checkNext(final Predicate<Character> p) {
        return p.test(text.charAt(index + 1));
    }
    
    @Override
    public char curChar() {
        return text.charAt(index);
    }
}
