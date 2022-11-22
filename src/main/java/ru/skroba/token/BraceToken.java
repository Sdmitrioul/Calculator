package ru.skroba.token;

import ru.skroba.visitor.TokenVisitor;

public enum BraceToken implements CharToken {
    LEFT('(', 1), RIGHT(')', 1);
    
    private final char value;
    private final int priority;
    
    BraceToken(final char value, final int priority) {
        this.value = value;
        this.priority = priority;
    }
    
    @Override
    public void accept(final TokenVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public char getValue() {
        return value;
    }
    
    public int getPriority() {
        return priority;
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
