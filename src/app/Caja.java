package app;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase con los datos necesarios para representar un contenedor.
 * 
 * @author Eduardo Perez Mederos
 * @author Miguel Monterrey Varela
 * @author Jaime Gonzalez Valdes
 * @author Oscar Mateos Lopez
 * 
 * @version 1.0
 * @since 0.1
 */
public class Caja 
{
	private int alto;
	private int ancho;
	private int area;
	private ArrayList<PuntoCota> PC;
	private ArrayList<Rectangulo> RecIn;
	
	/**
	 * Constructor para la clase Caja
	 * 
	 * @param alto - Alto de la caja
	 * @param ancho - Ancho de la caja
	 */
	public Caja(int alto, int ancho) {
		this.alto = alto;
		this.ancho = ancho;
		this.area = alto * ancho;
		
		RecIn = new ArrayList<Rectangulo>();
		PC = new ArrayList<PuntoCota>();
		PC.add(new PuntoCota(new Punto(), alto));
	}

	/**
	 * Metodo que devuelve el ancho de la caja
	 * 
	 * @return ancho - Ancho de la caja
	 */
	public int getAncho() {
		return ancho;
	}
	
	/**
	 * Metodo que devuelve el area de la caja
	 * 
	 * @return area - Area de la caja
	 */
	public int getArea() {
		return area;
	}

	public int getAreaRestante() {
		int aux = 0; 
		for (int i = 0; i < this.RecIn.size(); i++) {
			Rectangulo r = RecIn.get(i);
			aux = aux + r.getArea();
		}
		return aux;
	}

	
	/**
	 * Metodo que comprueba si cabe un rectangulo dentro de la caja
	 * 
	 * @param r - Rectangulo a introducir en la caja
	 * @return boolean
	 */
	public boolean CabeRectangulo(Rectangulo r) {
		boolean toRet = false;
		
		for (int i = 0; i < PC.size(); i++) {
			Punto p = PC.get(i).getPunto();
			
			if ((r.getAlto() <= PC.get(i).getCota()) && (r.getAncho() <= this.ancho - p.getX())) {
				toRet = true;

				r.setPos(p);
				
				break;
			}
		}
		
		return toRet;
	}
	
	/**
	 * Añade los puntos libres que se generan al añadir un rectangulo 
	 * en el contenedor
	 * 
	 * @param puntoRec - Punto donde se va a colocar el rectangulo
	 * @param cota - Altura maxima del punto
	 * @param altoRec - Alto del rectangulo
	 * @param anchoRec - Ancho del rectangulo
	 */
	public void AddPuntosLibres(Punto puntoRec, int cota, int altoRec, int anchoRec) {	
		if (((puntoRec.getY() + altoRec) < this.alto) && (cota > altoRec)) {
			if (puntoRec.getX() == 0) {
				PC.add(new PuntoCota(new Punto(puntoRec.getX(), puntoRec.getY() + altoRec), this.alto - (puntoRec.getY() + altoRec)));
			}
			else {
				Punto menor = new Punto(0, alto);
				Punto igual = new Punto(0, alto);
				int igualCota = alto;
				boolean cambio = false;
				
				for (int i = 0; i < PC.size(); i++) {
					if ((PC.get(i).getPunto().getY() == puntoRec.getY() + altoRec) && (PC.get(i).getPunto().getX() < puntoRec.getX())) {
						igual = PC.get(i).getPunto();
						igualCota = PC.get(i).getCota();
					}
					
					if ((PC.get(i).getPunto().getY() > puntoRec.getY()) && (PC.get(i).getPunto().getY() != puntoRec.getY() + altoRec)) {
						if (PC.get(i).getPunto().getY() < menor.getY()) {
							menor = PC.get(i).getPunto();
							cambio = true;
						}
					}
				}
				
				Punto nuevo = new Punto(puntoRec.getX(), puntoRec.getY() + altoRec);
				
				if ((igual.getY() == nuevo.getY()) && (igual.getX() < nuevo.getX()) && (igualCota == menor.getY() - (puntoRec.getY() + altoRec))) {
					System.out.println("punto igual");
				}
				else {
					if (cambio) 
						PC.add(new PuntoCota(nuevo, menor.getY() - (puntoRec.getY() + altoRec)));
					else 
						PC.add(new PuntoCota(nuevo, cota - altoRec));
				}
			}
		}
		
		if ((puntoRec.getX() + anchoRec) < this.ancho) {
			PC.add(new PuntoCota(new Punto(puntoRec.getX() + anchoRec, puntoRec.getY()), altoRec));
		}
		
		Collections.sort(PC);
	}
	
	
	/**
	 * Introduce un rectangulo en el contenedor y genera los puntos libres
	 * 
	 * @param r - Nuevo rectangulo a añadir
	 */
	public void NuevoRectangulo(Rectangulo r) {
		RecIn.add(r);
		
		int pos = 0;
		int cota = alto;
		
		for (int i = 0; i < PC.size(); i++) {
			if (PC.get(i).getPunto() == r.getPos()) {
				pos = i;
				cota = PC.get(i).getCota();
				
				break;
			}
		}
		
		PC.remove(PC.get(pos)); 
		
		AddPuntosLibres(r.getPos(), cota, r.getAlto(), r.getAncho());
	} 
	
	/**
	 * Salida del contenido de la caja 
	 * 
	 * @return String - Cadena de caracteres que muestra cada caja y los rectangulos introducidos en cada una
	 */
	public String toString() {
		String toRet = new String();
		
		int matriz[][] = new int[alto][ancho];
		
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				matriz[i][j] = 64;
			}
		}
				
		toRet += "Rectangulos:" + "\n";
		for (int i = 0; i < this.RecIn.size(); i++) {
			toRet += (char)(i + 65) + " -> " + this.RecIn.get(i) + "  en el punto -> " + this.RecIn.get(i).getPos() +  "\n";
		}
			
		toRet += "\n";
		
		Rectangulo r = new Rectangulo();
		
		for (int i = 0; i < this.RecIn.size(); i++) {
			r = RecIn.get(i);
			Punto p = r.getPos();
			
			for (int fila = this.alto - 1 - p.getY(); fila > (this.alto - 1 - p.getY()) - r.getAlto(); fila--) {
				for (int col = p.getX(); col < r.getAncho() + p.getX(); col++) {
					matriz[fila][col] += i + 1;
				}
			}
		} 
		
		
		for (int j = 0; j < this.ancho + 2; j++) {
			toRet += "#";
		}
		
		toRet += "\n";
		
		for (int i = 0; i < this.alto; i++) {
			toRet += "#";	
			
			for (int j = 0; j < this.ancho; j++) {
				toRet += (char)(matriz[i][j]);
			}
			
			toRet += "#\n";
		}	
		
		for (int j = 0; j < this.ancho + 2; j++) {
			toRet += "#";
		}
		
		toRet += "\n\n";
		
		int ocupa = (getAreaRestante() * 100) / area;
			
		toRet += "La caja tiene ocupado un ";
		toRet += ocupa;
		toRet += "% de su area total";
		toRet += "\n";
		
		return toRet;
	} 
}
