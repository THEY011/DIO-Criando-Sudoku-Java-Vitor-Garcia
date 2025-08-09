package com.vitorGarcia.sudoku.model;

import java.util.Collection;
import java.util.List;

import static com.vitorGarcia.sudoku.model.GameStatusEnum.COMPLETE;
import static com.vitorGarcia.sudoku.model.GameStatusEnum.INCOMPLETE;
import static com.vitorGarcia.sudoku.model.GameStatusEnum.NON_STARTED;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Board {

    private final List<List<Space>> spaces;
    public static final int BOARD_LIMIT = 9;

    public Board(final List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }


    public Space getSpace(final int col, final int row) {
        return spaces.get(col).get(row);
    }

    public GameStatusEnum getStatus(){
        if (spaces.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixed() && nonNull(s.getActual()))){
            return NON_STARTED;
        }

        return spaces.stream().flatMap(Collection::stream).anyMatch(s -> isNull(s.getActual())) ? INCOMPLETE : COMPLETE;
    }

    public boolean hasErrors(){
        if(getStatus() == NON_STARTED){
            return false;
        }


        return spaces.stream().flatMap(Collection::stream)
                .anyMatch(s -> nonNull(s.getActual()) && nonNull(s.getExpected()) && !s.getActual().equals(s.getExpected()));
    }

    public boolean changeValue(final int col, final int row, final int value){
        var space = spaces.get(col).get(row);
        if (space.isFixed()){
            return false;
        }
        space.setActual(value);
        return true;
    }

    public boolean clearValue(final int col, final int row){
        var space = spaces.get(col).get(row);
        if (space.isFixed()){
            return false;
        }
        space.clearSpace();
        return true;
    }

    public void reset(){
        spaces.forEach(c -> c.forEach(Space::clearSpace));
    }

    public boolean gameIsFinished(){
        return !hasErrors() && getStatus().equals(COMPLETE);
    }
}
