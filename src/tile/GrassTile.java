package tile;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class GrassTile extends Tile {

    public GrassTile() {
        image = null;
        collision = false;
        numberRepresentation = 0;

        loadImage();
    }

    private void loadImage() {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));
            this.image = image;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getNumberRepresentation() {
        return this.numberRepresentation;
    }

}
