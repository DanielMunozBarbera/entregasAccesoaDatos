package es.florida.aev1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Daniel Muñoz Barbera - Clase sobre la cual se construyen los metodos
 *         principales del programa
 */
public class AEV1Modelo {

	// PARA EL BOTON DE MOSTRAR INFORMACION
	/**
	 * @param file
	 * @param vista
	 * @throws IOException Metodo que es utilizado en la clase AEV1Controlador para
	 *                     mostrar informacion de un archivo. El archivo mostrado
	 *                     sera el que este escrito en el JTextField_Operaciones.
	 *                     Para ello utiliza diversas funciones de la clase File y
	 *                     luego realiza varios append sobre el JTextArea principal.
	 */
	public void mostrarInfoArchivo(File file, AEV1Vista vista) throws IOException {
		// BLOQUE PARA SABER LA EXTENSION
		String extension = "";
		String fileName = file.toString();
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
		}

		// BLOQUE PARA SABER CUANDO FUE CREADO
		BasicFileAttributes attrs;
		attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
		FileTime time = attrs.creationTime();
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String formatted = simpleDateFormat.format(new Date(time.toMillis()));

		// BLOQUE PARA SABER LA ULTIMA MODIFICACION
		Date ultimaMod = new Date(file.lastModified());

		vista.getTextArea().setText("");
		vista.getTextArea().append("Nombre: " + file.getName() + "\n");
		if (file.isDirectory())
			vista.getTextArea().append("Extension: " + "Es un directorio" + "\n");
		else
			vista.getTextArea().append("Extension: " + extension + "\n");
		vista.getTextArea().append("Tamaño: " + file.length() + " bytes." + "\n");
		vista.getTextArea().append("Fecha de creacion: " + formatted + "\n");
		vista.getTextArea().append("Fecha ultima modificacion: " + ultimaMod + "\n");
		vista.getTextArea().append("Ruta Absoluta: " + file.getAbsolutePath() + "\n");
		vista.getTextArea().append("Se puede ejecutar: " + file.canExecute() + "\n");
		vista.getTextArea().append("Se puede leer: " + file.canRead() + "\n");
		vista.getTextArea().append("Se puede escribir: " + file.canWrite() + "\n");

	}

	// PARA EL BOTON DE CREAR ARCHIVO
	/**
	 * @param nombre
	 * @param ruta   Metodo al que se llama en la clase AEV1Controlador para crear
	 *               un fichero Crea un fichero utilizando el nombre que haya en el
	 *               JTextField_Operaciones
	 */
	public void crearArchivo(String nombre, String ruta) {
		File file = new File(ruta + "/" + nombre);

		if (!ruta.isEmpty()) {
			if (!nombre.isEmpty()) {
				try {
					file.createNewFile();
					JOptionPane.showMessageDialog(new JFrame(), "El fichero " + nombre + " se ha creado correctamente ",
							"Fichero Creado", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(new JFrame(), "El fichero " + nombre + " no ha podido ser creado ",
							"Creacion fallida", JOptionPane.ERROR_MESSAGE);
					System.out.println(ruta + "/" + nombre);
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "No hay texto en el campo del nombre del archivo a crear",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(new JFrame(), "No hay texto en el campo del directorio!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	// PARA EL BOTON DE RENOMBRAR
	/**
	 * @param ruta
	 * @param nombreViejo
	 * @param vista       Metodo utilizado en la clase AEV1Controlador para
	 *                    renombrar un fichero Renombra el fichero escrito en el
	 *                    JTextField_Operaciones despues de preguntarnos por el
	 *                    nuevo nombre para el fichero
	 */
	public void renombrarArchivo(String ruta, String nombreViejo, AEV1Vista vista) {
		if (!vista.getTextFieldOperaciones().getText().isEmpty()) {
			File file = new File(ruta + "/" + nombreViejo);
			if (file.exists()) {

				String newNombre = JOptionPane.showInputDialog("Introduzca el nuevo nombre del fichero: ");
				if (!newNombre.isEmpty()) {
					int decision = JOptionPane.showConfirmDialog(null,
							"Esta seguro de que quiere renombrar el archivo? ", "Esta Seguro?",
							JOptionPane.YES_NO_OPTION);

					if (decision == 0) {
						File fileOriginal = new File(ruta + "/" + nombreViejo);
						File fileRenombrado = new File(ruta + "/" + newNombre);

						if (fileOriginal.renameTo(fileRenombrado)) {
							JOptionPane.showMessageDialog(new JFrame(),
									"El fichero " + newNombre + " ha sido renombrado correctamente ",
									"Fichero Renombrado", JOptionPane.INFORMATION_MESSAGE);
						}
						;
					} else {
						JOptionPane.showMessageDialog(new JFrame(),
								"El fichero " + newNombre + " no ha sido renombrado ", "Fichero Renombrado",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(),
							"El campo de texto del nuevo archivo esta vacio! no se renombrara ningun archivo", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "El fichero que quiere renombrar no existe!", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "El campo de texto esta vacio! no se renombrara ningun archivo",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}

	// PARA EL BOTON COPIAR
	/**
	 * @param ruta
	 * @param ficheroOriginal Metodo que es utilizado en la clase AEV1Controlador
	 *                        para realizar copias de ficheros y directorios. Crea
	 *                        una copia con el mismo nombre que el fichero o
	 *                        directorio original desde el JTextField_Operaciones y
	 *                        le añade "_copia" al final
	 */
	public void copiarArchivo(String ruta, String ficheroOriginal) {
		String nomFichNuevo;
		File f1 = new File(ruta + "/" + ficheroOriginal);
		if (f1.isDirectory()) {
			nomFichNuevo = ficheroOriginal + "_copia";
			File f2 = new File(ruta + "/" + nomFichNuevo);
			f2.mkdir();
			JOptionPane.showMessageDialog(new JFrame(), "El directorio se copio correctamente ", "Fichero Copiado",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			try {
				String extension = "";
				int i = ficheroOriginal.lastIndexOf('.');
				if (i > 0) {
					extension = ficheroOriginal.substring(i + 1);
				}
				String nomFichSinExtension = ficheroOriginal.replaceFirst("[.][^.]+$", "");
				nomFichNuevo = nomFichSinExtension + "_copia." + extension;

				File f2 = new File(ruta + "/" + nomFichNuevo);
				FileReader fr = new FileReader(f1);
				BufferedReader br = new BufferedReader(fr);
				FileWriter fw = new FileWriter(f2);
				BufferedWriter bw = new BufferedWriter(fw);
				String linea = br.readLine();
				while (linea != null) {
					bw.write(linea);
					bw.newLine();
					linea = br.readLine();
				}
				br.close();
				fr.close();
				bw.close();
				fw.close();
				JOptionPane.showMessageDialog(new JFrame(), "El fichero se copio correctamente ", "Fichero Copiado",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(), "Error copiando los ficheros", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}

	}

	// PARA EL BOTON SUPRIMIR
	/**
	 * @param ruta
	 * @param fichero Metodo utilizado en la clase AEV1Controlador para eliminar un
	 *                fichero Elimina el fichero escrito en el
	 *                JTextField_Operaciones despues de preguntarnos si estamos
	 *                seguros
	 */
	public void suprimirArchivo(String ruta, String fichero) {
		File file = new File(ruta + "/" + fichero);
		if (file.exists() && !ruta.isEmpty()) {
			int decision = JOptionPane.showConfirmDialog(null, "Esta seguro de que quiere eliminar el archivo? ",
					"Esta Seguro?", JOptionPane.YES_NO_OPTION);
			if (decision == 0) {
				if (file.delete())
					JOptionPane.showMessageDialog(new JFrame(), "El fichero se elimino correctamente ",
							"Fichero Eliminado", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(new JFrame(),
							"El fichero " + fichero + " no ha podido ser suprimido ", "Error en la eliminacion",
							JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "El fichero " + fichero + " no existe o no esta bien escrito ",
					"Error en la eliminacion", JOptionPane.ERROR_MESSAGE);
		}

	}

	// PARA BOTON MOSTRAR ARCHIVO
	/**
	 * @param fichero
	 * @param vista   Metodo utilizado en la clase AEV1Controlador para mostrar el
	 *                contenido de un fichero. Muestra el fichero que hayamos puesto
	 *                en el JTextField_Operaciones y lo muestra en el JTextArea
	 *                principal. Lee un fichero y lo introduce en un ArrayList de
	 *                String y luego lo escribe en el JTextArea.
	 */
	public void mostrarFichero(String fichero, AEV1Vista vista) {
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
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}

		File file = new File(fichero);
		if (file.exists()) {
			ArrayList<String> arrayLineas = contenidoFichero;
			vista.getTextArea().setText("");
			for (String linea : arrayLineas) {
				vista.getTextArea().append(linea + "\n");
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(),
					"El archivo que solicita mostrar no existe, asegurese de que lo escribe correctamente", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// PARA BOTON BUSCAR
	/**
	 * @param vista
	 * @param textoEnBuscar
	 * @return Metodo al que se llama en la clase AEV1Controlador para buscar
	 *         palabras. Busca en el JTextArea el texto que hayamos puesto en el
	 *         JTextField_Buscar y nos devuelve la cantidad de veces que lo
	 *         encontro, para ello utiliza el ".contains".
	 */
	public int buscarEnElArchivo(AEV1Vista vista, String textoEnBuscar) {
		int contador = 0;
		if (!textoEnBuscar.isEmpty()) {
			String[] textoEnArea = vista.getTextArea().getText().split(" ");
			for (int i = 0; i < textoEnArea.length; i++) {
				if (textoEnArea[i].contains(textoEnBuscar)) {
					contador++;
				}
			}
			JOptionPane.showMessageDialog(new JFrame(),
					"La palabra " + textoEnBuscar + " se encontro " + contador + " veces.", "Buqueda",
					JOptionPane.DEFAULT_OPTION);
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "No hay nada en el campo de busqueda", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
		return contador;
	}

	// PARA BOTON AÑADIR TEXTO
	/**
	 * @param texto
	 * @param vista Metodo al que se llama en la clase AEV1Controlador para añadir
	 *              texto. Realiza un append del texto del JTextField_anyadirTexto
	 *              en el JTextArea.
	 */
	public void anyadirTexto(String texto, AEV1Vista vista) {

		vista.getTextArea().append(texto + "\n");
	}

	// PARA BOTON REEMPLAZAR
	/**
	 * @param textoOriginal
	 * @param textoNuevo
	 * @param vista         Metodo utilizado en la clase AEV1Controlador para
	 *                      reemplazar texto. Reemplaza el texto que haya escrito en
	 *                      el JTextField_Buscar por el que haya en el
	 *                      JTextField_Reemplazar.
	 */
	public void reemplazarTexto(String textoOriginal, String textoNuevo, AEV1Vista vista) {
		String[] textoEnArea = vista.getTextArea().getText().split(" ");
		if (!textoOriginal.isEmpty()) {
			vista.getTextArea().setText("");
			for (int i = 0; i < textoEnArea.length; i++) {
				if (textoEnArea[i].contains(textoOriginal)) {
					vista.getTextArea().append(textoNuevo + " ");
				} else {
					vista.getTextArea().append(textoEnArea[i] + " ");
				}
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "No hay texto en el campo de buscar, no se reemplazaria nada!",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}

	// PARA BOTON DESTACAR
	/**
	 * @param vista
	 * @param textoDestacar Proximamente...
	 */
	public void destacarTexto(AEV1Vista vista, String textoDestacar) {
		String[] textoEnArea = vista.getTextArea().getText().split(" ");
		vista.getTextArea().setText("");

		if (vista.getCheckBoxNegrita().isSelected()) {
			for (int i = 0; i < textoEnArea.length; i++) {
				vista.getTextArea().append(textoEnArea[i] + " ");
			}
		}

	}

	// PARA BOTON GUARDAR CAMBIOS
	/**
	 * @param vista
	 * @param fichero Metodo utilizado en la clase AEV1Controlador para guardar
	 *                cambios. Sobreescribe el fichero que hay escrito en el
	 *                JTextField_Operaciones
	 */
	public void guardarCambios(AEV1Vista vista, String fichero) {
		FileWriter fw;
		if (!fichero.isEmpty()) {
			int decision = JOptionPane.showConfirmDialog(null,
					"Los cambios se guardaran en el fichero que haya escrito, esta seguro de guardar los cambios?",
					"Esta Seguro?", JOptionPane.YES_NO_OPTION);
			if (decision == 0) {
				try {
					fw = new FileWriter(fichero);
					BufferedWriter bw = new BufferedWriter(fw);
					vista.getTextArea().write(bw);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(),
					"No hay escrito ningun fichero sobre el cual se guardasen los cambios", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
