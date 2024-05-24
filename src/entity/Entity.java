package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.UtilityTool;
import object.armor.Armor;
import object.weapons.Weapon;
import util.CombatUtils;
import util.ExperienceUtils;

public class Entity {

    public GamePanel gamePanel;

    public int worldX, worldY, screenX, screenY;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public int speed;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int currentHitpoints;
    public int type; // 0 = player, 1 = npc, 2 = monster

    public int attackExperience;
    public int strengthExperience;
    public int defenceExperience;
    public int hitpointsExperience;

    int dialogueIndex = 0;

    public String name;
    public String direction = "down";

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage image, image2, image3;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1,
            attackRight2;
    public BufferedImage chatHead1, chatHead2, chatHead3, chatHead4;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);

    String dialogues[] = new String[20];

    public boolean collision = false;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean isAttacking = false;

    public boolean alive = true;
    public boolean dying = false;
    public boolean isHpBarShown = false;

    public String attackStyle;

    public int experience = 50;

    public Equipment equipment = new Equipment();

    public class Equipment {
        public Armor helmet, cape, amulet, chestArmor, shield, legArmor, gloves, boots, ring;
        public Weapon ammo, weapon;

        public Equipment() {
            helmet = null;
            cape = null;
            amulet = null;
            ammo = null;
            weapon = null;
            chestArmor = null;
            shield = null;
            legArmor = null;
            gloves = null;
            boots = null;
            ring = null;
        }
    }

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setAction() {
    }

    public void damageReaction() {
    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gamePanel.ui.hud.chat.currentDialogue = dialogues[dialogueIndex];
        gamePanel.ui.hud.chat.currentChatHeader = name;
        gamePanel.ui.hud.chat.currentChatImage = chatHead1;

        dialogueIndex++;

        switch (gamePanel.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }

    }

    public int getHitpointsLevel() {
        return ExperienceUtils.getLevelFromExperience(hitpointsExperience);
    }

    public int getAttackLevel() {
        return ExperienceUtils.getLevelFromExperience(attackExperience);
    }

    public int getStrengthLevel() {
        return ExperienceUtils.getLevelFromExperience(strengthExperience);
    }

    public int getDefenceLevel() {
        return ExperienceUtils.getLevelFromExperience(defenceExperience);
    }

    public int getAttackHitChance(int enemydefenceExperience) {
        return CombatUtils.getAttackHitChance(ExperienceUtils.getLevelFromExperience(attackExperience),
                ExperienceUtils.getLevelFromExperience(enemydefenceExperience));
    }

    public int getAttackDamage() {
        return CombatUtils.getAttackDamage(strengthExperience, equipment.weapon);
    }

    public void update() {
        setAction();

        collisionOn = false;
        gamePanel.collisionManager.checkTile(this);
        gamePanel.collisionManager.checkObject(this, false);
        gamePanel.collisionManager.checkEntity(this, gamePanel.npc);
        gamePanel.collisionManager.checkEntity(this, gamePanel.monster);
        boolean contactPlayer = gamePanel.collisionManager.checkPlayer(this);

        if (hpBarCounter > 0) {
            hpBarCounter--;
        }
        if (hpBarCounter == 0) {
            isHpBarShown = false;
        }

        if (this.type == 2 && contactPlayer) {
            if (gamePanel.player.invincible == false) {
                gamePanel.playSoundEffect(6);
                gamePanel.player.currentHitpoints--;
                gamePanel.player.isHpBarShown = true;
                gamePanel.player.hpBarCounter = 600;
                gamePanel.player.invincible = true;

            }
        }

        if (collisionOn == false) {
            if (direction == "up") {
                worldY -= speed;
            }
            if (direction == "down") {
                worldY += speed;
            }
            if (direction == "left") {
                worldX -= speed;
            }
            if (direction == "right") {
                worldX += speed;
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            spriteCounter = 0;
            spriteNum = spriteNum == 1 ? 2 : 1;
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincibleCounter = 0;
                invincible = false;
            }
        }

    }

    public void draw(Graphics2D graphics2) {
        BufferedImage image = null;

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
            }

            if (type == 2 && isHpBarShown) {
                graphics2.setColor(new Color(255, 0, 30));
                graphics2.fillRect(screenX, screenY - 15, gamePanel.tileSize, 8);

                graphics2.setColor(new Color(0, 255, 0));
                graphics2.fillRect(screenX, screenY - 15,
                        (int) ((double) currentHitpoints
                                / (double) ExperienceUtils.getLevelFromExperience(hitpointsExperience)
                                * gamePanel.tileSize),
                        8);
            }

            if (invincible == true) {
                graphics2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            }

            if (dying == true) {
                playDyingSound();
                dyingAnimation(graphics2);
            }

            graphics2.drawImage(image, screenX, screenY, null);
            graphics2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }
    }

    private void playDyingSound() {
        if (dyingCounter == 1) {
            gamePanel.playSoundEffect(4);
        }
    }

    public void dyingAnimation(Graphics2D graphics2) {
        dyingCounter++;
        float[] alphaValues = { 0f, 1f };

        if (dyingCounter <= 40) {
            int phase = (dyingCounter - 1) / 5;
            changeAlpha(graphics2, alphaValues[phase % 2]);
        } else {
            dying = false;
            alive = false;
        }
    }

    private void changeAlpha(Graphics2D graphics2, float alpha) {
        graphics2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }

    public BufferedImage setup(String imageName, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = uTool.setup(imageName, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

    public BufferedImage setup(String imageName) {
        return setup(imageName, gamePanel.tileSize, gamePanel.tileSize);
    }

}
