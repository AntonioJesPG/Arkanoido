 package Version_10;

public class Explosion extends Actor{

	private static String[] SPRITES = new String[] {"sprite-explosion1.png","sprite-explosion2.png","sprite-explosion3.png",
			"sprite-explosion4.png","sprite-explosion5.png","sprite-explosion6.png"
			,"sprite-explosion7.png","sprite-explosion8.png","sprite-explosion9.png"};
	
	public Explosion() {
		super(SPRITES,6);
	}
	
	@Override
	public void act() {
		super.act(); // Necesario para controlar diferentes aspectos de los actores
		
		// Si el actor llega a tocar el límite inferior de la escena, desaparecerá
		if (this.getSpriteActual().equals(this.sprites.get(this.sprites.size()-1))) {
			this.setMarkedForRemoval(true);
		}
}
	
}
