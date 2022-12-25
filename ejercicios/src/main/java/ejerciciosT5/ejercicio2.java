package ejerciciosT5;

public class ejercicio2 {

	private void println(String mensaje) {
		String nombreHilo = Thread.currentThread().getName();
		System.out.println(nombreHilo + ": " + mensaje);
	}

	public void mensajes() {

		try {
			println("La vida es bella");
			Thread.sleep(2000);
			println("O no...");
			Thread.sleep(2000);
			println("Los pajaritos cantan");
			Thread.sleep(2000);
			println("Y molestan...");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			println("Se acabó!!");
		}

	}

	private void exec() throws InterruptedException {

		Thread t = new Thread(() -> mensajes());
		t.start();
		long horaInicio = System.currentTimeMillis();

		while (t.isAlive()) {
			println("Todavia esperando");
			t.join(1000);

			long tiempoEsperando = System.currentTimeMillis() - horaInicio;
			if (tiempoEsperando > 5000 && t.isAlive()) {
				println("Cansado de esperar");
				t.interrupt();
				t.join();
			}
		}
		println("Por fin!");
	}

	public static void main(String[] args) throws InterruptedException {
		new ejercicio2().exec();

	}
}