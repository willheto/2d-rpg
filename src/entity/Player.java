package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import main.GamePanel;
import main.KeyHandler;
import object.OBJ_IronSword;
import object.OBJ_Key;
import util.CombatUtils;
import util.ExperienceUtils;

public class Player extends Entity {

    KeyHandler keyHandler;

    public final int screenX, screenY;
    public ArrayList<Entity> inventory = new ArrayList<Entity>();
    public final int inventorySize = 20;

    // New flag to ensure damage is only applied once per attack animation
    private boolean hasAttacked;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {

        super(gamePanel);
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 51;
        worldY = gamePanel.tileSize * 54;
        speed = 4;
        direction = "down";

        attackExperience = 0;
        strengthExperience = 0;
        defenceExperience = 0;
        hitpointsExperience = 1154;
        currentHitpoints = ExperienceUtils.getLevelFromExperience(hitpointsExperience);
        attackStyle = "Stab";

        wieldedWeapon = new OBJ_IronSword(gamePanel);
        wieldedShield = null;

        type = 0;

        setItems();
    }

    public void setItems() {
        inventory.add(new OBJ_IronSword(gamePanel));
        inventory.add(new OBJ_Key(gamePanel));
        inventory.add(new OBJ_Key(gamePanel));
        inventory.add(new OBJ_Key(gamePanel));
        inventory.add(new OBJ_Key(gamePanel));
        inventory.add(new OBJ_Key(gamePanel));
        inventory.add(new OBJ_Key(gamePanel));
        inventory.add(new OBJ_Key(gamePanel));
        inventory.add(new OBJ_Key(gamePanel));

    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("/player/player_attack_up_1", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackUp2 = setup("/player/player_attack_up_2", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackDown1 = setup("/player/player_attack_down_1", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackDown2 = setup("/player/player_attack_down_2", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackLeft1 = setup("/player/player_attack_left_1", gamePanel.tileSize * 2, gamePanel.tileSize);
        attackLeft2 = setup("/player/player_attack_left_2", gamePanel.tileSize * 2, gamePanel.tileSize);
        attackRight1 = setup("/player/player_attack_right_1", gamePanel.tileSize * 2, gamePanel.tileSize);
        attackRight2 = setup("/player/player_attack_right_2", gamePanel.tileSize * 2, gamePanel.tileSize);
    }

    public void update() {

        if (hpBarCounter > 0) {
            hpBarCounter--;
        }
        if (hpBarCounter == 0) {
            isHpBarShown = false;
        }

        boolean keyPressed = keyHandler.upPressed == true || keyHandler.downPressed == true
                || keyHandler.leftPressed == true || keyHandler.rightPressed == true || keyHandler.ePressed == true;

        if (keyHandler.kPressed == true) {
            gamePanel.playSoundEffect(5);
            isAttacking = true;
            hasAttacked = false; // Reset the attack flag
            keyHandler.kPressed = false;
        }

        if (isAttacking == true) {
            attacking();
        }

        else if (keyPressed) {
            if (keyHandler.upPressed == true) {
                direction = "up";
            }

            if (keyHandler.downPressed == true) {
                direction = "down";
            }

            if (keyHandler.leftPressed == true) {
                direction = "left";
            }

            if (keyHandler.rightPressed == true) {
                direction = "right";
            }

            collisionOn = false;
            gamePanel.collisionManager.checkTile(this);
            int objectIndex = gamePanel.collisionManager.checkObject(this, true);
            pickUpObject(objectIndex);

            // check npc collision
            int npcIndex = gamePanel.collisionManager.checkEntity(this, gamePanel.npc);
            interactNPC(npcIndex);

            // check monster
            int monsterIndex = gamePanel.collisionManager.checkEntity(this, gamePanel.monster);
            contactMonster(monsterIndex);

            // check event
            gamePanel.eventManager.checkEvent();

            if (collisionOn == false && keyHandler.ePressed == false) {
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

            gamePanel.keyHandler.ePressed = false;

            spriteCounter++;
            if (spriteCounter > 12) {
                spriteCounter = 0;
                spriteNum = spriteNum == 1 ? 2 : 1;
            }
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincibleCounter = 0;
                invincible = false;
            }
        }
    }

    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        }

        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            if (!hasAttacked) {
                int currentWorldX = worldX;
                int currentWorldY = worldY;
                int solidAreaWidth = solidArea.width;
                int solidAreaHeight = solidArea.height;

                switch (direction) {
                    case "up":
                        worldY -= attackArea.height;
                        break;
                    case "down":
                        worldY += attackArea.height;
                        break;
                    case "left":
                        worldX -= attackArea.width;
                        break;
                    case "right":
                        worldX += attackArea.height;
                        break;
                }

                solidArea.width = attackArea.width;
                solidArea.height = attackArea.height;

                int monsterIndex = gamePanel.collisionManager.checkEntity(this, gamePanel.monster);
                damageMonster(monsterIndex);

                worldX = currentWorldX;
                worldY = currentWorldY;
                solidArea.width = solidAreaWidth;
                solidArea.height = solidAreaHeight;

                hasAttacked = true; // Set the attack flag to true
            }
        }

        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            isAttacking = false;
        }
    }

