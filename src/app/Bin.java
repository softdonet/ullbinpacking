package app;

/**
 * Clase que contiene la informacion necesaria para representar una caja.
 * De momento, es basicamente un clon de la clase Rectangle. En el futuro, la
 * clase Bin tendra funcionalidad exclusiva.
 * 
 * @author Jaime Abraham Corrales Gonzalez
 * @author Jaime Gonzalez Valdes
 * @author Miguel Monterrey Varela
 * @author Eduardo Perez Mederos
 *
 * @version 0.1
 */
public class Bin
{
	/**
	 * Area - Area de la caja.
	 */
	private int area;
	
	/**
	 * Height - Alto de la caja.
	 */
	private int height;
	
	/**
	 * Width - Ancho de la caja.
	 */
	private int width;
	
	/**
	 * Constructor dado el ancho y el alto de una caja.
	 * 
	 * @param width - Ancho de la caja (en unidades).
	 * @param height - Alto de la caja (en unidades).
	 */
	public Bin (int width, int height)
	{
		this.height = height;
		this.width = width;
		this.area = height * width;
	}
	
	/**
	 * Metodo que obtiene el area de una caja.
	 * 
	 * @return Area de la caja.
	 */
	public int getArea ()
	{
		return area;
	}

	/**
	 * Metodo que obtiene el alto de una caja.
	 * 
	 * @return Alto de la caja.
	 */
	public int getHeight ()
	{
		return height;
	}

	/**
	 * Metodo que obtiene el ancho de una caja.
	 * 
	 * @return Ancho de la caja.
	 */
	public int getWidth ()
	{
		return width;
	}

	/**
	 * Metodo que asigna la nueva area de la caja.
	 * 
	 * @param area - Nueva area de la caja.
	 */
	public void setArea (int area)
	{
		this.area = area;
	}

	/**
	 * Metodo que asigna el nuevo alto de la caja (y su area).
	 * 
	 * @param height - Nuevo alto de la caja.
	 */
	public void setHeight (int height)
	{
		this.height = height;
		this.area = height * this.width;
	}

	/**
	 * Metodo que asigna el nuevo alto de la caja (y su area).
	 * 
	 * @param width - Nuevo ancho de la caja.
	 */
	public void setWidth (int width)
	{
		this.width = width;
		this.area = this.height * width;
	}
	
	/**
	 * Metodo que resume la informacion de una caja en una cadena de caracteres.
	 * 
	 * @return - Cadena de caracteres con informacion sobre la caja.
	 */
	public String toString ()
	{
		return new String ("[" + this.width + " x " + this.height + "], Area = " + this.area);
	}
}
