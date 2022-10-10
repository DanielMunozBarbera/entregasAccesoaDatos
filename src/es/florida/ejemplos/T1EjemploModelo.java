package es.florida.ejemplos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class T1EjemploModelo {

	private String fichero_lectura;
	private String fichero_escritura;
	
	public T1EjemploModelo() {
		fichero_lectura = "Ejemplo_T1_2_Streams_Groucho.txt";
		fichero_escritura = "Ejemplo_T1_2_Streams_Groucho_2.txt";
	}
	
	public ArrayList<String> contenidoFichero(String fichero){
		ArrayList<String> contenidoFichero = new ArrayList<String>();
		File f = new File(fichero);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			while (linea != null) {
				contenidoFichero.add(linea);
				linea = br.readLine();
			}
			br.close();
			fr.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		return contenidoFichero;
	}
	
	public String ficheroEscritura() {
		return fichero_escritura;
	}
	
	public String ficheroLectura() {
		return fichero_lectura;
	}
	
	public void anyadirTexto(String textoAnyadir) {
		File f1 = new File(ficheroLectura());
		File f2 = new File(ficheroEscritura());
		try {
			FileReader fr = new FileReader(f1);
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter(f2);
			BufferedWriter bw = new BufferedWriter(fw);
			String linea = br.readLine();
			while(linea != null) {
				bw.write(linea);
				bw.newLine();
				linea = br.readLine();
			}
			bw.write(textoAnyadir);
			br.close();
			fr.close();
			bw.close();
			fw.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
}
