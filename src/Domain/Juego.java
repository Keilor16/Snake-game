package Domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import Utility.Size;

public class Juego {

	// Serpiente
	private ArrayList<Serpiente> serpientes;
	private char direccion;
	private int nuevaPosX;
	private int nuevaPosY;
	// Manzana
	private Manzana manzana;
	// Timer
	private Timer timer; // herramienta que hace de reloj para el delay (velocidad de movimiento)
	private int delay;
	private boolean activo;

	public Juego(ActionListener evento) {
		this.serpientes = new ArrayList<>();
		this.manzana = new Manzana(Size.SIZE_ELEMENTO, Size.SIZE_MAXIMO_ELEMENTOS);
		this.direccion = 'd';
		this.nuevaPosX = 0;
		this.nuevaPosY = 0;
		this.delay = 100;
		this.activo = true;
		this.timer = new Timer(this.delay, evento); // delay como la velocidad y this para referirse al actionListener
		this.timer.start();
	} // constructor

	public void actualizar() {
		if (this.isActivo()) {
			moverSerpiente();
			colisionarManzana();
			validarColision();
		} else {
			this.timer.stop(); // detiene el timer y termina el juego
		}
	} // valida si el juego termina, de lo contrario continua con el juego

	public void iniciarJuego() {
		this.serpientes.add(new Serpiente(0, 575, Size.SIZE_ELEMENTO));
		this.manzana.agregarComida(this.serpientes);
	} // inicializa el juego

	public void moverSerpiente() {
		for (int i = this.serpientes.size() - 1; i > 0; i--) {
			Serpiente segmento = serpientes.get(i);
			Serpiente segmentoAnterior = serpientes.get(i - 1);
			segmento.setPosX(segmentoAnterior.getPosX());
			segmento.setPosY(segmentoAnterior.getPosY());
		} // mueve el cuerpo de la serpiente

		Serpiente cabeza = this.serpientes.get(0);
		this.nuevaPosX = cabeza.getPosX();
		this.nuevaPosY = cabeza.getPosY();

		switch (this.direccion) {
		case 'w':
			this.nuevaPosY -= Size.SIZE_ELEMENTO; // arriba
			break;
		case 's':
			this.nuevaPosY += Size.SIZE_ELEMENTO; // abajo
			break;
		case 'd':
			this.nuevaPosX += Size.SIZE_ELEMENTO; // derecha
			break;
		case 'a':
			this.nuevaPosX -= Size.SIZE_ELEMENTO; // izquierda
			break;
		default:
			break;
		} // mueve la cabeza de la serpiente
	} // cambia la direccion de la serpiente

	public void validarDireccion(char direccion) {
		if ((this.direccion == 'w' && direccion != 's') || (this.direccion == 's' && direccion != 'w')
				|| (this.direccion == 'a' && direccion != 'd') || (this.direccion == 'd' && direccion != 'a')) {
			this.direccion = direccion;
		}
	} // Verifica que la nueva dirección no sea opuesta a la dirección actual

	public void colisionarManzana() {
		Serpiente cabeza = serpientes.get(0);
		if (cabeza.getPosX() == this.manzana.getPosX() && cabeza.getPosY() == this.manzana.getPosY()) {

			// posición del último segmento de la serpiente
			Serpiente ultimoSegmento = serpientes.get(serpientes.size() - 1);
			int nuevaPosX = ultimoSegmento.getPosX();
			int nuevaPosY = ultimoSegmento.getPosY();

			// agrega un nuevo segmento a la serpiente con la posición del último segmento
			serpientes.add(new Serpiente(nuevaPosX, nuevaPosY, Size.SIZE_ELEMENTO));
			this.manzana.setPuntaje(this.manzana.getPuntaje() + 1);
			this.manzana.agregarComida(this.serpientes);
		}
	} // revisa si la serpiente comio, si lo hace aumento y la manzana cambia de posicion

	public void validarColision() {
		Serpiente cabeza = serpientes.get(0);
		// valida colisión con las paredes
		if (cabeza.getPosX() < 0 || cabeza.getPosY() < 0 || cabeza.getPosX() > Size.SIZE_PANTALLA - Size.SIZE_ELEMENTO
				|| cabeza.getPosY() > Size.SIZE_PANTALLA - Size.SIZE_ELEMENTO) {
			this.activo = false;
		}

		// Verificar colisión consigo misma
		for (int i = 1; i < this.serpientes.size(); i++) {
			Serpiente segmento = serpientes.get(i);
			if (nuevaPosX == segmento.getPosX() && nuevaPosY == segmento.getPosY()) {
				this.activo = false; // La serpiente ha chocado consigo misma
				return;
			}
		}

		cabeza.setPosX(this.nuevaPosX);
		cabeza.setPosY(this.nuevaPosY);
	} // revisa si la serpiente colisiona con alguna parte de la pantalla

	public void dibujar(Graphics g) {
		this.manzana.dibujar(g);
		for (Serpiente segmento : serpientes) {
			segmento.dibujar(g);
		} // se dibuja la serpiente
		g.setColor(Color.WHITE);
		g.drawString("Puntaje:" + this.manzana.getPuntaje(), 30, 30);
	} // dibujar

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Manzana getManzana() {
		return manzana;
	}

	public void setManzana(Manzana manzana) {
		this.manzana = manzana;
	}

} // fin clase
