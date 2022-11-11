package aev2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Daniel Muñoz Barbera
 * Metodo principal de la clase AEV2Modelo
 */
public class AEV2Modelo {

	// METODO CONEXION
	/**
	 * @return
	 * Es el metodo base para la conexion hacia la base de datos, utilizado por muchos otros metodos.
	 * Tambien lee un archivo CSV del que saca la URL, usuario y contraseña para conectarse a la DB.
	 */
	public Connection Conexion() {

		String url = null;
		String usuario = null;
		String contraseña = null;

		File loginCSV = new File("login.csv");

		try {
			FileReader fr = new FileReader(loginCSV);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			linea = br.readLine();

			while (linea != null) {
				String datos[] = linea.split(";");

				url = datos[0];
				usuario = datos[1];
				contraseña = datos[2];

				linea = br.readLine();
			}
			br.close();
			fr.close();
			if (contraseña.equals(" "))
				contraseña = "";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, usuario, contraseña);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}

	// METODO PARA CONSULTA
	/**
	 * @param con
	 * @param vista
	 * Metodo para hacer consultas SELECT, INSERT, UPDATE o DELETE, en caso de ser SELECT la muestra en la aplicacion.
	 * En cualquier otro caso pide una confirmacion y aparece un mensaje en la aplicacion si la salida es correcta y no ha dado error.
	 * Utiliza el metodo conexion y una instancia de la clase vista para realizar estas funciones 
	 */
	public void consulta(Connection con, AEV2Vista vista) {
		try {
			vista.getTextArea().setText("");
			String consulta = JOptionPane
					.showInputDialog("Introduce tu consulta SQL de SELECT / INSERT / UPDATE / DELETE: ");
			String consul[] = consulta.split(" ");
			String tipoConsulta = consul[0];
			Statement stmt = con.createStatement();

			if (tipoConsulta.equals("INSERT") || tipoConsulta.equals("UPDATE") || tipoConsulta.equals("DELETE")) {
				int decision = JOptionPane.showConfirmDialog(null, "Esta seguro de que quiere ejecutar esta Query? ",
						"Esta Seguro?", JOptionPane.YES_NO_OPTION);
				if (decision == 0) {
					int resolucion = stmt.executeUpdate(consulta);
					vista.getTextArea().setText("Consulta " + tipoConsulta + " realizada correctamente!");
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Decidiste no hacer la query! Mas vale prevenir...",
							"LOGIN", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				ResultSet rs = stmt.executeQuery(consulta);
				ResultSetMetaData rsmd = rs.getMetaData();
				int numCampos = rsmd.getColumnCount();
				for (int i = 1; i <= numCampos; i++) {
					vista.getTextArea().append(rsmd.getColumnLabel(i) + " - ");
				}
				vista.getTextArea().append("\n");
				vista.getTextArea().append("-----------------------------------------------------" + "\n");
				while (rs.next()) {
					for (int i = 1; i <= numCampos; i++) {
						vista.getTextArea().append(rs.getString(i) + " - ");
					}
					vista.getTextArea().append("\n");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// METODO PARA CERRAR DB
	/**
	 * @param con
	 * Metodo que cierra una conexion con la base de datos por lo tanto en la aplicacion no podrias
	 * hacer uso del metodo loguearte y en consecuencia de ningun otro metodo de esta.
	 */
	public Boolean cerrarDB(Connection con) {
		Boolean conectado = true;
		try {
			int decision = JOptionPane.showConfirmDialog(null,
					"Esta seguro de que quiere desconectar de la base de datos? ", "Esta Seguro?",
					JOptionPane.YES_NO_OPTION);
			if (decision == 0) {
				con.close();
				JOptionPane.showMessageDialog(new JFrame(), "Te has desconectado", "LOGIN",
						JOptionPane.INFORMATION_MESSAGE);
				conectado = false;
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Sigues conectado!",
						"LOGIN", JOptionPane.INFORMATION_MESSAGE);
				conectado = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conectado;
	}

	// METODO PARA ESTRUCTURA BDD
	/**
	 * @param con
	 * @param vista
	 * Metodo que devuelve en la aplicacion la estructura o tablas que componen la base de datos, recoge
	 * el metodo conexion y una vista para mostrar el contenido. Hace uso de la query SHOW TABLES
	 */
	public void estructuraBDD(Connection con, AEV2Vista vista) {
		try {
			vista.getTextArea().setText("");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SHOW TABLES");
			ResultSetMetaData rsmd = rs.getMetaData();
			String nombreColumna = rsmd.getColumnLabel(1);
			vista.getTextArea().setText(nombreColumna + "\n");
			vista.getTextArea().append("---------" + "\n");
			while (rs.next()) {
				vista.getTextArea().append(rs.getString(1) + "\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// METODO PARA ESTRUCTURA TABLA
	/**
	 * @param con
	 * @param vista
	 * Metodo que devuelve en la aplicacion la estructura de una tabla con los datos que la componen, recoge
	 * el metodo conexion y una vista para mostrar el contenido.
	 * Hace uso de la query DESCRIBE + nombre de tabla
	 */
	public void estructuraTabla(Connection con, AEV2Vista vista) {
		try {
			vista.getTextArea().setText("");
			String tabla = JOptionPane.showInputDialog("Dime la tabla: ");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("DESCRIBE " + tabla);
			ResultSetMetaData rsmd = rs.getMetaData();
			vista.getTextArea()
					.setText(rsmd.getColumnLabel(1) + " - " + rsmd.getColumnLabel(2) + " - " + rsmd.getColumnLabel(3)
							+ " - " + rsmd.getColumnLabel(4) + " - " + rsmd.getColumnLabel(5) + " - "
							+ rsmd.getColumnLabel(6) + "\n");
			vista.getTextArea().append("-----------------------------------------------------" + "\n");
			while (rs.next()) {
				vista.getTextArea().append(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3) + " - "
						+ rs.getString(4) + " - " + rs.getString(5) + " - " + rs.getString(6) + "\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// METODO PARA LOGUEAR
	/**
	 * @param con
	 * @return
	 * Metodo que se utiliza para simular un login en la base de datos, hace una query a la tabla users.
	 * Utiliza el recurso que ResulSet.next devuelve True si encuentra un valor y por tanto el user y la pass existen para comprobar
	 * que las credenciales son correctas, por otro lado devuelve False si no encuentra nada lo que simula que las credenciales son incorrectas
	 * Tambien hace uso del metodo conexion.
	 */
	public int Loguear(Connection con) {

		int conexionCorrecta = -1;
		String user = JOptionPane.showInputDialog("Introduce el usuario: ");
		String pass = JOptionPane.showInputDialog("Introduce la contraseña: ");
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT user, pass FROM users WHERE user = '" + user + "' AND pass = '" + pass + "'");

			if (rs.next()) {
				conexionCorrecta = 0;
			} else {
				conexionCorrecta = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexionCorrecta;
	}

	// METODO MOSTRAR TABLA
	/**
	 * @param con
	 * @param vista
	 * Metodo que se utiliza para mostrar todo el contenido de una Tabla, como las dimensiones encuanto columnas puede variar
	 * el metodo se adapta tambien haciendo uso del ResulSetMetaData para calcular cuantas columnas contiene la tabla a mostrar
	 * Tambien hace uso del metodo conexion y una instancia de vista, aparte la consulta se realiza con SELECT * FROM + nombre tabla
	 */
	public void mostrarTabla(Connection con, AEV2Vista vista) {
		try {
			vista.getTextArea().setText("");
			String tabla = JOptionPane.showInputDialog("Dime la tabla: ");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tabla);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numCampos = rsmd.getColumnCount();
			for (int i = 1; i <= numCampos; i++) {
				vista.getTextArea().append(rsmd.getColumnLabel(i) + " - ");
			}
			vista.getTextArea().append("\n");
			vista.getTextArea().append("-----------------------------------------------------" + "\n");
			while (rs.next()) {
				for (int i = 1; i <= numCampos; i++) {
					vista.getTextArea().append(rs.getString(i) + " - ");
				}
				vista.getTextArea().append("\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
