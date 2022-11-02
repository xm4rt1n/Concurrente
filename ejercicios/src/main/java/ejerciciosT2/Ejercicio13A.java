package ejerciciosT2;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class Ejercicio13A {
	private static final int NUM_TRENES = 5;
	private static SimpleSemaphore tA;
	private static SimpleSemaphore tB;
	private static SimpleSemaphore tC;

	public static void tren(int numTren) {
		sleepRandom(500);
		tA.acquire();
		recorrerTramoA(numTren);
		sleepRandom(500);
		tB.acquire();
		tA.release();
		recorrerTramoB(numTren);
		tC.acquire();
		tB.release();
		sleepRandom(500);
		recorrerTramoC(numTren);
		tC.release();
	}

	private static void recorrerTramoA(int numTren) {
		printlnI("Entra TA T" + numTren);
		sleepRandom(500);
		printlnI("Sale TA T" + numTren);
	}

	private static void recorrerTramoB(int numTren) {
		printlnI("Entra TB T" + numTren);
		sleepRandom(500);
		printlnI("Sale TB T" + numTren);
	}

	private static void recorrerTramoC(int numTren) {
		printlnI("Entra TC T" + numTren);
		sleepRandom(500);
		printlnI("Sale TC T" + numTren);
	}

	public static void main(String[] args) {
		tA = new SimpleSemaphore(1);
		tB = new SimpleSemaphore(1);
		tC = new SimpleSemaphore(1);

		for (int i = 0; i < NUM_TRENES; i++) {
			createThread("tren", i);
		}
		startThreadsAndWait();

	}

}
