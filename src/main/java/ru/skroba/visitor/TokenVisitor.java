package ru.skroba.visitor;

import ru.skroba.exception.VisitorException;
import ru.skroba.token.BraceToken;
import ru.skroba.token.NumberToken;
import ru.skroba.token.OperationToken;

public interface TokenVisitor {
    void visit(NumberToken token);
    
    void visit(BraceToken token) throws VisitorException;
    
    void visit(OperationToken token);
}
