package ru.skroba;

import org.junit.jupiter.api.Test;
import ru.skroba.exception.TokenizerException;
import ru.skroba.token.Token;
import ru.skroba.tokenizer.CalculatorTokenizer;
import ru.skroba.tokenizer.Tokenizer;
import ru.skroba.visitor.CalcVisitor;
import ru.skroba.visitor.ParserVisitor;
import ru.skroba.visitor.PrintVisitor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTests {
    private final PrintVisitor printer = new PrintVisitor();
    private final CalcVisitor calculator = new CalcVisitor();
    private final ParserVisitor parser = new ParserVisitor();
    
    @Test
    void randomTests() throws TokenizerException {
        Tokenizer tokenizer;
        List<Token> parsed;
        
        // 23 + 3536 - 0
        final String testOne = "23 + 34 * 104 - 45 / (21 + 43 * (1 + 7))";
        tokenizer = new CalculatorTokenizer(testOne);
        parsed = parser.parse(tokenizer.getTokens());
        assertEquals(3559, calculator.calculate(parsed));
        assertEquals("23 34 104 * + 45 21 43 1 7 + * + / -", printer.print(parsed));
        
        // 23 + 3536 - 454545 / 365 = 3559 - 1245
        final String testTwo = "23 + 34 * 104 - 454545 / (21 + 43 * (1 + 7))";
        tokenizer = new CalculatorTokenizer(testTwo);
        parsed = parser.parse(tokenizer.getTokens());
        assertEquals(2314, calculator.calculate(parsed));
        assertEquals("23 34 104 * + 454545 21 43 1 7 + * + / -", printer.print(parsed));
        
        // 18 / 3 = 6
        final String testThree = " (8+2*5)/(1+3*2-4)";
        tokenizer = new CalculatorTokenizer(testThree);
        parsed = parser.parse(tokenizer.getTokens());
        assertEquals(6, calculator.calculate(parsed));
        assertEquals("8 2 5 * + 1 3 2 * + 4 - /", printer.print(parsed));
    }
}
