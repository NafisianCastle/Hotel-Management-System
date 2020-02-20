package entity;

import java.lang.*;

public class Room
{
	private String roomNumber;
	private String Type;
	private String reserved;
	
	public Room(){}
	public Room(String roomNumber, String Type, String reserved)
	{
		this.roomNumber = roomNumber;
		this.Type = Type;
		this.reserved = reserved;
	}
	
	public void setroomNumber(String roomNumber){this.roomNumber = roomNumber;}
	public void setType(String Type){this.Type = Type;}
	public void setReserved(String reserved){this.reserved = reserved;}
	
	public String getroomNumber(){return roomNumber;}
	public String getType(){return Type;}
	public String getReserved(){return reserved;}
}