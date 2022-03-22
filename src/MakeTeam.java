import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.sql.*;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MakeTeam extends JInternalFrame {

	private JPanel contentPane;
	
	DbUtil util=new DbUtil();
	ResultSet rs;
	PreparedStatement ps;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakeTeam frame = new MakeTeam();
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
	public MakeTeam() {
		setTitle("Make a Team");
		setBounds(100, 100, 456, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setNull();
			}
		});
		btnRefresh.setBounds(226, 178, 82, 25);
		contentPane.add(btnRefresh);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(318, 178, 82, 25);
		contentPane.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tId=textField.getText();
				
				String tName= textField_1.getText();		
				
				try
				{
					util.stmt.executeUpdate("insert into team values('"+tId+"','"+tName+"')");
					setNull();
					JOptionPane.showMessageDialog(null,"Team Added Successfully");	
				}

				catch(SQLException sqle)
				{
					JOptionPane.showMessageDialog(null,"Data Could not Saved");
				}
				
			}
		});
		btnSave.setBounds(134, 178, 82, 25);
		contentPane.add(btnSave);
		
		JLabel lblNewLabel = new JLabel("Team No");
		lblNewLabel.setBounds(47, 106, 77, 25);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("Team Name");
		label.setBounds(47, 142, 90, 25);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(136, 107, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(136, 143, 193, 22);
		contentPane.add(textField_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		panel.setBounds(0, 30, 470, 44);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMakeATeam = new JLabel("Make A Team");
		lblMakeATeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblMakeATeam.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblMakeATeam.setForeground(new Color(255, 255, 255));
		lblMakeATeam.setBounds(24, 5, 415, 39);
		panel.add(lblMakeATeam);
		setVisible(true);
	}
	
	public void setNull()
	{
		textField.setText(null);
		textField_1.setText(null);
		textField.requestFocus();
	}
}
