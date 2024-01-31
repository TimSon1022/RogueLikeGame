package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;


public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		hitBox = new Rectangle();
		hitBox.x = 4;
		hitBox.y = 4;
		hitBox.width = 24;
		hitBox.height = 24;
		
		
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {		
		speed = 2;
		direction = "down";
	}
	
	public void getPlayerImage() {
		characterName = "Link";
		try {
			
			if (characterName.equals("Link")) {
				up1 = ImageIO.read(getClass().getResourceAsStream("/player/link_up_1.png"));
				up2 = ImageIO.read(getClass().getResourceAsStream("/player/link_up_2.png"));
				down1 = ImageIO.read(getClass().getResourceAsStream("/player/link_down_1.png"));
				down2 = ImageIO.read(getClass().getResourceAsStream("/player/link_down_2.png"));
				left1 = ImageIO.read(getClass().getResourceAsStream("/player/link_left_1.png"));
				left2 = ImageIO.read(getClass().getResourceAsStream("/player/link_left_2.png"));
				right1 = ImageIO.read(getClass().getResourceAsStream("/player/link_right_1.png"));
				right2 = ImageIO.read(getClass().getResourceAsStream("/player/link_right_2.png"));
				downAttack  = ImageIO.read(getClass().getResourceAsStream("/player/link_attack_down.png"));
				downSwordAttack1  = ImageIO.read(getClass().getResourceAsStream("/player/link_sword_attack_down_1.png"));
				downSwordAttack2  = ImageIO.read(getClass().getResourceAsStream("/player/link_sword_attack_down_2.png"));
				downSwordAttack3  = ImageIO.read(getClass().getResourceAsStream("/player/link_sword_attack_down_3.png"));
				leftAttack  = ImageIO.read(getClass().getResourceAsStream("/player/link_attack_left.png"));
				leftSwordAttack1  = ImageIO.read(getClass().getResourceAsStream("/player/link_sword_attack_left_1.png"));
				leftSwordAttack2  = ImageIO.read(getClass().getResourceAsStream("/player/link_sword_attack_left_2.png"));
				leftSwordAttack3  = ImageIO.read(getClass().getResourceAsStream("/player/link_sword_attack_left_3.png"));
				rightAttack  = ImageIO.read(getClass().getResourceAsStream("/player/link_attack_right.png"));
				rightSwordAttack1  = ImageIO.read(getClass().getResourceAsStream("/player/link_sword_attack_right_1.png"));
				rightSwordAttack2  = ImageIO.read(getClass().getResourceAsStream("/player/link_sword_attack_right_2.png"));
				rightSwordAttack3  = ImageIO.read(getClass().getResourceAsStream("/player/link_sword_attack_right_3.png"));
				upAttack  = ImageIO.read(getClass().getResourceAsStream("/player/link_attack_up.png"));
				upSwordAttack1  = ImageIO.read(getClass().getResourceAsStream("/player/link_sword_attack_up_1.png"));
				upSwordAttack2  = ImageIO.read(getClass().getResourceAsStream("/player/link_sword_attack_up_2.png"));
				upSwordAttack3  = ImageIO.read(getClass().getResourceAsStream("/player/link_sword_attack_up_3.png"));
			}
			else if (characterName.equals("Mario")) {
				up1 = ImageIO.read(getClass().getResourceAsStream("/player/mario_up_1.png"));
				up2 = ImageIO.read(getClass().getResourceAsStream("/player/mario_up_2.png"));
				down1 = ImageIO.read(getClass().getResourceAsStream("/player/mario_down_1.png"));
				down2 = ImageIO.read(getClass().getResourceAsStream("/player/mario_down_2.png"));
				left1 = ImageIO.read(getClass().getResourceAsStream("/player/mario_left_1.png"));
				left2 = ImageIO.read(getClass().getResourceAsStream("/player/mario_left_2.png"));
				right1 = ImageIO.read(getClass().getResourceAsStream("/player/mario_right_1.png"));
				right2 = ImageIO.read(getClass().getResourceAsStream("/player/mario_right_2.png"));
			}
			else if (characterName.equals("Pokemon Trainer")) {
				up1 = ImageIO.read(getClass().getResourceAsStream("/player/pkmn_trainer_up_1.png"));
				up2 = ImageIO.read(getClass().getResourceAsStream("/player/pkmn_trainer_up_2.png"));
				down1 = ImageIO.read(getClass().getResourceAsStream("/player/pkmn_trainer_down_1.png"));
				down2 = ImageIO.read(getClass().getResourceAsStream("/player/pkmn_trainer_down_2.png"));
				left1 = ImageIO.read(getClass().getResourceAsStream("/player/pkmn_trainer_left_1.png"));
				left2 = ImageIO.read(getClass().getResourceAsStream("/player/pkmn_trainer_left_2.png"));
				right1 = ImageIO.read(getClass().getResourceAsStream("/player/pkmn_trainer_right_1.png"));
				right2 = ImageIO.read(getClass().getResourceAsStream("/player/pkmn_trainer_right_2.png"));
			}

		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		if (keyH.upPressed == true && !attackBuffer) {
			direction = "up";	
		}
		else if (keyH.downPressed == true&& !attackBuffer) {
			direction = "down";

		}
		else if (keyH.leftPressed  == true&& !attackBuffer){
			direction = "left";
		}
		else if (keyH.rightPressed == true&& !attackBuffer) {
			direction = "right";

		}
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		if (!collisionOn) {

				switch(direction) {
				case "up":	
				if (keyH.upPressed == true&& !attackBuffer) {
					worldY -= speed;	
				}
					
					break;
				case "down":
					if (keyH.downPressed == true&& !attackBuffer) {
						worldY += speed;

					}
					break;
				case "left":
					if (keyH.leftPressed  == true&& !attackBuffer){
						worldX -= speed;
					}

					break;
				case "right":
					if (keyH.rightPressed == true&& !attackBuffer) {
						worldX += speed;

					}

					break;
				}
			

			
		}
		
		if (keyH.pPressed == true && !attack) {
			spriteAttackNum = 1;
			attack = true;
			attackBuffer = true;
			spriteNum = 1;
		}
		else if (keyH.pPressed == false && attack && spriteAttackNum == 0) {
			attackBuffer = false;
			if (!attackBuffer) {
				attack = false;
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
			if (attack) {
				if (spriteAttackNum == 1) {
					spriteAttackNum = 2;
				}
				else if (spriteAttackNum == 2) {
					spriteAttackNum = 3;

				}
				else if (spriteAttackNum == 3) {
					spriteAttackNum = 4;
				}
				else if (spriteAttackNum == 4) {
					spriteAttackNum = 0;
				}
			}

			spriteCounter = 0;
		}
		
		
	}
	
	public void draw(Graphics2D g2) {

		
		BufferedImage image = null;
		BufferedImage weaponImage = null;

		
		switch(direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			if (spriteAttackNum == 1 && attack) {
				image = upAttack;

			}
			if (spriteAttackNum == 2 && attack) {
				image = upAttack;
				weaponImage = upSwordAttack1;
				
			}
			if (spriteAttackNum == 3 && attack) {
				image = up1;
				weaponImage = upSwordAttack2;
				
			}
			if (spriteAttackNum == 4 && attack) {
				image = up2;
				weaponImage = upSwordAttack3;
			}

			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			if (spriteAttackNum == 1 && attack) {
				image = downAttack;

			}
			if (spriteAttackNum == 2 && attack) {
				image = downAttack;
				weaponImage = downSwordAttack1;
				
			}
			if (spriteAttackNum == 3 && attack) {
				image = down1;
				weaponImage = downSwordAttack2;
				
			}
			if (spriteAttackNum == 4 && attack) {
				image = down2;
				weaponImage = downSwordAttack3;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			if (spriteAttackNum == 1 && attack) {
				image = leftAttack;

			}
			if (spriteAttackNum == 2 && attack) {
				image = leftAttack;
				weaponImage = leftSwordAttack1;
				
			}
			if (spriteAttackNum == 3 && attack) {
				image = left2;
				weaponImage = leftSwordAttack2;
				
			}
			if (spriteAttackNum == 4 && attack) {
				image = left1;
				weaponImage = leftSwordAttack3;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			if (spriteAttackNum == 1 && attack) {
				image = rightAttack;

			}
			if (spriteAttackNum == 2 && attack) {
				image = rightAttack;
				weaponImage = rightSwordAttack1;
				
			}
			if (spriteAttackNum == 3 && attack) {
				image = right2;
				weaponImage = rightSwordAttack2;
				
			}
			if (spriteAttackNum == 4 && attack) {
				image = right1;
				weaponImage = rightSwordAttack3;
			}
			break;
		}
		
		
		
		
		
		
		g2.drawImage(image, screenX,screenY, gp.tileSize, gp.tileSize, null);
		g2.setColor(Color.BLACK);
		g2.drawRect(screenX + hitBox.x, screenY+ hitBox.y, hitBox.width, hitBox.height);
		if (attack && characterName.equals("Link") && spriteAttackNum > 0 ) {
			int attackMoveY = 0;
			int attackMoveX = 0;
			if (direction.equals("down")) {
				attackMoveY = 42;
			}
			else if (direction.equals("left")) {
				attackMoveX = -33;
			}
			else if (direction.equals("right")) {
				attackMoveX = 33;
			}
			else if (direction.equals("up")) {
				attackMoveY = -42;
			}
			g2.drawImage(weaponImage, screenX+attackMoveX, screenY+attackMoveY, gp.tileSize, gp.tileSize, null);
		}
		
		
		
		
		
	}

}
