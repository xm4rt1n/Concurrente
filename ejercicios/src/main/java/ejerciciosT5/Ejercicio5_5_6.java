package ejerciciosT5;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Ejercicio5_5_6 {
	private static final int NUM_DS = 2;
	private boolean[] busyDS = new boolean[NUM_DS];
	private ReentrantLock lock = new ReentrantLock(true);
	private Condition[] waitForDS = new Condition[NUM_DS];
	private Condition waitForAnyDS = lock.newCondition();

	public void accessDataSource(int DataSource) throws InterruptedException {
		lock.lock();
		try {
			while (busyDS[DataSource] == true) {
				waitForDS[DataSource].await();
			}
			busyDS[DataSource] = true;
		} finally {
			lock.unlock();
		}
	}

	public int accessAnyDataSource() throws InterruptedException {
		lock.lock();
		try {
			while (busyDS[0] == true && busyDS[1] == true) {
				waitForAnyDS.await();
			}
			if (busyDS[0] == false) {
				busyDS[0] = true;
				return 0;
			} else {
				busyDS[1] = true;
				return 1;
			}
		} finally {
			lock.unlock();
		}
	}

	public void freeDataSource(int DataSource) {
		lock.lock();
		try {
			busyDS[DataSource] = false;
			if (lock.hasWaiters(waitForDS[DataSource])) {
				waitForDS[DataSource].signal();
			} else if (lock.hasWaiters(waitForAnyDS)) {
				waitForAnyDS.signal();
			}
		} finally {
			lock.unlock();
		}
	}

	public Ejercicio5_5_6() {
		for (int i = 0; i < NUM_DS; i++) {
			waitForDS[i] = lock.newCondition();
		}
		Arrays.fill(busyDS, false);

	}

}
