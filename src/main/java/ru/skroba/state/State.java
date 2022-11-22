package ru.skroba.state;

import ru.skroba.exception.TokenizerException;
import ru.skroba.token.Token;
import ru.skroba.tokenizer.Tokenizer;

public interface State {
    static boolean contains(char value) {
        return false;
    }
    
    Token createToken(Tokenizer tokenizer) throws TokenizerException;
    
    void setNextState(Tokenizer tokenizer) throws TokenizerException;
}
