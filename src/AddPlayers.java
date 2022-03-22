import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.border.EmptyBorder;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.SystemColor;
import java.awt.Font;

public class AddPlayers extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtPhone;
	TextArea taAddress;

	DbUtil util=new DbUtil();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPlayers frame = new AddPlayers();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddPlayers() {		
		setBounds(100, 100, 601, 456);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Player ID");
		lblNewLabel.setBounds(71, 116, 98, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblPlayerName = new JLabel("Player Name");
		lblPlayerName.setBounds(71, 157, 112, 49);
		contentPane.add(lblPlayerName);
		
		JLabel lblPlayerAddress = new JLabel("Player Address");
		lblPlayerAddress.setBounds(71, 209, 127, 49);
		contentPane.add(lblPlayerAddress);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(71, 299, 127, 49);
		contentPane.add(lblPhoneNumber);
		
		txtId = new JTextField();
		txtId.setBounds(204, 130, 89, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		taAddress = new TextArea();
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(204, 171, 219, 20);
		contentPane.add(txtName);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(204, 313, 215, 20);
		contentPane.add(txtPhone);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(new Color(25, 25, 112));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String pid=txtId.getText();

				String pName=txtName.getText();
				
				String pAddress= taAddress.getText();

				String pPhone=txtPhone.getText();

				try
				{
					util.stmt.executeUpdate("insert into players values('"+pid+"','"+pName+"','"+pAddress+"','"+pPhone+"')");
					setNull();
					JOptionPane.showMessageDialog(null,"Player Added Successfully");	
				}

				catch(SQLException sqle)
				{
					JOptionPane.showMessageDialog(null,"Data Could not Saved");
				}

				
				
			}
		});
		btnSave.setBounds(204, 359, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnEdit = new JButton("Refresh");
		btnEdit.setBackground(new Color(25, 25, 112));
		btnEdit.setForeground(new Color(255, 255, 255));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setNull();
			}
		});
		btnEdit.setBounds(325, 359, 89, 23);
		contentPane.add(btnEdit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(25, 25, 112));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(444, 359, 89, 23);
		contentPane.add(btnCancel);
		
		taAddress.setBackground(SystemColor.controlHighlight);
		taAddress.setBounds(204, 209, 183, 87);
		contentPane.add(taAddress);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 100, 568, -49);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(25, 25, 112));
		panel_1.setBounds(10, 32, 586, 60);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAddNewPlayers = new JLabel("Add New Players");
		lblAddNewPlayers.setForeground(new Color(255, 255, 255));
		lblAddNewPlayers.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewPlayers.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblAddNewPlayers.setBounds(10, 5, 566, 55);
		panel_1.add(lblAddNewPlayers);
	}
	
	public void setNull()
	{
		txtId.setText("");
		txtName.setText("");
		txtPhone.setText("");
		taAddress.setText("");
		txtId.requestFocus();
	}
}
