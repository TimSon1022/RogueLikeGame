package main;

import javax.swing.JFrame;
import java.util.*;

public class main {
	

	public static void main(String[] args) {

		JFrame window = new JFrame(); 
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("RogueLike Game");
		
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);	
		window.pack();
		
		window.setLocationRelativeTo(null);
		window .setVisible(true);
		
		gamePanel.startGameThread();
		
		
	}
	
	
	


	
	
	

}