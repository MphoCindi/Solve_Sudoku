package Sudoku_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sudoku extends JFrame {
    private JPanel sudokuPanel;
    private JPanel buttonPanel;

    public sudoku() {
        setTitle("Sudoku Puzzle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);

        sudokuPanel = new JPanel(new GridLayout(9, 9));
        buttonPanel = new JPanel(new FlowLayout());

        int[][] puzzle = {
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

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField textField = new JTextField();
                textField.setDocument(new JTextFieldLimit(1)); // Set the document here
                textField.setHorizontalAlignment(JTextField.CENTER);
                if ((i / 3 + j / 3) % 2 == 0) {
                    textField.setBackground(Color.LIGHT_GRAY);
                } else {
                    textField.setBackground(Color.WHITE);
                }
                if (puzzle[i][j] != 0) {
                    textField.setText(String.valueOf(puzzle[i][j]));
                    textField.setEditable(false);
                    textField.setForeground(Color.BLACK);
                }
                sudokuPanel.add(textField);
            }
        }



        JButton solveButton = new JButton("Solve Puzzle");
        solveButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Puzzle solved!");
            }
        });


        JButton exitButton = new JButton("Exit");
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

    public static void main(String[] args) {
        new sudoku();
    }

}
