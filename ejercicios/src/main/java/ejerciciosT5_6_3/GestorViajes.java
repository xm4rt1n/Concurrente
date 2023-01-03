package ejerciciosT5_6_3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GestorViajes {
	List<Viaje> viajes;
	Map<String, List<Viaje>> ciudadesDestino;
	Map<String, List<Viaje>> ciudadesOrigen;
	Set<String> ciudades;

	public GestorViajes() {
		viajes = new ArrayList<Viaje>();
		ciudadesDestino = new HashMap<String, List<Viaje>>();
		ciudadesOrigen = new HashMap<String, List<Viaje>>();
		ciudades = new HashSet<String>();
	}

	public void addViaje(Viaje v) {
		viajes.add(v);
		put(ciudadesOrigen, v.getOrigen(), v);
		put(ciudadesDestino, v.getDestino(), v);
		ciudades.add(v.getDestino());
		ciudades.add(v.getOrigen());
	}

	public void put(Map<String, List<Viaje>> mapa, String ciudad, Viaje v) {
		List<Viaje> viajes = mapa.get(ciudad);
		if (viajes == null) {
			viajes = new ArrayList<Viaje>();
			mapa.put(ciudad, viajes);
		}
		viajes.add(v);
	}

	public Collection<Viaje> getViajesPorOrigen(String origen) {
		return ciudadesOrigen.get(origen);
	}

	public Collection<Viaje> getViajesPorDestino(String destino) {
		return ciudadesDestino.get(destino);
	}

	public Collection<Viaje> getViajes() {
		return viajes;
	}

	public Collection<String> getCiudades() {
		return ciudades;
	}
}
