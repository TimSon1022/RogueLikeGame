package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 2;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; //768 pixels
	public final int screenHeight = tileSize * maxScreenRow; //576 pixels
	
	//World Settings
	public final int maxWorldCol = 150;
	public final int maxWorldRow = 150;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	//FPS
	int FPS = 100;
	public KeyHandler keyH = new KeyHandler();
	public Player player = new Player(this, keyH);
	public TileManager tileM = new TileManager(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	Thread gameThread;
	


	
	//Set player's default position
	
	public GamePanel() {

		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void startGameThread() {
		player.worldX = tileM.playerX;
		player.worldY = tileM.playerY;

		System.out.println("Start");

		gameThread = new Thread(this);
		gameThread.start();
		
		
	}
	@Override
	public void run() {
		
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/ drawInterval;
			lastTime = currentTime;

			
			if (delta >= 1) {				
				update();		
				repaint();
				delta --;
			}
			
		}
		
		
		
	}
	
	public void update() {
		
		player.update();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		tileM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();
	}
	
	
	

}
