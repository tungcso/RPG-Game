package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Entity;
import entity.object.OBJ_Heart;
import entity.object.OBJ_ManaCrystal;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font arial_20, arial_80B;
	BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank;
	public boolean messageOn = false;
//	public String message = "";
//	int messageCounter = 0;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0; // 0: the first screen, 1: the second screen
	public int slotCol = 0;
	public int slotRow = 0;
	int subState = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_20 = new Font("Arial", Font.PLAIN, 20);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		
		
		//create hud object
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		Entity crystal = new OBJ_ManaCrystal(gp);
		crystal_full = crystal.image;
		crystal_blank = crystal.image2;
	}
	
	public void addMessage(String text) {
			
		message.add(text);
		messageCounter.add(0);
		
	}
	public void draw(Graphics2D g2) {
	
		this.g2 = g2;
		
		Font arial_40 = null;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		//Titlestate
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		//playstate
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
			drawMessage();
		}
		//pausestate
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		//dialoguestate
		if(gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}
		
		//characterstate
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory();
		}
		//option state
		if(gp.gameState == gp.optionsState) {
			drawOptionsScreen();
		}
		//gameover state
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
	}
	public void drawPlayerLife() {
		
//		gp.player.life = 5;
		
		int x = gp.TileSize/2;
		int y = gp.TileSize/2;
		int i = 0;
		
		//draw blank heart
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y,null);
			i++;
			x+= gp.TileSize;
		}
		
		//reset
		x = gp.TileSize/2;
		y = gp.TileSize/2;
		i = 0;
		
		//draw current life
		
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full,x, y, null);
			}
			i++;
			x += gp.TileSize;
		}
		
		//draw max mana
		x = gp.TileSize/2 - 5;
		y = (int) (gp.TileSize*1.5);
		i = 0;
		while ( i < gp.player.maxMana) {
			g2.drawImage(crystal_blank, x, y, null);
			i++;
			x += 35;
			
		}
		//reset
		x = gp.TileSize/2 - 5;
		y = (int) (gp.TileSize*1.5);
		
		//draw mana
		i = 0;
		while(i< gp.player.mana ) {
			g2.drawImage(crystal_full, x, y, null);
			i++;
			x+=35;
		}
		
	}
	public void drawMessage() {
		int messageX = gp.TileSize;
		int messageY = gp.TileSize * 4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));
		
		for(int i = 0; i < message.size(); i++) {
			
			if(message.get(i) != null) {
				
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX + 2, messageY + 2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1; //messageCounter++
				messageCounter.set(i, counter); //set the counter to the array
				messageY += 50;
				
				if(messageCounter.get(i) > 180 ) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
		
	}
	public void drawTitleScreen() {
		
		if(titleScreenState == 0) {
			g2.setColor(new Color(0,0,0));
			g2.fillRect(0, 0, gp.ScreenWidth, gp.ScreenHeight);
			//TitleName
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
			String text ="Adventure of Hipo";
			int x = getXforCenteredText(text);
			int y = gp.TileSize*3;
			
			//shadow
			g2.setColor(Color.black);
			g2.drawString(text, x+5, y+5);
			
			//main color
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			//Ha Ma Image
			x = gp.ScreenWidth/2 - (gp.TileSize*2)/2;
			y += gp.TileSize*2;
			g2.drawImage(gp.player.down1, x, y, gp.TileSize*2, gp.TileSize*2, null);
			
			//menu
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
			
			text ="NEW GAME";
			x = getXforCenteredText(text);
			y += gp.TileSize*4;
			g2.setColor(Color.black);
			g2.drawString(text, x+2, y+2);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x - gp.TileSize, y);
			}
			
			text ="LOAD GAME";
			x = getXforCenteredText(text);
			y += gp.TileSize;
			g2.setColor(Color.black);
			g2.drawString(text, x+2, y+2);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x - gp.TileSize, y);
			}
			
			text ="QUIT";
			x = getXforCenteredText(text);
			y += gp.TileSize;
			g2.setColor(Color.black);
			g2.drawString(text, x+2, y+2);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x - gp.TileSize, y);
			}
		}
	}
		
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		
		int y = gp.ScreenHeight/2;
		
		g2.drawString(text, x, y);
		
	}
	
	public void drawDialogueScreen() {
		
		//window
		int x = gp.TileSize * 2;
		int y = gp.TileSize/2;
		int width = gp.ScreenWidth - (gp.TileSize*4);
		int height = gp.TileSize*4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
		x += gp.TileSize;
		y += gp.TileSize;
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y +=40;
		}
	}
	public void drawCharacterScreen() {
		
		//create a frame
		final int frameX = gp.TileSize * 2;
		final int frameY = gp.TileSize;
		final int frameWidth = gp.TileSize * 5;
		final int frameHeight = gp.TileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		//text
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(28F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.TileSize;
		final int lineHeight = 36;
		
		//Names
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Mana", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("Next Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Coin", textX, textY);
		textY += lineHeight + 5;
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight + 15;	
		g2.drawString("Shield", textX, textY);
		textY += lineHeight;
		
		
		//values
		int tailX = (frameX + frameWidth) - 30;
		//reset textY
		textY = frameY + gp.TileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAligntoRightText(value, tailX);
		
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAligntoRightText(value, tailX);
		
		g2.drawString(value, textX, textY);	
		textY += lineHeight;
		
		value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
		textX = getXforAligntoRightText(value, tailX);
		
		g2.drawString(value, textX, textY);	
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXforAligntoRightText(value, tailX);
		
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXforAligntoRightText(value, tailX);
		
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXforAligntoRightText(value, tailX);
		
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.defense);
		textX = getXforAligntoRightText(value, tailX);
		
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXforAligntoRightText(value, tailX);
		
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAligntoRightText(value, tailX);
		
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXforAligntoRightText(value, tailX);
		
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		g2.drawImage(gp.player.currentWeapon.down1,tailX - gp.TileSize, textY - 30, null);
		textY += gp.TileSize;
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.TileSize, textY - 30, null);
		
	}
	public void drawInventory() {
		
		int frameX = gp.TileSize * 12;
		int frameY = gp.TileSize;
		int frameWidth = gp.TileSize * 6;
		int frameHeight = gp.TileSize * 5;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		//slot
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.TileSize + 3;
		
		//draw items in inventory
		for(int i = 0; i < gp.player.inventory.size(); i++) {
			
			//equip cursor
			
			if(gp.player.inventory.get(i)== gp.player.currentWeapon || gp.player.inventory.get(i) == gp.player.currentShield) {
				g2.setColor(new Color(240,190,90));
				g2.fillRoundRect(slotX, slotY, gp.TileSize, gp.TileSize, 10, 10);
			}
			
			
			
			g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
			slotX += slotSize;
			
			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}
		
		
		//cursor
		int cursorX = slotXstart + (slotSize * slotCol);
		int cursorY = slotYstart + (slotSize * slotRow);
		int cursorWidth = gp.TileSize;
		int cursorHeight  = gp.TileSize;
		
		
		
		//draw cursor
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		
		//description frame
		int dFrameX = frameX;
		int dFrameY = frameY + frameHeight;
		int dFrameWidth = frameWidth;
		int dFrameHeight = gp.TileSize*3;
		
		//draw description text
		int textX = dFrameX + 20;
		int textY = dFrameY + gp.TileSize;
		g2.setFont(g2.getFont().deriveFont(28F));
		
		int itemIndex = getItemIndexOnSlot();
		
		if(itemIndex < gp.player.inventory.size()) {
			drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
			for(String line: gp.player.inventory.get(itemIndex).description.split("\n")) {
				 g2.drawString(line, textX, textY);	
				 textY += 32;
			}
			
		}
	}
	public void drawGameOverScreen() {
		
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.ScreenWidth, gp.ScreenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,100F));
		
		text = "Game Over";
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.TileSize*4;
		g2.drawString(text, x, y);
		//main
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		
		//retry
			g2.setFont(g2.getFont().deriveFont(50f));
			text = "Retry";
			x = getXforCenteredText(text);
			y +=gp.TileSize *4;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">",  x- 40, y);
				
			}
			
		//back to title screen
			text = "Quit";
			x = getXforCenteredText(text);
			y += 55;
			g2.drawString(text	,x, y);
			if(commandNum == 1) {
				g2.drawString(">",  x- 40, y);
				
			}
	}
	public void drawOptionsScreen() {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(28F));
		
		//sub window
		int frameX = gp.TileSize * 6;
		int frameY = gp.TileSize;
		int frameWidth = gp.TileSize*9;
		int frameHeight = gp.TileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		
		switch(subState) {
		case 0: option_top( frameX,  frameY); break;
		case 1: option_fullScreenNotification( frameX, frameY); break;
		case 2: options_control(frameX, frameY); break; 
		case 3: options_endGameConfirmation(frameX, frameY); break;
		}
		
		gp.keyH.enterPressed = false;
	}
	public void option_top(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		//title
		String text = "Options";
		textX = getXforCenteredText(text);
		textY = frameY + gp.TileSize;
		g2.drawString(text, textX, textY);
		
		//fullscreen on off
		textX = frameX + gp.TileSize;
		textY += gp.TileSize*2;
		g2.drawString("FullScreen"	, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				if(gp.fullScreenOn == false) {
					gp.fullScreenOn = true;
				}
				else if(gp.fullScreenOn == true) {
					gp.fullScreenOn = false;	
				}
				subState = 1;
			}
		}
		
		//music
		textY += gp.TileSize;
		g2.drawString("Music"	, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX - 25, textY);
		}
		//se
		textY += gp.TileSize;
		g2.drawString("SE"	, textX, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX - 25, textY);
		}
		//control
		textY += gp.TileSize;
		g2.drawString("Control"	, textX, textY);
		if(commandNum == 3) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true)
			{
				subState = 2;
				commandNum = 0;
			}
		}
		//end game
		textY += gp.TileSize;
		g2.drawString("End Game", textX, textY);
		if(commandNum == 4) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 3;
				commandNum = 0;
			}
		}
		//back
		textY += gp.TileSize*2;
		g2.drawString("Back", textX, textY);
		if(commandNum == 5) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true)
			{
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}	
		
		//full screen check box
		textX = frameX + (int) (gp.TileSize*5.5);
		textY = frameY + gp.TileSize*2 + 28;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);
		if(gp.fullScreenOn == true) {
			g2.fillRect(textX, textY, 24, 24);
		}
		
		//music volume
		textY += gp.TileSize;
		g2.drawRect(textX, textY, 120, 24); //   120 : 5 = 24
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		//se volume
		textY += gp.TileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * gp.se.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		gp.config.saveConfig();
		
	}
	public void option_fullScreenNotification( int frameX, int frameY) {
		
		int textX = frameX + gp.TileSize;
		int textY = frameY + gp.TileSize*3;
		
		currentDialogue = "The change will take \neffect after restarting \nthe game.";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		//back
		textY = frameY + gp.TileSize*9;
		g2.drawString("Back", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
			}
		}
		
		
	}
	public void options_control(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		//title
		String text = "Control";
		textX = getXforCenteredText(text);
		textY = frameY + gp.TileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.TileSize;
		textY += gp.TileSize;
		g2.drawString("Move", textX, textY);	textY += gp.TileSize;
		g2.drawString("Confirm/Attack", textX, textY);	textY += gp.TileSize;
		g2.drawString("Shoot/Cast", textX, textY);	textY += gp.TileSize;
		g2.drawString("CharacterScreen", textX, textY);	textY += gp.TileSize;
		g2.drawString("Pause", textX, textY);	textY += gp.TileSize;
		g2.drawString("Options", textX, textY);	textY += gp.TileSize;
		
		
		
		textX = frameX + gp.TileSize*6 + 15;
		textY = frameY + gp.TileSize*2 + 3;
		g2.drawString("WASD", textX, textY);	textY += gp.TileSize;
		g2.drawString("ENTER", textX, textY);	textY += gp.TileSize;
		g2.drawString("F", textX, textY);	textY += gp.TileSize;
		g2.drawString("C", textX, textY);	textY += gp.TileSize;
		g2.drawString("P", textX, textY);	textY += gp.TileSize;
		g2.drawString("ESC", textX, textY);	textY += gp.TileSize;
		
		
		//back
		textX = frameX + gp.TileSize;
		textY = frameY + gp.TileSize*9;
		g2.drawString("Back", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 3;
			}
		}
	}
	
	public void options_endGameConfirmation(int frameX, int frameY) {
		
		int textX = frameX + gp.TileSize;
		int textY = frameY + gp.TileSize*3;
		
		currentDialogue = "Quit the game and return\nto the title screen?";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
			
		}
		
		//yes
		String text = "Yes";
		textX = getXforCenteredText(text);
		textY += gp.TileSize*3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0 ) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				gp.gameState = gp.titleState;
			}
		}
		
		//no
		text = "No";
		textX = getXforCenteredText(text);
		textY += gp.TileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1 ) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 4;
			}
		}
	}
	public int getItemIndexOnSlot() {
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
	}
 	public void drawSubWindow(int x, int y, int width, int height) {
		
		//create RPG Color, the fourth is the opacity
		Color c = new Color(0,0,0, 150);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		//RPG number for white
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
		
	}
	public int getXforCenteredText(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.ScreenWidth/2 - length/2;
		return x;
	}
	public int getXforAligntoRightText(String text, int tailx) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailx - length;
		return x;
	}
}





