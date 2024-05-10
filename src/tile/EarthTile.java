package tile;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class EarthTile extends Tile {

    public EarthTile() {
        image = null;
        collision = false;
        numberRepresentation = 3;

        loadImage();
    }

    private void loadImage() {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
            this.image = image;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
