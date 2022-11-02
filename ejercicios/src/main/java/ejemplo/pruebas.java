package ejemplo;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;


public class pruebas {
	public static final int N_THREADS = 5;
	public static final int DATA_SAMPLE_SIZE = 16;
	private static int[] data;
	private static int[] resultados = new int[DATA_SAMPLE_SIZE / 2];


	private static void initSampleData() {
		data = ThreadLocalRandom.current().ints(DATA_SAMPLE_SIZE, 1, 10).toArray();
		println("Los datos a sumar son: " + Arrays.toString(pruebas.data));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initSampleData();
		System.arraycopy(data, 0, resultados, 0, DATA_SAMPLE_SIZE/2);
		println("El array es: "+ Arrays.toString(pruebas.data));
		println(getThreadName()+ ": Actualiza el array de datos a  "+Arrays.toString(pruebas.resultados));

	}

}
