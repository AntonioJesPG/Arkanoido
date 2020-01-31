package Version_10;

import java.awt.Color;
import java.util.List;

public class Nivel1 extends Nivel{
	
	public Nivel1(List<Color> colores){
		Ladrillo l = new Ladrillo(colores.get(0));
		int x = 0;
		int y = 0;
		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 12; i++) {
				l = new Ladrillo(colores.get(j));
				l.setX(x);
				// Cada ladrillo va a estar al lado del siguiente hasta llegar a los 12
				x = x + l.getWidth();
				l.setY(y);
				this.nivel.add(l);
			}
			y = y + l.getHeight();
			x = 0;
		}
	}
}
