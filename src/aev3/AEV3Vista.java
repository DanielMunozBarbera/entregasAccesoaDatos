package aev3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.TextArea;
import java.awt.TextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

/**
 * @author Daniel Muñoz Barbera - Clase que conforma el esqueleto de nuestra
 *         aplicacion, en ella se definen todos los elementos visuales como los
 *         botones, textArea, radioButtons, textFields y etiquetas de texto
 *         (JLabels). Tambien se definen los metodos para acceder a estos que son utilizados por las clases AEV3Modelo y AEV3Controlador
 *
 */
public class AEV3Vista {

	private JFrame frmAev;
	private JTextField textField_Usuario;
	private JTextField textField_Password;
	private JTextField textField_Valor;
	private JButton btn_Connect, btn_Crear, btn_Mostrar, btn_Actualizar, btn_Borrar, btn_Consultar, btn_Resumen;
	private JTextArea textArea;
	private final ButtonGroup buttonGroupTipos = new ButtonGroup();
	private final ButtonGroup buttonGroupCampos = new ButtonGroup();
	private JRadioButton rdbtn_gte, rdbtn_eq, rdbtn_lte;
	private JRadioButton rdbtn_Id, rdbtn_Titulo, rdbtn_A_Nac, rdbtn_A_Pub, rdbtn_N_Pag, rdbtn_Editorial, rdbtn_Autor;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AEV3Vista window = new AEV3Vista();
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
	public AEV3Vista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAev = new JFrame();
		frmAev.setBackground(new Color(233, 198, 158));
		frmAev.getContentPane().setBackground(new Color(255, 196, 136));
		frmAev.setTitle("AEV3 MongoDB");
		frmAev.setBounds(100, 100, 472, 503);
		frmAev.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAev.getContentPane().setLayout(null);

		JLabel lbl_User = new JLabel("User");
		lbl_User.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_User.setBounds(25, 26, 49, 14);
		frmAev.getContentPane().add(lbl_User);

		textField_Usuario = new JTextField();
		textField_Usuario.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		textField_Usuario.setBounds(56, 23, 96, 20);
		frmAev.getContentPane().add(textField_Usuario);
		textField_Usuario.setColumns(10);

		JLabel lbl_Password = new JLabel("Password");
		lbl_Password.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_Password.setBounds(162, 26, 76, 14);
		frmAev.getContentPane().add(lbl_Password);

		textField_Password = new JTextField();
		textField_Password.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		textField_Password.setBounds(227, 23, 96, 20);
		frmAev.getContentPane().add(textField_Password);
		textField_Password.setColumns(10);

