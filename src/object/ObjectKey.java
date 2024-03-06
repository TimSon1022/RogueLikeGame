package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjectKey extends SuperObject{
	GamePanel gp;
	
	public ObjectKey(GamePanel gp) {
		this.gp = gp;
		name = "key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
