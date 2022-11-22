package ru.skroba.state;

import ru.skroba.exception.TokenizerException;
import ru.skroba.token.Token;
import ru.skroba.tokenizer.Tokenizer;

public final class EndState implements State {
    private final static String MESSAGE = "Tokenizer got to end state!";
    
    @Override
    public Token createToken(final Tokenizer tokenizer) throws TokenizerException {
        throw new TokenizerException(MESSAGE);
    }
    
    @Override
    public void setNextState(final Tokenizer tokenizer) {
    
    }
}
