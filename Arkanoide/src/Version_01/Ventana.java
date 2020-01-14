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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Ventana extends Canvas implements Stage{
	
	//Creamos la ventana
	JFrame ventana = new JFrame("Arkanoide");
	
	List<Actor> actores = new ArrayList<Actor>();
	private SpriteCache spriteCache;
	//Alto y ancho de la ventana
	private static final int JFRAME_WIDTH=600;
	private static final int JFRAME_HEIGHT=700;
	private static final int FPS = 101;
	
	private List<Color> colores = new ArrayList<Color>();
	
	private BufferStrategy strategy;
	
	//Singleton
	private static Ventana instancia = null;
		
	public Ventana() {
	
	colores.add(Color.RED);
	colores.add(Color.BLUE);
	colores.add(Color.ORANGE);
	colores.add(Color.CYAN);
	colores.add(Color.PINK);
	colores.add(Color.GREEN);
		
	this.spriteCache = new SpriteCache();
	//Dimensi�n de la ventana y mostrarla	
	JPanel panel = (JPanel)ventana.getContentPane();
	panel.setLayout(new BorderLayout());
	panel.add(this, BorderLayout.CENTER);
	
	ventana.setBounds(0,0,JFRAME_WIDTH,JFRAME_HEIGHT);
	
	ventana.setVisible(true);
	
	ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	ventana.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			cerrarAplicacion();
		}
	});
	
	this.createBufferStrategy(2);
	strategy = this.getBufferStrategy();
	// Con ignoreRepaint le decimos al JFrame que no debe repintarse cuando el Sistema Operativo se lo indique,
	// nosotros nos ocupamos totalmente del refresco de la pantalla
	ventana.setIgnoreRepaint(true);
	// La ventana no podr� redimensionarse
	ventana.setResizable(false);

	this.requestFocus();
	
	
	}
	
	public void initWorld() {
		actores.add(new Nave(this));
		actores.add(new Bola(this));
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
	}
	
	public void updateWorld() {
		for(Actor a : actores) {
			a.act();
		}
	}
	
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
		
	public void paintWorld() {
		getToolkit().getDefaultToolkit().sync();
		Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
		this.strategy.show();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,JFRAME_WIDTH, JFRAME_HEIGHT);
		for(Actor a : actores) {
			a.paint(g);
		}
		this.strategy.show();
		
	}
	
	public SpriteCache getSpriteCache() { return this.spriteCache; }
	
	public void game() {
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
	
	//M�todo main
	public static void main(String[] args) {
	Ventana.getInstancia().game();
	}
}
