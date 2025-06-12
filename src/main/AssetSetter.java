package main;

import entity.NPC_OldMan;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	
	}
	public void setObject() {
		
	}
	public void setNPC() {
		
		gp.NPC[0] = new NPC_OldMan(gp);
		gp.NPC[0].worldX = gp.TileSize * 21;
		gp.NPC[0].worldY = gp.TileSize * 21;
	}
}
