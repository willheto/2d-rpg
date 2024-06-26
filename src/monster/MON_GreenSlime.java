package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import util.ExperienceUtils;

public class MON_GreenSlime extends Entity {

    public MON_GreenSlime(GamePanel gamePanel) {
        super(gamePanel);
        name = "GreenSlime";

        type = 2;
        speed = 1;
        hitpointsExperience = 388;
        attackExperience = 0;
        strengthExperience = 0;
        defenceExperience = 0;

        currentHitpoints = ExperienceUtils.getLevelFromExperience(hitpointsExperience);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/monsters/greenslime_down_1");
        up2 = setup("/monsters/greenslime_down_2");
        down1 = setup("/monsters/greenslime_down_1");
        down2 = setup("/monsters/greenslime_down_2");
        left1 = setup("/monsters/greenslime_down_1");
        left2 = setup("/monsters/greenslime_down_2");
        right1 = setup("/monsters/greenslime_down_1");
        right2 = setup("/monsters/greenslime_down_2");
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

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gamePanel.player.direction;
    }
}
