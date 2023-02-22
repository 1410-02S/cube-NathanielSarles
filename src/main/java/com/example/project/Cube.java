package com.example.project;
import java.util.Random;
import java.util.ArrayList;

public class Cube {
	static String[][][] cube = {
		{
			{"01","02","03"},
			{"04","05","06"},
			{"07","08","09"}
		},
		{
			{"11","12","13"},
			{"14","15","16"},
			{"17","18","19"}
		},
		{
			{"21","22","23"},
			{"24","25","26"},
			{"27","28","29"}
		},
		{
			{"31","32","33"},
			{"34","35","36"},
			{"37","38","39"}
		},
		{
			{"41","42","43"},
			{"44","45","46"},
			{"47","48","49"}
		},
		{
			{"51","52","53"},
			{"54","55","56"},
			{"57","58","59"}
		}
	};

	/**Prints a given face of the cube
	 * 
	 * @param face a two dimensional array
	 */
	public static void printFace(String[][] face){
		for (int i = 0; i < face.length; i++){
			for(int j = 0; j < face[i].length; j++){
				System.out.print(face[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**Prints the entire cube
	 * 
	 * @param c The cube to be displayed
	 */
	public static void printCube(String[][][] c){
		for(int i = 0; i < c.length; i++){
			printFace(c[i]);
			System.out.println();
		}
	}
	
	/**Rotates a given face of the cube
	 * 
	 * @param face the face we want to rotate
	 * @param clockwise what direction it's being rotated
	 * @return The rotated face
	 */
	public static String[][] rotateFace(String[][] face, boolean clockwise){
		String[][] temp = new String[3][3];
		for (int i = 0; i < face.length; i++){
			for(int j = 0; j < face[i].length; j++){
				temp[i][j] = face[j][i];
			}
		}
		if (clockwise){
			temp = swapCol(temp,1,3);
		} else {
			temp = swapRow(temp,1,3);
		}
		return temp;
	}

	public static String[][][] rotateSide(String[][][] c, boolean clockwise, int index){
		int[] sides = new int[4];
		int[] sideRowIndex = new int[4];
		boolean[] transposeSide = {false, false, false, false};
		String[] temp = new String[3];
		switch (index){
			case 0:
				for (int i = 0; i < 4; i++){
					sides[i] = i+1;
					sideRowIndex[i] = 1;
				}
				break;
			case 5:
				for (int i = 0; i < 4; i++){
					sides[i] = i+1;
					sideRowIndex[i] = 3;
				}
				break;
		}
		return null;
	}

	/**Swaps the given rows of the given face.
	 * Rows should be given in natural numbers(first row is 1).
	 * 
	 * @param face the face we want to swap rows on
	 * @param r1 the first row we want to swap
	 * @param r2 the second row we want to swap
	 * @return The given face with the rows swapped
	 */
	public static String[][] swapRow(String[][] face, int r1, int r2){
		String[] row1 = new String[face[0].length];
		String[] row2 = new String[face[0].length];
		for(int i= 0; i < face[0].length; i++){
			row1[i] = face[(r1-1)][i];
		}
		for(int i= 0; i < face[0].length; i++){
			row2[i] = face[(r2-1)][i];
		}
		String[] temp = row1;
		row1 = row2;
		row2 = temp;
		for (int i = 0; i < face.length; i++){
			if (i == (r1-1) || i == (r2-1)){
				for(int j = 0; j < face[i].length; j++){
					if(i == r1-1){
						face[i][j] = row1[j];
					} else{
						face[i][j] = row2[j];
					}
				}
			}
		}
		return(face);
	}
	
	/**Swaps the given columns of the given face.
	 * Columns should be given in natural numbers(first col is 1).
	 * 
	 * @param face the face we want to swap columns on
	 * @param c1 the first of the columns we want to swap
	 * @param c2 the second of the columns we want to swap
	 * @return The given face with the columns swapped
	 */
	public static String[][] swapCol(String[][] face, int c1, int c2){
		String[] col1 = new String[face.length];
		String[] col2 = new String[face.length];
		for(int i= 0; i < face[0].length; i++){
			col1[i] = face[i][c1-1];
		}
		for(int i= 0; i < face[0].length; i++){
			col2[i] = face[i][c2-1];
		}
		String[] temp = col1;
		col1 = col2;
		col2 = temp;
		for (int i = 0; i < face.length; i++){
			for(int j = 0; j < face[i].length; j++){
				if(j == (c1-1)){
					face[i][j] = col1[i];
				} if(j == (c2-1)){
					face[i][j] = col2[i];
				}
			}
		}
		return(face);
	}

	/**Creates a series of random integers to be used in the 
	 * scramble cube function.
	 * 
	 * @return An array of integers
	 */
	public static int[] scramble(){
		Random random = new Random();
		int[] scrambled = new int[random.nextInt(100) + 1];
		for (int i = 0; i < scrambled.length; i++){
			scrambled[i] = random.nextInt(12) + 1;
		}
		return scrambled;
	}

	/**Takes the instructions used to scramble the cube and
	 * reverses it then inverts it. This creates the long brute
	 * force way of solving the cube, that is doing the opposite
	 * of what was done to it.
	 * 
	 * @param scram the instructions used to scramble the cube
	 * @return an array of ints showing the brute force solution
	 */
	public static int[] createSolution(int[] scram){
		int[] solution = new int[scram.length];
		for(int i = scram.length; i > 0; i--){
			int x = scram.length - i;
			solution[x] = scram[i-1];
		}
		for (int i = 0; i < solution.length; i++ ){
			int x = solution[i];
			switch (x){
				case 1:
				solution[i] = 7;
				break;
				case 2:
				solution[i] = 8;
				break;
				case 3:
				solution[i] = 9;
				break;
				case 4:
				solution[i] = 10;
				break;
				case 5:
				solution[i] = 11;
				break;
				case 6:
				solution[i] = 12;
				break;
				case 7:
				solution[i] = 1;
				break;
				case 8:
				solution[i] = 2;
				break;
				case 9:
				solution[i] = 3;
				break;
				case 10:
				solution[i] = 4;
				break;
				case 11:
				solution[i] = 5;
				break;
				case 12:
				solution[i] = 6;
				break;
			}
		}
		return solution;
	}

	/**Checks th solution for any loops(when the same move is
	 * used 4 times in the row) or any instance where the same 
	 * move is used 3 times in a row. If a loop is detected it
	 * skips the step. If the same move is used 3 times in a row
	 * it does the inverse of the move(if CW go CCW)
	 * 
	 * @param solution The brute force solution
	 * @return an array of ints showing a somewhat simplified solution
	 */
	public static int[] simplifySolution(int[] solution){
		ArrayList<Integer> temp = new ArrayList<Integer>();
		int x = 0;
		int y, z, w, i = 0;
		while (x < solution.length){
			y = x + 1;
			z = y + 1;
			w = z + 1;
			if (z >= solution.length){
				temp.add(i,solution[x]);
				x++;
				i++;
				continue;
			}
			if(solution[x] == solution[y] && solution[y] == solution[z]){
				if (w < solution.length){
					if(solution[z] == solution[w]){
						x = w + 1;
						continue;	
					}
				}
				switch (solution[x]){
					case 1:
					temp.add(i,7);
					break;
					case 2:
					temp.add(i,8);
					break;
					case 3:
					temp.add(i,9);
					break;
					case 4:
					temp.add(i,10);
					break;
					case 5:
					temp.add(i,11);
					break;
					case 6:
					temp.add(i,12);
					break;
					case 7:
					temp.add(i,1);
					break;
					case 8:
					temp.add(i,2);
					break;
					case 9:
					temp.add(i,3);
					break;
					case 10:
					temp.add(i,4);
					break;
					case 11:
					temp.add(i,5);
					break;
					case 12:
					temp.add(i,6);
					break;
				}
					x = z;
				}
			else {
				temp.add(i,solution[x]);
			}
			x++;
			i++;
		}
		int[] simp = new int[temp.size()];
		for (i = 0; i < simp.length; i++){
			simp[i] = temp.get(i);
		}
		return simp;
	}

	/**Takes a face and returns the transpose(rows become cols) of 
	 * the face.
	 * 
	 * @param face The face you want to transpose
	 * @return The transpose of face.
	 */
	public static String[][] transposeFace(String[][] face){
		String[][] temp = face;
		for (int i = 0; i < face.length; i++){
			for (int j = 0; j < face[i].length; j++){
				temp[i][j] = face[j][i];
			}
		}
		return temp;
	}

	public static void main(final String[] args) {
		System.out.println("Base Face 0 (Top)");
		printFace(cube[0]);
		System.out.println("Base Face 1 (Front)");
		printFace(cube[1]);
		String[][] test1 = rotateFace(cube[0], true);
		System.out.println("Test Clockwise Face 0");
		printFace(test1);
		String[][] test2 = rotateFace(cube[1], false);
		System.out.println("Test Counter Clockwise Face 1");
		printFace(test2);
		System.out.println("Test scramble");
		int[] scramble = scramble();
		for(int i = 0; i < scramble.length; i++){
			System.out.print(scramble[i] + " ");
		}
		System.out.println();
		System.out.println("Test createSolution");
		int[] solution = createSolution(scramble);
		for(int i = 0; i < solution.length; i++){
			System.out.print(solution[i] + " ");
		}
		System.out.println();
		System.out.println("Test simplifySolution");
		int[] simpSolution = simplifySolution(solution);
		for(int i = 0; i < simpSolution.length; i++){
			System.out.print(simpSolution[i] + " ");
		}
		System.out.println();
		System.out.println("Test printCube");
		printCube(cube);
	}

}

