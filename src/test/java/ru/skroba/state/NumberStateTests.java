package ru.skroba.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skroba.token.NumberToken;
import ru.skroba.tokenizer.CalculatorTokenizer;
import ru.skroba.tokenizer.Tokenizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.skroba.Util.CHAR_TOKENS;

public class NumberStateTests {
    private NumberState state;
    private Tokenizer tokenizer;
    
    @BeforeEach
    void setUp() {
        state = new NumberState();
    }
    
    @Test
    void testCreateTokenWithDifferentNumbers() {
        for (int i = 0; i < 100; i++) {
            int random = (int) (Math.random() * 10000);
            
            tokenizer = new CalculatorTokenizer(String.valueOf(random));
            
            assertEquals(new NumberToken(random), state.createToken(tokenizer));
        }
    }
    
    @Test
    void setNextStateForEnd() {
        tokenizer = new CalculatorTokenizer("");
        state.setNextState(tokenizer);
        assertTrue(tokenizer.getState() instanceof EndState);
    }
    
    @Test
    void setNextStateForStart() {
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
