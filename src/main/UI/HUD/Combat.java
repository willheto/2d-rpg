package main.UI.HUD;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import main.GamePanel;
import main.UtilityTool;
import util.DrawUtils;

public class Combat {

    GamePanel gamePanel;
    DrawUtils drawUtils;
    UtilityTool uTool;

    public class AttackStyle {
        String name;
        String description;
        BufferedImage image;

        public AttackStyle(String name, String description, BufferedImage image) {
            this.name = name;
            this.description = description;
            this.image = image;
        }
    }

    List<AttackStyle> attackStyles;

    public Combat(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.drawUtils = new DrawUtils();
        this.uTool = new UtilityTool();
        this.attackStyles = new ArrayList<>();

        setupCombatStyles();
    }

    private void setupCombatStyles() {
        try {
            attackStyles.add(new AttackStyle("stab", "Stab (attack)",
                    uTool.setup("/hud/stab", gamePanel.tileSize - 20, gamePanel.tileSize - 20)));
            attackStyles.add(new AttackStyle("slash", "Slash (strength)",
                    uTool.setup("/hud/slash", gamePanel.tileSize - 20, gamePanel.tileSize - 20)));
            attackStyles.add(new AttackStyle("block", "Block (defence)",
                    uTool.setup("/hud/block", gamePanel.tileSize - 20, gamePanel.tileSize - 20)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawCombat(Graphics2D graphics2, int x, int y) {
        graphics2.setFont(graphics2.getFont().deriveFont(30f));
        int textX = x + 20;
        int textY = y + 70;
        int width = gamePanel.tileSize * 5;

        graphics2.drawString("Attack style:", textX, textY);

        int tailX = (x + width) - 30;
        textY = y + 70;
        String value = gamePanel.player.attackStyle;

        textX = drawUtils.getXforAlignToRightText(graphics2, value, tailX);
        graphics2.drawString(value, textX, textY);

        textY += 55;

        for (int i = 0; i < attackStyles.size(); i++) {
            AttackStyle style = attackStyles.get(i);

            textX = x + 65;

            drawUtils.drawSubWindow(graphics2, gamePanel.tileSize * 11 + 20,
                    textY - 30,
                    gamePanel.tileSize * 4, gamePanel.tileSize, true);

            graphics2.drawString(style.description, textX, textY);
            graphics2.drawImage(style.image, textX - 35, textY - 20, null);

            textY += 55;
        }

    }

}
