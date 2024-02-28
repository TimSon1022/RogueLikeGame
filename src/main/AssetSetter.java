package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	
	public AssetSetter(GamePanel gp, DungeonGenerator dungeon) {
		this.gp = gp;
		this.dungeon = dungeon;
		rooms = new ArrayList<Room>(dungeon.getRooms());
		rooms.remove(rooms.size()-1);
		
		
	}
	
	public void setObject() {
	    Random random = new Random();

	    for (int i = 0; i < 15; i++) {
	        if (!rooms.isEmpty()) {
	            gp.obj[i] = new ObjectChest();
	            int roomNum = random.nextInt(rooms.size());

	            // Set the object's position, ensuring it is unique
	            setUniqueRandomPosition(gp.obj, i, rooms.get(roomNum), random);

	            rooms.remove(roomNum);
	        }
	    }
		rooms = new ArrayList<Room>(dungeon.getRooms());
		rooms.remove(rooms.size()-1);
		
	    for (int i = 15; i < 20; i++) {
	        if (!rooms.isEmpty()) {
	            gp.obj[i] = new ObjectAttackBoost();
	            int roomNum = random.nextInt(rooms.size());

	            // Set the object's position, ensuring it is unique
	            setUniqueRandomPosition(gp.obj, i, rooms.get(roomNum), random);


	        }
	    }

	    for (int i = 20; i < 25; i++) {
	        if (!rooms.isEmpty()) {
	            gp.obj[i] = new ObjectDefenseBoost();
	            int roomNum = random.nextInt(rooms.size());

	            // Set the object's position, ensuring it is unique
	            setUniqueRandomPosition(gp.obj, i, rooms.get(roomNum), random);


	        }
	    }

	    for (int i = 25; i < 30; i++) {
	        if (!rooms.isEmpty()) {
	            gp.obj[i] = new ObjectHealthBoost();
	            int roomNum = random.nextInt(rooms.size());

	            // Set the object's position, ensuring it is unique
	            setUniqueRandomPosition(gp.obj, i, rooms.get(roomNum), random);


	        }
	    }

	    for (int i = 30; i < 35; i++) {
	        if (!rooms.isEmpty()) {
	            gp.obj[i] = new ObjectHealthPotion();
	            int roomNum = random.nextInt(rooms.size());

	            // Set the object's position, ensuring it is unique
	            setUniqueRandomPosition(gp.obj, i, rooms.get(roomNum), random);


	        }
	    }

	    for (int i = 35; i < 55; i++) {
	        gp.obj[i] = new ObjectGold();
	        int roomNum = random.nextInt(rooms.size());

	        // Set the object's position, ensuring it is unique
	        setUniqueRandomPosition(gp.obj, i, rooms.get(roomNum), random);
	    }

	    for (int i = 55; i < 70; i++) {
	        if (!rooms.isEmpty()) {
	            gp.obj[i] = new ObjectKey();
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
	    int xLength = (room.x + room.width - 1) - (room.x + 1);
	    int yLength = (room.y + room.height - 1) - (room.y + 1);
	    object.worldX = (random.nextInt(xLength) + (room.x + 1)) * gp.tileSize;
	    object.worldY = (random.nextInt(yLength) + (room.y + 1)) * gp.tileSize;
	}

}
