package aev3;

import static com.mongodb.client.model.Filters.eq;

import static com.mongodb.client.model.Filters.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Base64;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * @author Daniel Muñoz Barbera Clase que constituye el nucleo del
 *         funcionamiento de nuestro programa, donde se construyen todos los
 *         metodos que llamamos al pulsar en los botones
 *
 */
public class AEV3Modelo {
	static MongoCollection<Document> coleccionUsers;
	static MongoCollection<Document> coleccionBooks;

	// METODO PARA CONEXION

	/**
	 * @param vista
	 * @return Boolean - Metodo Conexion de la clase AEV3Modelo, utiliza un archivo
	 *         JSON del que recoge: URL, Puerto, Database y las colecciones. Cada
	 *         una de las conexiones se enlazan con los objetos 'coleccionUsers' y
	 *         'coleccionBooks' de tipo MongoCollection<Document> que creamos
	 *         previamente Ademas comprueba en la coleccion de usuarios si el
	 *         usuario y contraseña introducidos en el programa coindicen con el de
	 *         la base de datos, y de ser afirmativo devuelve una conexion positiva
	 *         al controlador el cual habilita el uso de los demas botones.
	 *
	 */
	public Boolean Conexion(AEV3Vista vista) {

		Boolean conexionCorrecta = false;
		File nuevoArchivo = new File("conexion.json");
		FileReader fr;
		try {
			fr = new FileReader(nuevoArchivo);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			String todo = "";
			while (linea != null) {
				todo = todo + linea;
				linea = br.readLine();
			}
			todo = todo.substring(1);
			JSONObject obj = new JSONObject(todo);

			String ip = obj.getString("ip");
			int puerto = Integer.parseInt(obj.getString("puerto"));
			String db = obj.getString("database");
			String colBooks = obj.getString("collection1");
			String colUsers = obj.getString("collection2");

			Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
			mongoLogger.setLevel(Level.SEVERE);

			MongoClient mongoClient = new MongoClient(ip, puerto);
			MongoDatabase database = mongoClient.getDatabase(db);
			coleccionUsers = database.getCollection(colUsers);

			String userIntroducido = vista.getTextUsuario();
			String passIntroducido = vista.getTextPassword();

			MongoCursor<Document> cursor = coleccionUsers.find().iterator();
			while (cursor.hasNext()) {
				JSONObject obj2 = new JSONObject(cursor.next().toJson());
				if (userIntroducido.equals(obj2.get("user")) && passIntroducido.equals(obj2.getString("pass"))) {
					conexionCorrecta = true;
					coleccionBooks = database.getCollection(colBooks);

				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conexionCorrecta;
	}

	// METODO PARA RESUMEN

	/**
	 * @param vista - Metodo Resumen de la clase AEV3Modelo, recoge todos los
	 *              documentos que hay en la coleccion y los plasma en el JTextArea
	 *              de nuestro programa.
	 */
	public void Resumen(AEV3Vista vista) {
		vista.getTextArea().setText("");
		MongoCursor<Document> cursor = coleccionBooks.find().iterator();
		vista.getTextArea().append("Total de libros en la biblioteca: " + coleccionBooks.count());
		vista.getTextArea().append("\n");
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			vista.getTextArea().append("Id: " + obj.getInt("Id") + ". Titulo: " + obj.getString("Titulo"));
			vista.getTextArea().append("\n");
		}

	}

	// METODO PARA CREAR

	/**
	 * Metodo Crear de la clase AEV3Modelo, recoge una nuevaID con el metodo nuevoID
	 * de la clase AEV3Modelo, luego solicita via JOptionPane todos los datos
	 * necesarios para construir un nuevo documento, luego crea un documento y le
	 * añade esa informacion, la ruta de la fotografia es convertida a String Base64
	 * previamente.
	 */
	public void Crear() {
		int nuevaID = nuevoID();
		String nuevoTitulo = JOptionPane.showInputDialog("Introduce Titulo: ");
		String nuevoAutor = JOptionPane.showInputDialog("Introduce Autor: ");
		int nuevoAnyo_Nac = Integer.parseInt(JOptionPane.showInputDialog("Introduce Anyo de nacimiento: "));
		int nuevoAnyo_Pub = Integer.parseInt(JOptionPane.showInputDialog("Introduce Anyo de publicacion: "));
		String nuevoEditorial = JOptionPane.showInputDialog("Introduce Editorial: ");
		int nuevoNumeroPaginas = Integer.parseInt(JOptionPane.showInputDialog("Introduce Numero de paginas: "));
		String nuevoFoto = JOptionPane.showInputDialog("Introduce la ruta de la fotografia: ");
		File fotoNueva = new File(nuevoFoto);
		try {
			byte[] fileContent = Files.readAllBytes(fotoNueva.toPath());
			String encodedString = Base64.encodeBase64String(fileContent);
			Document doc = new Document();
			doc.append("Id", nuevaID);
			doc.append("Titulo", nuevoTitulo);
			doc.append("Autor", nuevoAutor);
			doc.append("Anyo_nacimiento", nuevoAnyo_Nac);
			doc.append("Anyo_publicacion", nuevoAnyo_Pub);
			doc.append("Editorial", nuevoEditorial);
			doc.append("Numero_paginas", nuevoNumeroPaginas);
			doc.append("Thumbnail", encodedString);
			coleccionBooks.insertOne(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// METODO PARA MOSTRAR

	/**
	 * @param vista - Metodo Mostrar de la clase AEV3Modelo, nos solicita un ID con
	 *              un JOptionPane y despues nos muestra el resto de campos de ese
	 *              documento en concreto en el JTextArea de nuestro programa,
	 *              tambien nos muestar la imagen vinculada a ese documento en una
	 *              ventana emerenge JOptionPane.
	 */
	public void Mostrar(AEV3Vista vista) {
		vista.getTextArea().setText("");
		int idSolicitada = Integer
				.parseInt(JOptionPane.showInputDialog("Dime la ID del libro del que quieres informacion: "));
		Bson query = eq("Id", idSolicitada);
		MongoCursor<Document> cursor = coleccionBooks.find(query).iterator();
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			vista.getTextArea().append("Titulo: " + obj.getString("Titulo"));
			vista.getTextArea().append("\nAutor: " + obj.getString("Autor"));
			vista.getTextArea().append("\nAnyo de nacimiento: " + obj.getInt("Anyo_nacimiento"));
			vista.getTextArea().append("\nAnyo de publicacion: " + obj.getInt("Anyo_publicacion"));
			vista.getTextArea().append("\nEditorial: " + obj.getString("Editorial"));
			vista.getTextArea().append("\nNumero de paginas: " + obj.getInt("Numero_paginas"));
			byte[] btDataFile = Base64.decodeBase64(obj.getString("Thumbnail"));
			try {
				BufferedImage imagen = ImageIO.read(new ByteArrayInputStream(btDataFile));
				ImageIcon imagenIcon = new ImageIcon(imagen);
				JOptionPane.showMessageDialog(null, "", "", JOptionPane.INFORMATION_MESSAGE, imagenIcon);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// METODO PARA ACTUALIZAR

	/**
	 * Metodo Actualizar de la clase AEV3Modelo, solicita la ID de un libro que
	 * utilizamos en una query para encontrar el documento especifico sobre el que
	 * se realizarian los cambios, luego nos pregunta por todos los campos exepto la
	 * Id utilizando un JOptionPane, en caso de responder con '=' no realizara
	 * ningun cambio y en caso de responder cualquier otra cosa, antes de realizar
	 * el cambio nos pregunta de nuevo si queremos o no realizar el cambio.
	 */
	public void Actualizar() {
		int idSolicitada = Integer
				.parseInt(JOptionPane.showInputDialog("Dime la ID del libro del que quieres actualizar: "));
		Bson query = eq("Id", idSolicitada);
		String nuevoCampo = JOptionPane
				.showInputDialog("Dime un nuevo Titulo o introduce '=' para dejarlo como esta: ");
		int nuevoCampoInt;
		if (!nuevoCampo.equals("=")) {
			int decision = JOptionPane.showConfirmDialog(null, "Esta seguro de que desea borrar este Libro? ",
					"Esta Seguro?", JOptionPane.YES_NO_OPTION);
			if (decision == 0) {
				coleccionBooks.updateOne(query, new Document("$set", new Document("Titulo", nuevoCampo)));
			}
		}
		nuevoCampo = JOptionPane.showInputDialog("Dime un nuevo Autor o introduce '=' para dejarlo como esta: ");
		if (!nuevoCampo.equals("=")) {
			int decision = JOptionPane.showConfirmDialog(null, "Esta seguro de que desea borrar este Libro? ",
					"Esta Seguro?", JOptionPane.YES_NO_OPTION);
			if (decision == 0) {
				coleccionBooks.updateOne(query, new Document("$set", new Document("Autor", nuevoCampo)));
			}
		}
		nuevoCampo = JOptionPane
				.showInputDialog("Dime un nuevo Anyo de Nacimiento o introduce '=' para dejarlo como esta: ");
		if (!nuevoCampo.equals("=")) {
			nuevoCampoInt = Integer.parseInt(nuevoCampo);
			int decision = JOptionPane.showConfirmDialog(null, "Esta seguro de que desea borrar este Libro? ",
					"Esta Seguro?", JOptionPane.YES_NO_OPTION);
			if (decision == 0) {
				coleccionBooks.updateOne(query, new Document("$set", new Document("Anyo_nacimiento", nuevoCampoInt)));
			}
		}
		nuevoCampo = JOptionPane
				.showInputDialog("Dime un nuevo Anyo de Publicacion o introduce '=' para dejarlo como esta: ");
		if (!nuevoCampo.equals("=")) {
			nuevoCampoInt = Integer.parseInt(nuevoCampo);
			int decision = JOptionPane.showConfirmDialog(null, "Esta seguro de que desea borrar este Libro? ",
					"Esta Seguro?", JOptionPane.YES_NO_OPTION);
			if (decision == 0) {
				coleccionBooks.updateOne(query, new Document("$set", new Document("Anyo_publicacion", nuevoCampoInt)));
			}
		}
		nuevoCampo = JOptionPane.showInputDialog("Dime una nueva Editorial o introduce '=' para dejarlo como esta: ");
		if (!nuevoCampo.equals("=")) {
			int decision = JOptionPane.showConfirmDialog(null, "Esta seguro de que desea borrar este Libro? ",
					"Esta Seguro?", JOptionPane.YES_NO_OPTION);
			if (decision == 0) {
				coleccionBooks.updateOne(query, new Document("$set", new Document("Editorial", nuevoCampo)));
			}
		}
		nuevoCampo = JOptionPane
				.showInputDialog("Dime un nuevo Numero de paginas o introduce '=' para dejarlo como esta: ");
		if (!nuevoCampo.equals("=")) {
			nuevoCampoInt = Integer.parseInt(nuevoCampo);
			int decision = JOptionPane.showConfirmDialog(null, "Esta seguro de que desea borrar este Libro? ",
					"Esta Seguro?", JOptionPane.YES_NO_OPTION);
			if (decision == 0) {
				coleccionBooks.updateOne(query, new Document("$set", new Document("Numero_paginas", nuevoCampoInt)));
			}

		}
		nuevoCampo = JOptionPane
				.showInputDialog("Dime una nueva ruta para la imagen o introduce '=' para dejarlo como esta: ");
		if (!nuevoCampo.equals("=")) {
			File nuevoArchivo = new File(nuevoCampo);
			byte[] fileContent;
			try {
				fileContent = Files.readAllBytes(nuevoArchivo.toPath());
				String encodedString = Base64.encodeBase64String(fileContent);
				int decision = JOptionPane.showConfirmDialog(null, "Esta seguro de que desea borrar este Libro? ",
						"Esta Seguro?", JOptionPane.YES_NO_OPTION);
				if (decision == 0) {
					coleccionBooks.updateOne(query, new Document("$set", new Document("Thumbnail", encodedString)));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	// METODO PARA BORRAR

	/**
	 * Metodo Borrar de la clase AEV3Modelo, nos solicita una ID para encontrar el
	 * documento concreto que deseamos Borrar, luego nos vuelve a preguntar si
	 * estamos seguros de que queremos borrar el documento y en caso afirmativo lo
	 * borra.
	 */
	public void Borrar() {
		int idSolicitada = Integer
				.parseInt(JOptionPane.showInputDialog("Dime la ID del libro del que quieres borrar: "));
		int decision = JOptionPane.showConfirmDialog(null, "Esta seguro de que desea borrar este Libro? ",
				"Esta Seguro?", JOptionPane.YES_NO_OPTION);
		if (decision == 0) {
			coleccionBooks.deleteOne(eq("Id", idSolicitada));
			JOptionPane.showMessageDialog(new JFrame(), "Libro borrado correctamente", "Borrar Libro",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "No se borro el libro", "Borrar Libro",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	// METODO PARA CONSULTA

	/**
	 * @param vista - Metodo Consulta de la clase AEV3Modelo, Es donde utilizamos
	 *              todos los JRadioButton de nuestra aplicacion, utilizamos el
	 *              grupo de los campos y el que este seleccionado se le asigna su
	 *              valor a una variable que utilizaremos como "clave" en una query,
	 *              lo mismo para los JRadioButton del grupo de tipo eq/gte/lte son
	 *              utilizados para crear un tipo de query diferente. Esta
	 *              programado para obviar el documento cuyo anyo de nacimiento es
	 *              anonimo debido a que eso hacia que el programa cayese
	 */
	public void Consulta(AEV3Vista vista) {
		vista.getTextArea().setText("");
		Bson query;
		String valor = vista.getTextValor();
		String clave = "";
		if (vista.getRadioID())
			clave = "Id";
		else if (vista.getRadioTitulo())
			clave = "Titulo";
		else if (vista.getRadioAutor())
			clave = "Autor";
		else if (vista.getRadioANac())
			clave = "Anyo_nacimiento";
		else if (vista.getRadioAPub())
			clave = "Anyo_publicacion";
		else if (vista.getRadioEditorial()) {
			clave = "Editorial";
		} else if (vista.getRadioNPag()) {
			clave = "Numero_paginas";
		}

		int valorInt = Integer.parseInt(valor);

		if (vista.getRadioGTE()) {
			if (vista.getRadioID() || vista.getRadioANac() || vista.getRadioAPub() || vista.getRadioNPag()) {
				query = gte(clave, valorInt);
			} else {
				query = gte(clave, valor);
			}
			MongoCursor<Document> cursor = coleccionBooks.find(query).iterator();
			while (cursor.hasNext()) {
				JSONObject obj = new JSONObject(cursor.next().toJson());
				try {
					vista.getTextArea()
							.append("\nID: " + obj.getInt("Id") + ". Titulo: " + obj.getString("Titulo")
									+ ". Anyo de Nacimiento: " + obj.getInt("Anyo_nacimiento")
									+ ". Anyo de Publicacion: " + obj.getInt("Anyo_publicacion") + ". Editorial: "
									+ obj.getString("Editorial") + ". NumPaginas: " + obj.getInt("Numero_paginas"));
				} catch (Exception e) {

				}
			}
		} else if (vista.getRadioEQ()) {
			if (vista.getRadioID() || vista.getRadioANac() || vista.getRadioAPub() || vista.getRadioNPag()) {
				query = eq(clave, valorInt);
			} else {
				query = eq(clave, valor);
			}
			MongoCursor<Document> cursor = coleccionBooks.find(query).iterator();
			while (cursor.hasNext()) {
				JSONObject obj = new JSONObject(cursor.next().toJson());
				try {
					vista.getTextArea()
							.append("\nID: " + obj.getInt("Id") + ". Titulo: " + obj.getString("Titulo")
									+ ". Anyo de Nacimiento: " + obj.getInt("Anyo_nacimiento")
									+ ". Anyo de Publicacion: " + obj.getInt("Anyo_publicacion") + ". Editorial: "
									+ obj.getString("Editorial") + ". NumPaginas: " + obj.getInt("Numero_paginas"));
				} catch (Exception e) {

				}
			}
		} else if (vista.getRadioLTE()) {
			if (vista.getRadioID() || vista.getRadioANac() || vista.getRadioAPub() || vista.getRadioNPag()) {
				query = lte(clave, valorInt);
			} else {
				query = lte(clave, valor);
			}
			MongoCursor<Document> cursor = coleccionBooks.find(query).iterator();
			while (cursor.hasNext()) {
				JSONObject obj = new JSONObject(cursor.next().toJson());
				try {
					vista.getTextArea()
							.append("\nID: " + obj.getInt("Id") + ". Titulo: " + obj.getString("Titulo")
									+ ". Anyo de Nacimiento: " + obj.getInt("Anyo_nacimiento")
									+ ". Anyo de Publicacion: " + obj.getInt("Anyo_publicacion") + ". Editorial: "
									+ obj.getString("Editorial") + ". NumPaginas: " + obj.getInt("Numero_paginas"));
				} catch (Exception e) {

				}

			}
		}

	}

	// METODO PARA ID AUTOMATICO
	/**
	 * @return int - Metodo de la clase AEV3Modelo que es utilizado por el metodo
	 *         Crear de la misma clase, lo utilizamos para nunca duplicar una Id.
	 *         Utilizando una Lista de Integers recorremos todos los documentos de
	 *         la coleccion y añadimos los ID's a la lista, que son de tipo Int, mas
	 *         adelante recorremos la Lista y nos quedamos con el valor mas alto al
	 *         que le sumamos 1, de esta forma podemos crear los documentos que
	 *         queramos sin preocuparnos de que haya redundancia en los Id.
	 */
	public int nuevoID() {
		List<Integer> listaDeID = new ArrayList();
		MongoCursor<Document> cursor = coleccionBooks.find().iterator();
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			listaDeID.add(obj.getInt("Id"));
		}

		int numero = 0;
		int nuevaId = 0;
		for (Object num : listaDeID) {
			int comparativa = (Integer) num;
			if (numero < comparativa)
				numero = comparativa;
		}
		nuevaId = numero + 1;

		return nuevaId;

	}

}
