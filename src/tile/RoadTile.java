package tile;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class RoadTile extends Tile {

    public RoadTile() {
        image = null;
        collision = false;
        numberRepresentation = 5;

        loadImage();
    }

    private void loadImage() {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/tiles/road00.png"));
            this.image = image;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
