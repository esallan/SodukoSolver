package sodukoSolver;

import java.awt.BorderLayout;
import java.awt.Color;
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
		colorGrid();
		return gridPanel;
	}

	private void colorGrid() {
		MatrixIterator iterator = new MatrixIterator(grid.length, grid[0].length);
		while (iterator.hasNext()) {
			int[] position = iterator.next();
			int row = position[0];
			int col = position[1];
			int region = getRegion(row, col);
			boolean isEvenRegion = (region % 2 == 0);
			if (isEvenRegion) {
				grid[row][col].setBackground(Color.pink);
			}
		}
	}

	private int getRegion(int row, int col) {
		int startRowInRegion = (row / 3) * 3;
		int startColInRegion = (col / 3) * 3;
		int rowRegionIndex = startRowInRegion / 3;
		int colRegionIndex = startColInRegion / 3;
		int regionIndex = rowRegionIndex + colRegionIndex;
		return regionIndex;

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

		try {
			copyGridToSolver(solver);
		} catch (InvalidInputException exception) {
			handleException(exception);
			return false;
		}
		
		boolean solved = solver.solve();

		if (solved) {
			copySolverToGrid(solver);
			return true;
		} else {
			showUnsolvableMessage();
			return false;
		}

	}

	private void handleException(InvalidInputException exception) {
		JTextField invalidTextField = grid[exception.row][exception.col];
		String invalidText = invalidTextField.getText();
		showErrorMessage(invalidText);
		invalidTextField.setText("");
	}

	private void copyGridToSolver(InterfaceSudokuSolver solver) throws InvalidInputException {
		MatrixIterator iterator = new MatrixIterator(grid.length, grid[0].length);
		while (iterator.hasNext()) {
			int[] position = iterator.next();
			int row = position[0];
			int col = position[1];
			String cellText = grid[row][col].getText();
			char digit = '0';

			if (cellText != null && cellText.length() == 1) { // om det i rutan inte är noll OCH längden av det är 1
				char candidate = cellText.charAt(0);
				if (Character.isDigit(candidate) && candidate != '0') {
					digit = candidate;
				} else {
					throw new InvalidInputException(row, col);
				}
			} else if (cellText.length() > 1) {
				throw new InvalidInputException(row, col);
			}

			solver.set(row, col, Character.getNumericValue(digit)); // TODO: handle parsing errors
		}
	}

	private void copySolverToGrid(InterfaceSudokuSolver solver) {
		MatrixIterator iterator = new MatrixIterator(grid.length, grid[0].length);
		while (iterator.hasNext()) {
			int[] position = iterator.next();
			int row = position[0];
			int col = position[1];
			grid[row][col].setText(String.valueOf(solver.get(row, col)));
		}
	}

	private void showErrorMessage(String invalidText) {
		JOptionPane.showMessageDialog(null,
				"\"" + invalidText + "\"" + " är ej giltigt.\n Skriv en siffra mellan 1 och 9!", "Ogiltig inmatning",
				JOptionPane.WARNING_MESSAGE);
	}

	private void showUnsolvableMessage() {
		JOptionPane.showMessageDialog(null, "Detta sudoku går inte att lösa", "Ej lösbart",
				JOptionPane.WARNING_MESSAGE);
	}

	private void clearGrid() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				grid[row][col].setText("");
			}
		}
	}
}
