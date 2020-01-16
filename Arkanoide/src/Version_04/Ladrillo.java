package Version_04;

import java.awt.Color;
import java.awt.Graphics2D;


public class Ladrillo extends Actor{

	//Color por defecto del ladrillo
	Color c = Color.RED;
	
	public Ladrillo(Stage stage, Color c) {
		super(stage);
		//Se le asigna al ladrillo un color
		this.c = c;
		width = (Stage.WIDTH/12);
		height = Stage.HEIGHT/20;
		
	}
	@Override
	public void paint(Graphics2D g) {
		//Se dibuja el ladrillo según su color
		g.setColor(c);
		g.drawRect(this.x,this.y, width-2,height-2);
		g.fillRect(this.x,this.y, width-2,height-2);
	}
	public void act() {
		System.out.println();
	}
	
	public void collision(Actor a) {
		if(a instanceof Bola) {
			remove();
		}
	}
	
}
