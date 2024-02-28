package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectHealthPotion extends SuperObject{

	
	public ObjectHealthPotion() {
		name = "health_potion";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/health_potion.png"));
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
