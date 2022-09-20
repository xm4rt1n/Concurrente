package ejerciciosT2;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ej4B {
	
	public static volatile boolean peticion[]= new boolean[2];
	public static volatile boolean respuesta[]= new boolean[2];
	public static volatile int numero[]= new int[2];
	
	
	public static void servidor() {
		while(true) {
			for(int i=0; i<peticion.length;i++) {
				while(peticion[i]) {
				peticion[i]=false;
				numero[i]++;
				respuesta[i]=true;
				}
			}
		}
	}
	
	public static void cliente() {
		for(int i=0; i<2; i++) {
		numero[0]=(int) (Math.random()*10);
		peticion[0]=true;
		while(!respuesta[0]);
		respuesta[0]=false;
		println("El numero:" +numero[0]);
		}
	}
	
	public static void cliente2() {
		for(int i=0; i<2; i++) {
		numero[1]=(int) (Math.random()*100+10);
		peticion[1]=true;
		while(!respuesta[1]);
		respuesta[1]=false;
		println("El numero es:" +numero[1]);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		peticion[0]=false;
		respuesta[0]=false;
		peticion[1]=false;
		respuesta[1]=false;
		
		createThread("servidor");
		createThread("cliente");
		createThread("cliente2");
		
		startThreadsAndWait();
		

	}

}
