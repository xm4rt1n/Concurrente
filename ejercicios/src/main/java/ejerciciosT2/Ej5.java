package ejerciciosT2;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ej5 {

	public static void persona() {
		while (true) {
			enterMutex();
			printlnI("hola");
			printlnI("que bonito!");
			printlnI("adios");
			exitMutex();

			printlnI("paseo");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createThreads(3, "persona");
		startThreadsAndWait();
	}

}
