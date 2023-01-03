package ejerciciosT5_6_2;

import java.util.HashMap;
import java.util.Map;

public class Controlador {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Aeropuerto> aeropuertos = new HashMap<String, Aeropuerto>();

		addAeropuerto(aeropuertos, new Aeropuerto("Barajas", "Madrid", 10));
		Aeropuerto prat = new Aeropuerto("El Prat", "Barcelona", 15);
		Aeropuerto castellon = new Aeropuerto("Castellon", "Castellon", 20);
		

		aeropuertos.put("Castellon", castellon);
		aeropuertos.put("El prat", prat);

		System.out.println(aeropuertos.get("Barajas"));

	}

	public static void addAeropuerto(Map<String, Aeropuerto> map, Aeropuerto a) {
		System.out.println(a.getNombre());
		map.put(a.getNombre(), a);
	}
}
