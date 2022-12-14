package ejerciciosT5;

public class Ejercicio5_5_4 {
	private static final int PRODUCTORES = 5;
	private static final int CONSUMIDORES = 3;

	private void sleepRandom(long max) throws InterruptedException {
		Thread.sleep((long) (Math.random() * max));
	}

	public void productor(BufferSinc buffer) {
		try {
			for (int i = 0; i < 20; i++) {
				sleepRandom(500);
				System.out.println("Producido:" + i);
				buffer.insertarBuffer(i);
			}
		} catch (InterruptedException e) {
		}
	}

	public void consumidor(BufferSinc buffer) {
		try {
			while (true) {
				int data = buffer.sacarBuffer();
				sleepRandom(1000);
				System.out.println("Consumido:" + data);
			}
		} catch (InterruptedException e) {
		}
	}

	public void exec() {

		BufferSinc buffer = new BufferSinc();

		for (int i = 0; i < PRODUCTORES; i++) {
			new Thread(() -> productor(buffer), "Productor " + i).start();
		}

		for (int i = 0; i < CONSUMIDORES; i++) {
			new Thread(() -> consumidor(buffer), "Consumidor " + i).start();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ejercicio5_5_4().exec();

	}

}
