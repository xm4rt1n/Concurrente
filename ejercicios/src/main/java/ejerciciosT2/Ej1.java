package ejerciciosT2;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ej1 {
	static volatile double num;
	static volatile boolean producido;
	
	public static void productor() {
		num=Math.random();
		producido=true;
	}
	
	public static void consumidor() {
		while(!producido);
		println("El numero es: "+num);
	}
	
	public static void main(String[] args) {
		producido=false;
		createThread("productor");
		createThread("consumidor");
		
		startThreadsAndWait();
	}
}
