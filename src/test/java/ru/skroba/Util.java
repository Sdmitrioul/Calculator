package ru.skroba;

import ru.skroba.token.BraceToken;
import ru.skroba.token.CharToken;
import ru.skroba.token.OperationToken;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {
    public static final List<CharToken> CHAR_TOKENS = Stream.concat(Arrays.stream(BraceToken.values()),
                    Arrays.stream(OperationToken.values()))
            .collect(Collectors.toList());
}
