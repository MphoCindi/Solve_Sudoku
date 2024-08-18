package Sudoku.src.Sudoku_GUI;

import Sudoku.src.Sudoku_Solver.SudokuSolver;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Sudoku extends JFrame {
    private final JPanel sudokuPanel;
    private JPanel buttonPanel;
    private final int[][] puzzle = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    SudokuSolver solver = new SudokuSolver(puzzle);

    public Sudoku() {
        setTitle("Sudoku Puzzle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);

        sudokuPanel = new JPanel(new GridLayout(9, 9));
        buttonPanel = new JPanel(new FlowLayout());

        initializeGrid();

        JButton solveButton = new JButton("Solve Puzzle");
        solveButton.setPreferredSize(new Dimension(150, 30));
        solveButton.setFont(new Font("Arial", Font.BOLD, 15));
        solveButton.setBackground(Color.WHITE);
        solveButton.setBackground(new Color(220, 220, 220));
        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                solvePuzzle();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(150, 30));
        exitButton.setFont(new Font("Arial", Font.BOLD, 15));
        exitButton.setBackground(new Color(220, 220, 220));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(solveButton);
        buttonPanel.add(exitButton);

        add(sudokuPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void initializeGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField textField = new JTextField();
                textField.setDocument(new limit(1));
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setPreferredSize(new Dimension(40, 40));
                textField.setFont(new Font("Arial", Font.BOLD, 15));
                textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if ((i / 3 + j / 3) % 2 == 0) {
                    textField.setBackground(new Color(220, 220, 220));
                } else {
                    textField.setBackground(Color.WHITE);
                }
                if (puzzle[i][j] != 0) {
                    textField.setText(String.valueOf(puzzle[i][j]));
                    textField.setEditable(false);
                    textField.setForeground(Color.BLACK);
                } else {
                    int row = i;
                    int col = j;
                    textField.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            String text = textField.getText();
                            if (!text.isEmpty() && Character.isDigit(text.charAt(0))) {
                                int num = Integer.parseInt(text);
                                if (isValid(num, row, col)) {
                                    textField.setForeground(Color.BLACK);
                                } else {
                                    textField.setForeground(Color.RED);
                                }
                            } else {
                                textField.setForeground(Color.BLACK);
                            }
                        }
                    });
                }
                sudokuPanel.add(textField);
            }
        }
    }

    private boolean isValid(int num, int row, int col) {
        // Check if the entered number matches the solved puzzle's number at the same position
        boolean solved = solver.solveSudoku(0, 0);
        int[][] solvedPuzzle = solver.getPuzzle();
        return solvedPuzzle[row][col] == num;
    }


    private void solvePuzzle() {
        if (solver.solveSudoku(0, 0)) {
            int[][] solution = solver.getPuzzle();

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    Component[] components = sudokuPanel.getComponents();
                    JTextField textField = (JTextField) components[i * 9 + j];

                    String userInput = textField.getText();

                    if (!userInput.isEmpty() && Character.isDigit(userInput.charAt(0))) {
                        int num = Integer.parseInt(userInput);

                        if (num != solution[i][j]) {
                            // Highlight the incorrect cell with a red background
                            textField.setBackground(Color.RED);
                            textField.setForeground(Color.WHITE);
                        } else {
                            // Correct cell, display in green
                            textField.setBackground(Color.GREEN);
                            textField.setForeground(Color.BLACK);
                        }
                    } else {
                        // If the cell was empty or had non-digit input, just show the solution in green
                        textField.setText(String.valueOf(solution[i][j]));
                        textField.setBackground(Color.GREEN);
                        textField.setForeground(Color.BLACK);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No solution found!");
        }
    }


    public static void main(String[] args) {
        new Sudoku();
    }
}
