package Tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		 this.gp = gp;
		  
		 tile = new Tile[100];
		 mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		 
		 getTileImage();
		 loadMap("/maps/test2.txt");
	}
	
	public void getTileImage() {
	
			setup(0,"dirt", false);
			setup(1,"floor1", false);
			setup(2,"floor1", false);
			setup(3,"floor2", false);
			setup(4,"water", false);
			setup(5,"fire", false);
			setup(6,"hole", false);
			setup(7,"water", false);
			setup(8,"tree1", false);
		
	}
	
	
	public void setup(int index, String imageName, boolean collision) {
		
		UtilityTool uTool = new UtilityTool();
		
		try {
			
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.TileSize, gp.TileSize);
			tile[index].collision = collision;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			
		}
		
		
	}
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;

		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
				
//video 6
			int worldX = worldCol * gp.TileSize;
			int worldY = worldRow * gp.TileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.TileSize > gp.player.worldX - gp.player.screenX 
				&& worldX - gp.TileSize < gp.player.worldX + gp.player.screenX
				&& worldY + gp.TileSize > gp.player.worldY - gp.player.screenY 
				&& worldY - gp.TileSize < gp.player.worldY + gp.player.screenY) {
			
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			
			}
			
					worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}

























