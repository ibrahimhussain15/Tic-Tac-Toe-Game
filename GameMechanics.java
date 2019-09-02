import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class GameMechanics {

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
		
		int cellChosen = GameMechanics.chooseComputerTurn();
		GameMechanics.getCoordinates(cellChosen);
		GameMechanics.gameBoard[cellChosen] = "O";
		GameMechanics.drawOval(GameMechanics.X, GameMechanics.Y);
		
		
		
		
	}
	
	


}
