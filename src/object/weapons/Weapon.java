package object.weapons;

import main.GamePanel;
import object.SuperObject;

public class Weapon extends SuperObject {

    public enum AttackStyle {
        PUNCH, KICK,
        STAB, SLASH, BLOCK
    }

    public AttackStyle[] attackStyles;

    public int attackValue;

    public Weapon(GamePanel gamePanel) {
        super(gamePanel);
        isWieldable = true;
    }

}
