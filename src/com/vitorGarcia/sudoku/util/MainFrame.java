package com.vitorGarcia.sudoku.util;

import com.vitorGarcia.sudoku.model.Board;
import com.vitorGarcia.sudoku.util.PuzzleLoader;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private Board board;
    private final SudokuPanel sudokuPanel;

    public MainFrame() {
        super("Sudoku Swing Edition");

        board = PuzzleLoader.loadRandomBoard();  
        sudokuPanel = new SudokuPanel(board);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setJMenuBar(createMenuBar());
        add(sudokuPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Jogo");

        JMenuItem verifyItem = new JMenuItem("Verificar");
        verifyItem.addActionListener(e -> {
            if (board.hasErrors()) {
                JOptionPane.showMessageDialog(this, "O jogo contém erros!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum erro encontrado.", "OK", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JMenuItem resetItem = new JMenuItem("Criar Novo Jogo");
        resetItem.addActionListener(e -> {
            board = PuzzleLoader.loadRandomBoard();  
            sudokuPanel.setBoard(board);             
        });

        JMenuItem exitItem = new JMenuItem("Sair");
        exitItem.addActionListener(e -> System.exit(0));

        gameMenu.add(verifyItem);
        gameMenu.add(resetItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        menuBar.add(gameMenu);
        return menuBar;
    }
}
