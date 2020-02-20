package frames;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


import repository.*;
import entity.*;

public class LoginFrame extends JFrame implements ActionListener,MouseListener
{
	JLabel  userLabel, passLabel;
	JTextField userTF;
	JPasswordField passPF;
	JButton loginBtn, exitBtn,showPassBtn;
	JPanel panel;
	
	
	public LoginFrame()
	{
		super("Hotel Management System - Login Window");
		
		this.setSize(800, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		Font f1= new Font("Serif",Font.BOLD,30);
		
		JPanel heading;
		heading=new JPanel();
		heading.setBackground(Color.CYAN);
		heading.setBounds(0,0,800,75);
		JLabel name= new JLabel(" Hotel Management System");
		name.setBounds(200,25,400,50);
		name.setFont(f1);
                
		heading.add(name);
		 
		
		panel = new JPanel();
		panel.setLayout(null);
		
		Font f2= new Font("Serif",Font.BOLD,19);
		userLabel = new JLabel("User ID : ");
		userLabel.setBounds(300, 150, 100, 30);
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(f2);
		panel.add(userLabel);
	
		
		userTF = new JTextField();
		userTF.setBounds(410, 150, 100, 30);
		
		panel.add(userTF);
		
		Font f3= new Font("Serif",Font.BOLD,19);
		passLabel = new JLabel("Password : ");
		passLabel.setBounds(300, 200, 100, 30);
		passLabel.setForeground(Color.WHITE);
		passLabel.setFont(f3);
		panel.add(passLabel);
		
		passPF = new JPasswordField();
		passPF.setBounds(410, 200, 100, 30);
		passPF.setEchoChar('*');
		
		panel.add(passPF);
		
		showPassBtn = new JButton("Show");
		showPassBtn.setBounds(520,200,80,30);
		showPassBtn.addMouseListener(this);
		panel.add(showPassBtn);
		
		loginBtn = new JButton("Login");
		loginBtn.setBounds(360, 300, 80, 30);
		loginBtn.addActionListener(this);
		loginBtn.setBackground(Color.GREEN);
		
		panel.add(loginBtn);
		
		
		exitBtn = new JButton("Exit");
		exitBtn.setBounds(470, 300, 80, 30);
		exitBtn.addActionListener(this);
		exitBtn.setBackground(Color.RED);
		
		panel.add(exitBtn);
		
		ImageIcon background_image = new ImageIcon("ht3.jpg");
		Image img = background_image.getImage();
		Image temp_img = img.getScaledInstance(800,450,Image.SCALE_SMOOTH);
		background_image = new ImageIcon(temp_img);
		JLabel background = new JLabel(" ",background_image, JLabel.CENTER);
		background.setBounds(0,0,800,450);
		panel.add(background);
        background.add(heading);
		
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		if(command.equals(loginBtn.getText()))
		{
			UserRepo ur = new UserRepo();
			User user = ur.getUser(userTF.getText(), passPF.getText());
			
			if(user != null)
			{
				if(user.getStatus() == 0 || user.getStatus() == 1)
				{
					ManageFrame eh = new ManageFrame(user);
					eh.setVisible(true);
					this.setVisible(false);
				}
				else{}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Invaild Id or Password");
			}
			
		}
		if(command.equals(exitBtn.getText()))
		{
			System.exit(0);
		}
		else{}
	}
	public void mouseClicked(MouseEvent me){}
	public void mousePressed(MouseEvent me)
	{
		passPF.setEchoChar((char)0);
	}
	public void mouseReleased(MouseEvent me)
	{
		passPF.setEchoChar('*');
	}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
}