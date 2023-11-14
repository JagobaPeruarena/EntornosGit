/**
 * 
 */
package clases;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.crypto.Data;

public class Alimentos {
	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("datos/janariak.txt");
		String[] nombres = new String[404];
		String[] situaciones = new String[404];
		double[] calorias = new double[404];
		double[] grasas = new double[404];
		double[] proteinas = new double[404];
		double[] carbohidratos = new double[404];
		String[] tipos = new String[404];
		String[] partes;
		String tipoR;
		double totalCal = 0;
		int cantAli = 0;
		int i = 0;
		int opcion = 1;
		String nombreR = "";
		String situacionR = "";
		Scanner scanf = new Scanner(file);
		String respStri = "";
		double respDouble = 0;
		int respInt = 0;
		int cont2 = 0;
		//CAmbios en line
		//Cambios en local
		leerFichero(nombres, situaciones, calorias, grasas, proteinas, carbohidratos, tipos, i, scanf);

		Scanner scan = new Scanner(System.in);

		while (opcion != 0 && opcion != 9) {
			llamar_menu();
			opcion = Integer.parseInt(scan.nextLine());

			switch (opcion) {

			case 1:
				System.out.println("Has escogido tipo alimento");
				System.out.println("Di un tipo de alimento");
				tipoR = scan.nextLine();

				alimentosDeUnTipo(nombres, tipos, tipoR);
				break;

			case 2:

				System.out.println("Has escogido media de calorias por tipo");
				System.out.println("Di un tipo de alimento");
				tipoR = scan.nextLine();
				totalCal = 0;

				totalCal = caloriasDeUnTipo(calorias, tipos, tipoR, totalCal);
				System.out.println("Las calorias totales son " + totalCal);
				break;

			case 3:

				System.out.println("Has escogido cantidad de un tipo de alimento");
				System.out.println("Di un tipo de alimento");
				tipoR = scan.nextLine();
				cantAli = 0;

				cantAli = cantidadDeUnTipo(tipos, tipoR, cantAli);
				System.out.println("Hay un total de " + cantAli + " alimentos");
				break;

			case 4:

				System.out.println("Has escogido valor nutricional en un estado");
				System.out.println("Di un nombre de alimento");
				nombreR = scan.nextLine();
				System.out.println("Di el estado del alimento");
				situacionR = scan.nextLine();

				buscarYMostrarValores(nombres, situaciones, calorias, grasas, proteinas, carbohidratos, nombreR,
						situacionR, scan, false);
				break;

			case 5:

				System.out.println("Has escogido valor nutricional");
				System.out.println("Di un nombre de alimento");
				nombreR = scan.nextLine();

				valorNutricionalAlimento(nombres, situaciones, calorias, grasas, proteinas, carbohidratos, nombreR);
				break;

			case 6:

				System.out.println("Opcion mostrar calorias y posibilidad de editar");
				System.out.println("Di un nombre de alimento");
				nombreR = scan.nextLine();
				System.out.println("Di el estado del alimento");
				situacionR = scan.nextLine();

				verYEditarCalorias(nombres, situaciones, calorias, nombreR, situacionR, scan);
				break;
			case 7:

				System.out.println("Opcion mostrar calorias y posibilidad de editar");
				System.out.println("Di un nombre de alimento");
				nombreR = scan.nextLine();
				System.out.println("Di el estado del alimento");
				situacionR = scan.nextLine();
				buscarYMostrarValores(nombres, situaciones, calorias, grasas, proteinas, carbohidratos, nombreR,
						situacionR, scan, true);

				break;

			case 8:
				System.out.println(
						"Escoja una opcion: \n 1.Opcion Listar frutos secos\n 2.Opcion Listar alimentos con mas de 200 calorias \n 3.Opcion Por cada tipo de alimento cuantos ahi \n 4.Opcion Valor nutricional de cada tipo");
				respInt = Integer.parseInt(scan.nextLine());

				switch (respInt) {

				case 1:
					System.out.println("Lista de frutos secos");
					frutosSecos(nombres, situaciones, tipos);
					break;

				case 2:
					System.out.println("Lista de alimentos con mas de 200 calorias");
					alimentosMayor200(nombres, situaciones, calorias);
					break;

				case 3:
					System.out.println("Por cada tipo su cantidad de alimentos");
					cont2 = 1;

					cont2 = cantidadPorTipo(tipos, cont2);

					break;

				case 4:
					System.out.println("Por cada tipo la media de valores");

					cont2 = mediaPorCadaTipo(calorias, grasas, proteinas, carbohidratos, tipos);
					

					break;
				default:
					break;
				}

				break;
			case 9:
				System.out.println("Dame el nombre del alimento que deseas\n");

				respStri = scan.nextLine();

				System.out.println("Dame la situación del alimento que deseas\n");

				String l = scan.nextLine();

				for (int cont = 0; cont < tipos.length; cont++) {

					if (nombres[cont].equalsIgnoreCase(respStri) && situaciones[cont].equalsIgnoreCase(l)) {

						eliminarDatos(nombres, situaciones, calorias, grasas, proteinas, carbohidratos,

								tipos, scan, cont);

						cont2++;

					}

				}

				if (cont2 == 0) {

					System.out.println("No me has dado un alimento correcto\n");

				}

				cont2 = 0;

				break;
			case 0:
				System.out.println("Quieres guardar? S/N");
				respStri = scan.nextLine();
				if (respStri.equalsIgnoreCase("S")) {
					guardar(nombres, situaciones, calorias, grasas, proteinas, carbohidratos, tipos);
				} else {
					System.out.println("Adiossss");
				}

				break;

			}
		}
	}

	//Esta funcion guarda los datos en un fichero
	private static void guardar(String[] nombres, String[] situaciones, double[] calorias, double[] grasas,
			double[] proteinas, double[] carbohidratos, String[] tipos) {
		try {
			FileWriter myWriter = new FileWriter("datos/janariak.txt");
			System.out.println("Archivo nuevo creado: ");
			for (int j = 0; j < nombres.length; j++) {
				myWriter.write(nombres[j] + ";" + situaciones[j] + ";" + calorias[j] + ";" + grasas[j] + ";"
						+ proteinas[j] + ";" + carbohidratos[j] + ";" + tipos[j] + "\n");
			}
			myWriter.close();
			System.out.println("Archivo nuevo correctamente rellenado.");
			System.out.println("Adiosssss");

		} catch (Exception e) {
			System.out.println("Error al cambiar");
		}
	}

	
	//Esta funcion dice la media de el tipo que le introduzcas
	private static int mediaPorCadaTipo(double[] calorias, double[] grasas, double[] proteinas, double[] carbohidratos,
			String[] tipos) {
		int cont2;
		cont2 = 1;
		double calTotal = 0;
		double grasTotal = 0;
		double proteTotal = 0;
		double carboTotal = 0;
		
		for (int cont = 0; cont < tipos.length - 1; cont++) {

			calTotal = calTotal + calorias[cont];
			grasTotal = grasTotal + grasas[cont];
			carboTotal = carboTotal + carbohidratos[cont];
			proteTotal = proteTotal + proteinas[cont];

			if (tipos[cont].equalsIgnoreCase(tipos[cont + 1])) {

				cont2 = cont2 + 1;

			}

			else {

				System.out.println(
						"El tipo de alimento llamado " + tipos[cont] + " tiene la media de: Calorias:"
								+ calTotal / cont2 + "; Grasas:" + grasTotal / cont2 + "; Proteinas:"
								+ proteTotal / cont2 + "; Carbohidratos:" + carboTotal / cont2);
				calTotal = 0;
				carboTotal = 0;
				grasTotal = 0;
				proteTotal = 0;
				cont2 = 1;

			}

		}

		System.out.println("El tipo de alimento llamado frutos secos tiene la media de: Calorias:"
				+ calTotal / cont2 + "; Grasas:" + grasTotal / cont2 + "; Proteinas:" + proteTotal / cont2
				+ "; Carbohidratos:" + carboTotal / cont2);
		return cont2;
	}

	//Muestra los frutos secos
	private static void frutosSecos(String[] nombres, String[] situaciones, String[] tipos) {
		for (int j = 0; j < nombres.length; j++) {

			if (tipos[j].equals("fruto seco")) {
				System.out.println(nombres[j] + "," + situaciones[j]);
			}
		}
	}

	//Muestra los alimentos con calorias superiores a 200
	private static void alimentosMayor200(String[] nombres, String[] situaciones, double[] calorias) {
		for (int j = 0; j < nombres.length; j++) {

			if (calorias[j] > 200) {
				System.out.println(nombres[j] + "," + situaciones[j]);
			}
		}
	}
	//Muestra la cantidad de alimentos por tipo
	private static int cantidadPorTipo(String[] tipos, int cont2) {
		for (int cont = 0; cont < tipos.length - 1; cont++) {

			if (tipos[cont].equalsIgnoreCase(tipos[cont + 1])) {

				cont2 = cont2 + 1;

			}

			else {

				System.out.println(
						"El tipo de alimento llamado " + tipos[cont] + " tiene " + cont2 + " registros");

				cont2 = 1;

			}

		}

		System.out.println("El tipo de alimento llamado frutos secos tiene " + cont2 + " registros");
		return cont2;
	}
	//Mostrar y dar la posibilidad de calorias
	private static void verYEditarCalorias(String[] nombres, String[] situaciones, double[] calorias, String nombreR,
			String situacionR, Scanner scan) {
		String respStri;
		double respDouble;
		for (int j = 0; j < nombres.length; j++) {

			if (nombres[j].equals(nombreR) && situaciones[j].equals(situacionR)) {
				System.out.println("Las calorias de este alimento es de: Calorias(" + calorias[j] + ")");
				System.out.println("Quieres editar las calorias? S/N");
				respStri = scan.nextLine();

				if (respStri.equalsIgnoreCase("S")) {
					System.out.println("Di el nuevo valor");
					respDouble = Double.parseDouble(scan.nextLine());
					calorias[j] = respDouble;
				} else {
					System.out.println("Saliendo al menu");
				}
			}
		}
	}
	//Muestra el valor nutricional de cada alimento
	private static void valorNutricionalAlimento(String[] nombres, String[] situaciones, double[] calorias,
			double[] grasas, double[] proteinas, double[] carbohidratos, String nombreR) {
		for (int j = 0; j < nombres.length; j++) {

			if (nombres[j].equals(nombreR)) {
				System.out.println("El valor nutricional de este alimento en el estado:" + situaciones[j]
						+ " es de: Calorias(" + calorias[j] + "); Grasas(" + grasas[j] + "); Proteinas(" + proteinas[j]
						+ "); Carbohidratos(" + carbohidratos[j] + ")");
			}
		}
	}
	//Devuelve la tandidad de alimentos por ese tipo
	private static int cantidadDeUnTipo(String[] tipos, String tipoR, int cantAli) {
		for (int j = 0; j < tipos.length; j++) {
			if (tipos[j].equals(tipoR)) {
				cantAli++;
			}
		}
		return cantAli;
	}
	//Devuelve la cantidad de calorias de un tipo
	private static double caloriasDeUnTipo(double[] calorias, String[] tipos, String tipoR, double totalCal) {
		for (int j = 0; j < tipos.length; j++) {
			if (tipos[j].equals(tipoR)) {
				totalCal += calorias[j];
			}
		}
		return totalCal;
	}
	//Devuelve la cantidad de alimentos de un tipo
	private static void alimentosDeUnTipo(String[] nombres, String[] tipos, String tipoR) {
		for (int j = 0; j < tipos.length; j++) {

			if (tipos[j].equals(tipoR)) {
				System.out.println(nombres[j]);
			}
		}
	}
	//Lee el fichero y lo almacena en arrays
	private static void leerFichero(String[] nombres, String[] situaciones, double[] calorias, double[] grasas,
			double[] proteinas, double[] carbohidratos, String[] tipos, int i, Scanner scanf) {
		String[] partes;
		while (scanf.hasNextLine()) {
			String linea = scanf.nextLine();
			partes = linea.split(";");
			nombres[i] = partes[0];

			situaciones[i] = partes[1];
			calorias[i] = Double.parseDouble(partes[2]);
			grasas[i] = Double.parseDouble(partes[3]);
			proteinas[i] = Double.parseDouble(partes[4]);
			carbohidratos[i] = Double.parseDouble(partes[5]);
			tipos[i] = partes[6];
			i++;
		}
	}
	//Busca un alimento y muestra sus valores
	private static void buscarYMostrarValores(String[] nombres, String[] situaciones, double[] calorias,
			double[] grasas, double[] proteinas, double[] carbohidratos, String nombreR, String situacionR,
			Scanner scan, boolean cambiar) {
		double respDouble;
		int respInt;
		for (int j = 0; j < nombres.length; j++) {
			if (nombres[j].equals(nombreR) && situaciones[j].equals(situacionR)) {
				System.out.println("El valor nutricional de este alimento en el estado:" + situaciones[j]
						+ " es de: Calorias(" + calorias[j] + "); Grasas(" + grasas[j] + "); Proteinas(" + proteinas[j]
						+ "); Carbohidratos(" + carbohidratos[j] + ")");
				if (cambiar) {
					System.out.println(
							"Que valor quieres editar \n 1.Calorias \n 2.Grasas \n 3.Proteinas \n 4.Carbohidratos");
					respInt = Integer.parseInt(scan.nextLine());

					switch (respInt) {

					case 1:
						System.out.println("Di el nuevo valor de calorias");
						respDouble = Double.parseDouble(scan.nextLine());
						calorias[j] = respDouble;
						break;

					case 2:
						System.out.println("Di el nuevo valor de grasas");
						respDouble = Double.parseDouble(scan.nextLine());
						grasas[j] = respDouble;
						break;

					case 3:
						System.out.println("Di el nuevo valor de proteinas");
						respDouble = Double.parseDouble(scan.nextLine());
						proteinas[j] = respDouble;
						break;

					case 4:
						System.out.println("Di el nuevo valor de carbohidratos");
						respDouble = Double.parseDouble(scan.nextLine());
						carbohidratos[j] = respDouble;
						break;

					default:
						System.out.println("Saliendo al menu");
					}
				}

			}
		}
	}
	//Imprime un menu
	private static void llamar_menu() {
		System.out.println("--MENU--");
		System.out.println("Opcion 1: Alimentos de un tipo");
		System.out.println("Opcion 2: Media de calorias de un tipo");
		System.out.println("Opcion 3: Cantidad de un tipo");
		System.out.println("Opcion 4: Valores nutritipos del alimento en una situacion");
		System.out.println("Opcion 5: Valores nutritibos de un alimento");
		System.out.println("Opcion 6: Mostrar calorias y posibilidad de editar");
		System.out.println("Opcion 7: Mostrar valores y posibilidad de editar");
		System.out.println("Opcion 8: Lista de Selects");
		System.out.println("Opcion 9: Eliminar datos");
		System.out.println("Opcion 0: SALIR y salir como opcion");
	}
	//Elimina un alimento
	private static void eliminarDatos(String[] nombres, String[] situaciones, double[] calorias, double[] grasas,

			double[] proteinas, double[] carbohidratos, String[] tipos, Scanner sn2, int cont) {

		String k;

		System.out.println(tipos[cont] + ":\n"

				+ "Calorias: " + calorias[cont] + "\n"

				+ "Proteinas: " + proteinas[cont] + "\n"

				+ "Grasas: " + grasas[cont] + "\n"

				+ "Carbohidratos: " + carbohidratos[cont] + "\n"

				+ "¿Quieres eliminar los valores? (S/N)\n");

		k = sn2.nextLine();

		if (k.equalsIgnoreCase("s")) {

			nombres[cont] = null;

			situaciones[cont] = null;

			calorias[cont] = -1;

			grasas[cont] = -1;

			proteinas[cont] = -1;

			carbohidratos[cont] = -1;

			tipos[cont] = null;

			System.out.println("Se ha eliminado correctamente\n");

		}

		else {

			System.out.println("Se cancela la solicitud de eliminar datos\n");

		}

	}
}
