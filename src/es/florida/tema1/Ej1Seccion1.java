package es.florida.tema1;

import java.io.*;
import java.sql.Date;
import java.util.Scanner;

public class Ej1Seccion1 {

	public static void main(String[] args) throws IOException {
		
		//1.1 , 1.2 & 1.3
//		String dir = args[0];
//		File directorio = new File(dir);
//		if (directorio.exists()) {
//			String[] listaArchivos = directorio.list();
//			System.out.println("Contenido del directorio: " + directorio.getName());
//			for (int i = 0; i < listaArchivos.length; i++) {
//				System.out.println(listaArchivos[i]);
//			}
//			System.out.println(" ");
//			System.out.println("Nombre del directorio: " + directorio.getName());
//			System.out.println("Ruta: " + directorio.getPath());
//			System.out.println("Ruta Absoluta: " + directorio.getAbsolutePath());
//			System.out.println("Se puede leer: " + directorio.canRead());
//			System.out.println("Se puede escribir: " + directorio.canWrite());
//			System.out.println("Tamaño: " + directorio.length());
//			System.out.println("Su directorio padre es: " + directorio.getParent());	
//		}else {
//			System.out.println("El directorio " + dir + " no existe");
//		}

		//1.4 
//		String dir = args[0];
//		System.out.println("Directorio: " + dir);
//		File directorio = new File(dir);
//		if (directorio.exists()) {
//			System.out.println("El directorio existe");
//		}else {
//			System.out.println("El directorio no existe!!!");
//		}
		
		//1.5 & 1.6
//		String dirRuta = args[0];
//		File dir = new File(dirRuta);
//		try {
//			String extension = args[1];
//			String[] listaArchivos = dir.list(new FiltroExtension(extension));
//			for (int i = 0; i < listaArchivos.length; i++) {
//				System.out.println(listaArchivos[i]);
//			}
//		}catch(ArrayIndexOutOfBoundsException e) {
//			System.out.println("No se encontro ninguna extension como parametro, se procede a mostrar todo el directorio");
//			String[] listaArchivos = dir.list();
//			for(String archivos : listaArchivos) {
//				System.out.println(archivos);
//			}
//		}
		
		//1.7
//		String directorio = args[0];
//		System.out.println("Directorio: " + directorio);
//		int numArg = args.length -1;
//		File dir = new File(directorio);
//		for(int i = 1; i <= numArg; i ++) {
//			FiltroExtension filtro = new FiltroExtension(args[i]);
//			String[] listaFiltrada = dir.list(filtro);
//			for (String archivo : listaFiltrada) {
//				System.out.println(archivo);
//			}
//		}
		
		//1.8
//		String nomFichero = args[0];
//		System.out.println("Fichero original: " + nomFichero);
//		int posPunto = nomFichero.indexOf(".");
//		String nomFicheroSinExtension = nomFichero.substring(0, posPunto);
//		String extension = nomFichero.substring(posPunto);
//		String nomFicheroCopia = nomFicheroSinExtension + "_copia" + extension;
//		System.out.println("Fichero copia: " + nomFicheroCopia);
//		File fileOriginal = new File (nomFichero);
//		File fileCopia = new File (nomFicheroCopia);
//		FileReader fr = new FileReader(fileOriginal);
//		BufferedReader br = new BufferedReader(fr);
//		FileWriter fw = new FileWriter(fileCopia);
//		BufferedWriter bw = new BufferedWriter(fw);
//		String linea = br.readLine();
//		while(linea != null) {
//			System.out.println("Copiando \"" + linea + "\" de " + nomFichero + " a " + nomFicheroCopia);
//			bw.write(linea);
//			linea = br.readLine();
//		}
//		br.close();
//		fr.close();
//		bw.close();
//		fw.close();
//		System.out.println("Borrando fichero " + nomFicheroCopia);
//		fileCopia.delete();
		
		
		//1.9 MP
//		boolean bucle = true;
//        Scanner teclado = new Scanner(System.in);
//            
//        do {
//        	System.out.println("----------------------------------------------");
//            String directorio = args[0];
//    		File dirOriginal = new File(directorio);
//    		String[] listaOriginal = dirOriginal.list();
//    		System.out.println("Directorio Base: " + directorio);
//    		for(String archivo : listaOriginal) {
//    			System.out.println(archivo);
//    		}
//    		System.out.println("Estos son los directorios y ficheros del directorio, que deseas hacer?: ");
//    		System.out.println("1. Get Information");
//    		System.out.println("2. Crear Carpeta");
//    		System.out.println("3. Crear Fichero");
//    		System.out.println("4. Eliminar");
//    		System.out.println("5. Renombrar");
//    		System.out.println("6. Salir");
//    		System.out.println("Selecciona la opcion: ");
//    		int opt = teclado.nextInt();
//    		
//    		if(opt == 1) {
//    			System.out.println("Escribe el nombre del fichero o directorio del que quieras informacion: ");
//    			String archivo = teclado.next();
//    			archivo = args[0] + "/" + archivo;
//    			File fileArchivo = new File(archivo); 
//    			getInformation(fileArchivo);
//    			System.out.println("Presiona Enter para continuar...");	
//    		}else if(opt == 2) {
//    			System.out.println("Como quieres que se llame la carpeta?");
//    			String carpeta = teclado.next();
//    			carpeta = args[0] + "/" + carpeta;
//    			File nuevaCarpeta = new File(carpeta);
//    			crearCarpeta(nuevaCarpeta);
//    			System.out.println("Presiona Enter para continuar...");	
//    		}else if(opt == 3) {
//    			System.out.println("Como quieres que se llame el fichero a crear? Ten en cuenta el directorio en el que estas ");
//    			String nuevoFichero = teclado.next();
//    			nuevoFichero = args[0] + "/" + nuevoFichero;
//    			File nf = new File (nuevoFichero);
//    			createNew(nf);
//    		}else if(opt == 4) {
//    			System.out.println("Escribe el fichero o directorio a eliminar: ");
//    			String eliminar = teclado.next();
//    			eliminar = args[0] + "/" + eliminar;
//    			File paraEliminar = new File(eliminar);
//    			eliminarCosas(paraEliminar);
//    			System.out.println("Presiona Enter para continuar...");
//    		}else if(opt == 5) {
//    			System.out.println("Escribe el fichero o directorio que quieras renombrar: ");
//    			String original = teclado.next();
//    			System.out.println("Escribe el nuevo nombre que quieres que tenga: ");
//    			String nuevoNom = teclado.next();
//    			original = args[0] + "/" + original;
//    			nuevoNom = args[0] + "/" + nuevoNom;
//    			File fileOld = new File(original);
//    			File fileNew = new File(nuevoNom);
//    			cambiaNombre(fileOld, fileNew);
//    			System.out.println("Presiona Enter para continuar...");
//    		}else if(opt == 6) {
//    			bucle = false;
//    			System.out.println("Saliendo...");
//    		}
//    		System.in.read();
//        }while(bucle);
//        teclado.close();
		
	}
	
