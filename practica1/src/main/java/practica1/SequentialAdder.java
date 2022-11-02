package practica1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class SequentialAdder {
	public static final int N_THREADS = 5;
	public static final int DATA_SAMPLE_SIZE = 16;
	private static int[] data;
	private static int[] resultados = new int[DATA_SAMPLE_SIZE / 2];
	public static volatile int maxPos = 16;
	public static volatile int actualPos = 0;
	public static volatile int nivel = 1;
	public static volatile int terminados = 0;
	private static SimpleSemaphore emPosicion = new SimpleSemaphore(1);
	private static SimpleSemaphore emTerminados = new SimpleSemaphore(1);
	private static SimpleSemaphore sb = new SimpleSemaphore(0);
	private static SimpleSemaphore continuar = new SimpleSemaphore(0);

	private static int add(int a, int b) {
		sleepRandom(1000);
		return a + b;
	}

	private static void initSampleData() {
		data = ThreadLocalRandom.current().ints(DATA_SAMPLE_SIZE, 1, 10).toArray();
		println("Los datos a sumar son: " + Arrays.toString(SequentialAdder.data));
	}

	private static void suma() {
		while (true) {
			emPosicion.acquire();
			if (actualPos == maxPos) {
				emPosicion.release();
				break;
			}
			int posicion = actualPos;
			actualPos += 2;
			emPosicion.release();

			int pos1 = posicion;
			int pos2 = posicion + 1;
			println(getThreadName() + ": Se inicia la suma de data[" + pos1 + "]=" + data[pos1] + " y data[" + pos2
					+ "]=" + data[pos2]);
			int resultado = add(data[pos1], data[pos2]);

			println(getThreadName() + ": Se guarda la suma en results[" + (pos1) / 2 + "]=" + resultado);
			resultados[(pos1) / 2] = resultado;

		}
	}

	public static void proceso() {
		for (int i = 0; i < 4; i++) {
			suma();
			emTerminados.acquire();
			terminados++;
			if (terminados < 5) {
				println(getThreadName() + ": Esperando a los demas procesos. Han terminado " + terminados
						+ " procesos.");
				emTerminados.release();
				sb.acquire();
				continuar.release();
			} else {
				if (nivel != 4) {
					data = new int[maxPos / 2];
					System.arraycopy(resultados, 0, data, 0, maxPos / 2);
					println(getThreadName() + ": Actualiza el array de datos a "
							+ Arrays.toString(SequentialAdder.data));
				}
				println(getThreadName() + ": Se ha terminado el nivel " + nivel);
				println("----------------------------------------------------------");
				if (nivel == 4) {
					println("El resultado es: " + resultados[0]);
				}

				actualPos = 0;
				maxPos /= 2;
				nivel++;
				terminados = 0;
				sb.release(4);
				continuar.acquire(4);
				emTerminados.release();
			}
		}

	}

	public static void main(String[] args) {
		initSampleData();
		LocalDateTime start = LocalDateTime.now();
		int sum = 0;
		for (int i = 0; i < DATA_SAMPLE_SIZE; i++) {
			sum = add(sum, data[i]);
		}
		Duration time = Duration.between(start, LocalDateTime.now());
		println("Suma total: " + sum);
		println("(Calculado en: " + Duration.between(start, LocalDateTime.now()) + ")");

		println("------------------------------------------------\nParte concurrente: ");
		createThreads(N_THREADS, "proceso");
		startThreadsAndWait();
	}
}