package ejerciciosT5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferSinc {
	private final int BUFFER_SIZE = 10;

	private int[] datos = new int[BUFFER_SIZE];
	private int posInser = 0;
	private int posSacar = 0;
	private int productos = 0;
	Lock procesosLock = new ReentrantLock();
	Condition empty = procesosLock.newCondition();
	Condition full = procesosLock.newCondition();

	public void insertarBuffer(int dato) throws InterruptedException {
		procesosLock.lock();
		while (productos == BUFFER_SIZE) {
			full.await();
		}
		datos[posInser] = dato;
		posInser = (posInser + 1) % datos.length;
		productos++;

		empty.signal();
		procesosLock.unlock();
	}

	public int sacarBuffer() throws InterruptedException {
		procesosLock.lock();

		while (productos == 0) {
			empty.await();
		}
		int dato = datos[posSacar];
		posSacar = (posSacar + 1) % datos.length;
		productos--;

		full.signal();
		procesosLock.unlock();
		return dato;
	}
}
