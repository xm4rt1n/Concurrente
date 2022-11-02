package prepExamenT2;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejercicio5 {

	public static void persona() {
		while (true) {
			enterMutex();
			printlnI("hola");
			printlnI("que bonito!");
			printlnI("alucinante!");
			printlnI("adios!");
			exitMutex();
			printlnI("paseo");
			sleepRandom(500);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createThreads(3, "persona");

		startThreadsAndWait();
	}

}
