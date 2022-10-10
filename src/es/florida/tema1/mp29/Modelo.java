package es.florida.tema1.mp29;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Modelo {
	
	private String fichero_original;
	private String fichero_alterado;
	
	public Modelo() {
		fichero_original = "TextoMP29_1.txt";
		fichero_alterado = "TextoMP29_2.txt";
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
	
	public String ficheroOriginal() {
		return fichero_original;
	}
	
	public String ficheroAlterado() {
		return fichero_alterado;
	}
	
	public void reemplazarTexto(String textoOriginal, String textoModificado) {
		File f1 = new File(ficheroOriginal());
		File f2 = new File(ficheroAlterado());
			try {
				FileReader fr = new FileReader(f1);
				BufferedReader br = new BufferedReader(fr);
				FileWriter fw = new FileWriter(f2);
				BufferedWriter bw = new BufferedWriter(fw);
				String linea = br.readLine();
				while(linea != null) {
					
					String[] prueba = linea.split(" ");
					for (int i = 0; i < prueba.length; i++) {
						if(prueba[i].equals(textoOriginal)) {
							prueba[i] = textoModificado;
						}else if(prueba[i].equals(textoOriginal+".")) {
							prueba[i] = textoModificado+".";
						}else if(prueba[i].equals(textoOriginal+",")) {
							prueba[i] = textoModificado+",";
						}else if(prueba[i].equals("\n"+textoOriginal)) {
							prueba[i] = "\n"+textoModificado;
						}
						bw.write(prueba[i]+" ");
					}
					bw.newLine();
					linea = br.readLine();
				}
				System.out.println("Entro en el primero");
				br.close();
				fr.close();
				bw.close();
				fw.close();
			} catch(Exception e) {
				JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
	}
}
