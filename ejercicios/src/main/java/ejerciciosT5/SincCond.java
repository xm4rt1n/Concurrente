package ejerciciosT5;

public class SincCond {
	private volatile boolean signal = false;

	public void await() {
		while (!signal);
	}

	public void signal() {
		signal = true;
	}

}
