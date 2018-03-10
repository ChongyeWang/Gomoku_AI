/**
 * The main class of Gomoku(Five in a row)
 * @author Chongye Wang
 */
package Game;
import java.lang.*;

public class Game {
    private boolean blackTurn;
    private String winner;
    private boolean gameEnd;
    public Piece[][] board = new Piece[13][13];
    
    private static int minimax_count = 0;//node expanded for minimax
    private static int alphabeta_count = 0;//node expanded for alphabeta
    
    /**
     * Game constructor
     */
    public Game(){
    	this.blackTurn = true;
    	this.gameEnd = false;
    }
    
    
    /**
     * Copy constructor
     * @param other
     */
    public Game(Game other){
    	this.blackTurn = other.blackTurn;
    	this.winner = other.winner;
    	this.gameEnd = other.gameEnd;
    	for(int i = 0; i < 13; i++){
    		for(int j = 0; j < 13; j++){
    			this.board[i][j] = other.board[i][j];
    		}
    	}
    }
    
    
    /**
     * Move the piece
     * @param x
     * @param y
     */
    public void movePiece(int x, int y){
    	if(x < 0 || y < 0 || x > 12 || y > 12){
    		return;
    	}//out of bound
        if(board[y][x] != null){
        	return;
        }//already occupied
        
        if(blackTurn == true){
            board[y][x] = new Piece(x, y, "black");
        }
        else{
        	board[y][x] = new Piece(x, y, "white");
        }
        blackTurn = !blackTurn;
        if(checkWinner()){
        	this.gameEnd = true;
        }
    }
    
    
    /**
     * Get the turn
     * @return blackTurn
     */
    public boolean getblackTurn(){
    	return this.blackTurn;
    }
    
    
    /**
     * Get the gameEnd
     * @return gameEnd
     */
    public boolean getgameEnds(){
    	return this.gameEnd;
    }
    
    
    /**
     * Get the winner of the game
     * @return winner
     */
    public String getWinner(){
    	return this.winner;
    }
    
    
    /**
     * Check if a player has already won the game.
     * @return if there is a winner
     */
    public boolean checkWinner(){
    	//horizontal
    	for(int i = 0; i <= 12; i++){
    		for(int j = 0; j <= 8; j++){
    			
    			if(board[i][j] != null && board[i][j].getColor().equals("black") 
    				&& board[i][j + 1] != null && board[i][j + 1].getColor().equals("black")
    				&& board[i][j + 2] != null && board[i][j + 2].getColor().equals("black")
    				&& board[i][j + 3] != null && board[i][j + 3].getColor().equals("black")
    				&& board[i][j + 4] != null && board[i][j + 4].getColor().equals("black")
    				){
    				this.winner = "black";
    				return true;
    			}
    			if(board[i][j] != null && board[i][j].getColor().equals("white") 
    				&& board[i][j + 1] != null && board[i][j + 1].getColor().equals("white")
    				&& board[i][j + 2] != null && board[i][j + 2].getColor().equals("white")
    				&& board[i][j + 3] != null && board[i][j + 3].getColor().equals("white")
    				&& board[i][j + 4] != null && board[i][j + 4].getColor().equals("white")
    				){
    				this.winner = "white";
    				return true;
    			}
    		}
    	}
    	
    	//Vertical
    	for(int i = 0; i <= 8; i++){
    		for(int j = 0; j <= 12; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals("black") 
    				&& board[i + 1][j] != null && board[i + 1][j].getColor().equals("black")
    				&& board[i + 2][j] != null && board[i + 2][j].getColor().equals("black")
    				&& board[i + 3][j] != null && board[i + 3][j].getColor().equals("black")
    				&& board[i + 4][j] != null && board[i + 4][j].getColor().equals("black")
    				){
    				this.winner = "black";
    				return true;
    			}
    			if(board[i][j] != null && board[i][j].getColor().equals("white") 
    				&& board[i + 1][j] != null && board[i + 1][j].getColor().equals("white")
    				&& board[i + 2][j] != null && board[i + 2][j].getColor().equals("white")
    				&& board[i + 3][j] != null && board[i + 3][j].getColor().equals("white")
    				&& board[i + 4][j] != null && board[i + 4][j].getColor().equals("white")
    				){
    				this.winner = "white";
    				return true;
    			}
    		}
    	}
    	
