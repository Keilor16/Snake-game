package Domain;

import java.awt.Color;
import java.awt.Graphics;

public class Serpiente {
	
	private int posX;
	private int posY;
	private int size; // size segmento de la serpiente
	
	public Serpiente(int posX, int posY, int size) {
		this.posX = posX;
		this.posY = posY;
		this.size = size;
	} // Constructor

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
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void dibujar(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(this.posX, this.posY, this.size, this.size);
	} // dibujar
	
} // fin clase Serpiente
