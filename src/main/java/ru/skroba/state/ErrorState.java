package ru.skroba.state;

import ru.skroba.exception.TokenizerException;
import ru.skroba.token.Token;
import ru.skroba.tokenizer.Tokenizer;

public final class ErrorState implements State {
    private final static String MESSAGE = "Tokenizer got to error state! Message: ";
    
    private final String error;
    
    public ErrorState(final String message) {
        this.error = message;
    }
    
    @Override
    public Token createToken(final Tokenizer tokenizer) throws TokenizerException {
        throw new TokenizerException(MESSAGE + error);
    }
    
    @Override
    public void setNextState(final Tokenizer tokenizer) throws TokenizerException {
        throw new TokenizerException(MESSAGE + error);
    }
}
