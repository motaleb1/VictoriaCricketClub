import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.awt.Color;
import java.awt.Font;

public class AddExpenditure extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtPurpose;
	private JTextField txtFee;
	private JTextField txtDate;
	String dOj;
	JComboBox cmbID;
	
	DbUtil util=new DbUtil();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddExpenditure frame = new AddExpenditure();
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
	public AddExpenditure() {
		setTitle("Add Expenditure");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMemberId = new JLabel("Member ID");
		lblMemberId.setBounds(67, 152, 89, 20);
		contentPane.add(lblMemberId);
		
		JLabel lblPurpose = new JLabel("Purpose");
		lblPurpose.setBounds(67, 186, 89, 20);
		contentPane.add(lblPurpose);
		
		JLabel lblMemberMebershipFee = new JLabel("Expenditure Amount");
		lblMemberMebershipFee.setBounds(67, 223, 111, 20);
		contentPane.add(lblMemberMebershipFee);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(67, 114, 75, 20);
		contentPane.add(lblDate);
		
		txtPurpose = new JTextField();
		txtPurpose.setColumns(10);
		txtPurpose.setBounds(176, 183, 181, 26);
		contentPane.add(txtPurpose);
		
		txtFee = new JTextField();
		txtFee.setColumns(10);
		txtFee.setBounds(176, 220, 111, 26);
		contentPane.add(txtFee);
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(176, 111, 138, 26);
		contentPane.add(txtDate);
		
		JButton btnNewButton = new JButton("...");
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
		btnNewButton.setBounds(324, 110, 33, 29);
		contentPane.add(btnNewButton);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(new Color(25, 25, 112));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mid=cmbID.getSelectedItem().toString();

				String purpose=txtPurpose.getText();

				int amount=Integer.parseInt(txtFee.getText());
					
				try
				{
					util.stmt.executeUpdate("insert into expenditure values(default,'"+mid+"','"+purpose+"',"+amount+",'"+dOj+"')");					
					setNull();
					JOptionPane.showMessageDialog(null,"Expenditure Added Successfully");	
				}

				catch(SQLException sqle)
				{
					JOptionPane.showMessageDialog(null,"Data Could not Saved");
				}

				
				
			}
		});
		btnSave.setBounds(176, 273, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBackground(new Color(25, 25, 112));
		btnRefresh.setForeground(new Color(255, 255, 255));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setNull();
			}
		});
		btnRefresh.setBounds(275, 273, 89, 23);
		contentPane.add(btnRefresh);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(25, 25, 112));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(374, 273, 89, 23);
		contentPane.add(btnCancel);
		
		cmbID = new JComboBox();
		cmbID.setBounds(176, 151, 181, 22);
			try
			{
				String sql = "select m_id from member";
				PreparedStatement ps = util.conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{				
					cmbID.addItem(rs.getString(1));
				}
	
			}
	
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
				JOptionPane.ERROR_MESSAGE);
			}
		contentPane.add(cmbID);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		panel.setBounds(0, 22, 499, 43);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Expenditure");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel.setBounds(0, 5, 489, 27);
		panel.add(lblNewLabel);
		setVisible(true);
	}

	public void setNull()
	{
		txtPurpose.setText("");
		cmbID.setSelectedIndex(0);
		txtDate.setText("");
		txtFee.setText("");
	}
}
