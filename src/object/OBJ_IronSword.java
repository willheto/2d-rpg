package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_IronSword extends Entity {

    public OBJ_IronSword(GamePanel gamePanel) {
        super(gamePanel);

        name = "Iron Sword";
        down1 = setup("/objects/iron_sword");
        attackValue = 2;
    }

}
