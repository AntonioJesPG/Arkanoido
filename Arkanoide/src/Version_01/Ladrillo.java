package Version_01;

import java.awt.Color;
import java.awt.Graphics2D;


public class Ladrillo extends Actor{

	Color c = Color.RED;
	
	public Ladrillo(Stage stage, Color c) {
		super(stage);
		this.c = c;
		
	}
	@Override
	public void paint(Graphics2D g) {
		g.setColor(c);
		g.drawRect(this.x,this.y, 48,28);
		g.fillRect(this.x,this.y, 48,28);
	}
	public void act() {
		
	}
}
