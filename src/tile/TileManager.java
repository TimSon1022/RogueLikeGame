package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import main.DungeonGenerator;
import main.GamePanel;
import main.Room;
import main.UtilityTool;

public class TileManager {

    GamePanel gp;
    DungeonGenerator dungeon;
    public char[][] dungeonTiles;
    public Tile[][] dungeonMap;
    
    List<Room> rooms;
    public int playerX;
    public int playerY;

    UtilityTool uTool;
    


    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.dungeon = gp.dungeon;
        uTool = new UtilityTool();        
        rooms = new ArrayList<>(dungeon.getRooms());

        
		dungeonTiles = gp.dungeon.matrix;
		dungeonMap = new Tile[dungeonTiles.length][dungeonTiles[0].length];
		generateDungeons();
    }
    
    
    public void generateDungeons() {

            for (int i = 0; i < dungeonTiles.length; i++) {
                for (int j = 0; j < dungeonTiles[0].length; j++) {
                    char tileChar = dungeonTiles[i][j];
                    if (tileChar == '.' || tileChar == '@' || tileChar == 'F') {
                    	if (tileChar == '@') {
                    		playerX = j*gp.tileSize;
                    		playerY = i*gp.tileSize;
                    	}
                    	setup(i,j,"dungeon", false);
                    	
                    	
                    } else if (tileChar == '#') {
                    	setup(i,j,"water", false);
                    } else if (tileChar == '|' || tileChar == '-') {
                    	setup(i,j,"wall", true);
                    }  
                    else {
                    	setup(i,j,"boundary", true);
                    }
                }
                
            }


    	

    }
    
    public void setup(int i, int j, String imageName, boolean collision) {
    	try {
    		dungeonMap[i][j] = new Tile();
    		dungeonMap[i][j].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + "_tiles.png"));
    		dungeonMap[i][j].image = uTool.scaleImage(dungeonMap[i][j].image, gp.tileSize, gp.tileSize);
    		dungeonMap[i][j].collision = collision;

    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    }
    



    public void draw(Graphics2D g2){
    	int worldCol = 0;
    	int worldRow = 0;


    	
    	while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
    	    int index = dungeon.getRoomIndexAt(gp.player.worldX/gp.tileSize, gp.player.worldY/gp.tileSize);
    	    int worldX = worldCol * gp.tileSize;
    	    int worldY = worldRow * gp.tileSize;
    	    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    	    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    	    if ((index != -1 && isWithinBounds(worldCol, worldRow, worldX, worldY, gp.player.worldX, gp.player.worldY, gp.player.screenX, gp.player.screenY, rooms.get(index))) || 
    	        (index == -1 && isWithinBounds(worldCol, worldRow, worldX, worldY, gp.player.worldX, gp.player.worldY, gp.player.screenX - 480, gp.player.screenY - 355, null))) {
    	        g2.drawImage(dungeonMap[worldRow][worldCol].image, screenX, screenY, null);

    	    }


    	    worldCol++;
    	    if (worldCol == gp.maxWorldCol) {
    	        worldCol = 0;
    	        worldRow++;
    	    }
    	}

    	
      
    }
    
 // Helper method
    private boolean isWithinBounds(int worldCol, int worldRow, int worldX, int worldY, int playerWorldX, int playerWorldY, int playerScreenX, int playerScreenY, Room room) {
        return worldX + gp.tileSize > playerWorldX - playerScreenX &&
               worldX - gp.tileSize < playerWorldX + playerScreenX &&
               worldY + gp.tileSize > playerWorldY - playerScreenY &&
               worldY - gp.tileSize < playerWorldY + playerScreenY &&
               (room == null || (worldCol >= room.x && worldCol < room.x + room.width && worldRow >= room.y && worldRow < room.y + room.height));
    }
    

}
