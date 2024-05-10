package object;

import main.GamePanel;

public class OBJ_Heart extends SuperObject {

    GamePanel gamePanel;

    public OBJ_Heart(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        name = "heart";
        collision = false;
        worldX = 0;
        worldY = 0;
    }

}
