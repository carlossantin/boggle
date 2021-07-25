package io.santin.boogle;

import io.santin.boggle.graph.GraphGenerator;
import io.santin.boggle.graph.GraphProcessor;
import io.santin.boggle.graph.Node;

import java.util.ArrayList;
import java.util.List;

public class Boogle {

    private final Board board;
    private final int maxColumn;
    private final int maxLine;
    private final List<Node> graph;

    public Boogle(final List<String> dictionary,
                  final Board board) {

        this.board = board;
        graph = GraphGenerator.createGraphFromStrings(dictionary);

        maxColumn = board.getBoard().get(0).size() - 1;
        maxLine = board.getBoard().size() - 1;
    }

    public List<String> findWords() {

        final List<String> foundWords = new ArrayList<>();

        for (int i = 0; i < maxLine; i++) {
            for (int j = 0; j < maxColumn; j++) {
                findWord("", i, j, foundWords, i, j);
            }
        }

        return foundWords;
    }

    private void findWord(String string,
                          final int line,
                          final int column,
                          List<String> foundWords,
                          final int previousLine,
                          final int previousColumn) {

        string = string + board.getBoard().get(line).get(column);

        if (!GraphProcessor.contains(graph, string)) {
            return;
        } else if (GraphProcessor.isACompleteWord(graph, string)) {
            foundWords.add(string);
        }

        for (int auxLine = line - 1; auxLine <= line + 1; auxLine++) {
            for (int auxColumn = column - 1; auxColumn <= column + 1; auxColumn++) {

                if (this.isInsideBoard(auxLine, auxColumn) &&
                        (this.isNotEqualsCurrentNeitherPrevious(auxLine, auxColumn, line, column, previousLine, previousColumn))) {

                        this.findWord(string, auxLine, auxColumn, foundWords, line, column);
                }
            }
        }
    }

    private boolean isNotEqualsCurrentNeitherPrevious(
            final int nextLine, final int nextColumn,
            final int currentLine, final int currentColumn,
            final int previousLine, final int previousColumn
    ) {
        return (!(nextLine == currentLine && nextColumn == currentColumn) &&
                !(nextLine == previousLine && nextColumn == previousColumn));
    }

    private boolean isInsideBoard(final int line,
                                  final int column) {
        return (line >= 0 && line <= maxLine) &&
                (column >= 0 && column <= maxColumn);
    }
}
