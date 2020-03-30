package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.ModificarDB;

/**
 * Servlet implementation class InsertarTransaccion
 */
@WebServlet("/InsertarTransaccion")
public class InsertarTransaccion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarTransaccion() {
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
		
		salida.println("<body>");
		System.out.println("Insertando transaccion ");
		ModificarDB db = new ModificarDB();
		SimpleDateFormat formateador = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" , new Locale("es_ES"));
		Date fechaDate = new Date();
		String fecha = formateador.format(fechaDate);
		String titulo = request.getParameter("titulo");
		String categoria  =request.getParameter("Categorias");
		String cuenta  =request.getParameter("Cuenta");
		Double valor=0.0;
		if(request.getParameterMap().containsKey("valorIngreso")) {
			valor =Double.parseDouble(request.getParameter("valorIngreso"));
		}
		
		else if(request.getParameterMap().containsKey("valorEgreso")) {
			valor =Double.parseDouble(request.getParameter("valorEgreso"))*-1;
		}
		
		else {
			System.out.println("error en servlet Insertar transaccion ");
		}
		String descripcion = request.getParameter("descr");	
		
		db.insertarNuevaTransaccion(titulo, categoria, cuenta, valor, fecha, descripcion);
		salida.println("Accion realizada");
		salida.println("<br><input type=\"button\" value=\"Volver a la pagina principal\" OnClick=\"location.href= '/Proyecto/index.jsp'\">\n");
		salida.println("<input type=\"button\" value=\"Agregar nueva transaccion\" OnClick=\"location.href= 'Ingreso.jsp'\">\n" );
		salida.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
