package cmp;

public class Board {
	private int [][] grid = new int [3] [3];
	private int turn = 0;
	private int steps = 0;
	private int endOfGame = 0;
	private int draw = 0;
	
	public Board () {
		for (int i=0;i<3;i++) {
			for (int j=0;j<3; j++) {
				grid[i][j] = -1;
			}
		}
	}
	
	public int[][] getBoard () {
		return grid;
	}
	
	public boolean makeMove (int i, int j) {
		if (endOfGame==1) return false;
		if (grid[i][j]!=-1) return false;
		grid [i][j] = turn;
		turn = (turn+1)%2;
		steps++;
		return true;
	}
	
	public int getWinner () {
		//check on rows
		if (grid[0][0]==grid[0][1] && grid[0][1]==grid[0][2]) return grid[0][0];
		if (grid[1][0]==grid[1][1] && grid[1][1]==grid[1][2]) return grid[1][0];
		if (grid[2][0]==grid[2][1] && grid[2][1]==grid[2][2]) return grid[2][0];
		
		//check on cols
		if (grid[0][0]==grid[1][0] && grid[1][0]==grid[2][0]) return grid[0][0];
		if (grid[0][1]==grid[1][1] && grid[1][1]==grid[2][1]) return grid[0][1];
		if (grid[0][2]==grid[1][2] && grid[1][2]==grid[2][2]) return grid[0][2];
		
		//check on diagonals
		if (grid[0][0]==grid[1][1] && grid[1][1]==grid[2][2]) return grid[0][0];
		if (grid[2][0]==grid[1][1] && grid[1][1]==grid[0][2]) return grid[1][1];
		
		return -1;
	}
	
	public void setEndOfGame () {
		endOfGame = 1;
	}
	public int isEndOfGame () { 
		return endOfGame;
	}
	public int isDraw () {
		if (steps==9) return 1;
		return 0;
	}
}
