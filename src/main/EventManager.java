package main;

import java.awt.Rectangle;

import util.ExperienceUtils;

public class EventManager {

    GamePanel gamePanel;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;

    }

    public void checkEvent() {
        if (hit(58, 48, "up")) {
            damagePit(gamePanel.dialogueState);
        }
        if (hit(49, 55, "down")) {
            healingPool(gamePanel.dialogueState);
        }

    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;
        eventRect.x = eventCol * gamePanel.tileSize + eventRect.x;
        eventRect.y = eventRow * gamePanel.tileSize + eventRect.y;

        if (gamePanel.player.solidArea.intersects(eventRect)) {
            if (reqDirection.equals(gamePanel.player.direction) || reqDirection.equals("any")) {
                hit = true;
            }
        }

        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;

    }

    public void damagePit(int gameState) {
        gamePanel.gameState = gameState;
        gamePanel.player.currentHitpoints--;
        gamePanel.ui.hud.chat.currentDialogue = "You fell into the pit!";

    }

    public void healingPool(int gameState) {
        if (gamePanel.keyHandler.ePressed) {
            gamePanel.gameState = gameState;
            gamePanel.player.currentHitpoints = ExperienceUtils
                    .getLevelFromExperience(gamePanel.player.hitpointsExperience);
            gamePanel.ui.hud.chat.currentDialogue = "You drink from the spring. \nIt heals some health!";
        }
    }

}
