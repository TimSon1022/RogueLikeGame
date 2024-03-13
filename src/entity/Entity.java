package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.Room;
import main.UtilityTool;

public class Entity {
	
	public double worldX, worldY;
	public double speed;
	
	GamePanel gp;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, 
	downAttack, downSwordAttack1, downSwordAttack2,downSwordAttack3,
	upAttack,upSwordAttack1, upSwordAttack2,upSwordAttack3,
	leftAttack, leftSwordAttack1, leftSwordAttack2,leftSwordAttack3,
	rightAttack, rightSwordAttack1, rightSwordAttack2,rightSwordAttack3;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public int spriteAttackNum = 0;
	public String characterName; 
	boolean attack;
	boolean attackBuffer;
	public Rectangle hitBox = new Rectangle (4,4, 24,24);
	public int hitBoxDefaultX, hitBoxDefaultY;
	
	public boolean collisionOn = false;
	
	public int actionLockCounter = 0;
	
	String dialogues[] = new String[20];
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public BufferedImage setup(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		return image;
		
	}
	public void setAction() {}
	
	public void setDialogue() {}
	
	public void speak() {
		gp.ui.currentDialogue = dialogues[0];
		
		switch(gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
			
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		
		}
	}
	
	public void update() {
		setAction();
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
		
		
		if (!collisionOn) {
			
			switch(direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
			}
		
	}
		
		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
		
	}

	public void draw(Graphics2D g2) throws InterruptedException {
		
		BufferedImage image = null;
		

		int index = gp.dungeon.getRoomIndexAt((int)gp.player.worldX/gp.tileSize, (int)gp.player.worldY/gp.tileSize);
	    int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
	    int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);
	    
	    if ((index != -1 && isWithinBounds((int)worldX, (int)worldY, (int)gp.player.worldX, (int)gp.player.worldY, gp.player.screenX, gp.player.screenY, gp.dungeon.getRooms().get(index), gp))
	    		|| (index == -1 && isWithinBounds((int)worldX, (int)worldY, (int)gp.player.worldX, (int)gp.player.worldY, gp.player.screenX - 500, gp.player.screenY - 355, null, gp))) {
	        
			switch(direction) {
			case "up":
				if (spriteNum == 1) {
					image = up1;
				}
				if (spriteNum == 2) {
					image = up2;
				}

				break;
			case "down":
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = down2;
				}

				break;
			case "left":
				if (spriteNum == 1) {
					image = left1;
				}
				if (spriteNum == 2) {
					image = left2;
				}

				break;
			case "right":
				if (spriteNum == 1) {
					image = right1;
				}
				if (spriteNum == 2) {
					image = right2;
				}
				break;
			}
	    	g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

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

}
