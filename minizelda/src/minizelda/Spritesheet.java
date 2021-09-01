package minizelda;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	public static BufferedImage spritesheet;
	
	public static BufferedImage[] player_front;
	
	public static BufferedImage tileWall;
	
	public static BufferedImage[] arrow;
	
	public static BufferedImage[] enemy_front;
	
	public Spritesheet() {
		
		try {
			spritesheet = ImageIO.read(getClass().getResource("/spritesheet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		player_front = new BufferedImage[2];
		player_front[0] = Spritesheet.getSprite(0, 11, 16, 16);
		player_front[1] = Spritesheet.getSprite(16, 11, 16, 16);
		
		tileWall = Spritesheet.getSprite(281, 221, 16, 16);
		
		arrow = new BufferedImage[2];
		arrow[0] = Spritesheet.getSprite(10, 185, 16, 16);
		arrow[1] = Spritesheet.getSprite(36, 185, 16, 16);
		
		enemy_front = new BufferedImage[2];
		enemy_front[0] = Spritesheet.getSprite(166, 11, 16, 16);
		enemy_front[1] = Spritesheet.getSprite(182, 11, 16, 16);
		
	}
	
	public static BufferedImage getSprite(int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
	}
	
}
