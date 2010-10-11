package app;

/**
 * Clase que contiene la informacion necesaria para representar un punto.
 *
 * @author Jaime Abraham Corrales Gonzalez
 * @author Jaime Gonzalez Valdes
 * @author Miguel Monterrey Varela
 * @author Eduardo Perez Mederos
 *
 * @version 0.1
 */
public class Point
{
	/**
	 * X - Coordenada x del punto.
	 */
	private int x;
	
	/**
	 * Y - Coordenada y del punto.
	 */
	private int y;
	
	/**
	 * Constructor por defecto de un punto (origen de coordenadas).
	 */
	public Point ()
	{
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Constructor dada las coordenadas x e y de un punto.
	 * 
	 * @param x - Coordenada x.
	 * @param y - Coordenada y.
	 */
	public Point (int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Metodo que obtiene la coordenada x de un punto.
	 * 
	 * @return Coordenada x.
	 */
	public int getX ()
	{
		return this.x;
	}
	
	/**
	 * Metodo que obtiene la coordenada y de un punto.
	 * 
	 * @return Coordenada y.
	 */
	public int getY ()
	{
		return this.y;
	}
	
	/**
	 * Metodo que asigna las nuevas coordenadas x e y de un punto.
	 * 
	 * @param x - Nueva coordenada x.
	 * @param y - Nueva coordenada y.
	 */
	public void setPoint (int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Metodo que asigna la nueva coordenada x de un punto.
	 *
	 * @param x - Nueva coordenada x.
	 */
	public void setX (int x)
	{
		this.x = x;
	}
	
	/**
	 * Metodo que asigna la nueva coordenada y de un punto.
	 * 
	 * @param y - Nueva coordenada y.
	 */
	public void setY (int y)
	{
		this.y = y;
	}
	
	/**
	 * Metodo que resume la informacion de un punto en una cadena de caracteres.
	 * 
	 * @return - Cadena de caracteres con informacion sobre el punto.
	 */
	public String toString ()
	{
		return new String ("(" + this.x + ", " + this.y + ")");
	}
}
