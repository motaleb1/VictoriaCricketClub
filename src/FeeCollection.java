import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.awt.Font;
import java.awt.Color;

public class FeeCollection extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtFee;
	private JTextField txtDate;
	private JComboBox cmbMID;
	private JComboBox cmbPurpose;
	
	
	DbUtil util=new DbUtil();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FeeCollection frame = new FeeCollection();
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
	public FeeCollection() {
		setTitle("Fee Collection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 288);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMemberId = new JLabel("Member ID");
		lblMemberId.setBounds(38, 151, 82, 20);
		contentPane.add(lblMemberId);
		
		cmbMID = new JComboBox();
		cmbMID.setBounds(136, 148, 160, 26);
		try
		{
			String sql = "select m_id from member";
			PreparedStatement ps = util.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{				
				cmbMID.addItem(rs.getString(1));
			}

		}

		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
			JOptionPane.ERROR_MESSAGE);
		}
		contentPane.add(cmbMID);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(321, 148, 73, 20);
		contentPane.add(lblAmount);
		
		JLabel lblPurpose = new JLabel("Purpose");
		lblPurpose.setBounds(321, 95, 82, 20);
		contentPane.add(lblPurpose);
		
		JLabel lblDate = new JLabel("Collection Date");
		lblDate.setBounds(38, 103, 96, 20);
		contentPane.add(lblDate);
		
		txtFee = new JTextField();
		txtFee.setColumns(10);
		txtFee.setBounds(373, 146, 104, 26);
		contentPane.add(txtFee);
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(136, 97, 120, 26);
		contentPane.add(txtDate);
		
		cmbPurpose = new JComboBox();
		cmbPurpose.addItem("Registration Fee");
		cmbPurpose.addItem("Monthly Fee");
		cmbPurpose.addItem("Match Fee");
		cmbPurpose.addItem("Training Fee");
		
		cmbPurpose.setBounds(373, 95, 104, 22);
		contentPane.add(cmbPurpose);
		
		JButton btnNewButton = new JButton("...");
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
			  public void actionPerformed(ActionEvent e) {
		            SimpleDateFormat sdf=new SimpleDateFormat();
		            sdf.applyPattern("yyyy-MM-dd");
		            ShowCalendar sc=new ShowCalendar((int)btnNewButton.getLocationOnScreen().getX(),(int)btnNewButton.getLocationOnScreen().getY(),btnNewButton.getWidth());
		            String dOj=sdf.format(sc.calendar.getDate());
		            txtDate.setText(dOj);
			  }
			});
		txtDate.setEditable(false);
		btnNewButton.setBounds(261, 94, 35, 29);
		contentPane.add(btnNewButton);		

		JButton btnSave = new JButton("Save");
		btnSave.setBackground(new Color(25, 25, 112));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mid=cmbMID.getSelectedItem().toString();
			
				String payDate= txtDate.getText();
				
				int amount=Integer.parseInt(txtFee.getText());
				
				String purpose=cmbPurpose.getSelectedItem().toString();				
				
				try
				{
					util.stmt.executeUpdate("insert into Fees values(DEFAULT,'"+mid+"',"+amount+",'"+payDate+"','"+purpose+"')");
					setNull();
					JOptionPane.showMessageDialog(null,"Fees Added Successfully");	
				}

				catch(SQLException sqle)
				{
					JOptionPane.showMessageDialog(null,"Data Could not Saved");
				}

				
				
			}
		});
		btnSave.setBounds(131, 203, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBackground(new Color(25, 25, 112));
		btnRefresh.setForeground(new Color(255, 255, 255));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setNull();
			}
		});
		btnRefresh.setBounds(244, 203, 89, 23);
		contentPane.add(btnRefresh);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(25, 25, 112));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(358, 203, 89, 23);
		contentPane.add(btnCancel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		panel.setBounds(0, 28, 528, 44);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFeeCollection = new JLabel("Fee Collection");
		lblFeeCollection.setBackground(new Color(25, 25, 112));
		lblFeeCollection.setForeground(new Color(255, 255, 255));
		lblFeeCollection.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblFeeCollection.setHorizontalAlignment(SwingConstants.CENTER);
		lblFeeCollection.setBounds(10, 5, 508, 39);
		panel.add(lblFeeCollection);
		
		setVisible(true);
	}

	public void setNull()
	{
		cmbPurpose.setSelectedIndex(0);
		cmbMID.setSelectedIndex(0);
		txtDate.setText("");
		txtFee.setText("");
		
	}
}
