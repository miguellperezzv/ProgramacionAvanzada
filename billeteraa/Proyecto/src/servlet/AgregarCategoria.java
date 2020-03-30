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
 * Servlet implementation class AgregarCategoria
 */
@WebServlet("/AgregarCategoria")
public class AgregarCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarCategoria() {
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
		salida.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'>");
		salida.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css' integrity='sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp' crossorigin='anonymous'>");
		salida.println("<body>");
		System.out.println("AgregarCategoria servlet 39");
		ModificarDB db = new ModificarDB();
		db.insertarNuevaCategoria(request.getParameter("categoria"));
		salida.println("Accion realizada!");
		salida.println("<br><input type=\"button\" class=\"btn btn-primary\" value=\"Volver a la pagina principal\" OnClick=\"location.href= '/Proyecto/index.jsp'\">\n");
		salida.println("<input type=\"button\"  class=\"btn btn-primary\" value=\"Agregar otra cuenta\" OnClick=\"location.href= 'AgregarCuenta.html'\">\n" );
		salida.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js' integrity='sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa' crossorigin='anonymous'></script>");
		salida.println("</body></html>");

	}

}
