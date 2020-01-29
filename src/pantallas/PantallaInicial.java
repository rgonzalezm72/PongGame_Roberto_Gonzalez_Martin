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
 * @author Roberto González Martín. Clase PantallaInicial que implementa la
 *         interfaz Pantalla. Esta clase nos servirá como el inicio del
 *         programa. El usuario deberá dar click en el ratón para pasar a la
 *         siguiente pantalla.
 * @version 1.0
 * @since 1.0
 */
public class PantallaInicial implements IPantalla {
	private PanelJuego panelJuego;
	private Font fuenteInicial;
	private Color colorLetras = Color.ORANGE;
	private int contadorColorFrames = 0;
	private static final int CAMBIO_COLOR_INICIO = 5;
	private BufferedImage imagenOriginalInicial;
	private Image imagenReescaladaInicial;

	/**
	 * Constructor de la clase
	 * 
	 * @param panelJuego: El panel de juego que iremos pasando a cada una de las
	 *        pantallas.
	 */
	public PantallaInicial(PanelJuego panelJuego) {
		this.panelJuego = panelJuego;
	}

	/**
	 * Método que nos sirve para iniciar la pantalla con los elementos necesarios.
	 */
	@Override
	public void iniciarPantalla() {
		try {
			imagenOriginalInicial = ImageIO.read(new File("Imagenes/fondo_inicio.jpg"));
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
		g.drawString("PIXEL PONG: Haz click para empezar", panelJuego.getWidth() / 2 - 175, panelJuego.getHeight() / 2);
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
			if (colorLetras.equals(Color.ORANGE)) {
				colorLetras = Color.PINK;
			} else {
				colorLetras = Color.ORANGE;
			}
		}
	}

	/**
	 * Método que nos sirve para hacer acciones al pulsar el ratón
	 */
	@Override
	public void pulsarRaton(MouseEvent e) {
		PantallaJuego pantallaJuego = new PantallaJuego(panelJuego);
		pantallaJuego.iniciarPantalla();
		panelJuego.setPantallaActual(pantallaJuego);
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
		if (panelJuego.getWidth() == 0 && panelJuego.getHeight() == 0) {
			imagenReescaladaInicial = imagenOriginalInicial.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
		} else {
			imagenReescaladaInicial = imagenOriginalInicial.getScaledInstance(panelJuego.getWidth(),
					panelJuego.getHeight(), Image.SCALE_SMOOTH);
		}
	}

}
