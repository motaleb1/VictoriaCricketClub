import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.awt.Color;
import java.awt.Font;

public class AddMember extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtFee;
	private JTextField txtDate;
	private TextArea taAddress ;
	private JComboBox cmbType;
	String dOj;
	
	DbUtil util=new DbUtil();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMember frame = new AddMember();
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
	public AddMember() {
		setTitle("Add Member");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMemberId = new JLabel("Member ID");
		lblMemberId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMemberId.setBounds(40, 137, 89, 20);
		contentPane.add(lblMemberId);
		
		JLabel lblMemberName = new JLabel("Member Name");
		lblMemberName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMemberName.setBounds(40, 180, 89, 20);
		contentPane.add(lblMemberName);
		
		JLabel lblMemberAddress = new JLabel("Member Address");
		lblMemberAddress.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMemberAddress.setBounds(40, 211, 89, 20);
		contentPane.add(lblMemberAddress);
		
		JLabel lblMemberPhone = new JLabel("Member Phone");
		lblMemberPhone.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMemberPhone.setBounds(301, 176, 86, 20);
		contentPane.add(lblMemberPhone);
		
		JLabel lblMemberMebershipFee = new JLabel("Mebership Fee");
		lblMemberMebershipFee.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMemberMebershipFee.setBounds(300, 137, 86, 20);
		contentPane.add(lblMemberMebershipFee);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDate.setBounds(40, 105, 50, 20);
		contentPane.add(lblDate);
		
		txtID = new JTextField();
		txtID.setBounds(129, 134, 107, 26);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(129, 173, 159, 26);
		txtName.setColumns(10);
		contentPane.add(txtName);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(388, 174, 138, 26);
		txtPhone.setColumns(10);
		contentPane.add(txtPhone);
		
		txtFee = new JTextField();
		txtFee.setBounds(387, 134, 139, 26);
		txtFee.setColumns(10);
		contentPane.add(txtFee);
		
		txtDate = new JTextField();
		txtDate.setBounds(129, 99, 107, 26);
		txtDate.setColumns(10);
		contentPane.add(txtDate);
		

		
		taAddress = new TextArea();
		taAddress.setBounds(128, 211, 162, 51);
		contentPane.add(taAddress);
		
		JButton btnNewButton = new JButton("...");
		btnNewButton.setBounds(246, 99, 28, 26);
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
			  public void actionPerformed(ActionEvent e) {
		            SimpleDateFormat sdf=new SimpleDateFormat();
		            sdf.applyPattern("yyyy-MM-dd");
		            ShowCalendar sc=new ShowCalendar((int)btnNewButton.getLocationOnScreen().getX(),(int)btnNewButton.getLocationOnScreen().getY(),btnNewButton.getWidth());
		            dOj=sdf.format(sc.calendar.getDate());
		            txtDate.setText(dOj);
			  }
			});
		txtDate.setEditable(false);
		contentPane.add(btnNewButton);
		
		JLabel lblMemberType = new JLabel("Member Type");
		lblMemberType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMemberType.setBounds(300, 105, 71, 20);
		contentPane.add(lblMemberType);
		
		cmbType = new JComboBox();
		cmbType.setBounds(387, 100, 140, 26);
		cmbType.addItem("Adult");
		cmbType.addItem("Children");
		cmbType.addItem("Player");
		contentPane.add(cmbType);
		
		JButton btnSave = new JButton("Save");
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setBackground(new Color(25, 25, 112));
		btnSave.setBounds(127, 282, 104, 28);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mid=txtID.getText();

				String mName=txtName.getText();
				
				String mAddress= taAddress.getText();

				String mPhone=txtPhone.getText();
				
				String mType=cmbType.getSelectedItem().toString();
				
				int fee=Integer.parseInt(txtFee.getText());
				
				try
				{
					util.stmt.executeUpdate("insert into Member values('"+mid+"','"+mName+"','"+mAddress+"','"+mPhone+"', '"+mType+"','"+dOj+"')");
					util.stmt.executeUpdate("insert into Fees values(default,'"+mid+"',"+fee+",'"+dOj+"','Registration')");
					setNull();
					JOptionPane.showMessageDialog(null,"Member Added Successfully");	
				}

				catch(SQLException sqle)
				{
					JOptionPane.showMessageDialog(null,"Data Could not Saved");
					JOptionPane.showMessageDialog(null,sqle);
				}

				
				
			}
		});
		contentPane.add(btnSave);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setForeground(new Color(255, 255, 255));
		btnRefresh.setBackground(new Color(25, 25, 112));
		btnRefresh.setBounds(242, 282, 98, 29);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setNull();
			}
		});
		contentPane.add(btnRefresh);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBackground(new Color(25, 25, 112));
		btnCancel.setBounds(351, 282, 109, 29);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnCancel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		panel.setBounds(0, 26, 558, 45);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAddNewMember = new JLabel("Add New Member");
		lblAddNewMember.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewMember.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAddNewMember.setForeground(new Color(255, 255, 255));
		lblAddNewMember.setBounds(75, 5, 402, 40);
		panel.add(lblAddNewMember);
		setVisible(true);
	}

	public void setNull()
	{
		txtID.setText("");
		txtName.setText("");
		txtPhone.setText("");
		taAddress.setText(null);
		cmbType.setSelectedIndex(0);
		txtDate.setText("");
		txtFee.setText("");
		txtID.requestFocus();
	}
}
