package ru.skroba.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skroba.exception.TokenizerException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EndStateTests {
    private EndState state;
    
    @BeforeEach
    void init() {
        state = new EndState();
    }
    
    @Test
    void testCreateToken() {
        Exception exception = assertThrows(TokenizerException.class, () -> {
            state.createToken(null);
        });
        
        assertEquals("Tokenizer got to end state!", exception.getMessage());
    }
}
