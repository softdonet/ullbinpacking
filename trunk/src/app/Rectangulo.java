package app;

/**
 * Clase con los datos necesarios para representar un rectangulo.
 * 
 * @author Jaime Abraham Corrales Gonzalez
 * @author Eduardo Perez Mederos
 * @author Miguel Monterrey Varela
 * @author Jaime Gonzalez Valdes
 * @author Oscar Mateos Lopez
 * 
 * @version 0.1
 */
public class Rectangulo
{
	private int alto;
	private int ancho;
	private int area;	
	
	public Rectangulo (int alto, int ancho)
	{
		this.alto = alto;
		this.ancho = ancho;
		this.area = alto * ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}
	
	public String toString ()
	{
		return new String ("[" + alto + ", " + ancho + "], Area = " + area);
	}
}
