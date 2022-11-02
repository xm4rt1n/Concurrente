package ejerciciosT2;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.createThread;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.printlnI;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.sleepRandom;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.startThreadsAndWait;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class Ejercicio13B {
	private static final int NUM_TRENES = 3;
	private static final int NUM_TRAMOS = 3;

	private static SimpleSemaphore t[] = new SimpleSemaphore[NUM_TRAMOS];

	public static void tren(int numTren) {
		for (int i = 0; i < NUM_TRAMOS; i++) {
			t[i].acquire();
			if (i != 0) {
				t[i - 1].release();
			}
			recorrerTramo(numTren, i);
		}
		t[NUM_TRAMOS - 1].release();
	}

	private static void recorrerTramo(int numTren, int numTramo) {
		printlnI("Entra al tramo " + numTramo + ", el tren " + numTren);
		sleepRandom(500);
		printlnI("Sale del tramo " + numTramo + ", el tren " + numTren);
	}

	public static void main(String[] args) {
		for (int j = 0; j < NUM_TRAMOS; j++) {
			t[j] = new SimpleSemaphore(1);
		}
		for (int i = 0; i < NUM_TRENES; i++) {
			createThread("tren", i);
		}
		startThreadsAndWait();

	}

}
