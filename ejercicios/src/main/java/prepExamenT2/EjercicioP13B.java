package prepExamenT2;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class EjercicioP13B {
	private static final int NUM_TRENES = 5;
	private static final int NUM_TRAMOS = 5;
	private static SimpleSemaphore[] semaforo = new SimpleSemaphore[NUM_TRAMOS];

	public static void tren(int numTren) {
		for (int i = 0; i < NUM_TRAMOS; i++) {
			semaforo[i].acquire();
			if (i != 0) {
				semaforo[i - 1].release();
			}
			recorrerTramo(numTren, i);
		}semaforo[NUM_TRAMOS - 1].release();
	}

	private static void recorrerTramo(int numTren, int numTramo) {
		printlnI("Entra al tramo: " + numTramo + " el tren T" + numTren);
		sleepRandom(500);
		printlnI("sale del tramo: " + numTramo + " el tren T" + numTren);
	}

	public static void main(String args[]) {
		for (int j = 0; j < NUM_TRAMOS; j++) {
			semaforo[j] = new SimpleSemaphore(1);
		}

		for (int i = 0; i < NUM_TRENES; i++) {
			createThread("tren", i);
		}
		startThreadsAndWait();
	}
}