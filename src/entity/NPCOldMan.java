package entity;


import java.util.Random;

import main.GamePanel;


public class NPCOldMan extends Entity{
	
	

	public NPCOldMan(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 1;
		getImage();
		setDialogue();
	}
	
	
	public void getImage() { 
		 up1 = setup("/npc/old_man_up_1");
	        up2 = setup("/npc/old_man_up_2");
	        down1 = setup("/npc/old_man_down_1");
	        down2 = setup("/npc/old_man_down_2");
	        left1 = setup("/npc/old_man_left_1");
	        left2 = setup("/npc/old_man_left_2");
	        right1 = setup("/npc/old_man_right_1");
	        right2 = setup("/npc/old_man_right_2");
	}
	
	public void setDialogue() {
		dialogues[0] = "What would you like to buy?\nHealth Potion\nAttack Boost\nDefense Boost\nHealth Boost";
	}

	
	public void setAction() {
		
		if (gp.gameState == gp.playState) {
			actionLockCounter++;
			
			if (actionLockCounter == 200) {
				Random random = new Random();
				
				int i = random.nextInt(100) + 1;
				
				if (i <= 25) {
					direction = "up";
				}
				if (i > 25 && i <= 50) {
					direction = "down";
				}
				if (i > 50 && i <= 75){
					direction = "left";
				}
				if (i > 75 && i <= 100) {
					direction = "right";
				}
				
				actionLockCounter = 0;
			}
		}

		
		
		
	}
	
	
	public void speak() {
		super.speak();
	}

	

	

	


}
