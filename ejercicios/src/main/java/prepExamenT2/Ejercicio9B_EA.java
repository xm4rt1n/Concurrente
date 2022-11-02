package prepExamenT2;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejercicio9B_EA {

	private static final int N_FRAGMENTOS = 10;
	private static final int N_HILOS = 3;
	private static volatile int[] fichero = new int[N_FRAGMENTOS]; // Add the attributes you need private static int
	private static volatile int posicion;
	private static volatile int guardados;

	private static int descargaDatos(int numFragmento) {
		sleepRandom(1000);
		return numFragmento * 2;
	}

	private static void mostrarFichero() {
		println("--------------------------------------------------");
		print("File = [");
		for (int i = 0; i < N_FRAGMENTOS; i++) {
			print(fichero[i] + ",");
		}
		println("]");
	}

	public static void downloader() {
		while (true) {
			enterMutex("posicion");
			if (posicion == N_FRAGMENTOS) {
				exitMutex("posicion");
				break;
			}
			int x = posicion;
			posicion++;
			exitMutex("posicion");
			printlnI("Descargando el ficharo: " + x);
			fichero[x] = descargaDatos(x);
			enterMutex("guardados");
			guardados++;
			boolean mostrar = (guardados == N_FRAGMENTOS);
			exitMutex("guardados");
			if (mostrar) {
				mostrarFichero();
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		posicion = 0;
		guardados = 0;
		createThreads(N_HILOS, "downloader");
		startThreadsAndWait();

	}

}
