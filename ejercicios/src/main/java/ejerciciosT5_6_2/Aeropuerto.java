package ejerciciosT5_6_2;

public class Aeropuerto {
	String nombre;
	String ciudad;
	int capacidad;
	public Aeropuerto(String nombre, String ciudad, int capacidad) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.capacidad = capacidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	@Override
	public String toString() {
		return "Aeropuerto [nombre=" + nombre + ", ciudad=" + ciudad + ", capacidad=" + capacidad + "]";
	}
	
}
