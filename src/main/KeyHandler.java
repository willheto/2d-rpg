package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class KeyHandler implements KeyListener, MouseListener, MouseMotionListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed, kPressed, oPressed;
    public int inventorySlotClicked;

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
            if (x > 551 && x <= 743 && y > 213 && y <= 457) {
                // Normalize x and y to be relative to the top-left corner of the inventory
                int normalizedX = x - 552;
                int normalizedY = y - 214;

                // Calculate the column and row
                int col = (int) (normalizedX / 47.5);
                int row = (int) (normalizedY / 48.4);

                // Calculate the slot index
                int slotIndex = row * 4 + col;

                // Set inventory slot clicked
                inventorySlotClicked = slotIndex;
                System.out.println("Slot clicked: " + slotIndex);
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
    public void mouseEntered(MouseEvent e) {
        // Handle mouse enter events
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
    public void mouseMoved(MouseEvent e) {
        // Handle mouse move events
    }
}
