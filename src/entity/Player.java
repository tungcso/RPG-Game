package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	int standC = 0;
	boolean moving = false;
	int pixelCounter = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		
		this.keyH = keyH;
		
		screenX = gp.ScreenWidth/2 - (gp.TileSize/2);
		screenY = gp.ScreenHeight/2 - (gp.TileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 1;
		solidArea.y = 1;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 46;
		solidArea.height = 46;
		
		setDefaultValue();
		getPlayerImage();
		
	}
	public void setDefaultValue() {
		
		worldX = gp.TileSize * 23;
		worldY = gp.TileSize * 21;
		speed = 4;
		direction = "down";
		
		//Player status
		maxLife = 6;
		life = maxLife;
	}
	public void getPlayerImage() {
	
			up1 = setup("/player/up1");
			up2 = setup("/player/up2");
			down1 = setup("/player/down1");
			down2 = setup("/player/down2");
			left1 = setup("/player/left1");
			left2 = setup("/player/left2");
			right1 = setup("/player/right1");
			right2 = setup("/player/right2");
		
	}
	
	public void update() {
		
			if(keyH.upPressed == true || keyH.downPressed == true || 
					keyH.leftPressed == true || keyH.rightPressed == true) {
				if(keyH.upPressed == true) {
					direction = "up";					
				}
				else if(keyH.downPressed == true) {
					direction = "down";
				}
				else if(keyH.leftPressed == true) {
					direction = "left";
				}
				else if(keyH.rightPressed == true) {
					direction ="right";
				}
			
				moving = true;
				
				//check tile collision
				collisionOn = false;
				gp.cChecker.checkTile(this);
				
				//CheckObject Collision
				int objIndex = gp.cChecker.checkObject(this, true);
				pickUpObject(objIndex);
				
				//Check NPC Collision
				int npcIndex = gp.cChecker.checkEntity(this, gp.NPC);
				interactNPCIndex(npcIndex);
				
				//check event
				gp.eHandler.checkEvent();
				
				gp.keyH.enterPressed = false;
		
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
	}
			else {
				standC++;
				
					if(standC == 20)
					{
				spriteNum = 1;
				standC = 0;
			}
			}
		}
		
	
			
	public void pickUpObject(int i) {
		
		if(i != 999) {
			
	
		}
	}
	
	public void interactNPCIndex(int i) {
		
		if(i != 999) {
				
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.NPC[i].speak();
			}
		}
	
	}
	
	public void draw(Graphics2D g2) {
		
		
//		g2.setColor(Color.white);
//
//		g2.fillRect(x, y, gp.TileSize, gp.TileSize);
		
		
		BufferedImage image = null;
		
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
		g2.drawImage(image, screenX, screenY, null);
//		g2.setColor(Color.red);
//		g2.drawRect(screenX + solidArea.x , screenY + solidArea.y, solidArea.width, solidArea.height);
		
	}
}














