package Version_01;

import java.awt.Color;
import java.awt.Graphics2D;


public class Ladrillo extends Actor{

	//Color por defecto del ladrillo
	Color c = Color.RED;
	//Alto y ancho del ladrillo
	int anchoLadrillo;
	int altoLadrillo;
	
	public Ladrillo(Stage stage, Color c) {
		super(stage);
		//Se le asigna al ladrillo un color
		this.c = c;
		anchoLadrillo = Stage.WIDTH/12;
		altoLadrillo = Stage.HEIGHT/20;
		
	}
	@Override
	public void paint(Graphics2D g) {
		//Se dibuja el ladrillo según su color
		g.setColor(c);
		g.drawRect(this.x,this.y, anchoLadrillo-2,altoLadrillo-2);
		g.fillRect(this.x,this.y, anchoLadrillo-2,altoLadrillo-2);
	}
	public void act() {
		
	}
	public int getAnchoLadrillo() {
		return anchoLadrillo;
	}
	public void setAnchoLadrillo(int anchoLadrillo) {
		this.anchoLadrillo = anchoLadrillo;
	}
	public int getAltoLadrillo() {
		return altoLadrillo;
	}
	public void setAltoLadrillo(int altoLadrillo) {
		this.altoLadrillo = altoLadrillo;
	}
	
}
