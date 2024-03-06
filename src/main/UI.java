package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
	Font arial;
	Font largeFont;
	Font messageFont;
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
	double playTime = 0;
	DecimalFormat dFormat = new DecimalFormat("00");
	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial = new Font("Arial", Font.PLAIN, 10);
		largeFont = new Font("Arial", Font.PLAIN, 80);
		messageFont = new Font("Arial", Font.PLAIN, 15);
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
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
		
	}
	
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(arial);
		g2.setColor(Color.WHITE);
		if (gp.gameState == gp.playState) {
			g2.drawImage(heartImage, gp.originalTileSize/2, 5, gp.tileSize, gp.tileSize, null);
			g2.drawString(gp.player.combatHealth + " / "  + gp.player.fullHealth, 39 , 25);
			g2.drawImage(attackImage, gp.tileSize/2, gp.tileSize/2 + 25, gp.originalTileSize, gp.originalTileSize, null);
			g2.drawString("  "  + gp.player.attackStat, 33 , 50);
			g2.drawImage(defenseImage, gp.tileSize/2, gp.tileSize/2  + 50, gp.originalTileSize, gp.originalTileSize, null);
			g2.drawString("  "  + gp.player.defenseStat, 33 , 75);	
			gold.update();
			gold.spriteNum++;
			if (gold.spriteNum > 41) {
				gold.spriteNum = 1;
			}
			goldImage = gold.image;
			
			g2.drawImage(goldImage, gp.tileSize/2, gp.tileSize/2 + 75, gp.originalTileSize, gp.originalTileSize, null);
			g2.drawString("x "  + gp.player.goldTotal, 33 , 100);	
			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2 + 100, gp.originalTileSize, gp.originalTileSize, null);
			g2.drawString("x "  + gp.player.keyTotal, 33 , 125);
			g2.drawImage(healthPotionImage, gp.tileSize/2, gp.tileSize/2 + 125, gp.originalTileSize, gp.originalTileSize, null);
			g2.drawString("x "  + gp.player.healthPotionTotal, 33 , 150);
			

			
			//Time
			playTime += (double)1/100;
			int hours = (int) (playTime / 3600);
	        int minutes = (int) ((playTime % 3600) / 60);
	        int remainingSeconds = (int) (playTime % 60);
	        String formattedHours = dFormat.format(hours);
	        String formattedMinutes = dFormat.format(minutes);
	        String formattedSeconds = dFormat.format(remainingSeconds);
			g2.drawString("Time: " + formattedHours + ":" + formattedMinutes + ":" + formattedSeconds, gp.tileSize*29 + 25, 25);
			//message
			if (messageOn == true) {
				g2.setFont(g2.getFont().deriveFont(15F));
				g2.drawString(message, 16 ,175);
				messageOn = false;
				
			}
		
		}
		
		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		

		


	}
	
	public void drawPauseScreen() {
		String text = "PAUSED";
		g2.setFont(largeFont);
		int x = getXforCenteredText(text);
		int y = gp.screenHeight / 2;
		g2.drawString(text, x, y);
		g2.setFont(arial);
		
	}
	
	
	public int getXforCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}

}
