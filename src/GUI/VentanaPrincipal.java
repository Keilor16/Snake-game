package GUI;

import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame{
	
	public VentanaPrincipal() {
		this.setTitle("Snake Game");
		this.add(new PanelJuego());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	} // constructor

} // fin clase
