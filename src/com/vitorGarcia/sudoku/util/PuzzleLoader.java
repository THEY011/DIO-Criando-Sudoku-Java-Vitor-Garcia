package com.vitorGarcia.sudoku.util;

import  com.vitorGarcia.sudoku.model.Board;
import  com.vitorGarcia.sudoku.model.Space;

import java.util.ArrayList;
import java.util.List;

public final class PuzzleLoader {

    private PuzzleLoader() {}

    public static Board loadSampleBoard() {
        int[][] solution = {
                {1,2,3,4,5,6,7,8,9},
                {4,5,6,7,8,9,1,2,3},
                {7,8,9,1,2,3,4,5,6},
                {2,3,4,5,6,7,8,9,1},
                {5,6,7,8,9,1,2,3,4},
                {8,9,1,2,3,4,5,6,7},
                {3,4,5,6,7,8,9,1,2},
                {6,7,8,9,1,2,3,4,5},
                {9,1,2,3,4,5,6,7,8}
        };

        boolean[][] fixed = {
                {true, true, false, false, true, false, false, false, false},
                {true, false, false, true, true, true, false, false, false},
                {false, true, true, false, false, false, false, true, false},
                {true, false, false, false, true, false, false, false, true},
                {true, false, false, true, false, true, false, false, true},
                {true, false, false, false, true, false, false, false, true},
                {false, true, false, false, false, false, true, true, false},
                {false, false, false, true, true, true, false, false, true},
                {false, false, false, false, true, false, false, true, true}
        };

        List<List<Space>> spaces = new ArrayList<>();

        for (int col = 0; col < Board.BOARD_LIMIT; col++) {
            List<Space> column = new ArrayList<>();
            for (int row = 0; row < Board.BOARD_LIMIT; row++) {
                Integer expected = solution[row][col];
                boolean isFixed = fixed[row][col];
                column.add(new Space(expected, isFixed));
            }
            spaces.add(column);
        }

        return new Board(spaces);
    }
    public static Board loadRandomBoard() {
        Board baseBoard = loadSampleBoard();
        List<List<Space>> spaces = baseBoard.getSpaces();


        for (int block = 0; block < 3; block++) {
            int start = block * 3;
            shuffleRows(spaces, start, start + 3);
        }

        for (int block = 0; block < 3; block++) {
            int start = block * 3;
            shuffleColumns(spaces, start, start + 3);
        }

        return new Board(spaces);
    }

    private static void shuffleRows(List<List<Space>> spaces, int start, int end) {
        List<List<Space>> rows = new ArrayList<>();
        for (int row = start; row < end; row++) {
            List<Space> currentRow = new ArrayList<>();
            for (int col = 0; col < Board.BOARD_LIMIT; col++) {
                currentRow.add(spaces.get(col).get(row));
            }
            rows.add(currentRow);
        }

        java.util.Collections.shuffle(rows);

        for (int row = start; row < end; row++) {
            List<Space> shuffledRow = rows.get(row - start);
            for (int col = 0; col < Board.BOARD_LIMIT; col++) {
                spaces.get(col).set(row, shuffledRow.get(col));
            }
        }
    }

    private static void shuffleColumns(List<List<Space>> spaces, int start, int end) {
        List<List<Space>> cols = new ArrayList<>();
        for (int col = start; col < end; col++) {
            List<Space> currentCol = new ArrayList<>(spaces.get(col));
            cols.add(currentCol);
        }

        java.util.Collections.shuffle(cols);

        for (int col = start; col < end; col++) {
            List<Space> shuffledCol = cols.get(col - start);
            for (int row = 0; row < Board.BOARD_LIMIT; row++) {
                spaces.get(col).set(row, shuffledCol.get(row));
            }
        }
    }
}
