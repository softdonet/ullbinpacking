package app;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase con los datos necesarios para representar un contenedor.
 * 
 * @author Jaime Abraham Corrales Gonzalez
 * @author Eduardo Perez Mederos
 * @author Miguel Monterrey Varela
 * @author Jaime Gonzalez Valdes
 * @author Oscar Mateos Lopez
 * 
 * @version 0.1
 */

public class Caja 
{
	private ArrayList<Punto> PuntosLibres;
	private ArrayList<Rectangulo> RecIn;
	
	/**
	 * Constructor para la clase Caja
	 */
	public Caja() {
		PuntosLibres = new ArrayList<Punto>();
		RecIn = new ArrayList<Rectangulo>();
		
		Punto p = new Punto(0,0);
		
		PuntosLibres.add(p);
	}
	
	/**
	 * Añade un punto a la lista de puntos libres de la Caja
	 */
	public void AddPuntoLibre(Punto p) {
		this.PuntosLibres.add(p);
	}
	
	/**
	 * Borra un punto ya ocupado
	 */
	// Parece que no va a hacer falta
	public void BorrarPuntoLibre(Punto p) {
		this.PuntosLibres.remove(p);
	}
	
	/**
	 * Ordena la lista de puntos libres
	 */
	public void OrdenarPuntosLibres() {
		Collections.sort(this.PuntosLibres);
	}
	
	/**
	 * Introduce un rectangulo en el contenedor
	 */
	public void NuevoRectangulo(Rectangulo r) {
		RecIn.add(r);
		
		PuntosLibres.remove(r.getPos());
	}
	
	/**
	 * Salida del contenido de la caja 
	 */
	public String toString() {
		String toRet = new String("Puntos libres: " + this.PuntosLibres.size() + "\n");
		
		for (int i = 0; i < this.PuntosLibres.size(); i++)
			toRet += this.PuntosLibres.get(i) + "\n";
		
		toRet += "\n";
		
		for (int i = 0; i < this.RecIn.size(); i++)
			toRet += this.RecIn.get(i) + "\n";
		
		return toRet;
	}
}
