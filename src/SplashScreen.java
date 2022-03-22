
import java.awt.*;
import javax.swing.*;



public class SplashScreen extends JWindow{
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel lblPic = new JLabel();
  JLabel lblStatus = new JLabel();
  Thread time=new Thread();


  public SplashScreen()
  {

	Toolkit toolkit=Toolkit.getDefaultToolkit();
	Dimension size=toolkit.getScreenSize();

	this.getContentPane().setBackground(Color.WHITE);
	this.getContentPane().setLayout(null);

    this.getContentPane().setLayout(new BorderLayout());
    this.getContentPane().add(lblPic, BorderLayout.CENTER);
    ImageIcon image=new ImageIcon("./img/logo.jpg");
	lblPic.setIcon(image);
    lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));

    this.getContentPane().add(lblStatus, BorderLayout.SOUTH);
    this.setBounds(size.width/2-190,size.height/2-330,330,517);


    this.setVisible(true);
    timer();
  }

   public void timer()
  {
	
	try
	{
		lblStatus.setText("Connecting with Database................");
		DbUtil util=new DbUtil();
		time.sleep(1000);
		lblStatus.setText("Database Connected.............");

		time.sleep(1000);
		lblStatus.setText("Updating Databse.............");

		time.sleep(1000);
		lblStatus.setText("Database Updated...");

		time.sleep(1000);
		lblStatus.setText("Preparing Login Interface...........");

		time.sleep(1000);

		LogInSystem login = new LogInSystem();
		
		this.dispose();
	}

	catch(InterruptedException ie)
	{

	}
  }

  public static void main(String[] args)
  {
    SplashScreen splashScreen = new SplashScreen();
  }
}