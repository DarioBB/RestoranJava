package restoran.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import restoran.dao.DatabaseManager;

public class AdminData {

	private final String _LNG = "hr";
	private String t_categories;
	private String sql;
	private PreparedStatement ps;
	private Statement st;
	private DatabaseManager db;
	private Connection connection;
	//private ResultSet rs;
	public String content;
	
	public AdminData(){
		t_categories = "categories";
		//this.content = "";
		ps = null;
		
		db = new DatabaseManager();
        connection = db.getConnection();
	}
	
	public LinkedHashMap<String,String> getData(String table, int id, String table2) throws SQLException{
		
		ResultSet resultSet = null;
		
		if(table2 != ""){ // site_photos or site_files
			sql = "select * from "+table2+" where table_name = '"+table+"' and table_id = "+id+" ";
		}
		else 
		{
			sql = "select * from "+table+" where id = "+id+" ";
		}
		//System.out.println("sql: "+sql);
		st = connection.createStatement();
		resultSet = st.executeQuery(sql);
		
		LinkedHashMap<String,String> map = new LinkedHashMap();
		
		ResultSetMetaData rsMetaData = resultSet.getMetaData();
        int count = rsMetaData.getColumnCount();
        int j = 1;
        String cnt;
        if(table2 != ""){ // site_photos or site_files
        	int m = 0;
        	while (resultSet.next()) {
			  //String ids = resultSet.getString(i);
		      for (int i = 1; i <= count; i++) {
	            String val = resultSet.getString(i);
	            //System.out.println(j+"photos or files: "+val);
	            map.put(rsMetaData.getColumnLabel(i)+"_"+resultSet.getString(1), val);
	            j++;
		      }
		      m++;
		    }
        	cnt = ""+m;
        	map.put("count", cnt);
        }
        else 
        {
        	int m = 0;
        	while (resultSet.next()) {
  			  //String ids = resultSet.getString(i);
  		      for (int i = 1; i <= count; i++) {
  	            String val = resultSet.getString(i);
  	            //System.out.println(j+": "+val);
  	            map.put(rsMetaData.getColumnLabel(i), val);
  	            j++;
  		      }
  		      //map.put("id", ids);
  		      //map.put("Zara", "34");
  		      m++;
  		    }
        	cnt = ""+m;
        	map.put("count", cnt);
        }
		
		//request.setAttribute("data_all", map);
		//servletContext.setAttribute("data_all", map);
		
		return map;
	}
	
}
