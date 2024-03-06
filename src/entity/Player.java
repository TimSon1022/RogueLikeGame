package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;


public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	
	double healthCounter = 0;
	public int goldTotal = 0;

	public int healthPotionTotal = 0;
	public int keyTotal = 0;
	public int fullHealth = 5;
	public int combatHealth = 5;
	public double xp = 0;
	public int attackStat = 5;
	public int defenseStat = 5;


	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		hitBox = new Rectangle();
		hitBox.x = 4;
		hitBox.y = 4;
		hitBoxDefaultX = hitBox.x;
		hitBoxDefaultY = hitBox.y;
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
		 if (characterName.equals("Link")) {
		        up1 = setup("link_up_1");
		        up2 = setup("link_up_2");
		        down1 = setup("link_down_1");
		        down2 = setup("link_down_2");
		        left1 = setup("link_left_1");
		        left2 = setup("link_left_2");
		        right1 = setup("link_right_1");
		        right2 = setup("link_right_2");
		        downAttack = setup("link_attack_down");
		        downSwordAttack1 = setup("link_sword_attack_down_1");
		        downSwordAttack2 = setup("link_sword_attack_down_2");
		        downSwordAttack3 = setup("link_sword_attack_down_3");
		        leftAttack = setup("link_attack_left");
		        leftSwordAttack1 = setup("link_sword_attack_left_1");
		        leftSwordAttack2 = setup("link_sword_attack_left_2");
		        leftSwordAttack3 = setup("link_sword_attack_left_3");
		        rightAttack = setup("link_attack_right");
		        rightSwordAttack1 = setup("link_sword_attack_right_1");
		        rightSwordAttack2 = setup("link_sword_attack_right_2");
		        rightSwordAttack3 = setup("link_sword_attack_right_3");
		        upAttack = setup("link_attack_up");
		        upSwordAttack1 = setup("link_sword_attack_up_1");
		        upSwordAttack2 = setup("link_sword_attack_up_2");
		        upSwordAttack3 = setup("link_sword_attack_up_3");
		    } 
		 else if (characterName.equals("Mario")) {
		        up1 = setup("mario_up_1");
		        up2 = setup("mario_up_2");
		        down1 = setup("mario_down_1");
		        down2 = setup("mario_down_2");
		        left1 = setup("mario_left_1");
		        left2 = setup("mario_left_2");
		        right1 = setup("mario_right_1");
		        right2 = setup("mario_right_2");
		    } 
		 else if (characterName.equals("Pokemon Trainer")) {
		        up1 = setup("pkmn_trainer_up_1");
		        up2 = setup("pkmn_trainer_up_2");
		        down1 = setup("pkmn_trainer_down_1");
		        down2 = setup("pkmn_trainer_down_2");
		        left1 = setup("pkmn_trainer_left_1");
		        left2 = setup("pkmn_trainer_left_2");
		        right1 = setup("pkmn_trainer_right_1");
		        right2 = setup("pkmn_trainer_right_2");
		    }
		
	}
	
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		return image;
		
	}
	
	public void update() {
		if (combatHealth < fullHealth) {
			healthCounter+= (double)1/100;
			if (healthCounter >= 10) {
				healthCounter = 0;
				combatHealth++;
			}
		}


		

		
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
		
		int objIndex = gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
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
					if (keyH.leftPressed  == true && !attackBuffer){
						worldX -= speed;
					}

					break;
				case "right":
					if (keyH.rightPressed == true && !attackBuffer) {
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
	
	
	public void pickUpObject(int i) {
		if (i != 999) {
			
			String objectName = gp.obj[i].name;
			switch(objectName) {
			case "attack_boost":
				

				attackStat++;
				gp.playSE(1);
				gp.obj[i] = null;
				break;
			case "chest":
				if (keyTotal > 0 && !gp.obj[i].opened) {
					try {
						gp.playSE(3);
						gp.obj[i].image = ImageIO.read(getClass().getResourceAsStream("/objects/chest_2.png"));
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
					keyTotal--;
					gp.obj[i].opened = true;
				}
				else if (keyTotal == 0 && !gp.obj[i].opened){
					gp.ui.showMessage("you need a key!");
				}
				break;
			case "defense_boost":
				
				defenseStat++;
				gp.playSE(1);
				gp.obj[i] = null;
				
				break;
			case "gold":
				
				goldTotal++;
				gp.playSE(0);
				gp.obj[i] = null;
				break;
			case "health_boost":
				
				fullHealth ++;
				gp.playSE(1);
				gp.obj[i] = null;
				
				break;
			case "health_potion":
				
				healthPotionTotal++;
				gp.playSE(1);
				gp.obj[i] = null;
				break;
			case "key":
				keyTotal++;
				gp.playSE(2);
				gp.obj[i] = null;

				
				break;
			}
			
			
			
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
		if (attack && characterName.equals("Link") && spriteAttackNum > 0 ) {
			int attackMoveY = 0;
			int attackMoveX = 0;
			if (direction.equals("down")) {
				attackMoveY = 28;
			}
			else if (direction.equals("left")) {
				attackMoveX = -22;
			}
			else if (direction.equals("right")) {
				attackMoveX = 22;
			}
			else if (direction.equals("up")) {
				attackMoveY = -28;
			}
			g2.drawImage(weaponImage, screenX+attackMoveX, screenY+attackMoveY, null);
		}
		
		
		
		
		
	}
	
	
	
	

}
