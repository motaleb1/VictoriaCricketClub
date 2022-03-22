import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class MatchResult extends JInternalFrame implements ActionListener{
	JLabel lblExpenditureFrom;
	JButton button;
	JPanel panel;
	static JTable table;	
	JComboBox comboBox_1;
	JComboBox comboBox;
	
	DbUtil util=new DbUtil();
	String[] columnNames = {"Match No", "Team Name", "Opposition", "Winner"};
	private JPanel panel_1;
	private JLabel lblMatchResult;

	public MatchResult()
	{
		
		setTitle("Match Result");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		lblExpenditureFrom = new JLabel("Opposition Team");
		lblExpenditureFrom.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpenditureFrom.setBounds(374, 63, 94, 20);
		button = new JButton("search");
		button.setBackground(new Color(25, 25, 112));
		button.setForeground(new Color(255, 255, 255));
		button.setBounds(315,94,100,20);
		button.addActionListener(this);
		getContentPane().add(lblExpenditureFrom);
		
		getContentPane().add(button);
		
		JLabel lblExpenditureFrom_1 = new JLabel("Host Team");
		lblExpenditureFrom_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpenditureFrom_1.setBounds(171, 63, 83, 20);
		getContentPane().add(lblExpenditureFrom_1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(25, 25, 112));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(425, 94, 100, 20);
		getContentPane().add(btnCancel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(264, 62, 100, 22);
		
		try
		{
			String sql = "select DISTINCT t_name from team";
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
		
		getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(478, 62, 164, 22);
		
		try
		{
			String sql = "select DISTINCT opposition from matches";
			PreparedStatement ps = util.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{				
				comboBox_1.addItem(rs.getString(1));
			}

		}

		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
			JOptionPane.ERROR_MESSAGE);
		}

		getContentPane().add(comboBox_1);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(25, 25, 112));
		panel_1.setBounds(0, 0, 853, 51);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblMatchResult = new JLabel("Match Result");
		lblMatchResult.setBounds(0, 5, 833, 46);
		lblMatchResult.setForeground(new Color(255, 255, 255));
		lblMatchResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatchResult.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		panel_1.add(lblMatchResult);
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
		String myTeam=comboBox.getSelectedItem().toString();
		
		String opposite= comboBox_1.getSelectedItem().toString();
		
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
		
		String m_id= "";
		String t_name= "";
		String oppo = "";
		String win = "";
		try
		{
			String sql = "select * from matches where t_name= '"+myTeam+"' AND opposition=  '"+opposite+"'";
			PreparedStatement ps = util.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();			
			while(rs.next())
			{
				m_id = rs.getString("match_no");
				t_name = rs.getString("t_name");
				oppo = rs.getString("opposition");
				win = rs.getString("winner");
				model.addRow(new Object[]{m_id, t_name, oppo, win});				
			}

		}

		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, ex);
		}		
		getContentPane().add(scroll);
		scroll.setBounds(80,130,700,320);
	}
}