package restoran.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {

    public Connection connection;
    public Statement statement;
    public ResultSet rs;
    public PreparedStatement prepared_statement;
    public String sql;
    
    private static String _JDBC_DRIVER;
    private static String _USERNAME;
    private static String _PASSWORD;
    private String _DATABASE;
    private String _CONNECTION_STRING;
    
    private String sql_command;
    //private String content;
    
    public DatabaseManager(){
        connection = null;
        statement = null;
        rs = null;
        sql = null;
        _JDBC_DRIVER = "com.mysql.jdbc.Driver";
        _USERNAME = "root";
        _PASSWORD = "";
        _DATABASE = "restoran_hr";
        _CONNECTION_STRING = "jdbc:mysql://localhost:3306/"+_DATABASE+"?useUnicode=true&characterEncoding=UTF-8";
        
        /*try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("DriverManager nije registrirao driver za JDBC");
			e1.printStackTrace();
		}*/
        
        System.out.println("Testiranje spoja na MySQL...");
        try {
            Class.forName(_JDBC_DRIVER);
            System.out.println("Driver za MySQL "+_JDBC_DRIVER+" je registrian i konekcije su omogućene ");
        } catch (ClassNotFoundException e){
            System.out.println("Ne postoji JDBC driver za MySQL na serveru ili vašem računalu.");
            e.printStackTrace();
        }
    }
    
    public Connection getConnection(){
        
        connection = null;
        
        try {
        	connection = DriverManager.getConnection(_CONNECTION_STRING, _USERNAME, _PASSWORD);
        } catch (SQLException e){
            System.out.println("Konekcija se nije izvršila: provjerite output konzolu.");
        } finally {
            if(connection != null){
                System.out.println("Konekcija na bazu je uspješno ostvarena.");
            } else {
                System.out.println("Konekcija na bazu se nije izvršila: provjerite da li je vaša baza uopće postoji.");
            }
        }
        
        try {
            //e.printStackTrace();
            statement = connection.createStatement();
            sql_command = "CREATE DATABASE IF NOT EXISTS "+_DATABASE+"";
            //System.out.println(sql_command);
            try {
                statement.executeUpdate(sql_command);
                System.out.println("Baza "+_DATABASE+" je uspješno postavljena.");
                
                //createTables();
                        
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("SQL upit za kreiranje baze "+_DATABASE+" se nije izvršio");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Greška kod zadavanja SQL zahtjeva");
        }

        return connection;
        
    }
    
    
    /*public int countColumnsInTable(String sectionType) {
        int count = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * FROM " + sectionType);
            ResultSetMetaData rsMetaData = rset.getMetaData();
            count = rsMetaData.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Remember to clean up
        return count;
    }*/
    
    /*public void fix_mysql_utf8() throws SQLException
    {
    	DatabaseManager db;
    	//Connection connection;
    	db = new DatabaseManager();
        connection = db.getConnection();
    	sql = "SET NAMES utf8";
        statement = connection.createStatement();
        ResultSet rs;
        statement.executeUpdate(sql);
        
        statement.executeUpdate("set character_set_results=utf8");
    }*/
    
    public String getUser(String un, String pw) throws SQLException {
        String content = "no";
		//try {
			//connection = getConnection();
            // 3. kreiranje naredbe - Statement
            sql = "SELECT id FROM `admin_users` "
            		+ "where username = '" + un + "' and password = '" + pw + "' ";
            //System.out.println(sql);
            //System.out.close();
            statement = connection.createStatement();

            // 4. izvršavanje naredbe, dohvaćanje ResultSeta i njegova obrada
            rs = statement.executeQuery(sql);
            //return "s";

            if(rs.next()) {
                System.out.println("dobiveno...");
                //userInfo(rs.getString("id"), rs.getString("ime"), rs.getString("prezime"), rs.getString("uname"), rs.getString("passwd"));
            	content = "yes";
                return content;
            }
        /*} catch (SQLException ex) {
            if (connection != null) {
                try {
                	connection.close();
                    return content;
                } catch (Exception e) {
                    return content;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return content;
        }*/
        return content;

    }
    
    /*public String insert_sql(String sql){
		return sql;
    	
    }*/
	
}
