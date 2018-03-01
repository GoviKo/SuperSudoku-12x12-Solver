package org.goviko.sudoku;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

public class SudokuSolver12x12 {

	public static final int BOARD_WIDTH = 12;
	public static final int BOARD_HEIGHT = BOARD_WIDTH;
	static int[][] sudoku;

	/**
	 * puzzle input is a text file with sudoku grid 12 x 12 the empty elements
	 * are replaced by zeroes cells are space separated, and lines are newline
	 * separated
	 * 
	 * @param filePath
	 * @return
	 */
	public static int[][] readFileInput(String filePath) {
		int[][] board = new int[BOARD_WIDTH][BOARD_HEIGHT];
		try {
			List<String> lines = Files.readAllLines(Paths.get(filePath));
			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);
				String[] elements = line.split(" ");
				for (int j = 0; j < elements.length; j++) {
					board[i][j] = Integer.valueOf(elements[j]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return board;
	}

	/**
	 * 
	 * @param board
	 * @param message
	 */
	public static void printSudoku(int[][] board, String message) {
		System.out.println("-----------// BEGIN PRINTING :: " + message + " // ---------------------------");
		for (int[] line : board) {
			for (int element : line) {
				System.out.print(element + " ");
			}
			System.out.println();
		}
		System.out.println("-----------// END PRINTING :: " + message + "// ---------------------------\n\n");
	}

	private static boolean solve(int[][] board, int i, int j) {
		if (i == BOARD_HEIGHT) {
			i = 0;
			if (++j == BOARD_WIDTH) {
				return true;
			}
		}
		if (board[i][j] != 0) { // Already answered, recurse somewhere else!
			return solve(board, i + 1, j);
		}
		for (int v = 1; v <= BOARD_WIDTH; v++) {
			if (isValid(board, v, i, j)) {
				board[i][j] = v;
				// Recurse into child node.
				if (solve(board, i + 1, j)) {
					return true;
				}
			}
		}
		board[i][j] = 0;
		return false;
	}
	
	public static boolean isValid(int[][] board, int value, int row, int column){
		boolean rowMatch = IntStream.range(0, board[0].length)
									.anyMatch(c -> value==board[row][c]);
		
		if (rowMatch) { return false;}  //duplicate value found in row, not valid sudoku
				
		boolean columnMatch = IntStream.range(0, board[0].length)
										.anyMatch(r -> value==board[r][column]);
		if(columnMatch){ return false;} //duplicate value found in column, not valid sudoku
		
		// 4x3 box test.
		int boxRow = (row / 3) * 3;
		int boxCol = (column / 4) * 4;
		
		// 4x3 loop
		for (int r = 0; r < board.length / 4; r++) {
			for (int c = 0; c < board[0].length / 3; c++) {
				if (value == board[r + boxRow][c + boxCol]) {
					return false;
				}
			}
		}
		return true;
	}

	public static int[][] solvePuzzle(int[][] puzzleToSolve) {
		sudoku = puzzleToSolve;
		printSudoku(sudoku, "puzzle Input");

		long startTime = System.nanoTime();
		solve(sudoku, 0, 0);
		printSudoku(sudoku, "solution");
		long endTime = System.nanoTime();
		System.out.println("\tTime taken: " + (endTime - startTime) / 1000000 + " ms.");
		return sudoku;
	}
}