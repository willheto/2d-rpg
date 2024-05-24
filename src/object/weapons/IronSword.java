package object.weapons;

import main.GamePanel;

public class IronSword extends Weapon {

    public IronSword(GamePanel gamePanel) {
        super(gamePanel);

        name = "Iron Sword";
        attackStyles = new AttackStyle[] { AttackStyle.STAB, AttackStyle.SLASH, AttackStyle.BLOCK };
        image = setup("/objects/iron_sword");
        attackValue = 2;
        examineText = "A sword made of iron. Looks pretty sharp!";
    }

}
