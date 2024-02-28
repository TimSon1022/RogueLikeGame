package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class SuperObject {
	
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public int spriteNum = 1;
	public Rectangle hitBox = new Rectangle(0,0,32,32);
	public int hitBoxDefaultX = 0;
	public int hitBoxDefaultY = 0;
	
	public void draw(Graphics2D g2, GamePanel gp) throws InterruptedException {
		
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

			if (name.equals("gold") && spriteNum  > 41) {
				spriteNum = 1;
			}
		}
		
		
	}

	public void update() {

		if (name.equals("gold")) {
			if (spriteNum == 1) {
				try {
					image = ImageIO.read(getClass().getResourceAsStream("/objects/gold_1.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if (spriteNum == 11) {
				try {
					image = ImageIO.read(getClass().getResourceAsStream("/objects/gold_2.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (spriteNum == 21) {
				try {
					image = ImageIO.read(getClass().getResourceAsStream("/objects/gold_3.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (spriteNum == 31) {
				try {
					image = ImageIO.read(getClass().getResourceAsStream("/objects/gold_4.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	


}
