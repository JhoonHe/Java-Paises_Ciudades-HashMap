import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Procesos {

	HashMap<String, ArrayList<String>> mapaPaises;
	ArrayList<String> listaCiudades;

	public Procesos() {
		mapaPaises = new HashMap<String, ArrayList<String>>();
		iniciar();
	}

	private void iniciar() {

		String menu = obtenerMenu();

		int opcion = 0;

		do {
			opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
			validarOpcion(opcion);
		} while (opcion != 5);

		JOptionPane.showMessageDialog(null, "Gracias por usar el programa.");
	}

	private String obtenerMenu() {

		String menu = "";

		menu += "***** Bienvenido *****";
		menu += "\n\nIngrese la opcion:\n";
		menu += "\n1- Registrar pais.";
		menu += "\n2- Registrar ciudades.";
		menu += "\n3- Consultar por pais la lista de ciudades asociadas.";
		menu += "\n4- Consultar ciudad.";
		menu += "\n5- Salir.\n\n";

		return menu;
	}

	private void validarOpcion(int opcion) {

		switch (opcion) {

		case 1:
			registrarPaises();
			break;

		case 2:
			if (!mapaPaises.isEmpty()) {
				registrarCiudades();
				break;
			} else {
				JOptionPane.showMessageDialog(null, "No se encuentran paises registrados");
				break;
			}

		case 3:
			if (!mapaPaises.isEmpty()) {
				consultarPais();
				break;
			} else {
				JOptionPane.showMessageDialog(null, "No se encuentran paises registrados");
				break;
			}

		case 4:
			if (!mapaPaises.isEmpty()) {
				consultarCiudad();
			} else {
				JOptionPane.showMessageDialog(null, "No se encuentran paises registrados");
				break;
			}

		case 5:
			break;

		default:
			JOptionPane.showMessageDialog(null, "Opcion incorrecta");

		}
	}

	private void consultarCiudad() {

		ArrayList<String> temporal;

		String ciudad = "";
		String mensaje = "";

		boolean registrado = false;

		ciudad = JOptionPane.showInputDialog("Ingrese la ciudad a consultar: ").toLowerCase();

		for (String pais : mapaPaises.keySet()) {

			temporal = mapaPaises.get(pais);

			if (temporal.contains(ciudad)) {
				mensaje += "La ciudad " + ciudad + " pertenece al pais: " + pais + "\n";
				registrado = true;
			}
		}

		if (registrado) {
			JOptionPane.showMessageDialog(null, mensaje);
		} else {
			JOptionPane.showMessageDialog(null,
					"La ciudad ingresada no se encuentra en el sistema o no pertenece a un pais registrado");
		}
	}

	private void consultarPais() {

		String pais = "";
		ArrayList<String> temporal;
		String mensaje = "";
		String menu = menuPaises();

		pais = JOptionPane.showInputDialog(menu + "Ingrese el nombre del pais a consultar: \n\n").toLowerCase();

		mensaje += "**** Ciudades de " + pais + " ****\n";

		if (mapaPaises.containsKey(pais)) {

			temporal = mapaPaises.get(pais);

			if (!temporal.isEmpty()) {

				for (int i = 0; i < temporal.size(); i++) {
					mensaje += "\n- " + temporal.get(i);
				}

				JOptionPane.showMessageDialog(null, mensaje);

			} else {
				JOptionPane.showMessageDialog(null, "El pais " + pais + " no tiene ciudades registradas");
			}

		} else {
			JOptionPane.showMessageDialog(null, "El pais ingresado no se encuentra en el sistema");
		}

	}

	private void registrarPaises() {

		int cantidad = 0;
		String pais = "";

		do {
			cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de paises a registrar"));
		} while (cantidad < 0);

		for (int i = 0; i < cantidad; i++) {

			do {
				pais = JOptionPane.showInputDialog("Ingrese el nombre del pais " + (i + 1)).toLowerCase();
			} while (mapaPaises.containsKey(pais));

			mapaPaises.put(pais, listaCiudades = new ArrayList<String>());
		}
	}

	private void registrarCiudades() {

		String menu = menuPaises();
		String pais = "";
		int ciudades = 0;
		String ciudad = "";

		pais = JOptionPane.showInputDialog("**** Registrar ciudades ****" + menu + "Ingrese el nombre del pais: \n\n")
				.toLowerCase();

		if (mapaPaises.containsKey(pais)) {
			do {
				ciudades = Integer.parseInt(
						JOptionPane.showInputDialog("Ingrese la cantidad de ciudades a registrar del pais " + pais));
			} while (ciudades < 0);

			ArrayList<String> listaCiudadesPais = mapaPaises.get(pais);

			for (int i = 0; i < ciudades; i++) {
				do {
					ciudad = JOptionPane
							.showInputDialog("Ingrese la ciudad " + (i + 1) + " que pertenece al pais " + pais)
							.toLowerCase();
				} while (listaCiudadesPais.contains(ciudad));

				listaCiudadesPais.add(ciudad);
			}
		} else {
			JOptionPane.showMessageDialog(null, "El pais " + pais + " no se encuentra en el sistema");
		}

	}

	private String menuPaises() {

		String menu = "";

		menu += "\n\nPaises registrados:\n\n";

		for (String llaves : mapaPaises.keySet()) {
			menu += "- " + llaves + "\n";
		}

		menu += "\n";

		return menu;
	}

}
