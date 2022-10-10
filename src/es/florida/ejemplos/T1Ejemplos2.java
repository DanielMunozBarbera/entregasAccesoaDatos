package es.florida.ejemplos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class T1Ejemplos2 {

	public static void main(String[] args) {
		
		//EJEMPLO 1: leer txt caracter a caracter y mostrarlo
//		try {
//			FileReader fr = new FileReader("textoDeEjemplo.txt");
//			int valor = fr.read();
//			while (valor !=-1) {
//				System.out.print((char)valor);
//				valor = fr.read();
//				Thread.sleep(10);
//			}
//			fr.close();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}

		
		//EJEMPLO 2: leer txt linea a linea y mostrarlo
//		try {
//			FileReader fr = new FileReader("textoDeEjemplo.txt");
//			BufferedReader br = new BufferedReader(fr);
//			String linea = br.readLine();
//			while(linea != null) {
//				System.out.println(linea);
//				linea = br.readLine();
//				Thread.sleep(1000);
//			}
//			fr.close();
//			br.close();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		
		//EJEMPLO 3: escribir en fichero
//		try {
//			FileWriter fw = new FileWriter("textoDeEjemplo2.txt");
//			BufferedWriter bw = new BufferedWriter(fw);
//			FileReader fr = new FileReader("textoDeEjemplo.txt");
//			BufferedReader br = new BufferedReader(fr);
//			String linea = br.readLine();
//			while(linea != null) {
//				bw.write(linea);
//				bw.newLine();
//				linea = br.readLine();
//			}
//			bw.write("(Algo de Texto)");
//			br.close();
//			fr.close();
//			bw.close();
//			fw.close();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
	}

}
