import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;
import java.util.Enumeration; 

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
  
class TicTacToe extends JFrame implements MouseListener, ItemListener {
	public static JPanel panel;
	public Graphics g2;
	public JRadioButton easy;
	public JRadioButton medium;
	public JRadioButton hard;
	ButtonGroup difficultyGroup;
	public static boolean userTurn = true;
	public static boolean difficultySelected = false;
	
	public TicTacToe() {
		super("Tic Tac Toe");
		
		 panel = new JPanel() {
			
			public void paintComponent(Graphics g) {
				
				super.paintComponent(g);
				this.setBackground(Color.GRAY);
				g2 = (Graphics2D) g;
				
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
		
		JLabel setDifficulty = new JLabel("Set Difficulty: ");
		setDifficulty.setBounds(5, 0, 100, 50);
		
		panel.add(setDifficulty);
		
		ButtonGroup difficultyGroup = new ButtonGroup();
	
		easy = new JRadioButton("Easy");
		medium = new JRadioButton("Medium");
		hard = new JRadioButton("Hard");
		
		this.easy.setActionCommand("easy");
		this.medium.setActionCommand("medium");
		this.hard.setActionCommand("hard");
		
		easy.setBounds(0, 20, 100, 50);
		medium.setBounds(100, 20, 100, 50);
		hard.setBounds(200, 20, 100, 50);
		
		easy.addItemListener(this);
		medium.addItemListener(this);
		hard.addItemListener(this);
		
		
		difficultyGroup.add(easy);
		difficultyGroup.add(medium);
	    difficultyGroup.add(hard);
		
//		easy.setSelected(true);
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	if(difficultySelected == true) {	
		if(userTurn == true) {
			
			if(GameMechanics.returnCell(e.getX(), e.getY()) != -1) {
			
				GameMechanics.getCoordinates(GameMechanics.returnCell(e.getX(), e.getY()));
				GameMechanics.gameBoard[GameMechanics.returnCell(e.getX(), e.getY())] = "X";
				GameMechanics.drawX(GameMechanics.X, GameMechanics.Y);
				userTurn = false;
				try {
					Thread.sleep(1200);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				GameMechanics.computerTurn();
			}
		
			}
		
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
	    if (e.getStateChange() == ItemEvent.SELECTED) {
	       if(difficultySelected == false) {
	    	
	    	if(easy.isSelected()) {
	        	
	    		GameMechanics.difficulty = "easy";
	    		difficultySelected = true;
	        	
	        	
	        }
	        else if(medium.isSelected()) {
	        	
	        	GameMechanics.difficulty = "medium";
	        	difficultySelected = true;
//	        	
	        	
	        }
	        else {
	        	
	        	GameMechanics.difficulty = "hard";
	        	difficultySelected = true;
	        	
	        	
	        }
	    }
	    }
	    
	    else if (e.getStateChange() == ItemEvent.DESELECTED) {
	        
	    }
	    
	}
	}
	
//	public String getSelectedButtonText(ButtonGroup buttonGroup) {
//        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
//            AbstractButton button = buttons.nextElement();
//
//            if (button.isSelected()) {
//                return button.getText();
//            }
//        }
//
//        return null;
//    }
	
	

  
   