package pp.com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SpeedCalculatorServlet")
public class SpeedCalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameters from the form
        double distance = Double.parseDouble(request.getParameter("distance"));
        double time = Double.parseDouble(request.getParameter("time"));

        // Calculate speed
        double speed = distance / time;

        // Forward the result to the JSP page
        request.setAttribute("speed", speed);
        request.getRequestDispatcher("result.jsp").forward(request, response);
	}
}