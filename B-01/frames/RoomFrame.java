package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

import repository.*;
import entity.*;


public class RoomFrame extends JFrame implements ActionListener
{
	private JPanel panel;
	private JLabel numberLabel,typeLabel,reservedLabel;
	private JButton loadBtn, insertBtn, updateBtn, deleteBtn, refreshBtn, getAllBtn, backBtn, bookBtn,logoutBtn;
	private JTextField numberTF,typeTF,reservedTF;
	private JTable roomTable;
	private JScrollPane roomTableSP;
	Color myColor;
	
	private User user;
	private RoomRepo rr;
	private UserRepo ur;
	
	public RoomFrame(User user)
	{
		super("RoomFrame");
		this.setSize(800,450);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		
		rr = new RoomRepo();
		ur = new UserRepo();
		
		panel = new JPanel();
		panel.setLayout(null);
		
		String data[][] = {{"", "", ""}};
		String head[] = {"Number", "Type", "Reserved",};
		
		roomTable = new JTable(data,head);
		roomTableSP = new JScrollPane(roomTable);
		roomTableSP.setBounds(350, 100, 400, 150);
		roomTable.setEnabled(false);
		panel.add(roomTableSP);
		
		numberLabel = new JLabel("Room No.:");
		numberLabel.setBounds(100,100,100,30);
		//numberLabel.setForeground(Color.WHITE);
		panel.add(numberLabel);
		
		numberTF = new JTextField();
		numberTF.setBounds(220,100,100,30);
		panel.add(numberTF);
		
		typeLabel = new JLabel("Type :");
		typeLabel.setBounds(100,150,100,30);
		//typeLabel.setForeground(Color.WHITE);
		panel.add(typeLabel);
		
		typeTF = new JTextField();
		typeTF.setBounds(220,150,100,30);
		panel.add(typeTF);
		
		reservedLabel = new JLabel("Reserved :");
		reservedLabel.setBounds(100,200,70,30);
		//reservedLabel.setForeground(Color.WHITE);
		panel.add(reservedLabel);
		
		reservedTF = new JTextField();
		reservedTF.setBounds(220,200,100,30);
		panel.add(reservedTF);
		
		
		loadBtn = new JButton("Load");
		loadBtn.setBounds(100,300,80,30);
		loadBtn.addActionListener(this);
		panel.add(loadBtn);
		
		insertBtn = new JButton("Insert");
		insertBtn.setBounds(200,300,80,30);
		insertBtn.addActionListener(this);
		panel.add(insertBtn);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(300,300,80,30);
		updateBtn.addActionListener(this);
		updateBtn.setEnabled(false);
		panel.add(updateBtn);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(400,300,80,30);
		deleteBtn.addActionListener(this);
		deleteBtn.setEnabled(false);
		panel.add(deleteBtn);
		
		refreshBtn = new JButton("Refresh");
		refreshBtn.setBounds(500,300,80,30);
		refreshBtn.addActionListener(this);
		refreshBtn.setEnabled(false);
		panel.add(refreshBtn);
		
		getAllBtn = new JButton("Get All");
		getAllBtn.setBounds(600,300,80,30);
		getAllBtn.addActionListener(this);
		panel.add(getAllBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(600, 350, 80, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		bookBtn = new JButton("Book");
		bookBtn.setBounds(500, 350, 80, 30);
		bookBtn.addActionListener(this);
		panel.add(bookBtn);
		
		logoutBtn = new JButton("Log Out");
		logoutBtn.setBounds(600, 260, 100, 30);
		logoutBtn.addActionListener(this);
		logoutBtn.setBackground(Color.WHITE);
		panel.add(logoutBtn);
		
		/*ImageIcon background_image = new ImageIcon("rf.jpg");
		Image img = background_image.getImage();
		Image temp_img = img.getScaledInstance(800,450,Image.SCALE_SMOOTH);
		background_image = new ImageIcon(temp_img);
		JLabel background = new JLabel(" ",background_image, JLabel.CENTER);
		background.setBounds(0,0,800,450);
		panel.add(background);*/
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(loadBtn.getText()))
		{
			if(!numberTF.getText().equals("") || !numberTF.getText().equals(null))
			{
				Room r = rr.searchRoom(numberTF.getText());
				if(r!= null)
				{
					numberTF.setText(r.getroomNumber());
					typeTF.setText(r.getType());
					reservedTF.setText(r.getReserved());
					
					numberTF.setEnabled(false);
					typeTF.setEnabled(true);
					reservedTF.setEnabled(true);
					
					updateBtn.setEnabled(true);
					deleteBtn.setEnabled(true);
					refreshBtn.setEnabled(true);
					insertBtn.setEnabled(false);
					loadBtn.setEnabled(false);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Invaild number");
				}
			}
		}
		else if(command.equals(insertBtn.getText()))
		{
			Room r = new Room();
			
			r.setroomNumber(numberTF.getText());
			r.setType(typeTF.getText());
			r.setReserved(reservedTF.getText());
			
			
			rr.insertInDB(r);
			
			JOptionPane.showMessageDialog(this, "Inserted, Id: "+numberTF.getText()+"and Type: "+typeTF.getText()+"and Reserved:"+reservedTF.getText());
			
			numberTF.setText("");
			typeTF.setText("");
			reservedTF.setText("");
			
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
		}
		else if(command.equals(refreshBtn.getText()))
		{
			numberTF.setText("");
			typeTF.setText("");
			reservedTF.setText("");
			
			numberTF.setEnabled(true);
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		else if(command.equals(updateBtn.getText()))
		{
			Room r = new Room();
			
			r.setroomNumber(numberTF.getText());
			r.setType(typeTF.getText());
			r.setReserved(reservedTF.getText());
			
			
			rr.updateInDB(r);
			
			JOptionPane.showMessageDialog(this, "Updated");
			
			numberTF.setText("");
			typeTF.setText("");
			reservedTF.setText("");
			
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
			numberTF.setEnabled(true);
			typeTF.setEnabled(true);
			reservedTF.setEnabled(true);
		}
		else if(command.equals(deleteBtn.getText()))
		{
			rr.deleteFromDB(numberTF.getText());
			
			JOptionPane.showMessageDialog(this,"Deleted");
			
			numberTF.setText("");
			typeTF.setText("");
			reservedTF.setText("");
			
			numberTF.setEnabled(true);
			typeTF.setEnabled(true);
			reservedTF.setEnabled(true);
	
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		else if(command.equals(getAllBtn.getText()))
		{
			String data[][] = rr.getAllRoom();
		    String head[] = {"Number", "Type", "Reserved",};
			
			panel.remove(roomTableSP);
			
			roomTable = new JTable(data,head);
			roomTable.setEnabled(false);
			roomTableSP = new JScrollPane(roomTable);
			roomTableSP.setBounds(350, 100, 400, 150);
			panel.add(roomTableSP);
			
			panel.revalidate();
			panel.repaint();
			
		}
		else if(command.equals(backBtn.getText()))
		{
		     ManageFrame eh = new ManageFrame(user);
			eh.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(bookBtn.getText()))
		{
			PaymentFrame pf = new PaymentFrame(user);
			pf.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(logoutBtn.getText()))
		{
			LoginFrame lf = new LoginFrame();
			lf.setVisible(true);
			this.setVisible(false);
		}
		
	}	
}









