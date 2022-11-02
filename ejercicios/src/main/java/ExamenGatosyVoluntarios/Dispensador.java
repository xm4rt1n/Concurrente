package ExamenGatosyVoluntarios;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class Dispensador {
	final static int P = 10; // numero de porciones maximo
	private static SimpleSemaphore cantidadP = new SimpleSemaphore(0);
	private static SimpleSemaphore huecos = new SimpleSemaphore(P);

	void introducirComida() {
		huecos.acquire();
		cantidadP.release();
	}

	public void sacarComida() {
		
		cantidadP.acquire();
		huecos.release();
	}
}
