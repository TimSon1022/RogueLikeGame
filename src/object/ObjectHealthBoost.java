package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjectHealthBoost extends SuperObject{
	GamePanel gp;
	
	public ObjectHealthBoost(GamePanel gp) {
		this.gp = gp;
		name = "health_boost";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/health_boost.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
	