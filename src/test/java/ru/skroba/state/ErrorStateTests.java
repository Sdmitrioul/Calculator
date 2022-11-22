package ru.skroba.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skroba.exception.TokenizerException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ErrorStateTests {
    private ErrorState state;
    
    @BeforeEach
    void init() {
        this.state = new ErrorState("");
    }
    
    @Test
    void testCreateToken() {
        Exception exception = assertThrows(TokenizerException.class, () -> {
            state.createToken(null);
        });
        
        assertEquals("Tokenizer got to error state! Message: ", exception.getMessage());
    }
    
    @Test
    void testSetState() {
        Exception exception = assertThrows(TokenizerException.class, () -> {
            state.setNextState(null);
        });
        
        assertEquals("Tokenizer got to error state! Message: ", exception.getMessage());
    }
}
