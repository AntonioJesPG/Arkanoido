package Version_05;

import java.awt.Color;
import java.awt.Graphics2D;


public class Bola extends Actor{

	int vx, vy;
	
	public Bola() {
		super();
		this.x = Stage.WIDTH/2;
		this.y = Stage.HEIGHT/2;
		this.vx =  5;
		this.vy =  5;
		this.width = 20;
		this.height = 20;
	}
	
	//Pintado de la bola
	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawOval(this.x,this.y, width,height);
		g.fillOval(this.x,this.y, width,height);
	}
	
	//Movimiento de la bola (necesita mejoras para que sea más natural)
	public void act() {
		
		this.x += this.vx;
		this.y += vy;
		
		if ( x < 0 || x > Stage.WIDTH-width) { 
			this.vx = -this.vx; 
			this.y += vx;
			}
		if( y < 0 || y > Stage.HEIGHT-height*2) {
			this.vy = -this.vy;
		}
		
	}
	public void collision(Actor a) {
		if(a instanceof Ladrillo || a instanceof Nave) {
			this.vy = -vy;
		}
	}
	
	public int getVx() { return this.vx; }
	public void setVx(int i) { this.vx = i; }
}
