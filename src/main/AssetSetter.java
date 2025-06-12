package main;

import entity.NPC_OldMan;
import object.OBJ_Door;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	
	}
	public void setObject() {
		
		gp.obj[0] = new OBJ_Door(gp);
		gp.obj[0].worldX = gp.TileSize*21;
		gp.obj[0].worldY = gp.TileSize*21;
		
		gp.obj[1] = new OBJ_Door(gp);
		gp.obj[1].worldX = gp.TileSize*23;
		gp.obj[1].worldY = gp.TileSize*25;
	}
	public void setNPC() {
		
		gp.NPC[0] = new NPC_OldMan(gp);
		gp.NPC[0].worldX = gp.TileSize * 26;
		gp.NPC[0].worldY = gp.TileSize * 28;
	}
}
