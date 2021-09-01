package minizelda;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends Rectangle {
	
	
	public int dir = 1;
	public int speed = 8;
	
	public int frames = 0;
	
	public Bullet(int x, int y, int dir) {
		super(x+16, y+16, 10, 10);
		this.dir = dir;
	}
	
	public static void killedEnemy(int x, int y) {
		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy inimigo = Game.enemies.get(i);
			if(inimigo.intersects(new Rectangle(x, y, 32, 32))) {
				Game.enemies.remove(i);
			}
		}
	}
	
	public void tick() {
		x+=speed*dir;
		frames++;
		killedEnemy(x, y);
		if(frames == 60) {
			frames = 0;
			Player.bullets.remove(this);
		}
	}
	
	
	public void render(Graphics g) {
		if(dir == 1) {
			g.drawImage(Spritesheet.arrow[0], x, y, 16, 16, null);
		}else if(dir == -1) {
			g.drawImage(Spritesheet.arrow[1], x, y, 16, 16, null);
		}
	}
	
}
