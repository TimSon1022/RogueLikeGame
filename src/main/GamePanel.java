package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 2;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 32;
	public final int maxScreenRow = 24;
	public final int screenWidth = tileSize * maxScreenCol; //768 pixels
	public final int screenHeight = tileSize * maxScreenRow; //576 pixels
	
	//World Settings
	public final int maxWorldCol = 150;
	public final int maxWorldRow = 150;
	
	//FPS
	public int FPS = 100;
	public DungeonGenerator dungeon = new DungeonGenerator();
	
	
	//System
	public TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	public Sound soundEffect = new Sound();
	public Sound music = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this, dungeon);
	public UI ui = new UI(this);
	Thread gameThread;
	
	// Entities and objects
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[150];
	

	
	//Game State
	public int gameState;
	public final int pauseState = 0;
	public final int playState = 1;
	
	
	public GamePanel() {

		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void setupGame() {
		aSetter.setObject();
		gameState = playState;
	}
	
	public void startGameThread() {
		player.worldX = tileM.playerX;
		player.worldY = tileM.playerY;
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
		
		if (gameState == playState) {
			player.update();
			for (int i = 0; i < obj.length; i++) {
				if (obj[i] != null) {
					obj[i].update();
					obj[i].spriteNum++;
				}
			}
		}
		if (gameState == pauseState) {
			
		}
		


		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//tile
		tileM.draw(g2);
		
		//object
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				try {
					obj[i].draw(g2, this);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		//player
		player.draw(g2);
		ui.draw(g2);
		
		g2.dispose();
	}
	
	public void playSE (int i) {
		soundEffect.setFile(i);
		soundEffect.play();
	}
	
	
	
	
	

}
