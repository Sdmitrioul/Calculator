package ru.skroba.token;

import ru.skroba.visitor.TokenVisitor;

public interface Token {
    void accept(TokenVisitor visitor);
}
