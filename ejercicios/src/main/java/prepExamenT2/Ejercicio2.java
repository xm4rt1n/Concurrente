package prepExamenT2;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejercicio2 {
	static volatile double dato;
	static volatile boolean continuar;
	static volatile boolean mostrado;

	public static void productor() {
		for (int i = 0; i < 5; i++) {
			while(!mostrado);
			mostrado=false;
			dato = Math.random() * 100;
			continuar = true;
		}
	}

	public static void consumidor() {
		for (int i = 0; i < 5; i++) {
			while (!continuar);
			continuar =false;
			println("El numero es: " + dato);
			mostrado=true;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		continuar = false;
		mostrado =true;
		createThread("productor");
		createThread("consumidor");
		startThreadsAndWait();
	}

}
