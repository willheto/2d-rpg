package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Barehands extends Entity {

    public OBJ_Barehands(GamePanel gamePanel) {
        super(gamePanel);

        down1 = setup("/objects/iron_sword");

        name = "Barehands";
        attackValue = 1;
    }

}
