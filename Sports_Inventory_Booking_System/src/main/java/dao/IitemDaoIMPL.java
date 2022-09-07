package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Item;
import util.DbConnection;

public class IitemDaoIMPL implements IItem{

public boolean addItem(Item i)
{
			try
			{
					Connection con=DbConnection.getConnection();
					String cmd="INSERT INTO item VALUES(?,?,?,?,?,?,?,?)";
					PreparedStatement ps=con.prepareStatement(cmd);
					ps.setInt(1, i.getItemId());
					ps.setString(2, i.getItemName());
					ps.setInt(3, i.getQuantity());
					ps.setInt(4,i.getRate());
					ps.setString(5, i.getCategory());
					ps.setString(6, i.getSubCategory());
					ps.setString(7, i.getBrand());
					ps.setString(8, i.getFile());
					ps.executeUpdate();
					con.close();
					return true;
			}catch(Exception e)
			{
			e.printStackTrace();
			}
			return false;
}

public ArrayList<Item> getItems()
{
try
{
		ArrayList<Item> itemList=new ArrayList<Item>();
		Connection con=DbConnection.getConnection();
		String cmd="SELECT * FROM item";
		PreparedStatement ps=con.prepareStatement(cmd);
		ResultSet res=ps.executeQuery();
		  while(res.next())
		  {
			int id=res.getInt(1);
			String itemName=res.getString(2);
			int quantity=res.getInt(3);
			int price=res.getInt(4);
			String category1=res.getString(5);
			String subCategory=res.getString(6);
			String brand=res.getString(7);
			String file=res.getString(8);
			Item l=new Item(id,itemName,quantity,price,category1,subCategory,brand,file);
			itemList.add(l);
		  }

return itemList;
}
catch(Exception e)
{
e.printStackTrace();
}
return null;
}




public boolean removeItem(int id)
{
try
{
		Connection con=DbConnection.getConnection();
		String cmd="DELETE FROM item WHERE itemId=?";
		PreparedStatement ps=con.prepareStatement(cmd);
		ps.setInt(1, id);
		ps.executeUpdate();
		return true;
}catch(Exception e)
{
e.printStackTrace();
}
return false;
}
public boolean changeDetails(int id,String name,int quantity,int rate)
{
try
{
Connection con=DbConnection.getConnection();
String cmd="UPDATE item SET name=?, quantity=?, price=? WHERE itemId=?";
PreparedStatement ps=con.prepareStatement(cmd);
ps.setString(1,name);
ps.setInt(2, quantity);
ps.setInt(3, rate);
ps.setInt(4, id);
ps.executeUpdate();
return true;
}catch(Exception e)
{
e.printStackTrace();
}
return false;
}
public boolean changeRate(int id,int rate)
{
	try
	{
			Connection con=DbConnection.getConnection();
			String cmd="UPDATE item SET price=? WHERE itemId=?";
			PreparedStatement ps=con.prepareStatement(cmd);
			ps.setInt(1,rate);
			ps.setInt(2, id);
			ps.executeUpdate();
			return true;
	}
	catch(Exception e)
	{
			e.printStackTrace();
	}
	return false;
}
public double changeQuantityBuy(int id,int quantity)
{
double price=0;
try
{
Connection con=DbConnection.getConnection();
String c="select quantity,price from item where itemId=?";
PreparedStatement ps2=con.prepareStatement(c);
ps2.setInt(1, id);
int q=0;

ResultSet res=ps2.executeQuery();
while(res.next()) {
q=res.getInt(1);
price=res.getDouble(2)*quantity;
}

String cmd1="update item set quantity=? where itemId=?";
PreparedStatement ps1=con.prepareStatement(cmd1);
ps1.setInt(1,q-quantity );
ps1.setInt(2,id);
ps1.executeUpdate();
}catch(Exception e)
{
e.printStackTrace();
}
return price;
}

public boolean changeQuantityRemove(int id,int quantity)
{
try
{
Connection con=DbConnection.getConnection();
String c="select quantity form item where itemId=?";
PreparedStatement ps2=con.prepareStatement(c);
ps2.setInt(1, id);
ResultSet res=ps2.executeQuery();
int q=res.getInt(1);


String cmd1="update item set quantity=? where itemId=?";
PreparedStatement ps1=con.prepareStatement(cmd1);
ps1.setInt(1,q+quantity );
ps1.setInt(2,id);
//ps1.setInt(3,quantity);
//ps.setString(2, i.getItemName());
//ps.setInt(3, i.getQuantity());
//ps.setDouble(4,i.getRate());
ps1.executeUpdate();
}catch(Exception e)
{
e.printStackTrace();
}
return false;
}

@Override
public Item viewItem(int id) {
// TODO Auto-generated method stub
Connection con=DbConnection.getConnection();
String cmd="SELECT * FROM item where itemId=?";
PreparedStatement ps;
try {
ps = con.prepareStatement(cmd);

ps.setInt(1, id);
ResultSet res=ps.executeQuery();
//int iid=res.getInt(1);
while(res.next()) {
String itemName=res.getString(2);
int quantity=res.getInt(3);
int price=res.getInt(4);
Item l=new Item(itemName,id,quantity,price);
return l;}
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return null;
}

@Override
public boolean changeDetails(int id, String name, int quantity, double rate) {
	// TODO Auto-generated method stub
	return false;
}

public ArrayList<Item> getItemsByCategory(String category)
{
	try 
	{
		ArrayList<Item> categoryList=new ArrayList<Item>();
		Connection con=DbConnection.getConnection();
		String cmd="SELECT * FROM item where category=?";
		PreparedStatement ps=con.prepareStatement(cmd);
		ps.setString(1,category);
		ResultSet res=ps.executeQuery();
	  while(res.next())
	  {
		int id=res.getInt(1);
		String itemName=res.getString(2);
		int quantity=res.getInt(3);
		int price=res.getInt(4);
		String category1=res.getString(5);
		String subCategory=res.getString(6);
		String brand=res.getString(7);
		String file=res.getString(8);
		Item l=new Item(id,itemName,quantity,price,category1,subCategory,brand,file);
		categoryList.add(l);
	  }
	return categoryList;
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return null;
}




}