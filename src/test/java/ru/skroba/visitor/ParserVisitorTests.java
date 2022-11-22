package ru.skroba.visitor;

import org.junit.jupiter.api.Test;
import ru.skroba.token.NumberToken;
import ru.skroba.token.OperationToken;
import ru.skroba.token.Token;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.skroba.token.BraceToken.LEFT;
import static ru.skroba.token.BraceToken.RIGHT;
import static ru.skroba.token.OperationToken.DIV;
import static ru.skroba.token.OperationToken.MUL;
import static ru.skroba.token.OperationToken.SUB;
import static ru.skroba.token.OperationToken.SUM;

public class ParserVisitorTests {
    private final ParserVisitor visitor = new ParserVisitor();
    
    @Test
    void testJustNumber() {
        for (int i = 0; i < 100; i++) {
            List<Token> token = List.of(new NumberToken((int) (Math.random() * Integer.MAX_VALUE)));
            assertEquals(token, visitor.parse(token));
        }
    }
    
    @Test
    void testBaseOperations() {
        for (OperationToken token : OperationToken.values()) {
            List<Token> tokens = List.of(new NumberToken(0), token, new NumberToken(25));
            List<Token> expected = List.of(new NumberToken(0), new NumberToken(25), token);
            assertEquals(expected, visitor.parse(tokens));
        }
    }
    
    @Test
    void testOperationInBraces() {
        for (OperationToken token : OperationToken.values()) {
            List<Token> tokens = List.of(LEFT, new NumberToken(0), token, new NumberToken(25), RIGHT);
            List<Token> expected = List.of(new NumberToken(0), new NumberToken(25), token);
            assertEquals(expected, visitor.parse(tokens));
        }
    }
    
    @Test
    void testCombinationOfOperations() {
        NumberToken num1 = new NumberToken(0);
        NumberToken num2 = new NumberToken(25);
        NumberToken num3 = new NumberToken(32);
        
        // (0 <+-> 25) <*/> 32
        for (Token firstOp : List.of(SUM, SUB)) {
            for (Token secOp : List.of(MUL, DIV)) {
                List<Token> tokens = List.of(LEFT, num1, firstOp, num2, RIGHT, secOp, num3);
                List<Token> expected = List.of(num1, num2, firstOp, num3, secOp);
                
                assertEquals(expected, visitor.parse(tokens));
            }
        }
        
        // 0 <+-> 25 <*/> 32
        for (Token firstOp : List.of(SUM, SUB)) {
            for (Token secOp : List.of(MUL, DIV)) {
                List<Token> tokens = List.of(num1, firstOp, num2, secOp, num3);
                List<Token> expected = List.of(num1, num2, num3, secOp, firstOp);
                
                assertEquals(expected, visitor.parse(tokens));
            }
        }
        
        // (0 <*/> 25) <*/> 32
        for (Token firstOp : List.of(MUL, DIV)) {
            for (Token secOp : List.of(MUL, DIV)) {
                List<Token> tokens = List.of(LEFT, num1, firstOp, num2, RIGHT, secOp, num3);
                List<Token> expected = List.of(num1, num2, firstOp, num3, secOp);
                
                assertEquals(expected, visitor.parse(tokens));
            }
        }
        
        // 0 <*/> 25 <*/> 32
        for (Token firstOp : List.of(MUL, DIV)) {
            for (Token secOp : List.of(MUL, DIV)) {
                List<Token> tokens = List.of(num1, firstOp, num2, secOp, num3);
                List<Token> expected = List.of(num1, num2, firstOp, num3, secOp);
                
                assertEquals(expected, visitor.parse(tokens));
            }
        }
        
        // (0 <+-> 25) <+-> 32
        for (Token firstOp : List.of(SUM, SUB)) {
            for (Token secOp : List.of(SUM, SUB)) {
                List<Token> tokens = List.of(LEFT, num1, firstOp, num2, RIGHT, secOp, num3);
                List<Token> expected = List.of(num1, num2, firstOp, num3, secOp);
                
                assertEquals(expected, visitor.parse(tokens));
            }
        }
        
        // 0 <+-> 25 <+-> 32
        for (Token firstOp : List.of(SUM, SUB)) {
            for (Token secOp : List.of(SUM, SUB)) {
                List<Token> tokens = List.of(num1, firstOp, num2, secOp, num3);
                List<Token> expected = List.of(num1, num2, firstOp, num3, secOp);
                
                assertEquals(expected, visitor.parse(tokens));
            }
        }
    }
}
