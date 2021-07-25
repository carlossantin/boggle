# Boggle

This project implements a boggle game, the popular word search game.

Feeding the program with a dictionary of words and a board of characters, the program is able to inform which words from dictionary are present in board.

### How to use

- Create a board using the Board class. You must define the amount of lines and columns your board will have, and add each line of characteres. For example:
```java
final Board board = new Board(4, 4);
        board.addLine(List.of('c', 'j', 'z', 'e'));
        board.addLine(List.of('v', 'a', 'r', 'b'));
        board.addLine(List.of('x', 'n', 't', 'u'));
        board.addLine(List.of('i', 'a', 'n', 'k'));
```

- Create the dictionary having the words for searching, for example:
```java
final List<String> dictionary = List.of(
    "cat", "dog", "byte", "tube", "can", "ant", "car", "tank", "cart", "camel"
);
```

- Instantiate a board and call the method *findWords*:
```java
final List<String> foundWords = boogle.findWords();
```