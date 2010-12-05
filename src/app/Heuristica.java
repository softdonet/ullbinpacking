package app;

import java.util.ArrayList;

public class Heuristica {

	/**
	 * Constantes para indicar el tipo de heur’stica a utilizar
	 */
	
	/**
	 * Busquedas por entornos
	 */
	public static final int BAP = 0;              // Busqueda aleatoria pura
	/*public static final int DETERMINISTA = 1;
	public static final int MIXTA = 2;*/
	
	/**
	 * Criterios de parada
	 */
	
	private Problema problema;
	private Solucion MejorSolucion;
	
	/**
	 * Constructor dado el tipo de generacion de solucion inicial y el numero de
	 * rectangulos.
	 * 
	 * @param permType - Tipo de heuristica.
	 * @param size 
	 */
	public Heuristica(int tipoHeuristica, ArrayList<Rectangulo> rec, int altoCaja, int anchoCaja) {
		problema = new Problema("./datasets/mod3.dat");
		
		switch (tipoHeuristica) {
			case BAP:
				BAP();
				break;
			/*case DETERMINISTA:
				permDeterminista(rec.size());
				break;
			case MIXTA:
				permMixta(rec.size());
				break;*/
		}
	}
	
	public void BAP() {
		int clembuterol = 0;
		
		Solucion solInicial = new Solucion(Solucion.ALEATORIA, problema, problema, problema);
		MejorSolucion = solInicial;
		
		do {
			Solucion solActual = new Solucion(Solucion.ALEATORIA, problema, problema, problema);
			
			if () {
		} while(clembuterol > NoMejora);
	GENERA(Soluci—n Actual); 
	if Objetivo(Soluci—n Actual) < Objetivo(MejorSoluci—n) then 
	Mejor Soluci—n := Soluci—n Actual; 
	until (criterio de parada);
	end
	}
}
