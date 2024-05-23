package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

    GamePanel gamePanel;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[100];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        getTileImage();
        loadMap("/maps/tutorial_island.txt");

    }

    public void getTileImage() {
        try {
            setup(0, "water10", true);
            setup(1, "water10", true);
            setup(2, "water02", true);
            setup(3, "water04", true);
            setup(4, "water08", true);
            setup(5, "water06", true);
            setup(6, "water01", true);
            setup(7, "wall", true);
            setup(8, "table01", true);

            setup(9, "road09", false);

            setup(10, "road10", false);

            setup(12, "water12", true);

            setup(13, "water13", true);
            setup(14, "water07", true);

            setup(15, "water09", true);
            setup(16, "water03", true);
            setup(17, "water05", true);
            setup(18, "water00", true);

            setup(19, "tree", true);
            setup(21, "road11", false);
            setup(22, "road12", false);
            setup(24, "road01", false);
            setup(25, "road03", false);
            setup(26, "road07", false);
            setup(27, "road05", false);
            setup(28, "road00", false);
            setup(29, "floor01", false);
            setup(30, "fence04", true);
            setup(31, "fence03", true);
            setup(36, "road06", false);
            setup(37, "road08", false);
            setup(38, "road02", false);
            setup(39, "road04", false);
            setup(41, "tree", false);

            setup(40, "grass01", false);
            setup(41, "grass00", false);

            setup(42, "fence01", true);

            setup(43, "fence02", true);

            setup(44, "fence05", true);

            setup(45, "fence06", true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setup(int index, String imagePath, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].collision = collision;
            tile[index].image = uTool.setup("/tiles/" + imagePath, gamePanel.tileSize, gamePanel.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
                String line = br.readLine();

                while (col < gamePanel.maxWorldCol) {
                    String numbers[] = line.split(",");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {
            try {
                int tileNum = mapTileNum[worldCol][worldRow];

                int worldX = worldCol * gamePanel.tileSize;
                int worldY = worldRow * gamePanel.tileSize;
                int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
                int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

                if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                        worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                        worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                        worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

                    graphics2.drawImage(tile[tileNum].image, screenX, screenY, null);
                }

                worldCol++;
                if (worldCol == gamePanel.maxWorldCol) {
                    worldCol = 0;
                    worldRow++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
