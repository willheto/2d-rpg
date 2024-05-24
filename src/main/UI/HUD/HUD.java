package main.UI.HUD;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.UtilityTool;
import util.DrawUtils;

public class HUD {
    GamePanel gamePanel;
    Graphics2D graphics2;
    Inventory inventory;
    Map map;
    Skills skills;
    public Combat combat;
    UtilityTool uTool;
    DrawUtils drawUtils;
    Equipment equipment;
    public Chat chat;

    public BufferedImage skillsImage, inventoryImage, combatImage, equipmentImage, magicImage;
    private String[] tabs = { "combat", "skills", "inventory", "equipment", "magic" };

    public HUD(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.inventory = new Inventory(gamePanel);
        this.map = new Map(gamePanel);
        this.skills = new Skills(gamePanel);
        this.combat = new Combat(gamePanel);
        this.uTool = new UtilityTool();
        this.drawUtils = new DrawUtils();
        this.chat = new Chat(gamePanel);
        this.equipment = new Equipment(gamePanel);

        setupImages();
    }

    private void setupImages() {
        try {
            skillsImage = uTool.setup("/hud/skills", gamePanel.tileSize - 20, gamePanel.tileSize - 20);
            inventoryImage = uTool.setup("/hud/inventory", gamePanel.tileSize - 20, gamePanel.tileSize - 20);
            combatImage = uTool.setup("/hud/combat", gamePanel.tileSize - 20, gamePanel.tileSize - 20);
            equipmentImage = uTool.setup("/hud/equipment", gamePanel.tileSize - 20, gamePanel.tileSize - 20);
            magicImage = uTool.setup("/hud/magic", gamePanel.tileSize - 20, gamePanel.tileSize - 20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawHUD(Graphics2D graphics2) {
        this.graphics2 = graphics2;
        map.drawMap(graphics2);
        chat.drawChat(graphics2);

        drawHUDActions();
    }

    private BufferedImage getTabImage(int i) {
        if (tabs[i] == "skills") {
            return skillsImage;
        }
        if (tabs[i] == "inventory") {
            return inventoryImage;
        }
        if (tabs[i] == "combat") {
            return combatImage;
        }
        if (tabs[i] == "equipment") {
            return equipmentImage;
        }
        if (tabs[i] == "magic") {
            return magicImage;
        }

        return null;
    }

    private void drawHUDActions() {
        int x = gamePanel.tileSize * 11;
        int y = gamePanel.tileSize * 3;
        int width = gamePanel.tileSize * 5;
        int height = gamePanel.tileSize * 7;

        drawUtils.drawSubWindow(graphics2, x, y, width, height);

        for (int i = 0; i < tabs.length; i++) {
            int xForTabs = gamePanel.tileSize * (11 + i);
            drawUtils.drawSubWindow(graphics2, xForTabs, y, gamePanel.tileSize, gamePanel.tileSize);
            graphics2.drawImage(getTabImage(i), xForTabs + 10, y + 10, gamePanel.tileSize - 20, gamePanel.tileSize - 20,
                    null);
        }

        if (gamePanel.keyHandler.activeTab == "combat") {
            combat.drawCombat(graphics2, x, y);
        }

        if (gamePanel.keyHandler.activeTab == "skills") {
            skills.drawSkills(graphics2, x, y);
        }

        if (gamePanel.keyHandler.activeTab == "inventory") {
            inventory.drawInventory(graphics2, x, y);
        }

        if (gamePanel.keyHandler.activeTab == "equipment") {
            equipment.drawEquipment(graphics2, x, y + gamePanel.tileSize);
        }

        if (gamePanel.keyHandler.activeTab == "magic") {
            gamePanel.ui.hud.chat.addChatString("magic");
        }

    }

}
