package Version_01;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Ventana extends Canvas implements Stage, KeyListener, MouseMotionListener{
	
	//Creamos la ventana
	JFrame ventana = new JFrame("Arkanoide");
	
	//Alto y ancho de la ventana
	private static final int JFRAME_WIDTH=600;
	private static final int JFRAME_HEIGHT=700;
	
	//Ejecuciones por segundo
	private static final int FPS = 60;
	
	//Lista con los colores que se van a usar para los ladrillos
	private List<Color> colores = new ArrayList<Color>();
	//Lista con los actores que se usan en el juego
	List<Actor> actores = new ArrayList<Actor>();
	
	private BufferStrategy strategy;
	private long usedTime;
	private SpriteCache spriteCache;
	
	//Variable del jugador
	private Nave nave;
	
	//Fondo del juego
	private BufferedImage space;
	
	//Singleton
	private static Ventana instancia = null;
		
	public Ventana() {
	
	//Añadimos los colores que vamos a usar a su array
	colores.add(Color.RED);
	colores.add(Color.BLUE);
	colores.add(Color.ORANGE);
	colores.add(Color.CYAN);
	colores.add(Color.PINK);
	colores.add(Color.GREEN);
		
	this.spriteCache = new SpriteCache();
	
	//Creamos el JPanel que vamos a usar con un borderLayout	
	JPanel panel = (JPanel)ventana.getContentPane();
	panel.setLayout(new BorderLayout());
	panel.add(this, BorderLayout.CENTER);
	
	//Límites de la ventana
	ventana.setBounds(0,0,JFRAME_WIDTH,JFRAME_HEIGHT);
	
	//Mostramos la ventana
	ventana.setVisible(true);
	//Añadimos nueva opción de cierre de la ventana
	ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	ventana.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			cerrarAplicacion();
		}
	});
	
	//Creación de la estrategía del Graphics2D
	this.createBufferStrategy(2);
	strategy = this.getBufferStrategy();
	
	// nosotros nos encargamos del refresco de la pantalla y lo indicamos con ignore repaint
	ventana.setIgnoreRepaint(true);
	// La ventana no podrá redimensionarse
	ventana.setResizable(false);
	//Cambiamos el foco al canvas
	this.requestFocus();
	
	//Se añaden a la ventana los listener del ratón y el teclado para el movimiento
	this.addMouseMotionListener(this);
	this.addKeyListener(this);
	}
	
	//Inicializamos las variables del juego
	public void initWorld() {
		//Creamos la nprivate BufferedImage space;
		nave = new Nave(this);
		actores.add(nave);
		
		//Creamos los ladrillos
		Ladrillo l = new Ladrillo(this, colores.get(0));
		int x = 0;
		int y = 0;
		for(int j = 0; j < 6; j++) {
			for(int i = 0 ; i < 12; i++) {
				l = new Ladrillo(this, colores.get(j));
				l.setX(((x)));
				//Cada ladrillo va a estar al lado del siguiente hasta llegar a los 12
				x = x + l.getAnchoLadrillo();
				l.setY(((y)));
				actores.add(l);	
			}
			y = y + l.getAltoLadrillo();
			x = 0;
		}
		//Añadimos la bola
		actores.add(new Bola(this));
	}
	
	//Método para actualizar el juego (Cada actor realiza su función)
	public void updateWorld() {
		for(Actor a : actores) {
			a.act();
		}
	}
	
	//Método para cerrar la ventana 
	private void cerrarAplicacion() {
		String[] opciones = {"Aceptar","Cancelar"};
		//Creamos las opciones de salir de la aplicaci�n
		int eleccion = JOptionPane.showOptionDialog(ventana, "¿Salir de la aplicación?","Salir",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		//Si el usuario desea salir lo cerramos
		if(eleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	//Singleton
	public static Ventana getInstancia () {
		if (instancia == null) {
			instancia = new Ventana();
		}
		return instancia;
	}
		
	//Método paint del juego
	public void paintWorld() {
		getToolkit().getDefaultToolkit().sync();
		Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
		this.strategy.show();
		
		//Fondo de pantalla
		space = spriteCache.getSprite("space2.gif");
		g.setPaint(new TexturePaint(space, new Rectangle(0,0,space.getWidth(),space.getHeight())));
		g.fillRect(0,0,JFRAME_WIDTH, JFRAME_HEIGHT);
		
		//Llamamos al método paint de cada actor
		for(Actor a : actores) {
			a.paint(g);
		}
		//Mostramos la estrategia
		this.strategy.show();
		
	}
	
	public SpriteCache getSpriteCache() { return this.spriteCache; }
	
	//Método de juego
	public void game() {
		//Iniciamos los elementos del juego
		initWorld();
		
		while(this.isVisible()) {
			long millisAntesDeConstruirEscena = System.currentTimeMillis();
			// Actualizamos y pintamos el nuevo frame
			updateWorld();
			paintWorld();
			// Calculamos la cantidad de milisegundos que se ha tardado en realizar un nuevo frame del juego
			int millisUsados = (int) (System.currentTimeMillis() - millisAntesDeConstruirEscena);

			try { 
				int millisADetenerElJuego = 1000 / FPS - millisUsados;
				if (millisADetenerElJuego >= 0) {
					 Thread.sleep(millisADetenerElJuego);
				}
			} catch (InterruptedException e) {}
		}
	}
	
	//Llamamos al método de despulsar una tecla
	public void keyReleased(KeyEvent e) {
		nave.keyReleased(e);
	}
	//Llamamos al método de pulsar una tecla
	public void keyPressed(KeyEvent e) {
		nave.keyPressed(e);	
	}
	//Llamamos al método de mover el ratón
	public void mouseMoved(MouseEvent e) {
		nave.mouseMoved(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	//M�todo main
	public static void main(String[] args) {
	Ventana.getInstancia().game();
	}
}
