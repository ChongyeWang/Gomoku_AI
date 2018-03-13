/**
 * Training data using linear regression
 * in supervised learning
 * @author Chongye Wang
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
	
	
	/**
	 * Generate the feature vector and
     * check if there are winning conditions
	 * @param board
	 * @param player
	 * @param num
	 * @return
	 */
    public static int evaluate(Piece[][] board, String player, double[] num){	
    	int score = 0;
    	score = score + CheckWin(board, player, num);
    	score = score + CheckNumFour(board, player, num);
    	score = score + CheckNumThree(board, player, num);
        score = score + CheckNumTwo(board, player, num);
        //score = score + CheckNumOne(board, player, num);
    	return score;
	}
    
    
    /**
     * Check one piece
     * @param board
     * @param player
     * @return 0
     */
    public static int CheckNumOne(Piece[][] board, String player, double[] num){
    	for(int i = 0; i <= 6; i++){
    		for(int j = 0; j <= 6; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(player)){
    				num[4] += 1;
    			}
    		}
    	}
    	return 0;
    }
    
    
    /**
     * Check continuing two
     * @param board
     * @param player
     * @return 0
     */
    public static int CheckNumTwo(Piece[][] board, String player, double[] num){
    	
    	//horizontal
    	for(int i = 0; i <= 6; i++){
    		for(int j = 1; j <= 4; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i][j + 1] != null && board[i][j + 1].getColor().equals(player)
    				){
					if(board[i][j - 1] == null && board[i][j + 2] == null){
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
    					num[3] += 1;
					}
    			}
    		}
    	}
    	return 0;
    }
    
    
    /**
     * Check continuing three
     * @param board
     * @param player
     * @return 0
     */
    public static int CheckNumThree(Piece[][] board, String player, double[] num){

    	//horizontal
    	for(int i = 0; i <= 6; i++){
    		for(int j = 1; j <= 3; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i][j + 1] != null && board[i][j + 1].getColor().equals(player)
    				&& board[i][j + 2] != null && board[i][j + 2].getColor().equals(player)
    				){
					if(board[i][j - 1] == null && board[i][j + 3] == null){
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
    					num[2] += 1;
					}
    			}
    		}
    	}
    	return 0;
    }
    
    
    /**
     * Check continuing Four
     * @param board
     * @param player
     * @return 0
     */
    public static int CheckNumFour(Piece[][] board, String player, double[] num){

    	//horizontal
    	for(int i = 0; i <= 6; i++){
    		for(int j = 1; j <= 2; j++){
    			if(board[i][j] != null && board[i][j].getColor().equals(player) 
    				&& board[i][j + 1] != null && board[i][j + 1].getColor().equals(player)
    				&& board[i][j + 2] != null && board[i][j + 2].getColor().equals(player)
    				&& board[i][j + 3] != null && board[i][j + 3].getColor().equals(player)
    				){
					if(board[i][j - 1] == null && board[i][j + 4] == null){
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
    					num[1] += 1;
					}
    			}
    		}
    	}
    	
    	return 0;
    	
    }//end of check four
    
    
    /**
     * Check continuing five
     * @param board
     * @param player
     * @return 1 or 0
     */
    public static int CheckWin(Piece[][] board, String player, double[] num){
    	
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
    				score = 1;
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
    				score = 1;
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
    				score = 1;
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
    				score = 1;
					num[0] += 1;
    			}
    		}
    	}
    	return score;	
    }
}
