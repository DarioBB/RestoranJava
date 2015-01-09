package restoran.admin;

import restoran.config.*;
import restoran.dao.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 * Servlet implementation class AdminCrud
 */
@WebServlet("/AdminCrudServlet")
@MultipartConfig
public class AdminCrudServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DatabaseManager db;
	private Connection connection;
	private PreparedStatement ps;
	private Statement st;
	private String sql;
	private String table;
    private int table_id;
    private String save_type;
    private Functions fn;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCrudServlet() {
        //super();
        // TODO Auto-generated constructor stub
    	db = new DatabaseManager();
        connection = db.getConnection();
        fn = new Functions();
        /*try {
			db.fix_mysql_utf8();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
    
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws  ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        /*PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //System.out.println("test");
        String title_hr = (String) request.getParameter("title_hr");
        out.println("testttt title_hr: "+title_hr);*/
        
        Config conf = new Config();
		try {
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        sql = "";
	        table = "";
	        table_id = 0;
	        save_type = "";
	        int w_1 = 0;
	        int h_1 = 0;
	        int w_2 = 0;
	        int h_2 = 0;
	        String[] skip_arr = {"id","table","w_1","h_1","w_2","h_2","spremi1","spremi2","spremi3"};
	        
	        int counter = 0;
	        for (FileItem item : items) 
	        {
	        	
	        	if (item.isFormField()) {
	        		String fieldname = item.getFieldName();
	                String fieldvalue = item.getString();
	                if(fieldname.equals("id")){
	                	table_id = Integer.parseInt(fieldvalue);
	        		}
	                if(fieldname.equals("table")){
	        			table = fieldvalue;
	        		}
	                if( fieldname.equals("spremi1") || fieldname.equals("spremi2") || fieldname.equals("spremi3") ){
	                	save_type = fieldname;
	        		}
	        	}
	        	
	            if (item.isFormField()) {
	                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
	                String fieldname = item.getFieldName();
	                String fieldvalue = item.getString();
	                //System.out.println("dobivena polja: "+ fieldname+", "+fieldvalue);
	                //String zarez_dod = ", ";
	                String zarez_dod = "";
	                String zarez_dod2 = "";
	                if(table_id == 0){
		                zarez_dod = "";
		                zarez_dod2 = ",";
	                } else {
						zarez_dod = (counter > 0) ? ", " : "";
						zarez_dod2 = "";
	                }
	                boolean containing = Arrays.asList(skip_arr).contains(fieldname);
	                if(containing == false)
	                {
	                	fieldvalue = fn.char_to_utf8(fieldvalue);
	                	sql += ""+zarez_dod+"`"+fieldname+"` = '"+fieldvalue+"'"+zarez_dod2+" ";
	                	counter++;
	                }
	                
	            }
	        }
	        
	        //System.out.println("Polja: "+sql);
	        table_id = save_items(table, table_id, sql); // SPREMA SVE U TABLICU
	        //System.out.println("tejbl ajdi ID: "+table_id);
	        
	        for (FileItem item : items) 
	        {
	        	
	        	if (item.isFormField()) {
	        		String fieldname = item.getFieldName();
	                String fieldvalue = item.getString();
	                if(fieldname.equals("table")){
	        			table = fieldvalue;
	        		}
	        		if(fieldname.equals("w_1")){
	        			w_1 = Integer.parseInt(fieldvalue);
	        		}
	        		if(fieldname.equals("h_1")){
	        			h_1 = Integer.parseInt(fieldvalue);
	        		}
	        		if(fieldname.equals("w_2")){
	        			w_2 = Integer.parseInt(fieldvalue);
	        		}
	        		if(fieldname.equals("h_2")){
	        			h_2 = Integer.parseInt(fieldvalue);
	        		}
	        	}
	        	
	            if (item.isFormField()) {
	                //
	            } else {
	                // Process form file field (input type="file").
	            	try {
		                String fieldname = item.getFieldName();
		                String filename = FilenameUtils.getName(item.getName());
		                String filename_for_docs = filename;
		                //System.out.println("slike i dokumenti: "+ fieldname+", "+filename);
		                if(fieldname.startsWith("img_") && (!filename.equals("")) )
		                {
			                BufferedImage img = ImageIO.read(item.getInputStream()); 
			                // ... (do your job here)
			                //System.out.println("slkeee: "+ fieldname+", "+filename);
		                
			                /*ServletContext context = getServletContext();
			                InputStream is = context.getResourceAsStream("/");
			                
			                String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			                absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
			                
			                String big = "http://"+request.getServerName()+":8080"+request.getContextPath()+"/web/upload_data/site_photos/"+filename;
			                //String big = "C:\\Users\\Dario\\Documents\\workspace\\RestoranJava\\web\\upload_data\\site_photos\\"+filename;
			                */
			                /*System.out.println("big: "+big+", aaa:"+context.getContextPath()+" bb: "+request.getServerName());
			                //System.out.println("abs path: "+absolutePath);
			                System.out.println("abs path: "+System.getProperty("user.dir"));
			                System.out.println("eeeee: "+new File("./").getAbsolutePath());
			                */
			                String big = conf._SITE_ROOT+"upload_data\\site_photos\\"+filename;
			                String thumb = conf._SITE_ROOT+"upload_data\\site_photos\\th_"+filename;
			                
			                String filename_to_write = "";
			                File f = new File(big);
			                if(f.exists()){
			                	//Random rand = new Random();
			                	//int num = rand.nextInt(90000000) + 10000000;
			                	long num = System.currentTimeMillis();
			                	big = conf._SITE_ROOT+"upload_data\\site_photos\\"+num+"_"+filename;
			                	thumb = conf._SITE_ROOT+"upload_data\\site_photos\\th_"+num+"_"+filename;
			                	
			                	filename_to_write = num+"_"+filename;
			                }
			                else 
			                {
			                	filename_to_write = filename;
			                }
			                
			                String temp = new String(big);
			                temp.toLowerCase();
			                String ext = null;
			                if(temp.endsWith(".png")){
			                	ext = "png";
			                }
			                else if(temp.endsWith(".gif")){
			                	ext = "gif";
			                }
			                else if(temp.endsWith(".jpeg")){
			                	ext = "jpg";
			                }
			                else if(temp.endsWith(".jpg")){
			                	ext = "jpg";
			                }
			                
		                	int w = img.getWidth(null);
			                int h = img.getHeight(null);  
			                BufferedImage scaled = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);       
			                Graphics2D g = scaled.createGraphics();
			                g.drawImage(img,0,0,null); 
			                if(g != null) g.dispose();
							ImageIO.write(scaled,ext, new File(big));
			                //System.out.println("dobivena polja (upload): "+ fieldname+", "+filename);
	
			                if(w_1 > 0)
			                {
				                Thumbnails.of(new File(big))
				                .size(w_1, h_1)
				                .outputQuality(1.0)
				                .toFile(new File(big));
			                }
			                
			                if(w_2 > 0)
			                {
				                Thumbnails.of(new File(big))
				                .size(w_2, h_2)
				                .outputQuality(1.0)
				                .toFile(new File(thumb));
			                }
	
			                //System.out.println("");
			                //System.out.println("dobivena polja: "+ fieldname+", "+filename);
			                
			                save_photos(table, table_id, filename_to_write);
		                
		                }
		                else if(fieldname.startsWith("file_") && (!filename_for_docs.equals("")) )
		                {
		                	String filename_to_write = "";
		                	
		                	/*filename_for_docs = filename_for_docs.replace('Š', 'S');
		                	filename_for_docs = filename_for_docs.replace('Đ', 'D');
		                	filename_for_docs = filename_for_docs.replace('Č', 'C');
		                	filename_for_docs = filename_for_docs.replace('Ć', 'C');
		                	filename_for_docs = filename_for_docs.replace('Ž', 'Z');
		                	filename_for_docs = filename_for_docs.replace('š', 's');
		                	filename_for_docs = filename_for_docs.replace('đ', 'd');
		                	filename_for_docs = filename_for_docs.replace('č', 'c');
		                	filename_for_docs = filename_for_docs.replace('ć', 'c');
		                	filename_for_docs = filename_for_docs.replace('ž', 'z');*/
		                	//System.out.println("dsdaasd "+filename_for_docs);
		                	
		                	String file = conf._SITE_ROOT+"upload_data\\site_files\\"+filename_for_docs;
		                	File f = new File(file);
		                	//item.write(f);
		                	filename_to_write = filename_for_docs;
		                	
		                	if(f.exists()){
			                	long num = System.currentTimeMillis();
			                	String file2 = conf._SITE_ROOT+"upload_data\\site_files\\"+num+"_"+filename_for_docs;
			                	File f2 = new File(file2);
			                	item.write(f2);
			                	filename_to_write = num+"_"+filename_for_docs;
			                }
		                	else 
		                	{
		                		item.write(f);
			                	filename_to_write = filename_for_docs;
		                	}
		                   //System.out.println("");
			               //System.out.println("file za zapisati: "+ filename_to_write);
			                
			               save_files(table, table_id, filename_to_write);
		                }
		                
	            	} catch (NullPointerException | IOException e) {
	        			System.out.println(e.getMessage());
	        		} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }
	        
	        //RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/"+table+"_unos.jsp");
	        //rd.forward(request, response);
	    
	        String redirect_path = "";
	        if(save_type.equals("spremi1"))
	        {
	        	redirect_path = "admin/"+table+"_unos.jsp?success=1";
	        }
	        else if(save_type.equals("spremi2"))
	        {
	        	redirect_path = "admin/"+table+"_pregled.jsp?success=1";
	        }
	        else if(save_type.equals("spremi3"))
	        {
	        	redirect_path = "admin/"+table+"_unos.jsp?id="+table_id+"&success=1";
	        }
	        response.sendRedirect(redirect_path);
	        
	    } catch (FileUploadException e) {
	        throw new ServletException("Cannot parse multipart request.", e);
	    }
	}
	
	public int save_items(String table, int table_id, String sql) throws SQLException{
		String table_dod = "";
		String sql_dod = "";
		String sql_dod2 = "";
		if(table_id == 0)
		{
			table_dod = "insert into";
			sql_dod = "";
			sql_dod2 = " created = now() ";
		}
		else 
		{
			table_dod = "update";
			sql_dod = " where id = '"+table_id+"' ";
			sql_dod2 = "";
		}
		sql = ""+table_dod+" "+table+" set "+sql+sql_dod+sql_dod2+" ";
		//System.out.println("Za spremiti u bazu: "+sql);
		st = connection.createStatement();
		//st.executeUpdate(sql);
		st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

		int key = 0;
		if(table_id == 0)
		{
			ResultSet keys = st.getGeneratedKeys();    
			keys.next();  
			key = keys.getInt(1);
		}
		else 
		{
			key = table_id;
		}
		return key;
	}
	
	public void save_photos(String table, int table_id, String filename) throws SQLException{
		if(filename != "")
		{
			String sql = " insert into site_photos set orderby = 0, table_name = ?, table_id = ?, photo_name = ? ";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, table);
			ps.setInt(2, table_id);
			ps.setString(3, filename);
			ps.executeUpdate();
		}
	}
	
	public void save_files(String table, int table_id, String filename) throws SQLException{
		if(filename != "")
		{
			String sql = " insert into site_files set orderby = 0, table_name = ?, table_id = ?, file_name = ? ";
			//System.out.println("Za spremiti u bazu: insert into site_files set orderby = 0, table_name = '"+table+"', table_id = '"+table_id+"', file_name = '"+filename+"' ");
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, table);
			ps.setInt(2, table_id);
			ps.setString(3, filename);
			ps.executeUpdate();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        //try {
			try {
				processRequest(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		/*} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
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
