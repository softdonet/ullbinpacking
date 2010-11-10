package app;

/**
 * Clase con los datos necesarios para representar un rectangulo.
 * 
 * @author Eduardo Perez Mederos
 * @author Miguel Monterrey Varela
 * @author Jaime Gonzalez Valdes
 * @author Oscar Mateos Lopez
 * 
 * @version 0.1
 */
public class Rectangulo implements Comparable<Rectangulo>
{
	private int alto;
	private int ancho;
	private int area;	
	private Punto pos;

	/**
	 * Constructor de la clase Rectangulo.
	 * Crea un rectangulo dado su alto y su ancho.
	 */
	public Rectangulo ()
	{
		this.alto = alto;
		this.ancho = ancho;
	}
	
	public Rectangulo(int alto, int ancho) {
		this.alto = alto;
		this.ancho = ancho;
		this.area = alto * ancho;
		
		this.pos = new Punto();
	}

	/**
	 * Metodo que compara dos rectangulos y determina cual es el mayor en funcion
	 * de su altura.
	 * 
	 * @param r - Rectangulo a comparar.
	 * @return 1, 0 o -1 en funcion de si el area del rectangulo comparador es
	 * mayor, igual o menor que el area del rectangulo comparado.
	 */
	public int compareTo(Rectangulo r)
	{
		return ((Integer)alto).compareTo(r.getAlto());
	}

	/**
	 * Metodo para obtener el alto de un rectangulo.
	 * 
	 * @return Alto del rectangulo.
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * Metodo para asignar el alto a un rectangulo.
	 * 
	 * @param alto - Nuevo alto del rectangulo.
	 */
	public void setAlto(int alto) {
		this.alto = alto;
	}

	/**
	 * Metodo para obtener el ancho de un rectangulo.
	 * 
	 * @return Ancho del rectangulo.
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Metodo para asignar el ancho a un rectangulo.
	 * 
	 * @param ancho - Nuevo ancho del rectangulo.
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	/**
	 * Metodo para obtener el area de un rectangulo.
	 * 
	 * @return Area del rectangulo.
	 */
	public int getArea() {
		return area;
	}
	
	/**
	 * Metodo para obtener la posicion de un rectangulo.
	 * 
	 * @return Posicion del rectangulo.
	 */
	public Punto getPos() {
		return pos;
	}

	/**
	 * Metodo para asignar la posicion a un rectangulo.
	 * 
	 * @param pos - Nueva posicion del rectangulo.
	 */
	public void setPos(Punto pos) {
		this.pos = pos;
	}
	
	/**
	 * Metodo para resumir la informacion de un rectangulo en una cadena de caracteres.
	 */
	public String toString ()
	{
		return new String ("[" + alto + ", " + ancho + "], Area = " + area);
	}
}
