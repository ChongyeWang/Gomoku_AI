/**
 * This class implements the reflex agent 
 * with 4 rules. This is a self playing AI,
 * and are not for players to play.
 * @author Chongye Wang
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Game.Game;
import Game.Piece;

import java.util.Random;


public class ReflexAgent extends JPanel implements ActionListener{
	private ImageIcon blackPiece = new ImageIcon("res/blackPiece.png");
	private ImageIcon whitePiece = new ImageIcon("res/whitePiece.png");
	
	private Timer timer = new Timer(1000, this);
	private static Random random = new Random();
	
	private static Game game = new Game();
	
	
	/**
	 * Constructor for ReflexAgent
	 */
	public ReflexAgent(){
    	this.setLayout(null);
		this.setBounds(0, 0, 650, 650);
		timer.start();
    }
    
    
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
    
    	game.reflexAgentMove();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}
