package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
/*import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;*/


/**
 * Clase con los datos necesarios para representar un problema de Bin Packing.
 * 
 * @author Eduardo Perez Mederos
 * @author Miguel Monterrey Varela
 * @author Jaime Gonzalez Valdes
 * @author Oscar Mateos Lopez
 * 
 * @version 0.1
 */
public class Problema
{
	private ArrayList<Rectangulo> rec;
	private int altoCaja; // H
	private int anchoCaja; // W
	private Solucion s;
	
	public Problema(String fileName) 
	{
		this.rec = readFile(fileName);
		
		// Preordenacion de rectangulos. Sin ella no funciona la permutacion determinista.
	    Collections.sort(rec, Collections.reverseOrder());
		
		s = new Solucion(Solucion.DETERMINISTA, rec, altoCaja, anchoCaja);
		
		// Prueba
		Caja c = new Caja(this.altoCaja, this.anchoCaja);
		
		Punto p1 = new Punto(0, 0);
		Punto p2 = new Punto(3, 0);
		
		Rectangulo r = new Rectangulo(3, 5);
		Rectangulo rr = new Rectangulo(2, 7);
		r.setPos(p1);
		rr.setPos(p2);
		
		c.NuevoRectangulo(r);
		c.NuevoRectangulo(rr);
		
		System.out.println(c);
		
		/* Otra Prueba
		HashMap<Punto, Integer> pc = new HashMap<Punto, Integer>();
		
		pc.put(new Punto(), 10);
		pc.put(new Punto(4,3), 10);
		pc.put(new Punto(2,4), 2);
		
		TreeSet<Punto> auxPC = new TreeSet<Punto>(pc.keySet());
		
		for (Iterator it = auxPC.iterator(); it.hasNext(); ) {
			Punto p = (Punto)it.next();//new Punto((Punto)it.hasNext());
			int cc = pc.get(p);
			System.out.println("punto: " + p + "  cota: " + cc);
		}
		*/
		
		
	}
	
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
                anchoCaja = (int)myTokenizer.nval;
                myTokenizer.nextToken();
                altoCaja = (int)myTokenizer.nval;
                
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
	
	public Solucion getSolucion()
	{
		return s;
	}
	
	public String toString ()
	{
		String toRet = new String ("Caja: [" + anchoCaja + ", " + altoCaja + "]\n");
		
		for (int i = 0; i < rec.size (); i++)
			toRet += rec.get (i) + "\n";
		
		return toRet;
	}
}
