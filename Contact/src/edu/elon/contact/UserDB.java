package edu.elon.contact;

import java.sql.*;
import java.util.ArrayList;


public class UserDB {
	
	private static String userName;
	private static String password;
	private static String IPaddress;
	private static String dBName;
	private static String tableName;
	private static String connectionString;
	
	public UserDB() {
	        userName=null;
	        password = null;
	        IPaddress = null;
	        dBName = null;
	        tableName = null;
	        connectionString=null;
	    }
	
    public UserDB(String user, String pass, String iP, String dB, String table) {
    	 	userName= user;
        password = pass;
        IPaddress = iP;
        dBName = dB;
        tableName = table;
        connectionString="jdbc:mysql://"+IPaddress+"/"+dBName;
    }
   
    
    public static int insert(Contact contact) {
        PreparedStatement ps = null;

        String query
                = "INSERT INTO (id, firstName, middleName, lastName, email, "
                + "major) "
                + "VALUES (default, ?, ?, ?, ?, ?)";
        try {
        		Connection connection=DriverManager.getConnection(connectionString,userName,password);
            ps = connection.prepareStatement(query);
            ps.setString(1, tableName);
            ps.setString(2, contact.getFirstName());
            ps.setString(3, contact.getMiddleName());
            ps.setString(4, contact.getLastName());
            ps.setString(5, contact.getEmail());
            ps.setString(6, contact.getMajor());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } 
    }
    
    public static int update(Contact contact) {
    		      
        PreparedStatement ps = null;

        String query
                = "UPDATE ?"
                +" SET firstName=?, middleName=?, lastName=?, email=?, major=?"+
                "WHERE id= ?";
        try {   
        		Connection connection=DriverManager.getConnection(connectionString,userName,password);
            ps = connection.prepareStatement(query);
            ps.setString(1, tableName);
            ps.setString(2, contact.getFirstName());
            ps.setString(3, contact.getMiddleName());
            ps.setString(4, contact.getLastName());
            ps.setString(5, contact.getEmail());
            ps.setString(6, contact.getMajor());
            ps.setString(7, Integer.toString(contact.getId()));
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }

    

    public static int delete(Contact contact) {
        PreparedStatement ps = null;

        String query = "DELETE FROM ? "
                + "WHERE email = ?";
        try {
        		Connection connection=DriverManager.getConnection(connectionString,userName,password);
            ps = connection.prepareStatement(query);
            ps.setString(1, tableName);
            ps.setString(2, contact.getEmail());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } 
    }


    public static Contact selectContact(String email) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM ? "
                + "WHERE email = ?";
        try {
        		Connection connection=DriverManager.getConnection(connectionString,userName,password);
            ps = connection.prepareStatement(query);
            ps.setString(1, tableName);
            ps.setString(2, email);
            rs = ps.executeQuery();
            Contact contact = null;
            if (rs.next()) {
            contact = new Contact(rs.getInt("id"), rs.getString("firstName"),rs.getString("middleName"),
                		rs.getString("lastName"), rs.getString("email"),rs.getString("major"));
            }
            return contact;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } 
    }
    
    public static ArrayList<Contact> selectContacts() {
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        String query = "SELECT * FROM ?";
        try {
        		Connection connection=DriverManager.getConnection(connectionString,userName,password);
            ps = connection.prepareStatement(query);
            ps.setString(1, tableName);
            rs = ps.executeQuery();
            ArrayList<Contact> contacts = new ArrayList<Contact>();
            while (rs.next())
            {
                Contact contact = new Contact(rs.getInt("id"), rs.getString("firstName"),rs.getString("middleName"),
                		rs.getString("lastName"), rs.getString("email"),rs.getString("major"));
                contacts.add(contact);
            }
            return contacts;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } 
    } 
    
    public static int clearDB() {
        PreparedStatement ps = null;
        
        String query
                = "TRUNCATE TABLE ?";
        try {
        	   Connection connection=DriverManager.getConnection(connectionString,userName,password);
            ps = connection.prepareStatement(query);
            ps.setString(1, tableName);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } 
    }
}