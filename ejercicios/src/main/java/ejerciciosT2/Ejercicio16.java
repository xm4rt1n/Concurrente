package ejerciciosT2;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejercicio16 {
	private static final int N_FRAGMENTOS = 10;
	private static final int N_FICHEROS = 10;
	private static final int N_HILOS = 3;
	private static volatile int[] fichero = new int[N_FRAGMENTOS];
	private static SimpleSemaphore nDescargados;
	private static SimpleSemaphore nAcabados;

	// Add the attributes you need
	private static volatile int nDescargas;
	private static volatile int nTerminados;
	private static volatile boolean mostrar;

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
			if (nDescargas == N_FRAGMENTOS) {
				nDescargados.release();
				break;
			}
			int i = nDescargas;
			nDescargas++;
			nDescargados.release();
			int n = descargaDatos(i);
			printlnI("Descargando el fragmento: " + i);
			fichero[i] = n;
			printlnI("Guardando el fragmento: " + i);

			nAcabados.acquire();
			nTerminados++;
			mostrar = nTerminados == N_FRAGMENTOS;
			nAcabados.release();
			if (mostrar) {
				mostrarFichero();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		nDescargas = 0;
		nTerminados = 0;
		nDescargados = new SimpleSemaphore(1);
		nAcabados = new SimpleSemaphore(1);
		createThreads(N_HILOS, "downloader");
		startThreadsAndWait();
	}

}