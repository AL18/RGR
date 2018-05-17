package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import data.EmployeeData;
import data.IAdressData;

public class EmployeePanel extends JPanel implements IDataPanel{

	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public EmployeePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{110, 227, 0};
		gridBagLayout.rowHeights = new int[]{14, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Employee`s name");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Age");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblRegion = new JLabel("Salary");
		GridBagConstraints gbc_lblRegion = new GridBagConstraints();
		gbc_lblRegion.anchor = GridBagConstraints.EAST;
		gbc_lblRegion.insets = new Insets(0, 0, 0, 5);
		gbc_lblRegion.gridx = 0;
		gbc_lblRegion.gridy = 2;
		add(lblRegion, gbc_lblRegion);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
	}

	
	public JTextField getTextField() {
		return textField;
	}
	public JTextField getTextField_1() {
		return textField_1;
	}
	public JTextField getTextField_2() {
		return textField_2;
	}

	
	public String getTabText() {
		return "Employee data";
	}
	
	public void setEditable(boolean b) {
		textField.setEditable(b);
		textField_1.setEditable(b);
		textField_2.setEditable(b);
	}
	
	public IAdressData getAdressData() {
		EmployeeData obj = null;
		try {
			String name = textField.getText();
			int age = Integer.parseInt(textField_1.getText());
			int salary = Integer.parseInt(textField_2.getText());
			obj = new EmployeeData(name, age, salary);
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(this, e.getMessage(),
					"ERROR "+getTabText(), JOptionPane.ERROR_MESSAGE);
		}
		return obj;
	}
	
	public void setData(IAdressData obj){
		EmployeeData data = (EmployeeData) obj;
		textField.setText(data.getName());
		textField_1.setText(String.valueOf(data.getAge()));
		textField_2.setText(String.valueOf(data.getSalary()));
	}
}
