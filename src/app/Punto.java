package app;

/**
 * Clase con los datos necesarios para representar un punto (y, x).
 * 
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
	private int y;
	
	// corregir esta clase y ponerla asi (eje y, eje x)
	
	/**
	 * Constructor por defecto de la clase Punto.
	 * Crea un punto situado en el origen de coordenadas.
	 */
	public Punto() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Constructor de la clase Punto.
	 * Crea un punto dadas sus coordeandas x e y.
	 * 
	 * @param x - Coordenada x.
	 * @param y - Coordenada y.
	 */
	public Punto(int y, int x) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Metodo que asigna la coordenada x de un punto.
	 * 
	 * @param x - Nueva coordeanda x.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Metodo que obtiene la coordenada x de un punto.
	 * 
	 * @return Coordenada x del punto.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Metodo que asigna la coordenada y de un punto.
	 * 
	 * @param y - Nueva coordeanda y.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Metodo que obtiene la coordenada y de un punto.
	 * 
	 * @return Coordenada y del punto.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Metodo que compara dos puntos y determina cual es prioritario segun su
	 * coordenada y.
	 *  
	 * @param p - Punto con el que compararse.
	 * @return 1, 0 o -1 en funcion de si el comparador es mayor, igual o menor
	 * al comparado.
	 */
	public int compareTo(Punto p) {
		return ((Integer) y).compareTo((Integer) p.getY()); // Si tienen la misma y se toma la x mas pequena???
	}
	
	/**
	 * Metodo para resumir la informacion de un Punto en una cadena de caracteres.
	 */
	public String toString() {
		return new String("(" + this.y + "," + this.x + ")");
	}
}
