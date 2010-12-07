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
	 * Criterios de intercambio en permutacion solucion vecina
	 */
	public static final int SIMPLE = 0;			 // Se intercambian dos posiciones aleatoria
	public static final int RODARP = 1;          // Se rueda tada la permutacion una posicion, dada una posicion aleatoria
	
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
		int criterioSV = 0;
		
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
				
				System.out.println("Introduzca el criterio a utilizar para obtener la solucion vecina");
				System.out.println("0 - SIMPLE: Intercambia deos posiciones aleatorias en la permutacion");
				System.out.println("1 - RODARP: Toma una posicion aleatoria y la rueda una posicion hasta el final");
				System.out.print("CriterioSV: ");
				try {
					criterioSV = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.MejorSolucion = BRA(criterio, criterioSV, veces);
				break;
				
			case BL:
				System.out.println("Busqueda local");
				
				Solucion solActual = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
						Problema.getAltoCaja(), Problema.getAnchoCaja());
				
				System.out.println("Introduzca el criterio a utilizar para obtener la solucion vecina");
				System.out.println("0 - SIMPLE: Intercambia deos posiciones aleatorias en la permutacion");
				System.out.println("1 - RODARP: Toma una posicion aleatoria y la rueda una posicion hasta el final");
				System.out.print("CriterioSV: ");
				try {
					criterioSV = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.MejorSolucion = BL(solActual, criterioSV);
				break;
				
			case GRASP:
				System.out.println("GRASP");
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
				Problema.getAltoCaja(), Problema.getAnchoCaja());

		Solucion mejorSolucion = solInicial;
		
		do {
			Solucion solActual = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
					Problema.getAltoCaja(), Problema.getAnchoCaja());
			
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
	public Solucion BRA(int criterio, int criterioSVecina, int veces) {
		int clembuterol = veces;
		
		Solucion solInicial = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
				Problema.getAltoCaja(), Problema.getAnchoCaja());
		
		Solucion solActual = solInicial;
		Solucion mejorSolucion = solActual;
		
		do {			
			solActual = GeneraSVecina(solActual, criterioSVecina);
			
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
	public Solucion BL(Solucion solActual, int criterioSVecina) {
		Solucion mejorSolucion = solActual;
		boolean mejor = false;
		
		do {	
			mejor = false;
			solActual = GeneraSVecina(solActual, criterioSVecina);
			
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
	public void GRASP() {
		/*
		 * Procedure GRASP
			Begin
  			Preprocesamiento
			Repeat
			Fase Constructiva(Solución);
			PostProcesamiento(Solución);
			Actualizar(Solución, MejorSolución);
			Until (Criterio de parada);
			End.
		 */
	}
	
	/**
	 * Metodo que devuelve la solucion del problema
	 * 
	 * @return Solucion - Solucion del problema
	 */
	public Solucion GeneraSVecina(Solucion s, int criterioSVecina) {
		int permutacion[] = s.getPermutacion();
		
		switch(criterioSVecina) {
			case SIMPLE:
				int i = 0;
				int j = 0;
				
				do {
					Random rnd = new Random(System.nanoTime());
					
					i = (int)(rnd.nextDouble() * permutacion.length);
					j = (int)(rnd.nextDouble() * permutacion.length);
					
					if (i != j)
						swap(permutacion, i, j);
				} while (i == j);
				
				break;
				
			case RODARP:
				Random rnd = new Random(System.nanoTime());
				
				for (i = (int)(rnd.nextDouble() * (s.getPermutacion().length - 1));
					i < s.getPermutacion().length - 1; i++) {
					swap(permutacion, i, i + 1);
				}
				
				break;
		}
		
		return new Solucion(permutacion, Problema.getRectangulos(), Problema.getAltoCaja(),
				Problema.getAnchoCaja());
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
	
	public String toString() {
		return new String(MejorSolucion + "\n");
	}
}
