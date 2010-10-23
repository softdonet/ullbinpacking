package app;
import java.lang.Comparable;
///import java.util.Collections;


/**
 * Clase con los datos necesarios para representar un punto (x,y).
 * 
 * @author Jaime Abraham Corrales Gonzalez
 * @author Eduardo Perez Mederos
 * @author Miguel Monterrey Varela
 * @author Jaime Gonzalez Valdes
 * @author Oscar Mateos Lopez
 * 
 * @version 0.1
 */

public class Punto implements Comparable<Punto>
{
	private int x;
	private Integer y;
	
	public Punto(int x, int y) {
		this.setX(x);
		this.y = new Integer(y);
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}
	
	public int compareTo(Punto p) {
		return this.y.compareTo(p.getY());
	}
}
