package minizelda;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;


public class Enemy extends Rectangle{
	
	public int right = 0, left = 0, up = 0, down = 0;
	public int spd = 1;
	
	public int curAnimation = 0;
	
	public int curFrames = 0, targetFrames = 15;
	
	public int dir = 1;
	
	public int timer = 0;
	
	public static List<Bullet> bullets = new ArrayList<>();	
	
	public boolean shoot = false;
	
	public static boolean alive = true;

	public Enemy(int x, int y) {
		
		super(x, y, 32, 32);
		
		
	}
	
	public void followPlayer() {
		Player p = Game.player;
		if(x < p.x && World.isFree(x+spd, y)) {
			x+=spd;
		}else if(x > p.x && World.isFree(x-spd, y)) {
			x-=spd;
		}
		
		if(y < p.y && World.isFree(x, y+spd)) {
			y+=spd;
		}else if(y > p.y && World.isFree(x, y-spd)) {
			y-=spd;
		}
		
		if(x == p.x && y == p.y) {
			p.alive = false;
		}
		
	}
	
	
	
	public void tick() {
		
		boolean moved = true;
		
		followPlayer();
		
		timer++;
		if(timer == 20) {
			timer = 0;
			if(shoot) {
				shoot = false;
				bullets.add(new Bullet(x, y, dir));
				
			}
			
			
		}
		
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
		
		
		if(right == 1 && World.isFree(x+spd, y)) {
			x++;
		}
		
		if(moved) {
			curFrames++;
			if(curFrames == targetFrames) {
				curFrames = 0;
				curAnimation++;
				if(curAnimation == Spritesheet.enemy_front.length) {
						curAnimation = 0;
				}
			}
		}
	
		
	}
	
	public void render(Graphics g) {
		
		//g.setColor(Color.blue);
		//g.fillRect(x, y, width, height);
			
			g.drawImage(Spritesheet.enemy_front[curAnimation], x, y, 32, 32, null);
			
			for(int i = 0; i < bullets.size(); i++) {
				bullets.get(i).render(g);
			}
	}
	
	
	
	
}
