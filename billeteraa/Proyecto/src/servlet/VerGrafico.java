package servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vista.Modelo;

/**
 * Servlet implementation class VerGrafico
 */
@WebServlet("/VerGrafico")
public class VerGrafico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerGrafico() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		if(Integer.parseInt(request.getParameter("SeleccionCuentas"))==0){
			
			PrintWriter salida=response.getWriter();
			salida.println("<html>");
			salida.println("<link rel=\"stylesheet\" href=\"/Proyecto/CSS/BasePaginaCSS.css\">");
			salida.println("<link rel=\"stylesheet\" href=\"/Proyecto/CSS/MostrarTxCSS.css\">");
			salida.println("<body>");
			Modelo model= new Modelo();
			BufferedImage imagen=model.dibujarGrafico12MesesHTML("1200","630");
			
			ImageIO.write(imagen,"png",new File("tmpImage.png"));
			byte[] imageBytes = Files.readAllBytes(Paths.get("tmpImage.png"));
			Base64.Encoder encoder = Base64.getEncoder();

			String encoding = "data:image/png;base64," + encoder.encodeToString(imageBytes);
			salida.println("<input type=\"button\" value=\"Volver\"\n" + 
					"			OnClick=\"location.href= '/Proyecto/Vista/Reportes.html'\">");
			salida.println("<input type=\"button\" value=\"Menu principal\"\n" + 
					"			OnClick=\"location.href= '/Proyecto/index.jsp'\">");
			salida.println("<img src=" + encoding +" width='1200' height='630' >");
			salida.println("</body></html>");

		}else if(Integer.parseInt(request.getParameter("SeleccionCuentas"))==1) {
			
			PrintWriter salida=response.getWriter();
			salida.println("<html>");
			salida.println("<link rel=\"stylesheet\" href=\"BasePaginaCSS.css\">");
			salida.println("<link rel=\"stylesheet\" href=\"MostrarTxCSS.css\">");
			salida.println("<body>");
			salida.println("<h1>Numero de transacciones por cuenta</h1>");
			Modelo model= new Modelo();
			BufferedImage imagen=model.dibujarGraficoTxPorCuentaHTML("775","350");
			
			ImageIO.write(imagen,"png",new File("tmpImage.png"));
			byte[] imageBytes = Files.readAllBytes(Paths.get("tmpImage.png"));
			Base64.Encoder encoder = Base64.getEncoder();

			String encoding = "data:image/png;base64," + encoder.encodeToString(imageBytes);
			salida.println("<img src=" + encoding +" width='775' height='350' >");
			salida.println("<input type=\"button\" value=\"Volver\"\n" + 
					"			OnClick=\"location.href= '/Proyecto/Vista/Reportes.html'\">");
			salida.println("<input type=\"button\" value=\"Menu principal\"\n" + 
					"			OnClick=\"location.href= '/Proyecto/index.jsp'\">");

			salida.println("</body></html>");
			
		}else if(Integer.parseInt(request.getParameter("SeleccionCuentas"))==2) {
			
			PrintWriter salida=response.getWriter();
			salida.println("<html>");
			salida.println("<link rel=\"stylesheet\" href=\"BasePaginaCSS.css\">");
			salida.println("<link rel=\"stylesheet\" href=\"MostrarTxCSS.css\">");
			salida.println("<body>");
			salida.println("<h1>Cantidad de Dinero por Cuenta </h1>");

			Modelo model= new Modelo();
			BufferedImage imagen=model.dibujarcantidadDineroPorCuentaHTML("750","500");
			
			ImageIO.write(imagen,"png",new File("tmpImage.png"));
			byte[] imageBytes = Files.readAllBytes(Paths.get("tmpImage.png"));
			Base64.Encoder encoder = Base64.getEncoder();

			String encoding = "data:image/png;base64," + encoder.encodeToString(imageBytes);
			salida.println("<input type=\"button\" value=\"Volver\"\n" + 
					"			OnClick=\"location.href= '/Proyecto/Vista/Reportes.html'\">");
			salida.println("<input type=\"button\" value=\"Menu principal\"\n" + 
					"			OnClick=\"location.href= '/Proyecto/index.jsp'\">");
			salida.println("<img src=" + encoding +" width='750' height='500' >");
			salida.println("</body></html>");
			
		}
	}

}
