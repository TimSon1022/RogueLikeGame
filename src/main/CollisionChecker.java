package main;

import entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}

	
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.hitBox.x;
		int entityRightWorldX = entity.worldX + entity.hitBox.x + entity.hitBox.width;
		int entityTopWorldY = entity.worldY + entity.hitBox.y;
		int entityBottomWorldY = entity.worldY + entity.hitBox.y + entity.hitBox.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		
		switch(entity.direction) {
		
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			if (gp.tileM.dungeonMap[entityTopRow][entityLeftCol].collision == true||
					gp.tileM.dungeonMap[entityTopRow][entityRightCol].collision == true) {
				entity.collisionOn = true;
			}
			
			break;
			
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			if (gp.tileM.dungeonMap[entityBottomRow][entityLeftCol].collision == true||
					gp.tileM.dungeonMap[entityBottomRow][entityRightCol].collision == true) {
				entity.collisionOn = true;
			}
			break;
			
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			if (gp.tileM.dungeonMap[entityTopRow][entityLeftCol].collision == true||
					gp.tileM.dungeonMap[entityBottomRow][entityLeftCol].collision == true) {
				entity.collisionOn = true;
			}
			break;
			
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			if (gp.tileM.dungeonMap[entityTopRow][entityRightCol].collision == true||
					gp.tileM.dungeonMap[entityBottomRow][entityRightCol].collision == true) {
				entity.collisionOn = true;
			}
			break;
			
		
		
		}
		
		
		
		
	}
}
