package main;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.OBJ_Axe;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	
	}
	public void setObject() {
		
		int mapNum = 1;
		int i = 0;
//		gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
//		gp.obj[mapNum][i].worldX = gp.TileSize*17;
//		gp.obj[mapNum][i].worldY = gp.TileSize*11;
//		i++;
//		gp.obj[mapNum][i] = new OBJ_Heart(gp);
//		gp.obj[mapNum][i].worldX = gp.TileSize*12;
//		gp.obj[mapNum][i].worldY = gp.TileSize*17;
//		i++;
//		gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
//		gp.obj[mapNum][i].worldX = gp.TileSize*19;
//		gp.obj[mapNum][i].worldY = gp.TileSize*25;
//		i++;
//		gp.obj[mapNum][i] = new OBJ_Axe(gp);
//		gp.obj[mapNum][i].worldX = gp.TileSize*12;
//		gp.obj[mapNum][i].worldY = gp.TileSize*9;
//		i++;
//		gp.obj[mapNum][i] = new OBJ_Shield_Blue(gp);
//		gp.obj[mapNum][i].worldX = gp.TileSize*11;
//		gp.obj[mapNum][i].worldY = gp.TileSize*9;
//		i++;
//		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
//		gp.obj[mapNum][i].worldX = gp.TileSize*10;
//		gp.obj[mapNum][i].worldY = gp.TileSize*9;
		i++;
//		gp.obj[1] = new OBJ_Door(gp);
//		gp.obj[1].worldX = gp.TileSize*23;
//		gp.obj[1].worldY = gp.TileSize*25;
	}
	public void setNPC() {
		int mapNum = 1;
//		gp.NPC[mapNum][0] = new NPC_OldMan(gp);
//		gp.NPC[mapNum][0].worldX = gp.TileSize*11;
//		gp.NPC[mapNum][0].worldY = gp.TileSize *20;
	}
	
	public void setMonster() {
		
		 int i = 0;
		 int mapNum = 0;
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.TileSize* 11;
		gp.monster[mapNum][i].worldY = gp.TileSize* 28;
		i++;
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.TileSize* 19;
		gp.monster[mapNum][i].worldY = gp.TileSize* 20;
		i++;
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.TileSize* 27;
		gp.monster[mapNum][i].worldY = gp.TileSize* 20;
		i++;
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.TileSize* 27;
		gp.monster[mapNum][i].worldY = gp.TileSize* 28;
		i++;
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.TileSize* 35;
		gp.monster[mapNum][i].worldY = gp.TileSize* 20;
		i++;
		mapNum =1;
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.TileSize* 35;
		gp.monster[mapNum][i].worldY = gp.TileSize* 20;
		i++;
	}
}
