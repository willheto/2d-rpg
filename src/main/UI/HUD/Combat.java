package main.UI.HUD;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import main.GamePanel;
import main.UtilityTool;
import object.weapons.Weapon.AttackStyle;
import util.DrawUtils;

public class Combat {

    GamePanel gamePanel;
    DrawUtils drawUtils;
    UtilityTool uTool;

    public class AttackStyleOption {
        String name;
        String description;
        BufferedImage image;

        public AttackStyleOption(String name, String description, BufferedImage image) {
            this.name = name;
            this.description = description;
            this.image = image;
        }
    }

    List<AttackStyleOption> attackStyleOptions;

    public Combat(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.drawUtils = new DrawUtils();
        this.uTool = new UtilityTool();
        this.attackStyleOptions = new ArrayList<>(
                List.of(new AttackStyleOption("punch", "Punch",
                        uTool.setup("/hud/punch", gamePanel.tileSize - 20, gamePanel.tileSize - 20)),
                        new AttackStyleOption("kick", "Kick",
                                uTool.setup("/hud/kick", gamePanel.tileSize - 20, gamePanel.tileSize - 20)),
                        new AttackStyleOption("block", "Block",
                                uTool.setup("/hud/block", gamePanel.tileSize - 20, gamePanel.tileSize - 20))));

    }

    public void setupCombatStyles() {
        try {
            // EMPTY LIST
            attackStyleOptions.clear();

            if (gamePanel.player.equipment.weapon != null) {
                AttackStyle[] attackStyles = gamePanel.player.equipment.weapon.attackStyles;

                for (int i = 0; i < attackStyles.length; i++) {
                    AttackStyle style = attackStyles[i];

                    String name = style.toString().toLowerCase();
                    String description = style.toString().substring(0, 1).toUpperCase()
                            + style.toString().substring(1).toLowerCase();

                    BufferedImage image = uTool.setup("/hud/" + name, gamePanel.tileSize - 20, gamePanel.tileSize - 20);

                    attackStyleOptions.add(new AttackStyleOption(name, description, image));
                }
            } else {
                attackStyleOptions.add(new AttackStyleOption("punch", "Punch",
                        uTool.setup("/hud/punch", gamePanel.tileSize - 20, gamePanel.tileSize - 20)));
                attackStyleOptions.add(new AttackStyleOption("kick", "Kick",
                        uTool.setup("/hud/kick", gamePanel.tileSize - 20, gamePanel.tileSize - 20)));
                attackStyleOptions.add(new AttackStyleOption("block", "Block",
                        uTool.setup("/hud/block", gamePanel.tileSize - 20, gamePanel.tileSize - 20)));
            }

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

        for (int i = 0; i < attackStyleOptions.size(); i++) {
            AttackStyleOption style = attackStyleOptions.get(i);

            textX = x + 75;

            drawSubWindow(graphics2, gamePanel.tileSize * 11 + 20,
                    textY - 30,
                    gamePanel.tileSize * 4, gamePanel.tileSize, true);

            graphics2.drawString(style.description, textX, textY);
            graphics2.drawImage(style.image, textX - 40, textY - 20, null);

            textY += 55;
        }

    }

    private void drawSubWindow(Graphics2D graphics2, int x, int y, int width, int height, boolean isTransparent) {
        Color c = new Color(255, 255, 255);
        graphics2.setStroke(new BasicStroke(2));
        graphics2.setColor(c);
        graphics2.drawRoundRect(x + 2, y + 2, width - 4, height - 6, 2, 2);
    }

}
