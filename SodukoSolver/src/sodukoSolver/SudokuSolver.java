package sodukoSolver;

public class SudokuSolver implements InterfaceSudokuSolver {
	private int[][] matrix = new int[9][9];

	public SudokuSolver() {
		for (int rows = 0; rows < 9; rows++) {
			for (int coulmns = 0; coulmns < 9; coulmns++) {
				matrix[rows][coulmns] = 0;
			}
		}
	}

	@Override
	public boolean solve() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void set(int row, int col, int digit) {
		if(row > 8 || row < 0) {
			throw new IndexOutOfBoundsException();
		}
		if(col > 8 || col < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		if(digit > 8 ||digit < 0) {
			throw new IndexOutOfBoundsException();
		}

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
		for(int row = 0; row < matrix.length; row++) {
			for(int col = 0; col < matrix.length; col++) {
				matrix[row][col]=0;
			}
		}

	}

	@Override
	public boolean isValid(int row, int col) {
		if(row > 8 || row < 0) {
			throw new IndexOutOfBoundsException();
		}
		if(col > 8 || col < 0) {
			throw new IndexOutOfBoundsException();
		}
		else return true;
	}

	@Override
	public boolean isAllValid() {
		for(int row = 0; row < matrix.length; row++) {
			for(int col = 0; col < matrix.length; col++) {
				if(get(row, col) < 0 || get(row,col) > 8){
					return false;
				}
			}
		}return true;
	}

	@Override
	public void setGrid(int[][] m) {
		// TODO Auto-generated method stub

	}

	@Override
	public int[][] getGrid() {
		// TODO Auto-generated method stub
		return null;
	}

}
