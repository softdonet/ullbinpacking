package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
	 * Constantes para indicar el tipo de heurística a utilizar
	 */
	
	/**
	 * Criterios de parada
	 */
	public static final int NoMejora = 0;
	public static final int NVeces = 1;
	
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
		
		switch (tipoHeuristica) {
			case BAP:
				System.out.println("Busqueda aleatoria pura");
				System.out.print("Introduzca el numero de ejecuciones: ");
				int veces = 0;
				try {
					veces = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Introduzca el criterio a utilizar para la parada");
				System.out.println("0 - NoMejora: No mejora la solucion optima en n-ejecuciones");
				System.out.println("1 - NVeces: Ejecutar N-veces");
				System.out.print("Criterio: ");
				int criterio = 0;
				try {
					criterio = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				BAP(criterio, veces);
				break;
				
			case BRA:
				System.out.println("Busqueda aleatoria pura");
				break;
				
			case BL:
				System.out.println("Busqueda local");
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
	public void BAP(int criterio, int veces) {
		int clembuterol = veces;
		
		Solucion solInicial = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
				Problema.getAltoCaja(), Problema.getAnchoCaja());

		this.MejorSolucion = solInicial;
		
		do {
			Solucion solActual = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
					Problema.getAltoCaja(), Problema.getAnchoCaja());
			
			if (solActual.compareTo(this.MejorSolucion) < 0) {
				this.MejorSolucion = solActual;
				
				if (criterio == NoMejora) {
					clembuterol = veces;
				}
			}
			
			clembuterol--;
			
		} while(clembuterol > 0);
	}
	
	/**
	 * Metodo que devuelve la solucion del problema
	 * 
	 * @return Solucion - Solucion del problema
	 */
	public void BRA(int criterio, int veces) {
		int clembuterol = veces;
		
		Solucion solInicial = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
				Problema.getAltoCaja(), Problema.getAnchoCaja());
		
		// aqui va a haber problemas modificar esto por las referencias
		Solucion solActual = solInicial;
		this.MejorSolucion = solActual;
		
		do {			
			solActual = GeneraSolVecina(solActual);
			
			if (solActual.compareTo(this.MejorSolucion) < 0) {
				this.MejorSolucion = solActual; // posible fallo
				
				if (criterio == NoMejora) {
					clembuterol = veces;
				}
			}
			
			clembuterol--;
		} while(clembuterol > 0);
	}
	
	/**
	 * Metodo que devuelve la solucion del problema
	 * 
	 * @return Solucion - Solucion del problema
	 */
	public void BL(Solucion s) {
	/*
	 * procedure Búsqueda Local(Var Solución Actual: Solución);
	begin
	GENERA(Solución Actual); 
	 repeat
	GENERA(Solución Vecina / Objetivo(Solución Vecina) < 
	                                      Objetivo(Solución Actual)); 
	Solución Actual := Solución Vecina; 
	until (Objetivo(Solución Vecina)  Objetivo(Solución Actual),       
	                                                  Solución Vecina);	
	 */
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
	public Solucion GeneraSolVecina(Solucion s) {
		return s;
	}
		
	/**
	 * Metodo que devuelve la solucion del problema
	 * 
	 * @return Solucion - Solucion del problema
	 */
	public Solucion getSolucion()
	{
		return MejorSolucion;
	}
	
	public String toString() {
		return new String("Solucion\n" + MejorSolucion + "\n");
	}
}
