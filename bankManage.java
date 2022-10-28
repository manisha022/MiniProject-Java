package simpleBankingApplication1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

class bank 
{

//insert details
	public void add_details() 
	{	
			try 
			{
				BufferedReader in=new  BufferedReader(new InputStreamReader(System.in)); 
				connectionDB ob=new connectionDB();
				Connection con=ob.c();
	     		Statement stm=con.createStatement();
				System.out.println("Enter accno  :");
				int a=Integer.parseInt(in.readLine());
				System.out.println("Enter password :");
				String b=in.readLine();
				System.out.println("Enter name :");
				String c=in.readLine();
				System.out.println("Enter contact :");
				String d=in.readLine();
				System.out.println("Enter balance :");
				float e=Float.parseFloat(in.readLine());
				System.out.println("Enter address :");
				String f=in.readLine();
				PreparedStatement pst=con.prepareStatement("insert into customer(accno,password,name,contact,balance,address) values (?,?,?,?,?,?)");
				pst.setInt(1, a);
				pst.setString(2,b);
				pst.setString(3,c);
				pst.setString(4, d);
				pst.setFloat(5, e);
				pst.setString(6, f);
				int i=pst.executeUpdate();
				if(i!=0) {
					System.out.println("Added");
				}
				else {
					System.out.println("failed to add");
				}
			 }
				catch(Exception e) {
					System.out.println(e);
				}
	}

		//login
public boolean Login(String acc,String pass) 
{
	try {
		connectionDB ob=new connectionDB();
		Connection con=ob.c();
		Statement stm=con.createStatement();
		ResultSet rst=stm.executeQuery("select * from customer where accno='"+acc+"'and password='"+pass+"'");
		if(rst.next()) {
			return true;
		}
		else {
			return false;
		}
	}
	catch(Exception e) {
		System.out.println("Customer class login method"+e);
		return false;
	}
}


//view details
public void display_Customer(String acc) 
{
	try {
		connectionDB ob=new connectionDB();
		Connection con=ob.c();
		Statement stm=con.createStatement();
		ResultSet rst=stm.executeQuery("select * from customer where accno='"+acc+"'");
		if(rst.next()) {
			System.out.println("Account no    #    : "+acc);
			System.out.println("Password           : "+rst.getString("password"));
			System.out.println("Name               : "+rst.getString("name"));
			System.out.println("Contact            : "+rst.getString("contact"));
			System.out.println("Current Balance    : "+rst.getString("balance"));
			System.out.println("address            : "+rst.getString("address"));
		}
	}
	catch(Exception e) {
		System.out.println("Customer class display method"+e);
	}
}
//change password
public void change_pass_cust(String acc,String pass) 
{
	try 
	{
		BufferedReader in=new  BufferedReader(new InputStreamReader(System.in)); 
		connectionDB ob=new connectionDB();
		Connection con=ob.c();
		Statement stm=con.createStatement();
		System.out.println("Enetr new password :");
		String p1=in.readLine();
		System.out.println("Confirm password :");
		String p2=in.readLine();
		if(p1.equals(p2)) {
			stm.executeUpdate("update customer set passwors='"+p1+"'where accno'"+acc+"'and password='"+pass+"'");
			System.out.println("password updated successfully...");
		}
		else {
			System.out.println("password does not match");
		}
	}
	catch(Exception e) {
		System.out.println("Customer class change pass method"+e);
	}
}
//withdraw
public void withdraw(String acc,String pass) 
{ 
	BufferedReader in=new  BufferedReader(new InputStreamReader(System.in)); 
	try {
	connectionDB ob=new connectionDB();
	Connection con=ob.c();
	Statement stm=con.createStatement();
	ResultSet rst=stm.executeQuery("select * from customer where accno='"+acc+"'and password='"+pass+"'");
	double amt1=0,amt2=0;
	System.out.println("Enter the amount :");
	amt2=Double.parseDouble(in.readLine());
	if(rst.next()) {
		amt1=rst.getDouble("Balance");
	}
	if(amt1-amt2<1000) {
		System.out.println("You cannot withdraw");
	}
	else {
		stm.executeUpdate("update customer set balance='"+(amt1-amt2)+"'where accno='"+acc+"'and password='"+pass+"'");
		System.out.println("money withdraw");
		System.out.println("Current balance :"+(amt1-amt2));
	}
    }
	catch(Exception e) {
		System.out.println("Customer class withdraw method"+e);
	}
}
}

public class bankManage{
//main method	
	private static String pass;
	public static void main(String[] args) 
	{
		try 
		{
			BufferedReader in=new  BufferedReader(new InputStreamReader(System.in)); 
			System.out.println("Welcome In Bank");
	        System.out.println(" ..You are Customer..");  
	        System.out.println("1-Yes or 2-No");  
	       int choice=Integer.parseInt(in.readLine());
	      String acc;  
	       switch(choice)  
	       {  
	               case 1:  
	                  bank bn=new bank();  
	                  System.out.println("Enter A/C # : ");  
	                   acc=in.readLine();
	                  System.out.println("Enter password :");  
	                   pass=in.readLine(); 
	                  boolean bb=bn.Login(acc,pass);  
	                  if(bb)  
	                  {  
	                  System.out.println("1-Display my details");  
	                  System.out.println("2-Change password");  
	                  System.out.println("3-Withdraw money");  
	                  System.out.print("Enter your choice: ");  
	                  int choice2=Integer.parseInt(in.readLine()); 
	                  switch(choice2)  
	                  {  
	                      case 1:bn.display_Customer(acc);  
	                          break;  
	                      case 2:bn.change_pass_cust(acc, pass);  
	                          break;  
	                      case 3:bn.withdraw(acc, pass);  
	                          break;  
	                      default:System.out.println("Wrong choice");  
	                  }    
	                 }  
	                  else {  
	                   System.out.println("Invalid A/C or password");  
	                  }  
	                   break;  
	               case 2 :
	            	   System.out.println("Enter your details");
	            	   bank bn1=new bank();
	            	   bn1.add_details();
	        	   System.out.println("----------Thank you----------");  	   
	       }  
	    }  
	catch(Exception e)  
	{  
	System.out.println("main method:"+e);  
	}  
	}
	}
