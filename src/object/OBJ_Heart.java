package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Heart extends SuperObject{

	GamePanel gp;
	
	public OBJ_Heart(GamePanel gp) {
		
		this.gp = gp;
		
		name = "Heart";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
			image = uTool.scaleImage(image, gp.TileSize, gp.TileSize);
			image2 = uTool.scaleImage(image2, gp.TileSize, gp.TileSize);
			image3 = uTool.scaleImage(image3, gp.TileSize, gp.TileSize);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
