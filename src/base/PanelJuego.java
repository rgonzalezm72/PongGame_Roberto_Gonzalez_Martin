package base;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import pantallas.PantallaInicial;

/**
 * 
 * @author Roberto Gonz�lez Mart�n. Clase PanelJuego. Controla los gráficos del
 *         Juego. Por ahora también controla la lógica del Juego. Extiende de
 *         JPanel. Todos los gráficos se gestionan mediante los gráficos de un
 *         JPanel. Implementa Runnable porque en el constructor se lanza un hilo
 *         que permite actualizar el Juego periódicamente. Implementa
 *         MouseListener para que pueda capturar las pulsaciones del ratón. Implementa
 *         KeyListener para capturar las pulsaciones del teclado.
 * 
 * @version 1.0
 * @since 1.0
 */
public class PanelJuego extends JPanel implements Runnable {
	private static final long serialVersionUID = 8987185933722893444L;
	IPantalla pantallaActual;

	public PanelJuego() {
		// MouseListener:
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				pantallaActual.pulsarRaton(e);
			}

		});
		// KeyListener:
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				pantallaActual.pulsarTeclado(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				pantallaActual.levantarTeclado(e);
			}
		});
		// ComponentListener:
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				pantallaActual.redimensionarPantalla(e);
			}

		});
		// Lanzo el hilo.
		new Thread(this).start();
		this.setFocusable(true);
		pantallaActual = new PantallaInicial(this);
		pantallaActual.iniciarPantalla();
	}

	/**
	 * Sobreescritura del método paintComponent. Este método se llama
	 * automáticamente cuando se inicia el componente, se redimensiona o bien
	 * cuando se llama al método "repaint()". Nunca llamarlo directamente.
	 * 
	 * @param g Es un Graphics que nos proveé JPanel para poner pintar el
	 *          componente a nuestro antojo.
	 */
	@Override
	public void paintComponent(Graphics g) {
		pantallaActual.pintarPantalla(g);
	}

	/**
	 * Método que se utiliza para rellenar el fondo del JPanel.
	 * 
	 * @param g Es el gráficos sobre el que vamos a pintar el fondo.
	 */

	/**
	 * Método para mover todos los Sprites del juego.
	 */
	@Override
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pantallaActual.ejecutarFrame();
			Toolkit.getDefaultToolkit().sync();
		}
	}

	public IPantalla getPantallaActual() {
		return pantallaActual;
	}

	public void setPantallaActual(IPantalla pantallaActual) {
		this.pantallaActual = pantallaActual;
	}
}
