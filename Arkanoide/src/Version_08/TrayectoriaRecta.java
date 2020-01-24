package Version_08;

/**
 * Clase que implementa una trayectoria en l�nea recta. En realidad, en su interior esta clase utiliza una recta en su
 * ecuaci�n   y = mx + n   siendo "m" la pendiente de la recta y "n" la ordenada en el origen de la misma.
 * Para poder trazar una recta vamos a tener dos constructores: podremos crear una recta a trav�s de dos puntos y tambi�n
 * a trav�s de una pendiente y un punto que pertenezca a la recta.
 * Adem�s, una recta puede recorrerse en dos sentidos, les he llamado "direccionCreciente" y "direccionDecreciente". Estos
 * dos sentidos se controlan con una variable booleana.
 * Para recorrer la recta utilizaremos el concepto de distancia. Partiendo de la base de que el punto actual en el que la
 * bola se encuentra pertenece a la trayectoria, para determinar el siguiente punto al que la bola debe llegar lo har� 
 * indicando la distancia del punto actual sobre la misma trayectoria.
 * @author R
 *
 */
public class TrayectoriaRecta {
	
	private float m; // Pendiente
	private float o; // ordenada en el origen
	private boolean direccionCreciente = false; // Direcci�n en la que se recorre la recta
	
	/**
	 * Constuctor de una trayectoria a partir de dos puntos de alta precisi�n y una direcci�n en la que se recorre
	 * @param p1
	 * @param p2
	 * @param direccionCreciente
	 */
	public TrayectoriaRecta (PuntoAltaPrecision p1, PuntoAltaPrecision p2, boolean direccionCreciente) {
		this.m = (p2.y - p1.y) / (p2.x - p1.x); // Calculo de la pendiente
		this.o = p1.y - this.m * p1.x; // Calculo de la ordenada en el origen
		this.direccionCreciente = direccionCreciente;
	}
		
	/**
	 * Constructor de una trayectoria a partir de un valor de su pendiente y un punto que pertenezca a la misma
	 * @param pendiente
	 * @param p
	 * @param direccionCreciente
	 */
	public TrayectoriaRecta (float pendiente, PuntoAltaPrecision p, boolean direccionCreciente) {
		this.m = pendiente;
		this.direccionCreciente = direccionCreciente;
		this.o = p.y - this.m * p.x; // Calculo de la ordenada en el origen
	}
	
