package ejerciciosT5_6_3;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestorViajes gv = new GestorViajes();

		gv.addViaje(new Viaje("Madrid", "Barcelona", 4));
		gv.addViaje(new Viaje("Sevilla", "Madrid", 5));
		gv.addViaje(new Viaje("Toledo", "Barcelona", 10));
		gv.addViaje(new Viaje("Madrid", "Córdoba", 7));
		gv.addViaje(new Viaje("Barcelona", "Burgos", 9));

		System.out.println("Ciudades en las que hay viajes:");
		System.out.println(gv.getCiudades());

		System.out.println("Viajes desde Madrid:");
		System.out.println(gv.getViajesPorOrigen("Madrid"));

		System.out.println("Viajes hacia Barcelona:");
		System.out.println(gv.getViajesPorDestino("Barcelona"));

		System.out.println("Viajes:");
		System.out.println(gv.getViajes());

	}

}
