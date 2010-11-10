package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Clase con los datos necesarios para representar un contenedor.
 * 
 * @author Eduardo Perez Mederos
 * @author Miguel Monterrey Varela
 * @author Jaime Gonzalez Valdes
 * @author Oscar Mateos Lopez
 * 
 * @version 0.1
 */

public class Caja 
{
	private int alto;
	private int ancho;
	private int area;
	private HashMap<Punto, Integer> PuntoCota;
	private ArrayList<Rectangulo> RecIn;
	
	/**
	 * Constructor para la clase Caja
	 */
	public Caja(int alto, int ancho) {
		this.alto = alto;
		this.ancho = ancho;
		this.area = alto * ancho;
		
		RecIn = new ArrayList<Rectangulo>();
		
		PuntoCota = new HashMap<Punto, Integer>();
		PuntoCota.put(new Punto(0,0), alto);
	}

	/**
	 * Añade los puntos libres que se generan al añadir un rectangulo 
	 * en el contenedor
	 */
	public void AddPuntosLibres(Punto puntoRec, int altoRec, int anchoRec) {
		if ((puntoRec.getY() + altoRec) < this.alto) {
			PuntoCota.put(new Punto(puntoRec.getX(), puntoRec.getY() + altoRec), this.alto);
		}
		
		if ((puntoRec.getX() + anchoRec) < this.ancho) {
			PuntoCota.put(new Punto(puntoRec.getX() + anchoRec, puntoRec.getY()), puntoRec.getY() + altoRec);
		}
	} // comprobar que esto esta bien
	
	
	/**
	 * Borra un punto ya ocupado
	 */
	// quizas no sea necesario
	/*public void BorrarPuntoLibre(Punto p) {
		this.PuntosLibres.remove(p);
	}*/
	
	/**
	 * Ordena la lista de puntos libres
	 */
	/*public void OrdenarPuntosLibres() {
		Collections.sort(this.PuntosLibres);
	}*/
	
	/**
	 * Introduce un rectangulo en el contenedor y genera dos puntos libres
	 */
	public void NuevoRectangulo(Rectangulo r) {
		RecIn.add(r);
		
		Punto p = new Punto();
		
		p = r.getPos();
		
		//PuntosLibres.remove(p);
		
		AddPuntosLibres(p, r.getAlto(), r.getAncho());
	} 
	
	public boolean NoCabe() {
		boolean toRet = true;
		
		return toRet;
	}
	
	/**
	 * Salida del contenido de la caja 
	 */
/*	public String toString() {
		String toRet = new String("Puntos libres: " + this.PuntosLibres.size() + "\n");
		
		for (int i = 0; i < this.PuntosLibres.size(); i++)
			toRet += this.PuntosLibres.get(i) + "\n";
		
		toRet += "\n";
		
		int matriz[][] = new int[ancho][alto];
		
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				matriz[i][j] = 0;
			}
		}
		
		
		for (int i = 0; i < this.RecIn.size(); i++)
			toRet += this.RecIn.get(i) + "\n";
		
		Rectangulo r = new Rectangulo();
		
		for (int i = 0; i < this.RecIn.size(); i++) {
			r = RecIn.get(i);
			Punto p = new Punto();
			p = r.getPos();
			
			for (int y = this.alto - 1 - p.getY(); y > (this.alto - 1 - p.getY()) - r.getAlto(); y--) {
				for (int x = p.getX(); x < r.getAncho() + p.getX(); x++) {
					matriz[x][y] = 1;
				}
			}
		}
			
		
		
		
		for (int j = 0; j < this.alto; j++) {
			for (int i = 0; i < this.ancho; i++) {
				toRet += matriz[i][j];
			}
			
			toRet += "\n";
		}
		
		
			
		
		return toRet;
	} */
}
