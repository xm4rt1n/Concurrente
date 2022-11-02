package ExamenGatosyVoluntarios;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class Simulador {
	private static final int N = 3;	//numero de gatos
	private static final int M = 2; //numero de voluntarios
	public static Dispensador dispensador = new Dispensador(); //dispensador de comida
	public static SimpleSemaphore emGatosComidos = new SimpleSemaphore(1); //EM para el nº de gatos que han comido ya
	public static SimpleSemaphore sb = new SimpleSemaphore(0); //sincronizacion ciclica
	public static SimpleSemaphore continuar = new SimpleSemaphore(0); //sincrionizacion ciblica
	public static volatile int gatosComidos = 0; //nº de gatos que han comido

	public static void gato() {
		while (true) {
			comerGato();
			sleepRandom(500);
			vivirGato();
			sleepRandom(500);
			emGatosComidos.acquire();
			gatosComidos++;
			if (gatosComidos < N) {
				emGatosComidos.release();
				sb.acquire();
				continuar.release();
			} else {
				gatosComidos = 0;
				sb.release(2);
				continuar.acquire(2);
				emGatosComidos.release();
			}
		}
	}

	public static void voluntario() {
		while(true) {
			llevarComida();
			sleepRandom(500);
			vivirVoluntario();
			sleepRandom(500);
		}
	}

	public static void comerGato() {
		println("El gato esta comiendo una porcion");
		dispensador.sacarComida();
	}

	public static void vivirGato() {
		println("El gato esta haciendo cosas de gato");
	}

	public static void llevarComida() {
		println("El voluntario añadio una porcion");
		dispensador.introducirComida();
	}

	public static void vivirVoluntario() {
		println("El voluntario esta haciendo quehaceres diarios");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createThreads(N, "gato");
		createThreads(M, "voluntario");
		
		startThreadsAndWait();

	}

}
