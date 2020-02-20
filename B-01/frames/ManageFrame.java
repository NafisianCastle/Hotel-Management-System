package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import interfaces.*;
import repository.*;
import entity.*;


public class ManageFrame extends JFrame implements ActionListener
{
	JButton logoutBtn, manageEmpBtn, manageRoomBtn;
	JPanel panel;
	User user;
	
	public ManageFrame(User user)
	{
		super("Manage");
		this.setSize(800,450);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		
		panel = new JPanel();
		panel.setLayout(null);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(300, 300, 150, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		
		manageEmpBtn = new JButton("Manage Employee");
		manageEmpBtn.setBounds(50, 100, 150, 30);
		manageEmpBtn.addActionListener(this);
		panel.add(manageEmpBtn);
		
		manageRoomBtn = new JButton("Manage Room");
		manageRoomBtn.setBounds(250, 100, 150, 30);
		manageRoomBtn.addActionListener(this);
		panel.add(manageRoomBtn);
		
		ImageIcon background_image = new ImageIcon("ht3.jpg");
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
		
		if(command.equals(logoutBtn.getText()))
		{
			LoginFrame lf = new LoginFrame();
			lf.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(manageEmpBtn.getText()))
		{
			if(user.getStatus()==0)
			{
				EmployeeFrame ef = new EmployeeFrame(user);
				ef.setVisible(true);
				this.setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Access Denied");
			}
		}
		else if(command.equals(manageRoomBtn.getText()))
		{
			RoomFrame rf= new RoomFrame(user);
			rf.setVisible(true);
			this.setVisible(false);
		}
		
		else{}
	}
}