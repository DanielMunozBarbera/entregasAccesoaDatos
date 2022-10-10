package es.florida.ejemplos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class T1EjemploVista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnAnyadir;
	private JScrollPane scrollPane;
	private JTextArea textArea_1;
	private JScrollPane scrollPane_1;
	private final JTextArea textArea = new JTextArea();

	public T1EjemploVista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);

		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(30);

		btnAnyadir = new JButton("Anyadir");
		btnAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnAnyadir);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.SOUTH);

		textArea_1 = new JTextArea();
		textArea_1.setColumns(50);
		textArea_1.setRows(8);
		scrollPane.setViewportView(textArea_1);

		scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1, BorderLayout.NORTH);
		textArea.setRows(8);
		scrollPane_1.setViewportView(textArea);

		setVisible(true);
	}

	public JButton getAnyadir() {
		return btnAnyadir;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JTextArea getTextArea_1() {
		return textArea_1;
	}

}

