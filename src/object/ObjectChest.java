package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectChest extends SuperObject{
	
	
	public ObjectChest () {
		name = "chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest_1.png"));
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
