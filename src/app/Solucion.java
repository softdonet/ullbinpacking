package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

/**
 * Clase con los datos necesarios para representar una solucion de Bin Packing.
 * 
 * @author Eduardo Perez Mederos
 * @author Miguel Monterrey Varela
 * @author Jaime Gonzalez Valdes
 * @author Oscar Mateos Lopez
 * 
 * @version 0.2
 * @since 0.1
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
	 * Constructor dado el numero de rectangulos
	 * 
	 * @param size - Numero de rectangulos.
	 */
	public Solucion(int size) {
		cajas = new ArrayList<Caja>();
		permutacion = new int[size];
	}
	
	/**
	 * Constructor dado el tipo de generacion de solucion inicial y el numero de
	 * rectangulos.
	 * 
	 * @param permType - Tipo de permutacion de datos.
	 * @param size - Numero de rectangulos.
	 */
	public Solucion (int permType, ArrayList<Rectangulo> rec, int altoCaja, int anchoCaja) {
		cajas = new ArrayList<Caja>();
		
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
	private void permAleatoria (int size)
	{
		permutacion = new int[size];
		ArrayList<Integer> toMix = new ArrayList<Integer>();

		for (int i = 0; i < size; i++)
			toMix.add(i);

		Collections.shuffle(toMix);

		for (int i = 0; i < size; i++)
			permutacion[i] = toMix.get(i);
	}
	
	/**
	 * Metodo que se encarga de la inicializacion de una solucion. Para un mismo
	 * conjunto de N rectangulos, crea una permutacion ordenada en funcion de su
	 * area (de mayor a menor).
	 * 
	 * @param size - Numero de rectangulos.
	 */
	private void permDeterminista(int size)
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
	private void permMixta (int size) // la dejamos o la quitamos
	{
		permutacion = new int[size];

		for (int i = 0; i < size; i++)
			permutacion[i] = i;

		for (int i = 0; (3 + i) < size; i++)
		{
			Random RNG = new Random(System.nanoTime());
			int j = RNG.nextInt(3);

			swap(permutacion, i, j + i);
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
	
	public void FiniteFirstFit(int altoCaja, int anchoCaja, ArrayList<Rectangulo> rec) {
		cajas.add(new Caja(altoCaja, anchoCaja));
		boolean introducido = false;
		
		for (int i = 0; i < permutacion.length; i++) {
			for (int j = 0; j < cajas.size(); j++) {
				if (CabeRectangulo(rec.get(permutacion[i]), cajas.get(j))) {
					cajas.get(j).NuevoRectangulo(rec.get(permutacion[i]));
					
					introducido = true;
					
					break;
				}
			}
			
			if (!introducido) {
				cajas.add(new Caja(altoCaja, anchoCaja));
				
				cajas.get(cajas.size()).NuevoRectangulo(rec.get(permutacion[i]));
			}
			
			introducido = false;
		}
	}
	
	public boolean CabeRectangulo(Rectangulo r, Caja c) {
		boolean toRet = false;
		
		HashMap<Punto, Integer> pc = c.getPuntoCota();
		TreeSet<Punto> auxPC = new TreeSet<Punto>(pc.keySet());
		
		for (Iterator<Punto> it = auxPC.iterator(); it.hasNext();) {
			Punto p = (Punto)it.next(); 
			int cota = pc.get(p);
			
			System.out.println("p libre: " + p + "  cota: " + cota);
			
			if ((r.getAlto() <= cota) && (r.getAncho() <= c.getAncho() - p.getX())) {
				toRet = true;
				
				r.setPos(p);
				
				break;
			}
		}
		
		return toRet;
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
