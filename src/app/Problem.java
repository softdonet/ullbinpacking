package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Clase que contiene la informacion necesaria para representar un problema.
 * 
 * @author Jaime Abraham Corrales Gonzalez
 * @author Anadid vuestros nombres
 * 
 * @version 0.1
 */
public class Problem
{
	private int binHeight;
	private int binWidth;
	private Rectangle rec[];
	
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
		rec = readFile (fileName);
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
			binWidth = (int) myTokenizer.nval;
			myTokenizer.nextToken();
			binHeight = (int) myTokenizer.nval;
			
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
		String toRet = new String ("Caja: [" + this.binWidth + " x " + this.binHeight + "], Area: " + this.binWidth * this.binHeight + "\n");
		
		toRet += "Rectangulos del problema:\n";
		
		for (int i = 0; i < rec.length; i++)
			toRet += (i + 1) + ". " + rec[i] + "\n";
		
		return toRet;
	}
}
