package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Clase que contiene la informacion necesaria para representar una solucion del
 * problema de Bin Packing.
 * 
 * @author Jaime Abraham Corrales Gonzalez
 * @author Jaime Gonzalez Valdes
 * @author Miguel Monterrey Varela
 * @author Eduardo Perez Mederos
 * 
 * @version 0.1
 */
public class Solution
{
	/**
	 * Constantes para indicar el tipo de generacion de solucion inicial
	 */
	public static final int RANDOM = 0;
	public static final int DETERMINISTIC = 1;
	public static final int MIXED = 2;

	/**
	 * Permutation - Array que indica el orden de colocacion de los rectangulos.
	 */
	private int permutation[];

	/**
	 * INF - Constante inicializada al maximo valor entero positivo para su uso
	 * en la inicializacion del valor de la funcion objetivo.
	 */
	private final int INF = Integer.MAX_VALUE;

	/**
	 * objF - Valor de la funcion objetivo, es decir, numero de bins abiertos al
	 * final de la generacion de la solucion.
	 */
	private int objF;

	/**
	 * Constructor dado el tipo de generacion de solucion inicial y el numero de
	 * rectangulos.
	 * 
	 * @param genType
	 *            - Tipo de generacion de solucion inicial.
	 * @param size
	 *            - Numero de rectangulos.
	 */
	public Solution (int genType, int size)
	{
		this.objF = INF;

		switch (genType)
		{
			case RANDOM:
				randomInit (size);
				break;
			case DETERMINISTIC:
				deterministicInit (size);
				break;
			case MIXED:
				mixedInit (size);
				break;
		}
	}

	/**
	 * Metodo que se encarga de la inicializacion de una solucion. Para un mismo
	 * conjunto de N rectangulos, crea una permutacion ordenada en funcion de su
	 * area (de mayor a menor).
	 * 
	 * @param size
	 *            - Numero de rectangulos.
	 */
	private void deterministicInit (int size)
	{
		permutation = new int[size];

		for (int i = 0; i < size; i++)
			permutation[i] = i;
	}

	/**
	 * Metodo que obtiene el valor de la funcion objetivo de la solucion.
	 * 
	 * @return Valor de la funcion objetivo.
	 */
	public int getObjF ()
	{
		return this.objF;
	}

	/**
	 * Metodo que se encarga de la inicializacion de una solucion. Para un mismo
	 * conjunto de N rectangulos, crea una permutacion mixta, resultante de
	 * aplicar un metodo determinista y un metodo aleatorio.
	 * 
	 * @param size
	 *            - Numero de rectangulos.
	 */
	private void mixedInit (int size)
	{
		permutation = new int[size];

		for (int i = 0; i < size; i++)
			permutation[i] = i;

		for (int i = 0; (3 * i) < size; i++)
		{
			Random RNG = new Random (System.nanoTime ());
			int j = RNG.nextInt (3);

			swap (permutation, i, j + i);
		}
	}

	/**
	 * Metodo que se encarga de la inicializacion de una solucion. Para un mismo
	 * conjunto de N rectangulos, crea una permutacion aleatoria.
	 * 
	 * @param size
	 *            - Numero de rectangulos.
	 */
	private void randomInit (int size)
	{
		permutation = new int[size];
		ArrayList<Integer> toMix = new ArrayList<Integer>();

		for (int i = 0; i < size; i++)
			toMix.add (i);

		Collections.shuffle (toMix);

		for (int i = 0; i < size; i++)
			permutation[i] = toMix.get (i);
	}

	/**
	 * Metodo que asigna el nuevo valor de la funcion objetivo a la solucion.
	 * 
	 * @param objF
	 *            - Nuevo valor de la funcion objetivo.
	 */
	public void setObjF (int objF)
	{
		this.objF = objF;
	}

	/**
	 * Metodo que se encarga de intercambiar elementos para las inicializaciones
	 * mixtas.
	 * 
	 * @param array
	 *            - Array que contiene la permutacion.
	 * @param posOne
	 *            - Posicion del primer elemento a intercambiar.
	 * @param posTwo
	 *            - Posicion del segundo elemento a intercambiar.
	 */
	private void swap (int array[], int posOne, int posTwo)
	{
		int temp = array[posTwo];
		array[posTwo] = array[posOne];
		array[posOne] = temp;
	}
}
