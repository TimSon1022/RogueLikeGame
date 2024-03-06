package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjectHealthPotion extends SuperObject{
	GamePanel gp;
	
	public ObjectHealthPotion(GamePanel gp) {
		this.gp = gp;
		name = "health_potion";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/health_potion.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
