package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Clase con los datos necesarios para representar un problema de Bin Packing.
 * 
 * @author Eduardo Perez Mederos
 * @author Miguel Monterrey Varela
 * @author Jaime Gonzalez Valdes
 * @author Oscar Mateos Lopez
 * 
 * @version 1.0
 * @version 0.1
 */
public class Problema
{
	private ArrayList<Rectangulo> Rec;
	private int AltoCaja; // H
	private int AnchoCaja; // W

	/**
	 * Constructor de la clase Problema
	 * 
	 * @param fileName - Nombre del archivo
	 */
	public Problema(String fileName) 
	{
		this.Rec = readFile(fileName);
		
		// Preordenacion de rectangulos. Sin ella no funciona la permutacion determinista.
	    Collections.sort(Rec, Collections.reverseOrder());
	}
	
	public ArrayList<Rectangulo> getRectangulos() {
		ArrayList<Rectangulo> recs = new ArrayList<Rectangulo>();
		
		for (int i = 0; i < Rec.size(); i++) {
			recs.add(new Rectangulo(Rec.get(i).getAlto(), Rec.get(i).getAncho()));
		} 
		
		Collections.sort(recs, Collections.reverseOrder());
		
		return recs;
	}
	
	public int getAltoCaja() {
		return AltoCaja;
	}
	
	public int getAnchoCaja() {
		return AnchoCaja;
	}
	
	/**
	 * Metodo que devuelve un array de Retangulos despues de leerlos de un fichero
	 * 
	 * @param fileName - Nombre del archivo
	 * @return ArrayList<Rectangulo> - Array de rectangulos
	 */
	public ArrayList<Rectangulo> readFile (String fileName)
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
                AnchoCaja = (int)myTokenizer.nval;
                myTokenizer.nextToken();
                AltoCaja = (int)myTokenizer.nval;
                
                myTokenizer.nextToken();
                int n = (int)myTokenizer.nval;
                ArrayList<Rectangulo> rec = new ArrayList<Rectangulo> ();
                
                for (int i = 0; i < n; i++) {
                	myTokenizer.nextToken ();
                    int width = (int)myTokenizer.nval;
                    myTokenizer.nextToken ();
                    int height = (int)myTokenizer.nval;
                        
                    Rectangulo r = new Rectangulo(height, width);
                    rec.add(r);
                }
                
                return rec;                     
        }
        catch (IOException e)
        {
                System.out.println("Error - Apertura/lectura de fichero.");
                
                System.exit(1);
        }
        finally
        {
                try
                {
                        myStream.close();
                        myReader.close();
                }
                catch (IOException e)
                {
                        System.out.println("Error - Cierre de fichero.");
                        
                        System.exit(1);
                }
        }
        
        return new ArrayList<Rectangulo> ();
	}
	
	/**
	 * Metodo que resume en una cadena de caracteres el problema
	 * 
	 * @return String - Problema
	 */
	public String toString ()
	{
		String toRet = new String ("Caja:\n[" + this.AnchoCaja + ", " + this.AltoCaja + "], Area: " +
				(this.AltoCaja * this.AnchoCaja) + "\n\n");
		
		toRet += "Rectangulos:\n";
		for (int i = 0; i < Rec.size(); i++)
			toRet += Rec.get(i) + "\n";
		
		return toRet;
	}
}
