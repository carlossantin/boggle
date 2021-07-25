package io.santin.boogle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BoogleTest {

    @Test
    public void testFindWords() {
        final List<String> dictionary = List.of(
                "cat", "dog", "byte", "tube", "can", "ant", "car", "tank", "cart", "camel"
        );

        final Board board = new Board(4, 4);
        board.addLine(List.of('c', 'j', 'z', 'e'));
        board.addLine(List.of('v', 'a', 'r', 'b'));
        board.addLine(List.of('x', 'n', 't', 'u'));
        board.addLine(List.of('i', 'a', 'n', 'k'));

        final Boogle boogle = new Boogle(dictionary, board);

        final List<String> expectedWords = List.of("ant", "can", "car", "cart", "cat", "tank", "tube");

        final List<String> foundWords = boogle.findWords();

        Assertions.assertEquals(expectedWords.size(), foundWords.size());
        expectedWords.forEach(it -> Assertions.assertTrue(foundWords.contains(it)));
    }
}
