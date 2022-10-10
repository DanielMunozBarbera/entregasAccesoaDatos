package es.florida.ejemplos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class T1EjemploControlador {

	private T1EjemploModelo modelo;
	private T1EjemploVista vista;
	private ActionListener actionListenerAnyadir;
	private String ficheroLectura, ficheroEscritura;
	
	public T1EjemploControlador(T1EjemploModelo modelo, T1EjemploVista vista) {
		this.modelo = modelo;
		this.vista = vista;
		control();
	}
	
	public void control() {
		
		ficheroLectura = modelo.ficheroLectura();
		ficheroEscritura = modelo.ficheroEscritura();
		
		mostrarFichero(ficheroLectura,1);
		actionListenerAnyadir = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String textoAnyadir = vista.getTextField().getText();
				modelo.anyadirTexto(textoAnyadir);
				mostrarFichero(ficheroEscritura,2);
			}
		};
		vista.getAnyadir().addActionListener(actionListenerAnyadir);
	}
	
	private void mostrarFichero(String fichero, int numeroTextArea) {
		ArrayList<String> arrayLineas = modelo.contenidoFichero(fichero);
		for (String linea : arrayLineas) {
			if(numeroTextArea == 1) {
				vista.getTextArea().append(linea+"\n");
			}else {
				vista.getTextArea_1().append(linea+"\n");
			}
		}
	}
	
}
