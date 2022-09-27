package ejerciciosT2;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ej6 {
	static volatile int x;

	public static void persona() {
		while (true) {
			enterMutex();
			x++;
			printlnI("Hola, somos " + x);
			exitMutex();
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
