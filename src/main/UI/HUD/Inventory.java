package main.UI.HUD;

import java.awt.Graphics2D;
import main.GamePanel;
import util.DrawUtils;

public class Inventory {

    GamePanel gamePanel;
    Graphics2D graphics2;
    DrawUtils drawUtils;

    public Inventory(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.drawUtils = new DrawUtils();
    }

    public void drawInventory(Graphics2D graphics2, int x, int y) {

        this.graphics2 = graphics2;
        final int slotXstart = x + 20;
        final int slotYstart = y + gamePanel.tileSize + 20;

        for (int i = 0; i < 20; i++) {
            int slotX = slotXstart + (i % 4) * 50;
            int slotY = slotYstart + (i / 4) * 50;

            drawUtils.drawSubWindow(graphics2, slotX, slotY, gamePanel.tileSize, gamePanel.tileSize);
        }

        drawItems(x, y);

    }

    private void drawItems(int x, int y) {
        for (int i = 0; i < gamePanel.player.inventory.size(); i++) {
            int slotX = x + 20 + (i % 4) * 50;
            int slotY = y + gamePanel.tileSize + 20 + (i / 4) * 50;

            graphics2.drawImage(gamePanel.player.inventory.get(i).down1, slotX + 5, slotY + 5, gamePanel.tileSize - 10,
                    gamePanel.tileSize - 10, null);

        }
    }

}
