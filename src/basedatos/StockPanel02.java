package basedatos;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class StockPanel02 extends JPanel{
	
	private JPanel celdaCodigo, celdaNombre, celdaPrecio;
	
	private JLabel labelCodigo, labelNombre, labelPrecio, labelEuros;
	
	private JTextField fieldCodigo, fieldNombre, fieldPrecio;

	public StockPanel02() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		
		celdaCodigo = new JPanel();
		labelCodigo = new JLabel("Código");
		fieldCodigo = new JTextField(5);
		celdaCodigo.add(labelCodigo);
		celdaCodigo.add(fieldCodigo);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(celdaCodigo, gbc);
		
		celdaNombre = new JPanel();
		labelNombre = new JLabel("Nómbre");
		fieldNombre = new JTextField(25);
		celdaNombre.add(labelNombre);
		celdaNombre.add(fieldNombre);
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(celdaNombre, gbc);
		
		celdaPrecio = new JPanel();
		labelPrecio = new JLabel("Precio");
		fieldPrecio = new JTextField(5);
		labelEuros = new JLabel("€");
		celdaPrecio.add(labelPrecio);
		celdaPrecio.add(fieldPrecio);
		celdaPrecio.add(labelEuros);
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(celdaPrecio, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(new StockPanel03(this), gbc);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public JTextField getFieldCodigo() {
		return fieldCodigo;
	}

	public JTextField getFieldNombre() {
		return fieldNombre;
	}

	public JTextField getFieldPrecio() {
		return fieldPrecio;
	}

}
