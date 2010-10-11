package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que contiene la informacion necesaria para representar un problema.
 * 
 * @author Jaime Abraham Corrales Gonzalez
 * @author Jaime Gonzalez Valdes
 * @author Miguel Monterrey Varela
 * @author Eduardo Perez Mederos
 * 
 * @version 0.1a
 */
public class Problem
{
	/**
	 * Bin - Prototipo de caja.
	 */
	private Bin b;
	
	/**
	 * Rec - Rectangulos del problema.
	 */
	private Rectangle rec[];
	
	/**
	 * S - Solucion inicial del problema.
	 */
	private Solution s;
	
	/**
	 * Constructor dada una ruta, absoluta o relativa, a un fichero de datos que
	 * contiene los datos de un problema de Bin Packing.
	 * 
	 * El formato del fichero viene especificado en el fichero modelo.dat.
	 * 
	 * @param fileName - Nombre del fichero de datos que representa el problema.
	 */
	public Problem (String fileName)
	{
		this.rec = readFile (fileName);
		
	    ArrayList<Rectangle> toSort = new ArrayList<Rectangle> ();
	    
		for (int i = 0; i < rec.length; i++)
			toSort.add (rec[i]);

	    Collections.sort (toSort, Collections.reverseOrder());
	    
		for (int i = 0; i < rec.length; i++)
			rec[i] = toSort.get(i);	
		
		this.s = new Solution (Solution.DETERMINISTIC, rec.length);
	}
	
	/**
	 * Metodo que obtiene la solucion inicial del problema.
	 * 
	 * @return Solucion inicial.
	 */
	public Solution getSolution ()
	{
		return this.s;
	}
	
	/**
	 * Metodo para leer un fichero de datos y construir los elementos basicos
	 * del problema.
	 * 
	 * @param fileName
	 * @return Array de rectangulos.
	 */
	public Rectangle[] readFile (String fileName)
	{	
		FileInputStream myStream = null;
		InputStreamReader myReader = null;
		StreamTokenizer myTokenizer = null;
		
		try
		{			
			myStream = new FileInputStream(fileName);
			myReader = new InputStreamReader(myStream);
			myTokenizer = new StreamTokenizer(myReader);
			
			myTokenizer.nextToken();
			int binWidth = (int) myTokenizer.nval;
			myTokenizer.nextToken();
			int binHeight = (int) myTokenizer.nval;
			b = new Bin (binWidth, binHeight);
			
			myTokenizer.nextToken();
			int n = (int) myTokenizer.nval;
			Rectangle rec[] = new Rectangle[n];
			
			for (int i = 0; i < n; i++)
			{
				myTokenizer.nextToken ();
				int width = (int) myTokenizer.nval;
				myTokenizer.nextToken ();
				int height = (int) myTokenizer.nval;
				
				Rectangle r = new Rectangle (height, width);
				rec[i] = r;
			}
			
			return rec;			
		}
		catch (NegativeArraySizeException e)
		{
			System.out.println ("Error - Numero negativo de rectangulos.");
			
			System.exit (1);
		}
		catch (IOException e)
		{
			System.out.println ("Error - Apertura/lectura de fichero.");
			
			System.exit (1);
		}
		finally
		{
			try
			{
				myStream.close ();
				myReader.close ();
			}
			catch (IOException e)
			{
				System.out.println ("Error - Cierre de fichero.");
				
				System.exit (1);
			}
		}
		
		return new Rectangle[0];
	}
	
	/**
	 * Metodo que resume la informacion del problema de Bin Packing en una
	 * cadena de caracteres.
	 * 
	 * @return - Cadena de caracteres con informacion sobre el problema.
	 */
	public String toString ()
	{
		String toRet = new String ("Caja: [" + b.getWidth () + " x " + b.getHeight () + "], Area = " + b.getArea () + "\n");
		
		toRet += "Rectangulos del problema:\n";
		
		for (int i = 0; i < rec.length; i++)
			toRet += (i + 1) + ". " + rec[i] + "\n";
		
		return toRet;
	}
}
