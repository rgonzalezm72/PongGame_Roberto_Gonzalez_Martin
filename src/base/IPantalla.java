package base;

import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * 
 * @author Roberto González Martín. Interfaz Pantalla. Establece los métodos
 *         necesarios a la hora de que creemos las diferentes pantallas
 * @version 1.0
 * @since 1.0
 */
public interface IPantalla {
	public void iniciarPantalla();

	public void pintarPantalla(Graphics g);

	public void ejecutarFrame();

	public void pulsarRaton(MouseEvent e);

	public void pulsarTeclado(KeyEvent e);

	public void levantarTeclado(KeyEvent e);

	public void redimensionarPantalla(ComponentEvent e);
}
