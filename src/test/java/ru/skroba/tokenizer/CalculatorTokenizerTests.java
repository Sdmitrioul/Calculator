package ru.skroba.tokenizer;

import org.junit.jupiter.api.Test;
import ru.skroba.exception.TokenizerException;
import ru.skroba.token.CharToken;
import ru.skroba.token.NumberToken;
import ru.skroba.token.Token;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.skroba.Util.CHAR_TOKENS;

public class CalculatorTokenizerTests {
    @Test
    void testNumberTokenAlone() throws TokenizerException {
        for (int i = 0; i < 100; i++) {
            final int number = (int) (Math.random() * (Integer.MAX_VALUE - 10));
            CalculatorTokenizer tokenizer = new CalculatorTokenizer(String.valueOf(number));
            assertEquals(List.of(new NumberToken(number)), tokenizer.getTokens());
        }
    }
    
    @Test
    void testOtherTokensAlone() {
        CHAR_TOKENS.forEach(it -> {
            CalculatorTokenizer tokenizer = new CalculatorTokenizer(String.valueOf(it.getValue()));
            try {
                assertEquals(List.of(it), tokenizer.getTokens());
            } catch (TokenizerException e) {
                throw new RuntimeException(e);
            }
        });
    }
    
    @Test
    void testRandomSequenceOfTokens() throws TokenizerException {
        for (int i = 0; i < 100; i++) {
            List<Token> tokens = Stream.iterate(generateRandomToken(),
                            token -> token instanceof NumberToken ? getCharToken() : generateRandomToken())
                    .limit((int) (Math.random() * 120))
                    .toList();
            
            CalculatorTokenizer tokenizer = new CalculatorTokenizer(tokens.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining()));
            
            assertEquals(tokens, tokenizer.getTokens());
        }
    }
    
    private static Token generateRandomToken() {
        if (Math.random() > 0.5) {
            return new NumberToken((int) (Math.random() * (Integer.MAX_VALUE - 10)));
        } else {
            return getCharToken();
        }
    }
    
    private static CharToken getCharToken() {
        int index = (int) (Math.random() * CHAR_TOKENS.size());
        return CHAR_TOKENS.get(index);
    }
}
