package base;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JFrame;

/**
 * Clase VentanaPrincipal. En ella se pinta el juego.
 * 
 * @author Roberto González Martín
 * @version 1.0
 * @since 1.0
 */
public class VentanaPrincipal {
	// Sigo teniendo la ventana
	JFrame ventana;
	PanelJuego panelJuego;
	Image fondo;

	public VentanaPrincipal() {
		ventana = new JFrame("Pixel Pong");
		ventana.setBounds(100, 50, 400, 600);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * MÃ©todo que inicializa todos los componentes de la ventana
	 */
	public void inicializarComponentes() {
		// Definimos el layout:
		ventana.setLayout(new GridLayout(1, 1));

		// PANEL JUEGO
		panelJuego = new PanelJuego();
		ventana.add(panelJuego);
	}

	/**
	 * MÃ©todo que inicializa todos los listeners del programa.
	 */
	public void inicializarListeners() {

	}

	/**
	 * MÃ©todo que realiza todas las llamadas necesarias para inicializar la ventana
	 * correctamente.
	 */
	public void inicializar() {
		ventana.setVisible(true);
		inicializarComponentes();
		inicializarListeners();
	}
}
