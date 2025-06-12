package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Door extends SuperObject{
	GamePanel gp;
	public OBJ_Door() {
		
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Door.png"));
			uTool.scaleImage(image, gp.TileSize, gp.TileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
