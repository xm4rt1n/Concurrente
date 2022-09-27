package ejerciciosT2;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ej7 {
	static volatile int x;

	public static void persona() {
		while (true) {
			enterMutex();
			x++;
			printlnI("Hola, somos " + x);
			if (x == 1) {
				exitMutex();
				printlnI("Tengo regalo");
			} else {
				exitMutex();
				printlnI("No tengo regalo");
			}
			printlnI("que bonito!");
			printlnI("alucinante!");
			enterMutex();
			x--;
			printlnI("adios a los " + x);
			exitMutex();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		x = 0;
		createThreads(3, "persona");
		startThreadsAndWait();
	}

}
