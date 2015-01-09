package restoran.admin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import restoran.config.Config;
import restoran.config.Functions;
import restoran.dao.DatabaseManager;

/**
 * Servlet implementation class AdminActionServlet
 */
@WebServlet("/AdminActionServlet")
public class AdminActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DatabaseManager db;
	private Connection connection;
	private PreparedStatement ps;
	private Statement st;
	private String sql;
	private String table;
    private int table_id;
    private Functions fn;
    
	String rootPath = System.getProperty("catalina.home");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminActionServlet() {
        super();
        db = new DatabaseManager();
        connection = db.getConnection();
        fn = new Functions();
    }
    
    
    private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws  ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8"); 
        
        //System.out.println("root path cataline: "+rootPath);
        
        Config conf = new Config();
        
        if((request.getParameter("delete_content") != null) && (request.getParameter("delete_content_id") != null) )
        {
        	String content_table = request.getParameter("content_table");
        	String table = "";
        	
        	if( (content_table.equals("site_photos")) || (content_table.equals("site_files")) ){
	        	if(content_table.equals("site_photos")){
	        		table = "site_photos";
	        	} else if(content_table.equals("site_files")) {
	        		table = "site_files";
	        	}
	        	
	        	String sql = " delete from "+table+" where id = ? ";
	    		ps = (PreparedStatement) connection.prepareStatement(sql);
	    		ps.setString(1, request.getParameter("delete_content_id"));
	    		ps.executeUpdate();
	    		
		        String file = request.getParameter("delete_content");
		        File f=new File(""+conf._SITE_ROOT+"upload_data/"+table+"/"+file);
		        if(f.exists()){
		        	f.delete();
		        }
		        f=new File(""+conf._SITE_ROOT+"upload_data/"+table+"/th_"+file);
		        if(f.exists()){
		        	f.delete();
		        }
        	}
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
