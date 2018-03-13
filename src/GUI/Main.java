/**
 * GUI Main
 * @author Chongye Wang
 */
package GUI;
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
    	JFrame frame = new JFrame();
    	frame.setResizable(false);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 650, 672);
        frame.setLayout(null);
        frame.add(new User_vs_AI());
        frame.setVisible(true);  
    }
}
