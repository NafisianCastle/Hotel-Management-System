package interfaces;

import java.lang.*;

import entity.*;

public interface IRoomRepo
{
	public void insertInDB(Room r);
	public void deleteFromDB(String roomNumber);
	public void updateInDB(Room r);
	public Room searchRoom(String roomNumber);
	public String[][] getAllRoom();
}