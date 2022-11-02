package prepExamenT2;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejercicio1 {
	static volatile double dato;
	static volatile boolean continuar;

	public static void productor() {
		dato= Math.random()*100;
		continuar=true;
	}

	public static void consumidor() {
		while(!continuar);
		println("El numero es: "+ dato);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		continuar=false;
		createThread("productor");
		createThread("consumidor");
		startThreadsAndWait();
	}

}
