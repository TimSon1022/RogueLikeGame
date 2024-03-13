package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, pPressed, enterPressed;
	GamePanel gp;

	public KeyHandler (GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int code = e.getKeyCode();
		
		
		//Play state
		if (gp.gameState == gp.playState) {
			if (code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if (code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if (code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if (code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if (code == KeyEvent.VK_P && !pPressed) {
				pPressed = true;
			}


			if (code == KeyEvent.VK_SPACE) {
				gp.gameState = gp.pauseState;
			}
			
			if (code == KeyEvent.VK_ENTER) {
				enterPressed = true;
				
			}
		

	}
		
		
		//Pause State
		
		else if (gp.gameState == gp.pauseState) {

			if (code == KeyEvent.VK_SPACE) {

				gp.gameState = gp.playState;
			}
			
		}
		
		
		//Dialogue State
		else if (gp.gameState == gp.dialogueState) {
			if (code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}

		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
        if (code == KeyEvent.VK_P) {
            // Set pReleased to true when the "P" key is released
            pPressed = false;
        }
        
        if (code == KeyEvent.VK_ENTER) {
            // Set pReleased to true when the "P" key is released
            enterPressed = false;
        }

		
		
		
	}

}
