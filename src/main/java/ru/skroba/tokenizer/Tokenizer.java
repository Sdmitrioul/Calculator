package ru.skroba.tokenizer;

import ru.skroba.exception.TokenizerException;
import ru.skroba.state.State;
import ru.skroba.token.Token;

import java.util.List;
import java.util.function.Predicate;

public interface Tokenizer {
    List<Token> getTokens() throws TokenizerException;
    
    State getState();
    
    void setState(State state);
    
    boolean hasNext();
    
    char nextChar();
    
    boolean checkNext(Predicate<Character> p);
    
    char curChar();
}
