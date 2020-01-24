package Version_08;

/**
 * Clase interna que nos aporta precisi�n en el c�lculo de la coordenada que ocupa la bola
 * @author R
 *
 */
public class PuntoAltaPrecision {
	// En lugar de utilizar valores enteros para determinar la coordenada, utilizamos flotantes
	public float x;
	public float y;
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	public PuntoAltaPrecision (float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * T�pico toString()
	 */
	@Override
	public String toString() {
		return "PuntoAltaPrecision [x=" + x + ", y=" + y + "]";
	}
}