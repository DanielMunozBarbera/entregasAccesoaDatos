package es.florida.aev1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Daniel Muñoz Barbera - Clase sobre la cual se pone a la escucha los
 *         JButton que componen nuestro programa y los enlaza con los metodos de
 *         la clase AEV1Modelo
 * 
 */
public class AEV1Controlador {

	private AEV1Modelo modelo;
	private AEV1Vista vista;
	private ActionListener actionListenerDir, actionListenerMostrarInfoArchivo;
	private ActionListener actionListenerCrear, actionListenerRenombrar, actionListenerCopiar;
	private ActionListener actionListenerSuprimir, actionListenerMostrarArchivo, actionListenerBuscar;
	private ActionListener actionListenerAnyadirTexto, actionListenerReemplazar, actionListenerDestacar;
	private ActionListener actionListenerGuardarCambios;

	public static String ruta;

	/**
	 * @param modelo
	 * @param vista  Metodo constructor de la clase AEV1Controlador, incluye una
	 *               llamada al metodo control().
	 */
	public AEV1Controlador(AEV1Modelo modelo, AEV1Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		control();
	}

	/**
	 * Metodo principal de la clase, en el cual se añaden los actionListeners a los
	 * botones y se les enlaza con los metodos de la clase AEV1Modelo
	 */
	public void control() {

		// BOTON MOSTRAR DIRECTORIO
		actionListenerDir = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				vista.getTextArea().setText("");
				String ruta = vista.getTextFieldDir().getText();
				try {
					mostrarDirectorio(ruta);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(new JFrame(),
							"El directorio introducido no existe o no esta bien escrito", "Busqueda fallida",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		};
		vista.getBotonDir().addActionListener(actionListenerDir);

		// BOTON MOSTRAR INFORMACION ARCHIVO
		actionListenerMostrarInfoArchivo = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				File file = new File(ruta + "/" + vista.getTextFieldOperaciones().getText());
				try {
					modelo.mostrarInfoArchivo(file, vista);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(new JFrame(),
							"El fichero " + vista.getTextFieldOperaciones().getText() + " no existe! ",
							"Busqueda fallida", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		vista.getBotonMostrarInfo().addActionListener(actionListenerMostrarInfoArchivo);

		// BOTON CREAR FICHERO
		actionListenerCrear = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String rutaParaCrear = vista.getTextFieldDir().getText();
				String nombreFichero = vista.getTextFieldOperaciones().getText();
				modelo.crearArchivo(nombreFichero, rutaParaCrear);
			}
		};
		vista.getBotonCrear().addActionListener(actionListenerCrear);

		// BOTON RENOMBRAR
		actionListenerRenombrar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String nombreViejo = vista.getTextFieldOperaciones().getText();
				modelo.renombrarArchivo(ruta, nombreViejo, vista);
			}
		};
		vista.getBotonRenombrar().addActionListener(actionListenerRenombrar);

		// BOTON COPIAR
		actionListenerCopiar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String nombreArchivoOriginal = vista.getTextFieldOperaciones().getText();
				modelo.copiarArchivo(ruta, nombreArchivoOriginal);
			}
		};
		vista.getBotonCopiar().addActionListener(actionListenerCopiar);

		// BOTON SUPRIMIR
		actionListenerSuprimir = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String nomArchivoParaEliminar = vista.getTextFieldOperaciones().getText();
				modelo.suprimirArchivo(ruta, nomArchivoParaEliminar);
			}
		};
		vista.getBotonSuprimir().addActionListener(actionListenerSuprimir);

		// BOTON MOSTRAR ARCHIVO
		actionListenerMostrarArchivo = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String archivoMostrar = vista.getTextFieldOperaciones().getText();
				modelo.mostrarFichero(ruta + "/" + archivoMostrar, vista);
			}
		};
		vista.getBotonMostrarArchivo().addActionListener(actionListenerMostrarArchivo);

		// BOTON BUSCAR
		actionListenerBuscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String palabra = vista.getTextFieldBuscar().getText();
				int numBusqueda = modelo.buscarEnElArchivo(vista, palabra);
			}
		};
		vista.getBotonBuscar().addActionListener(actionListenerBuscar);

		// BOTON AÑADIR TEXTO
		actionListenerAnyadirTexto = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String textoAnyadir = vista.getTextFielAnyadirTexto().getText();
				modelo.anyadirTexto(textoAnyadir, vista);
			}
		};
		vista.getBotonAnyadirTexto().addActionListener(actionListenerAnyadirTexto);

		// BOTON REEMPLAZAR
		actionListenerReemplazar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String textoOriginal = vista.getTextFieldBuscar().getText();
				String textoNuevo = vista.getTextFieldReemplazar().getText();
				modelo.reemplazarTexto(textoOriginal, textoNuevo, vista);
			}
		};
		vista.getBotonReemplazar().addActionListener(actionListenerReemplazar);

		// BOTON DESTACAR
		actionListenerDestacar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
//				String textoOriginal = vista.getTextFieldBuscar().getText();
//				modelo.destacarTexto(vista, textoOriginal);				
			}
		};
		vista.getBotonDestacar().addActionListener(actionListenerDestacar);

		// BOTON GUARDAR CAMBIOS
		actionListenerGuardarCambios = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String nomArchivo = vista.getTextFieldOperaciones().getText();
				modelo.guardarCambios(vista, ruta + "/" + nomArchivo);
			}
		};
		vista.getBotonGuardarCambios().addActionListener(actionListenerGuardarCambios);
	}

	/**
	 * @param ruta
	 * @throws Exception El unico metodo que esta separado de la clase AEV1Modelo.
	 *                   Se utiliza para mostrar el directorio en el JTextArea
	 *                   principal realizando un comando dir de cmd utilizando
	 *                   ProcessBuilder y Process.
	 */
	public void mostrarDirectorio(String ruta) throws Exception {
		this.ruta = ruta;
		ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "DIR");
		File rutas = new File(ruta);
		pb.directory(rutas);
		Process p = pb.start();

		InputStream is = p.getInputStream();
		int c;
		while ((c = is.read()) != -1) {
			vista.getTextArea().append((char) c + "");
		}
		is.close();

	}

}
