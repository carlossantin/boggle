package io.santin.boggle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BoardTest {

    @Test
    public void testAddingMoreColumnsThanDefined() {
        final Board board = new Board(2, 3);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> board.addLine(List.of('a', 'b', 'c', 'd')),
                "The maximum amount of columns is 3");
    }

    @Test
    public void testAddingLessColumnsThanDefined() {
        final int columnCount = 3;
        final Board board = new Board(2, columnCount);
        final List<Character> addedLine = new ArrayList<>(List.of('a', 'b'));
        board.addLine(addedLine);

        final List<Character> line = board.getBoard().get(0);
        Assertions.assertEquals(columnCount, line.size());
        for (int i = 0; i < addedLine.size(); i++) {
            Assertions.assertEquals(addedLine.get(i), line.get(i));
        }
        Assertions.assertNull(line.get(addedLine.size() - 1));
    }

    @Test
    public void testAddingExactlyColumnsAsDefined() {
        final int columnCount = 3;
        final Board board = new Board(2, columnCount);
        final List<Character> addedLine = new ArrayList<>(List.of('a', 'b', 'c'));
        board.addLine(addedLine);

        final List<Character> line = board.getBoard().get(0);
        Assertions.assertEquals(columnCount, line.size());
        for (int i = 0; i < addedLine.size(); i++) {
            Assertions.assertEquals(addedLine.get(i), line.get(i));
        }
    }

    @Test
    public void testAddingMoreLinesThanDefined() {
        final Board board = new Board(1, 4);

        board.addLine(List.of('a', 'b', 'c', 'd'));

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> board.addLine(List.of('a', 'b', 'c', 'd')),
                "The board is full. The maximum amount of lines is 1");
    }

    @Test
    public void testAddingLessLinesThanDefined() {
        final int lineCount = 2;
        final int columnCount = 4;
        final Board board = new Board(lineCount, columnCount);

        List<Character> addedLine = List.of('a', 'b', 'c', 'd');
        board.addLine(addedLine);

        final List<List<Character>> rawBoard = board.getBoard();
        Assertions.assertEquals(lineCount, rawBoard.size());

        for (int i = 0; i < columnCount; i++) {
            Assertions.assertEquals(addedLine.get(i), rawBoard.get(0).get(i));
            Assertions.assertNull(rawBoard.get(1).get(i));
        }


    }

}
