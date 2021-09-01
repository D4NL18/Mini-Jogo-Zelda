package minizelda;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
	
	public static int width = 640, height = 480;
	public static int scale = 3;
	public static Player player;
	public World world;
	
	public static List<Enemy> enemies = new ArrayList<>();
	
	public Game() {
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(width, height));
		new Spritesheet();
		player = new Player(width/2, height/2);
		world = new World();
		spawnEnemies();
		
	}
	
	public void spawnEnemies() {
		enemies.add(new Enemy(32, 32));
		enemies.add(new Enemy(width-64, 32));
		enemies.add(new Enemy(32, height-64));
		enemies.add(new Enemy(width-64, height-64));
		
		enemies.add(new Enemy(32, height/2));
		enemies.add(new Enemy(width/2, 32));
		enemies.add(new Enemy(width/2, height-64));
		enemies.add(new Enemy(width-64, height/2));
	}
	
	public void killEnemies() {
		enemies.clear();
	}

	public static void main(String[] args) {

		Game game = new Game();
		JFrame frame = new JFrame();
		
		frame.add(game);
		frame.setTitle("Mini Zelda");
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		new Thread(game).start();
		
	}
	
	
	
	public void run() {
		
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	public void tick() {
		
		if(enemies.size() == 0) {
			new Game();
		}
		
		if(!Player.alive) {
			Player.alive = true;
			player.x = width/2;
			player.y = height/2;
			killEnemies();
			spawnEnemies();
		}
		
		player.tick();
		
		for(int i = 0; i< enemies.size(); i++) {
			enemies.get(i).tick();
		}
		
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(0, 135, 13));
		g.fillRect(0, 0, width*scale, height*scale);
		
		player.render(g);
		for(int i = 0; i< enemies.size(); i++) {
			enemies.get(i).render(g);
		}
		world.render(g);
		
		bs.show();
		
	}



	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.shoot = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
		
		
	}
	
	


}
