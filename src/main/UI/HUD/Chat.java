package main.UI.HUD;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import util.DrawUtils;

public class Chat {

    GamePanel gamePanel;
    DrawUtils drawUtils;

    public String[] chatStrings = new String[6];
    public String currentDialogue;
    public String currentChatHeader;

    public BufferedImage currentChatImage;

    public Chat(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.drawUtils = new DrawUtils();
        this.chatStrings[0] = "Welcome to the game!";
    }

    public void addChatString(String chatString) {
        for (int i = chatStrings.length - 1; i > 0; i--) {
            chatStrings[i] = chatStrings[i - 1];
        }
        chatStrings[0] = chatString;
    }

    public void drawChat(Graphics2D graphics2) {
        int x = gamePanel.tileSize * 0;
        int y = gamePanel.tileSize * 7;
        int width = gamePanel.tileSize * 11;
        int height = gamePanel.tileSize * 3;

        drawUtils.drawSubWindow(graphics2, x, y, width, height);
        graphics2.setFont(graphics2.getFont().deriveFont(25f));

        java.awt.FontMetrics fm = graphics2.getFontMetrics();
        int textX;

        if (currentDialogue != null && currentChatHeader != null && currentChatImage != null) {
            // Calculate total text height (header + dialogue lines)
            int headerHeight = 30;
            graphics2.setFont(graphics2.getFont().deriveFont(30f));
            fm = graphics2.getFontMetrics();
            int lineHeight = 20;
            int dialogueHeight = (currentDialogue.split("\n").length) * lineHeight;

            int totalTextHeight = headerHeight + dialogueHeight;

            // Calculate starting y to vertically center the text block
            int textY = y + (height - totalTextHeight) / 2 + headerHeight - 12;

            // Center the NPC name
            graphics2.setFont(graphics2.getFont().deriveFont(40f));
            fm = graphics2.getFontMetrics();
            int npcNameWidth = fm.stringWidth(currentChatHeader);
            textX = x + (width - npcNameWidth) / 2 + 50;
            graphics2.drawString(currentChatHeader, textX, textY);

            // Draw the dialogue lines
            textY += 20;
            graphics2.setFont(graphics2.getFont().deriveFont(30f));
            fm = graphics2.getFontMetrics();

            for (String line : currentDialogue.split("\n")) {
                int lineWidth = fm.stringWidth(line);
                textX = x + (width - lineWidth) / 2 + 50;
                graphics2.drawString(line, textX, textY);
                textY += lineHeight;
            }

            // Draw the chat image
            graphics2.drawImage(currentChatImage, x + gamePanel.tileSize, y + 30, null);

        } else {

            textX = x + 20;
            int textY = y + 70;
            graphics2.setFont(graphics2.getFont().deriveFont(30f));

            textY += 55;

            // Draw chat strings in reverse order to stack them
            for (int i = 0; i < chatStrings.length; i++) {
                if (chatStrings[i] == null) {
                    continue;
                }
                graphics2.drawString(chatStrings[i], textX, textY);
                textY -= 20; // Decrease Y position for the next string
            }
        }

    }

}
