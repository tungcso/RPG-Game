package Tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		 this.gp = gp;
		 
		 tile = new Tile[10];
		 mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		 
		 getTileImage();
		 loadMap("/maps/test2.txt");
	}
	
	public void getTileImage() {
		System.out.println("Image loading started");
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt.png"));
		
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt1.png"));
//			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
//			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dead.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
//			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fire.png"));
//			tile[5].collision = true;
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/hole.png"));
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree1.png"));
//			tile[8].collision = true;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("Image loading ended");
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
			
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.TileSize, gp.TileSize, null);
				
			}
			
					worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}

























