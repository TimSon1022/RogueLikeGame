package main;

import entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}

	
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = (int)entity.worldX + entity.hitBox.x;
		int entityRightWorldX = (int)entity.worldX + entity.hitBox.x + entity.hitBox.width;
		int entityTopWorldY = (int)entity.worldY + entity.hitBox.y;
		int entityBottomWorldY = (int)entity.worldY + entity.hitBox.y + entity.hitBox.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		
		switch(entity.direction) {
		
		case "up":
			entityTopRow = (int) ((entityTopWorldY - entity.speed)/gp.tileSize);
			if (gp.tileM.dungeonMap[entityTopRow][entityLeftCol].collision == true||
					gp.tileM.dungeonMap[entityTopRow][entityRightCol].collision == true) {
				entity.collisionOn = true;
			}
			
			break;
			
		case "down":
			entityBottomRow = (int) ((entityBottomWorldY + entity.speed)/gp.tileSize);
			if (gp.tileM.dungeonMap[entityBottomRow][entityLeftCol].collision == true||
					gp.tileM.dungeonMap[entityBottomRow][entityRightCol].collision == true) {
				entity.collisionOn = true;
			}
			break;
			
		case "left":
			entityLeftCol = (int) ((entityLeftWorldX - entity.speed)/gp.tileSize);
			if (gp.tileM.dungeonMap[entityTopRow][entityLeftCol].collision == true||
					gp.tileM.dungeonMap[entityBottomRow][entityLeftCol].collision == true) {
				entity.collisionOn = true;
			}
			break;
			
		case "right":
			entityRightCol = (int) ((entityRightWorldX + entity.speed)/gp.tileSize);
			if (gp.tileM.dungeonMap[entityTopRow][entityRightCol].collision == true||
					gp.tileM.dungeonMap[entityBottomRow][entityRightCol].collision == true) {
				entity.collisionOn = true;
			}
			break;
			
		
		
		}	
		
	}
	
	public int checkObject(Entity entity, boolean player) {
		int index = 999;
		
		for (int i = 0; i < gp.obj.length; i++) {
			if (gp.obj[i] != null) {
				entity.hitBox.x = (int)entity.worldX + entity.hitBox.x;
				entity.hitBox.y = (int)entity.worldY + entity.hitBox.y;
				gp.obj[i].hitBox.x = gp.obj[i].worldX + gp.obj[i].hitBox.x;
				gp.obj[i].hitBox.y = gp.obj[i].worldY + gp.obj[i].hitBox.y;
			
				switch(entity.direction) {
				case "up":
					
					entity.hitBox.y -= entity.speed;
					if (entity.hitBox.intersects(gp.obj[i].hitBox)) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;
						}
					}
					
					
					
					break;
				case "down":
					
					entity.hitBox.y += entity.speed;
					if (entity.hitBox.intersects(gp.obj[i].hitBox)) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;
						}
					}
					
					break;
				case "left":
					
					entity.hitBox.x -= entity.speed;
					if (entity.hitBox.intersects(gp.obj[i].hitBox)) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;
						}
					}
					break;
				case "right":
					
					entity.hitBox.x += entity.speed;
					if (entity.hitBox.intersects(gp.obj[i].hitBox)) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;
						}
					}
					
					break;
				}
				
				entity.hitBox.x = entity.hitBoxDefaultX;
				entity.hitBox.y = entity.hitBoxDefaultY;
				gp.obj[i].hitBox.x = gp.obj[i].hitBoxDefaultX;
				gp.obj[i].hitBox.y = gp.obj[i].hitBoxDefaultY;
			}
		}
		
		
		return index;

		
	}
	//NPC or Enemies
	public int checkEntity(Entity entity, Entity[] entities) {

		int index = 999;
		
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null) {
				entity.hitBox.x = (int)entity.worldX + entity.hitBox.x;
				entity.hitBox.y = (int)entity.worldY + entity.hitBox.y;
				entities[i].hitBox.x = (int)entities[i].worldX + entities[i].hitBox.x;
				entities[i].hitBox.y = (int)entities[i].worldY + entities[i].hitBox.y;
			
				switch(entity.direction) {
				case "up":
					
					entity.hitBox.y -= entity.speed;
					if (entity.hitBox.intersects(entities[i].hitBox)) {
							entity.collisionOn = true;
							index = i;
					}
				
					break;
				case "down":
					
					entity.hitBox.y += entity.speed;
					if (entity.hitBox.intersects(entities[i].hitBox)) {
							entity.collisionOn = true;
							index = i;
					}
					
					break;
				case "left":
					
					entity.hitBox.x -= entity.speed;
					if (entity.hitBox.intersects(entities[i].hitBox)) {
							entity.collisionOn = true;
							index = i;
					}
					break;
				case "right":
					
					entity.hitBox.x += entity.speed;
					if (entity.hitBox.intersects(entities[i].hitBox)) {
							entity.collisionOn = true;
							index = i;
					}
					
					break;
				}
				
				entity.hitBox.x = entity.hitBoxDefaultX;
				entity.hitBox.y = entity.hitBoxDefaultY;
				entities[i].hitBox.x = entities[i].hitBoxDefaultX;
				entities[i].hitBox.y = entities[i].hitBoxDefaultY;
			}
		}
		
		
		return index;

		
	
	}
	
	public void checkPlayer(Entity entity) {

			entity.hitBox.x = (int)entity.worldX + entity.hitBox.x;
			entity.hitBox.y = (int)entity.worldY + entity.hitBox.y;
			gp.player.hitBox.x = (int)gp.player.worldX + gp.player.hitBox.x;
			gp.player.hitBox.y = (int)gp.player.worldY + gp.player.hitBox.y;
		
			switch(entity.direction) {
			case "up":
				
				entity.hitBox.y -= entity.speed;
				if (entity.hitBox.intersects(gp.player.hitBox)) {
						entity.collisionOn = true;
				}
			
				break;
			case "down":
				
				entity.hitBox.y += entity.speed;
				if (entity.hitBox.intersects(gp.player.hitBox)) {
						entity.collisionOn = true;
				}
				
				break;
			case "left":
				
				entity.hitBox.x -= entity.speed;
				if (entity.hitBox.intersects(gp.player.hitBox)) {
						entity.collisionOn = true;
				}
				break;
			case "right":
				
				entity.hitBox.x += entity.speed;
				if (entity.hitBox.intersects(gp.player.hitBox)) {
						entity.collisionOn = true;
				}
				
				break;
			}
			
			entity.hitBox.x = entity.hitBoxDefaultX;
			entity.hitBox.y = entity.hitBoxDefaultY;
			gp.player.hitBox.x = gp.player.hitBoxDefaultX;
			gp.player.hitBox.y = gp.player.hitBoxDefaultY;

		
		
		
	}
	
	
}
