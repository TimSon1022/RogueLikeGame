package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjectGold extends SuperObject{
	GamePanel gp;
	
	public ObjectGold(GamePanel gp) {
		this.gp = gp;
		name = "gold";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/gold_1.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
