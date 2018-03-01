package org.goviko.sudoku;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Library {

	public static int[][] readFile(String filePath, int width, int height) {
		int[][] board = new int[width][height];
		try {
			List<String> lines = Files.readAllLines(Paths.get(filePath));
			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);
				String[] elements = line.split(" ");
				for (int j = 0; j < elements.length; j++) {
					board[i][j] = Integer.valueOf(elements[j]);
				}
			}
		} catch (IOException e) {e.printStackTrace();}
		return board;
	}

	public static void writeToFile(int[][] board, String filePath) {
		StringBuffer sb = new StringBuffer();
		for(int[] line : board){
			for(int element : line){
				sb.append(element + " ");
			}
			sb.append("\n");
		}
		
		try {
			Files.write(Paths.get(filePath), sb.toString().getBytes());
		} catch (IOException e) {e.printStackTrace();}
	}
}