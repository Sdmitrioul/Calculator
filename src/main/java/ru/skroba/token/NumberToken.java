package ru.skroba.token;

import ru.skroba.visitor.TokenVisitor;

public class NumberToken implements Token {
    
    private final int value;
    
    public NumberToken(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    @Override
    public void accept(final TokenVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
    
    @Override
    public int hashCode() {
        return value;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof NumberToken token)) {
            return false;
        }
        
        return token.hashCode() == this.hashCode();
    }
}
