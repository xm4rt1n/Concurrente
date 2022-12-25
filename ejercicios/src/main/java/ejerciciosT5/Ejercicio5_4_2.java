package ejerciciosT5;

public class Ejercicio5_4_2 {
	private SincCond sincCond;
	private volatile double producto;

	private void productor() {
		producto = Math.random();
		sincCond.signal();
	}

	private void consumidor() {
		sincCond.await();
		System.out.println("Producto: " + producto);
	}

	public void exec() {
		new Thread(() -> productor()).start();
		new Thread(() -> consumidor()).start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ejercicio5_4_2().exec();
	}

}
