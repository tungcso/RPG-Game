package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Chest extends SuperObject{
	GamePanel gp;
	public OBJ_Chest() {
		
		name = "Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Chest.png"));
			uTool.scaleImage(image, gp.TileSize, gp.TileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
