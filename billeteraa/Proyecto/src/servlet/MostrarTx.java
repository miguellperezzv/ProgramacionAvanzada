package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.ModificarDB;

/**
 * Servlet implementation class MostrarTx
 */
@WebServlet("/MostrarTx")
public class MostrarTx extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarTx() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter salida=response.getWriter();
		salida.println("<html>");
		salida.println("<link rel=\"stylesheet\" href=\"/Proyecto/CSS/BasePaginaCSS.css\">");
		salida.println("<link rel=\"stylesheet\" href=\"/Proyecto/CSS/MostrarTxCSS.css\">");
		salida.println("<body>");
		salida.println("<p><b>Fecha solicitada:</b> "+request.getParameter("Fecha")+"<br>");
		salida.println("<b>Orden solicitado:</b> "+request.getParameter("Ordenar")+"<p><br>");
		salida.println("<table>"
				+ "<tr>"
				+ "<th>Titulo</th>"
				+ "<th>Descripcion</th>"
				+ "<th>Categoria</th>"
				+ "<th>Cuenta</th>"
				+ "<th>Valor</th>"
				+ "<th>Fecha</th>"
				+ "</tr>");
		ModificarDB db = new ModificarDB();
		ResultSet rs = db.getTablaTx(request.getParameter("Ordenar"),request.getParameter("Fecha"));
		ArrayList<String> titulo= new ArrayList<String>();
		ArrayList<String> descripcion= new ArrayList<String>();
		ArrayList<String> categoria= new ArrayList<String>();
		ArrayList<String> cuenta= new ArrayList<String>();
		ArrayList valor= new ArrayList();
		ArrayList<String> fecha= new ArrayList<String>();
		
		
		try {
			while(rs.next()) {
				titulo.add(rs.getString("titulo"));
				descripcion.add(rs.getString("descripcion"));
				categoria.add(rs.getString("categoria"));
				cuenta.add(rs.getString("cuenta"));
				valor.add(rs.getDouble("valor"));
				fecha.add(rs.getString("fecha"));

			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] tituloA= new String[titulo.size()];
		String[] descripcionA= new String[descripcion.size()];
		String[] categoriaA= new String[categoria.size()];
		String[] cuentaA= new String[cuenta.size()];
		Double[] valorA= new Double[valor.size()];
		String[] fechaA= new String[fecha.size()];

		tituloA=titulo.toArray(tituloA);
		descripcionA=descripcion.toArray(descripcionA);
		categoriaA=categoria.toArray(categoriaA);
		cuentaA=cuenta.toArray(cuentaA);
		valorA=(Double[]) valor.toArray(valorA);
		fechaA=fecha.toArray(fechaA);

		for (int i=0; i<tituloA.length;i++) {
			salida.println("<tr>"
					+ "<td>"+tituloA[i]+"</td>"
					+ "<td>"+descripcionA[i]+"</td>"
					+ "<td>"+categoriaA[i]+"</td>"
					+ "<td>"+cuentaA[i]+"</td>"
					+ "<td>"+valorA[i]+"</td>"
					+ "<td>"+fechaA[i]+"</td>"
							+ "</tr>");
		}
		salida.println("</table>");
		salida.println("<input type=\"button\" value=\"Volver\"\n" + 
				"			OnClick=\"location.href= '/Proyecto/Vista/OrdenMostrarTx.html'\">");
		
		salida.println("<input type=\"button\" value=\"Menu Principal\"\n" + 
				"			OnClick=\"location.href= '/Proyecto/index.jsp'\">");
		salida.println("</body></html>");
		

			
		

	}

}
