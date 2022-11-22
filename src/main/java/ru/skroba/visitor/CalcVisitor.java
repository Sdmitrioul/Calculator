package ru.skroba.visitor;

import ru.skroba.exception.VisitorException;
import ru.skroba.token.BraceToken;
import ru.skroba.token.NumberToken;
import ru.skroba.token.OperationToken;
import ru.skroba.token.Token;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CalcVisitor implements TokenVisitor {
    private Deque<Integer> queue;
    
    public synchronized int calculate(final List<Token> tokens) throws VisitorException {
        queue = new LinkedList<>();
        
        tokens.forEach(it -> it.accept(this));
        
        final int result = queue.isEmpty() ? 0 : queue.pollLast();
        
        if (!queue.isEmpty()) {
            throw new VisitorException("Queue is not empty: lost: " + queue.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(" ")));
        }
        
        return result;
    }
    
    @Override
    public void visit(final NumberToken token) {
        queue.addLast(token.value());
    }
    
    @Override
    public void visit(final BraceToken token) {
        throw new UnsupportedOperationException("This operation isn't allowed! Tokens list is not well formed!");
    }
    
    @Override
    public void visit(final OperationToken token) {
        if (queue.size() < 2) {
            throw new UnsupportedOperationException("This operation isn't allowed! Tokens list is not well formed!");
        }
    
    
        int second = queue.pollLast();
        int first = queue.pollLast();
        
        queue.addLast(token.eval(first, second));
    }
}
