import app.Problem;

/**
 * Clase principal del proyecto.
 * 
 * @author Jaime Abraham Corrales Gonzalez
 * @author Anadid vuestros nombres
 * 
 * @version 0.1
 */
public class Main
{
	public static void main (String args[])
	{
		Problem p = new Problem ("./datasets/modelo.dat");
		
		System.out.println (p);
	}
}
