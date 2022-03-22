import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.awt.event.*;

public class SearchResult extends JInternalFrame implements ActionListener{
	JLabel lblExpenditureFrom;
	JTextField txtDateFrom;
	JTextField txtDateTo;
	JButton button;
	JPanel panel;
	static JTable table;
	String dateFrom, dateTo;

	DbUtil util=new DbUtil();
	String[] columnNames = {"Fees ID", "Member ID", "Purpose", "Amount"};

	public SearchResult()
	{
		setTitle("Expenditure");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		lblExpenditureFrom = new JLabel("Expenditure To");
		lblExpenditureFrom.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpenditureFrom.setBounds(420, 64, 107, 20);
		button = new JButton("search");
		button.setBackground(new Color(25, 25, 112));
		button.setForeground(new Color(255, 255, 255));
		button.setBounds(346,102,100,20);
		button.addActionListener(this);
		getContentPane().add(lblExpenditureFrom);
		
		txtDateFrom=new JTextField();
		txtDateFrom.setBounds(261, 64, 118, 20);
		getContentPane().add(txtDateFrom);
		
		JButton btnNewButton = new JButton("...");
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
			  public void actionPerformed(ActionEvent e) {
		            SimpleDateFormat sdf=new SimpleDateFormat();
		            sdf.applyPattern("yyyy-MM-dd");
		            ShowCalendar sc=new ShowCalendar((int)btnNewButton.getLocationOnScreen().getX(),(int)btnNewButton.getLocationOnScreen().getY(),btnNewButton.getWidth());
		            dateFrom=sdf.format(sc.calendar.getDate());
		            txtDateFrom.setText(dateFrom);
			  }
			});
		txtDateFrom.setEditable(false);
		btnNewButton.setBounds(383, 62, 27, 24);
		getContentPane().add(btnNewButton);
		
		txtDateTo=new JTextField();
		txtDateTo.setBounds(525, 64, 118, 20);
		getContentPane().add(txtDateTo);
		
		JButton btnNewButton1 = new JButton("...");
		btnNewButton1.addActionListener(new java.awt.event.ActionListener() {
			  public void actionPerformed(ActionEvent e) {
		            SimpleDateFormat sdf=new SimpleDateFormat();
		            sdf.applyPattern("yyyy-MM-dd");
		            ShowCalendar sc=new ShowCalendar((int)btnNewButton1.getLocationOnScreen().getX(),(int)btnNewButton1.getLocationOnScreen().getY(),btnNewButton1.getWidth());
		            dateTo=sdf.format(sc.calendar.getDate());
		            txtDateTo.setText(dateTo);
			  }
			});
		txtDateTo.setEditable(false);
		
		btnNewButton1.setBounds(653, 62, 27, 24);
		getContentPane().add(btnNewButton1);
		
		getContentPane().add(button);
		
		JLabel lblExpenditureFrom_1 = new JLabel("Expenditure From");
		lblExpenditureFrom_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpenditureFrom_1.setBounds(135, 64, 118, 20);
		getContentPane().add(lblExpenditureFrom_1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(25, 25, 112));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(463, 102, 100, 20);
		getContentPane().add(btnCancel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(25, 25, 112));
		panel_1.setBounds(0, 11, 843, 40);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblExpenditure = new JLabel("Expenditure");
		lblExpenditure.setForeground(new Color(255, 255, 255));
		lblExpenditure.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblExpenditure.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpenditure.setBounds(0, 0, 843, 40);
		panel_1.add(lblExpenditure);
		this.setVisible(true);
		this.setBounds(100,100,859, 500);
	}

	public void actionPerformed(ActionEvent ae)
	{
		button = (JButton)ae.getSource();
		showTableData();
	}

	public void showTableData()
	{
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);

		table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		String x_id= "";
		String m_id= "";
		String x_purpose = "";
		String x_amount = "";
		try
		{
			String sql = "select * from expenditure where ex_payDate between '"+dateFrom+"' AND '"+dateTo+"'";
			PreparedStatement ps = util.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();			
			while(rs.next())
			{
				x_id = rs.getString("ex_id");
				m_id = rs.getString("m_id");
				x_purpose = rs.getString("ex_purpose");
				x_amount = rs.getString("ex_amount");
				model.addRow(new Object[]{x_id, m_id, x_purpose, x_amount});				
			}

		}

		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
			JOptionPane.ERROR_MESSAGE);
		}

		getContentPane().add(scroll);
		scroll.setBounds(80,130,700,320);
	}
}