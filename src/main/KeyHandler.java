package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	GamePanel gp;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {	
		
		int code = e.getKeyCode();
		//titlestate
		if(gp.gameState == gp.titleState) {
			
//			if(gp.ui.titleScreenState == 0) {
					if(code == KeyEvent.VK_W) {
						gp.ui.commandNum --;
						if(gp.ui.commandNum < 0) {
							gp.ui.commandNum = 2;
						}
					}

					if(code == KeyEvent.VK_S) {
						gp.ui.commandNum++;
						if(gp.ui.commandNum > 2) {
							gp.ui.commandNum = 0;
						}
					}
					if(code == KeyEvent.VK_ENTER) {
						if(gp.ui.commandNum == 0) {
							gp.gameState = gp.playState;
//							gp.ui.titleScreenState = 1;
						}
						if(gp.ui.commandNum == 1) {
							//later
						}
						if(gp.ui.commandNum == 2) {
							System.exit(0);
						}
					}
			}
				
//			else if(gp.ui.titleScreenState == 1) {
//						if(code == KeyEvent.VK_W) {
//							gp.ui.commandNum --;
//							if(gp.ui.commandNum < 0) {
//								gp.ui.commandNum = 3;
//							}
//						}
//
//						if(code == KeyEvent.VK_S) {
//							gp.ui.commandNum++;
//							if(gp.ui.commandNum > 3) {
//								gp.ui.commandNum = 0;
//							}
//						}
//						if(code == KeyEvent.VK_ENTER) {
//							if(gp.ui.commandNum == 0) {
//								System.out.println("Do some fighter specific stuff!");
//								gp.gameState = gp.playState;
//								gp.playMusic(0);
//							}
//							if(gp.ui.commandNum == 1) {
//								System.out.println("Do some thief specific stuff!");
//								gp.gameState = gp.playState;
//								gp.playMusic(0);
//							}
//							if(gp.ui.commandNum == 2) {
//								System.out.println("Do some sorcerer specific stuff!");
//								gp.gameState = gp.playState;
//								gp.playMusic(0);
//							}
//							if(gp.ui.commandNum == 3) {
//								gp.ui.titleScreenState = 0;
//						}
//					}
//			}
//			}
		//playstate
		if(gp.gameState == gp.playState) {
			if(code == KeyEvent.VK_W) {
				upPressed = true;
			}

			if(code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if(code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if(code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if(code == KeyEvent.VK_P) {
					gp.gameState = gp.pauseState;
			}
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
		}
		//pausestate
		else if(gp.gameState == gp.pauseState) {
			if(code == KeyEvent.VK_P) {
			gp.gameState = gp.playState;
			}
		}
		
		//dialoguestate
		else if(gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}
		}
	@Override
	public void keyReleased(KeyEvent e) {	
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}

		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}

		
	}

}
