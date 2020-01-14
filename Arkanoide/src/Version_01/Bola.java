package Version_01;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bola extends Actor{

	public Bola(Stage stage) {
		super(stage);
		this.x = Stage.WIDTH/2;
		this.y = Stage.HEIGHT/2;
	}
	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawOval(this.x,this.y, 150,150);
		g.fillOval(this.x,this.y, 150,150);
	}
	
	public void act() {
		
	}
}
