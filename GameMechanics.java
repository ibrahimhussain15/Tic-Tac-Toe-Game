import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JLabel;

public class GameMechanics {
	
	public static String winner;
	
	public static boolean reset = false;

	public static int X;
	public static int Y;
	
	public static String difficulty;
	public static String[] gameBoard = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
	
	
	public static Rectangle cell0 = new Rectangle(100, 200, 100 , 100);
	public static Rectangle cell1 = new Rectangle(200, 200, 100 , 100);
	public static Rectangle cell2 = new Rectangle(300, 200, 100 , 100);
	public static Rectangle cell3 = new Rectangle(100, 300, 100 , 100);
	public static Rectangle cell4 = new Rectangle(200, 300, 100 , 100);
	public static Rectangle cell5 = new Rectangle(300, 300, 100 , 100);
	public static Rectangle cell6 = new Rectangle(100, 400, 100 , 100);
	public static Rectangle cell7 = new Rectangle(200, 400, 100 , 100);
	public static Rectangle cell8 = new Rectangle(300, 400, 100 , 100);
	
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
	
	public static void drawOval(int x, int y) {
		Graphics g = TicTacToe.panel.getGraphics(); 
		  
        g.setColor(Color.red); 
  
        
        g.fillOval(x, y, 50, 50);
        
        gameBoard[returnCell(X,Y)] = "O";
        for(int i =0; i < gameBoard.length; i++)
        System.out.print(gameBoard[i] + " ");
        System.out.println();
	
	}
	
	public static void drawX (int x, int y) {
	
		Graphics g = (Graphics2D)TicTacToe.panel.getGraphics(); 
		  
        g.setColor(Color.red); 
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        
        
        g.drawLine(x, y, x + 50, y + 50);
        g.drawLine(x + 50, y, x, y + 50);
        for(int i =0; i < gameBoard.length; i++)
            System.out.print(gameBoard[i] + " ");
		System.out.println();
	}
	
	public static int chooseComputerTurn() {
		if(difficulty.equals("easy")) {
			
			Random r = new Random();
			int result = r.nextInt(9);
			while(gameBoard[result].equalsIgnoreCase("X") || gameBoard[result].equalsIgnoreCase("O")  ) {
			result =  r.nextInt(9);
			continue;
			}
			return result;
		}
		else if(difficulty.equals("medium")) {

			Random r = new Random();
			int result = r.nextInt(9);
			while(gameBoard[result].equalsIgnoreCase("X") || gameBoard[result].equalsIgnoreCase("O")  ) {
			result =  r.nextInt(9);
			continue;
			}
			return result;
		}
		else {

			Random r = new Random();
			int result = r.nextInt(9);
			while(gameBoard[result].equalsIgnoreCase("X") || gameBoard[result].equalsIgnoreCase("O") ) {
			result =  r.nextInt(9);
			continue;
			}
			return result;
		}
	
	}
	
	public static void computerTurn() {
		
		if(checkForWinner().equalsIgnoreCase("User") || checkForWinner().equalsIgnoreCase("Computer") ) {
			declareWinner(checkForWinner());
			return;
		}
		
		int cellChosen = GameMechanics.chooseComputerTurn();
		GameMechanics.getCoordinates(cellChosen);
		GameMechanics.gameBoard[cellChosen] = "O";
		GameMechanics.drawOval(GameMechanics.X, GameMechanics.Y);
		if(checkForWinner().equalsIgnoreCase("User") || checkForWinner().equalsIgnoreCase("Computer") ) {
			declareWinner(checkForWinner());
			return;
		}
		TicTacToe.userTurn = false;
		
		
		
		
	}
	
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
        
        for (int a = 0; a < 8; a++) {
        	
        	if(gameBoard[a].equalsIgnoreCase("X") || gameBoard[a].equalsIgnoreCase("O"))
        		checkDraw += gameBoard[a];
        	else {
        		break;
        	}
        	if(a == 7)
        		return "draw";
        	
        	
        	
        }
        
        
      
        
        
        return "none";
    }
    
    public static void declareWinner(String s) {
    	
    	Graphics g = TicTacToe.panel.getGraphics(); 
		  
        g.setColor(Color.BLACK);
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

	
	


}
