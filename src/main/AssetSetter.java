package main;

import entity.NPC_OldMan;
import entity.NPC_SurvivalGuide;
import monster.MON_GreenSlime;
import object.SuperObject;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {

    }

    public void setNPC() {
        gamePanel.npc[0] = new NPC_OldMan(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize * 55;
        gamePanel.npc[0].worldY = gamePanel.tileSize * 45;

        gamePanel.npc[1] = new NPC_SurvivalGuide(gamePanel);
        gamePanel.npc[1].worldX = gamePanel.tileSize * 54;
        gamePanel.npc[1].worldY = gamePanel.tileSize * 56;

    }

    public void setMonster() {
        gamePanel.monster[0] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[0].worldX = gamePanel.tileSize * 41;
        gamePanel.monster[0].worldY = gamePanel.tileSize * 51;

        gamePanel.monster[1] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[1].worldX = gamePanel.tileSize * 44;
        gamePanel.monster[1].worldY = gamePanel.tileSize * 50;

        gamePanel.monster[2] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[2].worldX = gamePanel.tileSize * 47;
        gamePanel.monster[2].worldY = gamePanel.tileSize * 49;

        gamePanel.monster[3] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[3].worldX = gamePanel.tileSize * 50;
        gamePanel.monster[3].worldY = gamePanel.tileSize * 48;

        gamePanel.monster[4] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[4].worldX = gamePanel.tileSize * 53;
        gamePanel.monster[4].worldY = gamePanel.tileSize * 47;

        gamePanel.monster[5] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[5].worldX = gamePanel.tileSize * 56;
        gamePanel.monster[5].worldY = gamePanel.tileSize * 46;

        gamePanel.monster[6] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[6].worldX = gamePanel.tileSize * 59;
        gamePanel.monster[6].worldY = gamePanel.tileSize * 45;

    }

    public void placeItemOnGround(SuperObject item) {
        // Find an empty slot

        for (int i = 0; i < gamePanel.itemsOnGround.length; i++) {
            if (gamePanel.itemsOnGround[i] == null) {
                gamePanel.itemsOnGround[i] = item;

                gamePanel.itemsOnGround[i].worldX = gamePanel.player.worldX;
                gamePanel.itemsOnGround[i].worldY = gamePanel.player.worldY;

                break;
            }
        }

    }
}
