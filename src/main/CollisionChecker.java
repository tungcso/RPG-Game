package main;

import entity.Entity;

public class CollisionChecker {
 
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
	
	int entityLeftCol = entityLeftWorldX/gp.TileSize;
	int entityRightCol = entityRightWorldX/gp.TileSize;
	int entityTopRow = entityTopWorldY/gp.TileSize;
	int entityBottomRow = entityBottomWorldY/gp.TileSize;
	
	int tileNum1, tileNum2;
	
	switch(entity.direction) {
	case "up":
		entityTopRow = (entityTopWorldY - entity.speed)/gp.TileSize;
		tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
		tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
		if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
			entity.collisionOn = true;
		}
		break;
	case "down":
	    entityBottomRow = (entityBottomWorldY + entity.speed) / gp.TileSize;
	    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
	    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
	    if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
	        entity.collisionOn = true;
	    }
		break;
	case "left":
	    entityLeftCol = (entityLeftWorldX - entity.speed) / gp.TileSize;
	    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
	    tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
	    if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
	        entity.collisionOn = true;
	    }
		break;
	case"right":
	    entityRightCol = (entityRightWorldX + entity.speed) / gp.TileSize;
	    tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
	    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
	    if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
	        entity.collisionOn = true;
	    }
		break;
	}
	}
	public int checkObject(Entity entity, boolean player) {
	
		int index = 999;
		
		for(int i = 0; i <gp.obj.length; i++) {
			
			if(gp.obj[i] != null) {
	
				//Get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				//get the object solid area position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "down" :
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					entity.solidArea.y += entity.speed;
					break;
				case "left" :
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					entity.solidArea.x -= entity.speed;
					break;
				case "right":
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					entity.solidArea.x += entity.speed;
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
			
		}
		
		return index;
	}
}








