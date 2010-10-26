package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Clase con los datos necesarios para representar una solucion de Bin Packing.
 * 
 * @author Jaime Abraham Corrales Gonzalez
 * @author Eduardo Perez Mederos
 * @author Miguel Monterrey Varela
 * @author Jaime Gonzalez Valdes
 * @author Oscar Mateos Lopez
 * 
 * @version 0.1
 */
public class Solucion
{
	/**
	 * Constantes para indicar el tipo de generacion de solucion inicial
	 */
	public static final int ALEATORIA = 0;
	public static final int DETERMINISTA = 1;
	public static final int MIXTA = 2;
	
	private ArrayList<Caja> cajas;
	private int permutacion[];
	
	/**
	 * Constructor dado el tipo de generacion de solucion inicial y el numero de
	 * rectangulos.
	 * 
	 * @param genType - Tipo de permutacion de datos.
	 * @param size - Numero de rectangulos.
	 */
	public Solucion (int permType, int size) // Habria que darle la caja prototipo para que genere mas iguales?
	{
		cajas = new ArrayList<Caja> ();
		
		switch (permType)
		{
			case ALEATORIA:
				permAleatoria (size);
				break;
			case DETERMINISTA:
				permDeterminista (size);
				break;
			case MIXTA:
				permMixta (size);
				break;
		}
	}
	
	/**
	 * Metodo que se encarga de la inicializacion de una solucion. Para un mismo
	 * conjunto de N rectangulos, crea una permutacion aleatoria.
	 * 
	 * @param size - Numero de rectangulos.
	 */
	private void permAleatoria (int size)
	{
		permutacion = new int[size];
		ArrayList<Integer> toMix = new ArrayList<Integer>();

		for (int i = 0; i < size; i++)
			toMix.add (i);

		Collections.shuffle (toMix);

		for (int i = 0; i < size; i++)
			permutacion[i] = toMix.get (i);
	}
	
	/**
	 * Metodo que se encarga de la inicializacion de una solucion. Para un mismo
	 * conjunto de N rectangulos, crea una permutacion ordenada en funcion de su
	 * area (de mayor a menor).
	 * 
	 * @param size - Numero de rectangulos.
	 */
	private void permDeterminista (int size)
	{
		permutacion = new int[size];

		for (int i = 0; i < size; i++)
			permutacion[i] = i;
	}
	
	/**
	 * Metodo que se encarga de la inicializacion de una solucion. Para un mismo
	 * conjunto de N rectangulos, crea una permutacion mixta, resultante de
	 * aplicar un metodo determinista y un metodo aleatorio.
	 * 
	 * @param size - Numero de rectangulos.
	 */
	private void permMixta (int size)
	{
		permutacion = new int[size];

		for (int i = 0; i < size; i++)
			permutacion[i] = i;

		for (int i = 0; (3 + i) < size; i++)
		{
			Random RNG = new Random (System.nanoTime ());
			int j = RNG.nextInt (3);

			swap (permutacion, i, j + i);
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
	private void swap (int array[], int posOne, int posTwo)
	{
		int temp = array[posTwo];
		array[posTwo] = array[posOne];
		array[posOne] = temp;
	}
	
	/**
	 * Metodo para resumir la informacion de una solucion en una cadena de caracteres.
	 */
	public String toString ()
	{
		String toRet = new String ("Permutation: ");
		
		for (int i = 0; i < permutacion.length; i++)
			toRet += permutacion[i] + " ";
		
		toRet += "\n";
		
		return toRet;
	}
}