package main.UI.HUD;

import java.awt.Graphics2D;
import main.GamePanel;
import util.DrawUtils;

public class Map {

    GamePanel gamePanel;
    DrawUtils drawUtils;

    public Map(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.drawUtils = new DrawUtils();
    }

    public void drawMap(Graphics2D graphics2) {
        int x = gamePanel.tileSize * 11;
        int y = gamePanel.tileSize * 0;
        int width = gamePanel.tileSize * 5;
        int height = gamePanel.tileSize * 3;

        drawUtils.drawSubWindow(graphics2, x, y, width, height);
        graphics2.setFont(graphics2.getFont().deriveFont(30f));
        int textX = x + 20;
        int textY = y + gamePanel.tileSize;

        graphics2.drawString("Map", textX, textY);
    }

}
