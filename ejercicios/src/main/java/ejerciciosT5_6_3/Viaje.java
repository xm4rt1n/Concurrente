package ejerciciosT5_6_3;

public class Viaje {
	String origen;
	String destino;
	int duracion;

	public Viaje(String origen, String destino, int duracion) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.duracion = duracion;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "Viaje [origen=" + origen + ", destino=" + destino + ", duracion=" + duracion + "]";
	}

}
