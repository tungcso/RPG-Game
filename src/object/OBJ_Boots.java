package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Boots extends SuperObject{
	
	GamePanel gp;
	public OBJ_Boots() {
		
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
			uTool.scaleImage(image, gp.TileSize, gp.TileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
