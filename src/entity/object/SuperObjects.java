package entity.object;

import entity.Entity;
import main.GamePanel;

public class SuperObjects extends Entity{

	public SuperObjects(GamePanel gp) {
		super(gp);
		
	}
	public void use(Entity entity) {}
// 	public void update() {
//		
//		collisionOn = false;
////		gp.cChecker.checkTile(this);
//		gp.cChecker.checkObject(this, false);
//		gp.cChecker.checkEntity(this, gp.NPC);
//		gp.cChecker.checkEntity(this, gp.monster);
////		boolean contactPlayer = gp.cChecker.checkPlayer(this);
////		
////		if(this.type == type_monster && contactPlayer == true) {
////			damagePlayer(attack);	
////		}
//		
//		//if collision is false, player can move
//		
//	}

}
