import app.Problem;

/**
 * Clase principal del proyecto.
 * 
 * @author Jaime Abraham Corrales Gonzalez
 * @author Jaime Gonzalez Valdes
 * @author Miguel Monterrey Varela
 * @author Eduardo Perez Mederos
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
