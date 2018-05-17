package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import data.CitycomData;
import data.IAdressData;

public class CitycomPanel extends JPanel implements IDataPanel{

	private static final long serialVersionUID = 1L;
	
	private JTextField textField;

	public CitycomPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{66, 227, 0};
		gridBagLayout.rowHeights = new int[]{14, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Citycom");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);

	}

	
	public JTextField getTextField() {
		return textField;
	}
	
	
	
	public String getTabText() {
		return "Citycom data";
	}
	
	public void setEditable(boolean b) {
		textField.setEditable(b);
	}
	
	public IAdressData getAdressData() {
		CitycomData obj = null;
		try {
			String name = textField.getText();
			
			obj = new CitycomData(name);
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(this, e.getMessage(),
					"ERROR "+getTabText(), JOptionPane.ERROR_MESSAGE);
		}
		return obj;
	}
	
	public void setData(IAdressData obj){
		CitycomData data = (CitycomData) obj;
		textField.setText(data.getName());
	}
}
