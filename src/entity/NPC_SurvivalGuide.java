package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_SurvivalGuide extends Entity {

    public NPC_SurvivalGuide(GamePanel gamePanel) {
        super(gamePanel);

        name = "Brynna";
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

    }

    public void setDialogue() {
        dialogues[0] = "Hello there, newcomer. My name is Brynna. \nMy job is to teach you about the skills you \ncan use to survive in this world.";
        dialogues[1] = "The first skill we're going to look at is \nFishing. There's some shrimp in this pond here. \nLet's try and catch some.";
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
