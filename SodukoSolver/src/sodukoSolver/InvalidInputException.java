package sodukoSolver;

public class InvalidInputException extends Exception {
	public int row;
	public int col;
	
	public InvalidInputException(int row, int col) {
		this.row = row;
		this.col = col;
	}

}
