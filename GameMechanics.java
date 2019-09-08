import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JLabel;

public class GameMechanics {
	
	
	//String that displays who won the game
	public static String winner;
	
	public static boolean reset = false;

	//coordinates
	public static int X;
	public static int Y;
	
	//stores difficulty level selected by user
	public static String difficulty;
	
	//create game board to store values of each cell
	public static String[] gameBoard = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
	
	//declare cells of Tic Tac Toe
	public static Rectangle cell0 = new Rectangle(100, 200, 100, 100);
	public static Rectangle cell1 = new Rectangle(200, 200, 100, 100);
	public static Rectangle cell2 = new Rectangle(300, 200, 100, 100);
	public static Rectangle cell3 = new Rectangle(100, 300, 100, 100);
	public static Rectangle cell4 = new Rectangle(200, 300, 100, 100);
	public static Rectangle cell5 = new Rectangle(300, 300, 100, 100);
	public static Rectangle cell6 = new Rectangle(100, 400, 100, 100);
	public static Rectangle cell7 = new Rectangle(200, 400, 100, 100);
	public static Rectangle cell8 = new Rectangle(300, 400, 100, 100);
	
	//function that takes in coordinates of mouse press and returns corresponding cell
	public static int returnCell(int x, int y) {
		if(cell0.contains(x,y))
			return 0;
		if(cell1.contains(x,y))
			return 1;
		if(cell2.contains(x,y))
			return 2;
		if(cell3.contains(x,y))
			return 3;
		if(cell4.contains(x,y))
			return 4;
		if(cell5.contains(x,y))
			return 5;
		if(cell6.contains(x,y))
			return 6;
		if(cell7.contains(x,y))
			return 7;
		if(cell8.contains(x,y))
			return 8;
		
		return -1;
		
	}
	
	//takes in cell number and assigns coordinates to X and Y points for where to draw X's and O's
	public static void getCoordinates(int n) {
		switch (n) {
		case 0: 
			X = 125;
			Y = 225;
			break;
		case 1: 
			X = 225;
			Y = 225;
			break;
		case 2: 
			X = 325;
			Y = 225;
			break;
		case 3: 
			X = 125;
			Y = 325;
			break;
		case 4: 
			X = 225;
			Y = 325;
			break;
		case 5: 
			X = 325;
			Y = 325;
			break;
		case 6: 
			X = 125;
			Y = 425;
			break;
		case 7: 
			X = 225;
			Y = 425;
			break;
		case 8: 
			X = 325;
			Y = 425;
			break;
		
		
		}
		
		
	}
	
	//Draws O using Graphics object
	public static void drawOval(int x, int y, Color color) {
		Graphics g = TicTacToe.panel.getGraphics(); 
		  
        g.setColor(color); 
  
        
        g.fillOval(x, y, 50, 50);
        
        gameBoard[returnCell(X,Y)] = "O";
        
        //print to console to ensure that game board array accurately reflects GUI
        for(int i =0; i < gameBoard.length; i++)
        System.out.print(gameBoard[i] + " ");
		
	
	}
	
	//Draws X using Graphics object
	public static void drawX (int x, int y, Color color) {
	
		Graphics g = (Graphics2D)TicTacToe.panel.getGraphics(); 
		  
        g.setColor(color); 
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        
        
        g.drawLine(x, y, x + 50, y + 50);
        g.drawLine(x + 50, y, x, y + 50);
        
        //print to console to ensure game board array accurately reflects GUI
        for(int i =0; i < gameBoard.length; i++)
            System.out.print(gameBoard[i] + " ");
        
	}
	
