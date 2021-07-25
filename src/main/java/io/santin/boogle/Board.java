package io.santin.boogle;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int lineCount;
    private final int columnCount;

    private List<List<Character>> board = new ArrayList<>();

    public Board(int lineCount, int columnCount) {
        this.lineCount = lineCount;
        this.columnCount = columnCount;
    }

    public void addLine(final List<Character> line) {
        if (line.size() > columnCount) {
            throw new IllegalArgumentException("The maximum amount of columns is " + columnCount);
        }
        if (board.size() >= lineCount) {
            throw new IllegalArgumentException("The board is full. The maximum amount of lines is " + lineCount);
        }

        while (line.size() < columnCount) {
            line.add(null);
        }

        board.add(line);
    }

    public List<List<Character>> getBoard() {
        while (board.size() < lineCount) {
            this.addLine(new ArrayList<>());
        }

        return board;
    }
}
