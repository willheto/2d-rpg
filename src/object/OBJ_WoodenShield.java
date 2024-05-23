package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_WoodenShield extends Entity {

    public OBJ_WoodenShield(GamePanel gamePanel) {
        super(gamePanel);

        name = "Wooden Shield";
        down1 = setup("/objects/wooden_shield");
        defenceValue = 1;
    }

}
