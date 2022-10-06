package es.florida.aev1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JEditorPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Font;

public class AEV1Vista {

	
	
	private JFrame frmAevAccesoA;
	private JButton btn_mostrarDir;
	private JTextArea textArea;
	private JTextField textField_Dir;
	private JScrollPane scrollPane;
	private JTextField textField_Operaciones;
	private JButton btn_mostrarInfo;
	private JButton btn_mostrarArch;
	private JTextField textField_Buscar;
	private JButton btn_Buscar;
	private JTextField textField_Reemplazar;
	private JButton btn_Reemplazar;
	private JButton btn_Crear;
	private JButton btn_Renombrar;
	private JButton btn_Copiar;
	private JButton btn_Suprimir;
	private JButton btn_guardarCambios;
	private JButton btn_Destacar;
	private JTextField textField_anyadirTexto;
	private JButton btn_anyadirTexto;
	private JCheckBox chckbx_Resaltado, chckbx_Negrita, chckbx_Subrayado;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AEV1Vista window = new AEV1Vista();
//					window.frmAevAccesoA.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public AEV1Vista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAevAccesoA = new JFrame();
		frmAevAccesoA.setTitle("AEV1 Acceso a Datos - Daniel Muñoz Barbera");
		frmAevAccesoA.setBounds(100, 100, 823, 599);
		frmAevAccesoA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 241, 219));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		GroupLayout groupLayout = new GroupLayout(frmAevAccesoA.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 808, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 561, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		btn_mostrarDir = new JButton("Mostrar Directorio");
		btn_mostrarDir.setBounds(638, 33, 143, 23);
		panel.add(btn_mostrarDir);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(30, 67, 750, 239);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		textField_Dir = new JTextField();
		textField_Dir.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField_Dir.setBounds(485, 34, 143, 20);
		textField_Dir.setColumns(10);
		panel.add(textField_Dir);
		textField_Dir.setVisible(true);
		
		textField_Operaciones = new JTextField();
		textField_Operaciones.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField_Operaciones.setBounds(30, 317, 245, 20);
		panel.add(textField_Operaciones);
		textField_Operaciones.setColumns(10);
		
		btn_mostrarInfo = new JButton("Mostrar Info");
		btn_mostrarInfo.setBounds(169, 348, 106, 23);
		panel.add(btn_mostrarInfo);
		
		btn_mostrarArch = new JButton("Mostrar Archivo");
		btn_mostrarArch.setBounds(30, 348, 129, 23);
		panel.add(btn_mostrarArch);
		
		textField_Buscar = new JTextField();
		textField_Buscar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField_Buscar.setBounds(443, 349, 96, 20);
		panel.add(textField_Buscar);
		textField_Buscar.setColumns(10);
		
		btn_Buscar = new JButton("Buscar");
		btn_Buscar.setBounds(344, 348, 89, 23);
		panel.add(btn_Buscar);
		
		textField_Reemplazar = new JTextField();
		textField_Reemplazar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField_Reemplazar.setBounds(443, 383, 96, 20);
		panel.add(textField_Reemplazar);
		textField_Reemplazar.setColumns(10);
		
		btn_Reemplazar = new JButton("Reemplazar");
		btn_Reemplazar.setBounds(327, 382, 106, 23);
		panel.add(btn_Reemplazar);
		
		btn_Crear = new JButton("Crear");
		btn_Crear.setBounds(30, 416, 89, 23);
		panel.add(btn_Crear);
		
		btn_Renombrar = new JButton("Renombrar");
		btn_Renombrar.setBounds(30, 382, 106, 23);
		panel.add(btn_Renombrar);
		
		btn_Copiar = new JButton("Copiar");
		btn_Copiar.setBounds(186, 382, 89, 23);
		panel.add(btn_Copiar);
		
		btn_Suprimir = new JButton("Suprimir");
		btn_Suprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Suprimir.setBounds(186, 416, 89, 23);
		panel.add(btn_Suprimir);
		
		btn_guardarCambios = new JButton("Guardar Cambios");
		btn_guardarCambios.setForeground(new Color(0, 0, 0));
		btn_guardarCambios.setBounds(30, 508, 143, 23);
		panel.add(btn_guardarCambios);
		
		textField_anyadirTexto = new JTextField();
		textField_anyadirTexto.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField_anyadirTexto.setBounds(443, 417, 96, 20);
		panel.add(textField_anyadirTexto);
		textField_anyadirTexto.setColumns(10);
		
		btn_anyadirTexto = new JButton("Añadir Texto");
		btn_anyadirTexto.setBounds(317, 416, 116, 23);
		panel.add(btn_anyadirTexto);
		
		chckbx_Resaltado = new JCheckBox("Resaltado");
		chckbx_Resaltado.setBackground(new Color(255, 241, 219));
		chckbx_Resaltado.setBounds(568, 382, 99, 23);
		panel.add(chckbx_Resaltado);
		
		chckbx_Subrayado = new JCheckBox("Subrayado");
		chckbx_Subrayado.setBackground(new Color(255, 241, 219));
		chckbx_Subrayado.setBounds(568, 408, 99, 23);
		panel.add(chckbx_Subrayado);
		
		chckbx_Negrita = new JCheckBox("Negrita");
		chckbx_Negrita.setBackground(new Color(255, 241, 219));
		chckbx_Negrita.setBounds(568, 434, 99, 23);
		panel.add(chckbx_Negrita);
		
		btn_Destacar = new JButton("Destacar");
		btn_Destacar.setBounds(568, 348, 89, 23);
		panel.add(btn_Destacar);
		
		JRadioButton rdbtn_Rojo = new JRadioButton("Rojo");
		rdbtn_Rojo.setBackground(new Color(255, 241, 219));
		rdbtn_Rojo.setBounds(669, 382, 111, 23);
		panel.add(rdbtn_Rojo);
		
		JRadioButton rdbtn_Azul = new JRadioButton("Azul");
		rdbtn_Azul.setBackground(new Color(255, 241, 219));
		rdbtn_Azul.setBounds(670, 408, 111, 23);
		panel.add(rdbtn_Azul);
		
		JRadioButton rdbtn_Verde = new JRadioButton("Verde");
		rdbtn_Verde.setBackground(new Color(255, 241, 219));
		rdbtn_Verde.setBounds(670, 434, 111, 23);
		panel.add(rdbtn_Verde);
		
		JLabel lblNewLabel = new JLabel("Colores");
		lblNewLabel.setBounds(699, 357, 49, 14);
		panel.add(lblNewLabel);
		
		JRadioButton rdbtn_Negro = new JRadioButton("Negro");
		rdbtn_Negro.setBackground(new Color(255, 241, 219));
		rdbtn_Negro.setSelected(true);
		rdbtn_Negro.setBounds(670, 460, 111, 23);
		panel.add(rdbtn_Negro);
		
		ButtonGroup grupoRadioButtons = new ButtonGroup();
		grupoRadioButtons.add(rdbtn_Rojo);
		grupoRadioButtons.add(rdbtn_Azul);
		grupoRadioButtons.add(rdbtn_Verde);
		grupoRadioButtons.add(rdbtn_Negro);
		
		separator = new JSeparator();
		separator.setBounds(30, 495, 751, 2);
		panel.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(292, 310, 2, 187);
		panel.add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		separator_2.setBounds(560, 310, 2, 187);
		panel.add(separator_2);
		
		lblNewLabel_1 = new JLabel("AEV1 Acceso a Datos");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 44));
		lblNewLabel_1.setBounds(30, 11, 403, 45);
		panel.add(lblNewLabel_1);
		
		frmAevAccesoA.getContentPane().setLayout(groupLayout);
		frmAevAccesoA.setVisible(true);
	}
	
	//BOTONES
	public JButton getBotonDir() {
		return btn_mostrarDir;
	}
	
	public JButton getBotonMostrarInfo() {
		return btn_mostrarInfo;
	}
	
	public JButton getBotonCrear() {
		return btn_Crear;
	}
	
	public JButton getBotonRenombrar() {
		return btn_Renombrar;
	}
	
	public JButton getBotonCopiar() {
		return btn_Copiar;
	}
	
	public JButton getBotonSuprimir() {
		return btn_Suprimir;
	}
	
	public JButton getBotonMostrarArchivo() {
		return btn_mostrarArch;
	}
	
	public JButton getBotonBuscar() {
		return btn_Buscar;
	}
	
	public JButton getBotonAnyadirTexto() {
		return btn_anyadirTexto;
	}
	
	public JButton getBotonReemplazar() {
		return btn_Reemplazar;
	}
	
	public JButton getBotonDestacar() {
		return btn_Destacar;
	}
	
	public JButton getBotonGuardarCambios() {
		return btn_guardarCambios;
	}
	//JTEXTFIELDS
	public JTextField getTextFieldDir() {
		return textField_Dir;
	}
	
	public JTextField getTextFieldOperaciones() {
		return textField_Operaciones;
	}
	
	public JTextField getTextFieldBuscar() {
		return textField_Buscar;
	}
	
	public JTextField getTextFielAnyadirTexto() {
		return textField_anyadirTexto;
	}
	
	public JTextField getTextFieldReemplazar() {
		return textField_Reemplazar;
	}
	
	//JCHECKBOXES
	public JCheckBox getCheckBoxNegrita() {
		return chckbx_Negrita;
	}
	
	//EL AREA
	public JTextArea getTextArea() {
		return textArea;
	}
}
