package es.florida.tema1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class EJ1Seccion2 {

	public static void main(String[] args) {
		
		//2.1 & 2.2
//		String fichero = args[0];
//		long valorLectura = Long.parseLong(args[1]);
//		try {
//			FileReader fr = new FileReader(fichero);
//			int valor = fr.read();
//			while(valor != -1) {
//				System.out.print((char)valor);
//				valor = fr.read();
//				Thread.sleep(valorLectura);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//2.3
//		try {
//			FileReader fr = new FileReader("textoDeEjemplo.txt");
//			int valor = fr.read();
//			int contador = 1;
//			while(valor != -1) {
//				System.out.print((char)valor);
//				valor = fr.read();
//				contador++;
//				if(contador == 100) {
//					System.in.read();
//					contador = 0;
//				}
//			}
//			fr.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		//2.4 & 2.5
//		long velocidadTexto = Long.parseLong(args[0]);
//		try {
//			FileReader fr = new FileReader("textoDeEjemplo.txt");
//			BufferedReader br = new BufferedReader(fr);
//			String linea = br.readLine();
//			while (linea != null) {
//				System.out.println(linea);
//				linea = br.readLine();
//				Thread.sleep(velocidadTexto);
//			}
//			fr.close();
//			br.close();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		
		//2.6
//		long velocidadTexto = Long.parseLong(args[0]);
//		try {
//			FileWriter fw = new FileWriter("textoDeEjemplo2.txt");
//			BufferedWriter bw = new BufferedWriter(fw);
//			FileReader fr = new FileReader("textoDeEjemplo.txt");
//			BufferedReader br = new BufferedReader(fr);
//			String linea = br.readLine();
//			while (linea != null) {
//				bw.write(linea);
//				bw.newLine();
//				linea = br.readLine();
//			}
//			br.close();
//			fr.close();
//			bw.close();
//			fw.close();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		
		//2.7
		Scanner teclado = new Scanner(System.in);
		File archivoDelEjercicio = new File("textoDelUsuario.txt");
		BasicFileAttributes attrs;
		String formatted;
		
		
		try {
			FileWriter fw = new FileWriter(archivoDelEjercicio);
			BufferedWriter bw = new BufferedWriter(fw);
			String linea;
			System.out.println("Escribe lo que quieres escribir en el archivo: ");
			linea = teclado.nextLine();
			while (!linea.equals("exit")) {
				bw.write(linea);
				bw.newLine();
				System.out.println("Escribe lo que quieres escribir en el archivo: ");
				linea = teclado.nextLine();
			}
			try {
			    attrs = Files.readAttributes(archivoDelEjercicio.toPath(), BasicFileAttributes.class);
			    FileTime time = attrs.creationTime();   
			    String pattern = "yyyy-MM-dd HH:mm:ss";
			    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			    formatted = simpleDateFormat.format( new Date( time.toMillis() ) );
			    bw.write(formatted);
			} catch (IOException e) {
			    e.printStackTrace();
			}
			
			bw.close();
			fw.close();
			teclado.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
