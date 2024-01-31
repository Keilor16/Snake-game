package Domain;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Manzana {
	
	private int posX;
	private int posY;
	private int cantPosVentana; // cantidad de lugares en los que puede aparecer la manzana, vertical u horizontal
	private int size; // size de la manzana
	private int puntaje;
	private Random random = new Random();
	
	public Manzana(int size, int cantPosVentana) {
		this.puntaje = 0;
		this.size = size;
		this.cantPosVentana = cantPosVentana;
	} // constructor

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

	public int getCantPosVentana() {
		return cantPosVentana;
	}

	public void setCantPosVentana(int cantPosVentana) {
		this.cantPosVentana = cantPosVentana;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public void agregarComida(ArrayList<Serpiente> serpientes) {
        boolean posicionValida = false;

        while (!posicionValida) {
            this.posX = random.nextInt(this.cantPosVentana) * this.size;
            this.posY = random.nextInt(this.cantPosVentana) * this.size;

            // Verificar si las nuevas posiciones coinciden con alguna posición de la serpiente
            boolean colision = false;
            for (Serpiente serpiente : serpientes) {
                if (serpiente.getPosX() == this.posX && serpiente.getPosY() == this.posY) {
                    colision = true;
                    break;
                }
            }

            // Si no hay colisión, la posición es válida
            if (!colision) {
                posicionValida = true;
            }
        }
    } // genera comida en posiciones random

	public void dibujar(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(this.posX, this.posY, this.size, this.size);
	} // dibujar

} // fin clase
