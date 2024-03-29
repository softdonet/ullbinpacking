package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Clase con los metodos heuristicos para obtener mejores soluciones.
 * 
 * @author Eduardo Perez Mederos
 * @author Miguel Monterrey Varela
 * @author Jaime Gonzalez Valdes
 * @author Oscar Mateos Lopez
 * 
 * @version 2.0
 * @since 1.0
 */
public class Heuristica 
{
	/**
	 * Criterios de parada
	 */
	public static final int NOMEJORA = 0;
	public static final int NVECES = 1;
	
	
	/**
	 * Busquedas por entornos
	 */
	public static final int BAP = 1;              // Busqueda aleatoria pura
	public static final int BRA = 2;			  // Busqueda por recorrido al azar
	public static final int BL = 3;				  // Busqueda local
	
	
	/**
	 * GRASP
	 */
	public static final int GRASP = 4;
	
	
	/**
	 * Metodos multiarranque
	 */
	public static final int BAM = 5;              // Busqueda con arranque multiple
	
	
	/**
	 * Recocido simulado
	 */
	public static final int RS = 6;              // Recocido simulado
	
	
	/**
	 * Busqueda por entornos variables
	 */
	public static final int VND = 7;
	public static final int BVNS = 8;
	
	/**
	 * Heuristicas poblacionales
	 */
	public static final int BT = 9;               // Busqueda tabu
	
	/**
	 * Heuristicas poblacionales
	 */
	public static final int BD = 10;              // Busquedas dispersas
	
