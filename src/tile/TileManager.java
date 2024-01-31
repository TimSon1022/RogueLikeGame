package tile;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class TileManager {

    GamePanel gp;
    public char[][] dungeonTiles;
    public Tile[][] dungeonMap;
    public int playerX;
    public int playerY;
    Tile[] tile;


    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
        
		DungeonGenerator dungeon = new DungeonGenerator();

		dungeonTiles = dungeon.matrix;
		dungeonMap = new Tile[dungeonTiles.length][dungeonTiles[0].length];
		generateDungeons();
    }
    
    public void getTileImage() {
    	
    	try {
    		tile[0] = new Tile();
    		tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dungeon_tiles.png"));
    		
    		tile[1] = new Tile();
    		tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water_tiles.png"));

    		tile[2] = new Tile();
    		tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_tiles.png"));

    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    }

    
    

    public void generateDungeons() {
    	try {
            for (int i = 0; i < dungeonTiles.length; i++) {
                for (int j = 0; j < dungeonTiles[0].length; j++) {
                    char tileChar = dungeonTiles[i][j];
                    dungeonMap[i][j] = new Tile();

                    if (tileChar == '.' || tileChar == '@' || tileChar == 'F') {
                    	if (tileChar == '@') {
                    		playerX = j*gp.tileSize;
                    		playerY = i*gp.tileSize;
                    	}
                    	dungeonMap[i][j].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dungeon_tiles.png"));
                    	
                    } else if (tileChar == '#') {
                    	dungeonMap[i][j].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water_tiles.png"));
                    } else if (tileChar == '|' || tileChar == '-') {
                    	dungeonMap[i][j].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_tiles.png"));
                    	dungeonMap[i][j].collision = true;
                    }  
                    else {
                    	dungeonMap[i][j].image = ImageIO.read(getClass().getResourceAsStream("/tiles/boundary_tiles.png"));
                    	dungeonMap[i][j].collision = true;
                    }
                }
                
            }
    	}
    	
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }


    public void draw(Graphics2D g2){
    	int worldCol = 0;
    	int worldRow = 0;

    	
    	while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
    		int worldX = worldCol * gp.tileSize;
    		int worldY = worldRow * gp.tileSize;
    		int screenX = worldX - gp.player.worldX + gp.player.screenX;
    		int screenY = worldY - gp.player.worldY + gp.player.screenY;
    		
    		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
    				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
    				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
    				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
    			g2.drawImage(dungeonMap[worldRow][worldCol].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    		}
    		
    		worldCol++;
    		if (worldCol == gp.maxWorldCol) {
    			worldCol = 0;
    			worldRow++;
    		}
    		
    	}

    	
      
    }
    

}