	//PARTE FUERA DEL MAIN
//	public static String getInformation(File archivo) {
//		if(archivo.exists()) {
//			Date ultimaMod = new Date (archivo.lastModified());
//			System.out.println("Nombre: " + archivo.getName());
//			if(archivo.isDirectory()) System.out.println("Tipo: Es un Directorio");
//			else System.out.println("Tipo: Es un Fichero");
//			System.out.println("Ruta Absoluta: " + archivo.getAbsolutePath());
//			System.out.println("Fecha ultima modificacion: " + ultimaMod);
//			System.out.println("Esta oculto?: " + archivo.isHidden());
//		
//			if(archivo.isDirectory()) {
//				String[] listaDir = archivo.list();
//				System.out.println("Contiene: " + (listaDir.length) + " elementos.");
//				System.out.println("Espacio libre: " + archivo.getFreeSpace());
//				System.out.println("Espacio disponible: " + archivo.getUsableSpace());
//				System.out.println("Espacio total: " + archivo.getTotalSpace());
//			}else {
//				System.out.println("Su tamanyo en bytes es de: " + archivo.length() + " bytes");
//			}
//		}else {
//			System.out.println("El archivo o directorio introducido no existen");
//		}
//		return null;
//	}
//
//	public static String crearCarpeta(File carpeta) {
//		if(carpeta.mkdir()) System.out.println("Directorio creado correctamente");
//		else System.out.println("No fue posible crear el directorio");
//		return null;
//	}
//	
//	public static String eliminarCosas(File eliminar) {
//		if(eliminar.delete()) System.out.println("Elemento eliminado correctamente");
//		else System.out.println("No fue posible eliminar el elemento");
//		return null;
//	}
//	
//	public static String cambiaNombre(File actual, File nuevo) {
//		if(actual.exists()) actual.renameTo(nuevo);
//		else System.out.println("El archivo o directorio que quieres cambiar de nombre no existe");
//		return null;
//	}
//	
//	public static String createNew(File file) {
//		try {
//			file.createNewFile();
//			System.out.println("Archivo creado correctamente");
//		} catch (IOException e) {
//			System.out.println("No fue posible crear el fichero");
//		}
//		return null;
//	}

}
