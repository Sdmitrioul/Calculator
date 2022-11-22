package ru.skroba.state;

import ru.skroba.token.NumberToken;
import ru.skroba.token.Token;
import ru.skroba.tokenizer.Tokenizer;

public final class NumberState implements State {
    @Override
    public Token createToken(final Tokenizer tokenizer) {
        int value = 0;
        
        while (contains(tokenizer.curChar())) {
            value = value * 10 + (tokenizer.curChar() - '0');
            
            if (tokenizer.hasNext() && tokenizer.checkNext(NumberState::contains)) {
                tokenizer.nextChar();
            } else {
                break;
            }
        }
        
        return new NumberToken(value);
    }
    
    @Override
    public void setNextState(final Tokenizer tokenizer) {
        if (!tokenizer.hasNext()) {
            tokenizer.setState(new EndState());
            return;
        }
        
        final char next = tokenizer.nextChar();
        
        if (StartState.contains(next)) {
            tokenizer.setState(new StartState());
            return;
        }
        
        tokenizer.setState(new ErrorState("Unsupported symbol: " + next));
    }
    
    public static boolean contains(final char value) {
        return Character.isDigit(value);
    }
}
