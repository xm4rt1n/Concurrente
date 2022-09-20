package ejerciciosT2;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ej2 {
	static volatile double num;
	static volatile boolean producido;
	static volatile boolean consumido;

	public static void productor() {
		for (int i = 0; i < 5; i++) {
			while (!consumido);
			num = Math.random();
			consumido=false;
			producido = true;
		}
	}

	public static void consumidor() {
		for (int i = 0; i < 5; i++) {
			while (!producido);
			producido = false;
			println("El numero:" + num);
			consumido = true;
		}
	}

	public static void main(String[] args) {
		producido = false;
		consumido = true;
		createThread("productor");
		createThread("consumidor");

		startThreadsAndWait();
	}
}
