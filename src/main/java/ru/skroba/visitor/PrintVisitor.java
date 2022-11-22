package ru.skroba.visitor;

import ru.skroba.token.BraceToken;
import ru.skroba.token.NumberToken;
import ru.skroba.token.OperationToken;
import ru.skroba.token.Token;

import java.util.List;

public class PrintVisitor implements TokenVisitor {
    private StringBuilder result;
    
    public synchronized String print(final List<Token> tokens) {
        result = new StringBuilder();
        
        tokens.forEach(it -> it.accept(this));
        
        return result.toString();
    }
    
    @Override
    public void visit(final NumberToken token) {
        result.append(token.toString()).append(" ");
    }
    
    @Override
    public void visit(final BraceToken token) {
        throw new UnsupportedOperationException("This operation isn't allowed! Tokens list is not well formed!");
    }
    
    @Override
    public void visit(final OperationToken token) {
        result.append(token.toString()).append(" ");
    }
}
