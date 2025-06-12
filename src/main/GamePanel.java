package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Tile.TileManager;
import entity.Entity;
import entity.Player;
import object.SuperObject;

public class GamePanel extends JPanel implements Runnable{
	
	//screen settings
	final int originalTileSize = 16; //16x16 Tile
	final int scale = 3;
	
	public final int TileSize = originalTileSize * scale; // 48x48 Tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int ScreenWidth = TileSize * maxScreenCol; // 768 pixels
	public final int ScreenHeight = TileSize * maxScreenRow; // 576 pixels
	
	//World Settings
	public final int maxWorldCol = 112;
	public final int maxWorldRow = 112;
	
	//FPS
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Thread gameThread;
	
	//ENTITY AND OBJECT
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[10];
	public Entity NPC[] = new Entity[10];
	
	//Game State
	public int gameState;
	public final int titleState = 0;
	public final int playState =  1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	
	//set player's default position
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void setupGame() {
		
		aSetter.setObject();
		aSetter.setNPC();
		playMusic(0);
		stopMusic();
		gameState = titleState;
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
		
		if(gameState == playState) {
			player.update();
			
			//NPC
			for(int i = 0; i < NPC.length; i++) {
				if(NPC[i] != null) {
					NPC[i].update();
				}
			}
		}
		
		if(gameState == pauseState) {
			//nothing
		}

	}
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//Title screen
		if(gameState == titleState) {
			ui.draw(g2);
		}
		//OTHER
		else {
			//Tile
			tileM.draw(g2);
			
			//Object
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2,this);
				}
			}
			
			//NPC
			for(int i = 0; i< NPC.length; i++) {
				if(NPC[i] != null ) {
					NPC[i].draw(g2);	
				}
			}
			
			//Player
			player.draw(g2);
		
			//UI
			ui.draw(g2);
			
			g2.dispose();
		}
		
		
	}
	
	public void playMusic(int i) {
		
		music.setFire(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		
		music.stop();
	}
	public void playSE(int i) {
		
		se.setFire(i);
		se.play();
	}
}








