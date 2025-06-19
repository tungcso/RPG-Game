package entity.monster;

import java.util.Random;

import entity.Entity;
import entity.object.OBJ_Coin_Bronze;
import entity.object.OBJ_Heart;
import entity.object.OBJ_ManaCrystal;
import entity.object.OBJ_Rock;
import entity.object.SuperObjects;
import main.GamePanel;

public class MON_GreenSlime extends Entity{

	GamePanel gp;
	
	public MON_GreenSlime(GamePanel gp) {
		super(gp);
	
		this.gp = gp;
		
		type = type_monster;
		name = "Green Slime";
		speed = 2;
		maxLife = 4;
		life = maxLife;
		attack = 5;
		defense = 0;
		exp = 2;
		projectile = new OBJ_Rock(gp);
		
		solidArea.x = 3;
		solidArea.y = 16;
		solidArea.height = 30;
		solidArea.width = 42;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	public void	getImage() {
		
		up1 = setup("/monster/greenslime_down_1", gp.TileSize, gp.TileSize);
		up2 = setup("/monster/greenslime_down_2", gp.TileSize, gp.TileSize);
		down1 = setup("/monster/greenslime_down_1", gp.TileSize, gp.TileSize);
		down2 = setup("/monster/greenslime_down_2", gp.TileSize, gp.TileSize);
		left1 = setup("/monster/greenslime_down_1", gp.TileSize, gp.TileSize);
		left2 = setup("/monster/greenslime_down_2", gp.TileSize, gp.TileSize);
		right1 = setup("/monster/greenslime_down_1", gp.TileSize, gp.TileSize);
		right2 = setup("/monster/greenslime_down_2", gp.TileSize, gp.TileSize);
	}
	public void ProtectTerritory() {
		actionLockCounter++;
		
		if (actionLockCounter == 120){

			Random random = new Random();
			int i = random.nextInt(100) + 1; //pickup a number from 100 to 1
			
			if(i <= 25 ) {
				direction = "up";
			}
			if(i > 25 && i <= 50 ) {
				direction = "down";
			}
			if(i > 25 && i <= 75 ) {
				direction = "left";
			}
			if(i > 75 ) {
				direction = "right";
			}
			actionLockCounter = 0;
		}
		
		int i = new Random().nextInt(100) + 1;
		if (i > 99 &&  projectile.alive == false && shotAvailableCounter == 30) {
			projectile.set(worldX, worldY, direction, true, this);
			gp.projectileList.add(projectile);
			shotAvailableCounter = 0;
		}
	}
	
	public void damageReaction() {
	
		actionLockCounter = 0;
		direction = gp.player.direction;
	}

	public void Die() {
		
		//cast a die
		int i = new Random().nextInt(100) + 1;
		
		//set the monster drop
		if(i < 50) {
			dropItem(new OBJ_Coin_Bronze(gp));
		}
		if( i >= 50 && i < 75) {
			dropItem(new OBJ_Heart(gp));
		}
		if( i >= 75 && i < 100) {
			dropItem(new OBJ_ManaCrystal(gp));
		}
		
	}
	
	public void update() {
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkPlayer(this);
		
		//if collision is false, player can move
		if(collisionOn == false) {

			switch(direction) {
			case "up":	 worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right":worldX += speed; break;
			}
		}
		spriteCounter ++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}		
		if(invincible == true) {
			invincibleCounter ++;
			if(invincibleCounter > 40) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if(shotAvailableCounter < 30) {
			shotAvailableCounter ++;
		}
	}
	
	public void damagePlayer(int attack) {
		if(gp.player.invincible == false) {
			//we can give damage
			
			int damage = attack - gp.player.defense;
			if(damage < 0 ) {
				damage = 0;
			}
			gp.player.life -= damage;
			gp.player.invincible = true;
		}
	}
	
	public void dropItem(SuperObjects droppedItem) {
		
		for ( int i = 0; i < gp.obj[1].length; i++) {
			if(gp.obj[gp.currentMap][i] == null) {
				gp.obj[gp.currentMap][i] = droppedItem;
				gp.obj[gp.currentMap][i].worldX = worldX; // the dead of monster
				gp.obj[gp.currentMap][i].worldY = worldY;
				break;
			}
		}
	}
}










