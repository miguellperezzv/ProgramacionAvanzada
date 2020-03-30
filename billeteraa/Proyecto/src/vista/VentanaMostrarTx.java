package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaMostrarTx extends JFrame {

	private ControladorMostrarTx controlador = new ControladorMostrarTx(this);
	private Modelo modelo;
	private JPanel contentPane;
	private JTable table;
	private JButton btnVolver;
	JComboBox comboBox = new JComboBox();
	JComboBox cmbCiclos = new JComboBox();


	
	public VentanaMostrarTx(Modelo m) {
		modelo=m;
		JPanel superior = new JPanel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		contentPane.setLayout(gbl_contentPane);
		
		
		JLabel lblFiltro = new JLabel("Filtrar por:");
		JLabel lblOrdenarPor = new JLabel("Ordenar Por:");
		
		comboBox.addItem("Fecha");
		comboBox.addItem("Descripcion");
		comboBox.addItem("Categorias");
		comboBox.addItem("Cuenta");
		comboBox.addItem("Valor");
		comboBox.addItem("Nombres");
		
		cmbCiclos.addItem("Ultimo mes");
		cmbCiclos.addItem("Hace 6 meses");
		cmbCiclos.addItem("Hace 12 horas");
		cmbCiclos.addItem("Todas las transacciones");
		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		superior.add(lblOrdenarPor);
		superior.add(cmbCiclos);
		superior.add(lblFiltro);
		superior.add(comboBox);
		
		contentPane.add(superior, gbc_comboBox);
		
		btnVolver=new JButton("Volver");
		
		table = new JTable() {public boolean isCellEditable(int rowIndec, int vColIndex) {
			return false;
		}
	};
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table);
		GridBagConstraints gbc_table = new GridBagConstraints();
		
		gbc_table.weightx = 1;
		gbc_table.weighty = 1;
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 1;
		contentPane.add(scroll, gbc_table);
		
		comboBox.addActionListener(controlador);
	
		gbc_table.weightx=0;
		gbc_table.weighty=0;
				gbc_table.gridx=0;
		gbc_table.gridy=2;
		btnVolver.addActionListener(controlador);
		contentPane.add(btnVolver, gbc_table);
		
		
		cmbCiclos.addActionListener(controlador);
		
		
		
		
	}
	
	public JComboBox getCombo() {
		return comboBox;
	}
	public Modelo getModelo() {
		return modelo;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	public JButton getBtnVolver() {
		return btnVolver;
		
	}
	
	public JComboBox getComboFiltroFechas(){
		return cmbCiclos;
		
	}

	
	
	

}
