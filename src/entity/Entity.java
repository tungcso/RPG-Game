package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.object.SuperObjects;
import entity.projectile.Projectile;
import main.GamePanel;
import main.UtilityTool;

public abstract class Entity {

	protected GamePanel gp;
	
	public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public Rectangle attackArea = new Rectangle( 0, 0, 0 ,0);
	public int solidAreaDefaultX, solidAreaDefaultY;
	protected String dialogue[] = new String[20];
	public BufferedImage image,  image2, image3;
	public boolean collision = false;
	
	//state
	public String direction = "down";
	public int worldX, worldY;
	public int spriteNum = 1;
	protected int dialogueIndex = 0;
	public boolean invincible = false;
	public boolean collisionOn = false;
	public boolean attacking = false;
	public boolean alive = true;
	public boolean dying = false;
	public boolean hpBarOn = false;
	
	//counter
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	int dyingCounter = 0;
	int hpBarCounter = 0;
	public int shotAvailableCounter = 0;
	
	//Character status
	public int maxLife;
	public int life;
	public int speed;
	public String name;
	public int level;
	public int strength;
	public int dexterity;
	public int attack;
	public int maxMana;
	public int mana;
	public int ammo;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public SuperObjects currentWeapon;
	public SuperObjects currentShield;
	public Projectile projectile;
	
	//item attributes
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public int useCost;
	public int value; 
	
	//type
	public int type; //0 is player, 1 is npc, 2 is mons
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_monster = 2;
	public final int type_sword = 3;
	public final int type_axe = 4;
	public final int type_shield = 5;
	public final int type_consumable = 6;
	public final int type_pickupOnly = 7;
	
	public Entity(GamePanel gp) {
	this.gp = gp;
	}

		
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.TileSize > gp.player.worldX - gp.player.screenX 
			&& worldX - gp.TileSize < gp.player.worldX + gp.player.screenX
			&& worldY + gp.TileSize > gp.player.worldY - gp.player.screenY 
			&& worldY - gp.TileSize < gp.player.worldY + gp.player.screenY) {
		
			switch(direction) {
			case "up":
				if (spriteNum == 1) {
					image = up1;
				}
				if(spriteNum == 2) {
					image = up2;
				}
				break;
			case "down":
				if (spriteNum == 1) {
					image = down1;
				}
				if(spriteNum == 2) {
					image = down2;
				}
				break;
			case "left":
				if (spriteNum == 1) {
					image = left1;
				}
				if(spriteNum == 2) {
					image = left2;
				}
				break;
			case "right":
				if (spriteNum == 1) {
					image = right1;
				}
				if(spriteNum == 2) {
					image = right2;
				}
				break;
			}
			
			//Monster health bar
			if(type == 2 && hpBarOn == true) {
				
				double oneScale = (double) gp.TileSize / maxLife;
				double hpBarValue = oneScale*life;
				
				
				g2.setColor(new Color(35,35,35));
				g2.fillRect(screenX - 1, screenY - 16, gp.TileSize + 2, 2);
				
				g2.setColor(new Color(255,0,30));
				g2.fillRect(screenX, screenY - 15, (int)hpBarValue , 10);
				
				hpBarCounter++;
				
				if(hpBarCounter > 600) {
					hpBarCounter = 0;
					hpBarOn = false;
				}
			}
			
			
			if(invincible == true) {
				hpBarOn = true;
				hpBarCounter = 0;
				changeAlpha(g2,0.2F);
			}
			if(dying == true) {
				dyingAnimation(g2);
				
			}
			
			g2.drawImage(image, screenX, screenY, null);
			changeAlpha(g2,1f);
		}
	}
	 	
	public void dyingAnimation(Graphics2D g2) {
		
		dyingCounter ++;	
		
		int i = 5;
		
		if(dyingCounter <= i) {	changeAlpha(g2, 0f);	}		
		if(dyingCounter > i	 && dyingCounter <= i*2)   {changeAlpha(g2, 1f);	}		
		if(dyingCounter > i *2 && dyingCounter <= i*3) {changeAlpha(g2, 0f);	}
		if(dyingCounter > i *3 && dyingCounter <= i*4) {changeAlpha(g2, 1f);	}
		if(dyingCounter > i *4 && dyingCounter <= i*5) {changeAlpha(g2, 0f);	}
		if(dyingCounter > i *5 && dyingCounter <= i*6) {changeAlpha(g2, 1f);	}
		if(dyingCounter > i *6 && dyingCounter <= i*7) {changeAlpha(g2, 0f);	}
		if(dyingCounter > i *7 && dyingCounter <= i*8) {changeAlpha(g2, 1f);	}
		if(dyingCounter > i *8) {
			alive = false;
		}	
	}
	
	public void changeAlpha(Graphics2D g2, float alphaValue) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	
	}

	public BufferedImage setup(String imagePath, int width, int height) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage Image = null;
		
		try {
			Image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			Image = uTool.scaleImage(Image, width, height);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return Image;
	}
}
