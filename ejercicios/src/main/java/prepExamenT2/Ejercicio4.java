package prepExamenT2;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejercicio4 {
	public static boolean pedido;
	public static boolean sumado;
	public static volatile int peticion;
	public static volatile int respuesta;

	public static void servidor() {
		while (true) {
			while (!pedido);
			pedido = false;
			respuesta = peticion + 1;
			sumado = true;
		}
	}

	public static void cliente() {
		while (true) {
			peticion = (int) (Math.random() * 10);
			pedido = true;
			while (!sumado);
			sumado=false;
			print("El nunero es " + respuesta);
		}
	}

	public static void main(String[] args) {
		pedido = false;
		sumado = false;
		createThread("cliente");
		createThread("servidor");

		startThreadsAndWait();
	}
}
