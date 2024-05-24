package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import main.UI.UI;
import object.SuperObject;
import tile.TileManager;
import util.ExperienceUtils;

public class GamePanel extends JPanel implements Runnable {

	final int originalTileSize = 16;
	final int scale = 3;

	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 11;
	public final int maxScreenRow = 7;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;

	// World settings
	public final int maxWorldCol = 100;
	public final int maxWorldRow = 100;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	int FPS = 60;

	TileManager tileManager = new TileManager(this);
	public KeyHandler keyHandler = new KeyHandler(this);
	Sound music = new Sound();
	Sound sfx = new Sound();

	public CollisionManager collisionManager = new CollisionManager(this);
	public AssetSetter assetSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventManager eventManager = new EventManager(this);
	public ExperienceUtils experienceUtils = new ExperienceUtils(this);

	public Player player = new Player(this, keyHandler);
	public Entity obj[] = new Entity[10];
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[20];
	public SuperObject itemsOnGround[] = new SuperObject[10];
	ArrayList<Entity> entityList = new ArrayList<Entity>();

	public int gameState;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;

	Thread gameThread;

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth + 5 * tileSize, screenHeight + 3 * tileSize));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void setupGame() {
		assetSetter.setObject();
		assetSetter.setNPC();
		assetSetter.setMonster();

		playMusic(0);
		gameState = playState;
	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		long timer = 0;
		int drawCount = 0;

		while (gameThread != null) {
			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}

			if (timer >= 1000000000) {

				drawCount = 0;
				timer = 0;

			}
		}
	}

	public void update() {
		if (gameState == playState) {

			player.update();
			for (int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					npc[i].update();
				}
			}
			for (int i = 0; i < monster.length; i++) {
				if (monster[i] != null) {
					if (monster[i].alive == true && monster[i].dying == false) {
						monster[i].update();
					}
					if (monster[i].alive == false) {
						monster[i] = null;
					}
				}
			}

		}
		if (gameState == pauseState) {
		}
	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2 = (Graphics2D) graphics;

		long drawStartTime = 0;
		if (keyHandler.checkDrawTime == true) {
			drawStartTime = System.nanoTime();
		}

		tileManager.draw(graphics2);

		entityList.add(player);
		addNpcsToEntityList();
		addObjectsToEntityList();
		addMonstersToEntityList();

		Collections.sort(entityList, new Comparator<Entity>() {

			@Override
			public int compare(Entity e1, Entity e2) {
				int result = Integer.compare(e1.worldY, e2.worldY);
				return result;
			}

		});

		drawObjectsOnGround(graphics2);
		drawEntities(graphics2);

		ui.draw(graphics2);

		graphics2.dispose();

	}

	private void drawEntities(Graphics2D graphics2d) {
		for (int i = 0; i < entityList.size(); i++) {
			entityList.get(i).draw(graphics2d);
		}
		entityList.clear();
	}

	private void drawObjectsOnGround(Graphics2D graphics2d) {
		for (int i = 0; i < itemsOnGround.length; i++) {
			if (itemsOnGround[i] != null) {

				int screenX = itemsOnGround[i].worldX - player.worldX + player.screenX;
				int screenY = itemsOnGround[i].worldY - player.worldY + player.screenY;

				graphics2d.drawImage(itemsOnGround[i].image, screenX + 5, screenY + 5,
						tileSize - 5, tileSize - 5, null);
			}
		}
	}

	private void addMonstersToEntityList() {
		for (int i = 0; i < monster.length; i++) {
			if (monster[i] != null) {
				entityList.add(monster[i]);
			}
		}
	}

	private void addNpcsToEntityList() {
		for (int i = 0; i < npc.length; i++) {
			if (npc[i] != null) {
				entityList.add(npc[i]);
			}
		}
	}

	private void addObjectsToEntityList() {
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				entityList.add(obj[i]);
			}
		}
	}

	public void playMusic(int i) {
		music.play(i);
		music.loop(i);
	}

	public void stopMusic(int i) {
		music.stop(i);
	}

	public void playSoundEffect(int i) {
		sfx.play(i);
	}
}
