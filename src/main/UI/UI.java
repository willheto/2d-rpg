package main.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.InputStream;
import main.GamePanel;
import main.UI.HUD.HUD;

public class UI {

    GamePanel gamePanel;
    Graphics2D graphics2;
    public HUD hud;
    Font chatFont, dialogueFont;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.hud = new HUD(gamePanel);
        setupFonts();
    }

    private void setupFonts() {
        try {
            InputStream is = getClass().getResourceAsStream("/font/font.ttf");
            chatFont = Font.createFont(Font.TRUETYPE_FONT, is);

            is = getClass().getResourceAsStream("/font/dialogue_font.ttf");
            dialogueFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2) {
        this.graphics2 = graphics2;

        graphics2.setFont(dialogueFont.deriveFont(20f));
        graphics2.setColor(Color.white);

        hud.drawHUD(graphics2);
    }

}
