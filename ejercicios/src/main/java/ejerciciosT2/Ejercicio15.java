package ejerciciosT2;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.*;

public class Ejercicio15 {
	public static final int N_PROCESOS = 4;
	public static volatile int nProcesos;
	public static SimpleSemaphore emNP;
	public static SimpleSemaphore continuar;
	public static SimpleSemaphore desbloqueo;

	public static void sync() {
		emNP.acquire();
		nProcesos++;
		if (nProcesos < 4) {
			emNP.release();
			continuar.acquire();
			desbloqueo.release();
			
		} else {
			nProcesos = 0;
			println("-");
			continuar.release(3);
			desbloqueo.acquire(3);
			emNP.release();
			
		}
	}

	public static void letraA() {
		while (true) {
			println("A");
			sync();
		}
	}

	public static void letraB() {
		while (true) {
			println("B");
			sync();
		}
	}

	public static void letraC() {
		while (true) {
			println("C");
			sync();
		}
	}

	public static void letraD() {
		while (true) {
			println("D");
			sync();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		nProcesos = 0;
		emNP = new SimpleSemaphore(1);
		continuar = new SimpleSemaphore(0);
		desbloqueo = new SimpleSemaphore(0);

		createThread("letraA");
		createThread("letraB");
		createThread("letraC");
		createThread("letraD");

		startThreadsAndWait();

	}

}
