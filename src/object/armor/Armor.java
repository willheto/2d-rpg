package object.armor;

import main.GamePanel;
import object.SuperObject;

public class Armor extends SuperObject {

    public int defenceValue;

    public Armor(GamePanel gamePanel) {
        super(gamePanel);
        isWieldable = true;
    }

}
