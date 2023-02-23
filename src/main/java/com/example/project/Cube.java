package com.example.project;
import java.util.Random;
import java.util.ArrayList;

public class Cube {
	static String[][][] cube = {
		{
			{"01","02","03"},
			{"04","W","06"},
			{"07","08","09"}
		},
		{
			{"11","12","13"},
			{"14","G","16"},
			{"17","18","19"}
		},
		{
			{"21","22","23"},
			{"24","R","26"},
			{"27","28","29"}
		},
		{
			{"31","32","33"},
			{"34","B","36"},
			{"37","38","39"}
		},
		{
			{"41","42","43"},
			{"44","O","46"},
			{"47","48","49"}
		},
		{
			{"51","52","53"},
			{"54","Y","56"},
			{"57","58","59"}
		}
	};

	static String[][][] cubeColor = {
		{
			{"w","w","w"},
			{"w","W","w"},
			{"w","w","w"}
		},
		{
			{"g","g","g"},
			{"g","G","g"},
			{"g","g","g"}
		},
		{
			{"r","r","r"},
			{"r","R","r"},
			{"r","r","r"}
		},
		{
			{"b","b","b"},
			{"b","B","b"},
			{"b","b","b"}
		},
		{
			{"o","o","o"},
			{"o","O","o"},
			{"o","o","o"}
		},
		{
			{"y","y","y"},
			{"y","Y","y"},
			{"y","y","y"}
		}
	};

