import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MyJdbc {
public static void main(String[] args) throws SQLException {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lakshya","root","root");
		if(con!=null) {
			System.out.println("connected");
			Statement st= con.createStatement();
			String query1="select * from people";
			Scanner sc =new Scanner(System.in);
			System.out.println("enter \n 1.Insert \n 2.Update \n 3.Delete \n 4.Display all \n 5.Display using Id \n 6.Search using Name \n 7. Sort using Name \n");
			int ch=sc.nextInt();
			if(ch==1) {
			System.out.println("Enter how many peoples : ");
			int i=Integer.parseInt(sc.next());
			String first;
			String last;
			int age;
			String city;
			long id;
			//String insertQuery =;
			PreparedStatement ps=con.prepareStatement("Insert into people values(?,?,?,?,?);");
			for(int j=0;j<i;j++) {
				System.out.print("Enter First name :");
				first = (sc.next());
				System.out.print("Enter Last name :");
				last = (sc.next());
				System.out.print("Enter age :");
				age = Integer.parseInt(sc.next());
				System.out.print("Enter city name :");
				city = (sc.next());
				System.out.print("Enter ID :");
				id = Long.parseLong(sc.next());
				ps.setString(1,first);
				ps.setString(2, last);
				ps.setInt(3, age);
				ps.setString(4, city);
				ps.setLong(5, id);
				ps.executeUpdate();
			}
			
			ResultSet rs1= st.executeQuery(query1);
			while(rs1.next()) {
				System.out.println("first : "+rs1.getString(1)+" last : "+rs1.getString(2)+
				" age : "+rs1.getInt(3)+" city : "+rs1.getString(4)+" Id : "+rs1.getLong(5));
			}
			}
			else if(ch==2) {
				System.out.print("Enter ID :");
				long id = Long.parseLong(sc.next());
				System.out.print("Enter First name :");
				String first = (sc.next());
				System.out.print("Enter Last name :");
				String last = (sc.next());
				System.out.print("Enter age :");
				int age = Integer.parseInt(sc.next());
				System.out.print("Enter city name :");
				String city = (sc.next());
				PreparedStatement psUpdate=con.prepareStatement("UPDATE people SET First=?, Last=?, Age=?, City=? WHERE ID=?;");
				psUpdate.setString(1,first);
				psUpdate.setString(2, last);
				psUpdate.setInt(3, age);
				psUpdate.setString(4, city);
				psUpdate.setLong(5, id);
				psUpdate.executeUpdate();

			}
			else if (ch==3) {
				PreparedStatement ps=con.prepareStatement("DELETE FROM people WHERE ID=?");
				System.out.print("Enter ID :");
				long id = Long.parseLong(sc.next());
				ps.setLong(1, id);
				ps.executeUpdate();
				ResultSet rs1= st.executeQuery(query1);
				while(rs1.next()) {
					System.out.println("first : "+rs1.getString(1)+" last : "+rs1.getString(2)+
					" age : "+rs1.getInt(3)+" city : "+rs1.getString(4)+" Id : "+rs1.getLong(5));
				}
			}
			else if(ch==4) {
				ResultSet rs1= st.executeQuery(query1);
				while(rs1.next()) {
					System.out.println("first : "+rs1.getString(1)+" last : "+rs1.getString(2)+
					" age : "+rs1.getInt(3)+" city : "+rs1.getString(4)+" Id : "+rs1.getLong(5));
				}

			}
			else if(ch==5) {
				PreparedStatement ps=con.prepareStatement("SElECT * FROM people WHERE ID=?");
				System.out.print("Enter ID :");
				long id = Long.parseLong(sc.next());
				ps.setLong(1, id);
				ResultSet rs1= ps.executeQuery();
				while(rs1.next()) {
					System.out.println("first : "+rs1.getString(1)+" last : "+rs1.getString(2)+
					" age : "+rs1.getInt(3)+" city : "+rs1.getString(4)+" Id : "+rs1.getLong(5));
				}
			}
			else if(ch==6) {
				PreparedStatement ps=con.prepareStatement("SElECT * FROM people WHERE first=?");
				System.out.print("Enter First name :");
				String first = (sc.next());
				ps.setString(1, first);
				ResultSet rs1= ps.executeQuery();
				while(rs1.next()) {
					System.out.println("first : "+rs1.getString(1)+" last : "+rs1.getString(2)+
					" age : "+rs1.getInt(3)+" city : "+rs1.getString(4)+" Id : "+rs1.getLong(5));
				}
			}
			else if(ch==7) {
				PreparedStatement ps=con.prepareStatement("SElECT * FROM people ORDER BY First");
				ResultSet rs1= ps.executeQuery();
				while(rs1.next()) {
					System.out.println("first : "+rs1.getString(1)+" last : "+rs1.getString(2)+
							" age : "+rs1.getInt(3)+" city : "+rs1.getString(4)+" Id : "+rs1.getLong(5));
				}
			}
			sc.close();
		}
		else {
			System.out.println("Not Connected");
		}
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	}
	
	
}
}
