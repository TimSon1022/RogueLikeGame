package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjectHeart extends SuperObject{
	GamePanel gp;
	
	public ObjectHeart(GamePanel gp) {
		this.gp = gp;
		name = "heart";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/heart.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
