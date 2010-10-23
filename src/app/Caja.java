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
	// Todas las cajas van a ser iguales y ya guardamos estos datos en problema
	// en principio en esta clase no hacen falta
	private int alto;
	private int ancho;
	private int area;
	private ArrayList<Punto> PuntosLibres;
	private ArrayList<Rectangulo> RecIn;
	
	public Caja(int alto, int ancho) {
		this.alto = alto;
		this.ancho = ancho;
		this.area = alto * ancho;
		PuntosLibres = new ArrayList<Punto>();
		RecIn = new ArrayList<Rectangulo>();
	}
	
	public void AddPuntoLibre(Punto p) {
		this.PuntosLibres.add(p);
	}
	
	public void BorrarPuntoLibre(Punto p) {
		this.PuntosLibres.remove(p);
	}
	
	public void OrdenarPuntosLibres() {
		Collections.sort(this.PuntosLibres);
	}
	
	public String toString() {
		String toRet = new String("Puntos libres: " + this.PuntosLibres.size() + "\n");
		
		for (int i = 0; i < this.PuntosLibres.size(); i++)
			toRet += this.PuntosLibres.get(i) + "\n";
		
		return toRet;
	}
}
