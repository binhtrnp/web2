import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// Ánh xạ URL "/survey" để khớp với action của form
@WebServlet("/survey")
public class SurveyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy dữ liệu từ form (Dựa trên thuộc tính name của các input)
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String dob = request.getParameter("dateOfBirth");
        String hearAbout = request.getParameter("hearAbout");

        // Checkbox: nếu được check sẽ trả về "yes", nếu không check sẽ trả về null
        String cdsOK = request.getParameter("cdsOK");
        String emailOK = request.getParameter("emailOK");

        String contactVia = request.getParameter("contactVia");

        // Xử lý logic hiển thị cho checkbox
        String cdsOKText = (cdsOK != null) ? "YES, I'd like that." : "No";
        String emailOKText = (emailOK != null) ? "YES, please send me email announcements." : "No";

        // Thiết lập response
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Tạo giao diện trả về (Tương tự trang "Thanks for joining...")
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Survey Results</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; width: 600px; margin: 2em auto; }");
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

        // Chỉ hiển thị DOB nếu người dùng có nhập
        if (dob != null && !dob.trim().isEmpty()) {
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

        out.println("<p>To enter another email address, click on the Back button in your browser or the Return button shown below.</p>");
        out.println("<form action='index.html' method='get'>");
        out.println("<input type='submit' value='Return'>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
    }

    // Nếu người dùng truy cập trực tiếp bằng GET, chuyển hướng về trang chủ
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }
}