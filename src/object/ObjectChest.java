package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjectChest extends SuperObject{
	GamePanel gp;
	
	public ObjectChest (GamePanel gp) {
		this.gp = gp;
		name = "chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest_1.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
		opened = false;

	}

}
