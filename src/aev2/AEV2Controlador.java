package aev2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * Metodo principal de la clase AEV2Controlador
 */
public class AEV2Controlador {

	private AEV2Modelo modelo;
	private AEV2Vista vista;
	private ActionListener actionListenerConexion, actionListenerLoguearse, actionListenerEstructuraBDD;
	private ActionListener actionListenerEstructuraTabla, actionListenerMostrarTodoTabla, actionListenerConsulta;
	private ActionListener actionListenerCerrarDB;
	public Boolean logueado = false;
	public Boolean conectado = false;

	/**
	 * @param modelo
	 * @param vista
	 * Metodo constructor de la clase AEV2Controlador, utiliza una instancia de sus iguales Modelo y Vista
	 * Ademas hace una llamada al metodo Control donde se pone a la escucha todos los elementos de la Vista
	 * 
	 */
	public AEV2Controlador(AEV2Modelo modelo, AEV2Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		control();
	}

	/**
	 * Metodo de la clase AEV2Controlador que contiene todos los ActionListener de los elementos que hay en la Vista
	 * Los pone a la escucha y enlaza los metodos de la clase AEV2Modelo con los de AEV2Vista
	 */
	public void control() {

		// BOTON CONECTAR
		actionListenerConexion = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (conectado == false) {
					modelo.Conexion();
					conectado = true;
					JOptionPane.showMessageDialog(new JFrame(), "La conexion se realizo correctamente", "Conexion BDD",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Ya estas conectado! ", "LOGIN",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		};
		vista.getBotonConectar().addActionListener(actionListenerConexion);
		
		// BOTON CONSULTA
		actionListenerConsulta = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if(logueado == true) {
					modelo.consulta(modelo.Conexion(), vista);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "No estas logueado, no puedes usar esta funcion!", "LOGIN",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		};
		vista.getBotonConsulta().addActionListener(actionListenerConsulta);
		
		// BOTON CERRAR DB
		actionListenerCerrarDB = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent){
				if(conectado == true) {
					conectado = modelo.cerrarDB(modelo.Conexion());
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "No estas conectado, no puedes desconectarte, tiene logica", "LOGIN",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		};
		vista.getBotonDesconectar().addActionListener(actionListenerCerrarDB);
		
		// BOTON ESTRUCTURA BDD
		actionListenerEstructuraBDD = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if(logueado == true) {
					modelo.estructuraBDD(modelo.Conexion(), vista);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "No estas logueado, no puedes usar esta funcion!", "LOGIN",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		};
		vista.getBotonEstructuraBDD().addActionListener(actionListenerEstructuraBDD);
		
		// BOTON ESTRUCTURA TABLA
		actionListenerEstructuraTabla = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if(logueado == true) {
					modelo.estructuraTabla(modelo.Conexion(), vista);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "No estas logueado, no puedes usar esta funcion!", "LOGIN",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		};
		vista.getBotonEstructuraTabla().addActionListener(actionListenerEstructuraTabla);
		
		
		// BOTON LOGUEARSE
		actionListenerLoguearse = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (conectado == false) {
					JOptionPane.showMessageDialog(new JFrame(), "No estas conectado, contectate primero! ", "LOGIN",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (logueado == false) {
						int conexionCorrecta = modelo.Loguear(modelo.Conexion());
						if (conexionCorrecta == 0) {
							logueado = true;
							JOptionPane.showMessageDialog(new JFrame(), "Conexion Correcta! ", "LOGIN",
									JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(new JFrame(), "Login Incorrecto! ", "LOGIN",
									JOptionPane.INFORMATION_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(new JFrame(), "Ya estas logueado! ", "LOGIN",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		};
		vista.getBotonLoguearse().addActionListener(actionListenerLoguearse);
		
		// BOTON MOSTRAR TABLA
		actionListenerMostrarTodoTabla = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if(logueado == true) {
					modelo.mostrarTabla(modelo.Conexion(), vista);
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "No estas logueado, no puedes usar esta funcion!", "LOGIN",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		};
		vista.getBotonMostrarTodoTabla().addActionListener(actionListenerMostrarTodoTabla);
	}

}
