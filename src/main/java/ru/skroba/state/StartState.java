package ru.skroba.state;

import ru.skroba.token.BraceToken;
import ru.skroba.token.CharToken;
import ru.skroba.token.OperationToken;
import ru.skroba.token.Token;
import ru.skroba.tokenizer.Tokenizer;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StartState implements State {
    private static final Set<CharToken> TOKENS = Stream.concat(Arrays.stream(BraceToken.values()),
                    Arrays.stream(OperationToken.values()))
            .collect(Collectors.toSet());
    
    @Override
    public Token createToken(final Tokenizer tokenizer) {
        final char nextChar = tokenizer.curChar();
        
        if (NumberState.contains(nextChar)) {
            NumberState state = new NumberState();
            tokenizer.setState(state);
            
            return state.createToken(tokenizer);
        }
        
        return TOKENS.stream()
                .filter(it -> it.getValue() == nextChar)
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public void setNextState(final Tokenizer tokenizer) {
        if (!tokenizer.hasNext()) {
            tokenizer.setState(new EndState());
            return;
        }
        
        final char next = tokenizer.nextChar();
        
        if (Character.isDigit(next)) {
            tokenizer.setState(new NumberState());
            return;
        }
        
        if (contains(next)) {
            tokenizer.setState(this);
            return;
        }
        
        tokenizer.setState(new ErrorState("unexpected char: " + next));
    }
    
    public static boolean contains(final char value) {
        return Character.isWhitespace(value) || TOKENS.stream()
                .map(CharToken::getValue)
                .anyMatch(it -> it == value);
    }
}