    private void damageMonster(int monsterIndex) {
        if (monsterIndex != 999 && gamePanel.monster[monsterIndex].invincible == false) {

            int damage = CombatUtils.getAttackDamage(strengthExperience, wieldedWeapon);
            int hitChance = CombatUtils.getAttackHitChance(ExperienceUtils.getLevelFromExperience(attackExperience),
                    ExperienceUtils.getLevelFromExperience(gamePanel.monster[monsterIndex].defenceExperience));

            int randomNumber = (new Random().nextInt(100) + 1);

            boolean isHit = randomNumber <= hitChance;
            if (isHit) {
                boolean isDamageMoreThanRemainingHitpoints = damage > gamePanel.monster[monsterIndex].currentHitpoints;
                gamePanel.monster[monsterIndex].currentHitpoints -= isDamageMoreThanRemainingHitpoints
                        ? gamePanel.monster[monsterIndex].currentHitpoints
                        : damage;
                gamePanel.monster[monsterIndex].invincible = true;
                gamePanel.monster[monsterIndex].damageReaction();
                gamePanel.playSoundEffect(7);
                gamePanel.monster[monsterIndex].isHpBarShown = true;
                gamePanel.monster[monsterIndex].hpBarCounter = 240;

                if (gamePanel.monster[monsterIndex].currentHitpoints <= 0) {
                    gamePanel.monster[monsterIndex].dying = true;

                    int currentHitpointsLevel = ExperienceUtils.getLevelFromExperience(hitpointsExperience);
                    int currentAttackLevel = ExperienceUtils.getLevelFromExperience(attackExperience);
                    int currentStrengthLevel = ExperienceUtils.getLevelFromExperience(strengthExperience);
                    int currentDefenceLevel = ExperienceUtils.getLevelFromExperience(defenceExperience);

                    hitpointsExperience += gamePanel.monster[monsterIndex].experience / 4;

                    if (attackStyle == "stab") {
                        attackExperience += gamePanel.monster[monsterIndex].experience;
                    } else if (attackStyle == "slash") {
                        strengthExperience += gamePanel.monster[monsterIndex].experience;
                    } else if (attackStyle == "block") {
                        defenceExperience += gamePanel.monster[monsterIndex].experience;
                    }

                    if (ExperienceUtils.getLevelFromExperience(hitpointsExperience) > currentHitpointsLevel) {
                        playLevelUpSound(11);
                        gamePanel.ui.hud.chat.currentDialogue = "\nYour hitpoints level is now "
                                + ExperienceUtils.getLevelFromExperience(hitpointsExperience) + ".";
                        gamePanel.ui.hud.chat.currentChatHeader = "Congratulations!";

                        BufferedImage image = setup("/objects/heart_full", 75, 75);

                        if (image != null) {
                            gamePanel.ui.hud.chat.currentChatImage = image;
                        }
                    }

                    if (attackStyle == "stab") {
                        if (ExperienceUtils.getLevelFromExperience(attackExperience) > currentAttackLevel) {
                            playLevelUpSound(8);
                            gamePanel.ui.hud.chat.currentDialogue = "\nYour attack level is now "
                                    + ExperienceUtils.getLevelFromExperience(attackExperience) + ".";
                            gamePanel.ui.hud.chat.currentChatHeader = "Congratulations!";

                            BufferedImage image = setup("/hud/stab", 75, 75);

                            if (image != null) {
                                gamePanel.ui.hud.chat.currentChatImage = image;
                            }
                        }
                    }

                    if (attackStyle == "slash") {
                        if (ExperienceUtils.getLevelFromExperience(strengthExperience) > currentStrengthLevel) {
                            playLevelUpSound(9);
                            gamePanel.ui.hud.chat.currentDialogue = "\nYour strength level is now "
                                    + ExperienceUtils.getLevelFromExperience(strengthExperience) + ".";
                            gamePanel.ui.hud.chat.currentChatHeader = "Congratulations!";

                            BufferedImage image = setup("/hud/slash", 75, 75);

                            if (image != null) {
                                gamePanel.ui.hud.chat.currentChatImage = image;
                            }
                        }
                    }

                    if (attackStyle == "block") {
                        if (ExperienceUtils.getLevelFromExperience(defenceExperience) > currentDefenceLevel) {
                            playLevelUpSound(10);
                            gamePanel.ui.hud.chat.currentDialogue = "\nYour defence level is now "
                                    + ExperienceUtils.getLevelFromExperience(defenceExperience) + ".";
                            gamePanel.ui.hud.chat.currentChatHeader = "Congratulations!";

                            BufferedImage image = setup("/hud/block", 75, 75);

                            if (image != null) {
                                gamePanel.ui.hud.chat.currentChatImage = image;
                            }
                        }
                    }
                }
            } else {
                gamePanel.monster[monsterIndex].isHpBarShown = true;
                gamePanel.monster[monsterIndex].hpBarCounter = 240;
            }

        }
    }

