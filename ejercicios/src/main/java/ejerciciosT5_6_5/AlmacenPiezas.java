package ejerciciosT5_6_5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AlmacenPiezas {
	private static int MAX_PIEZAS = 5;
	private List<BlockingQueue<Double>> colas;
	public AlmacenPiezas(int NumTiposPiezas) {
		colas= new ArrayList<BlockingQueue<Double>>();
		for(int i=0; i<NumTiposPiezas;i++) {
			colas.add(new LinkedBlockingQueue<Double>(MAX_PIEZAS));
		}
	}
	
	public void almacenarPieza(int tipoPieza, Double pieza) throws InterruptedException {
		colas.get(tipoPieza).put(pieza);
	}
	
	public double recogerPieza(int tipoPieza) throws InterruptedException {
		return colas.get(tipoPieza).take();
	}
}
