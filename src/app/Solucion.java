package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Clase con los datos necesarios para representar una solucion de Bin Packing.
 * 
 * @author Eduardo Perez Mederos
 * @author Miguel Monterrey Varela
 * @author Jaime Gonzalez Valdes
 * @author Oscar Mateos Lopez
 * 
 * @version 2.0
 * @since 0.1
 */
public class Solucion implements Comparable<Solucion>
{
	/**
	 * Constantes para indicar el tipo de generacion de solucion inicial.
	 */
	public static final int ALEATORIA = 0;
	public static final int DETERMINISTA = 1;
	public static final int MIXTA = 2;
	
	
	/**
	 * Constantes para indicar el algoritmo de colocacion.
	 */
	public static final int FINITE = 0;
	public static final int GRASP = 1;
	
	/**
	 * Array de Cajas.
	 */
	private ArrayList<Caja> Cajas;
	
	/**
	 * Solucion del problema.
	 */
	private int Permutacion[];
	private ArrayList<Integer> PermuOcupada;
	
	/**
	 * Valor de la funcion objetivo.
	 */
	private int Objetivo;
	
	/**
	 * Sumatorio del area ocupada por todos los rectangulos de todas las cajas.
	 */
	private int AreaOcupada;

	
	/**
	 * Constructor dado el numero de rectangulos.
	 */
	public Solucion() {
		Cajas = new ArrayList<Caja>();
		Permutacion = null;
		PermuOcupada = null;
		Objetivo = 0;
		AreaOcupada = 0;
	}
	
	
	/**
	 * Constructor dado el tipo de generacion de solucion inicial y el numero de
	 * rectangulos.
	 * 
	 * @param permType Tipo de permutacion de datos.
	 * @param rec Array de rectangulos.
	 * @param altoCaja Tamano del alto de la caja.
	 * @param anchoCaja Tamano del ancho de la caja.
	 */
	public Solucion (int permType, ArrayList<Rectangulo> rec, int altoCaja, int anchoCaja, int colocacion) {
		Cajas = new ArrayList<Caja>();
		PermuOcupada = new ArrayList<Integer>();
		
		for (int i = 0; i < rec.size(); i++) {
		    PermuOcupada.add(0);
		}
		
		switch (permType) {
			case ALEATORIA:
				permAleatoria(rec.size());
				break;
			case DETERMINISTA:
				permDeterminista(rec.size());
				break;
			case MIXTA:
				permMixta(rec.size());
				break;
		}
		
		switch(colocacion) {
			case FINITE: 
				FiniteFirstFit(altoCaja, anchoCaja, rec);
				break;
				
			case GRASP:
				Grasp(altoCaja, anchoCaja, rec);
				break;
		}
	}
	
	
	/**
	 * Constructor dado el tipo de generacion de solucion inicial y el numero de
	 * rectangulos.
	 * 
	 * @param rec Array de rectangulos.
	 * @param altoCaja Tamano del alto de la caja.
	 * @param anchoCaja Tamano del ancho de la caja.
	 * @param colocacion Tipo de algoritmo de colocacion.
	 */
	public Solucion (int permutacion[], ArrayList<Rectangulo> rec, int altoCaja, int anchoCaja, int colocacion) {
		Cajas = new ArrayList<Caja>();
		PermuOcupada = new ArrayList<Integer>();
		
		for (int i = 0; i < rec.size(); i++) {
		    PermuOcupada.add(0);
		}
		
		Permutacion = permutacion;
		
		switch(colocacion) {
		case FINITE: 
			FiniteFirstFit(altoCaja, anchoCaja, rec);
			break;
			
		case GRASP:
			Grasp(altoCaja, anchoCaja, rec);
			break;
		}
	}
	
	
	/**
	 * Metodo que se encarga de la inicializacion de una solucion. Para un mismo
	 * conjunto de N rectangulos, crea una permutacion aleatoria.
	 * 
	 * @param size Numero de rectangulos.
	 */
	private void permAleatoria (int size) {
		Permutacion = new int[size];
		ArrayList<Integer> toMix = new ArrayList<Integer>();

		for (int i = 0; i < size; i++)
			toMix.add(i);

		Collections.shuffle(toMix);

		for (int i = 0; i < size; i++)
			Permutacion[i] = toMix.get(i);
	}
	
	
	/**
	 * Metodo que se encarga de la inicializacion de una solucion. Para un mismo
	 * conjunto de N rectangulos, crea una permutacion ordenada en funcion de su
	 * area (de mayor a menor).
	 * 
	 * @param size Numero de rectangulos.
	 */
	private void permDeterminista(int size) {
		Permutacion = new int[size];

		for (int i = 0; i < size; i++)
			Permutacion[i] = i;
	}
	
	
	/**
	 * Metodo que se encarga de la inicializacion de una solucion. Para un mismo
	 * conjunto de N rectangulos, crea una permutacion mixta, resultante de
	 * aplicar un metodo determinista y un metodo aleatorio.
	 * 
	 * @param size Numero de rectangulos.
	 */
	private void permMixta (int size) {
		Permutacion = new int[size];

		for (int i = 0; i < size; i++)
			Permutacion[i] = i;

		for (int i = 0; (3 + i) < size; i++)
		{
			Random RNG = new Random(System.nanoTime());
			int j = RNG.nextInt(3);

			swap(Permutacion, i, j + i);
		}
	}
	
	
	/**
	 * Metodo que se encarga de intercambiar elementos para las inicializaciones
	 * mixtas.
	 * 
	 * @param array Array que contiene la permutacion.
	 * @param posOne Posicion del primer elemento a intercambiar.
	 * @param posTwo Posicion del segundo elemento a intercambiar.
	 */
	private void swap (int array[], int posOne, int posTwo) {
		int temp = array[posTwo];
		array[posTwo] = array[posOne];
		array[posOne] = temp;
	}
	
	
	/**
	 * Metodo que devuelve la permutacion de la solucion.
	 * 
	 * @return Permutacion
	 */
	public int[] getPermutacion() {
		return Permutacion.clone();
	}

	
	/**
	 * Metodo que devuelve el valor de la funcion objetivo de la solucion.
	 * 
	 * @return Objetivo
	 */
	public int getFObjetivo() {
		return Objetivo;
	}

	
	/**
	 * Calculo de el area total usada. Sumatorio del area
	 * total de cada caja.
	 * 
	 * @return areaTotal - Area total usada en todas las cajas.
	 */
	private int AreaTotal() {
		int aux = 0;
		int auxc = 0;
		for (int i = 0; i < Cajas.size(); i++){
			Caja c = Cajas.get(i);
			auxc = auxc + c.getArea();
			aux = aux + c.getAreaRestante();			
		}
		aux = (aux *100)/ auxc;
		return aux;
	}
	
	
	/**
	 * Calculo de el area total usada menos la ultima caja. Sumatorio del area
	 * total de cada caja.
	 * 
	 * @return areaTotal - Area total usada en todas las cajas menos la ultima.
	 */
	public int getAreaTotalMU() {
		int aux = 0;
		int auxc = 0;
		
		for (int i = 0; i < Cajas.size() - 1; i++){
			Caja c = Cajas.get(i);
			auxc = auxc + c.getArea();
			aux = aux + c.getAreaRestante();			
		}
		
		aux = (aux * 100) / auxc;
		
		return aux;
	}
	
	
	/**
	 * Algoritmo de colocacion inicial de los rectangulos en una caja
	 * tras obtener la permutacion inicial.
	 * 
	 * @param altoCaja Alto de las cajas.
	 * @param anchoCaja Ancho de las cajas.
	 * @param rec Array de rectangulos a introducir.
	 */
	private void FiniteFirstFit(int altoCaja, int anchoCaja, ArrayList<Rectangulo> rec) {
		Cajas.add(new Caja(altoCaja, anchoCaja));
		boolean introducido = false;
		
		for (int i = 0; i < Permutacion.length; i++) {
			for (int j = 0; j < Cajas.size(); j++) {
				if (Cajas.get(j).CabeRectangulo(rec.get(Permutacion[i]))) {
					Cajas.get(j).NuevoRectangulo(rec.get(Permutacion[i]));
					
					introducido = true;
					
					break;
				}
			}
			
			if (!introducido) {
				Cajas.add(new Caja(altoCaja, anchoCaja));				
				Cajas.get(Cajas.size() - 1).NuevoRectangulo(rec.get(Permutacion[i]));
			}
			
			introducido = false;
		}
		
		this.AreaOcupada = AreaTotal();
		this.Objetivo = Cajas.size();
	}
	
	
	/**
	 * Algoritmo de colocacion para el metodo Grasp. El cual elije al azar uno 
	 * de los tres mejores puntos disponibles para el rectangulo a insertar.
	 * 
	 * @param altoCaja Alto de las cajas.
	 * @param anchoCaja Ancho de las cajas.
	 * @param rec Array de rectangulos a introducir.
	 */
	private void Grasp(int altoCaja, int anchoCaja, ArrayList<Rectangulo> rec) {
		Cajas.add(new Caja(altoCaja, anchoCaja));
		boolean introducido = false;
		
		for (int i = 0; i < Permutacion.length; i++) {
			for (int j = 0; j < Cajas.size(); j++) {
				Rectangulo[] entrar = Cajas.get(j).CabeAlgunRectangulo(rec.get(Permutacion[i]));
				int N = Cajas.get(j).getNu();
				if (N > 0) {
					int numAlea = (int) (Math.random()*N+1);
					Cajas.get(j).NuevoRectangulo(entrar[numAlea - 1]);
					introducido = true;
					break;
				} 
			}
			if (!introducido) {
				Cajas.add(new Caja(altoCaja, anchoCaja));				
				Cajas.get(Cajas.size() - 1).NuevoRectangulo(rec.get(Permutacion[i]));
			}
			introducido = false;
		}
		
		this.AreaOcupada = AreaTotal();
		this.Objetivo = Cajas.size();
	}
	
	
	/**
	 * Metodo que compara dos soluciones y determina cual es el mayor en funcion
	 * de su valor de la F. Objetivo y su area total ocupada.
	 * 
	 * @param s Solucion a comparar.
	 * @return 1, 0 o -1 en funcion de si la solucion es
	 * mayor, igual o menor la solucion comparada.
	 */
	public int compareTo(Solucion s)
	{
		return ((Integer)Objetivo).compareTo(s.getFObjetivo());
	}

	
	/**
	 * Metodo para resumir la informacion de una solucion en una cadena de caracteres.
	 * 
	 * @return String - Solucion.
	 */
	public String toString () {
		String toRet = new String ("\n\nSolucion\n\n");
		
		for (int i = 0; i < this.Cajas.size(); i++) {
			toRet += "Caja: " + i + "\n" + "\n" + this.Cajas.get(i) + "\n" + "\n";
		}
		
		toRet += "Se han utilizado un total de ";
		toRet += this.Objetivo + " cajas" + "\n";
		toRet += "Con un espacio total ocupado del ";
		toRet += this.AreaOcupada + "%" + "\n" + "\n";
		
		toRet += "Permutacion: ";
		for (int i = 0; i < this.Permutacion.length; i++)
			toRet += this.Permutacion[i] + " ";
		
		toRet += "\n";
		
		return toRet;
	}
}
