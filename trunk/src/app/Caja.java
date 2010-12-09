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
	private int Nu;
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
		this.Nu = 0;
		
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
	 * Metodo que devuelve el valor Nu. Utilizado en el metodo Grasp
	 * 
	 * @return ancho - Ancho de la caja
	 */
	public int getNu() {
		return Nu;
	}
	
	/**
	 * Metodo que modifica el valor Nu
	 * 
	 * @return ancho - Ancho de la caja
	 */
	public void setNu(int i){
		Nu = i;
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
	 * Metodo que devuelve un array de rectangulos, en el cual almacenamos
	 * las 3 mejores posibilidades de inserción para un rectangulo r que pasamos
	 * por parametro.
	 * 
	 * @param r - Rectangulo a introducir en la caja
	 * @return Rectangulo[]
	 */
	public Rectangulo[] CabeAlgunRectangulo(Rectangulo r) {
		Rectangulo[] aux;
		aux = new Rectangulo[3];
		int x = 0;
		for (int i = 0; i < PC.size(); i++) {
			Punto p = PC.get(i).getPunto();
			if ((r.getAlto() <= PC.get(i).getCota()) && (r.getAncho() <= this.ancho - p.getX())) {
				r.setPos(p);
				if (x < 3) {
					aux[x] = r;
					x++;
				} else {
					int are = PC.get(i).getCota() * this.ancho - p.getX();
					int arrec = r.getAlto() * r.getAncho();
					int val = ValorMax(aux);
					if ((are - arrec) < val) {
						int x1 = PosicionInsertar(aux);
						aux[x1] = r;
					}
				}
			}
		}
		setNu(x);
		return aux;
	}
	
	/**
	 * Metodo que devuelve dentro de un array de rectangulos,
	 * la posicion del rectangulo con mayor desperdicio de espacio.
	 * Candidato a salir del array.
	 * 
	 * @param Rectangulo[] R - Array de candidatos a insertar
	 * @return int
	 */
	public int PosicionInsertar(Rectangulo[] R) {
		int toRet = 0;
		int aux = 0;
		for (int i = 0; i < R.length; i++) {
			Rectangulo r = R[i];
			int cota = 0, pos = 0;
			for (int j = 0; j < PC.size(); j++){
				if (PC.get(j).getPunto() == r.getPos()) {
					cota = PC.get(j).getCota();
					pos = j;
					break;
				}
			}
			Punto P = PC.get(pos).getPunto();
			int are = cota * this.ancho - P.getX();
			int arrec = r.getAlto() * r.getAncho();
			if ((are - arrec) > aux) {
				aux = are - arrec;
				toRet = i;
			}
		}
		return toRet;
	}
	
	/**
	 * Metodo que devuelve dentro de un array de rectangulos,
	 * el valor del rectangulo con mayor desperdicio de espacio.
	 * Candidato a salir del array.
	 * 
	 * @param Rectangulo[] R - Array de candidatos a insertar
	 * @return int
	 */
	public int ValorMax(Rectangulo[] R) {
		int aux = 0;
		for (int i = 0; i < R.length; i++) {
			Rectangulo r = R[i];
			int cota = 0, pos = 0;
			for (int j = 0; j < PC.size(); j++){
				if (PC.get(j).getPunto() == r.getPos()) {
					cota = PC.get(j).getCota();
					pos = j;
					break;
				}
			}
			Punto P = PC.get(pos).getPunto();
			int are = cota * this.ancho - P.getX();
			int arrec = r.getAlto() * r.getAncho();
			if ((are - arrec) > aux) {
				aux = are - arrec;
			}
		}
		return aux;
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
				PC.add(new PuntoCota(new Punto(puntoRec.getX(), puntoRec.getY() + altoRec),
						this.alto - (puntoRec.getY() + altoRec)));
			}
			else {
				Punto menor = new Punto(0, alto);
				Punto igual = new Punto(0, alto);
				int igualCota = alto;
				boolean cambio = false;
				
				for (int i = 0; i < PC.size(); i++) {
					if ((PC.get(i).getPunto().getY() == puntoRec.getY() + altoRec) && 
							(PC.get(i).getPunto().getX() < puntoRec.getX())) {
						igual = PC.get(i).getPunto();
						igualCota = PC.get(i).getCota();
					}
					
					if ((PC.get(i).getPunto().getY() > puntoRec.getY()) && (PC.get(i).getPunto().getY() != puntoRec.getY() + altoRec)
					    && (PC.get(i).getPunto().getY() <= cota + puntoRec.getY())) {
						if (PC.get(i).getPunto().getY() < menor.getY()) {
							menor = PC.get(i).getPunto();
							cambio = true;
						}
					}
				}
				
				Punto nuevo = new Punto(puntoRec.getX(), puntoRec.getY() + altoRec);
				
				if ((igual.getY() == nuevo.getY()) && (igual.getX() < nuevo.getX()) && 
						(igualCota == menor.getY() - (puntoRec.getY() + altoRec))) {
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
			toRet += (char)(i + 65) + " -> " + this.RecIn.get(i) + "  en el punto -> " + 
			this.RecIn.get(i).getPos() +  "\n";
		}
			
		toRet += "\n";
		
		Rectangulo r = new Rectangulo();
		
		// Hay fallos aun
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