	/**
	 * M�todo principal de la clase. Sobre la trayectoria actual calcular� un punto "destino" que se encuentre
	 * a una distancia concreta del punto "origen" y que se encuentre sobre la recta
	 * @param origen
	 * @param distancia
	 * @return
	 */
	public PuntoAltaPrecision getPuntoADistanciaDePunto (PuntoAltaPrecision origen, float distancia) {
		PuntoAltaPrecision destino = new PuntoAltaPrecision(0, 0);
		
		// Calculo de la coordenada x del punto de destino. Se hace resolviendo una ecuaci�n de segundo grado
		float a = 1 + m * m; // Coeficiente "a" de la ecuaci�n de segundo grado
		float b = (0-2) * (1+m*m)*origen.x; // Coeficiente "b" de la ecuaci�n de segundo grado
		float c = a*origen.x*origen.x-distancia*distancia; // Coeficiente "c" de la ecuaci�n de segundo grado
		
		// La ecuaci�n de segundo grado tiene dos soluciones pero nosotros estamos recorriendo la trayectoria
		// en uno de los sentidos.
		// Calculo la coordenada x del punto de destino
		if (this.direccionCreciente) {
			destino.x = (float) ((0 - b) + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
		}
		else {
			destino.x = (float) ((0 - b) - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
		}
		
		// Calculo de la coordenada y del punto de destino
		destino.y = m * destino.x + o;
		
		// Devuelvo el nuevo punto calculado
		return destino;
	}
	
	/**
	 * M�todo auxiliar, que calcula la distancia entre dos puntos dados. Utiliza la f�rmula de la distancia
	 * entre dos puntos
	 * @param p1
	 * @param p2
	 * @return
	 */
	public float getDistanciaPuntoAPunto (PuntoAltaPrecision p1, PuntoAltaPrecision p2) {
		return (float) Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
	}
	
	/**
	 * Permite reflejar verticalmente una trayectoria, esto hace que la pelota pueda rebotar de arriba hacia 
	 * abajo. Para determinar el lugar en el que "reflejamos" la trayectoria recibimos un punto como argumento
	 * @param p
	 */
	public void reflejarVerticalmenteRespectoAPunto (PuntoAltaPrecision p) {
		// Cambio de signo la pendiente de la recta y calculo el nuevo corte de la recta en el origen, 
		// aprovechando el saber que la recta pasa por un punto que me dan como argumento de entrada.
		this.m = -this.m;
		this.o = p.y - this.m * p.x;
	}
	
	/**
	 * M�todo muy parecido al anterior, en el que el reflejo se produce de derecha a izquierda, tambi�n
	 * respecto a un punto determinado
	 * @param p
	 */
	public void reflejarHorizontalmenteRespectoAPunto (PuntoAltaPrecision p) {
		// Cambio de signo la pendiente, calculo el nuevo punto de corte del eje de abcisas y cambio
		// la direcci�n en la que nos movemos sobre la trayectoria
		this.m = -this.m;
		this.o = p.y - this.m * p.x;
		this.direccionCreciente = !this.direccionCreciente;
	}
	
	
	/**
	 * M�todo para reflejar la trayectoria hac�a abajo respecto a un punto de pivote
	 * @param p
	 */
	public void reflejarHaciaAbajo (PuntoAltaPrecision p) {
		this.setPendiente(-this.m, p);
		this.direccionCreciente = (this.m < 0)? false : true;
	}
	
	/**
	 * M�todo para reflejar la trayectoria hac�a arriba respecto a un punto de pivote
	 * @param p
	 */
	public void reflejarHaciaArriba (PuntoAltaPrecision p) {
		this.setPendiente(-this.m, p);
		this.direccionCreciente = (this.m < 0)? true : false;
	}
	
	/**
	 * M�todo para reflejar la trayectoria hac�a la derecha respecto a un punto de pivote
	 * @param p
	 */
	public void reflejarHaciaDerecha (PuntoAltaPrecision p) {
		this.setPendiente(-this.m, p);
		this.direccionCreciente = true;
	}
	
	/**
	 * M�todo para reflejar la trayectoria hac�a la izquierda respecto a un punto de pivote
	 * @param p
	 */
	public void reflejarHaciaIzquierda (PuntoAltaPrecision p) {
		this.setPendiente(-this.m, p);
		this.direccionCreciente = false;
	}
	
	
	/**
	 * M�todo para modificar la pendiente por una nueva pendiente, adem�s tambi�n es necesario calcular
	 * el nuevo punto de corte del eje de abcisas. Cuando se cambia la trayectoria siempre es necesario
	 * hacerlo con un punto de pivote.
	 * @param nuevaPendiente
	 * @param puntoDePivote
	 */
	public void setPendiente (float nuevaPendiente, PuntoAltaPrecision puntoDePivote) {
		setPendiente(nuevaPendiente, puntoDePivote, this.direccionCreciente);
	}
	
	/**
	 * M�todo para modificar la pendiente por una nueva pendiente, adem�s tambi�n es necesario calcular
	 * el nuevo punto de corte del eje de abcisas. Cuando se cambia la trayectoria siempre es necesario
	 * hacerlo con un punto de pivote.
	 * @param nuevaPendiente
	 * @param puntoDePivote
	 */
	public void setPendiente (float nuevaPendiente, PuntoAltaPrecision puntoDePivote, boolean direccionCreciente) {
		this.m = nuevaPendiente;
		this.o = puntoDePivote.y - this.m * puntoDePivote.x;
		this.direccionCreciente = direccionCreciente;
	}
	
	/**
	 * T�pico toString
	 */
	@Override	
	public String toString () {
		return "y = " + this.m + "x + " + this.o + " - direcci�n creciente: " + this.direccionCreciente;
	}
	
	
	
	
	
	// Getters y setters
	public float getPendiente() {
		return m;
	}

	public void setPendiente(float pendiente) {
		this.m = m;
	}
}
