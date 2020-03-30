package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.ModificarDB;

/**
 * Servlet implementation class AgregarCuenta
 */
@WebServlet("/AgregarCuenta")
public class AgregarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter salida=response.getWriter();
		salida.println("<html>");
		salida.println("<link rel=\"stylesheet\" href=\"/Proyecto/CSS/BasePaginaCSS.css\">");
		salida.println("<link rel=\"stylesheet\" href=\"/Proyecto/CSS/MostrarTxCSS.css\">");
		salida.println("<body>");
		ModificarDB db = new ModificarDB();
		db.insertarNuevaCuenta(request.getParameter("cuenta"));
		salida.println("Accion realizada!");
		salida.println("<br><input type=\"button\" value=\"Volver a la pagina principal\" OnClick=\"location.href= '/Proyecto/index.jsp'\">\n");
		salida.println("<input type=\"button\" value=\"Agregar otra cuenta\" OnClick=\"location.href= '/Proyecto/Vista/AgregarCuenta.html'\">\n" );
		salida.println("</body></html>");

	}

}