	private Problema Problema;
	private Solucion MejorSolucion;
	
	
	/**
	 * Constructor dado el tipo de generacion de solucion inicial y el numero de
	 * rectangulos.
	 * 
	 * @param tipoHeuristica Tipo de heuristica.
	 * @param p Problema.
	 */
	public Heuristica(int tipoHeuristica, Problema p) {
		Problema = p;
		MejorSolucion = new Solucion();
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int veces = 50;
		int criterio = 0;
		
		switch (tipoHeuristica) {
			case BAP:
				System.out.println("Busqueda aleatoria pura");
				System.out.print("Introduzca el numero de ejecuciones: ");
				try {
					veces = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Introduzca el criterio a utilizar para la parada");
				System.out.println("0 - NOMEJORA: No mejora la solucion optima en n-ejecuciones");
				System.out.println("1 - NVECES: Ejecutar N-veces");
				System.out.print("Criterio: ");
				
				try {
					criterio = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.MejorSolucion = BAP(criterio, veces);
				break;
				
			case BRA:
				System.out.println("Busqueda por recorrido al azar");
				System.out.print("Introduzca el numero de ejecuciones: ");
				try {
					veces = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Introduzca el criterio a utilizar para la parada");
				System.out.println("0 - NOMEJORA: No mejora la solucion optima en n-ejecuciones");
				System.out.println("1 - NVECES: Ejecutar N-veces");
				System.out.print("Criterio: ");
				try {
					criterio = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.MejorSolucion = BRA(criterio, veces);
				break;
				
			case BL:
				System.out.println("Busqueda local");
				
				Solucion solActual = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
						Problema.getAltoCaja(), Problema.getAnchoCaja(), Solucion.FINITE);
				
				this.MejorSolucion = BL(solActual);
				break;
				
			case GRASP:
				System.out.println("GRASP");
				
				Solucion solInicial = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
						Problema.getAltoCaja(), Problema.getAnchoCaja(), Solucion.GRASP);
				
				System.out.print("Introduzca el numero de ejecuciones: ");
				try {
					veces = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.MejorSolucion = GRASP(solInicial, veces);
				break;
				
			case BAM:
				System.out.println("Busqueda con arranque multiple");
								
				System.out.print("Introduzca el numero de ejecuciones: ");
				try {
					veces = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.MejorSolucion = BAM(veces);
				break;
				
			case RS:
				System.out.println("Recocido simulado");
				
				this.MejorSolucion = RS();
				break;

			case VND:
				System.out.println("Busqueda por entornos variable");			
				System.out.print("Introduzca el numero de ejecuciones: ");
				try {
					veces = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				this.MejorSolucion = VND(veces);
				break;

			case BVNS:
				System.out.println("Busqueda por entornos variable");				
				System.out.print("Introduzca el numero de ejecuciones: ");
				try {
					veces = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				this.MejorSolucion = BVNS(veces);
				break;
				
			case BT:
				System.out.println("Busqueda tabu");
				
				System.out.print("Introduzca el numero de ejecuciones: ");
				try {
					veces = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.MejorSolucion = BT(veces);

				break;
				
			case BD:
				System.out.println("Busquedas dispersas");
				
				System.out.print("Introduzca el numero de ejecuciones: ");
				try {
					veces = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.MejorSolucion = BD(veces);
				break;
		}
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema. Busca la mejor solucion
	 * posible cambiando aleatoriamente las permutaciones que le pasamos al
	 * algoritmo de colocacion
	 * 
	 * @param criterio
	 * @param veces
	 * 
	 * @return Solucion - Solucion del problema
	 */
	public Solucion BAP(int criterio, int veces) {
		int clembuterol = veces;
		
		Solucion solInicial = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
				Problema.getAltoCaja(), Problema.getAnchoCaja(), Solucion.FINITE);

		Solucion mejorSolucion = solInicial;
		
		do {
			Solucion solActual = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
					Problema.getAltoCaja(), Problema.getAnchoCaja(),0);
			
			if (solActual.compareTo(mejorSolucion) < 0) {
				mejorSolucion = solActual;
				
				if (criterio == NOMEJORA) {
					clembuterol = veces;
				}
			}
			
			clembuterol--;
			
		} while (clembuterol > 0);
		
		return mejorSolucion;
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema. Busca nuevas vecinas intercambiando
	 * al azar algunas posiciones de la permutacion.
	 * 
	 * @param criterio
	 * @param veces
	 * 
	 * @return Solucion - Solucion del problema.
	 */
	public Solucion BRA(int criterio, int veces) {
		int clembuterol = veces;
		
		Solucion solInicial = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
				Problema.getAltoCaja(), Problema.getAnchoCaja(), Solucion.FINITE);
		
		Solucion solActual = solInicial;
		Solucion mejorSolucion = solActual;
		
		do {			
			solActual = GeneraSVecina(solActual);
			
			if (solActual.compareTo(mejorSolucion) < 0) {
				mejorSolucion = solActual;
				
				if (criterio == NOMEJORA) {
					clembuterol = veces;
				}
			}
			
			clembuterol--;
		} while (clembuterol > 0);
		
		return mejorSolucion;
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema. Busqueda local.
	 * 
	 * @param solActual
	 * 
	 * @return Solucion - Solucion del problema.
	 */
	public Solucion BL(Solucion solActual) {
		Solucion mejorSolucion = solActual;
		boolean mejor = false;
		int clembuterol = 100;

		
		do {	
			mejor = false;
			solActual = GeneraSVecina(solActual);
			
			if (solActual.compareTo(mejorSolucion) < 0) {
				mejorSolucion = solActual;
				mejor = true;
			}

			clembuterol--;
			
			mejor = clembuterol == 0;
		} while (!mejor);
		
		return mejorSolucion;
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema. Este metodo va seleccionando
	 * para cada rectangulo los 3 mejores puntos donde ponerlo, (donde menos desperdicio
	 * genera) y a partir de ahí inserta en uno de los 3 eligiendo al azar.
	 * 
	 * @param solInicial
	 * @param veces
	 * 
	 * @return Solucion - Solucion del problema.
	 */
	public Solucion GRASP(Solucion solInicial, int veces) {
		int clembuterol = veces;
		
		Solucion mejorSolucion = solInicial;
		
		do {
			Solucion solActual = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
					Problema.getAltoCaja(), Problema.getAnchoCaja(),1);
			
			if (solActual.compareTo(mejorSolucion) < 0) {
				mejorSolucion = solActual;
				clembuterol = veces;
			}
			
			clembuterol--;
			
		} while (clembuterol > 0);
		
		return mejorSolucion;
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema. Genera una solucion inicial
	 * y apartir de ahí N soluciones mediante Busquedas Locales haciendo las per
	 * tinentes comparaciones para quedarnos con la mejor.
	 * 
	 * @param veces
	 * 
	 * @return Solucion - Solucion del problema.
	 */	
	public Solucion BAM(int veces) {
		Solucion solInicial = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
				Problema.getAltoCaja(), Problema.getAnchoCaja(),0);

		int clembuterol = veces;
		Solucion mejorSolucion = solInicial;
		
        do {
			solInicial = BL(solInicial);
			
			if (solInicial.compareTo(mejorSolucion) < 0) {
				mejorSolucion = solInicial;
				clembuterol = veces;
			}
			
			clembuterol--;

        } while (clembuterol > 0);
		return mejorSolucion;
	}

	
	/**
	 * Metodo que devuelve la solucion del problema. Genera cambios aleatorios
	 * en las permutaciones pero ademása, contiene una cola-memoria que nos 
	 * ayuda a evitar repetir el mismo movimiento en las siguietnes N (en este 
	 * caso 7) veces.
	 * 
	 * @param veces
	 * 
	 * @return Solucion - Solucion del problema.
	 */
	public Solucion BT(int veces) {
		Solucion solInicial = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
				Problema.getAltoCaja(), Problema.getAnchoCaja(),0);

		int permutacion[] = solInicial.getPermutacion();
		Solucion mejorSolucion = solInicial;
		int i = 0, j = 0, clembuterol = veces;
		boolean encontrado = false;
		
		Integer[] pares;
        pares = new Integer[2];		

		Queue<Integer[]> colatabu = new LinkedList<Integer[]>();
		
		do {
			do {
				Random rnd = new Random(System.nanoTime());
				
				i = (int)(rnd.nextDouble() * permutacion.length);
				j = (int)(rnd.nextDouble() * permutacion.length);
			
				if (i != j) {
					pares[0] = i;
					pares[1] = j;
					if (!colatabu.contains(pares)) {
						if (colatabu.size() < 7) {
							colatabu.add(pares);							
						} else {
							colatabu.remove();
							colatabu.add(pares);
						}
						encontrado = true;
						swap(permutacion, i, j);
					}	
				}
				
				
			} while (!encontrado);
			
			solInicial = new Solucion(permutacion, Problema.getRectangulos(), Problema.getAltoCaja(),
					Problema.getAnchoCaja(), Solucion.FINITE);

			if (solInicial.compareTo(mejorSolucion) < 0) {
				mejorSolucion = solInicial;
				clembuterol = veces;
			}
			
			clembuterol--;
			
		} while (clembuterol > 0);
		
		return mejorSolucion;
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema. Metodo de busquedas por entorno
	 * el cual primero produce una agitacion en la permutación y posteriormente rea-
	 * liza una Busqueda Local.
	 * 
	 * @param veces
	 * 
	 * @return Solucion - Solucion del problema.
	 */	
	public Solucion BVNS(int veces) {
		Solucion solInicial = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
				Problema.getAltoCaja(), Problema.getAnchoCaja(),0);
		
		int clembuterol = veces;
		int k = 2;
		Solucion mejorSolucion = solInicial;
		
		
		do {
			solInicial = Agitacion(solInicial,k);
			solInicial = BL(solInicial);
			if (solInicial.compareTo(mejorSolucion) < 0) {
				mejorSolucion = solInicial;
				clembuterol = veces;
				k = 2;
			}
			k++;
			clembuterol--;
		} while (k == 0);	
		
		return mejorSolucion;
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema. Metodo de busquedas por entorno.
	 * 
	 * @param veces
	 * 
	 * @return Solucion - Solucion del problema.
	 */
	public Solucion VND(int veces) {
		Solucion solInicial = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(),
				Problema.getAltoCaja(), Problema.getAnchoCaja(),0);
		
		int clembuterol = veces;
		int k = 2;
		Solucion mejorSolucion = solInicial;
		
		
		do {
			solInicial = GeneraSEntorno(solInicial,k);
			if (solInicial.compareTo(mejorSolucion) < 0) {
				mejorSolucion = solInicial;
				clembuterol = veces;
				k = 2;
			}
			k++;
			clembuterol--;
		} while (k == 0);	
		
		return mejorSolucion;
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema. Metodo de recocido simulado.
	 * 
	 * @return Solucion - Solucion del problema.
	 */
	public Solucion RS() {
		Solucion mejorSolucion = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(), Problema.getAltoCaja(),
				Problema.getAnchoCaja(), Solucion.FINITE);
		Solucion solActual = mejorSolucion;
		
		Random r = new Random(System.nanoTime());
		
		int k = 0;
		double alfa = 0.9;
		double mediaObj = 0;
		
		for (int i = 0; i < 100; i++) {
			Solucion s = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(), Problema.getAltoCaja(),
					Problema.getAnchoCaja(), Solucion.FINITE);
			
			mediaObj += s.getFObjetivo();
		}
		
		mediaObj = mediaObj / 100;
		
		double temperatura = 50 * mediaObj;
		double L = (int)Math.pow(Problema.getRectangulos().size(), 2);
		
		do {
			solActual = GeneraSVecina(solActual);
			
			for (int m = 0; m < L; m++) {
				if (mejorSolucion.getFObjetivo() > solActual.getFObjetivo()) {
					mejorSolucion = solActual;
				}
				else if (Math.exp((mejorSolucion.getFObjetivo() - solActual.getFObjetivo()) / temperatura) > r.nextFloat()) {
					mejorSolucion = solActual;
				}
			}
			
			k++;
			
			temperatura = temperatura * alfa;
			L = k * 1.05;
		} while (temperatura > 0.1);
		
		return mejorSolucion;
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema. Metodo de busqueda dispersa.
	 * Realiza dentro la mejora de la solucion combinada.
	 * Y actualiza el RefSet.
	 * 
	 * @param veces
	 * 
	 * @return Solucion - Solucion del problema.
	 */
	@SuppressWarnings("unchecked")
	Solucion BD(int veces) {
		ArrayList<Solucion> Poblacion = new ArrayList<Solucion>();
		ArrayList<Solucion> RefSet = new ArrayList<Solucion>();
		ArrayList<Solucion> Subconjunto = new ArrayList<Solucion>();
		
		Solucion mejorSolucion = new Solucion();
		
		int clembuterol = veces;
		int intentos = 0;
		
		boolean gnp = false;  // Generar nueva poblacion
		boolean gnrs = false; // Generar nuevo reference set
		
		do {
			Poblacion = CrearPoblacion(100);
			intentos = 10;
			
			do {
				gnp = false;
				
				RefSet = GenerarReferenceSet(10, (ArrayList<Solucion>)Poblacion.clone());
				
				int i = 0;
				int j = 1;
				
				do {
					gnrs = false;
					
					Subconjunto = SeleccionarSubconjunto((ArrayList<Solucion>)RefSet.clone(), i, j);
					Solucion solActual = CombinarSoluciones(Subconjunto);
					solActual = BL(solActual); // Mejorar la solucion

					// Actualizar RefSet
					if ((solActual.compareTo(RefSet.get(i)) < 0) || (solActual.compareTo(RefSet.get(j)) < 0)) {
						if (solActual.compareTo(RefSet.get(i)) < 0) {
							RefSet.remove(RefSet.get(i));
						} else {
							RefSet.remove(RefSet.get(j));
						}
						
						RefSet.add(solActual);
						
						i = 0;
						j = 1;
					} else {
						j++;
						
						if (j > RefSet.size() - 1) {
							i++;
							j = i + 1;
						}
					}
					
					if (i == RefSet.size() - 1) {
						gnrs = true;
					}
				} while (!gnrs);
				
				mejorSolucion = RefSet.get(0);
				
				for (int x = 1; x < RefSet.size(); x++) {
					if (RefSet.get(x).compareTo(mejorSolucion) < 0) { 
						mejorSolucion = RefSet.get(x);
					}
				}
				
				// Si para un numero de intentos no mejora
				// creamos una nueva poblacion
				intentos--;
				
				if (intentos == 0) {
					gnp = true;
				}
			} while (!gnp);
		
			clembuterol--;
		} while (clembuterol > 0);
		
		return mejorSolucion;
	}
	
	
	/**
	 * Metodo que devuelve una solucion vecina a partir de otra solucion.
	 * Una solución vecina de otra se obtiene al intercambia el orden en
	 * que se consideran dos rectángulos.
	 * 
	 * @param s Solucion inicial
	 * 
	 * @return Solucion - Solucion del problema.
	 */
	public Solucion GeneraSVecina(Solucion s) {
		int permutacion[] = s.getPermutacion();
		
		int i = 0;
		int j = 0;
				
		do {
			Random rnd = new Random(System.nanoTime());
					
			i = (int)(rnd.nextDouble() * permutacion.length);
			j = (int)(rnd.nextDouble() * permutacion.length);
					
			if (i != j)
				swap(permutacion, i, j);
		} while (i == j);
				
		return new Solucion(permutacion, Problema.getRectangulos(), Problema.getAltoCaja(),
				Problema.getAnchoCaja(), Solucion.FINITE);
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema, generando entornos que 
	 * obtenemos a rodando k posiciones para el final de la permutacion, si-
	 * endo aleatoria la eleccion de la primera posicion a rodar y contiguas
	 * a esta el resto.
	 * 
	 * @param s Solucion inicial
	 * @param k Numero de elementos
	 * 
	 * @return Solucion - Solucion del problema.
	 */
	public Solucion GeneraSEntorno(Solucion s, int k) {
		int permutacion[] = s.getPermutacion();
		
		int i = 0;
		int j = permutacion.length - 1;
		int temp = 0;

		Random rnd = new Random(System.nanoTime());
		
		i = (int)(rnd.nextDouble() * (j - 1));
		temp = permutacion[i];

		for (int z = 0; z < k; z++) {				
			for (int m = i; m < j; m++) {
				swap(permutacion, m, m+1);
			}
			permutacion[j] = temp;
			temp = permutacion[i];
		}
				
		return new Solucion(permutacion, Problema.getRectangulos(), Problema.getAltoCaja(),
				Problema.getAnchoCaja(), Solucion.FINITE);
	}


	/**
	 * Metodo que devuelve la solucion del problema. Genera nuevos entornos
	 * mediante la agitacion que consiste en intercambiar la posicion de x
	 * elementos (valor que nos llega por parametro, k) en al permutacion.
	 * 
	 * @param s Solucion inicial
	 * @param k Numero de elementos
	 * 
	 * @return Solucion - Solucion del problema.
	 */
	public Solucion Agitacion(Solucion s, int k) {
		int permutacion[] = s.getPermutacion();
		
		int clembuterol = 0;
		int i = 0;
		int j = 0;
				
		do {
			Random rnd = new Random(System.nanoTime());
					
			i = (int)(rnd.nextDouble() * permutacion.length);
			j = (int)(rnd.nextDouble() * permutacion.length);
					
			if (i != j) {
				swap(permutacion, i, j);
				clembuterol++;
			}
		} while ((i == j) && (clembuterol < k - 1));
				
		return new Solucion(permutacion, Problema.getRectangulos(), Problema.getAltoCaja(),
				Problema.getAnchoCaja(), Solucion.FINITE);
	}
	
	
	/**
	 * Metodo que crea una poblacion. Una poblacion es un conjunto de soluciones.
	 * 
	 * @param tamPoblacion
	 * 
	 * @return ArrayList<Solucion> - Array de soluciones
	 */
	private ArrayList<Solucion> CrearPoblacion(int tamPoblacion) {
		ArrayList<Solucion> poblacion = new ArrayList<Solucion>();
		
		poblacion.add(new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(), Problema.getAltoCaja(),
				Problema.getAnchoCaja(), Solucion.FINITE));
		
		while (poblacion.size() < tamPoblacion) {
			Solucion s = new Solucion(Solucion.ALEATORIA, Problema.getRectangulos(), Problema.getAltoCaja(),
					Problema.getAnchoCaja(), Solucion.FINITE);
			
			s = GRASP(s, 10000); // Obtiene una solucion dispersa de la anterior mediante grasp
			
			boolean entra = true;
			
			for (int i = 0; i < poblacion.size(); i++) {
				if (poblacion.get(i).getPermutacion().equals(s.getPermutacion())) {
					entra = false;
					break;
				}
			}
			
			if (entra)
				poblacion.add(s);
		}
		
		return poblacion;
	}
	
	
	/**
	 * Metodo que crea un conjunto de referencia.
	 * Un conjunto de referencia es un nuevo conjunto creado a partir de la población
	 * con soluciones de alta calidad y dispersas.
	 * 
	 * @param tamRS
	 * @param pobAux
	 * 
	 * @return ArrayList<Solucion> - Array de soluciones
	 */
	private ArrayList<Solucion> GenerarReferenceSet(int tamRS, ArrayList<Solucion> pobAux) {
		ArrayList<Solucion> refset = new ArrayList<Solucion>();
		
		int r1 = tamRS / 2;
		int r2 = r1;
		int pos = 0;
		
		// Mejores soluciones
		while (r1 > 0) {
			pos = 0;
			Solucion s = pobAux.get(pos);
			
			for (int i = 0; i < pobAux.size(); i++) {
				if (pobAux.get(i).compareTo(s) < 0) {
					pos = i;
					s = pobAux.get(i);
				} else if (pobAux.get(i).compareTo(s) == 0) {
					if (pobAux.get(i).getAreaTotalMU() < s.getAreaTotalMU()) {
						pos = i;
						s = pobAux.get(i);
					}
				}
				
			}
			
			r1--;
			refset.add(s);
			pobAux.remove(pos);
		}
		
		// Mas dispersas 
		while (r2 > 0) {
			pos = pobAux.size() - 1;
			Solucion s = pobAux.get(pos);
			
			for (int i = 0; i < pobAux.size(); i++) {
				if (pobAux.get(i).compareTo(s) > 0) {
					pos = i;
					s = pobAux.get(i);
				} else if (pobAux.get(i).compareTo(s) == 0) {
					if (pobAux.get(i).getAreaTotalMU() > s.getAreaTotalMU()) {
						pos = i;
						s = pobAux.get(i);
					}
				}
			}
			
			r2--;
			refset.add(s);
			pobAux.remove(pos);
		}
		
		return refset;
	}
	
	
	/**
	 * Metodo que crea un subconjunto.
	 * Selecciona todos los subcojuntos de soluciones
	 * del conjunto de referencia para realizar buenas combinaciones.
	 * 
	 * @param RefSet
	 * @param x
	 * @param y
	 * 
	 * @return ArrayList<Solucion> - Array de soluciones
	 */
	private ArrayList<Solucion> SeleccionarSubconjunto(ArrayList<Solucion> RefSet, int x, int y) {
		ArrayList<Solucion> subconjunto = new ArrayList<Solucion>();
		
		subconjunto.add(RefSet.get(x));
		subconjunto.add(RefSet.get(y));
		
		return subconjunto;
	}
	
	
	/**
	 * Metodo que combina los subconjuntos de soluciones seleccionadas
	 * del conjunto de referencia para obtener una nueva solucion.
	 * 
	 * @param subconjunto
	 * 
	 * @return Solucion - Nueva solucion combinada
	 */
	private Solucion CombinarSoluciones(ArrayList<Solucion> subconjunto) {
		int perm0[] = subconjunto.get(0).getPermutacion();
		int perm1[] = subconjunto.get(1).getPermutacion();
		int permutacion[] = new int[perm0.length];
		
		for(int i = 0; i < perm1.length; i++) {
			permutacion[i] = perm1.length;
		}
		
		boolean dentro0 = false;
		boolean dentro1 = false;
		boolean problema = false;
		
		do {
			problema = false;
			
			for(int i = 0; i < perm1.length; i++) {
				// Busca si la primera esta dentro ya
				for (int x = 0; x < perm1.length; x++) {
					dentro0 = permutacion[x] == perm0[i];
						
					if (dentro0) {
						break;
					}
				}
				
				// Busca si la segunda esta dentro ya
				for (int x = 0; x < perm1.length; x++) {
					dentro1 = permutacion[x] == perm1[i];
						
					if (dentro1) {
						break;
					}
				}
				
				// si estan las dos hay un problema
				if (dentro0 && dentro1) {
					problema = true;
				} else {
					if (dentro0) {
						permutacion[i] = perm1[i];
					} else if (dentro1) {
						permutacion[i] = perm0[i];
					} else {
						if (Problema.getRectangulos().get(perm0[i]).getArea() > 
							Problema.getRectangulos().get(perm1[i]).getArea()) {
							permutacion[i] = perm0[i];
						} else {
							permutacion[i] = perm1[i];
						}
					}
				}
			}
			
			for(int i = 0; i < perm1.length; i++) {
				if (permutacion[i] == perm1.length) {
					problema = true;
					break;
				} else {
					problema = false;
				}
			}
		} while (problema);
		
		return new Solucion(permutacion, Problema.getRectangulos(), Problema.getAltoCaja(),
				Problema.getAnchoCaja(), Solucion.FINITE);
	}
	
	
	/**
	 * Metodo que se encarga de intercambiar elementos para 
	 * 
	 * @param array - Array que contiene la permutacion.
	 * @param posOne - Posicion del primer elemento a intercambiar.
	 * @param posTwo - Posicion del segundo elemento a intercambiar.
	 */
	private void swap(int array[], int posOne, int posTwo) {
		int temp = array[posTwo];
		array[posTwo] = array[posOne];
		array[posOne] = temp;
	}
	
	
	/**
	 * Metodo que devuelve la solucion del problema
	 * 
	 * @return Solucion - Solucion del problema.
	 */
	public Solucion getSolucion() {
		return MejorSolucion;
	}
	
	
	/**
	 * Metodo para resumir la informacion de una heuristica en una cadena de caracteres.
	 * 
	 * @return String - Mejor solucion de una heuristica.
	 */
	public String toString() {
		return new String(MejorSolucion + "\n");
	}
}
