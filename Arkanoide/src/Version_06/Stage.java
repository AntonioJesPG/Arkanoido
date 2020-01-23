package Version_06;

import java.awt.image.ImageObserver;

public interface Stage extends ImageObserver{
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 700;
	public static final int speed = 10;
	public static final int PLAY_HEIGHT = 400;
	
	public SpriteCache getSpriteCache();
	//public SoundCache getSoundCache();
	//public Player getPlayer();
	
	//public void addActor(Actor a);
	//public void gameOver();
	
}