    	//Diagonal 1
    	for(int i = 0; i <= 8; i++){
    		for(int j = 0; j <= 8; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals("black") 
    				&& board[i + 1][j + 1] != null && board[i + 1][j + 1].getColor().equals("black")
    				&& board[i + 2][j + 2] != null && board[i + 2][j + 2].getColor().equals("black")
    				&& board[i + 3][j + 3] != null && board[i + 3][j + 3].getColor().equals("black")
    				&& board[i + 4][j + 4] != null && board[i + 4][j + 4].getColor().equals("black")
    				){
    				this.winner = "black";
    				return true;
    			}
    			if(board[i][j] != null && board[i][j].getColor().equals("white") 
    				&& board[i + 1][j + 1] != null && board[i + 1][j + 1].getColor().equals("white")
    				&& board[i + 2][j + 2] != null && board[i + 2][j + 2].getColor().equals("white")
    				&& board[i + 3][j + 3] != null && board[i + 3][j + 3].getColor().equals("white")
    				&& board[i + 4][j + 4] != null && board[i + 4][j + 4].getColor().equals("white")
    				){
    				this.winner = "white";
    				return true;
    			}
    		}
    	}
    	
    	//Diagonal 2
    	for(int i = 0; i <= 8; i++){
    		for(int j = 12; j >= 4; j--){
    			if(board[i][j] != null && board[i][j].getColor().equals("black") 
    				&& board[i + 1][j - 1] != null && board[i + 1][j - 1].getColor().equals("black")
    				&& board[i + 2][j - 2] != null && board[i + 2][j - 2].getColor().equals("black")
    				&& board[i + 3][j - 3] != null && board[i + 3][j - 3].getColor().equals("black")
    				&& board[i + 4][j - 4] != null && board[i + 4][j - 4].getColor().equals("black")
    				){
    				this.winner = "black";
    				return true;
    			}
    			if(board[i][j] != null && board[i][j].getColor().equals("white") 
    				&& board[i + 1][j - 1] != null && board[i + 1][j - 1].getColor().equals("white")
    				&& board[i + 2][j - 2] != null && board[i + 2][j - 2].getColor().equals("white")
    				&& board[i + 3][j - 3] != null && board[i + 3][j - 3].getColor().equals("white")
    				&& board[i + 4][j - 4] != null && board[i + 4][j - 4].getColor().equals("white")
    				){
    				this.winner = "white";
    				return true;
    			}
    		}
    	}
    	
    	return false;
    	
    }// end of checkWinner method
    
    
    /**
     * Reflex Agent move function, which is used for
     * AI to decide which step to move.
     */
    public void reflexAgentMove(){
    	if(this.getgameEnds()){
    		return;
    	}
    	
    	//Get the current turn 
    	String currentPlayer = "";
    	if(this.getblackTurn()){
    		currentPlayer = "black";
    	}
    	else{
    		currentPlayer = "white";
    	}
    	
    	//Check first move
    	boolean blackEmpty = true;
    	boolean whiteEmpty = true;
    	for(int i = 0; i < 7; i++){
    		for(int j = 0; j < 7; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals("black")){
    				blackEmpty = false;
    			}
    			if(board[i][j] != null && board[i][j].getColor().equals("white")){
    				whiteEmpty = false;
    			}
    		}
    	}
    	
		if(currentPlayer.equals("black")){
			if(blackEmpty == true){
				if(board[3][3] == null)
			        this.movePiece(3, 3);
				else{
					this.movePiece(4, 4);
				}
			    return;
			}
	
		}
		else if(currentPlayer.equals("white")){
			if(whiteEmpty == true){
				if(board[3][3] == null)
			        this.movePiece(3, 3);
				else{
					this.movePiece(4, 4);
				}
			    return;
			}
		}
    
    	
    	//Check reflex agent rule one
    	int[][] reflex1 = reflexAgentRuleOne(currentPlayer);
    	for(int column = 0; column < 7; column++){
    		for(int row = 0; row < 7; row++){
    			if(reflex1[row][column] == 1){
    				this.movePiece(column, row);
    				return;
    			}
    		}
    	}
    	
    	//Check reflex agent rule two
    	int[] reflex2 = reflexAgentRuleTwo(currentPlayer);
    	if(reflex2[0] != -1 && reflex2[1] != -1){
    		this.movePiece(reflex2[0], reflex2[1]);
    		return;
    	}
    	
    	//Check reflex agent rule three
    	int[][] reflex3 = reflexAgentRuleThree(currentPlayer);
    	for(int column = 0; column < 7; column++){
    		for(int row = 0; row < 7; row++){
    			if(reflex3[row][column] == 1){
    				this.movePiece(column, row);
    				return;
    			}
    		}
    	}
    	
    	//Check reflex agent rule four
    	int[] reflex4 = reflexAgentRuleFour(currentPlayer);
    	if(reflex4[0] != 100 && reflex4[1] != -100){
    		this.movePiece(reflex4[0], reflex4[1]);
    		return;
    	}
    	
    }//end of reflexAgentMove
    
    
    /**
     * This method implements the reflex rule one.
     * Check whether the agent side is going to win by placing just one more stone. 
     * If so, place the stone which wins the game.
     * @param currentPlayer
     * @return result
     */
    public int[][] reflexAgentRuleOne(String currentPlayer){
    	int[][] result = new int[13][13];
    	for(int i = 0; i <= 6; i++){
    		for(int j = 0; j <= 6; j++){
    			if(this.board[i][j] == null){
    				board[i][j] = new Piece(j, i, currentPlayer);
    				if(this.checkWinner()){
    					result[i][j] = 1;
    				}
    				board[i][j] = null;
    				this.winner = "";//Set values back
    			}
    		}
    	}
    	return result;
    	/*
    	//horizontal
    	for(int i = 0; i <= 12; i++){
    		for(int j = 0; j <= 9; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(currentPlayer) 
    				&& board[i][j + 1] != null && board[i][j + 1].getColor().equals(currentPlayer)
    				&& board[i][j + 2] != null && board[i][j + 2].getColor().equals(currentPlayer)
    				&& board[i][j + 3] != null && board[i][j + 3].getColor().equals(currentPlayer)
    				){
    				
					if(j - 1 >= 0 && board[i][j - 1] == null){
						result[i][j - 1] = 1;
					}
					if(j + 4 <= 12 && board[i][j + 4] == null){
						result[i][j + 4] = 1;
					}
    			}
    		}
    	}
    	
    	//Vertical
    	for(int i = 0; i <= 9; i++){
    		for(int j = 0; j <= 12; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(currentPlayer) 
    				&& board[i + 1][j] != null && board[i + 1][j].getColor().equals(currentPlayer)
    				&& board[i + 2][j] != null && board[i + 2][j].getColor().equals(currentPlayer)
    				&& board[i + 3][j] != null && board[i + 3][j].getColor().equals(currentPlayer)
    				){
    				if(i - 1 >= 0 && board[i - 1][j] == null){
    					result[i - 1][j] = 1;
    				}
    				if(i + 4 <= 12 && board[i + 4][j] == null){
    					result[i + 4][j] = 1;
    				}
    			}
    		}
    	}
    	
    	//Diagonal 1
    	for(int i = 0; i <= 9; i++){
    		for(int j = 0; j <= 9; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(currentPlayer) 
    				&& board[i + 1][j + 1] != null && board[i + 1][j + 1].getColor().equals(currentPlayer)
    				&& board[i + 2][j + 2] != null && board[i + 2][j + 2].getColor().equals(currentPlayer)
    				&& board[i + 3][j + 3] != null && board[i + 3][j + 3].getColor().equals(currentPlayer)
    				){
					if(i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1] == null){
						result[i - 1][j - 1] = 1;
					}
					if(i + 4 <= 12 && j + 4 <= 12 && board[i + 4][j + 4] == null){
						result[i + 4][j + 4] = 1;
					}
    			}
    		}
    	}
    	
    	//Diagonal 2
    	for(int i = 0; i <= 9; i++){
    		for(int j = 12; j >= 3; j--){
    			if(board[i][j] != null && board[i][j].getColor().equals(currentPlayer) 
    				&& board[i + 1][j - 1] != null && board[i + 1][j - 1].getColor().equals(currentPlayer)
    				&& board[i + 2][j - 2] != null && board[i + 2][j - 2].getColor().equals(currentPlayer)
    				&& board[i + 3][j - 3] != null && board[i + 3][j - 3].getColor().equals(currentPlayer)
    				){
    				if(i - 1 >= 0 && j + 1 <= 12 && board[i - 1][j + 1] == null){
						result[i - 1][j + 1] = 1;
					}
					if(i + 4 <= 12 && j - 4 >= 0 && board[i + 4][j - 4] == null){
						result[i + 4][j - 4] = 1;
					}
    			}
    		}
    	}
    	*/
    }//reflex agent rule one
    
    
    /**
     * This method implements the reflex rule two.
     * Check whether the opponent has an unbroken chain formed by 4 stones and has 
     * an empty intersection on either head of the chain. If so, place a stone on the empty intersection.
     * @param currentPlayer
     * @return result
     */
    public int[] reflexAgentRuleTwo(String currentPlayer){
    	int[] result = new int[2];
    	result[0] = -1;
    	result[1] = -1;
    	
    	String opponent = "";
    	if(currentPlayer.equals("black")){
    		opponent = "white";
    	}
    	else{
    		opponent = "black";
    	}
    	
    	//Check if opponent have move that can win
    	int[][] opponent_win = reflexAgentRuleOne(opponent);
    	for(int i = 0; i <= 6; i++){
    		for(int j = 0; j <= 6; j++){
    			if(opponent_win[i][j] == 1){
    				result[0] = j;
    				result[1] = i;
    				return result;
    			}
    		}
    	}

    	return result;
    }//reflex agent rule two
    
    
    /**
     * This method implements the reflex agent rule three.
     * Check whether the opponent has an unbroken chain formed by 3 stones 
     * and has empty spaces on both ends of the chain. If so, place a stone on an empty 
     * space at one end of the chain. Choose which end of the chain to fill following 
     * the order: left > down > right > up.
     * @param currentPlayer
     * @return result
     */
    public int[][] reflexAgentRuleThree(String currentPlayer){
    	String opponent = "";
    	if(currentPlayer.equals("black")){
    		opponent = "white";
    	}
    	else{
    		opponent = "black";
    	}
    	
    	int[][] result = new int[7][7];
    	
    	//horizontal
    	for(int i = 0; i <= 6; i++){
    		for(int j = 1; j <= 3; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i][j + 1] != null && board[i][j + 1].getColor().equals(opponent)
    				&& board[i][j + 2] != null && board[i][j + 2].getColor().equals(opponent)
    				){
					if(board[i][j - 1] == null && board[i][j + 3] == null){
						result[i][j - 1] = 1;
						result[i][j + 3] = 1;
					}
    			}
    		}
    	}
    	
    	//Vertical
    	for(int i = 1; i <= 3; i++){
    		for(int j = 0; j <= 6; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i + 1][j] != null && board[i + 1][j].getColor().equals(opponent)
    				&& board[i + 2][j] != null && board[i + 2][j].getColor().equals(opponent)
    				){
    				if(board[i - 1][j] == null && board[i + 3][j] == null){
    					result[i - 1][j] = 1;
    					result[i + 3][j] = 1;
    				}
    			}
    		}
    	}
    	
    	//Diagonal 1
    	for(int i = 1; i <= 3; i++){
    		for(int j = 1; j <= 3; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i + 1][j + 1] != null && board[i + 1][j + 1].getColor().equals(opponent)
    				&& board[i + 2][j + 2] != null && board[i + 2][j + 2].getColor().equals(opponent)
    				){
					if(board[i - 1][j - 1] == null && board[i + 3][j + 3] == null){
						result[i - 1][j - 1] = 1;
						result[i + 3][j + 3] = 1;
					}
    			}
    		}
    	}
    	
    	//Diagonal 2
    	for(int i = 1; i <= 3; i++){
    		for(int j = 5; j >= 3; j--){
    			if(board[i][j] != null && board[i][j].getColor().equals(opponent) 
    				&& board[i + 1][j - 1] != null && board[i + 1][j - 1].getColor().equals(opponent)
    				&& board[i + 2][j - 2] != null && board[i + 2][j - 2].getColor().equals(opponent)
    				){
    				if(board[i - 1][j + 1] == null && board[i + 3][j - 3] == null){
						result[i - 1][j + 1] = 1;
						result[i + 3][j - 3] = 1;
					}
    			}
    		}
    	}
    	
    	return result;
    }//reflex agent rule three
    
    
    /**
     * This method implements the reflex agent rule four.
     * @param currentPlayer
     * @return currentBestPostion
     */
    public int[] reflexAgentRuleFour(String currentPlayer){
    	String opponent = "";
    	if(currentPlayer.equals("black")){
    		opponent = "white";
    	}
    	else{
    		opponent = "black";
    	}
    	
    	int currentMaxNum = -1;
    	int[] currentBestPosition = new int[2];
    	currentBestPosition[0] = 100; //x
    	currentBestPosition[1] = -100; //y
    	
    	//horizontal
    	for(int i = 0; i <= 6; i++){
    		for(int j = 0; j <= 2; j++){
    			if((board[i][j] == null || (board[i][j] != null && board[i][j].getColor().equals(currentPlayer)))
    				&& (board[i][j + 1] == null || (board[i][j + 1] != null && board[i][j + 1].getColor().equals(currentPlayer)))
    				&& (board[i][j + 2] == null || (board[i][j + 2] != null && board[i][j + 2].getColor().equals(currentPlayer)))
    				&& (board[i][j + 3] == null || (board[i][j + 3] != null && board[i][j + 3].getColor().equals(currentPlayer)))
    				&& (board[i][j + 4] == null || (board[i][j + 4] != null && board[i][j + 4].getColor().equals(currentPlayer)))
    				){
    				int numCurrentPieces = 0;
    				for(int curr = j; curr <= j + 4; curr++){
    					if(board[i][curr] != null){
    						numCurrentPieces++;
    					}
    				}
    				if(numCurrentPieces < currentMaxNum){//the current state will not be considered
    					continue;
    				}
    				for(int curr = j; curr <= j + 4; curr++){
    					if(board[i][curr] == null){//satisfy empty
    						if((curr - 1 >= j && board[i][curr - 1] != null) || (curr + 1 <= j + 4 && board[i][curr + 1] != null)){//satisfy adjacent
    							if(numCurrentPieces > currentMaxNum){//if more agents, update position
    								currentMaxNum = numCurrentPieces;
    								currentBestPosition[0] = curr;
    								currentBestPosition[1] = i;
    							}
    							if(numCurrentPieces == currentMaxNum){//equal number of agents, check furtherest left first, then check furtherest bottom
    								if(curr < currentBestPosition[0]){//check left
    									currentBestPosition[0] = curr;
    									currentBestPosition[1] = i;
    								}
    								else if(curr == currentBestPosition[0]){//check bottom
    									if(i > currentBestPosition[1]){
    										currentBestPosition[0] = curr;
    										currentBestPosition[1] = i;
    									}
    								}
    							}
    						}
    					}
    				}
    			}
    		}
    	}
    	
    	//Vertical
    	for(int i = 0; i <= 2; i++){
    		for(int j = 0; j <= 6; j++){
    			if((board[i][j] == null || (board[i][j] != null && board[i][j].getColor().equals(currentPlayer)))
    				&& (board[i + 1][j] == null || (board[i + 1][j] != null && board[i + 1][j].getColor().equals(currentPlayer)))
    				&& (board[i + 2][j] == null || (board[i + 2][j] != null && board[i + 2][j].getColor().equals(currentPlayer)))
    				&& (board[i + 3][j] == null || (board[i + 3][j] != null && board[i + 3][j].getColor().equals(currentPlayer)))
    				&& (board[i + 4][j] == null || (board[i + 4][j] != null && board[i + 4][j].getColor().equals(currentPlayer)))
    				){
    				int numCurrentPieces = 0;
    				for(int curr = i; curr <= i + 4; curr++){
    					if(board[curr][j] != null){
    						numCurrentPieces++;
    					}
    				}
    				if(numCurrentPieces < currentMaxNum){//the current state will not be considered
    					continue;
    				}
    				for(int curr = i; curr <= i + 4; curr++){
    					if(board[curr][j] == null){//satisfy empty
    						if((curr - 1 >= i && board[curr - 1][j] != null) || (curr + 1 <= i + 4 && board[curr + 1][j] != null)){//satisfy adjacent
    							if(numCurrentPieces > currentMaxNum){//if more agents, update position
    								currentMaxNum = numCurrentPieces;
    								currentBestPosition[0] = j;
    								currentBestPosition[1] = curr;
    							}
    							if(numCurrentPieces == currentMaxNum){//equal number of agents, check furtherest left first, then check furtherest bottom
    								if(j < currentBestPosition[0]){//check if 
    									currentBestPosition[0] = j;
    									currentBestPosition[1] = curr;
    								}
    								else if(j == currentBestPosition[0]){//check bottom
    									if(curr > currentBestPosition[1]){
    										currentBestPosition[0] = j;
    										currentBestPosition[1] = curr;
    									}
    								}
    							}
    						}
    					}
    				}
        		}
    		}
    	}
    	
    	//Diagonal 1
    	for(int i = 0; i <= 2; i++){
    		for(int j = 0; j <= 2; j++){
    			if((board[i][j] == null || (board[i][j] != null && board[i][j].getColor().equals(currentPlayer)))
    				&& (board[i + 1][j + 1] == null || (board[i + 1][j + 1] != null && board[i + 1][j + 1].getColor().equals(currentPlayer)))
    				&& (board[i + 2][j + 2] == null || (board[i + 2][j + 2] != null && board[i + 2][j + 2].getColor().equals(currentPlayer)))
    				&& (board[i + 3][j + 3] == null || (board[i + 3][j + 3] != null && board[i + 3][j + 3].getColor().equals(currentPlayer)))
    				&& (board[i + 4][j + 4] == null || (board[i + 4][j + 4] != null && board[i + 4][j + 4].getColor().equals(currentPlayer)))
    				){
    				int numCurrentPieces = 0;
    				for(int curr = 0; curr <= 4; curr++){
    					if(board[i + curr][j + curr] != null){
    						numCurrentPieces++;
    					}
    				}
    				if(numCurrentPieces < currentMaxNum){//the current state will not be considered
    					continue;
    				}
    				for(int curr = 0; curr <= 4; curr++){
    					if(board[i + curr][j + curr] == null){//satisfy empty
    						if((i + curr - 1 >= i && board[i + curr - 1][j + curr - 1] != null) || (i + curr + 1 <= i + 4 && board[i + curr + 1][j + curr + 1] != null)){//satisfy adjacent
    							if(numCurrentPieces > currentMaxNum){//if more agents, update position
    								currentMaxNum = numCurrentPieces;
    								currentBestPosition[0] = j + curr;
    								currentBestPosition[1] = i + curr;
    							}
    							if(numCurrentPieces == currentMaxNum){//equal number of agents, check furtherest left first, then check furtherest bottom
    								if(j + curr < currentBestPosition[0]){//check if 
    									currentBestPosition[0] = j + curr;
    									currentBestPosition[1] = i + curr;
    								}
    								else if(j + curr == currentBestPosition[0]){//check bottom
    									if(i + curr > currentBestPosition[1]){
    										currentBestPosition[0] = j + curr;
    										currentBestPosition[1] = i + curr;
    									}
    								}
    							}
    						}
    					}
    				}
        		}
    		}
    	}
    	
    	//Diagonal 2
    	for(int i = 0; i <= 2; i++){
    		for(int j = 6; j >= 4; j--){
    			if((board[i][j] == null || (board[i][j] != null && board[i][j].getColor().equals(currentPlayer)))
    				&& (board[i + 1][j - 1] == null || (board[i + 1][j - 1] != null && board[i + 1][j - 1].getColor().equals(currentPlayer)))
    				&& (board[i + 2][j - 2] == null || (board[i + 2][j - 2] != null && board[i + 2][j - 2].getColor().equals(currentPlayer)))
    				&& (board[i + 3][j - 3] == null || (board[i + 3][j - 3] != null && board[i + 3][j - 3].getColor().equals(currentPlayer)))
    				&& (board[i + 4][j - 4] == null || (board[i + 4][j - 4] != null && board[i + 4][j - 4].getColor().equals(currentPlayer)))
    				){
    				int numCurrentPieces = 0;
    				for(int curr = 0; curr <= 4; curr++){
    					if(board[i + curr][j - curr] != null){
    						numCurrentPieces++;
    					}
    				}
    				if(numCurrentPieces < currentMaxNum){//the current state will not be considered
    					continue;
    				}
    				for(int curr = 0; curr <= 4; curr++){
    					if(board[i + curr][j - curr] == null){//satisfy empty
    						if((i + curr - 1 >= i && board[i + curr - 1][j - curr + 1] != null) || (i + curr + 1 <= i + 4 && board[i + curr + 1][j - curr - 1] != null)){//satisfy adjacent
    							if(numCurrentPieces > currentMaxNum){//if more agents, update position
    								currentMaxNum = numCurrentPieces;
    								currentBestPosition[0] = j - curr;
    								currentBestPosition[1] = i + curr;
    							}
    							if(numCurrentPieces == currentMaxNum){//equal number of agents, check furtherest left first, then check furtherest bottom
    								if(j - curr < currentBestPosition[0]){//check if 
    									currentBestPosition[0] = j - curr;
    									currentBestPosition[1] = i + curr;
    								}
    								else if(j - curr == currentBestPosition[0]){//check bottom
    									if(i + curr > currentBestPosition[1]){
    										currentBestPosition[0] = j - curr;
    										currentBestPosition[1] = i + curr;
    									}
    								}
    							}
    						}
    					}
    				}
        		}
    		}
    	}
    	
    	return currentBestPosition;
    	
    }//end of reflex agent rule four
    

    /**
     * Minimax method.
     * @param depth
     * @param player
     */
    public void minimaxMove(int depth, String player){
    	int bestValue = Integer.MIN_VALUE;
    	int bestX = -1;
    	int bestY = -1;
    	String targetMax = player;
    	String targetMin = "";
    	if(targetMax.equals("black")){
    		targetMin = "white";
    	}
    	else{
    		targetMin = "black";
    	}
    	for(int i = 0; i < 7; i++){
    		for(int j = 0; j < 7; j++){
    			if(board[i][j] == null){
    				board[i][j] = new Piece(j, i, player);
    				minimax_count++;
    				int currBest = minimax(this.board, depth, targetMin, targetMin, targetMax);
    				board[i][j] = null;
    				if(currBest > bestValue){
    					bestX = j;
    					bestY = i;
    					bestValue = currBest;
    				}
    			}
    		}
    	}
    	System.out.println("Minimax : " + minimax_count);
    	
    	this.movePiece(bestX, bestY);
    }
    
    
    /**
     * Minimaxmove helper function
     * @param board
     * @param depth
     * @param player
     * @param opponent
     * @param targetMax
     * @return
     */
    public int minimax(Piece[][] board, int depth, String player, String targetMin, String targetMax){

    	if(depth == 0 || this.checkWinner()){
    		 int v = MiniMaxEvaluate.minimaxEvaluate(board, targetMax);
    		 return v;
    	}

    	if(player.equals(targetMax)){
    		int bestValue = Integer.MIN_VALUE;
    		for(int i = 0; i < 7; i++){
    			for(int j = 0; j < 7; j++){
    				if(board[i][j] == null){
    					this.board[i][j] = new Piece(j, i, targetMax);
    					minimax_count++;
    					bestValue = Math.max(bestValue, minimax(this.board, depth - 1, targetMin, targetMin, targetMax));
    					this.board[i][j] = null;
    				}
    			}
    		}
    		return bestValue;	
    	}
    	else{
    		int bestValue = Integer.MAX_VALUE;
    		for(int i = 0; i < 7; i++){
    			for(int j = 0; j < 7; j++){
    				if(board[i][j] == null){
    					this.board[i][j] = new Piece(j, i, targetMin);
    					minimax_count++;
    					bestValue = Math.min(bestValue, minimax(this.board, depth - 1, targetMax, targetMin, targetMax));
    					this.board[i][j] = null;
    				}
    			}
    		}
    		return bestValue;
    	}
    }
    
    
    /**
     * Alpha beta method.
     * @param depth
     * @param player
     */
    public void alphaBetaMove(int depth, String player){
    	int bestX = -1;
    	int bestY = -1;
    	int bestValue = Integer.MIN_VALUE;
    	int alpha = Integer.MIN_VALUE;
    	int beta = Integer.MAX_VALUE;
    	String targetMax = player;
    	String targetMin = "";
    	if(targetMax.equals("black")){
    		targetMin = "white";
    	}
    	else{
    		targetMin = "black";
    	}
    	for(int i = 0; i < 7; i++){
    		for(int j = 0; j < 7; j++){
    			if(board[i][j] == null){
    				board[i][j] = new Piece(j, i, player);
    				alphabeta_count++;
    				int currBest = alphaBeta(this.board, depth, alpha, beta, targetMin, targetMin, targetMax);
    				board[i][j] = null;
    				if(currBest > bestValue){
    					bestX = j;
    					bestY = i;
    					bestValue = currBest;
    				}
    				if(beta <= alpha){
    					this.movePiece(bestX, bestY);
    					return;
    				}
    			}
    		}
    	}
    	System.out.println("AlphaBeta : " + alphabeta_count);
    	
    	this.movePiece(bestX, bestY);
    }
    
    
    /**
     * alpha beta move helper function
     * @param board
     * @param depth
     * @param player
     * @param opponent
     * @param targetMax
     * @return
     */
    public int alphaBeta(Piece[][] board, int depth, int alpha, int beta, String player, String targetMin, String targetMax){

    	if(depth == 0 || this.checkWinner()){
    		 int v = MiniMaxEvaluate.minimaxEvaluate(board, targetMax);
    		 return v;
    	}

    	if(player.equals(targetMax)){
    		int bestValue = Integer.MIN_VALUE;
    		for(int i = 0; i < 7; i++){
    			for(int j = 0; j < 7; j++){
    				if(board[i][j] == null){
    					this.board[i][j] = new Piece(j, i, targetMax);
    					alphabeta_count++;
    					bestValue = Math.max(bestValue, alphaBeta(this.board, depth - 1, alpha, beta, targetMin, targetMin, targetMax));
    					alpha = Math.max(alpha, bestValue);
    					this.board[i][j] = null;
    				}
    				if(beta <= alpha){
    					return bestValue;
    				}
    			}
    		}
    		return bestValue;	
    	}
    	else{
    		int bestValue = Integer.MAX_VALUE;
    		for(int i = 0; i < 7; i++){
    			for(int j = 0; j < 7; j++){
    				if(board[i][j] == null){
    					this.board[i][j] = new Piece(j, i, targetMin);
    					alphabeta_count++;
    					bestValue = Math.min(bestValue, alphaBeta(this.board, depth - 1, alpha, beta, targetMax, targetMin, targetMax));
    					beta = Math.min(beta, bestValue);
    					this.board[i][j] = null;
    				}
    				if(beta <= alpha){
    					return bestValue;
    				}
    			}
    		}
    		return bestValue;
    	}
    }
     
}//end of class
