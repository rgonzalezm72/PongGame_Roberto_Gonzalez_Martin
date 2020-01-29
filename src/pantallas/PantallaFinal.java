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

/**
 * 
 * @author Roberto González Martín. Clase PantallaFinal que implementa la
 *         interfaz Pantalla. Esta clase nos servirá como el final del programa
 *         mostrando quién es el ganador. El usuario deberá dar click en el
 *         ratón para pasar a la pantalla inicial y volver a iniciar el juego.
 * @version 1.0
 * @since 1.0
 */
public class PantallaFinal implements IPantalla {
	private PanelJuego panelJuego;
	private Font fuenteInicial;
	private Color colorLetras = Color.GRAY;
	private int contadorColorFrames = 0;
	private static final int CAMBIO_COLOR_INICIO = 5;
	private BufferedImage imagenOriginalInicial;
	private Image imagenReescaladaInicial;
	private int puntuacionJugador1;
	private int puntuacionJugador2;

	/**
	 * Constructor de la clase
	 * 
	 * @param panelJuego: El panel de juego que iremos pasando a cada una de las
	 *        pantallas.
	 * @param tiempoJuego: Tiempo total en el que hemos superado ambas pantallas
	 */
	public PantallaFinal(PanelJuego panelJuego, int puntuacionJugador1, int puntuacionJugador2) {
		this.panelJuego = panelJuego;
		this.puntuacionJugador1 = puntuacionJugador1;
		this.puntuacionJugador2 = puntuacionJugador2;
	}

	/**
	 * Método que nos sirve para iniciar la pantalla con los elementos necesarios.
	 */
	@Override
	public void iniciarPantalla() {
		try {
			imagenOriginalInicial = ImageIO.read(new File("Imagenes/fondo_final.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		fuenteInicial = new Font("Arial", Font.BOLD, 20);
		reescalarImagen();
	}

	/**
	 * Método que nos pinta en pantalla los elementos visuales del programa
	 */
	@Override
	public void pintarPantalla(Graphics g) {
		rellenarFondo(g);
		g.setColor(colorLetras);
		g.setFont(fuenteInicial);
		if (puntuacionJugador1 == 5) {
			g.drawString("Ha ganado el Jugador 1", panelJuego.getWidth() / 2 - 125, panelJuego.getHeight() / 2 + 15);
		} else if (puntuacionJugador2 == 5) {
			g.drawString("Ha ganado el Jugador 2", panelJuego.getWidth() / 2 - 125, panelJuego.getHeight() / 2 + 15);
		}

	}

	/**
	 * Método que nos pinta el fondo del programa
	 * 
	 * @param g: Gráficos que pasamos desde el método
	 *        {@link #pintarPantalla(Graphics)}
	 */
	private void rellenarFondo(Graphics g) {
		g.drawImage(imagenReescaladaInicial, 0, 0, null);
	}

	/**
	 * Método que nos sirve para ejecutar los frames necesarios
	 */
	@Override
	public void ejecutarFrame() {
		contadorColorFrames++;
		if (contadorColorFrames % CAMBIO_COLOR_INICIO == 0) {
			if (colorLetras.equals(Color.GRAY)) {
				colorLetras = Color.BLACK;
			} else {
				colorLetras = Color.GRAY;
			}
		}
	}

	/**
	 * Método que nos sirve para hacer acciones al pulsar el ratón
	 */
	@Override
	public void pulsarRaton(MouseEvent e) {
		PantallaInicial pantallaInicial = new PantallaInicial(panelJuego);
		pantallaInicial.iniciarPantalla();
		panelJuego.setPantallaActual(pantallaInicial);
	}

	/**
	 * Método que nos sirve para hacer acciones al pulsar las teclas del teclado
	 */
	@Override
	public void pulsarTeclado(KeyEvent e) {

	}

	/**
	 * Método que nos sirve para hacer acciones al levantar las teclas del teclado
	 */
	@Override
	public void levantarTeclado(KeyEvent e) {

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
		imagenReescaladaInicial = imagenOriginalInicial.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
				Image.SCALE_SMOOTH);
	}

}
