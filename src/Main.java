import app.Problema;

public class Main
{
	public static void main (String args[])
	{
		Problema p = new Problema ("./datasets/mod3.dat");
		
		System.out.println (p);
		System.out.println (p.getSolucion ());
	}
}
