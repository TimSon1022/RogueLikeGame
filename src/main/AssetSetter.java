package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.Entity;
import entity.NPCOldMan;
import object.ObjectAttackBoost;
import object.ObjectChest;
import object.ObjectDefenseBoost;
import object.ObjectGold;
import object.ObjectHealthBoost;
import object.ObjectHealthPotion;
import object.ObjectKey;
import object.SuperObject;

public class AssetSetter {
	
	
	GamePanel gp;
	DungeonGenerator dungeon;
	List<Room> rooms;
	Random random = new Random();
	public AssetSetter(GamePanel gp, DungeonGenerator dungeon) {
		this.gp = gp;
		this.dungeon = dungeon;
		rooms = new ArrayList<Room>(dungeon.getRooms());
		rooms.remove(dungeon.playerRoomIndex);
		rooms.remove(rooms.size()-1);
		
		
		
	}
	
	public void setObject() {
	    

	    for (int i = 0; i < 15; i++) {
	        if (!rooms.isEmpty()) {
	            gp.obj[i] = new ObjectChest(gp);
	            int roomNum = random.nextInt(rooms.size());

	            // Set the object's position, ensuring it is unique
	            setUniqueRandomPosition(gp.obj, i, rooms.get(roomNum), random);

	            rooms.remove(roomNum);
	        }
	    }
		rooms = new ArrayList<Room>(dungeon.getRooms());
		rooms.remove(dungeon.playerRoomIndex);
		rooms.remove(rooms.size()-1);
		
	    for (int i = 15; i < 20; i++) {
	        if (!rooms.isEmpty()) {
	            gp.obj[i] = new ObjectAttackBoost(gp);
	            int roomNum = random.nextInt(rooms.size());

	            // Set the object's position, ensuring it is unique
	            setUniqueRandomPosition(gp.obj, i, rooms.get(roomNum), random);


	        }
	    }

	    for (int i = 20; i < 25; i++) {
	        if (!rooms.isEmpty()) {
	            gp.obj[i] = new ObjectDefenseBoost(gp);
	            int roomNum = random.nextInt(rooms.size());

	            // Set the object's position, ensuring it is unique
	            setUniqueRandomPosition(gp.obj, i, rooms.get(roomNum), random);


	        }
	    }

	    for (int i = 25; i < 30; i++) {
	        if (!rooms.isEmpty()) {
	            gp.obj[i] = new ObjectHealthBoost(gp);
	            int roomNum = random.nextInt(rooms.size());

	            // Set the object's position, ensuring it is unique
	            setUniqueRandomPosition(gp.obj, i, rooms.get(roomNum), random);


	        }
	    }

	    for (int i = 30; i < 35; i++) {
	        if (!rooms.isEmpty()) {
	            gp.obj[i] = new ObjectHealthPotion(gp);
	            int roomNum = random.nextInt(rooms.size());

	            // Set the object's position, ensuring it is unique
	            setUniqueRandomPosition(gp.obj, i, rooms.get(roomNum), random);


	        }
	    }

	    for (int i = 35; i < 55; i++) {
	        gp.obj[i] = new ObjectGold(gp);
	        int roomNum = random.nextInt(rooms.size());

	        // Set the object's position, ensuring it is unique
	        setUniqueRandomPosition(gp.obj, i, rooms.get(roomNum), random);
	    }

	    for (int i = 55; i < 70; i++) {
	        if (!rooms.isEmpty()) {
	            gp.obj[i] = new ObjectKey(gp);
	            int roomNum = random.nextInt(rooms.size());

	            // Set the object's position, ensuring it is unique
	            setUniqueRandomPosition(gp.obj, i, rooms.get(roomNum), random);

	            rooms.remove(roomNum);
	        }
	    }
	}
	
	
	// Helper method to set the object's position, ensuring it is unique
	private void setUniqueRandomPosition(SuperObject[] objects, int currentIndex, Room room, Random random) {
	    do {
	        setRandomPosition(objects[currentIndex], room, random);
	    } while (isPositionOccupied(objects, currentIndex));
	}