		btn_Connect = new JButton("Connect");
		btn_Connect.setBackground(new Color(233, 198, 158));
		btn_Connect.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		btn_Connect.setBounds(348, 22, 89, 23);
		frmAev.getContentPane().add(btn_Connect);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3));
		scrollPane.setBounds(15, 91, 433, 241);
		frmAev.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		textArea.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		scrollPane.setViewportView(textArea);

		btn_Crear = new JButton("Crear");
		btn_Crear.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		btn_Crear.setBackground(new Color(233, 198, 158));
		btn_Crear.setBounds(15, 355, 89, 23);
		frmAev.getContentPane().add(btn_Crear);

		btn_Mostrar = new JButton("Mostrar");
		btn_Mostrar.setBackground(new Color(233, 198, 158));
		btn_Mostrar.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		btn_Mostrar.setBounds(122, 355, 89, 23);
		frmAev.getContentPane().add(btn_Mostrar);

		btn_Actualizar = new JButton("Actualizar");
		btn_Actualizar.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		btn_Actualizar.setBackground(new Color(233, 198, 158));
		btn_Actualizar.setBounds(227, 355, 96, 23);
		frmAev.getContentPane().add(btn_Actualizar);

		btn_Borrar = new JButton("Borrar");
		btn_Borrar.setBackground(new Color(233, 198, 158));
		btn_Borrar.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		btn_Borrar.setBounds(348, 355, 89, 23);
		frmAev.getContentPane().add(btn_Borrar);

		JLabel lbl_Campo = new JLabel("Campo");
		lbl_Campo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_Campo.setBounds(15, 416, 49, 14);
		frmAev.getContentPane().add(lbl_Campo);

		JLabel lblNewLabel = new JLabel("Valor");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(368, 395, 49, 14);
		frmAev.getContentPane().add(lblNewLabel);

		textField_Valor = new JTextField();
		textField_Valor.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		textField_Valor.setBounds(335, 413, 102, 20);
		frmAev.getContentPane().add(textField_Valor);
		textField_Valor.setColumns(10);

		rdbtn_gte = new JRadioButton("GTE");
		rdbtn_gte.setBackground(new Color(255, 196, 136));
		buttonGroupTipos.add(rdbtn_gte);
		rdbtn_gte.setBounds(56, 437, 59, 23);
		frmAev.getContentPane().add(rdbtn_gte);

		rdbtn_eq = new JRadioButton("EQ");
		rdbtn_eq.setBackground(new Color(255, 196, 136));
		buttonGroupTipos.add(rdbtn_eq);
		rdbtn_eq.setBounds(189, 437, 49, 23);
		frmAev.getContentPane().add(rdbtn_eq);

		rdbtn_lte = new JRadioButton("LTE");
		rdbtn_lte.setBackground(new Color(255, 196, 136));
		buttonGroupTipos.add(rdbtn_lte);
		rdbtn_lte.setBounds(122, 437, 49, 23);
		frmAev.getContentPane().add(rdbtn_lte);

		btn_Consultar = new JButton("Consultar");
		btn_Consultar.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		btn_Consultar.setBackground(new Color(233, 198, 158));
		btn_Consultar.setBounds(335, 437, 102, 23);
		frmAev.getContentPane().add(btn_Consultar);

		btn_Resumen = new JButton("Resumen");
		btn_Resumen.setBackground(new Color(233, 198, 158));
		btn_Resumen.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		btn_Resumen.setBounds(15, 65, 109, 23);
		frmAev.getContentPane().add(btn_Resumen);

		JLabel lbl_Tipo = new JLabel("Tipo");
		lbl_Tipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_Tipo.setBounds(15, 441, 49, 14);
		frmAev.getContentPane().add(lbl_Tipo);

		rdbtn_Id = new JRadioButton("id");
		rdbtn_Id.setBackground(new Color(255, 196, 136));
		buttonGroupCampos.add(rdbtn_Id);
		rdbtn_Id.setBounds(49, 386, 49, 23);
		frmAev.getContentPane().add(rdbtn_Id);

		rdbtn_Titulo = new JRadioButton("Titulo");
		rdbtn_Titulo.setBackground(new Color(255, 196, 136));
		buttonGroupCampos.add(rdbtn_Titulo);
		rdbtn_Titulo.setBounds(100, 385, 59, 23);
		frmAev.getContentPane().add(rdbtn_Titulo);

		rdbtn_A_Nac = new JRadioButton("A_Nac");
		rdbtn_A_Nac.setBackground(new Color(255, 196, 136));
		buttonGroupCampos.add(rdbtn_A_Nac);
		rdbtn_A_Nac.setBounds(162, 385, 76, 23);
		frmAev.getContentPane().add(rdbtn_A_Nac);

		rdbtn_A_Pub = new JRadioButton("A_Pub");
		rdbtn_A_Pub.setBackground(new Color(255, 196, 136));
		buttonGroupCampos.add(rdbtn_A_Pub);
		rdbtn_A_Pub.setBounds(237, 385, 111, 23);
		frmAev.getContentPane().add(rdbtn_A_Pub);

		rdbtn_Editorial = new JRadioButton("Editorial");
		rdbtn_Editorial.setBackground(new Color(255, 196, 136));
		buttonGroupCampos.add(rdbtn_Editorial);
		rdbtn_Editorial.setBounds(56, 412, 76, 23);
		frmAev.getContentPane().add(rdbtn_Editorial);

		rdbtn_Autor = new JRadioButton("Autor");
		rdbtn_Autor.setBackground(new Color(255, 196, 136));
		buttonGroupCampos.add(rdbtn_Autor);
		rdbtn_Autor.setBounds(134, 412, 89, 23);
		frmAev.getContentPane().add(rdbtn_Autor);

		rdbtn_N_Pag = new JRadioButton("N_Pag");
		rdbtn_N_Pag.setBackground(new Color(255, 196, 136));
		buttonGroupCampos.add(rdbtn_N_Pag);
		rdbtn_N_Pag.setBounds(235, 412, 76, 23);
		frmAev.getContentPane().add(rdbtn_N_Pag);

		frmAev.setVisible(true);
	}

	// JTextField
	public String getTextUsuario() {
		return textField_Usuario.getText();
	}

	public String getTextPassword() {
		return textField_Password.getText();
	}

	public String getTextValor() {
		return textField_Valor.getText();
	}

	// JButtons
	public JButton getBotonConnect() {
		return btn_Connect;
	}

	public JButton getBotonCrear() {
		return btn_Crear;
	}

	public JButton getBotonMostrar() {
		return btn_Mostrar;
	}

	public JButton getBotonActualizar() {
		return btn_Actualizar;
	}

	public JButton getBotonBorrar() {
		return btn_Borrar;
	}

	public JButton getBotonConsultar() {
		return btn_Consultar;
	}

	public JButton getBotonResumen() {
		return btn_Resumen;
	}

	// EL AREA
	public JTextArea getTextArea() {
		return textArea;
	}

	// RADIO BUTTONS
	public Boolean getRadioEQ() {
		return rdbtn_eq.isSelected();
	}

	public Boolean getRadioGTE() {
		return rdbtn_gte.isSelected();
	}

	public Boolean getRadioLTE() {
		return rdbtn_lte.isSelected();
	}

	public Boolean getRadioID() {
		return rdbtn_Id.isSelected();
	}

	public Boolean getRadioTitulo() {
		return rdbtn_Titulo.isSelected();
	}

	public Boolean getRadioAutor() {
		return rdbtn_Autor.isSelected();
	}

	public Boolean getRadioANac() {
		return rdbtn_A_Nac.isSelected();
	}

	public Boolean getRadioAPub() {
		return rdbtn_A_Pub.isSelected();
	}

	public Boolean getRadioEditorial() {
		return rdbtn_Editorial.isSelected();
	}

	public Boolean getRadioNPag() {
		return rdbtn_N_Pag.isSelected();
	}

}
