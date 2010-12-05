package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Heuristica 
{
	/**
	 * Constantes para indicar el tipo de heur’stica a utilizar
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
	/*public static final int DETERMINISTA = 1;
	public static final int MIXTA = 2;*/
	
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
		
		switch (tipoHeuristica) {
			case BAP:
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
				
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
			/*case DETERMINISTA:
				permDeterminista(rec.size());
				break;
			case MIXTA:
				permMixta(rec.size());
				break;*/
		}
	}
	
	public void BAP(int criterio, int veces) {
		int clembuterol = veces;
		
		Solucion solInicial = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(), Problema.getAltoCaja(), Problema.getAnchoCaja());

		this.MejorSolucion = solInicial;
		
		System.out.println("---------triple-------");
		System.out.println(MejorSolucion);
		
		do {
			Solucion solActual = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(), Problema.getAltoCaja(), Problema.getAnchoCaja());
			
			System.out.println("---------XXX-------");
			System.out.println(solActual);
			
			if (solActual.compareTo(this.MejorSolucion) < 0) {
				System.out.println("hola");
				this.MejorSolucion.clear();
				this.MejorSolucion = solActual;
				
				if (criterio == NoMejora) {
					clembuterol = veces;
				}
			}
			
			clembuterol--;
			
		} while(clembuterol > 0);

		System.out.println("---------mostro-------");
		System.out.println(MejorSolucion);
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
}
