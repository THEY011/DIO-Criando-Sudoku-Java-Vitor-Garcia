package com.vitorGarcia.sudoku.util;

import com.vitorGarcia.sudoku.model.Board;
import com.vitorGarcia.sudoku.model.Space;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Supplier;

public class BoardPanel extends JPanel {

    private Board board;
    private final Supplier<Integer> numberSupplier;
    private static final int CELL_SIZE = 60;

    public BoardPanel(Board board, Supplier<Integer> numberSupplier) {
        this.board = board;
        this.numberSupplier = numberSupplier;
        setPreferredSize(new Dimension(Board.BOARD_LIMIT * CELL_SIZE, Board.BOARD_LIMIT * CELL_SIZE));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick(e.getX(), e.getY());
            }
        });
    }
    public void setBoard(Board board) {
        this.board = board;
        repaint();
    }

    private void handleClick(int x, int y) {
        int col = x / CELL_SIZE;
        int row = y / CELL_SIZE;

        if (col < 0 || col >= Board.BOARD_LIMIT || row < 0 || row >= Board.BOARD_LIMIT) {
            return;
        }

        Space space = board.getSpace(col, row);

        if (space.isFixed()) {
            JOptionPane.showMessageDialog(this, "Essa posição é fixa!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Integer selectedNumber = numberSupplier.get();
        if (selectedNumber == null) {
            board.clearValue(col, row);
        } else {
            board.changeValue(col, row, selectedNumber);
        }

        repaint();

        if (board.gameIsFinished()) {
            JOptionPane.showMessageDialog(this, "Parabéns, você completou o Sudoku!", "Vitória", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawNumbers(g);
    }

    private void drawGrid(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i <= Board.BOARD_LIMIT; i++) {
            if (i % 3 == 0) {
                g2.setStroke(new BasicStroke(3));
            } else {
                g2.setStroke(new BasicStroke(1));
            }
            g2.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, Board.BOARD_LIMIT * CELL_SIZE);
            g2.drawLine(0, i * CELL_SIZE, Board.BOARD_LIMIT * CELL_SIZE, i * CELL_SIZE);
        }
    }

    private void drawNumbers(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("SansSerif", Font.BOLD, 20));

        for (int col = 0; col < Board.BOARD_LIMIT; col++) {
            for (int row = 0; row < Board.BOARD_LIMIT; row++) {
                Space space = board.getSpace(col, row);
                Integer value = space.getActual();
                if (value != null) {
                    int x = col * CELL_SIZE + CELL_SIZE / 2 - 6;
                    int y = row * CELL_SIZE + CELL_SIZE / 2 + 8;

                    if (space.isFixed()) {
                        g2.setColor(Color.BLACK);
                    } else {
                        // Se valor atual é igual ao esperado -> verde
                        if (value.equals(space.getExpected())) {
                            g2.setColor(new Color(0, 128, 0)); // verde escuro
                        } else {
                            g2.setColor(Color.RED);
                        }
                    }
                    g2.drawString(String.valueOf(value), x, y);
                }
            }
        }
    }
}
