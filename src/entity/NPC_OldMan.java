package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gamePanel) {
        super(gamePanel);

        name = "Old man";
        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_1");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
        chatHead1 = setup("/npc/chatHeads/old_man_chat_head", 75, 75);

    }

    public void setDialogue() {
        dialogues[0] = "Greetings! I see you are a new arrival to \nthe world of Gielinor. My job is to welcome \nall new visitors. So welcome!";
        dialogues[1] = "You have already learned the first thing \nneeded to succeed in this world: \ntalking to other people!";
        dialogues[2] = "You will find many inhabitants of this world \nhave useful things to say to you. By talking to \nthem, you can gain information that may help \nyou on your adventures.";

    }

    public void setAction() {

        actionLockCounter++;
        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }

            if (i > 25 && i <= 50) {
                direction = "down";
            }

            if (i > 50 && i <= 75) {
                direction = "left";
            }

            if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    public void speak() {
        super.speak();
    }
}
