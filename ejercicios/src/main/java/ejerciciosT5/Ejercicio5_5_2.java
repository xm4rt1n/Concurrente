package ejerciciosT5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ejercicio5_5_2 {
	public volatile int x = 0;
	private Lock xLock = new ReentrantLock();

	public void persona() {
		while (true) {

			boolean regalo = false;
			xLock.lock();
			try {
				x++;
				System.out.println("Hola, somos " + x);
				if (x == 1) {
					regalo = true;
				}
			} finally {
				xLock.unlock();
			}
			if (regalo) {
				System.out.println("Tengo regalo!");
			} else {
				System.out.println("No tengo regalo!");
			}
			System.out.println("que bonito!");
			System.out.println("alucinante!");
			xLock.lock();
			try {
				x--;
				System.out.println("adios a los " + x);
			} finally {
				xLock.unlock();
			}
		}
	}

	public void exec() {
		for (int i = 0; i < 3; i++) {
			new Thread(() -> persona()).start();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ejercicio5_5_2().exec();

	}

}
