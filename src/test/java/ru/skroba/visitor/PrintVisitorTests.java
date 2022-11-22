package ru.skroba.visitor;

import org.junit.jupiter.api.Test;
import ru.skroba.token.NumberToken;
import ru.skroba.token.OperationToken;
import ru.skroba.token.Token;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintVisitorTests {
    private PrintVisitor printVisitor = new PrintVisitor();
    
    @Test
    void testBaseNum() {
        for (int i = 0; i < 100; i++) {
            int num = (int) (Math.random() * Integer.MAX_VALUE);
            List<Token> token = List.of(new NumberToken(num));
            
            assertEquals(String.valueOf(num), printVisitor.print(token));
        }
    }
    
    @Test
    void testBaseOperation() {
        int num = (int) (Math.random() * Integer.MAX_VALUE);
        NumberToken numToken = new NumberToken(num);
        
        for (OperationToken operationToken : OperationToken.values()) {
            List<Token> tokens = List.of(numToken, numToken, operationToken);
            
            assertEquals(String.format("%d %d %s", num, num, operationToken), printVisitor.print(tokens));
        }
    }
}
