package sodukoSolver;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SudokuGUI {

	private JFrame sodukoWindow;
	private JTextField[][] grid = new JTextField[9][9];

	public void createWindow() {
		sodukoWindow = new JFrame("SodukoSolver");
		sodukoWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sodukoWindow.setPreferredSize(new Dimension(600, 600));

		JPanel gridPanel = createGrid();

		JPanel buttonPanel = createButtons();

		JPanel wrapperPanel = createWrapperPanel(gridPanel);

		sodukoWindow.add(wrapperPanel, BorderLayout.CENTER);
		sodukoWindow.add(buttonPanel, BorderLayout.SOUTH);

		sodukoWindow.pack();
		sodukoWindow.setVisible(true);
	}

	private JPanel createWrapperPanel(JPanel gridPanel) {
		JPanel wrapperPanel = new JPanel(null);
		wrapperPanel.setPreferredSize(new Dimension(450, 450));
		wrapperPanel.add(gridPanel);
		gridPanel.setBounds(70, 70, 450, 450);
		return wrapperPanel;
	}

	private JPanel createGrid() {
		JPanel gridPanel = new JPanel(new GridLayout(9, 9));
		gridPanel.setPreferredSize(new Dimension(600, 600));
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				JTextField field = new JTextField();
				field.setHorizontalAlignment(JTextField.CENTER);
				field.setSize(50, 50);
				grid[row][col] = field;
				gridPanel.add(field); // Lägg till i rutnätet
			}
		}
		return gridPanel;
	}

	private JPanel createButtons() {
		JButton solveButton = new JButton("Solve");
		JButton clearButton = new JButton("Clear");

		solveButton.addActionListener(e -> solveSudoku());
		clearButton.addActionListener(e -> clearGrid());

		JPanel commandPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		commandPanel.add(solveButton);
		commandPanel.add(clearButton);

		return commandPanel;
	}

	private boolean solveSudoku() {
		InterfaceSudokuSolver solver = new SudokuSolver();
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				String cellText = grid[row][col].getText();
				char digit = '0';

				if (cellText != null && cellText.length() == 1) {
					char candidate = cellText.charAt(0);
					if (Character.isDigit(candidate) && candidate != '0') {
						digit = candidate;
					} else {
	                    errorMessage(grid[row][col]); // Skicka textfältet till errorMessage
	                }
				} else
					errorMessage(grid[row][col]);

				solver.set(row, col, digit); // TODO: handle parsing errors
			}
		}
		boolean solved = solver.solve();

		if (solved) {
			// copy the solver's grid onto the UI so the user can see the solution
			for (int row = 0; row < grid.length; row++) {
				for (int col = 0; col < grid[0].length; col++) {
					grid[row][col].setText(String.valueOf(solver.get(row, col)));
				}
			}
			return true;
		} else {
			System.out.print("This Soduko can't be solved");
			return false;
		}

	}

	private void errorMessage(JTextField textField) {
		JOptionPane.showMessageDialog(null, "Det måste vara en siffra mellan 1 och 9!", "Ogiltig inmatning",
				JOptionPane.WARNING_MESSAGE);

		textField.setText("");

	}

	private void clearGrid() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				grid[row][col].setText(""); // Rensa textfält
			}
		}
	}
}