	// Helper method to check if the position is occupied by another object
	private boolean isPositionOccupied(SuperObject[] objects, int currentIndex) {
	    for (int j = 0; j < currentIndex; j++) {
	        if (objects[j].worldX == objects[currentIndex].worldX && objects[j].worldY == objects[currentIndex].worldY) {
	            return true; // Position is occupied
	        }
	    }
	    return false; // Position is not occupied
	}

	// Helper method to set the object's position
	private void setRandomPosition(SuperObject object, Room room, Random random) {
	    int xLength = (room.x + room.width - 3) - (room.x + 3);
	    int yLength = (room.y + room.height - 3) - (room.y + 3);
	    object.worldX = (random.nextInt(xLength) + (room.x + 3)) * gp.tileSize;
	    object.worldY = (random.nextInt(yLength) + (room.y + 3)) * gp.tileSize;
	}
	
	public void setNPC() {
		List <Room> newRooms = new ArrayList<Room>(dungeon.getRooms());
		newRooms.remove(dungeon.playerRoomIndex);
		newRooms.remove(rooms.size()-1);
		
		for (int i = 0; i < newRooms.size(); i++) {
	    	if (newRooms.get(i).width > 15 && newRooms.get(i).height > 15) {
	    		newRooms.remove(i);
	    	}
		}
		
		gp.npc[0] = new NPCOldMan(gp);
		int roomNum = random.nextInt(newRooms.size());
	    setUniqueRandomPositionForEntities(gp.npc[0], gp.npc, newRooms.get(roomNum), gp.obj);
	    
	    

		
	}
	
	// Helper method to set the NPC's position, ensuring it is unique and not overlapping with objects
    private void setUniqueRandomPositionForEntities(Entity entity, Entity[] entities, Room room, SuperObject[] objects) {
    	
        Random random = new Random();
        do {
        	setRandomPositionForEntity(entity, room, random);
        } while (isPositionOccupiedByObjects(entity, objects) || isPositionOccupiedByOtherEntities(entity, entities));
    }

    // Helper method to check if the position is occupied by game objects
    private boolean isPositionOccupiedByObjects(Entity entity, SuperObject[] objects) {
        for (SuperObject object : objects) {
            if (object != null && object.worldX == entity.worldX && object.worldY == entity.worldY) {
                return true; // Position is occupied by objects
            }
        }
        return false; // Position is not occupied
    }

    // Helper method to check if the position is occupied by other NPCs
    private boolean isPositionOccupiedByOtherEntities(Entity entity, Entity[] entities) {
        for (Entity otherEntity : entities) {
            if (otherEntity != null && otherEntity != entity && otherEntity.worldX == entity.worldX && otherEntity.worldY == entity.worldY) {
                return true; 
            }
        }
        return false; // Position is not occupied
    }

    private void setRandomPositionForEntity(Entity entity, Room room, Random random) {
        int xMin = room.x + 1;
        int xMax = room.x + room.width - 2;
        int yMin = room.y + 1;
        int yMax = room.y + room.height - 2;

        do {
        	entity.worldX = (random.nextInt(xMax - xMin + 1) + xMin) * gp.tileSize;
        	entity.worldY = (random.nextInt(yMax - yMin + 1) + yMin) * gp.tileSize;
        } while (!isValidEntityPosition(entity, room));
        
        gp.dungeon.matrix[(int)entity.worldY/gp.tileSize][(int)entity.worldX/gp.tileSize] = 'N';
        gp.dungeon.printDungeon();
    }

    private boolean isValidEntityPosition(Entity entity, Room room) {
        int x = (int)entity.worldX / gp.tileSize;
        int y = (int)entity.worldY / gp.tileSize;


        return ((x == room.x + 1 || x == room.x + room.width - 2) && (room.x + 1 <= x && x < room.x + room.width - 1) || ((y == room.y + 1 || y == room.y + room.height - 2) && (room.y + 1 <= y && y < room.y + room.height - 1)));
    }

}
