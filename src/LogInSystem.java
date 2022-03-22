import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class LogInSystem extends JFrame implements ActionListener
{

	JLabel lblUserName = new JLabel("User Name");
	JTextField txtUserName = new JTextField(20);

	JLabel lblPassward = new JLabel("Password");
	JPasswordField txtPassward = new JPasswordField();

	JButton btnLogin = new JButton("Log In");

	JButton btnCancel = new JButton("Cancel");

	DbUtil util=new DbUtil();
	boolean flag=false;
	private final JLabel label = new JLabel("");

	public LogInSystem()
	{
		this.getContentPane().setBackground(new Color(51, 102, 153));
		getContentPane().setLayout(null);
		lblUserName.setForeground(new Color(255, 255, 255));
		lblUserName.setBounds(78, 82, 82, 20);


		this.getContentPane().add(lblUserName);
		txtUserName.setBackground(new Color(204, 204, 204));
		txtUserName.setBounds(175, 73, 212, 29);
		this.getContentPane().add(txtUserName);
		lblPassward.setForeground(new Color(255, 255, 255));
		lblPassward.setBounds(78, 131, 82, 20);

		this.getContentPane().add(lblPassward);
		txtPassward.setBackground(new Color(204, 204, 204));
		txtPassward.setBounds(175, 122, 212, 29);
		this.getContentPane().add(txtPassward);
		btnLogin.setBounds(175, 180, 100, 20);

		this.getContentPane().add(btnLogin);
		btnCancel.setBounds(285, 180, 100, 20);
		this.getContentPane().add(btnCancel);
		label.setForeground(new Color(255, 255, 255));
		label.setIcon(new ImageIcon("C:\\Users\\ERP-RFL\\Downloads\\Compressed\\New\\img\\BM.png"));
		label.setBounds(-70, 0, 755, 244);
		
		getContentPane().add(label);


		btnCancel.addActionListener(this);
		btnLogin.addActionListener(this);
		txtPassward.addActionListener(this);

		this.setBackground(Color.green);

		this.setTitle("LOG IN SYSTEM");
		this.setBounds(300,100,701,277);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==btnCancel)
		{
			this.dispose();
		}

		if(ae.getSource()==btnLogin || ae.getSource()==txtPassward)
		{
			Vector vecUser=new Vector();
			Vector vecPass=new Vector();
			String user=txtUserName.getText();
			String pass=txtPassward.getText();
			

			try
			{

				ResultSet rs=util.stmt.executeQuery("Select userName, password from Login");
				while(rs.next())
				{
					vecUser.addElement(rs.getString(1));
					vecPass.addElement(rs.getString(2));
				}
			}

			catch(Exception e)
			{
				System.out.println(e);
			}
			
			for(int i=0;i<vecUser.size();i++)
			{
				if(vecUser.elementAt(i).equals(user) && vecPass.elementAt(i).equals(pass))
				{
					System.out.println("Log in successful BoSs.. ");
					flag=true;
				}

			}

			if(flag)
			{
				MainInterface mi=new MainInterface();
				this.dispose();				
			}

			else
			JOptionPane.showMessageDialog(null, "Invalid username or Password");
		}
	}
	
	public static void main(String args[])
	{
		LogInSystem li=new LogInSystem();
	}
	
}