    private void playLevelUpSound(int index) {
        gamePanel.stopMusic(0);
        gamePanel.playSoundEffect(index);
        gamePanel.playMusic(0);
    }

    public void pickUpObject(int objectIndex) {
        if (objectIndex != 999) {

        }
    }

    public void interactNPC(int npcIndex) {
        if (npcIndex != 999) {
            if (gamePanel.keyHandler.ePressed == true) {
                gamePanel.gameState = gamePanel.dialogueState;
                gamePanel.npc[npcIndex].speak();
            }
        }
    }

    public void contactMonster(int monsterIndex) {
        if (monsterIndex != 999) {
            if (invincible == false && gamePanel.monster[monsterIndex].dying == false) {
                gamePanel.playSoundEffect(6);
                isHpBarShown = true;
                hpBarCounter = 600;
                currentHitpoints--;
                invincible = true;
            }
        }
    }

    public void draw(Graphics2D graphics2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (isAttacking == true) {
                    tempScreenY -= gamePanel.tileSize;
                }
                if (spriteNum == 1) {
                    image = isAttacking == true ? attackUp1 : up1;

                }
                if (spriteNum == 2) {
                    image = isAttacking == true ? attackUp2 : up2;

                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = isAttacking == true ? attackDown1 : down1;
                }
                if (spriteNum == 2) {
                    image = isAttacking == true ? attackDown2 : down2;
                }
                break;
            case "left":
                if (isAttacking == true) {
                    tempScreenX -= gamePanel.tileSize;
                }
                if (spriteNum == 1) {
                    image = isAttacking == true ? attackLeft1 : left1;
                }
                if (spriteNum == 2) {
                    image = isAttacking == true ? attackLeft2 : left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = isAttacking == true ? attackRight1 : right1;
                }
                if (spriteNum == 2) {
                    image = isAttacking == true ? attackRight2 : right2;
                }
                break;
        }

        if (isHpBarShown) {
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

        graphics2.drawImage(image, tempScreenX, tempScreenY, null);
        graphics2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }

    public void getPlayerImage() {
        up1 = setup("/player/player_up_1");
        up2 = setup("/player/player_up_2");
        down1 = setup("/player/player_down_1");
        down2 = setup("/player/player_down_2");
        left1 = setup("/player/player_left_1");
        left2 = setup("/player/player_left_2");
        right1 = setup("/player/player_right_1");
        right2 = setup("/player/player_right_2");

    }

}
