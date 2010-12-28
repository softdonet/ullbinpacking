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
	public static final int BAP = 1;              // Busqueda aleatoria pura
	public static final int BRA = 2;			  // Busqueda por recorrido al azar
	public static final int BL = 3;				  // Busqueda local
	
	
	/**
	 * GRASP
	 */
	public static final int GRASP = 4;
	
	
	/**
	 * Metodos multiarranque
	 */
	public static final int BAM = 5;              // Busqueda con arranque multiple
	
	
	/**
	 * Recocido simulado
	 */
	public static final int RS = 6;              // Recocido simulado
	
	
	/**
	 * Busqueda por entornos variables
	 */
	public static final int VND = 7;
	public static final int BVNS = 8;
	
	
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
				System.out.println("Busqueda con arranque multiple");
								
				System.out.print("Introduzca el numero de ejecuciones: ");
				try {
					veces = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.MejorSolucion = BAM(veces);
				break;
				
			case RS:
				System.out.println("Recocido simulado");
				/*				
				System.out.print("Introduzca el numero de ejecuciones: ");
				try {
					veces = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				this.MejorSolucion = RS();
				break;

			case VND:
				System.out.println("Busqueda por entornos variable");
								
				System.out.print("Introduzca el numero de ejecuciones: ");
				try {
					veces = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.MejorSolucion = VND(veces);
				break;

			case BVNS:
				System.out.println("Busqueda por entornos variable");
								
				System.out.print("Introduzca el numero de ejecuciones: ");
				try {
					veces = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.MejorSolucion = BVNS(veces);
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
	public Solucion BVNS(int veces) {
		Solucion solInicial = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
				Problema.getAltoCaja(), Problema.getAnchoCaja(),0);
		
		int clembuterol = veces;
		int k = 2;
		Solucion mejorSolucion = solInicial;
		
		
		do {
			solInicial = Agitacion(solInicial,k);
			solInicial = BL(solInicial);
			if (solInicial.compareTo(mejorSolucion) < 0) {
				mejorSolucion = solInicial;
				clembuterol = veces;
				k = 2;
			}
			k++;
			clembuterol--;
		} while (k == 0);	
		
		return mejorSolucion;
	}
	
	
	public Solucion VND(int veces) {
		Solucion solInicial = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
				Problema.getAltoCaja(), Problema.getAnchoCaja(),0);
		
		int clembuterol = veces;
		int k = 2;
		Solucion mejorSolucion = solInicial;
		
		
		do {
			solInicial = GeneraSEntorno(solInicial,k);
			if (solInicial.compareTo(mejorSolucion) < 0) {
				mejorSolucion = solInicial;
				clembuterol = veces;
				k = 2;
			}
			k++;
			clembuterol--;
		} while (k == 0);	
		
		return mejorSolucion;
	}
	
	
	public Solucion RS() {
		Solucion mejorSolucion = new Solucion();
		int temperatura = 0;
		int L[];
		
		int k = 0;
		
		do {
			Solucion solActual = new Solucion();
			
			for (int m = 1;m < 10; m++) { // sustituir el 10 por L[k]
				if (Math.exp((mejorSolucion.getFObjetivo() - solActual.getFObjetivo())) >
				(int)(Math.random() * 2)) {
					mejorSolucion = solActual;
				}
			}
			
			k++;
		} while(k < 4); //falta el criterio de parada
		
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
	 * Metodo que devuelve la solucion del problema
	 * 
	 * @return Solucion - Solucion del problema
	 */
	public Solucion GeneraSEntorno(Solucion s, int k) {
		int permutacion[] = s.getPermutacion();
		
		int clembuterol = 0;
		int i = 0, x = 0, f = 0;
		int j = permutacion.length - 1;
		int temp = 0;

		Random rnd = new Random(System.nanoTime());
		
		i = (int)(rnd.nextDouble() * (j - 1));
		x = i;
		temp = permutacion[i];

		for (int z = 0; z < k; z++) {				
			for (int m = i; m < j; m++) {
				System.out.println("aaaaaaaaaaaaskadjflñksa");
				swap(permutacion, m, m+1);
				//if (i == j) {
			}
			//permutacion[j] = temp;
			//temp = permutacion[i];
		}
		
		
				
		return new Solucion(permutacion, Problema.getRectangulos(), Problema.getAltoCaja(),
				Problema.getAnchoCaja(), Solucion.FINITE);
	}

	
	
	/**
	 * Metodo que devuelve la solucion del problema
	 * 
	 * @return Solucion - Solucion del problema
	 */
	public Solucion Agitacion(Solucion s, int k) {
		int permutacion[] = s.getPermutacion();
		
		int clembuterol = 0;
		int i = 0;
		int j = 0;
				
		do {
			Random rnd = new Random(System.nanoTime());
					
			i = (int)(rnd.nextDouble() * permutacion.length);
			j = (int)(rnd.nextDouble() * permutacion.length);
					
			if (i != j) {
				swap(permutacion, i, j);
				clembuterol++;
			}
		} while ((i == j) && (clembuterol < k - 1));
				
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
