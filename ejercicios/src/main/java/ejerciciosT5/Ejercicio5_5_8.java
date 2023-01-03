package ejerciciosT5;

import java.util.concurrent.Exchanger;

public class Ejercicio5_5_8 {

	private Exchanger<Double> exchagerCP = new Exchanger<>();
	private Exchanger<Double> exchagerPS = new Exchanger<>();

	private void cliente() {
		double num = Math.random();
		double respuesta = 0;
		try {
			System.out.println("El numero creado es: " + num);
			exchagerCP.exchange(num);
			respuesta = exchagerCP.exchange(null);
			System.out.println("La respuesta es: " + respuesta);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void proxy() {
		double peticion, respuesta;
		try {
			peticion = exchagerCP.exchange(null) + 1;
			System.out.println("La peticion del proxy es:" + peticion);
			exchagerPS.exchange(peticion);
			respuesta = exchagerPS.exchange(null);
			System.out.println("La respuesta en el proxy es: " + respuesta);
			exchagerCP.exchange(respuesta);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void servidor() {
		double respuesta;
		try {
			respuesta = exchagerPS.exchange(null) + 1;
			System.out.println("La respuesta en el servidor es: " + respuesta);
			exchagerPS.exchange(respuesta);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void exec() {
		new Thread(() -> cliente()).start();
		new Thread(() -> proxy()).start();
		new Thread(() -> servidor()).start();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ejercicio5_5_8().exec();
	}

}
