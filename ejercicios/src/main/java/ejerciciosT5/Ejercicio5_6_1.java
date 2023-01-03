package ejerciciosT5;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio5_6_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> lista = new ArrayList<>();

		lista.add("jose");
		lista.add("Juan");

		System.out.println(lista);
		System.out.println(lista.size());

		String primero = lista.remove(0);

		System.out.println(primero);
		System.out.println(lista);
		System.out.println(lista.size());

	}
}
