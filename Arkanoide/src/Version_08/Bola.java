package Version_08;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Date;


public class Bola extends Actor{
	//Para calcular los segundos en los que inicia el juego
	Date startDate = new Date();
	Date endDate;
	boolean inicioJuego=true;
	
	//Fijamos el inicio a 5 segundos si no se pulsa el boton antes
	private static final int TIEMPO_INICIO = 5;
	int segundos;
	
	//Distancia de la bola y aumento de velocidad
	int distancia = 5;
	int factorVelocidad = 10000;
	
	//El punto de la bola y su trayectoria
	PuntoAltaPrecision p;
	TrayectoriaRecta t;
	
	public Bola() {
		super();
		this.x = Ventana.getInstancia().getNave().getX();
		this.y = Ventana.getInstancia().getNave().getY()-20;
		this.width = 20;
		this.height = 20;
	}
	
	//Pintado de la bola
	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawOval(this.x,this.y, width,height);
		g.fillOval(this.x,this.y, width,height);
	}
	
	//Movimiento de la bola (necesita mejoras para que sea más natural)
	public void act() {
		//Cada vez que se llama a act aumenta su velocidad
		distancia += distancia/factorVelocidad;
		
		//Si no empieza en 5s el juego comienza solo
		endDate = new Date();
		segundos = (int) ((endDate.getTime()-startDate.getTime())/1000);
		if(segundos >= TIEMPO_INICIO && inicioJuego) iniciarJuego();
		
		//Posición inicial de la bola junto a la nave
		if(inicioJuego) {
			this.x = Ventana.getInstancia().getNave().getX();
			this.y = Ventana.getInstancia().getNave().getY()-20;
		}			
		
		else {	
			
			//Límite con derecha e izquierda
			if (x <= 0 || x > (Ventana.getInstancia().getWidth() - this.width)) {
				t.reflejarHorizontalmenteRespectoAPunto(p);
			}
				
			//Límite con arriba y abajo
			if( y <= 0 || y > (Ventana.getInstancia().getHeight() - this.height)) {
				t.reflejarVerticalmenteRespectoAPunto(p);
			}
				
			//Se calcula el siguiente punto
			p = t.getPuntoADistanciaDePunto(p, distancia);
			//Nueva posición de la bola
			this.x = Math.round(p.x);
			this.y = Math.round(p.y);	

			//Límites que no puede pasar la bola
			if (this.x < 0) x = 0;
			if (this.y < 0) y = 0;
		}
	}
	
	//Método para iniciar el movimiento de la bola
	public void iniciarJuego() {
		this.inicioJuego = false;
		p = new PuntoAltaPrecision(x,y);
		this.t = new TrayectoriaRecta(1.7f, this.p, false);
		AlmacenSonidos.getInstance().playSound(AlmacenSonidos.getInstance().BOLA_INICIO);
	}
	
	//Colisión de la bola con la nave o los ladrillos
	public void collision(Actor a) {
		if(!inicioJuego) {
			if(a instanceof Ladrillo || a instanceof Nave) {
				t.reflejarVerticalmenteRespectoAPunto(p);
			}
		}
	}
}
