package basedatos;

import java.awt.*;
import javax.swing.*;

public class StockControl01 {

	public static void main(String[] args) {
		
		new FrameStockControl();

	}

}

@SuppressWarnings("serial")
class FrameStockControl extends JFrame {
	
	private JPanel stockPanel01;
	
	public FrameStockControl() {
		
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Stock Control");
		setResizable(false);
		
		stockPanel01 = new JPanel();
		stockPanel01.setLayout(new GridLayout(1, 2));
		stockPanel01.add(new StockPanel02());
		stockPanel01.add(new StockTab());
		add(stockPanel01);
		
		setVisible(true);
		
	}
	
}
