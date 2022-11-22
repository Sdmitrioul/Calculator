package ru.skroba;

import ru.skroba.exception.TokenizerException;
import ru.skroba.token.Token;
import ru.skroba.tokenizer.CalculatorTokenizer;
import ru.skroba.tokenizer.Tokenizer;
import ru.skroba.visitor.CalcVisitor;
import ru.skroba.visitor.ParserVisitor;
import ru.skroba.visitor.PrintVisitor;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        ParserVisitor parser = new ParserVisitor();
        CalcVisitor calculator = new CalcVisitor();
        PrintVisitor printer = new PrintVisitor();
        
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            while (true) {
                greet();
                String input = scanner.nextLine();
                
                if (":e".equals(input)) {
                    break;
                }
                
                Tokenizer tokenizer = new CalculatorTokenizer(input);
                
                try {
                    List<Token> parsed = parser.parse(tokenizer.getTokens());
                    
                    System.out.println(input + " -> ");
                    System.out.println(printer.print(parsed) + " == " + calculator.calculate(parsed));
                } catch (TokenizerException e) {
                    System.err.println("Exception: " + e.getMessage());
                } catch (ArithmeticException e) {
                    System.err.println("Division by null!");
                }
            }
        }
    }
    
    private static void greet() {
        System.out.print("For exit write :e\nWrite your expression: ");
    }
}
