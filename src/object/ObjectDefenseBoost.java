package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjectDefenseBoost extends SuperObject{
	GamePanel gp;
	
	public ObjectDefenseBoost(GamePanel gp) {
		this.gp = gp;
		name = "defense_boost";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/defense_boost.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
