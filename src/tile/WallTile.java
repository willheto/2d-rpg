package tile;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class WallTile extends Tile {

    public WallTile() {
        image = null;
        collision = true;
        numberRepresentation = 1;

        loadImage();
    }

    private void loadImage() {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            this.image = image;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
