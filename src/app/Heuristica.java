package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Clase con los datos necesarios para representar una solucion de Bin Packing.
 * 
 * @author Eduardo Perez Mederos
 * @author Miguel Monterrey Varela
 * @author Jaime Gonzalez Valdes
 * @author Oscar Mateos Lopez
 * 
 * @version 1.0
 * @since 1.0
 */
public class Heuristica 
{
	/**
	 * Criterios de parada
	 */
	public static final int NOMEJORA = 0;
	public static final int NVECES = 1;
	
	
	/**
	 * Busquedas por entornos
	 */
	public static final int BAP = 0;              // Busqueda aleatoria pura
	public static final int BRA = 1;			  // Busqueda por recorrido al azar
	public static final int BL = 2;				  // Busqueda local
	
	
	/**
	 * GRASP
	 */
	public static final int GRASP = 3;
	
	
	/**
	 * Metodos multiarranque
	 */
	public static final int BAM = 4;              // Busqueda con arranque multiple
	
	
	private Problema Problema;
	private Solucion MejorSolucion;
	
	
	/**
	 * Constructor dado el tipo de generacion de solucion inicial y el numero de
	 * rectangulos.
	 * 
	 * @param permType - Tipo de heuristica.
	 * @param size 
	 */
	public Heuristica(int tipoHeuristica, Problema p) {
		Problema = p;
		MejorSolucion = new Solucion();
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int veces = 0;
		int criterio = 0;
		
		switch (tipoHeuristica) {
			case BAP:
				System.out.println("Busqueda aleatoria pura");
				System.out.print("Introduzca el numero de ejecuciones: ");
				try {
					veces = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Introduzca el criterio a utilizar para la parada");
				System.out.println("0 - NOMEJORA: No mejora la solucion optima en n-ejecuciones");
				System.out.println("1 - NVECES: Ejecutar N-veces");
				System.out.print("Criterio: ");
				
				try {
					criterio = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.MejorSolucion = BAP(criterio, veces);
				break;
				
			case BRA:
				System.out.println("Busqueda por recorrido al azar");
				System.out.print("Introduzca el numero de ejecuciones: ");
				try {
					veces = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Introduzca el criterio a utilizar para la parada");
				System.out.println("0 - NOMEJORA: No mejora la solucion optima en n-ejecuciones");
				System.out.println("1 - NVECES: Ejecutar N-veces");
				System.out.print("Criterio: ");
				try {
					criterio = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.MejorSolucion = BRA(criterio, veces);
				break;
				
			case BL:
				System.out.println("Busqueda local");
				
				Solucion solActual = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
						Problema.getAltoCaja(), Problema.getAnchoCaja(), Solucion.FINITE);
				
				this.MejorSolucion = BL(solActual);
				break;
				
			case GRASP:
				System.out.println("GRASP");
				
				Solucion solInicial = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
						Problema.getAltoCaja(), Problema.getAnchoCaja(),1);
				
				System.out.print("Introduzca el numero de ejecuciones: ");
				try {
					veces = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.MejorSolucion = GRASP(solInicial, veces);
				break;
				
			case BAM:
				System.out.println("GRASP");
								
				System.out.print("Introduzca el numero de ejecuciones: ");
				try {
					veces = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.MejorSolucion = BAM(veces);
				break;
		}
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema
	 * 
	 * @return Solucion - Solucion del problema
	 */
	public Solucion BAP(int criterio, int veces) {
		int clembuterol = veces;
		
		Solucion solInicial = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
				Problema.getAltoCaja(), Problema.getAnchoCaja(), Solucion.FINITE);

		Solucion mejorSolucion = solInicial;
		
		do {
			Solucion solActual = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
					Problema.getAltoCaja(), Problema.getAnchoCaja(),0);
			
			if (solActual.compareTo(mejorSolucion) < 0) {
				mejorSolucion = solActual;
				
				if (criterio == NOMEJORA) {
					clembuterol = veces;
				}
			}
			
			clembuterol--;
			
		} while (clembuterol > 0);
		
		return mejorSolucion;
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema
	 * 
	 * @return Solucion - Solucion del problema
	 */
	public Solucion BRA(int criterio, int veces) {
		int clembuterol = veces;
		
		Solucion solInicial = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
				Problema.getAltoCaja(), Problema.getAnchoCaja(), Solucion.FINITE);
		
		Solucion solActual = solInicial;
		Solucion mejorSolucion = solActual;
		
		do {			
			solActual = GeneraSVecina(solActual);
			
			if (solActual.compareTo(mejorSolucion) < 0) {
				mejorSolucion = solActual;
				
				if (criterio == NOMEJORA) {
					clembuterol = veces;
				}
			}
			
			clembuterol--;
		} while (clembuterol > 0);
		
		return mejorSolucion;
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema
	 * 
	 * @return Solucion - Solucion del problema
	 */
	public Solucion BL(Solucion solActual) {
		Solucion mejorSolucion = solActual;
		boolean mejor = false;
		
		do {	
			mejor = false;
			solActual = GeneraSVecina(solActual);
			
			if (solActual.compareTo(mejorSolucion) < 0) {
				mejorSolucion = solActual;
				mejor = true;
			}
		} while (mejor);
		
		return mejorSolucion;
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema
	 * 
	 * @return Solucion - Solucion del problema
	 */
	public Solucion GRASP(Solucion solInicial, int veces) {
		int clembuterol = veces;
		
		Solucion mejorSolucion = solInicial;
		
		do {
			Solucion solActual = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
					Problema.getAltoCaja(), Problema.getAnchoCaja(),1);
			
			if (solActual.compareTo(mejorSolucion) < 0) {
				mejorSolucion = solActual;
				clembuterol = veces;
			}
			
			clembuterol--;
			
		} while (clembuterol > 0);
		
		return mejorSolucion;
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema
	 * 
	 * @return Solucion - Solucion del problema
	 */	
	public Solucion BAM(int veces) {
		Solucion solInicial = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
				Problema.getAltoCaja(), Problema.getAnchoCaja(),0);

		int clembuterol = veces;
		Solucion mejorSolucion = solInicial;
		
        do {
			solInicial = BL(solInicial);
			
			if (solInicial.compareTo(mejorSolucion) < 0) {
				mejorSolucion = solInicial;
				clembuterol = veces;
			}
			
			clembuterol--;

        } while (clembuterol > 0);
		return mejorSolucion;
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema
	 * 
	 * @return Solucion - Solucion del problema
	 */
	public Solucion GeneraSVecina(Solucion s) {
		int permutacion[] = s.getPermutacion();
		
		int i = 0;
		int j = 0;
				
		do {
			Random rnd = new Random(System.nanoTime());
					
			i = (int)(rnd.nextDouble() * permutacion.length);
			j = (int)(rnd.nextDouble() * permutacion.length);
					
			if (i != j)
				swap(permutacion, i, j);
		} while (i == j);
				
		return new Solucion(permutacion, Problema.getRectangulos(), Problema.getAltoCaja(),
				Problema.getAnchoCaja(), Solucion.FINITE);
	}
	
	
	/**
	 * Metodo que se encarga de intercambiar elementos para 
	 * 
	 * @param array - Array que contiene la permutacion.
	 * @param posOne - Posicion del primer elemento a intercambiar.
	 * @param posTwo - Posicion del segundo elemento a intercambiar.
	 */
	private void swap(int array[], int posOne, int posTwo) {
		int temp = array[posTwo];
		array[posTwo] = array[posOne];
		array[posOne] = temp;
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema
	 * 
	 * @return Solucion - Solucion del problema
	 */
	public Solucion getSolucion() {
		return MejorSolucion;
	}
	
	
	/**
	 * Metodo para resumir la informacion de una heuristica en una cadena de caracteres.
	 * 
	 * @return String - Mejor solucion de una heuristica
	 */
	public String toString() {
		return new String(MejorSolucion + "\n");
	}
}