package es.florida.ejemplos;

import java.io.File;
import java.io.IOException;

public class T1Ejemplos {

	public static void main(String[] args) {
		
		// EJEMPLO 1: informacion de un fichero
//		File fichero = new File("Ejemplo.txt");
//		
//		System.out.println("Nombre del archivo: " + fichero.getName());
//		System.out.println("Ruta: " + fichero.getPath());
//		System.out.println("Ruta Absoluta: " + fichero.getAbsolutePath());
//		System.out.println("Se puede leer: " + fichero.canRead());
//		System.out.println("Se puede escribir: " + fichero.canWrite());
//		System.out.println("Tamaño: " + fichero.length());
		
		//EJEMPLO 2: listar contenido directorio
//		File directorio = new File(".");
//		String[] listaArchivos = directorio.list();
//		System.out.println("Contenidos directorio: " + directorio.getName());
//		for(int i = 0; i < listaArchivos.length; i++) {
//			System.out.println(listaArchivos[i]);
//		}
		
		//EJEMPLO 3: listar contenido directorio filtrando por extension
//		File directorio2 = new File(".");
//		String[] listaArchivos2 = directorio2.list(new FiltroExtension(".txt"));
//		for (int i = 0; i < listaArchivos2.length; i++) {
//			System.out.println(listaArchivos2[i]);
//		}
		
		//EJEMPLO 4: crear fichero
//		File fichero = new File("Ejemplo_T1_1_File_2.txt");
//		try {
//			fichero.createNewFile();
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
		
		
	}

}
