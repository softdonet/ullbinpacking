package app;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.HashMap;
//import java.util.TreeSet;

/**
 * Clase con los datos necesarios para representar un contenedor.
 * 
 * @author Eduardo Perez Mederos
 * @author Miguel Monterrey Varela
 * @author Jaime Gonzalez Valdes
 * @author Oscar Mateos Lopez
 * 
 * @version 0.2
 * @since 0.1
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
	 * Cota - Altura maxima  -------------
	 *                       |           |
	 *                       |           |
	 *                       |           |
	 *      P: (0,2) C: 3 -> -------------
	 *                       |     |     |
	 *                       |     | <-  | P: (2,0) C: 2
	 *                       -------------
	 * 
	 * @param alto - Alto de la caja
	 * @param ancho - Ancho de la caja
	 */
	public Caja(int alto, int ancho) {
		this.alto = alto;
		this.ancho = ancho;
		this.area = alto * ancho;
		
		RecIn = new ArrayList<Rectangulo>();
		
		PuntoCota = new HashMap<Punto, Integer>();
		PuntoCota.put(new Punto(), alto);
	}

	/**
	 * Metodo que devuelve el area de la caja
	 * 
	 * @return area - Area de la caja
	 */
	public int getArea() {
		return area;
	}

	/**
	 * Añade los puntos libres que se generan al añadir un rectangulo 
	 * en el contenedor
	 * 
	 * @param puntoRec - Punto donde se va a colocar el rectangulo
	 * @param altoRec - Alto del rectangulo
	 * @param anchoRec - Ancho del rectangulo
	 */
	public void AddPuntosLibres(Punto puntoRec, int altoRec, int anchoRec) {
		if ((puntoRec.getY() + altoRec) < this.alto) {
			PuntoCota.put(new Punto(puntoRec.getX(), puntoRec.getY() + altoRec), this.alto - (puntoRec.getY() + altoRec));
		}
		
		if ((puntoRec.getX() + anchoRec) < this.ancho) {
			PuntoCota.put(new Punto(puntoRec.getX() + anchoRec, puntoRec.getY()), altoRec);
		}
		
		//TreeSet<Punto> auxPC = new TreeSet<Punto>(PuntoCota.keySet());
	}
	
	/**
	 * Ordena la lista de puntos libres
	 */
	/*public void OrdenarPuntosLibres() {
		Collections.sort(this.PuntosLibres);
	}*/
	
	/**
	 * Introduce un rectangulo en el contenedor y genera dos puntos libres
	 * 
	 * @param r - Nuevo rectangulo a añadir
	 */
	public void NuevoRectangulo(Rectangulo r) {
		RecIn.add(r);
		
		Punto p = new Punto();
		
		p = r.getPos();
		
		AddPuntosLibres(p, r.getAlto(), r.getAncho());
		
		PuntoCota.remove(p);
	} 
	
	/*public boolean NoCabe() {
		boolean toRet = true;
		
		return toRet;
	}*/
	
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
