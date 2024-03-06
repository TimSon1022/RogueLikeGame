package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjectAttackBoost extends SuperObject{
	GamePanel gp;
	
	public ObjectAttackBoost(GamePanel gp) {
		this.gp = gp;
		name = "attack_boost";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/attack_boost.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}

	}
}
