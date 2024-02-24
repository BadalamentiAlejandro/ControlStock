package basedatos;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("serial")
public class StockTab extends JPanel{
	
	private JTable tabla;
	
	private DefaultTableModel dtm;
	
	private JButton refrescarLista;
	
	public StockTab() {
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		dtm = new DefaultTableModel();
		dtm.addColumn("Codigo");
		dtm.addColumn("Nombre");
		dtm.addColumn("Precio");
		
		llamadaDatos();
		
		refrescarLista = new JButton("Refrescar Lista");
		refrescarLista.addActionListener(new AccionBoton());
		
		tabla = new JTable(dtm);
		tabla.setEnabled(false); //------PARA QUE NO SEA EDITABLE-----
		
		//EL JScrollPane ES OBLIGATORIO, SINO NO SE MUESTRAN LOS NOMBRES DE LAS COLUMNAS.
		JScrollPane scrollPane = new JScrollPane(tabla);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(scrollPane, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(refrescarLista, gbc);
		
	}
	
	class AccionBoton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			llamadaDatos();
			
		}
		
	}
	
	public void llamadaDatos() {
		
		try(Connection miCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionpedidos", "root", "");
				PreparedStatement prSt = miCon.prepareStatement("SELECT * FROM PRODS");
				ResultSet miRs = prSt.executeQuery()) {
			
			dtm.setRowCount(0);
			//POR CADA ITERACION DEL RESULTSET SE CREA UNA FILA QUE SE INCLUYE EN LA TABLA.
			while(miRs.next()) {
				
				Object[] Fila = {miRs.getString("CODIGO"), miRs.getString("NOMBRE"), miRs.getDouble("PRECIO")};
				dtm.addRow(Fila);
				
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "No hemos podido conectar con la base de datos");
		}
		
	}

}