	//CPU picks a spot to draw O, depending on difficulty selected
	public static int chooseComputerTurn() {
		
		//easy mode - choose any random spot to place O
		if(difficulty.equals("easy")) {
			
			Random r = new Random();
			int result = r.nextInt(9);
			while(gameBoard[result].equalsIgnoreCase("X") || gameBoard[result].equalsIgnoreCase("O")  ) {
			result =  r.nextInt(9);
			continue;
			}
			return result;
		}
		
		//medium mode - somewhat calculated approach, not 100% fool proof
		else if(difficulty.equals("medium")) {

			if( !(gameBoard[4].equalsIgnoreCase("X")) && !(gameBoard[4].equalsIgnoreCase("O")) ) {
				return 4;
			}
			else if(!(gameBoard[8].equalsIgnoreCase("X")) && !(gameBoard[8].equalsIgnoreCase("O"))) {
				
				return 8;
				
			}
			else if(!(gameBoard[0].equalsIgnoreCase("X")) && !(gameBoard[0].equalsIgnoreCase("O"))) {
				
				return 0;
				
			}
			else if(!(gameBoard[2].equalsIgnoreCase("X")) && !(gameBoard[2].equalsIgnoreCase("O"))) {
				
				return 2;
				
			}
			else if(!(gameBoard[6].equalsIgnoreCase("X")) && !(gameBoard[6].equalsIgnoreCase("O"))) {
				
				return 6;
				
			}
			
			else {

				Random r = new Random();
				int result = r.nextInt(9);
				while(gameBoard[result].equalsIgnoreCase("X") || gameBoard[result].equalsIgnoreCase("O")  ) {
				result =  r.nextInt(9);
				continue;
				}
				return result;	
			
			}
		}
		
		//hard mode - use MinMax algorithm to determine best move 
		else {
			String [][]b = {{GameMechanics.gameBoard[0],GameMechanics.gameBoard[1],GameMechanics.gameBoard[2]},{GameMechanics.gameBoard[3],GameMechanics.gameBoard[4],GameMechanics.gameBoard[5]},{GameMechanics.gameBoard[6],GameMechanics.gameBoard[7],GameMechanics.gameBoard[8]}};
			System.out.println("\nCol: " + MinMax.findBestMove(b).column + "\tRow: " + MinMax.findBestMove(b).row);
			int moveToMake = MinMax.convertMoveToInt(MinMax.findBestMove(b));
			
			return moveToMake;
			
			
			
			
			
			
		}
	
	}
	
	
    // Draws O on cell chosen by chooseComputerTurn() method
	// Checks if winner has been determined
	// sets userTurn to false
	public static void computerTurn() {
		
		
		//check winner and display "play again" button if game is over
		if(checkForWinner().equalsIgnoreCase("User") || checkForWinner().equalsIgnoreCase("Computer")|| checkForWinner().equalsIgnoreCase("draw") ) {
			declareWinner(checkForWinner(), Color.black);
			TicTacToe.panel.add(TicTacToe.reset);
			return;
		}
		
		//invoke chooseComputerTurn() to select next move
		int cellChosen = GameMechanics.chooseComputerTurn();
		
		//get coordinates of cell chosen by chooseComputerTurn()
		GameMechanics.getCoordinates(cellChosen);
		
		//replace array index with O
		GameMechanics.gameBoard[cellChosen] = "O";
		
		//draw an O in the GUI Game Board
		GameMechanics.drawOval(GameMechanics.X, GameMechanics.Y, Color.red);
		
		//2D array representation of game board (for use in MinMax algorithm)
		String [][]b = {{GameMechanics.gameBoard[0],GameMechanics.gameBoard[1],GameMechanics.gameBoard[2]},{GameMechanics.gameBoard[3],GameMechanics.gameBoard[4],GameMechanics.gameBoard[5]},{GameMechanics.gameBoard[6],GameMechanics.gameBoard[7],GameMechanics.gameBoard[8]}};
		MinMax.convertMoveToInt(MinMax.findBestMove(b));
		
		//check if game is over, if so display winner 
		if(checkForWinner().equalsIgnoreCase("User") || checkForWinner().equalsIgnoreCase("Computer")|| checkForWinner().equalsIgnoreCase("draw") ) {
			declareWinner(checkForWinner(), Color.black);
			TicTacToe.panel.add(TicTacToe.reset);
			return;
		}
		
		//set userTurn to false
		
		TicTacToe.userTurn = false;
		
		
		
		
	}
	
	//the following function analyzez game board array and checks if the game has been won by either side
    public static String checkForWinner() {
        for (int a = 0; a < 8; a++) {
            String combo = "";
            switch (a) {
                case 0:
                    combo = gameBoard[0] + gameBoard[1] + gameBoard[2];
                    break;
                case 1:
                	combo = gameBoard[3] + gameBoard[4] + gameBoard[5];
                    break;
                case 2:
                	combo = gameBoard[6] + gameBoard[7] + gameBoard[8];
                    break;
                case 3:
                	combo = gameBoard[0] + gameBoard[3] + gameBoard[6];
                    break;
                case 4:
                	combo = gameBoard[1] + gameBoard[4] + gameBoard[7];
                    break;
                case 5:
                	combo = gameBoard[2] + gameBoard[5] + gameBoard[8];
                    break;
                case 6:
                	combo = gameBoard[0] + gameBoard[4] + gameBoard[8];
                    break;
                case 7:
                	combo = gameBoard[2] + gameBoard[4] + gameBoard[6];
                    break;
            }
            if (combo.equals("XXX")) {
                return "User";
            } else if (combo.equals("OOO")) {
                return "Computer";
            }
        }
        String checkDraw = "";
        
        //checks if it was a draw
        for (int a = 0; a < 8; a++) {
        	
        	if(gameBoard[a].equalsIgnoreCase("X") || gameBoard[a].equalsIgnoreCase("O"))
        		checkDraw += gameBoard[a];
        	else {
        		break;
        	}
        	if(a == 7)
        		return "draw";
        	
        	
        	
        }
        
        
      
        
        //otherwise, return none to indicate game is not over
        return "none";
    }
    
    //following function takes in result of checkForWinner() and displays a string to the GUI that declares outcome of match
    public static void declareWinner(String s, Color color) {
    	
    	Graphics g = TicTacToe.panel.getGraphics(); 
		  
        g.setColor(color);
        Font font = new Font("Verdana", Font.BOLD, 20);
        g.setFont(font);
    	if(s.equalsIgnoreCase("User")) {
    		winner = "You Win!";
    		g.drawString(winner, 100, 600);
    	}
    	if(s.equalsIgnoreCase("Computer")) {
    		winner = "You Lose, Computer Wins.";
    		g.drawString(winner, 100, 600);
    	}
    	if(s.equalsIgnoreCase("draw")) {
    		winner = "Draw!";
    		g.drawString(winner, 100, 600);
    	}
    }

    //clears the board of all X's and O's so that user can play again
	public static void clearBoard() {
		
		//clear all X's and O's
		
		Graphics g = TicTacToe.panel.getGraphics();
		
		declareWinner(checkForWinner(), Color.gray);
		
		for(int i = 0; i < gameBoard.length; i++) {
			
			getCoordinates(i);
			if(gameBoard[i].equalsIgnoreCase("x")) 
				drawX(GameMechanics.X, GameMechanics.Y, Color.gray);
			if(gameBoard[i].equalsIgnoreCase("o"))
				drawOval(GameMechanics.X, GameMechanics.Y, Color.gray);
			gameBoard[i]= String.valueOf(i);
			
		}
		TicTacToe.panel.remove(TicTacToe.reset);
		
		
	}
    

	
	


}
