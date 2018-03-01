package org.goviko.sudoku;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class SudokuSolverTest {

	@Test
	public void solveTest() {
		int boardWidth = 12;
		int boardHeight = 12;
		int[][] expected = Library.readFile("src/test/resources/expected/puzzleSolution.txt", boardWidth, boardHeight);

		int[][] puzzleToSolve = Library.readFile("src/test/resources/input/sudoku12x12Puzzle.txt", boardWidth,
				boardHeight);
		int[][] solution = SudokuSolver12x12.solvePuzzle(puzzleToSolve);
		Library.writeToFile(solution, "src/test/resources/puzzleOutput/solvedSolution.txt");
		assertThat(solution, equalTo(expected));
	}
}
