package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, pPressed, enterPressed;
	GamePanel gp;
	
	public String inputString = ""; // String to store user input

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
		
        if (gp.gameState == gp.titleScreen) {

            if (code == KeyEvent.VK_W) {
                upPressed = true;
            } 
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            
        	if (gp.ui.titleScreenState == 0) {
        		if (code == KeyEvent.VK_ENTER) {
                	
                	
                	if (gp.ui.commandNum == 0) {
                		code = 0;
                		gp.playSE(8, -10.0f);
                		gp.ui.titleScreenState = 1;
                		
                	}
                	if (gp.ui.commandNum == 1) {
                		
                	}
                	
                	if (gp.ui.commandNum == 2) {
                		
                	}
                	
                	if (gp.ui.commandNum == 3) {
                		System.exit(0);
                	}
        		}
        	}
            
            if (gp.ui.titleScreenState == 1) {
                if (inputString.length() < 30 && (Character.isLetter(e.getKeyChar()) 
                		|| Character.isDigit(e.getKeyChar())
                		|| Character.isWhitespace(e.getKeyChar()))) {
                    inputString += e.getKeyChar();
                    
                }
                // Handle backspace to delete the last character from inputString
                else if (code == KeyEvent.VK_BACK_SPACE && inputString.length() > 0) {
                    inputString = inputString.substring(0, inputString.length() - 1);
                }
                gp.ui.inputGameName = inputString;
                if (code == KeyEvent.VK_ENTER) { 
                	
                	if (inputString.isBlank()) {
                		gp.ui.inputIsEmpty = true;
                	}
                	else {
                		gp.playSE(8, -10.0f);
                		gp.gameState = gp.playState;
                	}
                	
                }
                else if (code == KeyEvent.VK_ESCAPE) {
                	gp.playSE(9, -10.0f);
                	gp.ui.titleScreenState = 0;
                }
                else {
                		gp.ui.inputIsEmpty = false;
                }

            	
            	

            }
            

        }
		
		
		//Play state
        else if (gp.gameState == gp.playState) {
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
				gp.playSE(6, -10.0f);
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
				gp.playSE(7, -10.0f);
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
            enterPressed = false;
        }

		
		
		
	}

}
