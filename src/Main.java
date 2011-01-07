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
		
		Problema p = new Problema("./datasets/mod5.dat");
		System.out.println(p);
		
		int opcion = 0;
		do {
			System.out.println("Introduzca la heuristica que desea utilizar para obtener una solucion optima");		System.out.println("Busquedas por entornos:");
			System.out.println("1 - BAP: Busqueda aleatoria pura");
			System.out.println("2 - BRA: Busqueda por recorrido al azar");
			System.out.println("3 - BL: Busqueda local");
			System.out.println("GRASP:");
			System.out.println("4 - GRP: GRASP");
			System.out.println("Metodos multiarranque:");
			System.out.println("5 - BAM: Busqueda con Arranque Multiple");
			System.out.println("Recocido simulado:");
			System.out.println("6 - RS: Recocido simulado");
			System.out.println("Busqueda por entornos variable:");
			System.out.println("7 - VND: Variable Neighbourhood Descent");
			System.out.println("8 - BVNS: VNS Basico. Busquedas multistart");
			System.out.println("Busqueda Tabu:");
			System.out.println("9 - BT: Busqueda Tabu");
			System.out.println("Busqueda por entornos variable:");
			System.out.println("10 - BD: Busquedas Dispersas");
			System.out.println();
			System.out.println();
			System.out.println("0 - Salir ");
			System.out.print("Opcion: "); 
			try {
				opcion = Integer.parseInt(br.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			System.out.println();
			Heuristica h = new Heuristica(opcion, p);
		
			System.out.println(h);
		} while (opcion != 0);
	}
}
