package app;

/**
 * Clase que contiene la informacion necesaria para representar un rectangulo.
 * 
 * @author Jaime Abraham Corrales Gonzalez
 * @author Jaime Gonzalez Valdes
 * @author Miguel Monterrey Varela
 * @author Eduardo Perez Mederos
 *
 * @version 0.1a
 */
public class Rectangle implements Comparable<Rectangle>
{
	/**
	 * Area - Area del rectangulo.
	 */
	private int area;
	
	/**
	 * Height - Alto del rectangulo.
	 */
	private int height;
	
	/**
	 * Width - Ancho del rectangulo.
	 */
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
	 * Metodo que compara dos rectangulos y determina cual es el mayor en funcion
	 * del area que ocupa.
	 * 
	 * @param r - Rectangulo a comparar.
	 * @return 1, 0 o -1 en funcion de si el area del rectangulo comparador es
	 * mayor, igual o menor que la del rectangulo comparado.
	 */
	public int compareTo (Rectangle r)
	{
		if (this.area > r.getArea ())
			return 1;
		else if (this.area < r.getArea ())
			return -1;
		else
			return 0;
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
