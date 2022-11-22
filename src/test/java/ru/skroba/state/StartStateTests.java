package ru.skroba.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skroba.tokenizer.CalculatorTokenizer;
import ru.skroba.tokenizer.Tokenizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.skroba.Util.CHAR_TOKENS;

public class StartStateTests {
    private StartState state;
    private Tokenizer tokenizer;
    
    @BeforeEach
    void setUp() {
        state = new StartState();
    }
    
    @Test
    void testGenerateToken() {
        CHAR_TOKENS.forEach(it -> {
            tokenizer = new CalculatorTokenizer(String.valueOf(it.getValue()));
            assertEquals(it, state.createToken(tokenizer));
        });
    }
    
    @Test
    void testNextStateEndState() {
        tokenizer = new CalculatorTokenizer("");
        state.setNextState(tokenizer);
        assertTrue(tokenizer.getState() instanceof EndState);
    }
    
    @Test
    void testNextStateNumber() {
        for (int i = 0; i < 10; i++) {
            tokenizer = new CalculatorTokenizer("!" + i);
            state.setNextState(tokenizer);
            assertTrue(tokenizer.getState() instanceof NumberState);
        }
    }
    
    @Test
    void testNextStateStart() {
        CHAR_TOKENS.forEach(it -> {
            tokenizer = new CalculatorTokenizer("!" + it.getValue());
            state.setNextState(tokenizer);
            assertTrue(tokenizer.getState() instanceof StartState);
        });
    }
    
    @Test
    void setNextStateForError() {
        tokenizer = new CalculatorTokenizer("!!");
        state.setNextState(tokenizer);
        System.out.println(tokenizer.getState());
        assertTrue(tokenizer.getState() instanceof ErrorState);
    }
}
