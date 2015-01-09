package restoran.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import restoran.dao.DatabaseManager;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet("/Auth")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AuthServlet() {
        // TODO Auto-generated constructor stub
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            //ServletContext c = getServletContext();
            //String uname = c.getInitParameter("uname");
            //String passwd = c.getInitParameter("passwd");
            
            DatabaseManager db = new DatabaseManager();
            Connection connection = db.getConnection();
            
            String un = request.getParameter("uname");
            String pw = request.getParameter("passwd");
            
            String rez = db.getUser(un, pw);
            out.println("<h1>"+rez+"</h1>");
            out.println("<h1>"+un+"</h1>");
            out.println("<h1>"+pw+"</h1>");
            if( rez.equals("yes"))
            {
                //String rez = db.getUser(un, pw);
                //String ime = request.getParameter("uname");
                HttpSession session = request.getSession();
                session.setAttribute("user", un);
                session.setAttribute("pass", pw);
                Hashtable<String, String> shop_artikli = new Hashtable<String, String>();
                session.setAttribute("kosarica", shop_artikli);
                
                //out.println("<h1>aa bb: "+un+"</h1>");
                //request.setAttribute("msg", "Bok ");
                //RequestDispatcher rd = getServletContext().getRequestDispatcher("/Nesto");
                //rd.forward(request, response);
                response.sendRedirect("admin/main.jsp");
            }
            else 
            {
                HttpSession session = request.getSession();
                session.setAttribute("user", null);
                session.setAttribute("pass", null);
                
                //response.sendRedirect(request.getContextPath()+"/admin/");
            }
            
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("index.jsp");
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
