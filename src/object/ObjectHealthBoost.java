package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectHealthBoost extends SuperObject{

	
	public ObjectHealthBoost() {
		name = "health_boost";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/health_boost.png"));
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
	