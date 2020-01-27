package Version_10;

import java.awt.Color;
import java.util.List;

public class Nivel2 extends Nivel{
	public Nivel2(List<Color> colores){
		Ladrillo l = new Ladrillo(colores.get(0));
		int x = 0;
		int y = 0;
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 12; i++) {
				if((i < 3 || i > 8) && (j < 2 || j < 6)) {
				l = new Ladrillo(Color.GRAY,2);
				l.setX(x);
				// Cada ladrillo va a estar al lado del siguiente hasta llegar a los 12
				l.setY(y);
				this.nivel.add(l);
				}
				
				if( i > 3 && i < 8 && j > 0 && j < 5 ) {
					l = new Ladrillo(Color.magenta,-1);
					l.setX(x);
					l.setY(y);
					this.nivel.add(l);
				}
				
				if( j == 6) {
					l = new Ladrillo(Color.GRAY,2);
					l.setX(x);
					l.setY(y);
					this.nivel.add(l);
				}
				x = x + l.getWidth();
			}
			y = y + l.getHeight();
			x = 0;
		}
	}
}
