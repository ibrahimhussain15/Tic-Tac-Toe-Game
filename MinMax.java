
public class MinMax {


	 //class of object that contains two values, one for row and one for column
	static class Move { 
	     int row, column; 
	}
	
	//convert col and row values into a single cell within gameboard array
	  
	public static int convertMoveToInt(Move m) {
		if(m.row == 0 && m.column == 0)
			return 0;
		if(m.row == 0 && m.column == 1)
			return 1;
		if(m.row == 0 && m.column == 2)
			return 2;
		if(m.row == 1 && m.column == 0)
			return 3;
		if(m.row == 1 && m.column == 1)
			return 4;
		if(m.row == 1 && m.column == 2)
			return 5;
		if(m.row == 2 && m.column == 0)
			return 6;
		if(m.row == 2 && m.column == 1)
			return 7;
		if(m.row == 2 && m.column == 2)
			return 8;
		
		return 0;
	}
	  
	// returns true if there are moves remaining on the board. returns false if no moves left to play. 
	public static boolean movesLeft(String[][] b) { 
	    
			
			
			for(int i = 0; i < 3; i++) {
				
				for(int j = 0; j < 3; j++) {
					
					if(!b[i][j].equalsIgnoreCase("X") && !b[i][j].equalsIgnoreCase("O")) 
						return true;
					
					}
			}
	        
					
		    return false; 
	} 
	  
	
	public static int evaluate(String[][] b) { 
	    
		// check rows for victory
		
	    for (int row = 0; row < 3; row++) 
	    { 
	        if ((b[row][0].equalsIgnoreCase(b[row][1])) && 
	            (b[row][1].equalsIgnoreCase(b[row][2]))) 
	        { 
	            if (b[row][0].equalsIgnoreCase("x")) 
	                return +10; 
	            else if (b[row][0].equalsIgnoreCase("o")) 
	                return -10; 
	        } 
	    } 
	  
	    // check columns for victory
	    for (int col = 0; col<3; col++) 
	    { 
	        if ((b[0][col].equalsIgnoreCase(b[1][col])) && 
	            (b[1][col].equalsIgnoreCase(b[2][col]))) 
	        { 
	            if (b[0][col].equalsIgnoreCase("x")) 
	                return +10; 
	  
	            else if (b[0][col].equalsIgnoreCase("o")) 
	                return -10; 
	        } 
	    } 
	  
	    // check diagonals. 
	    if ((b[0][0].equalsIgnoreCase(b[1][1])) && (b[1][1].equalsIgnoreCase(b[2][2]))) 
	    { 
	        if (b[0][0].equalsIgnoreCase("x")) 
	            return 10; 
	        else if (b[0][0].equalsIgnoreCase("o")) 
	            return -10; 
	    } 
	  
	    if ((b[0][2].equalsIgnoreCase(b[1][1])) && (b[1][1].equalsIgnoreCase(b[2][0]))) 
	    { 
	        if (b[0][2].equalsIgnoreCase("x")) 
	            return +10; 
	        else if (b[0][2].equalsIgnoreCase("o")) 
	            return -10; 
	    } 
	  
	    // Else if none of them have won then return 0 
	    return 0; 
	} 
	  
	//minimax function, returns value of board after examining all possible outcomes
	static int minimax(String board[][], int depth, boolean isMaximum) { 
	    
		
		int score = evaluate(board); 
	  
	    // If Maximizer has won the game return his/her 
	    // evaluated score 
	    if (score == 10) 
	        return 20; 
	  
	    // If Minimizer has won the game return his/her 
	    // evaluated score 
	    if (score == -10) 
	        return -20; 
	  
	    // If there are no more moves and no winner then 
	    // it is a tie 
	    if (!movesLeft(board))
	        return 0; 
	  
	    // If maximizer's move 
	    if (isMaximum) 
	    { 
	        int best = -1000000; 
	  
	        // Traverse all cells 
	        for (int i = 0; i<3; i++) 
	        { 
	            for (int j = 0; j<3; j++) 
	            { 
	                // Check if cell is empty 
	                if (!(board[i][j].equalsIgnoreCase("X")
                            || board[i][j].equalsIgnoreCase("O")))
	                { 
	                    // Make the move 
	                    board[i][j] = "X"; 
	  
	                    // Call minimax recursively and choose 
	                    // the maximum value 
	                    best = Math.max( best, minimax(board, depth + 1, !isMaximum) ); 
	  
	                    // Undo the move 
	                    board[i][j] = "4"; 
	                } 
	            } 
	        } 
	        return best; 
	    } 
	  
	    // If minimizer's move 
	    else
	    { 
	        int best = 1000000; 
	  
	        // Traverse all cells 
	        for (int i = 0; i<3; i++) 
	        { 
	            for (int j = 0; j<3; j++) 
	            { 
	                // Check if cell is empty 
	                if (!(board[i][j].equalsIgnoreCase("X")
                            || board[i][j].equalsIgnoreCase("O")))
	                { 
	                    // Make the move 
	                    board[i][j] = "O"; 
	  
	                    // Call minimax recursively and choose 
	                    // the minimum value 
	                    best = Math.min(best, 
	                           minimax(board, depth+1, !isMaximum)); 
	  
	                    // Undo move 
	                    board[i][j] = "4"; 
	                } 
	            } 
	        } 
	        return best; 
	    } 
	} 
	  
	// This will return the best possible move for the player 
	 static Move findBestMove(String[][] board) { 
	    int bestVal = -1000; 
	    Move bestMove = new Move(); 
	    bestMove.row = -1; 
	    bestMove.column = -1; 
	  
	    // Traverse all cells, evaluate minimax function for 
	    // all empty cells. And return the cell with optimal 
	    // value. 
	    for (int i = 0; i < 3; i++) 
	    { 
	        for (int j = 0; j < 3; j++) 
	        { 
	            // Check if cell is empty 
	            if (!(board[i][j].equalsIgnoreCase("X")
                        || board[i][j].equalsIgnoreCase("O"))) {
	                // Make the move 
	                board[i][j] = "O"; 
	  
	                // compute evaluation function for this 
	                // move. 
	                int moveVal = minimax(board, 0, false); 
	  
	                // Undo the move 
	                board[i][j] = "4"; 
	  
	                // If the value of the current move is 
	                // more than the best value, then update 
	                // best 
	                if (moveVal > bestVal) { 
	                    bestMove.row = i; 
	                    bestMove.column = j; 
	                    bestVal = moveVal; 
	                } 
	            } 
	        } 
	    }
	   
	    return bestMove; 

	} 
	 
	}

	


