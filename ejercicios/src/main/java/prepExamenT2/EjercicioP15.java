package prepExamenT2;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class EjercicioP15 {
	private static final int NPROCESOS = 4;
	private static int nProcesos;
	private static SimpleSemaphore numProcesos;
	private static SimpleSemaphore cont;
	private static SimpleSemaphore desbloqueo;

	public static void letraA() {
		while (true) {
			print("A");
			sincronizar();
		}
	}

	public static void letraB() {
		while (true) {
			print("B");
			sincronizar();
		}
	}

	public static void letraC() {
		while (true) {
			print("C");
			sincronizar();
		}
	}

	public static void letraD() {
		while (true) {
			print("D");
			sincronizar();
		}
	}

	public static void sincronizar() {
		numProcesos.acquire();
		nProcesos++;
		if (nProcesos < NPROCESOS) {
			numProcesos.release();
			cont.acquire();
			desbloqueo.release();
		} else {
			nProcesos = 0;
			println("-");
			cont.release(3);
			desbloqueo.acquire(3);
			numProcesos.release();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		nProcesos = 0;
		numProcesos = new SimpleSemaphore(1);
		cont = new SimpleSemaphore(0);
		desbloqueo = new SimpleSemaphore(0);

		createThread("letraA");
		createThread("letraB");
		createThread("letraC");
		createThread("letraD");

		startThreadsAndWait();

	}

}
