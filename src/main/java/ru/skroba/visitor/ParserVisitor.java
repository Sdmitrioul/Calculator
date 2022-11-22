package ru.skroba.visitor;

import ru.skroba.exception.ParseTokensException;
import ru.skroba.token.BraceToken;
import ru.skroba.token.NumberToken;
import ru.skroba.token.OperationToken;
import ru.skroba.token.Token;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ParserVisitor implements TokenVisitor {
    private List<Token> parsed;
    private Deque<Token> queue;
    
    public synchronized List<Token> parse(final List<Token> tokens) throws ParseTokensException {
        parsed = new LinkedList<>();
        queue = new LinkedList<>();
        
        tokens.forEach(it -> it.accept(this));
        
        while (!queue.isEmpty()) {
            Token prev = queue.peekLast();
            if (prev instanceof OperationToken) {
                parsed.add(prev);
                queue.pollLast();
                continue;
            }
            
            throw new ParseTokensException("Unexpected token in stack: possible </, *, +, ->, got: " + prev);
        }
        
        return parsed;
    }
    
    @Override
    public void visit(final NumberToken token) {
        parsed.add(token);
    }
    
    @Override
    public void visit(final BraceToken token) throws ParseTokensException {
        if (token == BraceToken.LEFT) {
            queue.push(token);
            return;
        }
        
        while (!queue.isEmpty()) {
            Token prev = queue.peekLast();
            
            if (prev == BraceToken.LEFT) {
                queue.pollLast();
                break;
            }
            
            if (prev instanceof OperationToken) {
                parsed.add(prev);
                queue.pollLast();
                continue;
            }
            
            if (prev == BraceToken.RIGHT || prev instanceof NumberToken) {
                throw new ParseTokensException("Unexpected token in stack: possible ( or </, *, +, ->, got: " + prev);
            }
        }
    }
    
    @Override
    public void visit(final OperationToken token) {
        while (!queue.isEmpty()) {
            Token prev = queue.peekLast();
            
            if (!(prev instanceof OperationToken prevOp) || token.getPriority() < prevOp.getPriority()) {
                break;
            }
            
            parsed.add(prev);
            queue.pollLast();
        }
        
        queue.add(token);
    }
}
