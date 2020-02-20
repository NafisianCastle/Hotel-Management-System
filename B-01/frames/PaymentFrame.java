package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import entity.*;



public class PaymentFrame extends JFrame implements ActionListener
{
	private JPanel panel;
	private JButton confirmBtn,logoutBtn,backBtn;
	private JComboBox combo;
	private User user;
	
	
	public PaymentFrame(User user)
	{
		super("Payment");
		this.setSize(800, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.user = user;
		
		Font f1= new Font("Serif",Font.BOLD,30);
		
		JPanel heading;
		heading=new JPanel();
		heading.setBackground(Color.CYAN);
		heading.setBounds(0,0,800,75);
		JLabel name= new JLabel(" Payment Method");
		name.setBounds(200,25,400,50);
		name.setFont(f1);
                
		heading.add(name);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		
		confirmBtn = new JButton("Confirm");
	    confirmBtn.setBounds(150, 260, 100, 30);
		confirmBtn.addActionListener(this);
		confirmBtn.setBackground(Color.WHITE);
		panel.add(confirmBtn);
		
		
		backBtn = new JButton("Back");
		backBtn.setBounds(300, 260, 100, 30);
		backBtn.addActionListener(this);
		backBtn.setBackground(Color.WHITE);
		panel.add(backBtn);
		
		logoutBtn = new JButton("Log Out");
		logoutBtn.setBounds(450, 260, 100, 30);
		logoutBtn.addActionListener(this);
		logoutBtn.setBackground(Color.WHITE);
		panel.add(logoutBtn);
			
		
		String items[] = new String []{"Cash", "Card", "BKash"};
		combo = new JComboBox(items);
		combo.setBounds(300, 180, 100, 30);
		combo.setBackground(Color.WHITE);
		panel.add(combo);
			
		ImageIcon background_image = new ImageIcon("pm.jpg");
		Image img = background_image.getImage();
		Image temp_img = img.getScaledInstance(800,450,Image.SCALE_SMOOTH);
		background_image = new ImageIcon(temp_img);
		JLabel background = new JLabel(" ",background_image, JLabel.CENTER);
		background.setBounds(0,0,800,450);
		panel.add(background);
		
		this.add(panel);
	}
		public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		if(command.equals(backBtn.getText()))
		{
			RoomFrame rf= new RoomFrame(user);
			rf.setVisible(true);
			this.setVisible(false);
		}
		if(command.equals(logoutBtn.getText()))
		{
			LoginFrame lf = new LoginFrame();
			lf.setVisible(true);
			this.setVisible(false);
		}
		if(command.equals(confirmBtn.getText()))
		{
			JOptionPane.showMessageDialog(this, "Payment confirmed Successfully !!!");
		}
		
	}
	
	
	
}










