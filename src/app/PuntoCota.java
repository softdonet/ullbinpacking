package app;

/**
 * Clase con los datos necesarios para representar la altura maxima de un punto
 * 
 * @author Eduardo Perez Mederos
 * @author Miguel Monterrey Varela
 * @author Jaime Gonzalez Valdes
 * @author Oscar Mateos Lopez
 * 
 * @version 1.0
 * @since 0.2
 */
public class PuntoCota implements Comparable<PuntoCota>
{

	private Punto punto;
	private int cota;

	/**
	 * Constructor de la clase PuntoCota
	 * 
	 * @param p - Punto
	 * @param cota - Altura maxima que se puede alcanzar desde un punto
	 */
	public PuntoCota (Punto p, int cota) {
		punto = p;
		this.cota = cota;
	}
	
	/**
	 * Metodo que devuelve el punto
	 * 
	 * @return punto
	 */
	public Punto getPunto() {
		return punto;
	}
	
	/**
	 * Metodo que devuelve la cota
	 * 
	 * @return cota
	 */
	public int getCota() {
		return cota;
	}
	
	/**
	 * Metodo que compara dos puntos y determina cual es prioritario segun su
	 * coordenada y.
	 *  
	 * @param pc - PuntoCota con el que compararse.
	 * @return 1, 0 o -1 en funcion de si el comparador es mayor, igual o menor
	 * al comparado.
	 */
	public int compareTo(PuntoCota pc) {
		return punto.compareTo(pc.getPunto());
	}
}

