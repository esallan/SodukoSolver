package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import sodukoSolver.InterfaceSudokuSolver;
import sodukoSolver.SudokuSolver;

class testSodukoSolver {

	
	
// Tests for solve(); method
	@Test
	@DisplayName("solve an empty soduko, should be able and return true")
	void solveEmptySoduko() {
		SudokuSolver solver = new SudokuSolver();
		boolean solved = solver.solve();
		assertTrue(solved, "Should be solved and return true");
	}

	@Test
	@DisplayName("solve given soduko, should return true")
	void solveGivenSoduko() {
		SudokuSolver solver = new SudokuSolver();
		solver.set(0, 3, 8);
		solver.set(0, 5, 9);
		solver.set(0, 7, 6);
		solver.set(0, 8, 2);
		solver.set(1, 8, 5);
		solver.set(2, 0, 1);
		solver.set(2, 2, 2);
		solver.set(2, 3, 5);
		solver.set(3, 3, 2);
		solver.set(3, 4, 1);
		solver.set(3, 7, 9);
		solver.set(4, 1, 5);
		solver.set(4, 6, 6);
		solver.set(5, 0, 6);
		solver.set(5, 7, 2);
		solver.set(5, 8, 8);
		solver.set(6, 0, 4);
		solver.set(6, 1, 1);
		solver.set(6, 3, 6);
		solver.set(6, 5, 8);
		solver.set(7, 0, 8);
		solver.set(7, 1, 6);
		solver.set(7, 4, 3);
		solver.set(7, 6, 1);
		solver.set(8, 6, 4);

		assertTrue(solver.solve(), "Given Soduko should return true");
	}

	@Test
	@DisplayName("not possible to solve, should return false")
	void solveTryNotPossible() {
		SudokuSolver solver = new SudokuSolver();
		solver.set(0, 0, 1);
		solver.set(0, 1, 1);
		assertFalse(solver.solve(), "does not follow rules and should be false");
	}

// Tests for set(); method
//	@Test
//	@DisplayName("check if digit is in correct box")
//	void setCheckCorrectDigit() {
//		fail("Not yet implemented");
//	}

	@Test
	@DisplayName("throws IndexOutOfBoundsException if row or col is outside the range[0..8]")
	void setCheckIndexOutOfBoundsException() {
		SudokuSolver solver = new SudokuSolver();
		assertThrows(IndexOutOfBoundsException.class, () -> {
			solver.set(9, 9, 0);
		});
	}

	@Test
	@DisplayName("IllegalArgumentException if digit is outside the range [0..9]")
	void setCheckIllegalArgumentException() {
		SudokuSolver solver = new SudokuSolver();
		assertThrows(IllegalArgumentException.class, () -> {
			solver.set(0, 0, 0);
		});
	}

// Tests for get(); method
	@Test
	@DisplayName("IndexOutOfBoundsException if row or col is outside the range[0..8]")
	void getCheckIndexOutOfBoundsException() {
		SudokuSolver solver = new SudokuSolver();
		assertThrows(IndexOutOfBoundsException.class, () -> {
			solver.get(9, 9);
		});
		
	}

	@Test
	@DisplayName("gets the digit in box row")
	void getCheckDigitInBox() {
		SudokuSolver solver = new SudokuSolver();
		solver.set(0, 0, 1);
		assertEquals(1, solver.get(0, 0));

	}

	@Test
	@DisplayName("gets 0 if the box i empty")
	void getCheckThatDigitIsZeroIfBoxISEmpty() {
		SudokuSolver solver = new SudokuSolver();
		assertEquals(0, solver.get(0, 0));

	}

//Tests for clear(); method
	@Test
	@DisplayName("IndexOutOfBoundsException if row or col is outside the range[0..8]")
	void clearCheckIndexOutOfBoundsException() {
		SudokuSolver solver = new SudokuSolver();
		assertThrows(IndexOutOfBoundsException.class, () -> {
			solver.clear(9, 9);
		});
	}

	@Test
	@DisplayName("Test that box, row, col, is empty, 0, after cleared")
	void clearCheck() {
		SudokuSolver solver = new SudokuSolver();
		solver.set(0, 0, 1);
		solver.clear(0, 0);
		assertEquals(0, solver.get(0, 0));
 
	}

//Test for clearAll(); method
	@Test
	@DisplayName("Test that every box in grid is empty, 0, after clearAll")
	void clearAllCheck() {
		SudokuSolver solver = new SudokuSolver();
		solver.set(0, 0, 1);
		solver.set(1, 0, 2);
		solver.set(0, 2, 3);
		solver.clearAll();
		assertEquals(0, solver.get(0, 0));
		assertEquals(0, solver.get(1, 0));
		assertEquals(0, solver.get(0, 2));
		
	}

//Tests for isValid(); method
	@Test
	@DisplayName("IndexOutOfBoundsException if row or col is outside the range [0..8]")
	void isValidOutsideRange() {
		SudokuSolver solver = new SudokuSolver();
		assertThrows(IndexOutOfBoundsException.class, () -> {
			solver.isValid(9,9);
		});
	}

	@Test
	@DisplayName("true if the box is empty")
	void isValidEmptyBox() {
		SudokuSolver solver = new SudokuSolver();
		assertTrue(solver.isValid(0, 0), "true if the box is empty");
		
	}
	
	

	@Test
	@DisplayName("true if the digit in the box row, col follows the sudoku rules")
	void isValidSodukoRules() {
		SudokuSolver solver = new SudokuSolver();
		solver.set(0, 0, 1);
		assertTrue(solver.isValid(0, 0), "true if the digit is valid");
	}

//Tests for isAllValid(); method
	@Test
	@DisplayName("true if all filled in digits follows the the sudoku rules")
	void isAllValidSodukoRules() {
		fail("Not yet");
	}

	@Test
	@DisplayName("false if all filled in digits does NOT follows the the sudoku rules")
	void isAllValidSodukoRulesNotFollowed() {
		fail("Not yet");
	}

//Tests for setGrid();
	@Test
	@DisplayName("IllegalArgumentException if m has the wrong dimension")
	void setGridWrongDimensions() {
		fail("Not yet");

	}

	@Test
	@DisplayName("IllegalArgumentException if m contains values outside the range [0..9]")
	public void setGridValuesOutsideRange() {
		fail("Not yet");

	}

//Test for getGrid(); method
	@Test
	@DisplayName("a matix with all digits in the sudoku grid gets returned")
	public void getGrid() {
		fail("Not yet");

	}

}
