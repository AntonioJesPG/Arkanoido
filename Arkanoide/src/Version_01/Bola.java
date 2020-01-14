package Version_01;

import java.awt.Color;
import java.awt.Graphics2D;


public class Bola extends Actor{

	int vx, vy;
	int tamanioBola;
	
	public Bola(Stage stage) {
		super(stage);
		this.x = Stage.WIDTH/2;
		this.y = Stage.HEIGHT/2;
		this.vx =  3;
		this.vy =  3;
		this.tamanioBola = 30;
	}
	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawOval(this.x,this.y, tamanioBola,tamanioBola);
		g.fillOval(this.x,this.y, tamanioBola,tamanioBola);
	}
	
	public void act() {
		
		this.x += this.vx;
		this.y += vy;
		
		if ( x < 0 || x > Stage.WIDTH-tamanioBola) { 
			this.vx = -this.vx; 
			this.y += vx;
			}
		if( y < 0 || y > Stage.HEIGHT-tamanioBola*2) {
			this.vy = -this.vy;
		}
		
	}
	
	public int getVx() { return this.vx; }
	public void setVx(int i) { this.vx = i; }
}
