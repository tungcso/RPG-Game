package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {

	GamePanel gp;
	Font arial_20, arial_80B;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_20 = new Font("Arial", Font.PLAIN, 20);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		OBJ_Key key = new OBJ_Key();
		keyImage = key.image;
	}
	
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
		
		if(gameFinished == true) {
			
			g2.setFont(arial_20);
			g2.setColor(Color.white);
			
			String text;
			int textLength;
			int x;
			int y;
			
			text = "You found the treasure!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
			x = gp.ScreenWidth/2 - textLength/2;
			y = gp.ScreenHeight/2 - (gp.TileSize*2) + 50;
			g2.drawString(text, x, y);
	
			text = "Your time is : " + dFormat.format(playTime) + "!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
			x = gp.ScreenWidth/2 - textLength/2;
			y = gp.ScreenHeight/2 + (gp.TileSize*2) + 50;
			g2.drawString(text, x, y);
			
			
			g2.setFont(arial_80B);
			g2.setColor(Color.yellow);
			
			text = "Congratulations!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.ScreenWidth/2 - textLength/2;
			y = gp.ScreenHeight/2 + (gp.TileSize*2);
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
			
		} else {
			g2.setFont(arial_20);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.TileSize/4, gp.TileSize/4, gp.TileSize/2, gp.TileSize/2, null);
			g2.drawString("x "+ gp.player.hasKey, 38, 32);
			
			//Time
			playTime +=(double)1/60;
			g2.drawString("Time:"+dFormat.format(playTime), gp.TileSize * 14 - 10, 32 );
			//MESSAGE
			if(messageOn == true) {
				
				g2.setFont(g2.getFont().deriveFont(20F));
				g2.drawString(message, gp.TileSize*7 - 15, gp.TileSize*6 - 34);
			
				messageCounter++;
				
				//Dissapear after 2s
				if(messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
				}
			}
		}
	}
}





