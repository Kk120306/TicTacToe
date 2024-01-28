
import java.util.*;
import java.io.*;

public class tictactoe {
	
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int x =0;
		do {
            System.out.print("Please enter size of the board (min 3): ");
            x = scanner.nextInt();
        } while (x < 3); // Used to repeat if size is lower than 3 
		
		char[][] array = new char[x][x]; //initializing the array 
		
		String player = "";
		
		
		
		while (!isFull(array)){ //makes sure that only happens when table is not full 
			
			for( int i =0; i< x*x; i++){
				if (i % 2 == 0){
					player = "O";
				}else{
					player = "X";
				} //switches between player X and O each turn 
				
				printBoard(array); // Print array 
				
				int row =0;
				int col = 0;
				
				do {
					System.out.println("Player " + player + " enter your move (row and column) (can't be above bounds):");
					row = scanner.nextInt();
					col = scanner.nextInt();
					scanner.nextLine();

					if (row > x || col > x || row < 1 || col < 1) {
						System.out.println("Invalid move. Re-enter valid row and column values:");
					}
				} while (row > x || col > x || row < 1 || col < 1);

				if (isBoxFull(array, row, col) != 0) {
					System.out.println("Column is already full. Re-enter a valid space:");
					i--; // Retry the same player's turn - makes sure that doesn't play for the other player.
				} else {
					if (player.equals("O")) {
						array[row - 1][col - 1] = 'O'; // Update array with player's move
					} else {
						array[row - 1][col - 1] = 'X'; // Update array with player's move
					}
				}
				
				for (int k =0; k < array.length; k++){
					if (winRow(array, player, k)){
						System.out.println("");
						printBoard(array);
						System.out.println("Player " + player + " won");
						System.exit(2);
					}else if (winCol(array, player, k)){
						System.out.println("");
						printBoard(array);
						System.out.println("Player " + player + " won");
						System.exit(2);
					}else if (winRL(array, player)){
						System.out.println("");
						printBoard(array);
						System.out.println("Player " + player + " won");
						System.exit(2);
					}else if (winLR(array, player)){
						System.out.println("");
						printBoard(array);
						System.out.println("Player " + player + " won");
						System.exit(2);
					}
				}
								
			}
		}
	}
	public static boolean isFull(char[][]array){
		for (int i =0; i < array.length; i++){
			for (int j =0; j < array.length; j++){
				if (array[i][j] == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	public static int isBoxFull(char[][]array, int row, int col){
		if (array[row-1][col-1] == 0){
					return 0;
		}
		return -1;
	}
	
	public static void printBoard(char[][] a) // n*(n+n) = O(n^2)
	{
		int rowLength = a.length;	// number of rows
		for(int row = 0; row < rowLength; row++)
        {
			int columnLength = a[row].length;	// number of columns in a row
			for(int col = 0; col < columnLength; col++)
			{
				System.out.print( a[row][col] );
				if(col < columnLength-1)
				{	System.out.print("|");
				}
			}
			System.out.println();
			if(row < rowLength-1)
			{	for(int i = 0; i < columnLength*2-1; i++)
				{	System.out.print("-");
				}
			}
			System.out.println();
        }
	}
	
	public static boolean winRow(char[][] array, String player, int row){
		if (player.equals("O")){
			for (int i =0; i< array.length; i++){
				if (array[row][i] != 'O'){
					return false;
				}
			}
		}else if (player.equals("X")){
			for (int j =0; j< array.length; j++){
				if (array[row][j] != 'X'){
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean winCol(char[][] array, String player, int col){
		if (player.equals("O")){
			for (int i =0; i< array.length; i++){
				if (array[i][col] != 'O'){
					return false;
				}
			}
		}else{
			for (int j =0; j< array.length; j++){
				if (array[j][col] != 'X'){
					return false;
				}
			}
		}
		return true;
	}
		
	
	public static boolean winRL(char[][]array, String player){
		if (player.equals("O")){
			for (int i =0; i< array.length; i++){
				if (array[i][i] != 'O'){
					return false;
				}
			}
		}else{
			for (int j =0; j< array.length; j++){
				if (array[j][j] != 'X'){
					return false;
				}
			}
		}
		return true; 		
	}
	
	public static boolean winLR(char[][]array, String player){
		if (player.equals("O")) {
			for (int i = 0; i < array.length; i++) {
				if (array[i][array.length - 1 - i] != 'O') {
                return false;
				}
			}
		} else {
			for (int j = 0; j < array.length; j++) {
				if (array[j][array.length - 1 - j] != 'X') {
                return false;
				}
			}
		}
		return true;
	}
}

