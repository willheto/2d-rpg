package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity {

    GamePanel gamePanel;

    public OBJ_Door(GamePanel gamePanel) {
        super(gamePanel);

        name = "Door";
        collision = true;
        down1 = setup("/objects/door");

    }

}
