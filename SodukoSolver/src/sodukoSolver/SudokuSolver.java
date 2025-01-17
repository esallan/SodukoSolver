package sodukoSolver;

public class SudokuSolver implements InterfaceSudokuSolver {
	private int[][] matrix = new int[9][9];

	public SudokuSolver() {
		clearAll();
	}

	@Override
	public boolean solve() {
		
		if(!isAllValid()) {
			return false;
		}
		
		return solve(0, 0);
	}

	private boolean solve(int row, int col) {
		if (row == -1 && col == -1) {
			return true;
		}

		int[] nextElementIndecies = next(row, col);
		int nextRow = nextElementIndecies[0];
		int nextCol = nextElementIndecies[1];

		boolean isPreFilled = (matrix[row][col] != 0);

		if (isPreFilled) {
			return solve(nextRow, nextCol);
		}

		for (int attemptedNumber = 1; attemptedNumber < 10; attemptedNumber++) {
			matrix[row][col] = attemptedNumber;

			if (isValid(row, col) && solve(nextRow, nextCol)) {
				return true;
			}

			matrix[row][col] = 0; // måste återställa

		}
		return false;
	}

	private int[] next(int row, int col) {
		if (row == 8 && col == 8) {
			return new int[] { -1, -1 }; // Finished
		} else if (col == 8) {
			return new int[] { row + 1, 0 };
		} else {
			return new int[] { row, col + 1 };
		}
	}

	@Override
	public void set(int row, int col, int digit) {
		if (row > 8 || row < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (col > 8 || col < 0) {
			throw new IndexOutOfBoundsException();
		}

		if (digit > 9 || digit < 0) {
			throw new IllegalArgumentException();
		}

		matrix[row][col] = digit;

	}

	@Override
	public int get(int row, int col) {
		return matrix[row][col];
	}

	@Override
	public void clear(int row, int col) {
		matrix[row][col] = 0;

	}

	@Override
	public void clearAll() {
		MatrixIterator iterator = new MatrixIterator(matrix.length, matrix[0].length);
		while (iterator.hasNext()) {
			int[] position = iterator.next();
			int row = position[0];
			int col = position[1];
			matrix[row][col] = 0;
		}

	}

	@Override
	public boolean isValid(int row, int col) {
		if (row > 8 || row < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (col > 8 || col < 0) {
			throw new IndexOutOfBoundsException();
		}

		if (matrix[row][col] == 0) {
			return true;
		} else {
			boolean validRow = isRowValid(row, col);
			boolean validCol = isColValid(row, col);
			boolean validRegion = isRegionValid(row, col);
			return validRow && validCol && validRegion;

		}
	}

	private boolean isRowValid(int row, int col) {
		int digit = matrix[row][col];
		for (int c = 0; c < 9; c++) {
			if (c != col && digit == matrix[row][c]) { // Uteslut samma cell.
				return false;
			}
		}
		return true;
	}

	private boolean isColValid(int row, int col) {
		int digit = matrix[row][col];
		for (int r = 0; r < 9; r++) {
			if (r != row && digit == matrix[r][col]) {
				return false;
			}
		}
		return true;
	}

	private boolean isRegionValid(int row, int col) {
		int startRowInRegion = (row / 3) * 3;
		int startColInRegion = (col / 3) * 3;

		boolean[] seen = new boolean[10];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int current = matrix[startRowInRegion + i][startColInRegion + j];
				if (current != 0) {
					if (seen[current]) {
						return false;
					}
					seen[current] = true;
				}
			}
		}
		return true;
	}

	@Override
	public boolean isAllValid() {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix.length; col++) {
				if (get(row, col) < 0 || get(row, col) > 9) {
					return false;
				}
				if (!isValid(row, col)) {
					return false;
				}
			}
			continue;
		}
		return true;
	}

	@Override
	public void setGrid(int[][] m) {
		if (m.length != 9 || m[0].length != 9) {
			throw new IllegalArgumentException("Grid must be 9x9");
		}

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (m[row][col] < 0 || m[row][col] > 9) {
					throw new IllegalArgumentException("Value must be between 0 and 9");
				}
			}
		}
		this.matrix = m;
	}

	@Override
	public int[][] getGrid() {
		return this.matrix;
	}

}
