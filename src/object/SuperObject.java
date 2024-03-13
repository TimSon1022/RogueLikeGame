package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.Room;
import main.UtilityTool;

public class SuperObject {
	
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public int spriteNum = 1;
	public Rectangle hitBox = new Rectangle(0,0,32,32);
	public int hitBoxDefaultX = 0;
	public int hitBoxDefaultY = 0;
	public boolean opened = true;
	UtilityTool uTool = new UtilityTool();
	
	public void draw(Graphics2D g2, GamePanel gp) throws InterruptedException {
		int index = gp.dungeon.getRoomIndexAt((int)gp.player.worldX/gp.tileSize, (int)gp.player.worldY/gp.tileSize);
	    int screenX = worldX - (int)gp.player.worldX + gp.player.screenX;
	    int screenY = worldY - (int)gp.player.worldY + gp.player.screenY;
	    
	    if (index != -1 && isWithinBounds(worldX, worldY, (int)gp.player.worldX, (int)gp.player.worldY, gp.player.screenX, gp.player.screenY, gp.dungeon.getRooms().get(index), gp)) {
	        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

	        if (name.equals("gold") && spriteNum > 41) {
	            spriteNum = 1;
	        }
	    }
	}

	// Helper method
	private boolean isWithinBounds(int worldX, int worldY, int playerWorldX, int playerWorldY, int playerScreenX, int playerScreenY, Room room, GamePanel gp) {
	    return worldX + gp.tileSize > playerWorldX - playerScreenX &&
	           worldX - gp.tileSize < playerWorldX + playerScreenX &&
	           worldY + gp.tileSize > playerWorldY - playerScreenY &&
	           worldY - gp.tileSize < playerWorldY + playerScreenY &&
               (room == null || (worldX/gp.tileSize >= room.x && worldX/gp.tileSize < room.x + room.width && worldY/gp.tileSize >= room.y && worldY/gp.tileSize < room.y + room.height));
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
