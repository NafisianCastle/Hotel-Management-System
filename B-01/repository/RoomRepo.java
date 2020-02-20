package repository;

import java.lang.*;
import java.util.ArrayList;
import entity.*;
import interfaces.*;

public class RoomRepo implements IRoomRepo
{
	DatabaseConnection dbc;
	
	public RoomRepo()
	{
		dbc = new DatabaseConnection();
	}
	
	public void insertInDB(Room r)
	{
		String query = "INSERT INTO room VALUES ('"+r.getroomNumber()+"','"+r.getType()+"','"+r.getReserved()+"');";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}
	public void deleteFromDB(String roomNumber)
	{
		String query = "DELETE from room WHERE roomNumber='"+roomNumber+"';";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception e){System.out.println(e.getMessage());}
	}
	public void updateInDB(Room r)
	{
		String query = "UPDATE room SET type='"+r.getType()+"', reserved = '"+r.getReserved()+" WHERE roomNumber='"+r.getroomNumber()+"'";
		
		try
		{
			dbc.openConnection();
			dbc.st.executeUpdate(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}
	public Room searchRoom(String roomNumber)
	{
		Room room = null;
		String query = "SELECT `type`, `reserved` FROM `room` WHERE `roomNumber`='"+roomNumber+"';";     
		try
		{
		
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				
				String Type = dbc.result.getString("type");
				String reserved = dbc.result.getString("reserved");
				
				room = new Room();
				room.setroomNumber(roomNumber);
				room.setType(Type);
				room.setReserved(reserved);
			}
			
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
		dbc.closeConnection();
		return room;
	}
	public String[][] getAllRoom()
	{
		ArrayList<Room> ar = new ArrayList<Room>();
		String query = "SELECT * FROM room;";  
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
			
		
			while(dbc.result.next())
			{
				String roomNumber = dbc.result.getString("roomNumber");
				String Type = dbc.result.getString("type");
				String reserved = dbc.result.getString("reserved");
				
				Room r = new Room(roomNumber,Type,reserved);
				ar.add(r);
			}
		}
		catch(Exception e){System.out.println(e.getMessage());}
		dbc.closeConnection();
		
		
		Object obj[] = ar.toArray();
		String data[][] = new String [ar.size()][3];
		
		for(int i=0; i<obj.length; i++)
		{
			data[i][0] = ((Room)obj[i]).getroomNumber();
			data[i][1] = ((Room)obj[i]).getType();
			data[i][2] = ((Room)obj[i]).getReserved();
		}
		return data;
	}
}












































