package restoran.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import restoran.dao.DatabaseManager;

public class AdminCategories {

	private final String _LNG = "hr";
	private String t_categories;
	private String sql;
	private PreparedStatement ps;
	private DatabaseManager db;
	private Connection connection;
	//private ResultSet rs;
	public String content;
	
	public AdminCategories(){
		t_categories = "categories";
		//this.content = "";
		ps = null;
		
		db = new DatabaseManager();
        connection = db.getConnection();
	}
	
	public static String repeat(String s, int n) {
	    if (s == null) {
	        return null;
	    }
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < n; i++) {
	        sb.append(s);
	    }
	    return sb.toString();
	}

	
	public String display_tree_select2(int parent, int level, int curr_id) throws SQLException{
		
		//this.content = "";
		ResultSet rs;
		
		sql = "SELECT k.id as id, k.title_"+this._LNG+" as title FROM "+this.t_categories+" k WHERE k.parent_id = ? ";
		//System.out.println(sql);
		ps = (PreparedStatement) connection.prepareStatement(sql);
		ps.setInt(1, parent);
		rs = ps.executeQuery();
		while(rs.next()){
			
			String str = "&nbsp;&nbsp;";
			if(level > 1){
				str = repeat(str, level*2)+"|__";
			}
			else {
				str = "";
			}
			String sel = ( curr_id == rs.getInt("id") ) ? " selected=\"selected\"" : "";
			this.content += "<option value=\""+rs.getInt("id")+"\""+sel+">"+str+""+rs.getString("title")+"</option>";
			
			this.display_tree_select2(rs.getInt("id"), level+1, curr_id);
			//System.out.println("rs.getId: "+rs.getInt("id")+", level_new: "+level_new+", curr_id: "+curr_id);
		}
		//sql = "SELECT k.id as id, k.title_"+this._LNG+" as title FROM "+this.t_categories+" k WHERE k.parent_id = "+ parent+" ";
		//System.out.println(sql);

		return this.content;
	}
	
}
