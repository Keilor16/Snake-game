package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Domain.Juego;
import Utility.Size;

public class PanelJuego extends JPanel implements ActionListener, KeyListener {

	private Juego juego; // logica del juego

	public PanelJuego() {
		this.setPreferredSize(new Dimension(Size.SIZE_PANTALLA, Size.SIZE_PANTALLA));
		this.setFocusable(true);
		this.addKeyListener(this);
		this.juego = new Juego(this);
		this.juego.iniciarJuego();
	} // constructor

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // se deja por estandar
		this.setBackground(Color.BLACK);
		this.juego.dibujar(g);
		if(!this.juego.isActivo()) {
			Window ventana = SwingUtilities.getWindowAncestor(this);
            ventana.dispose(); // cierra la ventana actual
            JOptionPane.showMessageDialog(getComponentPopupMenu(), "El juego termino");
		}
	} // paintComponent

	@Override
	public void actionPerformed(ActionEvent e) {
		this.juego.actualizar();
		repaint(); // reejecuta el codigo
	} // actionPerformed

	@Override
	public void keyPressed(KeyEvent e) {
	} 

	@Override
	public void keyTyped(KeyEvent e) {
		switch (e.getKeyChar()) { // toma la tecla que se pulsa
		case 'w':
			this.juego.validarDireccion('w');
			break;
		case 's':
			this.juego.validarDireccion('s');
			break;
		case 'd':
			this.juego.validarDireccion('d');
			break;
		case 'a':
			this.juego.validarDireccion('a');
			break;
		default:
			break;
		}
	} // para cambiar de direccion a la serpiente

	@Override
	public void keyReleased(KeyEvent e) {
	}

} // fin clase
