package com.vitorGarcia.sudoku.model;

public class Space {

    private Integer actual;
    private final Integer expected;
    private final boolean fixed;

    public Space(final Integer expected, final boolean fixed) {
        this.expected = expected;
        this.fixed = fixed;
        if (fixed) {
            this.actual = expected;
        }
    }

    public Integer getActual() {
        return actual;
    }

    public void setActual(final Integer actual) {
        if (fixed) return;
        this.actual = actual;
    }

    public void clearSpace() {
        setActual(null);
    }

    public Integer getExpected() {
        return expected;
    }

    public boolean isFixed() {
        return fixed;
    }
}
