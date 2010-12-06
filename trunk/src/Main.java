import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import app.Problema;
import app.Heuristica;

public class Main
{
	public static void main (String args[])
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		System.out.println("Proyecto Heuristicas 2010/11: Non-guillotine two-dimensional bin packing problem");
		System.out.println();
		System.out.println();
		System.out.println("Problema a resolver:");
		System.out.println();
		
		Problema p = new Problema("./datasets/mod3.dat");
		System.out.println(p);
		
		System.out.println("Introduzca la heuristica que desea utilizar para obtener una solucion optima");
		System.out.println("Busquedas por entornos:");
		System.out.println("0 - BAP: Busqueda aleatoria pura");
		System.out.println("1 - BRA: Busqueda por recorrido al azar");
		System.out.println("2 - BL: Busqueda local");
		System.out.print("Opcion: "); 
		int opcion = 0;
		try {
			opcion = Integer.parseInt(br.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		Heuristica h = new Heuristica(opcion, p);
		
	}
}
