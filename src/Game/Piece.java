/**
 * Piece class of the game.
 * @author Chongye Wang
 */
package Game;

public class Piece {
	private int x;
	private int y;
	private String color;
	
	/**
	 * Piece constructor
	 */
    public Piece(){
    	
    }
    
    /**
     * Piece constructor
     * @param x
     * @param y
     * @param color
     */
    public Piece(int x, int y, String color){
    	this.x = x;
    	this.y = y;
    	this.color = color;
    }
    /**
     * Get X
     * @return
     */
    public int getX(){
    	return this.x;
    }
    
    /**
     * Get Y
     * @return
     */
    public int getY(){
    	return this.y;
    }
    
    /**
     * Get color
     * @return
     */
    public String getColor(){
    	return this.color;
    }
    
}
