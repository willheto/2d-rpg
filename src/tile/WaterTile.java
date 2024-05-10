package tile;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class WaterTile extends Tile {

    public WaterTile() {
        image = null;
        collision = true;
        numberRepresentation = 2;

        loadImage();
    }

    private void loadImage() {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/tiles/water00.png"));
            this.image = image;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
