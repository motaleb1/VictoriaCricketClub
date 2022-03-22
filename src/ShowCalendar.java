

import java.awt.*;
import javax.swing.*;
import com.toedter.calendar.*;
import java.awt.event.*;


public class ShowCalendar extends JDialog {
  JCalendar calendar=new JCalendar(true);
  JButton btnOk = new JButton();

  public ShowCalendar(int x,int y,int a) {
    this.getContentPane().setLayout(null);
    calendar.setBounds(new Rectangle(3,3, 305, 227));
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setResizable(false);
    this.setBounds(x+a+5,y,311,265);
    btnOk.setBounds(new Rectangle(125, 235, 64, 28));
    btnOk.setText("OK");
    btnOk.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });

    this.getContentPane().add(calendar);
    this.getContentPane().add(btnOk, null);
    this.getContentPane().setBackground(Color.PINK);
    this.setUndecorated(true);
    this.setVisible(true);  }

}