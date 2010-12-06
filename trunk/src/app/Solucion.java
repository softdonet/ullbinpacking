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
 * @version 1.0
 * @since 0.1
 */
public class Solucion implements Comparable<Solucion>
{
	/**
	 * Constantes para indicar el tipo de generacion de solucion inicial
	 */
	public static final int ALEATORIA = 0;
	public static final int DETERMINISTA = 1;
	public static final int MIXTA = 2;
	
	private ArrayList<Caja> Cajas;
	private int Permutacion[];
	private int Objetivo;
	private int AreaOcupada;

	/**
	 * Constructor dado el numero de rectangulos
	 * 
	 * @param size - Numero de rectangulos.
	 */
	public Solucion() {
		Cajas = new ArrayList<Caja>();
		Permutacion = null;
		Objetivo = 0;
		AreaOcupada = 0;
	}
	
	/**
	 * Constructor dado el tipo de generacion de solucion inicial y el numero de
	 * rectangulos.
	 * 
	 * @param permType - Tipo de permutacion de datos.
	 * @param rec - Array de rectangulos
	 * @param altoCaja - Tama–o del alto de la caja
	 * @param anchoCaja - Tama–o del ancho de la caja
	 */
	public Solucion (int permType, ArrayList<Rectangulo> rec, int altoCaja, int anchoCaja) {
		Cajas = new ArrayList<Caja>();
		
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
		
		FiniteFirstFit(altoCaja, anchoCaja, rec);
	}
	
	/**
	 * Metodo que se encarga de la inicializacion de una solucion. Para un mismo
	 * conjunto de N rectangulos, crea una permutacion aleatoria.
	 * 
	 * @param size - Numero de rectangulos.
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
	 * @param size - Numero de rectangulos.
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
	 * @param size - Numero de rectangulos.
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
	 * @param array - Array que contiene la permutacion.
	 * @param posOne - Posicion del primer elemento a intercambiar.
	 * @param posTwo - Posicion del segundo elemento a intercambiar.
	 */
	private void swap (int array[], int posOne, int posTwo) {
		int temp = array[posTwo];
		array[posTwo] = array[posOne];
		array[posOne] = temp;
	}
	
	public int getAreaOcupada() {
		return AreaOcupada;
	}
	
	public void setAreaOcupada(int areaOcupada) {
		AreaOcupada = areaOcupada;
	}
	
	public ArrayList<Caja> getCajas() {
		return Cajas;
	}

	public void setCajas(ArrayList<Caja> cajas) {
		this.Cajas = cajas;
	}
	public int[] getPermutacion() {
		return Permutacion;
	}

	public void setPermutacion(int[] permutacion) {
		this.Permutacion = permutacion;
	}
	
	public int getFObjetivo() {
		return Objetivo;
	}

	public void setFObjetivo(int objetivo) {
		Objetivo = objetivo;
	}
	
	public int AreaTotal() {
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
	 * Algoritmo de colocacion inicial de los rectangulos en una caja
	 * tras obtener la permutacion inicial.
	 * 
	 * @param altoCaja - Alto de las cajas
	 * @param anchoCaja - Ancho de las cajas
	 * @param rec - Array de rectangulos a introducir
	 */
	public void FiniteFirstFit(int altoCaja, int anchoCaja, ArrayList<Rectangulo> rec) {
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
	 * Metodo que compara dos soluciones y determina cual es el mayor en funcion
	 * de su valor de la F. Objetivo y su area total ocupada.
	 * 
	 * @param s - Solucion a comparar.
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
	 * @return String - Solucion
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
		
		toRet += "Pemutacion: ";
		for (int i = 0; i < this.Permutacion.length; i++)
			toRet += this.Permutacion[i] + " ";
		
		toRet += "\n";
		
		return toRet;
	}
}
