package Version_01;

import java.awt.event.KeyEvent;

public class Nave extends Actor{

	protected static final int PLAYER_SPEED = 4;
	protected int vx;
	protected int vy;
	private boolean up,down,left,right;
	
	public Nave(Stage stage) {
		super(stage);
		setSpriteNames (new String[] {"nave5.png"});
		this.x = Stage.WIDTH/2;
		this.y = 600;
	}
	
	public void act() {
		this.x += this.vx;
		this.y += this.vy;
		
		if(this.x < 0)
			this.x = 0;
		if(this.x > Stage.WIDTH - getWidth())
			x = Stage.WIDTH - getWidth();
		
		if(this.y < 0)
			this.y = 0;
		if(this.y > Stage.HEIGHT - getHeight())
			this.y = Stage.HEIGHT - getHeight();
		
	}

	public int getVx() { return this.vx; }
	public void setVx( int i) { this.vx = i; }
	
	public int getVy() { return this.vy; }
	public void setVy( int i) { this.vy = i; }
	
	protected void updateSpeed() {
		
		this.vx=0; this.vy=0;
		if(this.down) this.vy = PLAYER_SPEED;
		if(this.up) this.vy = -PLAYER_SPEED;
		if(this.left) this.vx = -PLAYER_SPEED;
		if(this.right) this.vx = PLAYER_SPEED;
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		
			case KeyEvent.VK_DOWN : this.down = false;break;
			case KeyEvent.VK_UP : this.up = false;break;
			case KeyEvent.VK_LEFT : this.left = false;break;
			case KeyEvent.VK_RIGHT : this.right = false;break;
		
		}
		
		updateSpeed();
		
	}
	
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		
			case KeyEvent.VK_DOWN : this.down = true;break;
			case KeyEvent.VK_UP : this.up = true;break;
			case KeyEvent.VK_LEFT : this.left = true;break;
			case KeyEvent.VK_RIGHT : this.right = true;break;
			
		}
		
		updateSpeed();
		
	}
}
