package ejerciciosT2;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ej9 {
	private static final int N_FRAGMENTOS = 10;
	private static final int N_HILOS = 3;
	private static volatile int[] fichero = new int[N_FRAGMENTOS];
	private static SimpleSemaphore nDescargados;

	// Add the attributes you need
	private static int nD;

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
			nDescargados.acquire();
			if (nD == N_FRAGMENTOS) {
				nDescargados.release();
				break;
			}
			int i = nD;
			nD++;
			nDescargados.release();
			int n = descargaDatos(i);
			printlnI("Descargando el fragmento: " + i);
			fichero[i] = n;
			printlnI("Guardando el fragmento: " + i);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		nD = 0;
		nDescargados = new SimpleSemaphore(1);
		createThreads(N_HILOS, "downloader");
		startThreadsAndWait();
		mostrarFichero();
	}

}
