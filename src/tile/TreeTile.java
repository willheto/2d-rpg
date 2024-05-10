package tile;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class TreeTile extends Tile {

    public TreeTile() {
        image = null;
        collision = true;
        numberRepresentation = 4;

        loadImage();
    }

    private void loadImage() {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            this.image = image;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
