package ejerciciosT5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ejercicio5_5_7 {

	private final int N_FRAGMENTOS = 10;
	private final int N_HILOS = 3;
	private final int N_FICHEROS = 2;

	private volatile int[] fichero = new int[N_FRAGMENTOS];
	private volatile int fragPendiente = 0;

	private Lock lock = new ReentrantLock();
	private CyclicBarrier cb;

	private static int descargarDatos(int numFragment) throws InterruptedException {
		Thread.sleep(1000);
		return numFragment * 2;
	}

	private void mostrarFichero() {
		System.out.println("--------------------------------------------------");
		System.out.print("File = [");
		for (int i = 0; i < N_FRAGMENTOS; i++) {
			System.out.print(fichero[i] + ",");
		}
		System.out.println("]");
	}

	public void downloader() {

		for (int i = 0; i < N_FICHEROS; i++) {

			descargaFragmentos();

			try {
				System.out.println(Thread.currentThread().getName() + " esperando al siguiente fichero");
				cb.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void descargaFragmentos() {
		while (true) {

			lock.lock();
			if (fragPendiente == N_FRAGMENTOS) {
				lock.unlock();
				break;
			}

			int fragDescargar = fragPendiente;
			fragPendiente++;
			lock.unlock();

			System.out.println(Thread.currentThread().getName() + ": Descargando fragmento " + fragDescargar);

			int downloadedData;
			try {
				downloadedData = descargarDatos(fragDescargar);

				System.out.println(Thread.currentThread().getName() + ": Escribiendo fragmento " + fragDescargar);

				fichero[fragDescargar] = downloadedData;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void recargaFichero() {
		mostrarFichero();
		fragPendiente = 0;
	}

	private void exec() {
		cb = new CyclicBarrier(N_HILOS, () -> recargaFichero());
		for (int i = 0; i < N_HILOS; i++) {
			new Thread(() -> downloader(), "Descargador_" + i).start();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ejercicio5_5_7().exec();
	}

}
