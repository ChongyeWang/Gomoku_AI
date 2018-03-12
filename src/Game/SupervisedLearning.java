/**
 * Training data using linear regression
 * in supervised learning
 */
package Game;

import java.util.ArrayList;
import java.util.Random;
import org.apache.commons.math.*;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.DecompositionSolver;
import org.apache.commons.math.linear.LUDecompositionImpl;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.linear.SingularValueDecomposition;
import org.apache.commons.math.linear.SingularValueDecompositionImpl;

public class SupervisedLearning {
	public static void main(String args[]){
		
		ArrayList<double[]> matrix = new ArrayList<double[]>();
		ArrayList<Double> result = new ArrayList<Double>();
		//Generate 10000 game
		Random random = new Random();
		for(int i = 0; i < 3000; i++){
			Game game = new Game();
			double[] num = new double[5];
			for(int j = 0; j < 30; j++){
				int x = random.nextInt(7);
				int y = random.nextInt(7);
				while(game.board[y][x] != null){
					x = random.nextInt(7);
					y = random.nextInt(7);
				}
				game.movePiece(x, y);
			}
			double score = (double)evaluate(game.board, "black", num);
			matrix.add(num);
			result.add(score);
		}
		double[][] regressorsArray = new double[matrix.size()][5];
		double[] regressandArray = new double[matrix.size()];
		for(int i = 0; i < matrix.size(); i++){
			regressorsArray[i] = matrix.get(i).clone();
			regressandArray[i] = result.get(i);
		}
		
		RealMatrix regressorsMatrix = new Array2DRowRealMatrix(regressorsArray, false);
		RealVector regressandVector = new ArrayRealVector(regressandArray);
		SingularValueDecomposition svd = new SingularValueDecompositionImpl(regressorsMatrix);
		DecompositionSolver solver = svd.getSolver();
		RealVector solution = solver.solve(regressandVector);
		double[] resultVector = solution.toArray();
		for(int i = 0; i < resultVector.length; i++){
			System.out.println(resultVector[i]);
		}
	}
	
public static int evaluate(Piece[][] board, String player, double[] num){
    	
    	int score = 0;
    	
    	score = score + minimaxCheckWin(board, player, num);
    	score = score + minimaxCheckNumFour(board, player, num);
    	score = score + minimaxCheckNumThree(board, player, num);
        score = score + minimaxCheckNumTwo(board, player, num);
    	
    	for(int i = 0; i < 7; i++){
    		for(int j = 0; j < 7; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(player)){
					score += 1;
					num[4] += 1;
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
    public static int minimaxCheckNumFour(Piece[][] board, String player, double[] num){
    	
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
						num[1] += 1;
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
    					num[1] += 1;
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
						num[1] += 1;
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
    					num[1] += 1;
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
    public static int minimaxCheckNumTwo(Piece[][] board, String player, double[] num){
    	
    	int score = 0;
    	
    	//horizontal
    	for(int i = 0; i <= 6; i++){
    		for(int j = 1; j <= 4; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i][j + 1] != null && board[i][j + 1].getColor().equals(player)
    				){
					if(board[i][j - 1] == null && board[i][j + 2] == null){
						score += 50;
						num[3] += 1;
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
    					num[3] += 1;
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
						num[3] += 1;
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
    					num[3] += 1;
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
    public static int minimaxCheckNumThree(Piece[][] board, String player, double[] num){

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
						num[2] += 1;
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
    					num[2] += 1;
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
						num[2] += 1;
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
    					num[2] += 1;
					}
    			}
    		}
    	}
    	return score;
    }
    
    
    /**
     * Check if the playler has already won
     * if yes, return score 100000
     * @param board
     * @param player
     * @return int
     */
    public static int minimaxCheckWin(Piece[][] board, String player, double[] num){
    	
    	int score = 0;
 
    	//horizontal
    	for(int i = 0; i <= 6; i++){
    		for(int j = 0; j <= 2; j++){
    			
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i][j + 1] != null && board[i][j + 1].getColor().equals(player)
    				&& board[i][j + 2] != null && board[i][j + 2].getColor().equals(player)
    				&& board[i][j + 3] != null && board[i][j + 3].getColor().equals(player)
    				&& board[i][j + 4] != null && board[i][j + 4].getColor().equals(player)
    				){
    				score += 100000;
					num[0] += 1;
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
    				score += 100000;
					num[0] += 1;
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
    				score += 100000;
					num[0] += 1;
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
    				score += 100000;
					num[0] += 1;
    			}
    		}
    	}
    	return score;	
    }
}
