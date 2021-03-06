package minizelda;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;


public class Player extends Rectangle{
	
	public boolean right, left, up, down;
	public int spd = 4;
	
	public int curAnimation = 0;
	
	public int curFrames = 0, targetFrames = 15;
	
	public int dir = 1;
	
	public int timer = 0;
	
	public static List<Bullet> bullets = new ArrayList<>();	
	
	public boolean shoot = false;
	
	public static boolean alive = true;

	public Player(int x, int y) {
		
		super(x, y, 32, 32);
		
		
	}
	
	
	public void die() {
		if(!alive) {
			new Game();
			return;
		}
	}
	
	public void tick() {
		
		boolean moved = false;
		
		die();
		
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
		
		
		if(right && World.isFree(x+spd, y)) {
			x+=spd;
			moved = true;
			dir = 1;
		} else if(left && World.isFree(x-spd, y)) {
			x-=spd;
			moved = true;
			dir = -1;
		}
		if(up && World.isFree(x, y-spd)) {
			y-=spd;
			moved = true;
		} else if(down && World.isFree(x, y+spd)) {
			y+=spd;
			moved = true;
		}
		
		if(moved) {
			curFrames++;
			if(curFrames == targetFrames) {
				curFrames = 0;
				curAnimation++;
				if(curAnimation == Spritesheet.player_front.length) {
						curAnimation = 0;
				}
			}
		}
	
		
	}
	
	public void render(Graphics g) {
		
		//g.setColor(Color.blue);
		//g.fillRect(x, y, width, height);
			
			g.drawImage(Spritesheet.player_front[curAnimation], x, y, 32, 32, null);
			
			for(int i = 0; i < bullets.size(); i++) {
				bullets.get(i).render(g);
			}
	}
	
	
	
	
}
