import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;
import java.util.Enumeration; 

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
  
class TicTacToe extends JFrame implements MouseListener, ItemListener, ActionListener {
	
	//declare elements to be added to Frame
	public static JPanel panel;
	public Graphics g2;
	public JRadioButton easy;
	public JRadioButton medium;
	public JRadioButton hard;
	ButtonGroup difficultyGroup;
	public static boolean userTurn = true;
	public static boolean difficultySelected = false;
	public static JLabel thenPlay;
	public boolean resetable = false;
	public static Button reset;
	
	public TicTacToe() {
		
		//set title of app
		super("Tic Tac Toe");
		
		 panel = new JPanel() {
			
			public void paintComponent(Graphics g) {
				
				super.paintComponent(g);
				this.setBackground(Color.GRAY);
				Font title = new Font("TimesRoman", Font.BOLD, 15);
				g.setFont(title);
				g.setColor(Color.black);
				
				//prompt user to select difficulty
				g.drawString("Welcome to Tic Tac Toe! To start, select a difficulty below: ", 10, 30);
				g2 = (Graphics2D) g;
				
				//draw Tic Tac Toe Board
				g2.setColor(Color.BLACK);
				((Graphics2D) g2).setStroke(new BasicStroke(8));
				g2.drawLine(200, 200, 200, 500);
				g2.drawLine(300, 200, 300, 500);
				g2.drawLine(100, 300, 400, 300);
				g2.drawLine(100, 400, 400, 400);
				
				
				
			}
		};
		
		
		panel.setLayout(null);
		panel.addMouseListener(this);
		
		
		//"Play Again" button
		reset = new Button("Play Again");
		
		
		reset.setBounds(100, 625, 200, 30);
		reset.setFont(new Font("Verdana", Font.BOLD, 10));
		reset.setBackground(Color.gray);
		reset.addActionListener(this);
		
		
		//radio buttons for selecting difficulty
		ButtonGroup difficultyGroup = new ButtonGroup();
	
		easy = new JRadioButton("Easy");
		medium = new JRadioButton("Medium");
		hard = new JRadioButton("Hard");
		
		this.easy.setActionCommand("easy");
		this.medium.setActionCommand("medium");
		this.hard.setActionCommand("hard");
		
		easy.setBounds(5, 40, 100, 50);
		medium.setBounds(100, 40, 100, 50);
		hard.setBounds(200, 40, 100, 50);
		
		//add item listener to radio buttons
		easy.addItemListener(this);
		medium.addItemListener(this);
		hard.addItemListener(this);
		
		
		difficultyGroup.add(easy);
		difficultyGroup.add(medium);
	    difficultyGroup.add(hard);
		
	    //add buttons to panel
		panel.add(easy);
		panel.add(medium);
		panel.add(hard);
		
		
		this.show();
		
		
		this.add(panel);
		
		this.setSize(600, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	
	public static void main(String[] args) {
		
		TicTacToe t = new TicTacToe();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
		//check if game is over. if it is, display "Play Again" button at bottom of screen.
		if(GameMechanics.checkForWinner().equalsIgnoreCase("User") || GameMechanics.checkForWinner().equalsIgnoreCase("Computer") || GameMechanics.checkForWinner().equalsIgnoreCase("draw") ) {
			GameMechanics.declareWinner(GameMechanics.checkForWinner(), Color.black);
			panel.add(reset);
			return;
		}
	
		//check all of the following: difficulty has been selected, it is the user's turn, and the mouse was clicked within one of the open cells
		if(difficultySelected == true && userTurn == true && GameMechanics.returnCell(e.getX(), e.getY()) != -1 && !(GameMechanics.gameBoard[GameMechanics.returnCell(e.getX(), e.getY())].equalsIgnoreCase("O"))) {
		
		
				//get coordinates of mouse click, replace array index with "X", and draw an X on appropriate cell
				GameMechanics.getCoordinates(GameMechanics.returnCell(e.getX(), e.getY()));
				GameMechanics.gameBoard[GameMechanics.returnCell(e.getX(), e.getY())] = "X";
				GameMechanics.drawX(GameMechanics.X, GameMechanics.Y, Color.red);
				
				//check if game is over, if so, display winner and "Play Again" button
				if(GameMechanics.checkForWinner().equalsIgnoreCase("User") || GameMechanics.checkForWinner().equalsIgnoreCase("Computer") || GameMechanics.checkForWinner().equalsIgnoreCase("draw")) {
					GameMechanics.declareWinner(GameMechanics.checkForWinner(), Color.black);
					userTurn = false;
					panel.add(reset);
					return;
				}
				userTurn = false;
				
				//initiate Computer's turn. Sleep thread so it appears as if Computer is thinking.
				try {

					Graphics g = TicTacToe.panel.getGraphics(); 
					g.setColor(Color.BLACK);
					Font font = new Font("Verdana", Font.BOLD, 20);
			        g.setFont(font); 
			        g.drawString("Computer's turn...", 100, 600);
			        
					Thread.sleep(1000);
					g.setColor(Color.GRAY); 
					g.drawString("Computer's turn...", 100, 600);
					
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
					
				}
				GameMechanics.computerTurn();
				userTurn = true;
			}
		
		}
		
		
	

	@Override
	public void mouseReleased(MouseEvent e) {

		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		//if one of the radio buttons is selected, set difficultySelected to true so that game can begin. set difficulty to whichever difficulty was picked.
	    if (e.getStateChange() == ItemEvent.SELECTED) {
	    	
	       if(difficultySelected == false) {
	    	   
	    	   Graphics g = this.getGraphics();
	    	   g.setFont(new Font("TimesRoman", Font.BOLD, 15));
	    	   g.setColor(Color.black);
	    	   g.drawString("Great! Now play your turn: ", 10, 150);
	    	   
	    	   difficultySelected = true;
	    	   
	    	if(easy.isSelected()) {
	        	
	    		GameMechanics.difficulty = "easy";
	   
	        	
	        }
	    	
	        else if(medium.isSelected()) {
	        	
	        	GameMechanics.difficulty = "medium";

	        }
	    	
	        else {
	        	
	        	GameMechanics.difficulty = "hard";
	        	
	        }
	    }
	    }
	    
	    else if (e.getStateChange() == ItemEvent.DESELECTED) {
	        
	    }
	    
	}

	
	// if "Play Again" button is clicked, clear board and set userTurn to true so that user can start a new game
	@Override
	public void actionPerformed(ActionEvent e) {
		GameMechanics.clearBoard();
		userTurn = true;
		
	}
	}
	

	

  
   