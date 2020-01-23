package Version_06;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Nave extends Actor{

	protected static final int PLAYER_SPEED = 4;
	protected int vx;
	private boolean left,right,control, n , shift;
	
	public Nave() {
		super();
		setSpriteNames (new String[] {"nave5.png"});
		this.x = Stage.WIDTH/2;
		this.y = 600;
	}
	
	//Movimiento de la nave
	public void act() {
		super.act();
		//Gracias www.trucoteca.com
		if(control && shift && n) {
			this.x = Ventana.getInstancia().getBola().getX();
		}
		//Si quieres ser legal
		else {
		this.x += this.vx;
		}
		if(this.x < 0)
			this.x = 0;
		if(this.x > Stage.WIDTH - getWidth())
			x = Stage.WIDTH - getWidth();
		}

	public int getVx() { return this.vx; }
	public void setVx( int i) { this.vx = i; }

	
	//Método para cambiar la velocidad del jugador
	protected void updateSpeed() {
		
		this.vx=0;
		if(this.left) this.vx = -PLAYER_SPEED;
		if(this.right) this.vx = PLAYER_SPEED;
		
	}
	
	//Movimiento del ratón
	public void mouseMoved(MouseEvent e) {
		if(e.getX() > 0 && e.getX() < Stage.WIDTH - getWidth()) {
		this.x = e.getX();
		}
	}
	

	public void mouseDragged(MouseEvent e) {

	}
	
	//Liberar una tecla
	public void keyReleased(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		
			case KeyEvent.VK_LEFT : this.left = false;break;
			case KeyEvent.VK_RIGHT : this.right = false;break;
		
		}
		
		updateSpeed();
		
	}
	
	//Pulsar una tecla
	public synchronized void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		
			case KeyEvent.VK_LEFT : this.left = true;break;
			case KeyEvent.VK_RIGHT : this.right = true;break;
			//Para el cheat
			case KeyEvent.VK_CONTROL : this.control = true;break;
			case KeyEvent.VK_SHIFT : this.shift = true;break;
			case KeyEvent.VK_N : this.n = true;break;
			case KeyEvent.VK_S : this.control = false; this.n=false; this.shift = false; break;
			
		}
		
		updateSpeed();
		
	}
	
	public void collision(Actor a) {
	
	}
}
