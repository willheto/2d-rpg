package object;

import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;

public class SuperObject {

    GamePanel gamePanel;

    public SuperObject(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public int worldX, worldY, screenX, screenY;
    public int solidAreaDefaultX, solidAreaDefaultY;

    public BufferedImage image;

    public boolean isWieldable = false;

    public String name;
    public String examineText;

    public BufferedImage setup(String imageName, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = uTool.setup(imageName, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

    public BufferedImage setup(String imageName) {
        return setup(imageName, gamePanel.tileSize, gamePanel.tileSize);
    }

    public void handleItemExamine() {
        gamePanel.ui.hud.chat.addChatString(examineText);
    }

}
