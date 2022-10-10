package es.florida.tema1.mp29;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controlador {

	private Modelo modelo;
	private Vista vista;
	private ActionListener actionListenerBuscar, actionListenerReemplazar;
	private String ficheroOriginal, ficheroAlterado;
	
	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		control();
	}
	
	public void control() {
		ficheroOriginal = modelo.ficheroOriginal();
		ficheroAlterado = modelo.ficheroAlterado();
		
		mostrarFicheroAreaOriginal(ficheroOriginal);
		
		//BOTON DE BUSCAR
		actionListenerBuscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String textoEnBuscar = vista.getTextFieldBuscar().getText();
				String[] textoEnArea = vista.getTextAreaOriginal().getText().split(" ");
				int coincidencias = 0;
				for (int i = 0; i < textoEnArea.length; i++) {
					if(textoEnArea[i].equals(textoEnBuscar) || 
							textoEnArea[i].equals(textoEnBuscar+".") || 
							textoEnArea[i].equals(textoEnBuscar+",") || 
							textoEnArea[i].equals("\n"+textoEnBuscar)) {	
						coincidencias++;
					}
				}
				JOptionPane.showMessageDialog(new JFrame(), "La palabra " + textoEnBuscar + " se encontro " + coincidencias + " veces.", "Buqueda", JOptionPane.DEFAULT_OPTION);
			}
			
		};
		vista.getBtnBuscar().addActionListener(actionListenerBuscar);
		
		//BOTON REMPLAZAR
		actionListenerReemplazar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String textoEnBuscar = vista.getTextFieldBuscar().getText();
				String textoEnReemplazar = vista.getTextFieldReemplazar().getText();
				modelo.reemplazarTexto(textoEnBuscar,textoEnReemplazar);
				mostrarFicheroAreaModificado(ficheroAlterado);
			}
		};
		vista.getBtnReemplazar().addActionListener(actionListenerReemplazar);
		
	}
	
	private void mostrarFicheroAreaOriginal(String fichero) {
		ArrayList<String> arrayLineas = modelo.contenidoFichero(fichero);
		for (String linea : arrayLineas) {
				vista.getTextAreaOriginal().append(linea+"\n");
		}
	}
	
	private void mostrarFicheroAreaModificado(String fichero) {
		ArrayList<String> arrayLineas = modelo.contenidoFichero(fichero);
		for (String linea : arrayLineas) {
				vista.getTextAreaModificado().append(linea+"\n");
		}
	
	}
}
