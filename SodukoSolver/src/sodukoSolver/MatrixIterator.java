package sodukoSolver;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<int[]> {
    private final int rows;
    private final int cols;
    private int currentRow = 0;
    private int currentCol = 0;

    public MatrixIterator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }
    
    public void forEachElement() {
    	while (hasNext()) {
			int[] position = next();
			int row = position[0];
			int col = position[1];
			
    	}
    }

    @Override
    public boolean hasNext() {
        return currentRow < rows && currentCol < cols;
    }

    @Override
    public int[] next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int[] position = {currentRow, currentCol};
        currentCol++;
        if (currentCol >= cols) {
            currentCol = 0;
            currentRow++;
        }
        return position;
    }
}
