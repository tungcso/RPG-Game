package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity{

	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		solidArea.x = 1;
		solidArea.y = 2;
		solidArea.width = 40;
		solidArea.height = 40;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		setDialogue();
		getImage();
		
	}
	public void getImage() {
		
			up1 = setup("/NPC/oldman_up_1", gp.TileSize, gp.TileSize);
			up2 = setup("/NPC/oldman_up_2", gp.TileSize, gp.TileSize);
			down1 = setup("/NPC/oldman_down_1", gp.TileSize, gp.TileSize);
			down2 = setup("/NPC/oldman_down_2", gp.TileSize, gp.TileSize);
			left1 = setup("/NPC/oldman_left_1", gp.TileSize, gp.TileSize);
			left2 = setup("/NPC/oldman_left_2", gp.TileSize, gp.TileSize);
			right1 = setup("/NPC/oldman_right_1", gp.TileSize, gp.TileSize);
			right2 = setup("/NPC/oldman_right_2", gp.TileSize, gp.TileSize);

	}
	
	public void setDialogue() {
		
		dialogue[0]= "Hello";
		dialogue[1]= "Welcome to my world";
		dialogue[2]= "anh Jack J97 J97 J97 Thien Ly oi, \nHong Nhan, Bac Phan, Song Gio, \nEm gi oi, Duoi tan cay kho hoa no";
		dialogue[3]= "my Idolllllllll!!!!!!!!!!!!!!";
	}
	public void setAction() {
		
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
		}

	public void speak() {
		
		super.speak();
	}
}























