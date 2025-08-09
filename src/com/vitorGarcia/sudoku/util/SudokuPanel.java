package com.vitorGarcia.sudoku.util;

import com.vitorGarcia.sudoku.model.Board;

import javax.swing.*;
import java.awt.*;

public class SudokuPanel extends JPanel {

    private Board board;
    private final BoardPanel boardPanel;
    private Integer selectedNumber = null;
    private final JTextArea howToPlayTextArea;

    public SudokuPanel(Board board) {
        this.board = board;
        setLayout(new BorderLayout());

        boardPanel = new BoardPanel(board, this::getSelectedNumber);
        JPanel keypad = createKeypad();


        howToPlayTextArea = createHowToPlayTextArea();

        JPanel rightSidePanel = new JPanel();
        rightSidePanel.setLayout(new BorderLayout());
        rightSidePanel.add(keypad, BorderLayout.NORTH);
        rightSidePanel.add(howToPlayTextArea, BorderLayout.CENTER);

        add(boardPanel, BorderLayout.CENTER);
        add(rightSidePanel, BorderLayout.EAST);
    }

    private JTextArea createHowToPlayTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText(
                "Como jogar Sudoku:\n\n" +
                        "1. O objetivo é preencher a grade 9x9 com números de 1 a 9.\n" +
                        "2. Cada linha, coluna e bloco 3x3 deve conter todos os números de 1 a 9, sem repetir.\n" +
                        "3. Números fixos (em preto) não podem ser alterados.\n" +
                        "4. Selecione um número no teclado à direita e clique na célula onde quer colocar.\n" +
                        "5. Para apagar um número, selecione o botão 'Apagar' e clique na célula.\n" +
                        "6. Use o menu 'Jogo' para verificar erros ou resetar com um novo desafio.\n\n" +
                        "Divirta-se e exercite seu cérebro!"
        );
        textArea.setBorder(BorderFactory.createTitledBorder("Como jogar"));
        textArea.setPreferredSize(new Dimension(250, 300));
        return textArea;
    }

    private JPanel createKeypad() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 1; i <= 9; i++) {
            final int num = i;
            JButton btn = new JButton(String.valueOf(i));
            btn.setFont(new Font("SansSerif", Font.BOLD, 18));
            btn.addActionListener(e -> selectedNumber = num);
            panel.add(btn);
        }

        JButton clearBtn = new JButton("Apagar");
        clearBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        clearBtn.setForeground(Color.RED);
        clearBtn.addActionListener(e -> selectedNumber = null);
        panel.add(clearBtn);

        return panel;
    }

    public Integer getSelectedNumber() {
        return selectedNumber;
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public void setBoard(Board newBoard) {
        this.board = newBoard;
        boardPanel.setBoard(newBoard);
        repaint();
    }
}
