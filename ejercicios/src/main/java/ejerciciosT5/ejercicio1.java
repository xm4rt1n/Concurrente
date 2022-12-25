package ejerciciosT5;

public class ejercicio1 {
	volatile boolean producido = false;
	volatile double producto;

	public void productor() {
		producto = Math.random();
		producido = true;
	}

	public void consumidor() {
		while (!producido);
		System.out.println("Producto: " + producto);
	}

	private void exec() {
		new Thread(() -> productor()).start();
		new Thread(() -> consumidor()).start();
	}

	public static void main(String[] args) {
		new ejercicio1().exec();
	}
}
