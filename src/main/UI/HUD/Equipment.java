package main.UI.HUD;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.UtilityTool;
import object.SuperObject;
import util.DrawUtils;

public class Equipment {

    GamePanel gamePanel;
    DrawUtils drawUtils;
    UtilityTool uTool;

    public Equipment(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.drawUtils = new DrawUtils();
        this.uTool = new UtilityTool();
    }

    public void drawEquipment(Graphics2D graphics2, int x, int y) {
        drawEquipmentBackground(graphics2, x, y);
        drawEquippedItems(graphics2, x, y);
    }

    private void drawEquippedItems(Graphics2D graphics2, int x, int y) {
        SuperObject helmet = gamePanel.player.equipment.helmet;

        SuperObject cape = gamePanel.player.equipment.cape;
        SuperObject amulet = gamePanel.player.equipment.amulet;
        SuperObject ammo = gamePanel.player.equipment.ammo;

        SuperObject weapon = gamePanel.player.equipment.weapon;
        SuperObject chestArmor = gamePanel.player.equipment.chestArmor;
        SuperObject shield = gamePanel.player.equipment.shield;

        SuperObject legArmor = gamePanel.player.equipment.legArmor;

        SuperObject gloves = gamePanel.player.equipment.gloves;
        SuperObject boots = gamePanel.player.equipment.boots;
        SuperObject ring = gamePanel.player.equipment.ring;

        int itemRowY = y + 10;

        if (helmet != null) {
            int helmetX = x + gamePanel.tileSize * 2;
            graphics2.drawImage(helmet.image, helmetX, itemRowY,
                    gamePanel.tileSize, gamePanel.tileSize, null);
        }

        itemRowY += gamePanel.tileSize + 5;

        if (cape != null) {
            int capeX = x + gamePanel.tileSize * 1 - 5;
            graphics2.drawImage(cape.image, capeX, itemRowY,
                    gamePanel.tileSize, gamePanel.tileSize, null);
        }

        if (amulet != null) {
            int amuletX = x + gamePanel.tileSize * 2;
            graphics2.drawImage(amulet.image, amuletX, itemRowY,
                    gamePanel.tileSize, gamePanel.tileSize, null);
        }

        if (ammo != null) {
            int ammoX = x + gamePanel.tileSize * 3 + 5;
            graphics2.drawImage(ammo.image, ammoX, itemRowY,
                    gamePanel.tileSize, gamePanel.tileSize, null);
        }

        itemRowY += gamePanel.tileSize + 5;

        if (weapon != null) {
            int weaponX = x + gamePanel.tileSize * 1 - 15;
            graphics2.drawImage(weapon.image, weaponX, itemRowY,
                    gamePanel.tileSize, gamePanel.tileSize, null);
        }

        if (chestArmor != null) {
            int chestArmorX = x + gamePanel.tileSize * 2;
            graphics2.drawImage(chestArmor.image, chestArmorX, itemRowY,
                    gamePanel.tileSize, gamePanel.tileSize, null);
        }

        if (shield != null) {
            int shieldX = x + gamePanel.tileSize * 3 + 15;
            graphics2.drawImage(shield.image, shieldX, itemRowY,
                    gamePanel.tileSize, gamePanel.tileSize, null);
        }

        itemRowY += gamePanel.tileSize + 5;

        if (legArmor != null) {
            int legArmorX = x + gamePanel.tileSize * 2;
            graphics2.drawImage(legArmor.image, legArmorX, itemRowY,
                    gamePanel.tileSize, gamePanel.tileSize, null);
        }

        itemRowY += gamePanel.tileSize + 5;

        if (gloves != null) {
            int glovesX = x + gamePanel.tileSize * 1 - 15;
            graphics2.drawImage(gloves.image, glovesX, itemRowY,
                    gamePanel.tileSize, gamePanel.tileSize, null);
        }

        if (boots != null) {
            int bootsX = x + gamePanel.tileSize * 2;
            graphics2.drawImage(boots.image, bootsX, itemRowY,
                    gamePanel.tileSize, gamePanel.tileSize, null);
        }

        if (ring != null) {
            int ringX = x + gamePanel.tileSize * 3 + 15;
            graphics2.drawImage(ring.image, ringX, itemRowY,
                    gamePanel.tileSize, gamePanel.tileSize, null);
        }

    }

    private void drawEquipmentBackground(Graphics2D graphics2, int x, int y) {
        graphics2.setFont(graphics2.getFont().deriveFont(30f));

        int helmetX = x + gamePanel.tileSize * 2;
        int itemRowY = y + 10;
        drawSubWindow(graphics2, helmetX, itemRowY, gamePanel.tileSize, gamePanel.tileSize);
        itemRowY += gamePanel.tileSize + 5;

        int capeX = x + gamePanel.tileSize * 1 - 5;
        drawSubWindow(graphics2, capeX, itemRowY, gamePanel.tileSize, gamePanel.tileSize);

        int amuletX = x + gamePanel.tileSize * 2;
        drawSubWindow(graphics2, amuletX, itemRowY, gamePanel.tileSize, gamePanel.tileSize);

        int ammoX = x + gamePanel.tileSize * 3 + 5;
        drawSubWindow(graphics2, ammoX, itemRowY, gamePanel.tileSize, gamePanel.tileSize);
        itemRowY += gamePanel.tileSize + 5;

        int weaponX = x + gamePanel.tileSize * 1 - 15;
        drawSubWindow(graphics2, weaponX, itemRowY, gamePanel.tileSize, gamePanel.tileSize);

        int chestArmorX = x + gamePanel.tileSize * 2;
        drawSubWindow(graphics2, chestArmorX, itemRowY, gamePanel.tileSize, gamePanel.tileSize);

        int shieldX = x + gamePanel.tileSize * 3 + 15;
        drawSubWindow(graphics2, shieldX, itemRowY, gamePanel.tileSize, gamePanel.tileSize);
        itemRowY += gamePanel.tileSize + 5;

        int legArmorX = x + gamePanel.tileSize * 2;
        drawSubWindow(graphics2, legArmorX, itemRowY, gamePanel.tileSize, gamePanel.tileSize);
        itemRowY += gamePanel.tileSize + 5;

        int glovesX = x + gamePanel.tileSize * 1 - 15;
        drawSubWindow(graphics2, glovesX, itemRowY, gamePanel.tileSize, gamePanel.tileSize);

        int bootsX = x + gamePanel.tileSize * 2;
        drawSubWindow(graphics2, bootsX, itemRowY, gamePanel.tileSize, gamePanel.tileSize);

        int ringX = x + gamePanel.tileSize * 3 + 15;
        drawSubWindow(graphics2, ringX, itemRowY, gamePanel.tileSize, gamePanel.tileSize);
    }

    private void drawSubWindow(Graphics2D graphics2, int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0);
        graphics2.setColor(c);
        graphics2.fillRoundRect(x, y, width, height, 2, 2);

        c = new Color(255, 255, 255);
        graphics2.setStroke(new BasicStroke(2));
        graphics2.setColor(c);
        graphics2.drawRoundRect(x + 2, y + 2, width - 4, height - 4, 2, 2);
    }

}
