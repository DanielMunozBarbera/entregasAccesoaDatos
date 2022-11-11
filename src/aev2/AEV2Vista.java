package aev2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

/**
 * @author Daniel Muñoz Barbera
 * Metodo principal de la clase AEV2Vista.
 */
public class AEV2Vista {

	private JButton btn_Conectar;
	private JButton btn_Consulta;
	private JButton btn_Desconectar;
	private JButton btn_estructuraBDD;
	private JButton btn_estructuraTabla;
	private JButton btn_Loguear;
	private JButton btn_mostrarTodoTabla;
	private JFrame frmAevAccesoA;
	private JTextArea textArea;
	private final JPanel panel = new JPanel();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AEV2Vista window = new AEV2Vista();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	/**
	 * Metodo constructor de la clase AEV2Vista, su funcion es inicicializar el JFrame que compone el esqueleto de la aplicacion
	 */
	public AEV2Vista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAevAccesoA = new JFrame();
		frmAevAccesoA.setTitle("AEV2 Acceso a Datos - Daniel Muñoz Barbera");
		
		frmAevAccesoA.getContentPane().setBackground(new Color(147, 201, 255));
		frmAevAccesoA.setBounds(100, 100, 618, 398);
		frmAevAccesoA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAevAccesoA.getContentPane().setLayout(null);
		panel.setBackground(new Color(159, 207, 255));
		panel.setBounds(0, 0, 601, 352);
		frmAevAccesoA.getContentPane().add(panel);
		panel.setLayout(null);
		
		btn_Conectar = new JButton("Conectar");
		btn_Conectar.setBounds(23, 238, 89, 23);
		panel.add(btn_Conectar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		scrollPane.setBounds(23, 11, 553, 197);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		btn_estructuraBDD = new JButton("Estructura BDD");
		btn_estructuraBDD.setBounds(136, 238, 129, 23);
		panel.add(btn_estructuraBDD);
		
		btn_estructuraTabla = new JButton("Estructura Tabla");
		btn_estructuraTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_estructuraTabla.setBounds(285, 238, 129, 23);
		panel.add(btn_estructuraTabla);
		
		btn_mostrarTodoTabla = new JButton("Mostrar Todo Tabla");
		btn_mostrarTodoTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_mostrarTodoTabla.setBounds(428, 238, 147, 23);
		panel.add(btn_mostrarTodoTabla);
		
		btn_Consulta = new JButton("Consulta");
		btn_Consulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Consulta.setBounds(136, 291, 89, 23);
		panel.add(btn_Consulta);
		
		btn_Desconectar = new JButton("Desconectar");
		btn_Desconectar.setBounds(482, 318, 109, 23);
		panel.add(btn_Desconectar);
		
		btn_Loguear = new JButton("Loguear");
		btn_Loguear.setBounds(23, 291, 89, 23);
		panel.add(btn_Loguear);
		frmAevAccesoA.setVisible(true);
	}
	
	//BOTONES
	public JButton getBotonConectar() {
		return btn_Conectar;
	}
	
	public JButton getBotonConsulta() {
		return btn_Consulta;
	}
	
	public JButton getBotonDesconectar() {
		return btn_Desconectar;
	}
	
	public JButton getBotonEstructuraBDD() {
		return btn_estructuraBDD;
	}
	
	public JButton getBotonEstructuraTabla() {
		return btn_estructuraTabla;
	}
	
	public JButton getBotonLoguearse() {
		return btn_Loguear;
	}
	
	public JButton getBotonMostrarTodoTabla() {
		return btn_mostrarTodoTabla;
	}
	
	//EL AREA
	public JTextArea getTextArea() {
		return textArea;
	}
	
}
