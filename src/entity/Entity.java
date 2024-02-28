package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	
	public int worldX, worldY;
	public int speed;
	
	
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
	public Rectangle hitBox;
	public int hitBoxDefaultX, hitBoxDefaultY;
	
	public boolean collisionOn = false;

}
