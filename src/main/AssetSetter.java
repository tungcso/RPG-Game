package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	
	}
	public void setObject() {
		
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 17 * gp.TileSize;
		gp.obj[0].worldY = 21 * gp.TileSize;
		
		gp.obj[1] = new OBJ_Key();
		gp.obj[1].worldX = 18 * gp.TileSize;
		gp.obj[1].worldY = 21 * gp.TileSize;
		
		gp.obj[2] = new OBJ_Key();
		gp.obj[2].worldX = 12 * gp.TileSize;
		gp.obj[2].worldY = 21 * gp.TileSize;
		
		gp.obj[3] = new OBJ_Door();
		gp.obj[3].worldX = 13 * gp.TileSize;
		gp.obj[3].worldY = 21 * gp.TileSize;
		
		gp.obj[4] = new OBJ_Door();
		gp.obj[4].worldX = 14 * gp.TileSize;
		gp.obj[4].worldY = 21 * gp.TileSize;
		
		gp.obj[5] = new OBJ_Door();
		gp.obj[5].worldX = 15 * gp.TileSize;
		gp.obj[5].worldY = 21 * gp.TileSize;
		
		gp.obj[6] = new OBJ_Chest();
		gp.obj[6].worldX = 16 * gp.TileSize;
		gp.obj[6].worldY = 21 * gp.TileSize;
		
		gp.obj[7] = new OBJ_Boots();
		gp.obj[7].worldX = 19 * gp.TileSize;
		gp.obj[7].worldY = 21 * gp.TileSize;
	}
}
