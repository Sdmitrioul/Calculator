# Calculator task

Repository was created for Software Design course in ITMO (CT) university by
Skroba Dmitriy.

### Purpose:

Gain practical experience in applying visitor and state behavior patterns.

### What`s has been done:

1. Created three
   visitors: [ParserVisitor](./src/main/java/ru/skroba/visitor/ParserVisitor.java) - for parsing list of
   tokens, [PrintVisitor](./src/main/java/ru/skroba/visitor/PrintVisitor.java) -
   for converting parsed list of tokens to
   string, [CalcVisitor](./src/main/java/ru/skroba/visitor/CalcVisitor.java) -
   for calculating value of parsed list of tokens.
2. Created tokenizer - [Tokenizer](./src/main/java/ru/skroba/tokenizer/CalculatorTokenizer.java)
   that works on state machine. State classes can be found
   here: [dir](./src/main/java/ru/skroba/state).
3. For all classes was developed tests.
4. Created [Application](./src/main/java/ru/skroba/Calculator.java) for testing
   developed classes in console in interactive mod.

### How to launch:

1. Clone repository: ```git clone https://github.com/Sdmitrioul/Calculator.git```.
2. Go to project root directory.
3. Run: ```./gradlew run --console=plain```.
