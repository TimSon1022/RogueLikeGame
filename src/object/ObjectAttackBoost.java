package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectAttackBoost extends SuperObject{

	
	public ObjectAttackBoost() {
		name = "attack_boost";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/attack_boost.png"));
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}

	}
}
