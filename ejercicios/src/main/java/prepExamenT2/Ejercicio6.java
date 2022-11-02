package prepExamenT2;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejercicio6 {
	public static volatile int personas;

	public static void persona() {
		while (true) {
			enterMutex();
			personas++;
			printlnI("hola, somos "+ personas);
			exitMutex();
			
			printlnI("que bonito!");
			printlnI("alucinante!");
			
			enterMutex();
			personas--;
			printlnI("adios a los "+ personas);
			exitMutex();
			
			printlnI("paseo");
			sleepRandom(500);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		personas=0;
		createThreads(3, "persona");

		startThreadsAndWait();
	}

}
