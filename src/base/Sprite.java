package base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * @author Roberto Gonz醠ez Mart韓. Clase Sprite. Representa un elemento
 *         pintable y colisionable del juego.
 * @version 1.0
 * @since 1.0
 */
public class Sprite {
	private BufferedImage buffer;
	private Color color = Color.BLACK;
	// Variables de dimensi贸n
	private int ancho;
	private int alto;
	// Variables de colocaci贸n
	private int posX;
	private int posY;
	// Variables para la velocidad
	private int velocidadX;
	private int velocidadY;
	// Ruta de la imagen
	private String rutaImagen;

	/**
	 * Constructor simple para un Sprite sin imagen y sin velocidad.
	 * 
	 * @param ancho Ancho que ocupa el Sprite (en pixels)
	 * @param alto  Altura que ocupa el Sprite (en pixels)
	 * @param posX  posici贸n horizontal del sprite en el mundo.
	 * @param posY  posici贸n vertical del Sprite en el mundo. El origen se sit煤a
	 *              en la parte superior.
	 */
	public Sprite(int ancho, int alto, int posX, int posY, String rutaImagen) {
		this.ancho = ancho;
		this.alto = alto;
		this.posX = posX;
		this.posY = posY;
		this.rutaImagen = rutaImagen;
		actualizarBuffer();
	}

	/**
	 * Constructor para un Sprite sin imagen.
	 * 
	 * @param ancho      Ancho que ocupa el Sprite (en pixels)
	 * @param alto       Altura que ocupa el Sprite (en pixels)
	 * @param posX       posici贸n horizontal del sprite en el mundo.
	 * @param posY       posici贸n vertical del Sprite en el mundo. El origen se
	 *                   sit煤a en la parte superior.
	 * @param velocidadX velocidad horizontal del Sprite.
	 * @param velocidadY velocidad vertical del Sprite.
	 */
	public Sprite(int ancho, int alto, int posX, int posY, int velocidadX, int velocidadY, String rutaImagen) {
		this.ancho = ancho;
		this.alto = alto;
		this.posX = posX;
		this.posY = posY;
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
		this.rutaImagen = rutaImagen;
		actualizarBuffer();
	}

	public Sprite(int ancho, int alto, int posX, int posY, int velocidadX, String rutaImagen) {
		this.ancho = ancho;
		this.alto = alto;
		this.posX = posX;
		this.posY = posY;
		this.velocidadX = velocidadX;
		this.rutaImagen = rutaImagen;
		actualizarBuffer();
	}

	/**
	 * M茅todo para actualizar el buffer que guarda cada Sprite. Por ahora s贸lo
	 * guarda un bufferedImage que est谩 completamente relleno de un color.
	 */
	public void actualizarBuffer() {
		buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
		Graphics g = buffer.getGraphics();
		try {
			BufferedImage imagenSprite = ImageIO.read(new File(rutaImagen));
			g.drawImage(imagenSprite.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH), 0, 0, null);
		} catch (Exception e) {
			g.setColor(color);
			g.fillRect(0, 0, ancho, alto);
			g.dispose();
		}
	}

	/**
	 * M茅todo para mover el Sprite por el mundo.
	 * 
	 * @param anchoMundo ancho del mundo sobre el que se mueve el Sprite
	 * @param altoMundo  alto del mundo sobre el que se mueve el Sprite
	 */
	public void moverSprite(int anchoMundo, int altoMundo) {
		if (posX >= anchoMundo - ancho) { // por la derecha
			velocidadX = -1 * Math.abs(velocidadX);
		}
		if (posX <= 0) {// por la izquierda
			velocidadX = Math.abs(velocidadX);
		}
		if (posY >= altoMundo - alto) {// por abajo
			velocidadY = -1 * Math.abs(velocidadY);
		}
		if (posY <= 0) { // Por arriba
			velocidadY = Math.abs(velocidadY);
		}
		posX = posX + velocidadX;
		posY = posY + velocidadY;
	}

	/**
	 * M茅todo que pinta el Sprite en el mundo teniendo en cuenta las
	 * caracter铆sticas propias del Sprite.
	 * 
	 * @param g Es el Graphics del mundo que se utilizar谩 para pintar el Sprite.
	 */
	public void pintarSprite(Graphics g) {
		g.drawImage(buffer, posX, posY, null);
	}

	/**
	 * M茅todo que nos dice si el Sprite ha colisionado con otro
	 * 
	 * @param otro Es el Sprite con el que comprobaremos si ambos han colisionado
	 * @return Retorna si hay colisi贸n
	 */
	public boolean colisiona(Sprite otro) {

		boolean colisionEjeX = false;

		// 緾u醠 m醩 a la izq??:

		if (posX > otro.posX) { // El de de la izquierda es el otro:

			if (otro.getPosX() + otro.getAncho() >= posX) {

				colisionEjeX = true;

			}

		} else { // si no yo soy el de la izq.

			if (posX + ancho >= otro.getPosX()) {

				colisionEjeX = true;

			}

		}

		// EJE Y

		boolean colisionEjeY = false;

		if (posY > otro.posY) { // El de de la izquierda es el otro:

			if (otro.getPosY() + otro.getAlto() >= posY) {

				colisionEjeY = true;

			}

		} else { // si no yo soy el de la izq.

			if (posY + alto >= otro.getPosY()) {

				colisionEjeY = true;

			}

		}

		return colisionEjeX && colisionEjeY;

	}

	public BufferedImage getBuffer() {
		return buffer;
	}

	public void setBuffer(BufferedImage buffer) {
		this.buffer = buffer;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}

	public int getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
}
