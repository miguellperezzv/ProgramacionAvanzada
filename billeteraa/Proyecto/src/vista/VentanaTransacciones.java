package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class VentanaTransacciones extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtDescripcion;
	private Modelo modelo;
	private JComboBox<String> cmbBoxCategoria = new JComboBox<String>();
	private JComboBox<String> cmbBoxCuenta = new JComboBox<String>();
	private JButton btnVolver = new JButton("Volver");
	private JButton btnAnadir = new JButton("Crear");
	public ControladorTransacciones controlador = new ControladorTransacciones(this);
	

	public VentanaTransacciones(Modelo modelo) {
		this.modelo = modelo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblTitulo = new JLabel("Titulo");
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.anchor = GridBagConstraints.EAST;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 0;
		contentPane.add(lblTitulo, gbc_lblTitulo);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 0;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblValor = new JLabel("Valor");
		GridBagConstraints gbc_lblValor = new GridBagConstraints();
		gbc_lblValor.insets = new Insets(0, 0, 5, 5);
		gbc_lblValor.anchor = GridBagConstraints.EAST;
		gbc_lblValor.gridx = 0;
		gbc_lblValor.gridy = 2;
		contentPane.add(lblValor, gbc_lblValor);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		contentPane.add(textField, gbc_textField);

		
		JLabel lblDescripcion = new JLabel("Descripcion");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 0;
		gbc_lblDescripcion.gridy = 3;
		contentPane.add(lblDescripcion, gbc_lblDescripcion);
		
		txtDescripcion = new JTextField();
		GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
		gbc_txtDescripcion.anchor = GridBagConstraints.EAST;
		gbc_txtDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_txtDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescripcion.gridx = 1;
		gbc_txtDescripcion.gridy = 3;
		contentPane.add(txtDescripcion, gbc_txtDescripcion);
		
		
		JLabel lblCategoria = new JLabel("Categoria:");
		GridBagConstraints gbc_lblCategoria = new GridBagConstraints();
		gbc_lblCategoria.anchor = GridBagConstraints.EAST;
		gbc_lblCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoria.gridx = 0;
		gbc_lblCategoria.gridy = 4;
		contentPane.add(lblCategoria, gbc_lblCategoria);

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 4;
		contentPane.add(cmbBoxCategoria, gbc_comboBox);

		JLabel lblCuenta = new JLabel("Cuenta");
		GridBagConstraints gbc_lblCuenta = new GridBagConstraints();
		gbc_lblCuenta.insets = new Insets(0, 0, 0, 5);
		gbc_lblCuenta.anchor = GridBagConstraints.EAST;
		gbc_lblCuenta.gridx = 0;
		gbc_lblCuenta.gridy = 5;
		contentPane.add(lblCuenta, gbc_lblCuenta);

		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 5;
		contentPane.add(cmbBoxCuenta, gbc_comboBox_1);
		
		
		
		JPanel botones= new JPanel();
		botones.add(btnAnadir);
		btnAnadir.addActionListener(controlador);
		botones.add(btnVolver);
		btnVolver.addActionListener(controlador);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=1;
		c.gridy=6;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.CENTER;
		contentPane.add(botones,c);
		
		
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public void setTextField_1(JTextField textField_1) {
		this.textField_1 = textField_1;
	}

	public JComboBox<String> getCmbBoxCategoria() {
		return cmbBoxCategoria;
	}

	public void setCmbBoxCategoria(JComboBox<String> cmbBoxCategoria) {
		this.cmbBoxCategoria = cmbBoxCategoria;
	}

	public JComboBox<String> getCmbBoxCuenta() {
		return cmbBoxCuenta;
	}

	public void setCmbBoxCuenta(JComboBox<String> cmbBoxCuenta) {
		this.cmbBoxCuenta = cmbBoxCuenta;
	}

	// Categoria
	public void ingresarDatosCombo(ArrayList<String> salida) {
		cmbBoxCategoria.removeAllItems();
		for(int i =0;i<salida.size();i++) {
			cmbBoxCategoria.addItem(salida.get(i));
		}
		
		
	}

	// Cuentas
	public void ingresarDatosCombo2(ArrayList<String> texto) {
		cmbBoxCuenta.removeAllItems();
		for(int i=0;i< texto.size();i++) {
			if (texto.get(i).equals("Total") == false)
				cmbBoxCuenta.addItem(texto.get(i));
		}
		
	}
	
	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}

	public Modelo getModelo() {
		return modelo;
	}
	
	public JButton getbtnVolver(){
		return btnVolver;
	}
	
	public JButton getbtnAnadir(){
		return btnAnadir;
	}
	

}
