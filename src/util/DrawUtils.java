package util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class DrawUtils {

    public void drawSubWindow(Graphics2D graphics2, int x, int y, int width, int height, boolean isTransparent) {
        Color c = new Color(255, 255, 255);
        graphics2.setStroke(new BasicStroke(3));
        graphics2.setColor(c);
        graphics2.drawRoundRect(x + 3, y + 3, width - 6, height - 6, 2, 2);
    }

    public void drawSubWindow(Graphics2D graphics2, int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0);
        graphics2.setColor(c);
        graphics2.fillRoundRect(x, y, width, height, 2, 2);

        c = new Color(255, 255, 255);
        graphics2.setStroke(new BasicStroke(3));
        graphics2.setColor(c);
        graphics2.drawRoundRect(x + 3, y + 3, width - 6, height - 6, 2, 2);
    }

    public int getXforCenteredText(int screenWidth, Graphics2D graphics2, String text) {
        int length = (int) graphics2.getFontMetrics().getStringBounds(text, graphics2).getWidth();
        int x = screenWidth / 2 - length / 2;

        return x;
    }

    public int getXforAlignToRightText(Graphics2D graphics2, String text, int tailX) {
        int length = (int) graphics2.getFontMetrics().getStringBounds(text, graphics2).getWidth();
        int x = tailX - length;

        return x;
    }
}
