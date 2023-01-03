package ejerciciosT5_6_5;

public class Fabrica {
	private static final int NUM_ROBOTS = 5;
	private static final int NUM_TIPOS_PIEZAS = 5;
	private AlmacenPiezas almacen = new AlmacenPiezas(NUM_TIPOS_PIEZAS);

	private double fabricarPieza(int tipoPieza) {
		double pieza = tipoPieza + Math.random();
		System.out.println("---> " + Thread.currentThread().getName() + " Producto montado");
		return pieza;
	}

	private void montarPieza(int tipoPieza, double pieza) throws InterruptedException {
		Thread.sleep((long) (Math.random() * 500));
		System.out.println(Thread.currentThread().getName() + ": " + pieza);
	}

	private void maquinas(int numMaquina) {
		try {
			while (true) {
				double pieza = fabricarPieza(numMaquina);
				almacen.almacenarPieza(numMaquina, pieza);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void robots(int numRobot) {
		try {
			while (true) {
				for (int i = 0; i < NUM_TIPOS_PIEZAS; i++) {
					double pieza = almacen.recogerPieza(i);
					montarPieza(i, pieza);
				}
				System.out.println("---> " + Thread.currentThread().getName() + " Producto montado");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void exec() {
		for (int i = 0; i < NUM_ROBOTS; i++) {
			int numRobot = i;
			new Thread(() -> robots(numRobot), "Robot_" + numRobot).start();
		}

		for (int i = 0; i < NUM_TIPOS_PIEZAS; i++) {
			int numMaquina = i;
			new Thread(() -> maquinas(numMaquina), "Maquina-" + numMaquina).start();
		}
	}

	public static void main(String[] args) {
		new Fabrica().exec();
	}
}
