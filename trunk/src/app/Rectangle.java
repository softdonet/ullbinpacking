package app;

/**
 * Clase que contiene la informacion necesaria para representar un rectangulo.
 * 
 * @author Jaime Abraham Corrales Gonzalez
 * @author Anadid vuestros nombres
 *
 * @version 0.1
 */
public class Rectangle
{
	private int area;
	private int height;
	private int width;
	
	/**
	 * Constructor dado el ancho y el alto de un rectangulo.
	 * 
	 * @param width - Ancho de un rectangulo (en unidades).
	 * @param height - Alto de un rectangulo (en unidades).
	 */
	public Rectangle (int width, int height)
	{
		this.height = height;
		this.width = width;
		this.area = height * width;
	}
	
	/**
	 * Metodo que obtiene el area de un rectangulo.
	 * 
	 * @return Area de un rectangulo.
	 */
	public int getArea ()
	{
		return area;
	}

	/**
	 * Metodo que obtiene el alto de un rectangulo.
	 * 
	 * @return Alto de un rectangulo.
	 */
	public int getHeight ()
	{
		return height;
	}

	/**
	 * Metodo que obtiene el ancho de un rectangulo.
	 * 
	 * @return Ancho de un rectangulo.
	 */
	public int getWidth ()
	{
		return width;
	}

	/**
	 * Metodo que asigna la nueva area de un rectangulo.
	 * 
	 * @param area - Nueva area de un rectangulo.
	 */
	public void setArea (int area)
	{
		this.area = area;
	}

	/**
	 * Metodo que asigna el nuevo alto de un rectangulo (y su area).
	 * 
	 * @param height - Nuevo alto de un rectangulo.
	 */
	public void setHeight (int height)
	{
		this.height = height;
		this.area = height * this.width;
	}

	/**
	 * Metodo que asigna el nuevo alto de un rectangulo (y su area).
	 * 
	 * @param width - Nuevo ancho de un rectangulo.
	 */
	public void setWidth (int width)
	{
		this.width = width;
		this.area = this.height * width;
	}
	
	/**
	 * Metodo que resume la informacion de un rectangulo en una cadena de
	 * caracteres.
	 * 
	 * @return - Cadena de caracteres con informacion sobre el rectangulo.
	 */
	public String toString ()
	{
		return new String ("[" + this.width + " x " + this.height + "], Area = " + this.area);
	}
}
