package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectDefenseBoost extends SuperObject{

	
	public ObjectDefenseBoost() {
		name = "defense_boost";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/defense_boost.png"));
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
