package ru.skroba.visitor;

import org.junit.jupiter.api.Test;
import ru.skroba.token.NumberToken;
import ru.skroba.token.OperationToken;
import ru.skroba.token.Token;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.skroba.token.OperationToken.DIV;
import static ru.skroba.token.OperationToken.MUL;
import static ru.skroba.token.OperationToken.SUB;
import static ru.skroba.token.OperationToken.SUM;

public class CalcVisitorTests {
    private final CalcVisitor visitor = new CalcVisitor();
    
    @Test
    void testJustNumber() {
        for (int i = 0; i < 100; i++) {
            NumberToken token = new NumberToken((int) (Math.random() * Integer.MAX_VALUE));
            List<Token> tokens = List.of(token);
            assertEquals(token.value(), visitor.calculate(tokens));
        }
    }
    
    @Test
    void testBaseOperations() {
        for (OperationToken token : OperationToken.values()) {
            List<Token> tokens = List.of(new NumberToken(0), new NumberToken(25), token);
            
            assertEquals(token.eval(0, 25), visitor.calculate(tokens));
        }
    }
    
    @Test
    void testOperationInBraces() {
        for (OperationToken token : OperationToken.values()) {
            List<Token> tokens = List.of(new NumberToken(0), new NumberToken(25), token);
            assertEquals(token.eval(0, 25), visitor.calculate(tokens));
        }
    }
    
    @Test
    void testCombinationOfOperations() {
        NumberToken num1 = new NumberToken(0);
        NumberToken num2 = new NumberToken(32);
        NumberToken num3 = new NumberToken(25);
        
        // (0 <+-> 32) <*/> 35
        for (OperationToken firstOp : List.of(SUM, SUB)) {
            for (OperationToken secOp : List.of(MUL, DIV)) {
                List<Token> tokens = List.of(num1, num2, firstOp, num3, secOp);
                
                assertEquals(secOp.eval(firstOp.eval(num1.value(), num2.value()), num3.value()),
                        visitor.calculate(tokens));
            }
        }
        
        // 0 <+-> 32 <*/> 25
        for (OperationToken firstOp : List.of(SUM, SUB)) {
            for (OperationToken secOp : List.of(MUL, DIV)) {
                List<Token> tokens = List.of(num1, num2, num3, secOp, firstOp);
                
                assertEquals(firstOp.eval(num1.value(), secOp.eval(num2.value(), num3.value())),
                        visitor.calculate(tokens));
            }
        }
        
        // (0 <*/> 32) <*/> 25
        for (OperationToken firstOp : List.of(MUL, DIV)) {
            for (OperationToken secOp : List.of(MUL, DIV)) {
                List<Token> tokens = List.of(num1, num2, firstOp, num3, secOp);
                
                assertEquals(secOp.eval(firstOp.eval(num1.value(), num2.value()), num3.value()),
                        visitor.calculate(tokens));
            }
        }
        
        // 0 <*/> 32 <*/> 25
        for (OperationToken firstOp : List.of(MUL, DIV)) {
            for (OperationToken secOp : List.of(MUL, DIV)) {
                List<Token> tokens = List.of(num1, num2, firstOp, num3, secOp);
                
                assertEquals(secOp.eval(firstOp.eval(num1.value(), num2.value()), num3.value()),
                        visitor.calculate(tokens));
            }
        }
        
        // (0 <+-> 32) <+-> 25
        for (OperationToken firstOp : List.of(SUM, SUB)) {
            for (OperationToken secOp : List.of(SUM, SUB)) {
                List<Token> tokens = List.of(num1, num2, firstOp, num3, secOp);
                
                assertEquals(secOp.eval(firstOp.eval(num1.value(), num2.value()), num3.value()),
                        visitor.calculate(tokens));
            }
        }
        
        // 0 <+-> 32 <+-> 25
        for (OperationToken firstOp : List.of(SUM, SUB)) {
            for (OperationToken secOp : List.of(SUM, SUB)) {
                List<Token> tokens = List.of(num1, num2, firstOp, num3, secOp);
                
                System.out.println(tokens);
                assertEquals(secOp.eval(firstOp.eval(num1.value(), num2.value()), num3.value()),
                        visitor.calculate(tokens));
            }
        }
    }
}
