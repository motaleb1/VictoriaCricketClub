import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
public class ArrangeMatch extends JInternalFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	
	DbUtil util=new DbUtil();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrangeMatch frame = new ArrangeMatch();
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
	public ArrangeMatch() {
		setTitle("Arrange a Match");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setBounds(66, 112, 120, 22);
		try
		{
			String sql = "select t_name from team";
			PreparedStatement ps = util.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{				
				comboBox.addItem(rs.getString(1));
			}

		}

		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
			JOptionPane.ERROR_MESSAGE);
		}
			
		contentPane.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(305, 112, 120, 22);
		comboBox_1.addItem("Belkuchi");
		comboBox_1.addItem("Sirajganj");
		comboBox_1.addItem("Shahjadpur");
		comboBox_1.addItem("Kazipur");
		contentPane.add(comboBox_1);
		
		JLabel lblVs = new JLabel("VS");
		lblVs.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblVs.setBounds(223, 101, 56, 32);
		contentPane.add(lblVs);
		
		JLabel lblResult = new JLabel("Winner");
		lblResult.setForeground(new Color(255, 0, 0));
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setBounds(160, 171, 169, 16);
		contentPane.add(lblResult);
		
		textField = new JTextField();
		textField.setBounds(160, 190, 169, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("Cancel");
		button.setBackground(new Color(25, 25, 112));
		button.setForeground(new Color(255, 255, 255));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(328, 246, 97, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Refresh");
		button_1.setBackground(new Color(25, 25, 112));
		button_1.setForeground(new Color(255, 255, 255));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setNull();
			}
		});
		button_1.setBounds(181, 246, 132, 25);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Save");
		button_2.setBackground(new Color(25, 25, 112));
		button_2.setForeground(new Color(255, 255, 255));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String myTeam=comboBox.getSelectedItem().toString();
				
				String opposite= comboBox_1.getSelectedItem().toString();
				
				String result=textField.getText();				
				
				try
				{
					util.stmt.executeUpdate("insert into matches values(default,'"+myTeam+"','"+opposite+"','"+result+"')");
					setNull();
					JOptionPane.showMessageDialog(null,"Result Added Successfully");	
				}

				catch(SQLException sqle)
				{
					JOptionPane.showMessageDialog(null,"Data Could not Saved");
					JOptionPane.showMessageDialog(null,sqle);
				}
				
			}
		});
		button_2.setBounds(66, 246, 97, 25);
		contentPane.add(button_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		panel.setBounds(0, 25, 480, 49);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblArrangeMatch = new JLabel("Arrange Match & Winner");
		lblArrangeMatch.setForeground(new Color(255, 255, 255));
		lblArrangeMatch.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblArrangeMatch.setHorizontalAlignment(SwingConstants.CENTER);
		lblArrangeMatch.setBounds(10, 5, 460, 33);
		panel.add(lblArrangeMatch);
		setVisible(true);
	}
	
	public void setNull()
	{
		comboBox.setSelectedIndex(0);
		comboBox_1.setSelectedIndex(0);
		textField.setText(null);
	}
	
}
