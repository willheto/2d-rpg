package util;

import object.weapons.Weapon;

public class CombatUtils {

    public static int getAttackHitChance(int attackValue, int defenceValue) {
        int hitChance = 50 + (attackValue - defenceValue) * 5;
        if (hitChance < 5) {
            hitChance = 5;
        } else if (hitChance > 95) {
            hitChance = 95;
        }

        return hitChance;
    }

    public static int getAttackDamage(long strengthExperience, Weapon wieldedWeapon) {
        int strengthLevel = ExperienceUtils.getLevelFromExperience(strengthExperience);

        long damage;
        if (wieldedWeapon == null) {

            damage = 1 + strengthLevel / 4;

        } else {
            damage = strengthLevel / 4 + wieldedWeapon.attackValue;
        }

        return (int) damage;
    }
}
