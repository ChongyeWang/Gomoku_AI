/**
 * Class used for minimax
 * @author Chongye Wang
 */
package Game;

public class MiniMaxEvaluate {
    public static int minimaxEvaluate(Piece[][] board, String targetMax){
    	
    	int score = 0;
    	
    	score = score + minimaxCheckWin(board, targetMax);
    	score = score + minimaxCheckNumFour(board, targetMax);
    	score = score + minimaxCheckNumThree(board, targetMax);
        score = score + minimaxCheckNumTwo(board, targetMax);
    	
    	String opponent = "";
    	if(targetMax.equals("black")){
    		opponent = "white";
    	}
    	else{
    		opponent = "black";
    	}
    	
    	for(int i = 0; i < 7; i++){
    		for(int j = 0; j < 7; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(targetMax)){
					if(i > 1 && i < 5 && j > 1 && j < 5){
						score += 8;
					}
					else{
						score += 4;
					}
    			}
    			else if(board[i][j] != null && board[i][j].getColor().equals(opponent)){
    				if(i > 1 && i < 5 && j > 1 && j < 5){
						score -= 8;
					}
					else{
						score -= 4;
					}
    			}
    		}
    	}
    	return score;
    }
    
    
    /**
     * Check continuing three
     * @param board
     * @param player
     * @return score
     */
    public static int minimaxCheckNumFour(Piece[][] board, String player){
    	String opponent = "";
    	if(player.equals("black")){
    		opponent = "white";
    	}
    	else{
    		opponent = "black";
    	}
    	int score = 0;
    	//horizontal
    	for(int i = 0; i <= 6; i++){
    		for(int j = 1; j <= 2; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i][j + 1] != null && board[i][j + 1].getColor().equals(player)
    				&& board[i][j + 2] != null && board[i][j + 2].getColor().equals(player)
    				&& board[i][j + 3] != null && board[i][j + 3].getColor().equals(player)
    				){
					if(board[i][j - 1] == null && board[i][j + 4] == null){
						score += 3000;
					}
    			}
    			else if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i][j + 1] != null && board[i][j + 1].getColor().equals(opponent)
    				&& board[i][j + 2] != null && board[i][j + 2].getColor().equals(opponent)
    				&& board[i][j + 3] != null && board[i][j + 3].getColor().equals(opponent)
    				){
					if(board[i][j - 1] == null && board[i][j + 4] == null){
						score -= 3000;
					}
    			}
    		}
    	}
    	
    	//Vertical
    	for(int i = 1; i <= 2; i++){
    		for(int j = 0; j <= 6; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i + 1][j] != null && board[i + 1][j].getColor().equals(player)
    				&& board[i + 2][j] != null && board[i + 2][j].getColor().equals(player)
    				&& board[i + 3][j] != null && board[i + 3][j].getColor().equals(player)
    				){
    				if(board[i - 1][j] == null && board[i + 4][j] == null){
    					score += 3000;
    				}
    			}
    			else if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i + 1][j] != null && board[i + 1][j].getColor().equals(opponent)
    				&& board[i + 2][j] != null && board[i + 2][j].getColor().equals(opponent)
    				&& board[i + 3][j] != null && board[i + 3][j].getColor().equals(opponent)
    				){
    				if(board[i - 1][j] == null && board[i + 4][j] == null){
    					score -= 3000;
    				}
    			}
    		}
    	}
    	
    	//Diagonal 1
    	for(int i = 1; i <= 2; i++){
    		for(int j = 1; j <= 2; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i + 1][j + 1] != null && board[i + 1][j + 1].getColor().equals(player)
    				&& board[i + 2][j + 2] != null && board[i + 2][j + 2].getColor().equals(player)
    				&& board[i + 3][j + 3] != null && board[i + 3][j + 3].getColor().equals(player)
    				){
					if(board[i - 1][j - 1] == null && board[i + 4][j + 4] == null){
						score += 3000;
					}
    			}
    			else if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i + 1][j + 1] != null && board[i + 1][j + 1].getColor().equals(opponent)
    				&& board[i + 2][j + 2] != null && board[i + 2][j + 2].getColor().equals(opponent)
    				&& board[i + 3][j + 3] != null && board[i + 3][j + 3].getColor().equals(opponent)
    				){
					if(board[i - 1][j - 1] == null && board[i + 4][j + 4] == null){
						score -= 3000;
					}
    			}
    		}
    	}
    	
    	//Diagonal 2
    	for(int i = 1; i <= 2; i++){
    		for(int j = 5; j >= 4; j--){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i + 1][j - 1] != null && board[i + 1][j - 1].getColor().equals(player)
    				&& board[i + 2][j - 2] != null && board[i + 2][j - 2].getColor().equals(player)
    				&& board[i + 3][j - 3] != null && board[i + 3][j - 3].getColor().equals(player)
    				){
    				if(board[i - 1][j + 1] == null && board[i + 4][j - 4] == null){
    					score += 3000;
					}
    			}
    			else if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i + 1][j - 1] != null && board[i + 1][j - 1].getColor().equals(opponent)
    				&& board[i + 2][j - 2] != null && board[i + 2][j - 2].getColor().equals(opponent)
    				&& board[i + 3][j - 3] != null && board[i + 3][j - 3].getColor().equals(opponent)
    				){
    				if(board[i - 1][j + 1] == null && board[i + 4][j - 4] == null){
    					score -= 3000;
					}
    			}
    		}
    	}
    	return score;
    }//end of check four
    
    
    /**
     * Check continuing two
     * @param board
     * @param player
     * @return
     */
    public static int minimaxCheckNumTwo(Piece[][] board, String player){
    	String opponent = "";
    	if(player.equals("black")){
    		opponent = "white";
    	}
    	else{
    		opponent = "black";
    	}
    	
    	int score = 0;
    	
    	//horizontal
    	for(int i = 0; i <= 6; i++){
    		for(int j = 1; j <= 4; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i][j + 1] != null && board[i][j + 1].getColor().equals(player)
    				){
					if(board[i][j - 1] == null && board[i][j + 2] == null){
						score += 50;
					}
    			}
    			else if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i][j + 1] != null && board[i][j + 1].getColor().equals(opponent)
    				){
					if(board[i][j - 1] == null && board[i][j + 2] == null){
						score -= 50;
					}
    			}
    		}
    	}
    	
    	//Vertical
    	for(int i = 1; i <= 4; i++){
    		for(int j = 0; j <= 6; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i + 1][j] != null && board[i + 1][j].getColor().equals(player)
    				){
    				if(board[i - 1][j] == null && board[i + 2][j] == null){
    					score += 50;
    				}
    			}
    			else if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i + 1][j] != null && board[i + 1][j].getColor().equals(opponent)
    				){
    				if(board[i - 1][j] == null && board[i + 2][j] == null){
    					score -= 50;
    				}
    			}
    		}
    	}
    	
    	//Diagonal 1
    	for(int i = 1; i <= 4; i++){
    		for(int j = 1; j <= 4; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i + 1][j + 1] != null && board[i + 1][j + 1].getColor().equals(player)
    				){
					if(board[i - 1][j - 1] == null && board[i + 2][j + 2] == null){
						score += 50;
					}
    			}
    			else if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i + 1][j + 1] != null && board[i + 1][j + 1].getColor().equals(opponent)
    				){
					if(board[i - 1][j - 1] == null && board[i + 2][j + 2] == null){
						score -= 50;
					}
    			}
    		}
    	}
    	
    	//Diagonal 2
    	for(int i = 1; i <= 4; i++){
    		for(int j = 5; j >= 2; j--){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i + 1][j - 1] != null && board[i + 1][j - 1].getColor().equals(player)
    				){
    				if(board[i - 1][j + 1] == null && board[i + 2][j - 2] == null){
    					score += 50;
					}
    			}
    			else if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i + 1][j - 1] != null && board[i + 1][j - 1].getColor().equals(opponent)
    				){
    				if(board[i - 1][j + 1] == null && board[i + 2][j - 2] == null){
    					score -= 50;
					}
    			}
    		}
    	}
    	return score;
    }
    
    /**
     * Check continuing three
     * @param board
     * @param player
     * @return score
     */
    public static int minimaxCheckNumThree(Piece[][] board, String player){
    	String opponent = "";
    	if(player.equals("black")){
    		opponent = "white";
    	}
    	else{
    		opponent = "black";
    	}
    	int score = 0;
    	//horizontal
    	for(int i = 0; i <= 6; i++){
    		for(int j = 1; j <= 3; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i][j + 1] != null && board[i][j + 1].getColor().equals(player)
    				&& board[i][j + 2] != null && board[i][j + 2].getColor().equals(player)
    				){
					if(board[i][j - 1] == null && board[i][j + 3] == null){
						score += 200;
					}
    			}
    			else if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i][j + 1] != null && board[i][j + 1].getColor().equals(opponent)
    				&& board[i][j + 2] != null && board[i][j + 2].getColor().equals(opponent)
    				){
					if(board[i][j - 1] == null && board[i][j + 3] == null){
						score -= 200;
					}
    			}
    		}
    	}
    	
    	//Vertical
    	for(int i = 1; i <= 3; i++){
    		for(int j = 0; j <= 6; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i + 1][j] != null && board[i + 1][j].getColor().equals(player)
    				&& board[i + 2][j] != null && board[i + 2][j].getColor().equals(player)
    				){
    				if(board[i - 1][j] == null && board[i + 3][j] == null){
    					score += 200;
    				}
    			}
    			else if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i + 1][j] != null && board[i + 1][j].getColor().equals(opponent)
    				&& board[i + 2][j] != null && board[i + 2][j].getColor().equals(opponent)
    				){
    				if(board[i - 1][j] == null && board[i + 3][j] == null){
    					score -= 200;
    				}
    			}
    		}
    	}
    	
    	//Diagonal 1
    	for(int i = 1; i <= 3; i++){
    		for(int j = 1; j <= 3; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i + 1][j + 1] != null && board[i + 1][j + 1].getColor().equals(player)
    				&& board[i + 2][j + 2] != null && board[i + 2][j + 2].getColor().equals(player)
    				){
					if(board[i - 1][j - 1] == null && board[i + 3][j + 3] == null){
						score += 200;
					}
    			}
    			else if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i + 1][j + 1] != null && board[i + 1][j + 1].getColor().equals(opponent)
    				&& board[i + 2][j + 2] != null && board[i + 2][j + 2].getColor().equals(opponent)
    				){
					if(board[i - 1][j - 1] == null && board[i + 3][j + 3] == null){
						score -= 200;
					}
    			}
    		}
    	}
    	
    	//Diagonal 2
    	for(int i = 1; i <= 3; i++){
    		for(int j = 5; j >= 3; j--){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i + 1][j - 1] != null && board[i + 1][j - 1].getColor().equals(player)
    				&& board[i + 2][j - 2] != null && board[i + 2][j - 2].getColor().equals(player)
    				){
    				if(board[i - 1][j + 1] == null && board[i + 3][j - 3] == null){
    					score += 200;
					}
    			}
    			else if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i + 1][j - 1] != null && board[i + 1][j - 1].getColor().equals(opponent)
    				&& board[i + 2][j - 2] != null && board[i + 2][j - 2].getColor().equals(opponent)
    				){
    				if(board[i - 1][j + 1] == null && board[i + 3][j - 3] == null){
    					score -= 200;
					}
    			}
    		}
    	}
    	return score;
    }
    
    /**
     * Check opponent
     * @param board
     * @param player
     * @return int
     */
    public static int minimaxCheckOpponent(Piece[][] board, String player){
    	String opponent = "";
    	if(player.equals("black")){
    		opponent = "white";
    	}
    	else{
    		opponent = "black";
    	}
    	
    	for(int i = 0; i <= 6; i++){
    		for(int j = 0; j <= 6; j++){
    			if(board[i][j] == null){
	    			board[i][j] = new Piece(j, i, opponent);
	    			int opponent_win = minimaxCheckWin(board, opponent);
	    			board[i][j] = null;
	    			if(Math.abs(opponent_win) == 10000){//opponent will win in this place
	    				if(player.equals("black")) return 5000;
	    				return -5000;
	    			}
    			}
    		    
    		}
    	}
    	return 0;
    }
    
    /**
     * Check if the playler has already won
     * if yes, return score 100000
     * @param board
     * @param player
     * @return int
     */
    public static int minimaxCheckWin(Piece[][] board, String player){
    	String opponent = "";
        if(player.equals("black")){
        	opponent = "white";
        }
        else{
        	opponent = "black";
        }
    	//horizontal
    	for(int i = 0; i <= 6; i++){
    		for(int j = 0; j <= 2; j++){
    			
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i][j + 1] != null && board[i][j + 1].getColor().equals(player)
    				&& board[i][j + 2] != null && board[i][j + 2].getColor().equals(player)
    				&& board[i][j + 3] != null && board[i][j + 3].getColor().equals(player)
    				&& board[i][j + 4] != null && board[i][j + 4].getColor().equals(player)
    				){
    				return 100000;
    			}
    			else if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i][j + 1] != null && board[i][j + 1].getColor().equals(opponent)
    				&& board[i][j + 2] != null && board[i][j + 2].getColor().equals(opponent)
    				&& board[i][j + 3] != null && board[i][j + 3].getColor().equals(opponent)
    				&& board[i][j + 4] != null && board[i][j + 4].getColor().equals(opponent)
    				){
    				return -100000;
    			}
    		}
    	}
    	
    	//Vertical
    	for(int i = 0; i <= 2; i++){
    		for(int j = 0; j <= 6; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i + 1][j] != null && board[i + 1][j].getColor().equals(player)
    				&& board[i + 2][j] != null && board[i + 2][j].getColor().equals(player)
    				&& board[i + 3][j] != null && board[i + 3][j].getColor().equals(player)
    				&& board[i + 4][j] != null && board[i + 4][j].getColor().equals(player)
    				){
                    return 100000;
    			}
    			else if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i + 1][j] != null && board[i + 1][j].getColor().equals(opponent)
    				&& board[i + 2][j] != null && board[i + 2][j].getColor().equals(opponent)
    				&& board[i + 3][j] != null && board[i + 3][j].getColor().equals(opponent)
    				&& board[i + 4][j] != null && board[i + 4][j].getColor().equals(opponent)
    				){
    				return -100000;
    			}
    		}
    	}
    	
    	//Diagonal 1
    	for(int i = 0; i <= 2; i++){
    		for(int j = 0; j <= 2; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i + 1][j + 1] != null && board[i + 1][j + 1].getColor().equals(player)
    				&& board[i + 2][j + 2] != null && board[i + 2][j + 2].getColor().equals(player)
    				&& board[i + 3][j + 3] != null && board[i + 3][j + 3].getColor().equals(player)
    				&& board[i + 4][j + 4] != null && board[i + 4][j + 4].getColor().equals(player)
    				){
    				return 100000;
    			}
    			else if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i + 1][j + 1] != null && board[i + 1][j + 1].getColor().equals(opponent)
    				&& board[i + 2][j + 2] != null && board[i + 2][j + 2].getColor().equals(opponent)
    				&& board[i + 3][j + 3] != null && board[i + 3][j + 3].getColor().equals(opponent)
    				&& board[i + 4][j + 4] != null && board[i + 4][j + 4].getColor().equals(opponent)
    				){
    				return -100000;
    			}

    		}
    	}
    	
    	//Diagonal 2
    	for(int i = 0; i <= 2; i++){
    		for(int j = 6; j >= 4; j--){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i + 1][j - 1] != null && board[i + 1][j - 1].getColor().equals(player)
    				&& board[i + 2][j - 2] != null && board[i + 2][j - 2].getColor().equals(player)
    				&& board[i + 3][j - 3] != null && board[i + 3][j - 3].getColor().equals(player)
    				&& board[i + 4][j - 4] != null && board[i + 4][j - 4].getColor().equals(player)
    				){
    				return 100000;
    			}
    			else if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i + 1][j - 1] != null && board[i + 1][j - 1].getColor().equals(opponent)
    				&& board[i + 2][j - 2] != null && board[i + 2][j - 2].getColor().equals(opponent)
    				&& board[i + 3][j - 3] != null && board[i + 3][j - 3].getColor().equals(opponent)
    				&& board[i + 4][j - 4] != null && board[i + 4][j - 4].getColor().equals(opponent)
    				){
    				return -100000;
    			}

    		}
    	}
    	return 0;	
    }
}
