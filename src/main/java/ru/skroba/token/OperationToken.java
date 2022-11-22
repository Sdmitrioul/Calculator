package ru.skroba.token;

import ru.skroba.visitor.TokenVisitor;

import java.util.function.BiFunction;

public enum OperationToken implements CharToken {
    SUM('+', Integer::sum, 4), SUB('-', (a, b) -> a - b, 4), MUL('*', (a, b) -> a * b, 2), DIV('/', (a, b) -> a / b, 2);
    
    private final char value;
    private final BiFunction<Integer, Integer, Integer> fun;
    private final int priority;
    
    OperationToken(final char value, final BiFunction<Integer, Integer, Integer> fun, final int priority) {
        this.value = value;
        this.fun = fun;
        this.priority = priority;
    }
    
    @Override
    public void accept(final TokenVisitor visitor) {
        visitor.visit(this);
    }
    
    public int eval(int a, int b) {
        return fun.apply(a, b);
    }
    
    @Override
    public char getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
    
    public int getPriority() {
        return priority;
    }
}
