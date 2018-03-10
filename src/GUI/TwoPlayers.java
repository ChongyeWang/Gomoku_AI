/**
 * This class implements the two-player of the game.
 * @author Chongye Wang
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Game.Game;
import Game.Piece;



public class TwoPlayers extends JPanel implements MouseListener{
	private ImageIcon blackPiece = new ImageIcon("res/blackPiece.png");
	private ImageIcon whitePiece = new ImageIcon("res/whitePiece.png");
	
	public TwoPlayers(){
    	this.setLayout(null);
		this.setBounds(0, 0, 650, 650);
        addMouseListener(this);
    }
    
    public static Game game = new Game();
   
    public static int mouseX;
    public static int mouseY;
   
    public void paint(Graphics g){
    	setBackground(Color.GRAY);
		for(int i = 0; i <= 600; i += 100){
			for(int j = 0; j <= 600; j += 100){
				g.clearRect(i, j, 50, 50);
			}
		}
		for(int i = 50; i <= 600; i += 100){
			for(int j = 50; j <= 700; j += 100){
				g.clearRect(i, j, 50, 50);
			}
		}
    	
    	if(game.getgameEnds()){
			g.setColor(Color.BLUE);
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.drawString("Winner:" + game.getWinner(), 100, 100);	
    	}
    	for(int i = 0; i < 13; i++){
    		for(int j = 0; j < 13; j++){
    			if(game.board[i][j] != null){
    				if(game.board[i][j].getColor().equals("black")){
    					blackPiece.paintIcon(this, g, j * 50, i * 50);
    				}
    				if(game.board[i][j].getColor().equals("white")){
    					whitePiece.paintIcon(this, g, j * 50, i * 50);
    				}
    			}
    		}
    	}
    }
    
    @Override
	public void mouseClicked(MouseEvent e) {
    	if(!game.getgameEnds()){
			mouseX = e.getX();
			mouseY = e.getY();
			int targetX = mouseX / 50;
			int targetY = mouseY / 50;
			game.movePiece(targetX, targetY);
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
