package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import object.SuperObject;

public class KeyHandler implements KeyListener, MouseListener, MouseMotionListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed, kPressed, oPressed;
    public InventoryClickData inventoryClickData;

    public class InventoryClickData {
        public int inventoryItemOptionIndexHovered;
        public int inventoryItemOptionIndexClicked;
        public int inventorySlotClicked;
        public String clickType;
        public int clickX;
        public int clickY;

        public InventoryClickData(int inventorySlotClicked, String clickType, int clickX, int clickY) {
            this.inventorySlotClicked = inventorySlotClicked;
            this.clickType = clickType;
            this.clickX = clickX;
            this.clickY = clickY;

        }
    }

    public String activeTab = "inventory";

    public boolean checkDrawTime = false;
    private GamePanel gamePanel;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        gamePanel.addKeyListener(this);
        gamePanel.addMouseListener(this);
        gamePanel.addMouseMotionListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gamePanel.gameState == gamePanel.playState) {
            playState(code);
        } else if (gamePanel.gameState == gamePanel.pauseState) {
            pauseState(code);
        } else if (gamePanel.gameState == gamePanel.dialogueState) {
            dialogueState(code);
        }
    }

    public void titleState(int code) {
        // Handle title state key presses here
    }

    public void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        } else if (code == KeyEvent.VK_A) {
            leftPressed = true;
        } else if (code == KeyEvent.VK_S) {
            downPressed = true;
        } else if (code == KeyEvent.VK_D) {
            rightPressed = true;
        } else if (code == KeyEvent.VK_E) {
            ePressed = true;
        } else if (code == KeyEvent.VK_K) {
            kPressed = true;
        } else if (code == KeyEvent.VK_O) {
            oPressed = true;
        }

        if (code == KeyEvent.VK_P) {
            gamePanel.gameState = gamePanel.pauseState;
        }

        if (code == KeyEvent.VK_Q) {
            gamePanel.gameState = gamePanel.characterState;
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gamePanel.gameState = gamePanel.playState;
        }
    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_E) {
            gamePanel.gameState = gamePanel.playState;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        } else if (code == KeyEvent.VK_A) {
            leftPressed = false;
        } else if (code == KeyEvent.VK_S) {
            downPressed = false;
        } else if (code == KeyEvent.VK_D) {
            rightPressed = false;
        } else if (code == KeyEvent.VK_E) {
            ePressed = false;
        } else if (code == KeyEvent.VK_K) {
            kPressed = false;
        } else if (code == KeyEvent.VK_O) {
            oPressed = false;
        } else if (code == KeyEvent.VK_T) {
            checkDrawTime = !checkDrawTime;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        boolean isRightClick = (e.getButton() == MouseEvent.BUTTON3);

        // Handle mouse click events
        int x = e.getX();
        int y = e.getY();
        System.out.println("x: " + x + " y: " + y);

        if (x > 534 && x <= 578 & y < 188 && y > 144) {
            activeTab = "combat";
        }

        if (x > 578 && x <= 626 & y < 188 && y > 144) {
            activeTab = "skills";
        }

        if (x > 626 && x <= 670 & y < 188 && y > 144) {
            activeTab = "inventory";

        }

        if (x > 670 && x <= 714 & y < 188 && y > 144) {
            activeTab = "equipment";
        }

        if (activeTab == "combat") {
            if (x > 550 && x <= 736 & y < 284 && y >= 241) {
                gamePanel.player.attackStyle = "stab";
            }
            if (x > 550 && x <= 736 & y < 336 && y >= 295) {
                gamePanel.player.attackStyle = "slash";
            }
            if (x > 550 && x <= 736 & y < 391 && y >= 348) {
                gamePanel.player.attackStyle = "block";
            }
        }
        if (activeTab.equals("inventory")) {

            if (inventoryClickData != null) {

                // a box of w170 h105 is drawn. split it to 4 rows equally
                if (x > inventoryClickData.clickX - 85 && x < inventoryClickData.clickX + 85
                        && y < inventoryClickData.clickY + 105
                        && y > inventoryClickData.clickY) {

                    if (y < inventoryClickData.clickY + 26) {
                        gamePanel.player.handleRightClickOnItem(inventoryClickData.inventorySlotClicked, 0);

                    } else if (y < inventoryClickData.clickY + 52) {
                        gamePanel.player.handleRightClickOnItem(inventoryClickData.inventorySlotClicked, 1);
                    } else if (y < inventoryClickData.clickY + 78) {
                        gamePanel.player.handleRightClickOnItem(inventoryClickData.inventorySlotClicked, 2);
                    } else {
                        gamePanel.player.handleRightClickOnItem(inventoryClickData.inventorySlotClicked, 3);
                    }

                }
                inventoryClickData = null;
                return;

            }
            if (x > 551 && x <= 743 && y > 213 && y <= 457) {

                // Normalize x and y to be relative to the top-left corner of the inventory
                int normalizedX = x - 552;
                int normalizedY = y - 214;

                // Calculate the column and row
                int col = (int) (normalizedX / 47.5);
                int row = (int) (normalizedY / 48.4);

                // Calculate the slot index
                int slotIndex = row * 4 + col;

                if (isRightClick == false) {
                    if (gamePanel.player.inventory.size() > slotIndex) {
                        gamePanel.player.handleLeftClickOnitem(slotIndex);
                    }
                }

                inventoryClickData = new InventoryClickData(slotIndex, isRightClick ? "right" : "left", x, y);
            }

        }

        if (activeTab.equals("equipment")) {
            if (x > 551 && x <= 743 && y > 213 && y <= 457) {

                // left column
                if (x > 565 && x <= 604) {

                    if (y > 255 && y <= 300) {
                        if (gamePanel.player.equipment.helmet != null) {
                            gamePanel.player.inventory.add(gamePanel.player.equipment.helmet);
                            gamePanel.player.equipment.cape = null;
                        }
                    }
                    if (y > 310 && y <= 355) {
                        if (gamePanel.player.equipment.weapon != null) {
                            gamePanel.player.inventory.add(gamePanel.player.equipment.weapon);
                            gamePanel.player.equipment.weapon = null;
                            gamePanel.ui.hud.combat.setupCombatStyles();
                        }
                    }

                    if (y > 420 && y <= 465) {
                        if (gamePanel.player.equipment.gloves != null) {
                            gamePanel.player.inventory.add(gamePanel.player.equipment.gloves);
                            gamePanel.player.equipment.gloves = null;
                        }
                    }
                }

                // middle column
                if (x > 626 && x <= 668) {
                    if (y > 200 && y <= 245) {
                        if (gamePanel.player.equipment.helmet != null) {
                            gamePanel.player.inventory.add(gamePanel.player.equipment.helmet);
                            gamePanel.player.equipment.helmet = null;
                        }
                    }
                    if (y > 255 && y <= 300) {
                        if (gamePanel.player.equipment.amulet != null) {
                            gamePanel.player.inventory.add(gamePanel.player.equipment.amulet);
                            gamePanel.player.equipment.amulet = null;
                        }
                    }
                    if (y > 310 && y <= 355) {
                        if (gamePanel.player.equipment.chestArmor != null) {
                            gamePanel.player.inventory.add(gamePanel.player.equipment.chestArmor);
                            gamePanel.player.equipment.chestArmor = null;
                        }
                    }
                    if (y > 365 && y <= 410) {
                        if (gamePanel.player.equipment.legArmor != null) {
                            gamePanel.player.inventory.add(gamePanel.player.equipment.legArmor);
                            gamePanel.player.equipment.legArmor = null;
                        }
                    }
                    if (y > 420 && y <= 465) {
                        if (gamePanel.player.equipment.boots != null) {
                            gamePanel.player.inventory.add(gamePanel.player.equipment.boots);
                            gamePanel.player.equipment.boots = null;
                        }
                    }

                }

                // right column
                if (x > 691 && x <= 731) {

                    if (y > 255 && y <= 300) {
                        if (gamePanel.player.equipment.ammo != null) {
                            gamePanel.player.inventory.add(gamePanel.player.equipment.ammo);
                            gamePanel.player.equipment.ammo = null;
                        }
                    }
                    if (y > 310 && y <= 355) {
                        gamePanel.player.inventory.add(gamePanel.player.equipment.shield);
                        gamePanel.player.equipment.shield = null;
                    }

                    if (y > 420 && y <= 465) {
                        gamePanel.player.inventory.add(gamePanel.player.equipment.ring);
                        gamePanel.player.equipment.ring = null;
                    }
                }

            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Handle mouse press events
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Handle mouse release events
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (activeTab.equals("inventory")) {
            if (inventoryClickData != null) {
                if (x > inventoryClickData.clickX - 85 && x < inventoryClickData.clickX + 85
                        && y < inventoryClickData.clickY + 105
                        && y > inventoryClickData.clickY - 20) {

                    if (inventoryClickData != null && inventoryClickData.clickType.equals("right")) {

                        // a box of w170 h105 is drawn. split it to 4 rows equally
                        if (x > inventoryClickData.clickX - 85 && x < inventoryClickData.clickX + 85
                                && y < inventoryClickData.clickY + 105
                                && y > inventoryClickData.clickY) {

                            if (y < inventoryClickData.clickY + 26) {
                                inventoryClickData.inventoryItemOptionIndexHovered = 0;
                            } else if (y < inventoryClickData.clickY + 52) {
                                inventoryClickData.inventoryItemOptionIndexHovered = 1;
                            } else if (y < inventoryClickData.clickY + 78) {
                                inventoryClickData.inventoryItemOptionIndexHovered = 2;
                            } else {
                                inventoryClickData.inventoryItemOptionIndexHovered = 3;
                            }

                        }

                    }
                } else {
                    if (inventoryClickData != null) {
                        inventoryClickData = null;
                    }
                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Handle mouse exit events
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Handle mouse drag events
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Handle mouse enter events
    }

}
