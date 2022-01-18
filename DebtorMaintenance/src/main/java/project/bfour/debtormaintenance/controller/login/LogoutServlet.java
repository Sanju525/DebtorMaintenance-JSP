package project.bfour.debtormaintenance.controller.login;

import project.bfour.debtormaintenance.customexceptions.UnauthException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("who")!= null) {
            session.removeAttribute("username");
            session.removeAttribute("uid");
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            if (session.getAttribute("who").equals("deb")) {
                session.removeAttribute("who");
                session.invalidate();
                response.sendRedirect("/"); // index
            } else {
                session.removeAttribute("who");
                session.invalidate();
                response.sendRedirect("/admin/login"); // for admin
            }
        } else {
            throw new UnauthException();
        }
    }
}
