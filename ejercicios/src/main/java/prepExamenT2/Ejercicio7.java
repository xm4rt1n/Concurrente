package prepExamenT2;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejercicio7 {
	public static volatile int personas;

	public static void persona() {
		while (true) {
			enterMutex();
			personas++;
			printlnI("hola, somos " + personas);
			if (personas == 1) {
				exitMutex();
				printlnI("Tengo regalo");
			} else {
				exitMutex();
				printlnI("No tengo regalo");
			}

			printlnI("que bonito!");
			printlnI("alucinante!");

			enterMutex();
			personas--;
			printlnI("adios a los " + personas);
			exitMutex();

			printlnI("paseo");
			sleepRandom(500);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		personas = 0;
		createThreads(3, "persona");

		startThreadsAndWait();
	}

}
