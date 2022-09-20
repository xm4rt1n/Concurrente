package ejerciciosT2;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ej3 {
	
	public static volatile boolean peticion;
	public static volatile boolean respuesta;
	public static volatile int numero;
	
	public static void servidor() {
		while(!peticion);
		numero++;
		respuesta=true;
	}
	
	public static void cliente() {
		numero=(int) (Math.random()*10);
		peticion=true;
		while(!respuesta);
		println("El numero es:" +numero);
	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		peticion=false;
		respuesta=false;
		
		createThread("servidor");
		createThread("cliente");
		
		startThreadsAndWait();
		

	}

}
