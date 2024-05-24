package object.armor;

import main.GamePanel;

public class WoodenShield extends Armor {

    public WoodenShield(GamePanel gamePanel) {
        super(gamePanel);

        name = "Wooden Shield";
        image = setup("/objects/wooden_shield");
        defenceValue = 1;
        examineText = "A shield made of wood. It's not very strong, but it's better than nothing!";
    }

}
