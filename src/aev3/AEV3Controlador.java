package aev3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Daniel Muñoz Barbera
 * Clase que actua como Controlador en nuestro Modelo MVC, en ella se unen los metodos junto a los elementos de la vista
 *
 */
public class AEV3Controlador {

	private AEV3Modelo modelo;
	private AEV3Vista vista;
	public Boolean conectado = false;
	private ActionListener actionListenerConexion, actionListenerCrear, actionListenerMostrar;
	private ActionListener actionListenerActualizar, actionListenerBorrar, actionListenerConsultar;
	private ActionListener actionListenerResumen;
	
	public AEV3Controlador(AEV3Modelo modelo, AEV3Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		control();
	}

	/**
	 * Metodo principal de la clase AEV3Controlador, en este metodo se engloban todos los ActionListener de nuestro programa y se ponen elementos de la vista a la escucha de metodos de la clase AEV3Modelo 
	 */
	public void control() {

		// BOTON CONECTAR
		actionListenerConexion = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (conectado == false) {
					conectado = modelo.Conexion(vista);
					if (conectado == false) {
						JOptionPane.showMessageDialog(new JFrame(), "Login Incorrecto", "LOGIN",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "Conexion realizada correctamente", "LOGIN",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Ya estas conectado", "LOGIN",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		};
		vista.getBotonConnect().addActionListener(actionListenerConexion);

		// BOTON CREAR
		actionListenerCrear = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if(conectado == false) {
					JOptionPane.showMessageDialog(new JFrame(), "Debes estar conectado para utilizar esta funcion", "LOGIN",
							JOptionPane.INFORMATION_MESSAGE);
				}else {
					modelo.Crear();
				}
			}
		};
		vista.getBotonCrear().addActionListener(actionListenerCrear);

		// BOTON MOSTRAR
		actionListenerMostrar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if(conectado == false) {
					JOptionPane.showMessageDialog(new JFrame(), "Debes estar conectado para utilizar esta funcion", "LOGIN",
							JOptionPane.INFORMATION_MESSAGE);
				}else {
					modelo.Mostrar(vista);
				}
			}
		};
		vista.getBotonMostrar().addActionListener(actionListenerMostrar);

		// BOTON ACTUALIZAR
		actionListenerActualizar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if(conectado == false) {
					JOptionPane.showMessageDialog(new JFrame(), "Debes estar conectado para utilizar esta funcion", "LOGIN",
							JOptionPane.INFORMATION_MESSAGE);
				}else {
					modelo.Actualizar();
				}
			}
		};
		vista.getBotonActualizar().addActionListener(actionListenerActualizar);

		// BOTON BORRAR
		actionListenerBorrar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if(conectado == false) {
					JOptionPane.showMessageDialog(new JFrame(), "Debes estar conectado para utilizar esta funcion", "LOGIN",
							JOptionPane.INFORMATION_MESSAGE);
				}else {
					modelo.Borrar();
				}
			}
		};
		vista.getBotonBorrar().addActionListener(actionListenerBorrar);

		// BOTON CONSULTAR
		actionListenerConsultar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if(conectado == false) {
					JOptionPane.showMessageDialog(new JFrame(), "Debes estar conectado para utilizar esta funcion", "LOGIN",
							JOptionPane.INFORMATION_MESSAGE);
				}else {
					modelo.Consulta(vista);
				}
			}
		};
		vista.getBotonConsultar().addActionListener(actionListenerConsultar);
		
		// BOTON RESUMEN
		actionListenerResumen = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent){
				if(conectado == false) {
					JOptionPane.showMessageDialog(new JFrame(), "Debes estar conectado para utilizar esta funcion", "LOGIN",
							JOptionPane.INFORMATION_MESSAGE);
				}else {
					modelo.Resumen(vista);
				}
			}
		};
		vista.getBotonResumen().addActionListener(actionListenerResumen);

	}

}
