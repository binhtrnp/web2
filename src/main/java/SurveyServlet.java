import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/survey")
public class SurveyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String firstName = escape(request.getParameter("firstName"));
        String lastName = escape(request.getParameter("lastName"));
        String email = escape(request.getParameter("email"));
        String dob = escape(request.getParameter("dateOfBirth"));
        String hearAbout = escape(request.getParameter("hearAbout"));
        String contactVia = escape(request.getParameter("contactVia"));

        String cdsOK = request.getParameter("cdsOK");
        String emailOK = request.getParameter("emailOK");

        String cdsOKText = (cdsOK != null) ? "YES, I'd like that." : "No";
        String emailOKText = (emailOK != null) ? "YES, please send me email announcements." : "No";

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Survey Results</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; max-width: 600px; margin: 2em auto; padding: 0 1em; }");
        out.println("h1 { color: #008080; }");
        out.println("h2 { color: #008080; margin-top: 1.5em; }");
        out.println("table { border-collapse: collapse; width: 100%; }");
        out.println("td { padding: 0.5em 0; vertical-align: top; }");
        out.println("td.label { font-weight: bold; width: 200px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>Thanks for joining our email list</h1>");
        out.println("<p>Here is the information that you entered:</p>");

        out.println("<table>");
        out.println("<tr><td class='label'>First Name:</td><td>" + firstName + "</td></tr>");
        out.println("<tr><td class='label'>Last Name:</td><td>" + lastName + "</td></tr>");
        out.println("<tr><td class='label'>Email:</td><td>" + email + "</td></tr>");

        if (!dob.isEmpty()) {
            out.println("<tr><td class='label'>Date of Birth:</td><td>" + dob + "</td></tr>");
        }

        out.println("</table>");

        out.println("<h2>Other information:</h2>");
        out.println("<table>");
        out.println("<tr><td class='label'>Heard about us via:</td><td>" + hearAbout + "</td></tr>");
        out.println("<tr><td class='label'>Receive announcements:</td><td>" + cdsOKText + "</td></tr>");
        out.println("<tr><td class='label'>Email announcements:</td><td>" + emailOKText + "</td></tr>");
        out.println("<tr><td class='label'>Contact method:</td><td>" + contactVia + "</td></tr>");
        out.println("</table>");

        out.println("<p>To enter another response, click on the Back button in your browser or the Return button shown below.</p>");
        out.println("<form action='index.html' method='get'>");
        out.println("<input type='submit' value='Return'>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }

    private String escape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}