package Version_10;

import java.awt.Color;
import java.awt.Graphics2D;


public class Ladrillo extends Actor{

	//Color por defecto del ladrillo
	Color c = Color.RED;
	int resistencia;
	
	public Ladrillo(Color c) {
		super();
		//Se le asigna al ladrillo un color
		this.c = c;
		width = (Stage.WIDTH/12);
		height = (Stage.HEIGHT/20);
		resistencia = 1;
		
	}
	public Ladrillo(Color c, int resistencia) {
		super();
		//Se le asigna al ladrillo un color
		this.c = c;
		width = (Stage.WIDTH/12);
		height = (Stage.HEIGHT/20);
		this.resistencia = resistencia;
		
	}
	@Override
	public void paint(Graphics2D g) {
		//Se dibuja el ladrillo según su color
		g.setColor(c);
		g.drawRect(this.x,this.y, width-2,height-2);
		g.fillRect(this.x,this.y, width-2,height-2);
	}
	
	public void act() {
	}
	
	public void collision(Actor a) {
		if(a instanceof Bola) {
			resistencia--;
			if(resistencia >= 1) {
			c = Color.LIGHT_GRAY;
			}
			if(resistencia == 0) {
			setMarkedForRemoval(true);
			Explosion ex = new Explosion();
			ex.setX(this.x+(width/2)-ex.getWidth()/2);
			ex.setY(this.y+(height/2)-ex.getHeight()/2);
			Ventana.getInstancia().addNewActorToNextIteration(ex);
			}
		}
	}
	
}
