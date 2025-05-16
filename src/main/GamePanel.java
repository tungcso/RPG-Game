package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Tile.TileManager;
import entity.Player;

public class GamePanel extends JPanel implements Runnable{
	
	//screen settings
	final int originalTileSize = 16; //16x16 Tile
	final int scale = 3;
	
	public final int TileSize = originalTileSize * scale; // 48x48 Tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int ScreenWidth = TileSize * maxScreenCol; // 768 pixels
	public final int ScreenHeight = TileSize * maxScreenRow; // 576 pixels
	
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player1 = new Player(this,keyH);
	
	//set player's default position
	Character player = new Character(100, 100, 4);
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}

	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}

//	@Override
//	public void run() {
//		
//		double drawInterval = 1000000000/FPS; // 0.0166666 seconds
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		while (gameThread != null) {
//			
//			update();
//			
//			repaint();
//			
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;
//				
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long) remainingTime);
//				nextDrawTime += drawInterval;
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//	}
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			if(timer >= 1000000000) {
				System.out.println("FPS :" + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
		
	}
	public void update() {
		player1.update();

	}
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		
		player1.draw(g2);
	
		g2.dispose();
	}
}








