package main.UI.HUD;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import object.SuperObject;
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
        drawItems(x, y);

    }

    private void drawItems(int x, int y) {
        for (int i = 0; i < gamePanel.player.inventory.size(); i++) {
            int slotX = x + 20 + (i % 4) * 50;
            int slotY = y + gamePanel.tileSize + 20 + (i / 4) * 50;

            graphics2.drawImage(gamePanel.player.inventory.get(i).image, slotX + 5, slotY + 5, gamePanel.tileSize - 10,
                    gamePanel.tileSize - 10, null);

        }

        if (gamePanel.keyHandler.inventoryClickData != null) {
            if (gamePanel.keyHandler.inventoryClickData.clickType == "right") {
                int slotIndex = gamePanel.keyHandler.inventoryClickData.inventorySlotClicked;

                if (gamePanel.player.inventory.size() <= slotIndex) {
                    return;
                }

                int clickX = gamePanel.keyHandler.inventoryClickData.clickX;
                int clickY = gamePanel.keyHandler.inventoryClickData.clickY;
                drawItemOptions(clickX, clickY, gamePanel.player.inventory.get(slotIndex));
            }
        }
    }

    private void drawItemOptions(int clickX, int clickY, SuperObject item) {
        int width = 180;
        int height = 105;
        int x = clickX - width / 2;
        int y = clickY;

        boolean isWieldable = item.isWieldable;
        String itemName = item.name;

        drawInventoryOptionsWindow(graphics2, x, y, width, height);

        graphics2.setFont(graphics2.getFont().deriveFont(30f));

        int currentY = y + 30;

        if (isWieldable) {
            drawItemOption(itemName, "Wield", 0, x, currentY);
            currentY += 20;
        } else {
            drawItemOption(itemName, "Not wieldable", 0, x, currentY);
            currentY += 20;
        }

        drawItemOption(itemName, "Use", 1, x, currentY);
        currentY += 20;
        drawItemOption(itemName, "Drop", 2, x, currentY);
        currentY += 20;
        drawItemOption(itemName, "Examine", 3, x, currentY);

    }

    private void drawItemOption(String itemName, String actionName, int optionIndex, int x, int currentY) {
        int hoveredIndex = gamePanel.keyHandler.inventoryClickData.inventoryItemOptionIndexHovered;
        Color actionColor = hoveredIndex == optionIndex ? Color.RED : Color.WHITE;

        drawText(graphics2, actionName, itemName, x, currentY, actionColor, Color.YELLOW);
    }

    private void drawText(Graphics2D graphics2, String text, String itemName, int x, int y, Color textColor,
            Color itemColor) {
        graphics2.setColor(textColor);
        graphics2.drawString(text, x + 10, y);
        graphics2.setColor(itemColor);
        graphics2.drawString(itemName, x + 10 + graphics2.getFontMetrics().stringWidth(text + " "), y);
    }

    public void drawInventoryOptionsWindow(Graphics2D graphics2, int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0);
        graphics2.setColor(c);
        graphics2.fillRoundRect(x, y, width, height, 2, 2);

        c = new Color(255, 255, 255);
        graphics2.setStroke(new BasicStroke(1));
        graphics2.setColor(c);
        graphics2.drawRoundRect(x + 3, y + 3, width - 6, height - 6, 2, 2);
    }

}
