package main.UI.HUD;

import java.awt.Graphics2D;
import main.GamePanel;
import util.DrawUtils;

public class Skills {

    GamePanel gamePanel;
    DrawUtils drawUtils;

    String[] skills = { "Hitpoints", "Attack", "Strength", "Defence" };

    public Skills(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.drawUtils = new DrawUtils();
    }

    public void drawSkills(Graphics2D graphics2, int x, int y) {
        graphics2.setFont(graphics2.getFont().deriveFont(30f));
        int width = gamePanel.tileSize * 5;
        int textX = x + 20;
        int textY = y + 70;

        final int lineHeight = 20;
        graphics2.drawString("Hitpoints:", textX, textY);
        textY += lineHeight;
        graphics2.drawString("Attack:", textX, textY);
        textY += lineHeight;
        graphics2.drawString("Strength:", textX, textY);
        textY += lineHeight;
        graphics2.drawString("Defence:", textX, textY);

        int tailX = (x + width) - 30;
        textY = y + 70;
        String value;

        value = String.valueOf(gamePanel.player.currentHitpoints + " / " + gamePanel.player.getHitpointsLevel());
        textX = drawUtils.getXforAlignToRightText(graphics2, value, tailX);

        graphics2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.getAttackLevel() + " / " + gamePanel.player.getAttackLevel());
        textX = drawUtils.getXforAlignToRightText(graphics2, value, tailX);
        graphics2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.getStrengthLevel() + " / " + gamePanel.player.getStrengthLevel());
        textX = drawUtils.getXforAlignToRightText(graphics2, value, tailX);
        graphics2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.getDefenceLevel() + " / " + gamePanel.player.getDefenceLevel());
        textX = drawUtils.getXforAlignToRightText(graphics2, value, tailX);
        graphics2.drawString(value, textX, textY);

    }

}
