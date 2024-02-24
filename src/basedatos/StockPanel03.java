package basedatos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

@SuppressWarnings("serial")
public class StockPanel03 extends JPanel{
	
	private JButton agregar, modificar, borrar;
	
	//SE PASA LA CLASE POR PARAMETROS PARA PODER ACCEDER A LOS TEXTFIELDS Y SUS VALORES.
	private StockPanel02 panel;
	
	public StockPanel03(StockPanel02 panel) {
		
		setLayout(new GridBagLayout());
		
		AccionBotones accion = new AccionBotones();
		
		agregar = new JButton("Agregar");
		agregar.addActionListener(accion);
		add(agregar);
		
		modificar = new JButton("Modificar");
		modificar.addActionListener(accion);
		add(modificar);
		
		borrar = new JButton("Borrar");
		borrar.addActionListener(accion);
		add(borrar);
		
		this.panel = panel;
		
	}
	
	private class AccionBotones implements ActionListener{
		
		

		public void actionPerformed(ActionEvent e) {
			
			//-------TRY-WITH-RESOURCES PARA QUE CIERRE LA CONECCION AUTOMATICAMENTE AUNQUE NO SE HAGA LA LLAMADA--------
			try (Connection miCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionpedidos", "root", "");
				 PreparedStatement miStAgregar = miCon.prepareStatement("INSERT INTO PRODS (CODIGO, NOMBRE, PRECIO) VALUES (?, ?, ?)");
				 PreparedStatement miStModificar = miCon.prepareStatement("UPDATE PRODS SET NOMBRE = ?, PRECIO = ? WHERE CODIGO = ?");
				 PreparedStatement miStBorrar = miCon.prepareStatement("DELETE FROM PRODS WHERE CODIGO = ?")) {

				    //---------AQUI SE TOMAN DATOS DE LOS TEXTFIELDS--------
				    String codigo = panel.getFieldCodigo().getText();
				    String nombre = panel.getFieldNombre().getText();
				    String precioString = panel.getFieldPrecio().getText();
				    double precio = 0;

				    //-----------BOTON AGREGAR------------
				    if (e.getSource().equals(agregar)) {
				        if (!codigo.isEmpty() && !nombre.isEmpty()) {
				        	
				        	//--------VERIFICAMOS QUE EL CODIGO NO SE REPITA EN LA BASE DE DATOS---------
				        	try(PreparedStatement miStCodigo = miCon.prepareStatement("SELECT COUNT(*) FROM PRODS WHERE CODIGO = ?")) {
				        		
				        		miStCodigo.setString(1, codigo);
				        		
				        		try(ResultSet rSet = miStCodigo.executeQuery()) {
				        			
				        			//--------AL EJECUTAR LA CONSULTA, EL CONTADOR INCREMENTARÁ GRACIAS AL METODO GETINT() SI EL CODIGO INTRODUCIDO YA EXISTE.
				        			rSet.next();
				        			int contador = rSet.getInt(1);
				        			
				        			if(contador > 0) {
				        				
				        				JOptionPane.showMessageDialog(null, "El código introducido ya existe, debes incluir un código único");
				        				return;
				        				
				        			}
				        			
				        		}
				        		
				        	}
				        	
				        	//-------VALIDAMOS EL CAMPO PRECIO YA QUE UN CAMPO VACIO GENERA UN NUMERO INVALIDO--------
						    try {
						    	precio = Double.parseDouble(precioString);
						    	if(precio < 0) {
						    		JOptionPane.showMessageDialog(null, "El precio debe ser un número positivo.");
						    	}
						    } catch(NumberFormatException e2) {
						    	e2.printStackTrace();
						    }
				            miStAgregar.setString(1, codigo);
				            miStAgregar.setString(2, nombre);
				            miStAgregar.setDouble(3, precio);
				            miStAgregar.executeUpdate();
				            System.out.println("Agregando documento");
				        } else {
				            JOptionPane.showMessageDialog(null, "Debes introducir Código y Nombre obligatoriamente.");
				        }
				        
				    //---------BOTON MODIFICAR--------
				    } else if (e.getSource().equals(modificar)) {
				        if (!codigo.isEmpty() && !nombre.isEmpty() && !precioString.isEmpty()) {
				        	//-------VALIDAMOS EL CAMPO PRECIO YA QUE UN CAMPO VACIO GENERA UN NUMERO INVALIDO--------
						    try {
						    	precio = Double.parseDouble(precioString);
						    	if(precio < 0) {
						    		JOptionPane.showMessageDialog(null, "El precio debe ser un número positivo.");
						    	}
						    } catch(NumberFormatException e2) {
						    	e2.printStackTrace();
						    }
				            miStModificar.setString(1, nombre);
				            miStModificar.setDouble(2, precio);
				            miStModificar.setString(3, codigo);
				            miStModificar.executeUpdate();
				            System.out.println("Modificando documento");
				        } else {
				            JOptionPane.showMessageDialog(null, "Debes incluir Código, Nombre y Precio obligatoriamente.");
				        }
				    
				    //---------BOTON BORRAR---------
				    } else if (e.getSource().equals(borrar)) {
				        if (!codigo.isEmpty()) {
				            miStBorrar.setString(1, codigo);
				            miStBorrar.executeUpdate();
				            System.out.println("Borrando documento");
				        } else {
				            JOptionPane.showMessageDialog(null, "Debes incluir un Código obligatoriamente.");
				        }
				    }
				    
				} catch (SQLException e1) {
				    e1.printStackTrace();
				}
			
		}
		
	}

}
