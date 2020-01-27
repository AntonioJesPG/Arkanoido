package Version_10;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Actor {
	protected int x,y;
	protected int width, height;
	
	protected List<BufferedImage> sprites = new ArrayList<BufferedImage>();
	protected BufferedImage spriteActual = null;
	private int unidadDeTiempo = 0;
	protected int velocidadDeCambioDeSprite = 0;
	
	protected boolean markedForRemoval = false;
	
	protected Stage stage;
	protected SpriteCache spriteCache = Ventana.getInstancia().getSpriteCache();;
	
	public Actor() {

	}
	
	public Actor (String spritenames[], int velocidadCambioSprite) {
		this.velocidadDeCambioDeSprite = velocidadCambioSprite;
		setSpriteNames(spritenames);
	}
	
	public Rectangle getBounds() {
		
		return new Rectangle(x,y,width,height);
		
	}
	
	public void act() {
		
		if(this.sprites != null && this.sprites.size() > 1) {
		
			this.unidadDeTiempo++;
			
			if( this.unidadDeTiempo % this.velocidadDeCambioDeSprite == 0) {
				this.unidadDeTiempo = 0;
				int indiceSpritesActual = sprites.indexOf(this.spriteActual);
				int indiceSpriteSiguiente = (indiceSpritesActual +1 )%sprites.size();
				this.spriteActual = sprites.get(indiceSpriteSiguiente);
			}
		}
	}
	
	public void paint(Graphics2D g) {
		g.drawImage( this.spriteActual,this.x,this.y, this.stage);
	}
	
	public int getX() { return this.x; }
	public void setX(int x) { this.x = x; }
	
	public int getY() { return this.y; }
	public void setY(int y) { this.y = y; }
	
	public int getHeight() { return this.height; }
	public void setHeight(int i) { this.height = i; }
	
	public int getWidth() { return this.width; }
	public void setWidth(int i) { this.width = i; }
	
	public void setSpriteNames(String [] names) {
		
		for(String sprite: names) {
			this.sprites.add(this.spriteCache.getSprite(sprite));
		}
		
		if(this.sprites.size() > 0) {
			this.spriteActual = this.sprites.get(0);
		}
		adjustHeightAndWidth();
	}
	
	private void adjustHeightAndWidth() {
		if(this.sprites.size() > 0 ) {
			this.height = this.sprites.get(0).getHeight();
			this.width = this.sprites.get(0).getWidth();
		}
		
		for (BufferedImage sprite : this.sprites) {
			// Ajusto el máximo width como el width del actor
			if (sprite.getWidth() > this.width) {
				this.width = sprite.getWidth();
			}
			// Lo mismo que el anterior, pero con el máximo height
			if (sprite.getHeight() > this.height) {
				this.height = sprite.getHeight();
			}
}
	}
	
	public void collision(Actor a) {	
		
	}
	
	public boolean isMarkedForRemoval() {
		return this.markedForRemoval;
	}
	public void setMarkedForRemoval(boolean valor) {
		this.markedForRemoval = valor;
	}
	public BufferedImage getSpriteActual() {
		return this.spriteActual;
	}
	
}