	static String[][][] cubeColorCorners = {
		/** The order of the colors goes like this
		 * (Face)(Top/Bottom Face)(Front/Back)(Left/Right)
		 * so the green white red corner on the red face is
		 * rwg 
		 * 
		*/
		{
			{"wbo","wb","wbr"},
			{"wo","W","wr"},
			{"wgo","wg","wgr"}
		},
		{
			{"gwo","gw","gwr"},
			{"go","G","gr"},
			{"gyo","gy","gyr"}
		},
		{
			{"rwg","rw","rwb"},
			{"rg","R","rb"},
			{"ryg","ry","ryb"}
		},
		{
			{"bwr","bw","bwo"},
			{"br","B","bo"},
			{"byr","by","byo"}
		},
		{
			{"owb","ow","owg"},
			{"ob","O","og"},
			{"oyb","oy","oyg"}
		},
		{
			{"ygo","yg","ygr"},
			{"yo","Y","yr"},
			{"ybo","yb","ybr"}
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

	/**Swaps rows/cols between faces so that the sides of the rotated
	 * face are what they were before the rotation 
	 * 
	 * (ie for a solved cube if you rotated the white face clockwise
	 * the row/cols of the faces adjacent to will recieve a row/col
	 * of the face to its right. For the cube I'm using to model this
	 * that means that green<-red<-blue<-orange<-green )
	 * 
	 * @param c The whole cube
	 * @param clockwise Which direction the face was rotated
	 * @param index The index of the rotated face
	 * @return The entire cube with the sides fixed
	 */
	public static String[][][] rotateSide(String[][][] c, boolean clockwise, int index){
		int[] sides = new int[4];
		int[] sideRowIndex = new int[4];
		boolean[] transposeSide = {false, false, false, false};
		String[] temp = new String[3];
		switch (index){
			case 0:
				for (int i = 0; i < 4; i++){
					sides[i] = i+1;
					sideRowIndex[i] = 0;
				}
				break;
			case 1:
			sides[0] = 0;
			sides[1] = 4;
			sides[2] = 5;
			sides[3] = 2;
			for (int i = 0; i < 4; i++){
				if (i==0||i == 1){
					sideRowIndex[i] = 2;
					if(i==1){
						transposeSide[i]=true;
					}
				} else{
					sideRowIndex[i] = 0;
					if(i==3){
						transposeSide[i]=true;
					}
				}
			}
			break;
			case 2:
			sides[0] = 0;
			sides[1] = 1;
			sides[2] = 5;
			sides[3] = 3;
			for (int i = 0; i < 4; i++){
				transposeSide[i] = true;
				if (i != 3){
					sideRowIndex[i] = 2;
				} else{
					sideRowIndex[i] = 0;
				}
			}
			break;
			case 3:
			sides[0] = 0;
			sides[1] = 2;
			sides[2] = 5;
			sides[3] = 4;
			for (int i = 0; i < 4; i++){
				if (i==0||i == 3){
					sideRowIndex[i] = 0;
					if(i==3){
						transposeSide[i]=true;
					}
				} else{
					sideRowIndex[i] = 2;
					if(i==1){
						transposeSide[i]=true;
					}
				}
			}
			break;
			case 4:
			sides[0] = 0;
			sides[1] = 3;
			sides[2] = 5;
			sides[3] = 1;
			for (int i = 0; i < 4; i++){
				transposeSide[i] = true;
				if (i != 1){
					sideRowIndex[i] = 0;
				} else{
					sideRowIndex[i] = 2;
				}
			}
			break;
			case 5:
				for (int i = 0; i < 4; i++){
					sides[i] = 4-i;
					sideRowIndex[i] = 2;
				}
				break;
		}
		for(int i = 0; i < transposeSide.length; i++){
			if(transposeSide[i]){
				c[sides[i]] = transposeFace(c[sides[i]]);
			}
		}
		if(clockwise){
			temp = c[sides[0]][sideRowIndex[0]];
			c[sides[0]][sideRowIndex[0]] = c[sides[1]][sideRowIndex[1]];
			c[sides[1]][sideRowIndex[1]] = c[sides[2]][sideRowIndex[2]];
			c[sides[2]][sideRowIndex[2]] = c[sides[3]][sideRowIndex[3]];
			c[sides[3]][sideRowIndex[3]] = temp;
			switch(index){
				case 0,5:
				break;
				case 1:
				c[0][2]= reverseRow(c[0][2]);
				c[5][0]= reverseRow(c[5][0]);
				break;
				case 2:
				c[3][0]= reverseRow(c[3][0]);
				c[5][2]= reverseRow(c[5][2]);
				break;
				case 3:
				c[2][2]= reverseRow(c[2][2]);
				c[4][0]= reverseRow(c[4][0]);
				break;
				case 4:
				c[0][0]= reverseRow(c[0][0]);
				c[3][2]= reverseRow(c[3][2]);
				break;
			}
		} else{
			temp = c[sides[3]][sideRowIndex[3]];
			c[sides[3]][sideRowIndex[3]] = c[sides[2]][sideRowIndex[2]];
			c[sides[2]][sideRowIndex[2]] = c[sides[1]][sideRowIndex[1]];
			c[sides[1]][sideRowIndex[1]] = c[sides[0]][sideRowIndex[0]];
			c[sides[0]][sideRowIndex[0]] = temp;
			switch(index){
				case 0,5:
				break;
				case 1:
				c[2][0]= reverseRow(c[2][0]);
				c[4][2]= reverseRow(c[4][2]);
				break;
				case 2:
				c[3][0]= reverseRow(c[3][0]);
				c[0][2]= reverseRow(c[0][2]);
				break;
				case 3:
				c[0][0]= reverseRow(c[0][0]);
				c[5][2]= reverseRow(c[5][2]);
				break;
				case 4:
				c[3][2]= reverseRow(c[3][2]);
				c[5][0]= reverseRow(c[5][0]);
				break;
			}
		}
		for(int i = 0; i < transposeSide.length; i++){
			if(transposeSide[i]){
				c[sides[i]] = transposeFace(c[sides[i]]);
			}
		}
		return c;
	}

	/**Basically just here to call rotateFace and rotateSide at the
	 * same time with the same things so I don't have to type more
	 * later.
	 * 
	 * @param c The cube
	 * @param clockwise the direction to rotate
	 * @param index The face to rotate
	 * @return The cube after being rotated
	 */
	public static String[][][] rotateCube(String[][][] c, boolean clockwise, int index){
		c[index] = rotateFace(c[index], clockwise);
		c = rotateSide(c, clockwise, index);
		return c;
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
		String[][] temp = new String[face.length][face[0].length];
		for (int i = 0; i < temp.length; i++){
			for (int j = 0; j < temp[i].length; j++){
				temp[j][i] = face[i][j];
			}
		}
		return temp;
	}

	/**Takes an array and puts it in reverse order ie if the 
	 * array is {1,5,9,10,11} the output is {11,10,9,5,1}
	 * 
	 * @param row The row to reverse
	 * @return The reverse of the row
	 */
	public static String[] reverseRow(String[] row){
		String[] temp = new String[row.length];
		for(int i = 0; i<temp.length;i++){
			temp[i] = row[(row.length-1)-i];
		}
		return temp;
	}
	public static void main(final String[] args) {
		/**System.out.println("Base Face 0 (Top)");
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
		printCube(cubeColor);
		System.out.println();
		String[][][] testCube = rotateCube(cubeColor, false, 4);
		System.out.println("Test Rotate Cube with cubeColor");
		printCube(testCube);
		System.out.println();
		printFace(cube[1]);
		System.out.println("Test transposeFace");
		String[][] testFace = transposeFace(cube[1]);
		printFace(testFace);
		System.out.println("Test Rotate Cube with cubeColorCorners"); 
		boolean cw = false;
		int x = 10;
		switch (x){
			case 1:
			System.out.println("Testing Case 1: CW Green Face");
			cw = true;
			break;
			case 2:
			System.out.println("Testing Case 2: CW Red Face");
			cw = true;
			break;
			case 3:
			System.out.println("Testing Case 3: CW Blue Face");
			cw = true;
			break;
			case 4:
			System.out.println("Testing Case 4: CW Orange Face");
			cw = true;
			break;
			case 5:
			System.out.println("Testing Case 5: CW Yellow Face");
			cw = true;
			break;
			case 6:
			System.out.println("Testing Case 6: CW White Face");
			cw = true;
			break;
			case 7:
			System.out.println("Testing Case 7: CCW Green Face");
			cw = false;
			break;
			case 8:
			System.out.println("Testing Case 8: CCW Red Face");
			cw = false;
			break;
			case 9:
			System.out.println("Testing Case 9: CCW Blue Face");
			cw = false;
			break;
			case 10:
			System.out.println("Testing Case 10: CCW Orange Face");
			cw = false;
			break;
			case 11:
			System.out.println("Testing Case 11: CCW Yellow Face");
			cw = false;
			break;
			case 12:
			System.out.println("Testing Case 12: CCW White Face");
			cw = false;
			break;
		}
		x %= 6;
		String[][][] testCube = rotateCube(cubeColorCorners, cw, x);
		printCube(testCube);
		System.out.println();
		*/
	}

}

