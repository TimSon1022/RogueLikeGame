package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import object.ObjectAttackBoost;
import object.ObjectDefenseBoost;
import object.ObjectGold;
import object.ObjectHealthPotion;
import object.ObjectHeart;
import object.ObjectKey;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font font;

	BufferedImage keyImage;
	BufferedImage healthPotionImage;
	BufferedImage attackImage;
	BufferedImage defenseImage;
	BufferedImage goldImage;
	BufferedImage heartImage;
	ObjectKey key;
	ObjectHealthPotion healthPotion;
	ObjectAttackBoost attack;
	ObjectDefenseBoost defense;
	ObjectGold gold;
	ObjectHeart heart;

	String message = "";
	boolean messageOn = false;
	boolean levelUp = false;
	int messageCounter = 0;
	double playTime = 0;
	DecimalFormat dFormat = new DecimalFormat("00");
	public String currentDialogue = "";
	String inputGameName = "";
	boolean inputIsEmpty = false;
	
	int xNewGame;
    int yNewGame;
	
    // Calculate triangle position based on the selected menu text (assuming "NEW GAME" is selected)
    public int triangleX; // Adjust this value to position the triangle correctly
    public int triangleY;
    public int commandNum = 0;
    
    public int titleScreenState = 0;
    
	
	
	public UI(GamePanel gp) {
		this.gp = gp;

		yNewGame = gp.tileSize * 15;

	    triangleY = yNewGame - 30;
		
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/font.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		key = new ObjectKey(gp);
		healthPotion = new ObjectHealthPotion(gp);
		attack = new ObjectAttackBoost(gp);
		defense = new ObjectDefenseBoost(gp);
		gold = new ObjectGold(gp);
		heart = new ObjectHeart(gp);
		
		keyImage = key.image;
		healthPotionImage = healthPotion.image;
		attackImage = attack.image;
		defenseImage = defense.image;
		goldImage = gold.image;
		heartImage = heart.image;
	}
	
	public void showMessage(String text, boolean isLevel) {
		messageCounter = 0;
		message = text;
		if (isLevel) {
			levelUp = true;
		}
		else {
			messageOn = true;
		}
		
		
	}
	
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(font);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 14F));
		g2.setColor(Color.WHITE);
		
		//Title Screen
		if (gp.gameState == gp.titleScreen) {
			drawTitleScreen();
		}
		
		
		
		//Play State and Dialogue State
		if (gp.gameState == gp.playState || gp.gameState == gp.dialogueState) {
			
			gold.update();
			gold.spriteNum++;
			if (gold.spriteNum > 41) {
				gold.spriteNum = 1;
			}
			goldImage = gold.image;	
			//Time
			playTime += (double)1/100;

	        //message
			if (levelUp == true || messageOn == true) {
				
				if (messageCounter <= 200 && levelUp == true) {
					messageCounter++;
					g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
					g2.drawString(message, 16 ,200);
					g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 14F));
				}
				else {
					messageCounter = 0;
					levelUp = false;
				}
				if (messageOn == true) {
					g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
					g2.drawString(message, 16 ,200);
					g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 14F));
					messageOn = false;					
				}
				
				
			}
			
			if (gp.gameState == gp.dialogueState) {
				drawDialogueScreen();
			}
		
		}
		
		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		

		
		if (gp.gameState != gp.titleScreen) {
			g2.drawImage(heartImage, gp.originalTileSize/2, 5, gp.tileSize, gp.tileSize, null);
			g2.drawString(gp.player.combatHealth + " / "  + gp.player.fullHealth, 39 , 25);
			g2.drawString("Level: "  + gp.player.playerLevel, gp.tileSize/2 , 50);
			g2.drawImage(attackImage, gp.tileSize/2, gp.tileSize/2 + 47, gp.originalTileSize, gp.originalTileSize, null);
			g2.drawString("  "  + gp.player.attackStat, 33 , 75);
			g2.drawImage(defenseImage, gp.tileSize/2, gp.tileSize/2  + 72, gp.originalTileSize, gp.originalTileSize, null);
			g2.drawString("  "  + gp.player.defenseStat, 33 , 100);	
			g2.drawImage(goldImage, gp.tileSize/2, gp.tileSize/2 + 97, gp.originalTileSize, gp.originalTileSize, null);
			g2.drawString("x "  + gp.player.goldTotal, 33 , 125);	
			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2 + 122, gp.originalTileSize, gp.originalTileSize, null);
			g2.drawString("x "  + gp.player.keyTotal, 33 , 150);
			g2.drawImage(healthPotionImage, gp.tileSize/2, gp.tileSize/2 + 147, gp.originalTileSize, gp.originalTileSize, null);
			g2.drawString("x "  + gp.player.healthPotionTotal, 33 , 175);
			
			int hours = (int) (playTime / 3600);
	        int minutes = (int) ((playTime % 3600) / 60);
	        int remainingSeconds = (int) (playTime % 60);
			String formattedHours = dFormat.format(hours);
	        String formattedMinutes = dFormat.format(minutes);
	        String formattedSeconds = dFormat.format(remainingSeconds);
			g2.drawString("Time: " + formattedHours + ":" + formattedMinutes + ":" + formattedSeconds, gp.tileSize*29 + 25, 25);
		}
	}
	
	public void drawTitleScreen() {
		
		
		
		g2.setColor(new Color(24,60,92));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
		if (titleScreenState == 0) {		
			
			//Title Name

			String text = "2D RogueLike";
			
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 3;
			
			g2.setColor(Color.BLACK);
			g2.drawString(text, x + 5, y);
			
			
			g2.setColor(Color.WHITE);
			g2.drawString(text,x,y);
			
			//Menu
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
			xNewGame = getXforCenteredText("NEW GAME");
			triangleX = xNewGame - 50; // Adjust this value to position the triangle correctly
			
	        int xLoadGame = getXforCenteredText("LOAD GAME");
	        int yLoadGame = yNewGame + gp.tileSize * 2;
	        
	        int xSettings = getXforCenteredText("SETTINGS");
	        int ySettings = yLoadGame + gp.tileSize * 2;

	        int xQuit = getXforCenteredText("QUIT");
	        int yQuit = ySettings + gp.tileSize * 2;
	        
	        

	        // Draw the menu texts
	        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
	        g2.drawString("NEW GAME", xNewGame, yNewGame);
	        g2.drawString("LOAD GAME", xLoadGame, yLoadGame);
	        g2.drawString("SETTINGS", xSettings, ySettings);
	        g2.drawString("QUIT", xQuit, yQuit);

	        updateMovingArrow(30, yNewGame - 30, yNewGame - 30 + gp.tileSize*6, 4);
	        
		
		}
		
		else if (titleScreenState == 1) {
			
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(40F));
			String text = "What would you like to name your game file?";
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 3;
			g2.drawString(text, x, y);
			
			
			
			g2.setFont(g2.getFont().deriveFont(20F));
			
			 // Draw text box
	        int textBoxX = getXforCenteredText("") - gp.tileSize * 5;
	        int textBoxY = gp.tileSize * 5;
	        int textBoxWidth = 300;
	        int textBoxHeight = 40;
	        g2.setColor(Color.WHITE);
	        g2.fillRect(textBoxX, textBoxY, textBoxWidth, textBoxHeight);
	        g2.setColor(Color.BLACK);
	        g2.drawRect(textBoxX, textBoxY, textBoxWidth, textBoxHeight);

	        // Draw text in the text box
	        g2.setColor(Color.BLACK);
	        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
	        g2.drawString(inputGameName, textBoxX + 10, textBoxY + 25); // Assuming inputText is the text entered by the player
	        drawTextCursor(textBoxX + 10 + measureInputGameNameLength(g2), textBoxY, textBoxHeight);
	        
	        
        	if (inputIsEmpty) {
        		drawFileNameIsEmpty();
        	}
			
		}
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 14F));
		
	}
	
	private void drawFileNameIsEmpty() {
		String text = "File name cannot be empty!";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 7;
		g2.drawString(text, x, y);
		
	}

	public int measureInputGameNameLength(Graphics2D g2) {
	    FontMetrics fontMetrics = g2.getFontMetrics();
	    return fontMetrics.stringWidth(inputGameName);
	}
	
	public void drawTextCursor(int cursorX, int cursorY, int cursorHeight) {
	    g2.setColor(Color.BLACK); // Set the color of the cursor
	    g2.drawLine(cursorX, cursorY, cursorX, cursorY + cursorHeight); // Draw the cursor line
	}
	
	public void updateMovingArrow(int triangleSize, int yMin, int yMax, int options) {
		Polygon triangle = new Polygon();
		if (gp.keyH.upPressed == true) {
			triangleY -= gp.tileSize * 2;
			commandNum--;
			if (triangleY < yMin) {
				triangleY = yMax;
				commandNum = options - 1;
			}
			gp.playSE(5, -10.0f);
			
			gp.keyH.upPressed = false;

		}
		if (gp.keyH.downPressed == true) {
			triangleY += gp.tileSize * 2;
			commandNum++;
			if (triangleY > yMax) {
				triangleY = yMin;
				commandNum = 0;
			}
			gp.playSE(5, -10.0f);
			gp.keyH.downPressed = false;

		}
		
        triangle.addPoint(triangleX, triangleY);
        triangle.addPoint(triangleX, triangleY + triangleSize);
        triangle.addPoint(triangleX + triangleSize, triangleY + triangleSize / 2);
        g2.fillPolygon(triangle);

	}

	public void drawDialogueScreen() {
		
		//Window
		int x = gp.tileSize * 2;
		int y = gp.tileSize * 15;
		int width  = gp.screenWidth - (gp.tileSize * 4);
		int height = gp.tileSize * 7;
		
		drawDialogueWindow(x,y,width,height);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
		x+= gp.tileSize;
		y += gp.tileSize;
		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y+= 40;
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
		}
		
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 14F));
		
	}
	
	public void drawDialogueWindow(int x, int y, int width, int height) {
		Color c = new Color(0 , 0, 0,200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width-10, height-10, 25,25);
	}

	public void drawPauseScreen() {
		String text = "PAUSED";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		int x = getXforCenteredText(text);
		int y = gp.screenHeight / 2;
		g2.drawString(text, x, y);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 14F));
		
	}
	
	
	public int getXforCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}

}
