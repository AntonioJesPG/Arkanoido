package Version_01;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Ventana extends Canvas implements Stage{
	
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
	
	//Creamos el JPanel qu vamos a usar	
	JPanel panel = (JPanel)ventana.getContentPane();
	panel.setLayout(new BorderLayout());
	panel.add(this, BorderLayout.CENTER);
	
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
	
	this.createBufferStrategy(2);
	strategy = this.getBufferStrategy();
	
	// nosotros nos encargamos del refresco de la pantalla y lo indicamos con ignore repaint
	ventana.setIgnoreRepaint(true);
	// La ventana no podrá redimensionarse
	ventana.setResizable(false);
	//Cambiamos el foco al canvas
	this.requestFocus();
	}
	
	//Inicializamos las variables del juego
	public void initWorld() {
		//Creamos la nprivate BufferedImage space;
		nave = new Nave(this);
		//actores.add(nave);
		
		
		//Creamos los ladrillos
		Ladrillo l;
		int x = 0;
		int y = 0;
		for(int j = 0; j < 6; j++) {
			for(int i = 0 ; i < 12; i++) {
				l = new Ladrillo(this, colores.get(j));
				l.setX(((x)));
				x = x + 50;
				l.setY(((y)));
				actores.add(l);	
			}
			y = y + 30;
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
		nave.act();
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
		for(Actor a : actores) {
			a.paint(g);
		}
		nave.paint(g);
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
	
	public void keyReleased(KeyEvent e) {
		nave.keyReleased(e);
	}
	public void keyPressed(KeyEvent e) {
		nave.keyPressed(e);	
	}
	
	//M�todo main
	public static void main(String[] args) {
	Ventana.getInstancia().game();
	}
}
