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

public class AEV1Modelo {

	// PARA EL BOTON DE MOSTRAR INFORMACION
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
	public void crearArchivo(String nombre, String ruta) {
		File file = new File(ruta + "/" + nombre);

		if (!ruta.isEmpty()) {
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
			JOptionPane.showMessageDialog(new JFrame(), "No hay texto en el campo del directorio!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	// PARA EL BOTON DE RENOMBRAR
	public void renombrarArchivo(String ruta, String nombreViejo, AEV1Vista vista) {
		if (!vista.getTextFieldOperaciones().getText().isEmpty()) {
			String newNombre = JOptionPane.showInputDialog("Introduzca el nuevo nombre del fichero: ");
			if (!newNombre.isEmpty()) {
				int decision = JOptionPane.showConfirmDialog(null, "Esta seguro de que quiere renombrar el archivo? ",
						"Esta Seguro?", JOptionPane.YES_NO_OPTION);

				if (decision == 0) {
					File fileOriginal = new File(ruta + "/" + nombreViejo);
					File fileRenombrado = new File(ruta + "/" + newNombre);

					if (fileOriginal.renameTo(fileRenombrado)) {
						JOptionPane.showMessageDialog(new JFrame(),
								"El fichero " + newNombre + " ha sido renombrado correctamente ", "Fichero Renombrado",
								JOptionPane.INFORMATION_MESSAGE);
					}
					;
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "El fichero " + newNombre + " no ha sido renombrado ",
							"Fichero Renombrado", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(new JFrame(),
						"El campo de texto del nuevo archivo esta vacio! no se renombrara ningun archivo", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(new JFrame(), "El campo de texto esta vacio! no se renombrara ningun archivo",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}

	// PARA EL BOTON COPIAR
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

	// PARA BOTON MOSTRAR FICHERO
	public ArrayList<String> contenidoFichero(String fichero) {
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

		return contenidoFichero;
	}

	public void mostrarArchivo(String fichero, AEV1Vista vista) {
		ArrayList<String> arrayLineas = this.contenidoFichero(fichero);
		vista.getTextArea().setText("");
		for (String linea : arrayLineas) {
			vista.getTextArea().append(linea + "\n");
		}

	}

	// PARA BOTON BUSCAR
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
		}else {
			JOptionPane.showMessageDialog(new JFrame(), "No hay nada en el campo de busqueda", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return contador;
	}

	// PARA BOTON AÑADIR TEXTO
	public void anyadirTexto(String texto, AEV1Vista vista) {
		vista.getTextArea().append(texto + "\n");
	}

	// PARA BOTON REEMPLAZAR
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
	public void guardarCambios(AEV1Vista vista, String fichero) {
		FileWriter fw;
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
	}

}
