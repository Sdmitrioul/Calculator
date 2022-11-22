package ru.skroba.token;

import ru.skroba.visitor.TokenVisitor;

public record NumberToken(int value) implements Token {
    
    @Override
    public void accept(final TokenVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof NumberToken token)) {
            return false;
        }
        
        return token.hashCode() == this.hashCode();
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
