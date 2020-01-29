package pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import base.IPantalla;
import base.PanelJuego;
import base.Sprite;

/**
 * 
 * @author Roberto González Martín. Clase PantallaJuego que implementa la
 *         interfaz Pantalla. Esta clase es en la que se enfrentaran los dos
 *         jugadores para ganar el juego, lo hará el primero que consiga 5
 *         puntos sobre el otro.
 * @version 1.0
 * @since 1.0
 */
public class PantallaJuego implements IPantalla {
	private static final int anchoCuadrado = 30;
	private PanelJuego panelJuego;
	private BufferedImage fondo;
	private Image fondoReescalado;
	private Sprite bola;
	private Sprite jugador1;
	private Sprite jugador2;
	private int puntuacionJugador1 = 0;
	private int puntuacionJugador2 = 0;

	/**
	 * Constructor de la clase
	 * 
	 * @param panelJuego: El panel de juego que iremos pasando a cada una de las
	 *        pantallas.
	 */
	public PantallaJuego(PanelJuego panelJuego) {
		this.panelJuego = panelJuego;
	}

	/**
	 * Método que nos sirve para iniciar la pantalla con los elementos necesarios.
	 */
	@Override
	public void iniciarPantalla() {
		try {
			fondo = ImageIO.read(new File("Imagenes/fondo_juego.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bola = new Sprite(anchoCuadrado, anchoCuadrado, 120, 120, 5, 5, "Imagenes/bola.png");
		jugador1 = new Sprite(150, 50, 120, 0, 0, "Imagenes/jugador1.png");
		jugador2 = new Sprite(150, 50, 120, 500, 0, "Imagenes/jugador2.png");
		// Creamos un sprite Random cuando se pulsa el botÃ³n:
		reescalarImagen();
	}

	/**
	 * Método que nos pinta en pantalla los elementos visuales del programa
	 */
	@Override
	public void pintarPantalla(Graphics g) {
		rellenarFondo(g);
		pintarPuntuacion(g);
		bola.pintarSprite(g);
		jugador1.pintarSprite(g);
		jugador2.pintarSprite(g);
	}

	private void pintarPuntuacion(Graphics g) {
		Font fuente = new Font("Arial", Font.BOLD, 30);
		g.setColor(Color.WHITE);
		g.setFont(fuente);
		g.drawString("" + puntuacionJugador1, 375, 25);
		g.drawString("" + puntuacionJugador2, 375, 565);

	}

	/**
	 * Método que nos pinta el fondo del programa
	 * 
	 * @param g: Gráficos que pasamos desde el método
	 *        {@link #pintarPantalla(Graphics)}
	 */
	private void rellenarFondo(Graphics g) {
		g.drawImage(fondoReescalado, 0, 0, null);
	}

	/**
	 * Método que nos sirve para ejecutar los frames necesarios
	 */
	@Override
	public void ejecutarFrame() {
		comprobarColisiones();
		moverSprites();
	}

	/**
	 * Método que comprueba las colisiones de los diferentes sprites
	 */
	private void comprobarColisiones() {
		if (jugador1.colisiona(bola)) {
			bola.setVelocidadY(Math.abs(bola.getVelocidadY()));
		}
		if (jugador2.colisiona(bola)) {
			bola.setVelocidadY(-1 * Math.abs(bola.getVelocidadY()));
		}
		if (bola.getPosY() == 545) {
			puntuacionJugador1++;
		}
		if (bola.getPosY() == 0) {
			puntuacionJugador2++;
		}
		if (puntuacionJugador1 == 5 || puntuacionJugador2 == 5) {
			PantallaFinal pantallaFinal = new PantallaFinal(panelJuego, puntuacionJugador1, puntuacionJugador2);
			pantallaFinal.iniciarPantalla();
			panelJuego.setPantallaActual(pantallaFinal);
		}
	}

	/**
	 * Método que nos permite mover los diferentes sprites en pantalla
	 */
	private void moverSprites() {
		bola.moverSprite(panelJuego.getWidth(), panelJuego.getHeight());
		jugador1.moverSprite(panelJuego.getWidth(), panelJuego.getHeight());
		jugador2.moverSprite(panelJuego.getWidth(), panelJuego.getHeight());
	}

	/**
	 * Método que nos sirve para hacer acciones al pulsar el ratón
	 */
	@Override
	public void pulsarRaton(MouseEvent e) {

	}

	/**
	 * Método que nos sirve para hacer acciones al pulsar las teclas del teclado
	 */
	@Override
	public void pulsarTeclado(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) {
			jugador1.setVelocidadX(-1 * Math.abs(5));
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			jugador1.setVelocidadX(Math.abs(5));
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			jugador2.setVelocidadX(-1 * Math.abs(5));
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			jugador2.setVelocidadX(Math.abs(5));
		}
	}

	/**
	 * Método que nos sirve para hacer acciones al levantar las teclas del teclado
	 */
	@Override
	public void levantarTeclado(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
			jugador1.setVelocidadX(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			jugador2.setVelocidadX(0);
		}
	}

	/**
	 * Método que nos permite que el fondo no cambie al redimensionar la pantalla
	 */
	@Override
	public void redimensionarPantalla(ComponentEvent e) {
		reescalarImagen();
	}

	/**
	 * Método que nos sirve para reescalar la imagen del fondo
	 * {@link #redimensionarPantalla(ComponentEvent)}
	 */
	private void reescalarImagen() {
		fondoReescalado = fondo.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(), Image.SCALE_SMOOTH);
	}

}
